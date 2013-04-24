package com.sg.vim.sync;

import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

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

/**
 * The activator class controls the plug-in life cycle
 */
public class Sync extends AbstractUIPlugin {

  private static final String _delay = "02:07:00";

  private static final String query = "select materialcode,materialname,vehicletype "
      + "from VI_CBO_ITEMMASTER where factorycode='001' and materialcode like '88%'";

  // The plug-in ID
  public static final String PLUGIN_ID = "com.sg.vim.sync"; //$NON-NLS-1$

  // The shared instance
  private static Sync plugin;

  private Job job;

  private JobChangeAdapter listener;

  /**
   * The constructor
   */
  public Sync() {
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext )
   */
  public void start(BundleContext context) throws Exception {
    super.start(context);
    plugin = this;
    startSyncProductCodeData();
  }

  private void doSync() {
    Commons.LOGGER.info("start sync product code info");
    // 测试查询语句
    DBCollection c = DBActivator.getCollection("appportal", "productcodeinfo");
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

  private void syncItem(DBCollection c, Object materialcode, Object materialname, Object vehicletype) {
    try {
      // 查询有无记录

      DBObject q = new BasicDBObject().append("e_02", materialcode);
      DBCursor cursor = c.find(q);
      int cnt = cursor.count();
      if (cnt != 0) {
        c.insert(new BasicDBObject()
            .append("f_0_2c", vehicletype)
            .append("e_03", materialname)
            .append("e_02", materialcode)
            .append("_cdate", new Date())
            .append("_caccount",
                new BasicDBObject().append("username", "robot-sync").append("userid", "robot-sync")));

      }
    } catch (Exception e) {
      Commons.LOGGER.error("error sync product code info", e);
    }
  }

  private void startSyncProductCodeData() {
    job = new Job("SyncProductCodeData") {

      @Override
      protected IStatus run(IProgressMonitor monitor) {
        doSync();
        return Status.OK_STATUS;
      }

    };
    listener = new JobChangeAdapter() {
      @Override
      public void done(IJobChangeEvent event) {
        job.schedule(getDelay(_delay));
      }
    };
    job.addJobChangeListener(listener);

    long delay = getDelay(_delay);
    Commons.LOGGER.info("service sync product code info start, job will start after " + delay
        + " millseconds.");
    job.schedule(delay);

  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext )
   */
  public void stop(BundleContext context) throws Exception {
    plugin = null;
    job.removeJobChangeListener(listener);
    job.cancel();
    super.stop(context);
  }

  /**
   * Returns the shared instance
   * 
   * @return the shared instance
   */
  public static Sync getDefault() {
    return plugin;
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
      long delay = schedualCal.getTimeInMillis() - currentCal.getTimeInMillis();
      return delay;
    } catch (Exception e) {
    }
    return 24 * 60 * 60 * 1000;

  }

}
