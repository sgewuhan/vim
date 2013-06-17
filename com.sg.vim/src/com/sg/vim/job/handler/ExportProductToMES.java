package com.sg.vim.job.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.handlers.HandlerUtil;

import com.mobnut.db.DBActivator;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.sg.ui.UIUtils;
import com.sg.vim.Vim;
import com.sg.vim.datamodel.IVIMFields;
import com.sg.vim.datamodel.util.VimUtils;

public class ExportProductToMES extends AbstractHandler {

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        final Shell activeShell = HandlerUtil.getActiveShell(event);
        UIUtils.showMessage(activeShell, "导出",
                "同步MES中间表的数据，可能需要较长的时间完成同步，系统将在后台运行。\n在没有提示同步完成以前，您对成品码的更改将不会被同步", SWT.ICON_WARNING
                        | SWT.OK);
        final Display diplay = activeShell.getDisplay();
        Job job = new Job("同步MES中间表的数据") {

            @Override
            protected IStatus run(IProgressMonitor monitor) {
                DBCollection collection = DBActivator.getCollection(IVIMFields.DB_NAME,
                        IVIMFields.COL_PRODUCTCODEINFO);
                DBCursor cur = collection.find(new BasicDBObject().append(IVIMFields.COC_ID,
                        new BasicDBObject().append("$ne", null)));
                while (cur.hasNext()) {
                    DBObject product = cur.next();
                    try {
                        VimUtils.mntMesProductInfo(product);
                    } catch (Exception e) {
                        return new Status(Status.ERROR, Vim.PLUGIN_ID, "同步发生错误,成品码为:"
                                + product.get(IVIMFields.E_02), e);
                    }
                }
                return Status.OK_STATUS;
            }
        };
        job.setUser(true);
        job.addJobChangeListener(new JobChangeAdapter() {

            @Override
            public void done(IJobChangeEvent event) {
                diplay.syncExec(new Runnable() {

                    @Override
                    public void run() {
                        UIUtils.showMessage(activeShell, "导出", "同步MES中间表的数据已经完成",
                                SWT.ICON_INFORMATION | SWT.OK);
                    }
                });
            }

        });
        job.schedule();

        return null;
    }

}
