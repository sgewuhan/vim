package com.sg.vim.sync;

import java.sql.Connection;
import java.util.Iterator;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import com.sg.sqldb.DDB;
import com.sg.sqldb.utility.SQLResult;
import com.sg.sqldb.utility.SQLRow;
import com.sg.sqldb.utility.SQLUtil;

/**
 * The activator class controls the plug-in life cycle
 */
public class Sync extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "com.sg.vim.sync"; //$NON-NLS-1$

	private static final int DELAYHOURS = 8;

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
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext
	 * )
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
//		testSQLInteface();
//		startSyncProductCodeData();
	}

	private void testSQLInteface() {
		//获得SQL Server连接
		String query = "select materialcode,materialname,vehicletype "
				+ "from VI_CBO_ITEMMASTER where factorycode='001' and materialcode like '88%'";

		//测试查询语句
		try {
			SQLResult result = SQLUtil.SQL_QUERY("erp",query);
			Iterator<SQLRow> iter = result.iterator();
			while(iter.hasNext()){
				SQLRow row = iter.next();
				Object materialcode = row.getValue("materialcode");//成品码
				Object materialname = row.getValue("materialname");//车型名称
				Object vehicletype = row.getValue("vehicletype");//公告车型
				System.out.println(materialcode+">>"+materialname+">>"+vehicletype);
			}
			System.out.println(result.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void startSyncProductCodeData() {
		job = new Job("SyncProductCodeData") {

			@Override
			protected IStatus run(IProgressMonitor monitor) {
				// TODO Auto-generated method stub
				return Status.OK_STATUS;
			}

		};
		listener = new JobChangeAdapter() {
			@Override
			public void done(IJobChangeEvent event) {
				job.schedule(getDelay());
			}
		};
		job.addJobChangeListener(listener);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext
	 * )
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

	private long getDelay() {
		return DELAYHOURS * 60 * 60 * 1000;
	}

}
