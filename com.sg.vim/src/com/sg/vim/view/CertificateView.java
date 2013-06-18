package com.sg.vim.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.bson.types.ObjectId;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.action.IToolBarManager;
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
import com.sg.sqldb.utility.SQLRow;
import com.sg.ui.UIUtils;
import com.sg.ui.model.DataObjectEditorInput;
import com.sg.ui.part.editor.DataObjectEditor;
import com.sg.ui.part.editor.IEditorSaveHandler;
import com.sg.ui.part.view.TableNavigator;
import com.sg.vim.VimUtils;
import com.sg.vim.job.DataAssembly;
import com.sg.vim.model.IVIMFields;

public class CertificateView extends TableNavigator {

    private final class NumberInputValidator implements IInputValidator {
        @Override
        public String isValid(String newText) {
            if (Utils.isNumbers(newText)) {
                return null;
            } else {
                return "您需要输入合法的数字";
            }
        }
    }

    private BrowserFunction printCertResultFunction;
    private DBObject currentPrintData;
    private Browser browser;
    private int zcStartNumber = -1;
    private int dpStartNumber = -1;

    // private DataObjectEditor editor;
    private DataObjectEditor editor;

    @Override
    public void createPartControl(Composite parent) {
        parent.setLayout(new FormLayout());
        createBrowser(parent);
        super.createPartControl(parent);
        addFilterTools();
    }

    private void addFilterTools() {
        IToolBarManager manager = getToolBarManager();
        manager.add(new ShortcutFilterAction(getNavigator(),"shortcut.cert"));
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

    protected String getMemo(String title) {
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

            Object jsReturn = arguments[0];
            Object mVeh_ErrorInfo = arguments[1];
            // Object mVeh_Clztxx = arguments[2];
            Object mVeh__Wzghzbh = arguments[3];
            Object mVeh_Jyw = arguments[4];
            Object mVeh_Veh_Dywym = arguments[5];
            if ("-1".equals(jsReturn.toString()) && !Utils.isNullOrEmptyString(mVeh_ErrorInfo)) {
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
                    VimUtils.saveRePrintData(currentPrintData, info,IVIMFields.COL_CERF);
                } else {
                    VimUtils.saveRePrintData(currentPrintData, null,IVIMFields.COL_CERF);
                }

                getNavigator().getViewer().update(currentPrintData, null);

            }
        }

    }

    private void doRePrint(DBObject data) {
        this.currentPrintData = data;

        String lc = (String) currentPrintData.get(IVIMFields.LIFECYCLE);
        if (!IVIMFields.LC_PRINTED.equals(lc) && !IVIMFields.LC_UPLOADED.equals(lc)) {
            // UIUtils.showMessage(shell, "合格证补打", "您选中的合格证不满足补打的条件",
            // SWT.ICON_ERROR | SWT.OK);
            return;
        }

        HashMap<String, String> printerPara;

        // 设置纸张编号
        if ("QX".equals(currentPrintData.get(IVIMFields.mVeh_Clztxx))) {
            setZCPaperNumber();
            VimUtils.setCurrentPaperZCCert(zcStartNumber);
            String pnum = String.format("%07d", zcStartNumber);
            currentPrintData.put(IVIMFields.mVeh_Zzbh, pnum);
            printerPara = VimUtils.getPrinterParameters(IVIMFields.PRINTER_FUNCTIONS[0]);
        } else {
            setDPPaperNumber();
            VimUtils.setCurrentPaperDPCert(dpStartNumber);
            String pnum = String.format("%07d", dpStartNumber);
            currentPrintData.put(IVIMFields.mVeh_Zzbh, pnum);
            printerPara = VimUtils.getPrinterParameters(IVIMFields.PRINTER_FUNCTIONS[3]);
        }

        // 设置打印机
        Iterator<String> iter = printerPara.keySet().iterator();
        while (iter.hasNext()) {
            String key = iter.next();
            Object value = printerPara.get(key);
            currentPrintData.put(key, value);
        }

        VimUtils.setValues(browser, currentPrintData);
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
            DBObject setting = VimUtils.saveUploadData(idList, memo, IVIMFields.COL_CERF,
                    IVIMFields.ACTION_REC_TYPE_VALUE_UPLOAD2);
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
        IStructuredSelection selection = getNavigator().getViewer().getSelection();
        Iterator<?> iter = selection.iterator();
        while (iter.hasNext()) {
            DBObject data = (DBObject) iter.next();
            doRePrint(data);
        }
    }

    private void setDPPaperNumber() {
        if (this.dpStartNumber != -1) {
            return;
        }

        int dpStartNumber = getInputDPPaperNumber();

        int maxNumber = VimUtils.getCurrentMaxPaperOfDPCert();
        if (maxNumber > dpStartNumber) {
            int re = UIUtils.showMessage(getSite().getShell(), "合格证补打",
                    "输入的纸张编号已被占用\n选择“是”使用该底盘合格证纸张编号（可能产生重复的纸张编号）\n选择“否”进行重新设置。", SWT.ICON_WARNING
                            | SWT.YES | SWT.NO);
            if (re == SWT.NO) {
                setDPPaperNumber();
                return;
            }
        }
        this.dpStartNumber = dpStartNumber;
    }

    private int getInputDPPaperNumber() {
        int maxPaperOfDPCert = VimUtils.getMaxPaperOfDPCert();
        String initialValue = String.format("%" + 0 + 7 + "d", maxPaperOfDPCert);
        InputDialog input = new InputDialog(getSite().getShell(), "合格证补打",
                "如果您需要设置新的底盘合格证纸张编号，请输入:", initialValue, new NumberInputValidator());
        int open = input.open();
        if (open != InputDialog.OK) {
            return maxPaperOfDPCert;
        }
        int dpStartNumber = Integer.parseInt(input.getValue());
        return dpStartNumber;
    }

    private void setZCPaperNumber() {
        if (this.zcStartNumber != -1) {
            return;
        }
        int zcStartNumber = getInputZCPaperNumber();

        int maxNumber = VimUtils.getCurrentMaxPaperOfZCCert();
        if (maxNumber > dpStartNumber) {
            int re = UIUtils.showMessage(getSite().getShell(), "合格证补打",
                    "输入的纸张编号已被占用\n选择“是”使用该整车合格证纸张编号（可能产生重复的纸张编号）\n选择“否”进行重新设置。", SWT.ICON_WARNING
                            | SWT.YES | SWT.NO);
            if (re == SWT.NO) {
                setZCPaperNumber();
                return;
            }
        }
        this.zcStartNumber = zcStartNumber;
    }
    
    private int getInputZCPaperNumber() {
        int maxPaperOfZCCert = VimUtils.getMaxPaperOfZCCert();
        String initialValue = String.format("%" + 0 + 7 + "d", maxPaperOfZCCert);
        InputDialog input = new InputDialog(getSite().getShell(), "合格证补打",
                "如果您需要设置新的整车合格证纸张编号，请输入:", initialValue, new NumberInputValidator());
        int open = input.open();
        if (open != InputDialog.OK) {
            return maxPaperOfZCCert;
        }
        int zcStartNumber = Integer.parseInt(input.getValue());
        return zcStartNumber;
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
            DBObject setting = VimUtils.saveUploadData(idList, "", IVIMFields.COL_CERF,
                    IVIMFields.ACTION_REC_TYPE_VALUE_UPLOAD);

            for (int i = 0; i < dataList.size(); i++) {
                DBObject item = dataList.get(i);
                item.putAll(setting);
            }

            getNavigator().getViewer().update(dataList.toArray(), null);
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
            DBObject setting = VimUtils.saveCancelData(idList, memo,IVIMFields.COL_CERF);

            for (int i = 0; i < dataList.size(); i++) {
                DBObject item = dataList.get(i);
                item.putAll(setting);
            }

            getNavigator().getViewer().update(dataList.toArray(), null);
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

        if (editor != null) {
            UIUtils.showMessage(getSite().getShell(), "更新合格证数据", "您不能同时更新多条合格证数据。", SWT.OK);
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
                VimUtils.saveUpdateData((ObjectId) data.get("_id"), memo,IVIMFields.COL_CERF);
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
                editor = null;
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

        DBObject set = VimUtils.saveRemoveData(idList, memo, IVIMFields.COL_CERF);
        for (int i = 0; i < dataList.size(); i++) {
            dataList.get(i).putAll(set);
            getNavigator().getViewer().update(dataList.get(i), null);
        }

    }

    public void doReAssembly() {
        IStructuredSelection selection = getNavigator().getViewer().getSelection();
        if (selection == null || selection.isEmpty()) {
            return;
        }

        Iterator<?> iter = selection.iterator();
        while (iter.hasNext()) {
            DBObject dataItem = (DBObject) iter.next();
            final ObjectId _id = (ObjectId) dataItem.get("_id");
            // 取出vin
            final String vin = (String) dataItem.get(IVIMFields.mVeh_Clsbdh);
            new DataAssembly(vin) {

                @Override
                protected void asyncAssemblyDone(IStatus result, SQLRow mesRawData,
                        DBObject productCodeData, DBObject cocData, DBObject confData,
                        DBObject dpcocData, DBObject dpconfData) {
                    try {
                        DataObjectEditorInput input = VimUtils.getCerfInput(cocData, confData,
                                productCodeData, mesRawData, null, vin, false, _id);
                        input.save();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };

        }

    }

}
