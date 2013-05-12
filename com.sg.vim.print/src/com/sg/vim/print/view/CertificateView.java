package com.sg.vim.print.view;

import java.util.HashMap;
import java.util.Iterator;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.BrowserFunction;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.mobnut.commons.util.Utils;
import com.mobnut.db.DBActivator;
import com.mobnut.db.utils.DBUtil;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.sg.ui.UIUtils;
import com.sg.ui.part.view.TableNavigator;
import com.sg.vim.datamodel.IVIMFields;
import com.sg.vim.datamodel.util.VimUtils;

public class CertificateView extends TableNavigator {

    private BrowserFunction printCertResultFunction;
    private DBObject data;
    private Shell reprintShell;
    private Text zzbnInput;
    private Browser certBrowser;

    @Override
    public void createPartControl(Composite parent) {
        parent.setLayout(new FormLayout());
        createBrowser(parent);
        super.createPartControl(parent);
    }

    private void createBrowser(final Composite parent) {
        certBrowser = new Browser(parent, SWT.NONE);
        certBrowser.setUrl("/vert2");
        printCertResultFunction = new BrowserFunction(certBrowser, "printCertResult") {
            public Object function(Object[] arguments) {
                doPrintCertResultCallback(arguments);
                return null;
            }
        };

        FormData fd = new FormData();
        certBrowser.setLayoutData(fd);
        fd.top = new FormAttachment(0, 0);
        fd.left = new FormAttachment(0, 0);
        fd.width = 1;
        fd.height = 1;
    }

    @Override
    public void dispose() {
        printCertResultFunction.dispose();
        super.dispose();
    }

    private void doPrintCertResultCallback(Object[] arguments) {
        if (arguments != null) {
            // jsreturn,Veh_ErrorInfo,Veh_Clztxx,VehCert.Veh_Zchgzbh,VehCert.Veh_Jyw,
            // VehCert.Veh_Dywym

            // Object jsReturn = arguments[0];
            Object mVeh_ErrorInfo = arguments[1];
            // Object mVeh_Clztxx = arguments[2];
            Object mVeh__Wzghzbh = arguments[3];
            Object mVeh_Jyw = arguments[4];
            Object mVeh_Veh_Dywym = arguments[5];
            if (!Utils.isNullOrEmptyString(mVeh_ErrorInfo)) {
                UIUtils.showMessage(getSite().getShell(), "合格证补打", "您选中的合格证补打发生错误：\n"
                        + mVeh_ErrorInfo, SWT.ICON_ERROR | SWT.OK);
            } else {
                String lc = (String) data.get(IVIMFields.LIFECYCLE);
                BasicDBObject info = new BasicDBObject();
                info.put(IVIMFields.mVeh__Wzghzbh, mVeh__Wzghzbh);
                info.put(IVIMFields.mVeh_Jyw, mVeh_Jyw);
                info.put(IVIMFields.mVeh_Dywym, mVeh_Veh_Dywym);
                // 尚未上传
                if (IVIMFields.LC_PRINTED.equals(lc)) {
                    VimUtils.saveRePrintData(data, info);
                } else {
                    VimUtils.saveRePrintData(data, null);
                }
            }
        }
        if(reprintShell!=null&&!reprintShell.isDisposed()){
            reprintShell.dispose();
        }

    }

    public void doRePrint(DBObject data) {
        this.data = data;

        String lc = (String) data.get(IVIMFields.LIFECYCLE);
        Shell shell = getSite().getShell();
        if (!IVIMFields.LC_PRINTED.equals(lc) && !IVIMFields.LC_UPLOADED.equals(lc)) {
            UIUtils.showMessage(shell, "合格证补打", "您选中的合格证不满足补打的条件", SWT.ICON_ERROR | SWT.OK);
        }

        // 显示补打对话框
        reprintShell = new Shell(shell, SWT.APPLICATION_MODAL | SWT.BORDER);
        reprintShell.setLayout(new FormLayout());

        Label label = new Label(reprintShell, SWT.NONE);
        label.setText("请输入新的合格证纸张编号:");
        FormData fd = new FormData();
        label.setLayoutData(fd);
        fd.top = new FormAttachment(0, 10);
        fd.left = new FormAttachment(0, 10);
        // 创建一个文本输入框
        zzbnInput = new Text(reprintShell, SWT.BORDER);
        fd = new FormData();
        zzbnInput.setLayoutData(fd);
        fd.top = new FormAttachment(label, 10);
        fd.left = new FormAttachment(0, 10);
        fd.width = 240;
        fd.right = new FormAttachment(100, -10);

        // 取出当前的纸张编号值
        int num = VimUtils.getMaxPaperOfCert();
        String s = String.format("%" + 0 + 7 + "d", num);
        zzbnInput.setText(s);

        // 创建补打按钮
        Button printButton = new Button(reprintShell, SWT.PUSH);
        printButton.setText("确定");
        fd = new FormData();
        printButton.setLayoutData(fd);
        fd.top = new FormAttachment(zzbnInput, 10);
        fd.left = new FormAttachment(0, 10);
        fd.bottom = new FormAttachment(100, -10);
        printButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                // 设置纸张编号
                try {
                    setHGZPaperNumber();
                    // 设置打印机数据
                    setPrinter();

                    VimUtils.setValues(certBrowser, CertificateView.this.data);
                    VimUtils.rePrint(certBrowser);
                } catch (Exception e1) {
                    UIUtils.showMessage(reprintShell, "合格证补打",
                            "您选中的合格证补打发生错误：\n" + e1.getMessage(), SWT.ICON_ERROR | SWT.OK);
                }
            }
        });

        Button closeButton = new Button(reprintShell, SWT.PUSH);
        closeButton.setText("取消");
        fd = new FormData();
        closeButton.setLayoutData(fd);
        fd.top = new FormAttachment(zzbnInput, 10);
        fd.left = new FormAttachment(printButton, 10);
        fd.bottom = new FormAttachment(100, -10);
        closeButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                reprintShell.dispose();
            }

        });

        reprintShell.setLocation(200, 200);
        reprintShell.pack();
        reprintShell.open();

    }

    void setPrinter() throws Exception {
        HashMap<String, String> printerPara = VimUtils
                .getPrinterParameters(IVIMFields.PRINTER_FUNCTIONS[0]);
        if (printerPara == null) {
            throw new Exception("没有对" + IVIMFields.PRINTER_FUNCTIONS[0] + "设置打印机");
        }
        Iterator<String> iter = printerPara.keySet().iterator();
        while (iter.hasNext()) {
            String key = iter.next();
            Object value = printerPara.get(key);
            data.put(key, value);
        }
    }

    void setHGZPaperNumber() throws Exception {
        String text = zzbnInput.getText();
        if (!Utils.isNumbers(text)) {
            throw new Exception("需要输入合法的数字");
        }
        Integer inputPageNumber = Integer.parseInt(text);

        DBCollection ids = DBActivator.getCollection("appportal", "ids");
        String pnum;
        if (inputPageNumber != null) {
            int intValue = inputPageNumber.intValue();
            int curId = DBUtil.getCurrentID(ids, "Veh_Zzbh");
            if (curId > intValue) {
                throw new Exception("输入的纸张编号已被占用");
            }
            pnum = String.format("%07d", intValue);
        } else {
            pnum = DBUtil.getIncreasedID(ids, "Veh_Zzbh", "0", 7);
        }
        data.put(IVIMFields.mVeh_Zzbh, pnum);
    }

}
