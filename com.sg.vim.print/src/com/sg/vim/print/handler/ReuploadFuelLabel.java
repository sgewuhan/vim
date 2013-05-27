package com.sg.vim.print.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.handlers.HandlerUtil;

import com.sg.vim.print.view.FuelLabelView;

public class ReuploadFuelLabel extends AbstractHandler {

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        // 对于打印后2天内未及时上传的数据为补传，对于正常数据，点击补传，弹出提示框，
        // 对于补传数据，要求填写补传原因
        IStructuredSelection sel = (IStructuredSelection) HandlerUtil.getCurrentSelection(event);
        if (sel == null || sel.isEmpty()) {
            return null;
        }
        
        IWorkbenchPart part = HandlerUtil.getActivePart(event);
        if(part instanceof FuelLabelView){
            ((FuelLabelView)part).doReUpload();
        }
        return null;
    }

}
