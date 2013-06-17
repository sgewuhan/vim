package com.sg.vim.job.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

import com.sg.vim.job.SyncProductCodeData;

public class ImportProductData extends AbstractHandler {

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        SyncProductCodeData job = new SyncProductCodeData() ;
        job.setUser(true);
        job.start(null);
        return null;
    }

}
