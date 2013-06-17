package com.sg.vim.print.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.handlers.HandlerUtil;

import com.sg.vim.print.view.COCPaperView;
import com.sg.vim.print.view.CertificateView;
import com.sg.vim.print.view.FuelLabelView;

public class Reassemably extends AbstractHandler {

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        IWorkbenchPart part = HandlerUtil.getActivePart(event);
        if(part instanceof CertificateView){
            ((CertificateView)part).doReAssembly();
        }else if(part instanceof FuelLabelView){
            ((FuelLabelView)part).doReAssembly();
        }else if(part instanceof COCPaperView){
            ((COCPaperView)part).doReAssembly();

        }
        
        return null;
    }

}
