package com.sg.vim.print.view;

import java.util.List;

import org.bson.types.ObjectId;
import org.eclipse.swt.browser.Browser;

import com.mongodb.DBObject;
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

}
