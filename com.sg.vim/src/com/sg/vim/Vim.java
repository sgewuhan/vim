package com.sg.vim;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import com.sg.vim.cocinfo.SyncProductCodeData;
import com.sg.vim.datamodel.util.VimUtils;


public class Vim implements BundleActivator {

    private static BundleContext context;
    
    static BundleContext getContext() {
        return context;
    }

    private static boolean debug;
    private String syncTime;
    private SyncProductCodeData job;

    /*
     * (non-Javadoc)
     * 
     * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
     */
    public void start(BundleContext bundleContext) throws Exception {
        Vim.context = bundleContext;
        InputStream is = null;
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(System.getProperty("user.dir") + "/conf/vim.properties");
            is = new BufferedInputStream(fis);
            Properties appProps = new Properties();
            appProps.load(is);
            syncTime = appProps.getProperty("synchronize.time","02:00:00");
            debug = "true".equalsIgnoreCase(appProps.getProperty("debug","false"));
            VimUtils.HARDWAREID  = appProps.getProperty("vidc.hardware");
            VimUtils.HD_USER=appProps.getProperty("vidc.user");
            VimUtils.COC_REPRINT="true".equalsIgnoreCase(appProps.getProperty("reprint.coc"));
            VimUtils.FL_REPRINT="true".equalsIgnoreCase(appProps.getProperty("reprint.fuallabel"));
            VimUtils.LOCAL_CERT_ARRESS = appProps.getProperty("certUpload.localaddress");
            
            VimUtils.debug = debug;
        } catch (Exception e) {
        } finally {
            if (fis != null)
                fis.close();
            if (is != null)
                is.close();
        }
        
        job = new SyncProductCodeData() ;
        job.start(syncTime);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
     */
    public void stop(BundleContext bundleContext) throws Exception {
        job.stop();
        Vim.context = null;
    }

    public static boolean isDebug() {
        return debug;
    }

}
