package com.sg.vim;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.internal.util.BundleUtility;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;

import com.mobnut.db.DBActivator;
import com.sg.vim.datamodel.util.VimUtils;
import com.sg.vim.job.SyncProductCodeData;


public class Vim extends AbstractUIPlugin {

    public static final String PLUGIN_ID = "com.sg.vim";
    private static BundleContext context;
    
    static BundleContext getContext() {
        return context;
    }

    private static boolean debug;
    private String syncTime;
    private SyncProductCodeData job;
    private static Vim plugin;

    /*
     * (non-Javadoc)
     * 
     * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
     */
    public void start(BundleContext bundleContext) throws Exception {
        Vim.context = bundleContext;
        DBActivator.SKIP_AUTH = true;
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
            VimUtils.LOCAL_SERVER=appProps.getProperty("vidc.localaddress");
            VimUtils.FUELLABEL_USERNAME=appProps.getProperty("flsys.username");
            VimUtils.FUELLABEL_PASSWORD=appProps.getProperty("flsys.password");
            VimUtils.FUELLABEL_OKEY=appProps.getProperty("flsys.okey");
            VimUtils.ENV_USERNAME=appProps.getProperty("env.username");
            VimUtils.ENV_PASSWORD=appProps.getProperty("env.password");
            
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
        plugin = this;

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
     */
    public void stop(BundleContext bundleContext) throws Exception {
        job.stop();
        Vim.context = null;
        plugin = null;
    }

    public static boolean isDebug() {
        return debug;
    }
    
    @Override
    protected void initializeImageRegistry(ImageRegistry reg) {
        // 注册image目录下的所有文件
        Bundle bundle = Platform.getBundle(PLUGIN_ID);
        if (BundleUtility.isReady(bundle)) {

            URL fullPathString = BundleUtility.find(bundle, "image");
            try {
                File folder = new File(FileLocator.toFileURL(fullPathString)
                        .getFile());
                File[] files = folder.listFiles();
                ImageDescriptor imgd;
                String key;
                for (File f : files) {
                    key = f.getName();
                    imgd = AbstractUIPlugin.imageDescriptorFromPlugin(
                            PLUGIN_ID, "image/" + key);
                    reg.put(key, imgd);
                }
            } catch (Exception e) {
            }
        }

        super.initializeImageRegistry(reg);
    }

    public static ImageDescriptor getImageDescriptor(String key) {

        return getDefault().getImageRegistry().getDescriptor(key);
    }

    private static AbstractUIPlugin getDefault() {
        return plugin;
    }

    public static Image getImage(String key) {

        return getDefault().getImageRegistry().get(key);
    }
    

}
