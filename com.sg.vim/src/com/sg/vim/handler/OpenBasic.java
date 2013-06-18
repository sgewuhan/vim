package com.sg.vim.handler;

import org.bson.types.ObjectId;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;

import com.mobnut.commons.util.Utils;
import com.mobnut.db.DBActivator;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.sg.ui.UIUtils;
import com.sg.vim.model.IVIMFields;

public class OpenBasic extends AbstractHandler {

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        IStructuredSelection sel = (IStructuredSelection) HandlerUtil.getCurrentSelection(event);

        if (sel == null || sel.isEmpty()) {
            return null;
        }
        DBObject prod = (DBObject) sel.getFirstElement();

        String type = (String) prod.get(IVIMFields.F_0_2C1);
        if (Utils.isNullOrEmpty(type)) {
            return null;
        }

        DBCollection col = DBActivator.getCollection(IVIMFields.DB_NAME, "basicinfo");
        DBObject basicData = col.findOne(new BasicDBObject().append(IVIMFields.F_0_2C1, type),new BasicDBObject().append("_id", 1));
        if(basicData==null){
            return null;
        }
        try {
            UIUtils.open((ObjectId) basicData.get("_id"), "com.sg.vim.editor.basicinfo", false, false, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
