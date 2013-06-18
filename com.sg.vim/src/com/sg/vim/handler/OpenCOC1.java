package com.sg.vim.handler;

import org.bson.types.ObjectId;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;

import com.mongodb.DBObject;
import com.sg.ui.UIUtils;
import com.sg.vim.model.IVIMFields;

public class OpenCOC1 extends AbstractHandler {

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        IStructuredSelection sel = (IStructuredSelection) HandlerUtil.getCurrentSelection(event);

        if (sel == null || sel.isEmpty()) {
            return null;
        }
        DBObject prod = (DBObject) sel.getFirstElement();

        ObjectId id = (ObjectId) prod.get(IVIMFields.COC_ID);
        if (id == null) {
            return null;
        }

        try {
            UIUtils.open(id, "com.sg.vim.editor.cocinfo", false, false, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
