package com.sg.vim.print.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.handlers.HandlerUtil;

import com.mongodb.DBObject;
import com.sg.vim.print.view.CertificateView;

public class Reprint extends AbstractHandler {


    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        // 对于已打印，未上传的合格证，再继续打印的话，可以更改纸质编号，记录打印日期，并保留历史数据
        // 2、对于已上传如果不修改任何内容只改纸质编号的话可以补打
        IStructuredSelection sel = (IStructuredSelection) HandlerUtil.getCurrentSelection(event);
        if (sel == null || sel.isEmpty()) {
            return null;
        }
        
        IWorkbenchPart part = HandlerUtil.getActivePart(event);
        DBObject data = (DBObject) sel.getFirstElement();
        if(part instanceof CertificateView){
            ((CertificateView)part).doRePrint(data);
        }
        return null;
    }
        
 

}
