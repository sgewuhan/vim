package com.sg.vim.cocinfo.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;

import com.mongodb.DBObject;
import com.sg.vim.datamodel.util.VimUtils;

public class CopyCreateConfigCode extends AbstractHandler {

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {

        // 获得当前选择的记录
        IStructuredSelection selection = (IStructuredSelection) HandlerUtil
                .getCurrentSelection(event);
        if (selection == null || selection.isEmpty()) {
            return null;
        }
        Object selected = selection.getFirstElement();
        if (!(selected instanceof DBObject)) {
            return null;
        }

        VimUtils.copyCreateCFG((DBObject) selected);

        return null;
    }

}
