package com.sg.vim.view;

import java.util.Iterator;
import java.util.List;

import org.bson.types.ObjectId;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.browser.Browser;

import com.mongodb.DBObject;
import com.sg.sqldb.utility.SQLRow;
import com.sg.ui.model.DataObjectEditorInput;
import com.sg.vim.VimUtils;
import com.sg.vim.job.DataAssembly;
import com.sg.vim.model.IVIMFields;

public class COCPaperView extends GenericPrintabelView {


    @Override
    protected String getMessageReprintTitle() {
        return "车型一致性证书补打";
    }

    @Override
    protected String getMessageRemoveTitle() {
        return "车型一致性证书作废";
    }

    @Override
    protected String getMessagePrintTitle() {
        return "车型一致性证书打印";
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
                        DataObjectEditorInput input = VimUtils.getCOCInput(cocData, confData,
                                productCodeData, mesRawData,null, vin,_id);
                        input.save();
                        
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };

        }
    }


}
