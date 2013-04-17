package com.sg.vim.sync;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.IJobChangeListener;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class Sync extends AbstractUIPlugin implements IJobChangeListener {

  // The plug-in ID
  public static final String PLUGIN_ID = "com.sg.vim.sync"; //$NON-NLS-1$

  private static final int DELAYHOURS = 8;

  // The shared instance
  private static Sync plugin;

  private Job job;

  /**
   * The constructor
   */
  public Sync() {
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
   */
  public void start(BundleContext context) throws Exception {
    super.start(context);
    plugin = this;
    startSyncProductCodeData();
  }

  private void startSyncProductCodeData() {
    job = new Job("SyncProductCodeData"){

      @Override
      protected IStatus run(IProgressMonitor monitor) {
        // TODO Auto-generated method stub
        return Status.OK_STATUS;
      }
      
    };
    job.addJobChangeListener(this);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
   */
  public void stop(BundleContext context) throws Exception {
    plugin = null;
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

  @Override
  public void aboutToRun(IJobChangeEvent event) {
    
  }

  @Override
  public void awake(IJobChangeEvent event) {
    
  }

  @Override
  public void done(IJobChangeEvent event) {
    job.schedule(getDelay());
  }

  private long getDelay() {
    return DELAYHOURS*60*60*1000;
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

}
