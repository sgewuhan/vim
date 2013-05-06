package com.sg.vim.print.control;

import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.BrowserFunction;

import com.mobnut.commons.util.Utils;
import com.sg.ui.model.DataObject;
import com.sg.vim.datamodel.util.VimUtils;
import com.sg.vim.print.module.DPCertPrintModule;
import com.sg.vim.print.module.PrintModule;
import com.sg.vim.print.module.QXCertPrintModule;

public class PrintCertResultFunction extends BrowserFunction {

    private DPCertPrintModule dpModule;
    private QXCertPrintModule qxModule;

    public PrintCertResultFunction(Browser browser, String name) {
        super(browser, name);
    }

    @Override
    public Object function(Object[] arguments) {
        // Veh_ErrorInfo,Veh_Clztxx,VehCert.Veh_Zchgzbh,VehCert.Veh_Jyw, VehCert.Veh_Dywym
        if (arguments != null) {
        	Object jsReturn = arguments[0];
        	Object mVeh_ErrorInfo = arguments[1];
            Object mVeh_Clztxx = arguments[2];
            Object mVeh__Wzghzbh = arguments[3];
            Object mVeh_Jyw = arguments[4];
            Object mVeh_Veh_Dywym = arguments[5];
            
            System.out.println(jsReturn);
            PrintModule currentModule = null;
            if (mVeh_Clztxx != null && "dp".equalsIgnoreCase(mVeh_Clztxx.toString())) {
                currentModule = dpModule;
            } else if (mVeh_Clztxx != null && "qx".equalsIgnoreCase(mVeh_Clztxx.toString())) {
                currentModule = qxModule;
            }
        	if(!Utils.isNullOrEmptyString(mVeh_ErrorInfo)){
        		currentModule.setError(mVeh_ErrorInfo.toString());
        		return null;
        	}
            if (currentModule != null) {
                DataObject data = currentModule.getInput().getData();
                data.setValue(VimUtils.mVeh__Wzghzbh, mVeh__Wzghzbh);
                data.setValue(VimUtils.mVeh_Jyw, mVeh_Jyw);
                data.setValue(VimUtils.mVeh_Dywym, mVeh_Veh_Dywym);
            }

            if (currentModule == dpModule) {
                qxModule.getInput().getData().setValue(VimUtils.mVeh_Dphgzbh, mVeh__Wzghzbh);// ���������ɵĺϸ�֤��д�뵽����������

            }
        }
        return null;
    }

    public QXCertPrintModule getQxModule() {
        return qxModule;
    }

    public void setQxModule(QXCertPrintModule qxModule) {
        this.qxModule = qxModule;
    }

    public DPCertPrintModule getDpModule() {
        return dpModule;
    }

    public void setDpModule(DPCertPrintModule dpModule) {
        this.dpModule = dpModule;
    }

}