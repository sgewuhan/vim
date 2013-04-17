package com.sg.sqldb;

import java.sql.Connection;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;


/**
 * The activator class controls the plug-in life cycle
 */
public class DDB extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "com.sg.sqldb"; //$NON-NLS-1$

	// The shared instance
	private static DDB plugin;

	private ConnectionManager connMgr;

	private Connection mdb;

//	private Model model;
	
	public static String BASIC_DB = "bdb";
	
	/**
	 * The constructor
	 */
	public DDB() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		initConnection();
//		model = new Model();
	}

	private void initConnection() {
		connMgr = ConnectionManager.getInstance();
		mdb = connMgr.getConnection("mdb");
	}
	
	public Connection getModelConnection(){
		return mdb;
	}
	
//	public Model getModel(){
//		return model;
//	}

	public Connection getConnection(){
		return connMgr.getConnection("bdb");
	}
	
	public Connection getConnection(String dataSourceName){
		if(dataSourceName!=null){
			return connMgr.getConnection(dataSourceName);
		}else{
			return getConnection();
		}
	}
	
	public void freeConnection(String poolName ,Connection connection){
		connMgr.freeConnection(poolName, connection);
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		destoryConnection();
		super.stop(context);
	}

	private void destoryConnection() {
		connMgr.release();		
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static DDB getDefault() {
		return plugin;
	}

}
