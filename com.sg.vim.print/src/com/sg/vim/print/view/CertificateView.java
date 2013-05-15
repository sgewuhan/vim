package com.sg.vim.print.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.bson.types.ObjectId;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.BrowserFunction;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;

import com.mobnut.commons.util.Utils;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.sg.ui.UIUtils;
import com.sg.ui.model.DataObjectEditorInput;
import com.sg.ui.part.editor.DataObjectEditor;
import com.sg.ui.part.editor.IEditorSaveHandler;
import com.sg.ui.part.view.TableNavigator;
import com.sg.vim.datamodel.IVIMFields;
import com.sg.vim.datamodel.util.VimUtils;

public class CertificateView extends TableNavigator {

    private BrowserFunction printCertResultFunction;
    private DBObject currentPrintData;
    private Browser browser;
    private int startNumber;
    private DataObjectEditor editor;

    @Override
    public void createPartControl(Composite parent) {
        parent.setLayout(new FormLayout());
        createBrowser(parent);
        super.createPartControl(parent);
    }

    private void createBrowser(final Composite parent) {
        browser = new Browser(parent, SWT.NONE);
        browser.setUrl("/vert2");
        printCertResultFunction = new BrowserFunction(browser, "printCertResult") {
            public Object function(Object[] arguments) {
                doPrintCertResultCallback(arguments);
                return null;
            }
        };

        FormData fd = new FormData();
        browser.setLayoutData(fd);
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

    private String getMemo(String title) {
        IInputValidator validator = new IInputValidator() {
    
            @Override
            public String isValid(String newText) {
                if (Utils.isNullOrEmpty(newText)) {
                    return "您必须输入原因";
                }
                return null;
            }
        };
        InputDialog d = new InputDialog(getSite().getShell(), title, "请输入原因", "", validator);
        if (InputDialog.OK != d.open()) {
            return null;
        }
        String memo = d.getValue();
        return memo;
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
                String lc = (String) currentPrintData.get(IVIMFields.LIFECYCLE);
                BasicDBObject info = new BasicDBObject();
                info.put(IVIMFields.mVeh__Wzghzbh, mVeh__Wzghzbh);
                info.put(IVIMFields.mVeh_Jyw, mVeh_Jyw);
                info.put(IVIMFields.mVeh_Dywym, mVeh_Veh_Dywym);
                // 尚未上传
                if (IVIMFields.LC_PRINTED.equals(lc)) {
                    VimUtils.saveRePrintData(currentPrintData, info);
                } else {
                    VimUtils.saveRePrintData(currentPrintData, null);
                }

                getNavigator().getViewer().update(currentPrintData, null);
            }
        }

    }

    private void doRePrint(DBObject data) {
        this.currentPrintData = data;

        String lc = (String) currentPrintData.get(IVIMFields.LIFECYCLE);
        if (!IVIMFields.LC_PRINTED.equals(lc) && !IVIMFields.LC_UPLOADED.equals(lc)) {
            // UIUtils.showMessage(shell, "合格证补打", "您选中的合格证不满足补打的条件", SWT.ICON_ERROR | SWT.OK);
            return;
        }

        HashMap<String, String> printerPara = VimUtils
                .getPrinterParameters(IVIMFields.PRINTER_FUNCTIONS[0]);
        if (printerPara == null) {
            return;
        }

        // 设置纸张编号
        VimUtils.setCurrentPaperCert(startNumber);
        String pnum = String.format("%07d", startNumber);
        currentPrintData.put(IVIMFields.mVeh_Zzbh, pnum);

        // 设置打印机
        Iterator<String> iter = printerPara.keySet().iterator();
        while (iter.hasNext()) {
            String key = iter.next();
            Object value = printerPara.get(key);
            currentPrintData.put(key, value);
        }

        VimUtils.print(browser);

    }

    public void doReUpload() {

        IStructuredSelection selection = getNavigator().getViewer().getSelection();
        if (selection == null || selection.isEmpty()) {
            return;
        }
        String memo = getMemo("合格证补传");
        if (Utils.isNullOrEmpty(memo)) {
            return;
        }

        try {

            List<ObjectId> idList = new ArrayList<ObjectId>();
            List<DBObject> dataList = new ArrayList<DBObject>();
            Iterator<?> iter = selection.iterator();
            while (iter.hasNext()) {
                DBObject dataItem = (DBObject) iter.next();
                idList.add((ObjectId) dataItem.get("_id"));
                dataList.add(dataItem);
            }
            VimUtils.uploadCert2(dataList, memo);
            DBObject setting = VimUtils.saveUpload2Data(idList, memo);
            for (int i = 0; i < dataList.size(); i++) {
                DBObject item = dataList.get(i);
                item.putAll(setting);
            }

            getNavigator().getViewer().update(dataList.toArray(), null);
        } catch (Exception e) {
            UIUtils.showMessage(getSite().getShell(), "合格证补传", e.getMessage(), SWT.ICON_ERROR
                    | SWT.OK);
        }
    }

    public void doRePrint() {
        NumberInput numberShell = new NumberInput(getSite().getShell());
        numberShell.setLocation(200, 200);
        numberShell.pack();
        numberShell.open();
        startNumber = numberShell.getNumber();
        int maxNumber = VimUtils.getCurrentMaxPaperOfCert();
        if (maxNumber > startNumber) {
            UIUtils.showMessage(getSite().getShell(), "合格证补打", "输入的纸张编号已被占用\n请重新补打并设置正确的纸张编号。",
                    SWT.ICON_ERROR | SWT.OK);
        }

        IStructuredSelection selection = getNavigator().getViewer().getSelection();
        Iterator<?> iter = selection.iterator();
        while (iter.hasNext()) {
            DBObject data = (DBObject) iter.next();
            doRePrint(data);
        }
    }

    public void doUpload() {
        IStructuredSelection selection = getNavigator().getViewer().getSelection();
        if (selection == null || selection.isEmpty()) {
            return;
        }

        List<ObjectId> idList = new ArrayList<ObjectId>();
        List<DBObject> dataList = new ArrayList<DBObject>();
        Iterator<?> iter = selection.iterator();
        while (iter.hasNext()) {
            DBObject dataItem = (DBObject) iter.next();
            idList.add((ObjectId) dataItem.get("_id"));
            dataList.add(dataItem);
        }
        try {
            VimUtils.uploadCert(dataList);
            DBObject setting = VimUtils.saveUploadData(idList, "");

            for (int i = 0; i < dataList.size(); i++) {
                DBObject item = dataList.get(i);
                item.putAll(setting);
            }
        } catch (Exception e) {
            UIUtils.showMessage(getSite().getShell(), "合格证上传", e.getMessage(), SWT.ICON_ERROR
                    | SWT.OK);
        }

    }

    public void doCancel() {
        IStructuredSelection selection = getNavigator().getViewer().getSelection();
        if (selection == null || selection.isEmpty()) {
            return;
        }

        String memo = getMemo("合格证撤消");
        if (Utils.isNullOrEmpty(memo)) {
            return;
        }

        List<String> certNumberList = new ArrayList<String>();
        List<ObjectId> idList = new ArrayList<ObjectId>();
        List<DBObject> dataList = new ArrayList<DBObject>();
        Iterator<?> iter = selection.iterator();
        while (iter.hasNext()) {
            DBObject dataItem = (DBObject) iter.next();
            certNumberList.add((String) dataItem.get(IVIMFields.mVeh__Wzghzbh));
            idList.add((ObjectId) dataItem.get("_id"));
            dataList.add(dataItem);
        }
        try {
            VimUtils.deleteCert(certNumberList, memo);
            DBObject setting = VimUtils.saveCancelData(idList, memo);

            for (int i = 0; i < dataList.size(); i++) {
                DBObject item = dataList.get(i);
                item.putAll(setting);
            }
        } catch (Exception e) {
            UIUtils.showMessage(getSite().getShell(), "合格证撤消", e.getMessage(), SWT.ICON_ERROR
                    | SWT.OK);
        }
    }

    public void doEdit() {
        IStructuredSelection selection = getNavigator().getViewer().getSelection();
        if (selection == null || selection.isEmpty()) {
            return;
        }

        final DBObject data = (DBObject) selection.getFirstElement();

        IEditorSaveHandler saveHandler = new IEditorSaveHandler() {

            private String memo;

            @Override
            public boolean doSaveBefore(DataObjectEditorInput input, IProgressMonitor monitor,
                    String operation) throws Exception {

                int yes = UIUtils.showMessage(getSite().getShell(), "更新合格证数据",
                        "您希望使用当前的数据更新至国家合格证服务器吗？\n保存后，系统将更新国家合格证服务器的数据。", SWT.OK | SWT.CANCEL
                                | SWT.ICON_QUESTION);
                if (yes != SWT.OK) {
                    return false;
                }

                memo = input.getData().getText(IVIMFields.mVeh_A_update_memo);
                input.getData().getData().removeField(IVIMFields.mVeh_A_update_memo);
                List<DBObject> certList = new ArrayList<DBObject>();
                certList.add(input.getData().getData());
                VimUtils.updateCert(certList, memo);
                return true;
            }

            @Override
            public boolean doSaveAfter(DataObjectEditorInput input, IProgressMonitor monitor,
                    String operation) throws Exception {
                VimUtils.saveUpdateData((ObjectId) data.get("_id"), memo);
                // data与input同步
                Iterator<String> iter = data.keySet().iterator();
                while (iter.hasNext()) {
                    String key = iter.next();
                    if (key.startsWith("Veh_")) {
                        data.put(key, input.getData().getValue(key));
                    }
                }
                getNavigator().getViewer().update(data, null);
                UIUtils.showMessage(getSite().getShell(), "更新合格证数据", "已提交更新国家合格证服务器的数据。\n编辑器即将关闭。",
                        SWT.OK | SWT.ICON_INFORMATION);
                editor.close(false);
                return true;
            }
        };
        try {
            editor = UIUtils.open((ObjectId) data.get("_id"),
                    "com.sg.vim.print.editor.certificate_edit", true, false, saveHandler);
        } catch (Exception e) {
            UIUtils.showMessage(getSite().getShell(), "合格证修改", e.getMessage(), SWT.ICON_ERROR
                    | SWT.OK);
        }

    }

    public void doRemove() {
        IStructuredSelection selection = getNavigator().getViewer().getSelection();
        if (selection == null || selection.isEmpty()) {
            return;
        }
        
        String memo = getMemo("合格证作废");
        if (Utils.isNullOrEmpty(memo)) {
            return;
        }

        List<ObjectId> idList = new ArrayList<ObjectId>();
        List<DBObject> dataList = new ArrayList<DBObject>();
        Iterator<?> iter = selection.iterator();
        while (iter.hasNext()) {
            DBObject dataItem = (DBObject) iter.next();
            idList.add((ObjectId) dataItem.get("_id"));
            dataList.add(dataItem);
        }
        
        DBObject set = VimUtils.saveRemoveData(idList, memo);
        for (int i = 0; i < dataList.size(); i++) {
            dataList.get(i).putAll(set);
            getNavigator().getViewer().update(dataList.get(i), null);
        }

    }

}
