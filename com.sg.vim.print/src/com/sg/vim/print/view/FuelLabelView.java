package com.sg.vim.print.view;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bson.types.ObjectId;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;

import com.mobnut.commons.util.Utils;
import com.mongodb.DBObject;
import com.sg.sqldb.utility.SQLRow;
import com.sg.ui.UIUtils;
import com.sg.ui.model.DataObjectEditorInput;
import com.sg.vim.datamodel.IVIMFields;
import com.sg.vim.datamodel.util.DataAssembly;
import com.sg.vim.datamodel.util.VimUtils;

public class FuelLabelView extends GenericPrintabelView {

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

    public void doCancel(DBObject firstElement) {
        // TODO Auto-generated method stub

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

}
