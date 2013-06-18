package com.sg.vim.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.handlers.HandlerUtil;

import com.mongodb.DBObject;
import com.sg.vim.view.COCPaperView;

public class ReprintCOC extends AbstractHandler {

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        IStructuredSelection sel = (IStructuredSelection) HandlerUtil.getCurrentSelection(event);
        if (sel == null || sel.isEmpty()) {
            return null;
        }
        
        IWorkbenchPart part = HandlerUtil.getActivePart(event);
        if(part instanceof COCPaperView){
            ((COCPaperView)part).doRePrint((DBObject)sel.getFirstElement());
        }
        return null;
    }

}
