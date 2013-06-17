package com.sg.vim.print.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.handlers.HandlerUtil;

import com.sg.vim.print.view.FuelLabelView;

public class PrintFuelLabel extends AbstractHandler {

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        IStructuredSelection sel = (IStructuredSelection) HandlerUtil.getCurrentSelection(event);
        if (sel == null || sel.isEmpty()) {
            return null;
        }
        
        IWorkbenchPart part = HandlerUtil.getActivePart(event);
        if(part instanceof FuelLabelView){
            ((FuelLabelView)part).doPrint(sel.toArray());
        }
        return null;
    }

}
