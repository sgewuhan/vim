package com.sg.vim.sync;

import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.IJobChangeListener;
import org.eclipse.core.runtime.jobs.Job;

import com.mobnut.commons.Commons;
import com.mobnut.commons.util.Utils;
import com.mobnut.db.DBActivator;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.sg.sqldb.utility.SQLResult;
import com.sg.sqldb.utility.SQLRow;
import com.sg.sqldb.utility.SQLUtil;

public class SyncProductCodeData extends Job implements IJobChangeListener {

    private String time;
    
    private static final String query = "select materialcode,materialname,vehicletype "
            + "from VI_CBO_ITEMMASTER where factorycode='001' and materialcode like '88%'";

    public SyncProductCodeData(String id) {
        super(id);
    }

    @Override
    protected IStatus run(IProgressMonitor monitor) {
        doSync();
        return Status.OK_STATUS;
    }
    
    public void start(String time){
        this.time = time;
        if(time!=null){
            addJobChangeListener(this);
            long delay = getDelay(time);
            Commons.LOGGER
            .info("service sync product code info start, job will start after "
                    + (delay / (1000 * 60)) + " min.");
            schedule(delay);
        }else{
            schedule();
        }
    }

    @Override
    public void aboutToRun(IJobChangeEvent event) {
    }

    @Override
    public void awake(IJobChangeEvent event) {
    }

    @Override
    public void done(IJobChangeEvent event) {
        if(time!=null){
            long delay = getDelay(time);
            Commons.LOGGER
            .info("service sync product code info job will restart after "
                    + (delay / (1000 * 60)) + " min.");
            schedule(delay);        
        }
    }

    @Override
    public void running(IJobChangeEvent event) {
    }

    @Override
    public void scheduled(IJobChangeEvent event) {
    }

    @Override
    public void sleeping(IJobChangeEvent event) {
    }

    private static long getDelay(String timeRule) {

        String[] strTime = timeRule.split(":");
        try {
            int h = Integer.parseInt(strTime[0]);
            int m = Integer.parseInt(strTime[1]);
            int s = Integer.parseInt(strTime[2]);

            Calendar schedualCal = Calendar.getInstance();
            schedualCal.set(Calendar.HOUR_OF_DAY, h);
            schedualCal.set(Calendar.MINUTE, m);
            schedualCal.set(Calendar.SECOND, s);

            Calendar currentCal = Calendar.getInstance();
            if (schedualCal.before(currentCal)) {
                schedualCal.add(Calendar.DAY_OF_MONTH, 1);
            }
            long delay = schedualCal.getTimeInMillis()
                    - currentCal.getTimeInMillis();
            return delay;
        } catch (Exception e) {
        }
        return 24 * 60 * 60 * 1000;

    }
    
    private void doSync() {
        Commons.LOGGER.info("start sync product code info");
        // 测试查询语句
        DBCollection c = DBActivator.getCollection("appportal",
                "productcodeinfo");
        try {
            SQLResult result = SQLUtil.SQL_QUERY("erp", query);
            Iterator<SQLRow> iter = result.iterator();
            while (iter.hasNext()) {
                SQLRow row = iter.next();
                Object materialcode = row.getValue("materialcode");// 成品码
                Object materialname = row.getValue("materialname");// 车型名称
                Object vehicletype = row.getValue("vehicletype");// 公告车型
                if (Utils.isNullOrEmptyString(vehicletype)) {
                    continue;
                }
                syncItem(c, materialcode, materialname, vehicletype);
            }
        } catch (Exception e) {
            Commons.LOGGER.error("error sync product code info", e);
        }
        Commons.LOGGER.info("finish sync product code info");
    }

    private void syncItem(DBCollection c, Object materialcode,
            Object materialname, Object vehicletype) {
        try {
            // 查询有无记录

            DBObject q = new BasicDBObject().append("e_02", materialcode);
            DBCursor cursor = c.find(q);
            int cnt = cursor.count();
            if (cnt == 0) {
                c.insert(new BasicDBObject()
                        .append("f_0_2c1", vehicletype)
                        .append("e_03", materialname)
                        .append("e_02", materialcode)
                        .append("_cdate", new Date())
                        .append("_caccount",
                                new BasicDBObject().append("username",
                                        "robot-sync").append("userid",
                                        "robot-sync")));

            }
        } catch (Exception e) {
            Commons.LOGGER.error("error sync product code info", e);
        }
    }

    public void stop() {
        removeJobChangeListener(this);
        cancel();
    }
}
