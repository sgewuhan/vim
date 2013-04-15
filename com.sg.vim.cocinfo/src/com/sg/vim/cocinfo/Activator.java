package com.sg.vim.cocinfo;

import java.io.File;
import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.internal.util.BundleUtility;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "com.sg.vim.cocinfo"; //$NON-NLS-1$

	// The shared instance
	private static Activator plugin;
	
	/**
	 * The constructor
	 */
	public Activator() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
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
