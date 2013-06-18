package com.sg.vim.view;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bson.types.ObjectId;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;

import com.mobnut.commons.util.Utils;
import com.mongodb.DBObject;
import com.sg.sqldb.utility.SQLRow;
import com.sg.ui.UIUtils;
import com.sg.ui.model.DataObjectEditorInput;
import com.sg.ui.part.editor.DataObjectEditor;
import com.sg.ui.part.editor.IEditorSaveHandler;
import com.sg.vim.VimUtils;
import com.sg.vim.job.DataAssembly;
import com.sg.vim.model.IVIMFields;

public class FuelLabelView extends GenericPrintabelView {

    private DataObjectEditor editor;

    @Override
    protected String getMessageReprintTitle() {
        return "燃油标识补打";
    }

    @Override
    protected String getMessageRemoveTitle() {
        return "燃油标识作废";
    }

    @Override
    protected String getMessagePrintTitle() {
        return "燃油标识打印";
    }

    @Override
    protected DBObject remove(List<ObjectId> idList, String memo) {
        return VimUtils.saveRemoveData(idList, memo, IVIMFields.COL_FUELABEL);
    }

    @Override
    protected void print(Browser browser, DBObject dbObject) throws Exception {
        VimUtils.printFuelLabel(browser, dbObject);
    }

    @Override
    protected void reprint(Browser browser, DBObject dbObject) throws Exception {
        VimUtils.printFuelLabel(browser, dbObject);
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
            VimUtils.uploadFuelLabel(dataList);
            DBObject setting = VimUtils.saveUploadData(idList, "", IVIMFields.COL_FUELABEL,
                    IVIMFields.ACTION_REC_TYPE_VALUE_UPLOAD);

            for (int i = 0; i < dataList.size(); i++) {
                DBObject item = dataList.get(i);
                item.putAll(setting);
            }
        } catch (Exception e) {
            UIUtils.showMessage(getSite().getShell(), "燃油数据上传", e.getMessage(), SWT.ICON_ERROR
                    | SWT.OK);
        }
    }

    public void doCancel() {
        IStructuredSelection selection = getNavigator().getViewer().getSelection();
        if (selection == null || selection.isEmpty()) {
            return;
        }

        String memo = getMemo("燃油标识撤消");
        if (Utils.isNullOrEmpty(memo)) {
            return;
        }

        List<String> numberList = new ArrayList<String>();
        List<ObjectId> idList = new ArrayList<ObjectId>();
        List<DBObject> dataList = new ArrayList<DBObject>();
        Iterator<?> iter = selection.iterator();
        while (iter.hasNext()) {
            DBObject dataItem = (DBObject) iter.next();
            numberList.add((String) dataItem.get(IVIMFields.F_0_6b));
            idList.add((ObjectId) dataItem.get("_id"));
            dataList.add(dataItem);
        }
        try {
            VimUtils.deleteFuelLabel(numberList, memo);
            DBObject setting = VimUtils.saveCancelData(idList, memo);

            for (int i = 0; i < dataList.size(); i++) {
                DBObject item = dataList.get(i);
                item.putAll(setting);
            }
            
            getNavigator().getViewer().update(dataList.toArray(), null);
        } catch (Exception e) {
            UIUtils.showMessage(getSite().getShell(), "燃油标识撤消", e.getMessage(), SWT.ICON_ERROR
                    | SWT.OK);
        }
    }

    public void doReUpload() {
        IStructuredSelection selection = getNavigator().getViewer().getSelection();
        if (selection == null || selection.isEmpty()) {
            return;
        }
        String memo = getMemo("燃油标识补传");
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
            VimUtils.uploadFuelLabel2(dataList, memo);
            DBObject setting = VimUtils.saveUploadData(idList, memo, IVIMFields.COL_FUELABEL,
                    IVIMFields.ACTION_REC_TYPE_VALUE_UPLOAD2);
            for (int i = 0; i < dataList.size(); i++) {
                DBObject item = dataList.get(i);
                item.putAll(setting);
            }

            getNavigator().getViewer().update(dataList.toArray(), null);
        } catch (Exception e) {
            UIUtils.showMessage(getSite().getShell(), "燃油标识补传", e.getMessage(), SWT.ICON_ERROR
                    | SWT.OK);
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
                        DataObjectEditorInput input = VimUtils.getFLInput(cocData, confData,
                                productCodeData, mesRawData, null, vin, _id);
                        input.save();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };

        }
    }
    
    public void doEdit() {
        IStructuredSelection selection = getNavigator().getViewer().getSelection();
        if (selection == null || selection.isEmpty()) {
            return;
        }
        
        if(editor!=null){
            UIUtils.showMessage(getSite().getShell(), "更新燃油标识数据",
                    "您不能同时更新多条燃油标识数据。", SWT.OK);
        }

        final DBObject data = (DBObject) selection.getFirstElement();

        IEditorSaveHandler saveHandler = new IEditorSaveHandler() {

            private String memo;

            @Override
            public boolean doSaveBefore(DataObjectEditorInput input, IProgressMonitor monitor,
                    String operation) throws Exception {

                int yes = UIUtils.showMessage(getSite().getShell(), "更新燃油标识数据",
                        "您希望使用当前的数据更新至国家燃油标识服务器吗？\n保存后，系统将更新国家燃油标识服务器的数据。", SWT.OK | SWT.CANCEL
                                | SWT.ICON_QUESTION);
                if (yes != SWT.OK) {
                    return false;
                }

                memo = input.getData().getText(IVIMFields.mVeh_A_update_memo);
                input.getData().getData().removeField(IVIMFields.mVeh_A_update_memo);
                List<DBObject> certList = new ArrayList<DBObject>();
                certList.add(input.getData().getData());
                VimUtils.updateFuellabel(certList, memo);
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
                UIUtils.showMessage(getSite().getShell(), "更新燃油标识数据", "已提交更新国家燃油标识服务器的数据。\n编辑器即将关闭。",
                        SWT.OK | SWT.ICON_INFORMATION);
                editor.close(false);
                editor = null;
                return true;
            }
        };
        try {
            editor = UIUtils.open((ObjectId) data.get("_id"),
                    "com.sg.vim.print.editor.fuellabel_edit", true, false, saveHandler);
        } catch (Exception e) {
            UIUtils.showMessage(getSite().getShell(), "更新燃油标识数据", e.getMessage(), SWT.ICON_ERROR
                    | SWT.OK);
        }

    }

}
