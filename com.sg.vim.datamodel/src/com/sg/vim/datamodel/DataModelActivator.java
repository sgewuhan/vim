package com.sg.vim.datamodel;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import com.sg.vim.datamodel.vidcservice.CertificateRequestService;
import com.sg.vim.datamodel.vidcservice.CertificateRequestServiceSoap;

public class DataModelActivator implements BundleActivator {

    public static final String PLUGIN_ID = "com.sg.vim.datamodel.DataModelActivator";
    private static BundleContext context;

    static BundleContext getContext() {
        return context;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
     */
    public void start(BundleContext bundleContext) throws Exception {
        DataModelActivator.context = bundleContext;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
     */
    public void stop(BundleContext bundleContext) throws Exception {
        DataModelActivator.context = null;
    }

    public static CertificateRequestServiceSoap getVIDCService() {
        CertificateRequestServiceSoap service = new CertificateRequestService()
                .getCertificateRequestServiceSoap();
        return service;
    }

}
