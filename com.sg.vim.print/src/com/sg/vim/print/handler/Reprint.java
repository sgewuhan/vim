package com.sg.vim.print.handler;

import java.util.HashMap;
import java.util.Iterator;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.BrowserFunction;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.handlers.HandlerUtil;

import com.mobnut.commons.util.Utils;
import com.mobnut.db.DBActivator;
import com.mobnut.db.utils.DBUtil;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.sg.ui.UIUtils;
import com.sg.vim.datamodel.IVIMFields;
import com.sg.vim.datamodel.util.VimUtils;

public class Reprint extends AbstractHandler {

    private Shell reprintShell;
    private Text zzbnInput;

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        // 对于已打印，未上传的合格证，再继续打印的话，可以更改纸质编号，记录打印日期，并保留历史数据
        // 2、对于已上传如果不修改任何内容只改纸质编号的话可以补打
        IStructuredSelection sel = (IStructuredSelection) HandlerUtil.getCurrentSelection(event);
        if (sel == null || sel.isEmpty()) {
            return null;
        }
        DBObject data = (DBObject) sel.getFirstElement();
        String lc = (String) data.get(IVIMFields.LIFECYCLE);
        Shell shell = HandlerUtil.getActiveShell(event);
        if (!IVIMFields.LC_PRINTED.equals(lc) && !IVIMFields.LC_UPLOADED.equals(lc)) {
            UIUtils.showMessage(shell, "合格证补打", "您选中的合格证不满足补打的条件", SWT.ICON_ERROR | SWT.OK);
        }

        rePrint(shell, data);

        return null;
    }

    private void rePrint(Shell parentShell, final DBObject data) {
        // 显示补打对话框
        reprintShell = new Shell(parentShell, SWT.APPLICATION_MODAL | SWT.BORDER);
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

        // 创建一个浏览器
        final Browser certBrowser = new Browser(reprintShell, SWT.NONE);
        certBrowser.setUrl("/vert2");
        final BrowserFunction printCertResultFunction = new BrowserFunction(certBrowser,
                "printCertResult") {
            public Object function(Object[] arguments) {
                doPrintCertResultCallback(reprintShell, data, arguments);
                return null;
            }
        };

        fd = new FormData();
        certBrowser.setLayoutData(fd);
        fd.top = new FormAttachment(zzbnInput, 0);
        fd.left = new FormAttachment(0, 10);
        fd.width = 1;
        fd.height = 1;

        // 创建补打按钮
        Button printButton = new Button(reprintShell, SWT.PUSH);
        printButton.setText("补打");
        fd = new FormData();
        printButton.setLayoutData(fd);
        fd.top = new FormAttachment(certBrowser, 10);
        fd.left = new FormAttachment(0, 10);
        fd.bottom = new FormAttachment(100, -10);
        printButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                // 设置纸张编号
                try {
                    setHGZPaperNumber(data);
                    // 设置打印机数据
                    setPrinter(data);

                    VimUtils.setValues(certBrowser, data);
                    VimUtils.rePrint(certBrowser);
                } catch (Exception e1) {
                    UIUtils.showMessage(reprintShell, "合格证补打",
                            "您选中的合格证补打发生错误：\n" + e1.getMessage(), SWT.ICON_ERROR | SWT.OK);
                }
            }
        });

        Button closeButton = new Button(reprintShell, SWT.PUSH);
        closeButton.setText("关闭");
        fd = new FormData();
        closeButton.setLayoutData(fd);
        fd.top = new FormAttachment(certBrowser, 10);
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
        reprintShell.addDisposeListener(new DisposeListener() {

            @Override
            public void widgetDisposed(DisposeEvent event) {
                printCertResultFunction.dispose();
            }
        });
    }

    protected void setPrinter(DBObject data) throws Exception {
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

    protected void setHGZPaperNumber(DBObject data) throws Exception {
        String text = zzbnInput.getText();
        if (!Utils.isNumbers(text)) {
            throw new Exception("需要输入合法的数字");
        }
        Integer inputPageNumber = Integer.parseInt(text);

        DBCollection ids = DBActivator.getCollection("appportal", "ids");
        String pnum;
        if (inputPageNumber != null) {
            pnum = String.format("%07d", inputPageNumber.intValue());
        } else {
            pnum = DBUtil.getIncreasedID(ids, "Veh_Zzbh", "0", 7);
        }
        data.put(IVIMFields.mVeh_Zzbh, pnum);
    }

    protected void doPrintCertResultCallback(Shell shell, DBObject data, Object[] arguments) {
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
                UIUtils.showMessage(shell, "合格证补打", "您选中的合格证补打发生错误：\n" + mVeh_ErrorInfo,
                        SWT.ICON_ERROR | SWT.OK);
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

    }

}
