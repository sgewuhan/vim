package com.sg.vim.publicdata.handler;

import java.util.Iterator;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.handlers.HandlerUtil;

import com.mongodb.DBObject;
import com.sg.ui.part.view.TableNavigator;
import com.sg.vim.datamodel.util.VimUtils;

public class TransferToCOC extends AbstractHandler {

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        IStructuredSelection sel = (IStructuredSelection) HandlerUtil.getCurrentSelection(event);
        @SuppressWarnings("rawtypes")
        Iterator iter = sel.iterator();
        IWorkbenchPart part = HandlerUtil.getActivePart(event);
        while(iter.hasNext()){
            DBObject dbo = (DBObject) iter.next();
            try {
                VimUtils.syncPublicDataToCOC(dbo);
                
                if(part instanceof TableNavigator){
                    ((TableNavigator) part).getNavigator().getViewer().update(dbo, null);
                }
                
            } catch (Exception e) {
            }
        }
        return null;
    }

}
