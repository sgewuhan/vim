package com.sg.vim.print.view;

import java.util.List;

import org.bson.types.ObjectId;
import org.eclipse.swt.browser.Browser;

import com.mongodb.DBObject;
import com.sg.vim.datamodel.IVIMFields;
import com.sg.vim.datamodel.util.VimUtils;

public class COCPaperView extends GenericPrintabelView {


    @Override
    protected String getMessageReprintTitle() {
        return "����һ����֤�鲹��";
    }

    @Override
    protected String getMessageRemoveTitle() {
        return "����һ����֤������";
    }

    @Override
    protected String getMessagePrintTitle() {
        return "����һ����֤���ӡ";
    }

    @Override
    protected DBObject remove(List<ObjectId> idList, String memo) {
        return VimUtils.saveRemoveData(idList, memo,IVIMFields.COL_COCPAPER);
    }

    @Override
    protected void print(Browser browser, DBObject dbObject) throws Exception {
        VimUtils.printCOC(browser, dbObject);
    }
    

    @Override
    protected void reprint(Browser browser, DBObject dbObject) throws Exception {
        VimUtils.printCOC(browser, dbObject);
    }

    public void doReAssembly() {
        // TODO Auto-generated method stub
        
    }


}
