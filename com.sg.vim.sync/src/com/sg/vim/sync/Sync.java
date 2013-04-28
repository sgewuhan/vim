package com.sg.vim.sync;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class Sync extends AbstractUIPlugin {

	private static final String time = "02:40:00";

	// The plug-in ID
	public static final String PLUGIN_ID = "com.sg.vim.sync"; //$NON-NLS-1$

	// The shared instance
	private static Sync plugin;

	private SyncProductCodeData job;

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
		startSyncProductCodeData();
	}



	private void startSyncProductCodeData() {
		job = new SyncProductCodeData("SyncProductCodeData") ;
		job.start(time);
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
		job.stop();
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


}
