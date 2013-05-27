package com.sg.vim.print.view;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bson.types.ObjectId;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;

import com.mongodb.DBObject;
import com.sg.ui.UIUtils;
import com.sg.vim.datamodel.IVIMFields;
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
        return VimUtils.saveRemoveData(idList, memo,IVIMFields.COL_FUELABEL);
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
        IStructuredSelection selection = getNavigator().getViewer()
                .getSelection();
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
            DBObject setting = VimUtils.saveFuelLabelUploadData(idList, "");

            for (int i = 0; i < dataList.size(); i++) {
                DBObject item = dataList.get(i);
                item.putAll(setting);
            }
        } catch (Exception e) {
            UIUtils.showMessage(getSite().getShell(), "燃油数据上传", e.getMessage(),
                    SWT.ICON_ERROR | SWT.OK);
        }        
    }

    public void doCancel(DBObject firstElement) {
        // TODO Auto-generated method stub
        
    }

    public void doReUpload() {
        // TODO Auto-generated method stub
        
    }

    public void doReAssembly() {
        // TODO Auto-generated method stub
        
    }

}
