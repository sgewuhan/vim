package com.sg.vim.savehandler;

import java.util.Iterator;

import org.bson.types.ObjectId;
import org.eclipse.core.runtime.IProgressMonitor;

import com.mobnut.commons.util.Utils;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.sg.ui.model.DataObjectEditorInput;
import com.sg.ui.part.editor.IEditorSaveHandler;
import com.sg.vim.model.BasicInfo;

public class COCSave implements IEditorSaveHandler {

    private BasicInfo serviceB;

    public COCSave() {
        serviceB = new BasicInfo();

    }

    @Override
    public boolean doSaveBefore(DataObjectEditorInput input, IProgressMonitor monitor,
            String operation) throws Exception {
        return true;
    }

    @Override
    public boolean doSaveAfter(DataObjectEditorInput input, IProgressMonitor monitor,
            String operation) throws Exception {
        //反写到公告车型数据中
        DBObject cocItem = input.getData().getData();
        ObjectId basicInfoId = (ObjectId) cocItem.get("basicinfo_id");
        Iterator<String> iter = cocItem.keySet().iterator();
        BasicDBObject data = new BasicDBObject();
        while(iter.hasNext()){
            String key = iter.next();
            if(Utils.inArray(key, serviceB.getFullFields())){
                data.put(key, cocItem.get(key));
            }
        }
        serviceB.update(basicInfoId, data);
        
        return false;
    }

}
