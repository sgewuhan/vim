package com.sg.vim.print.control;

import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.BrowserFunction;

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
        // Veh_Clztxx,VehCert.Veh_Zchgzbh,VehCert.Veh_Jyw, VehCert.Veh_Dywym
        if (arguments != null) {
            Object mVeh_Clztxx = arguments[0];
            Object mVeh_Zchgzbh = arguments[1];
            Object mVeh_Jyw = arguments[2];
            Object mVeh_Veh_Dywym = arguments[3];
            PrintModule currentModule = null;
            if (mVeh_Clztxx != null && "dp".equalsIgnoreCase(mVeh_Clztxx.toString())) {
                currentModule = dpModule;
            } else if (mVeh_Clztxx != null && "qx".equalsIgnoreCase(mVeh_Clztxx.toString())) {
                currentModule = qxModule;
            }
            if (currentModule != null) {
                currentModule.setCallbackProperties("Veh_Zchgzbh", mVeh_Zchgzbh);
                currentModule.setCallbackProperties("Veh_Jyw", mVeh_Jyw);
                currentModule.setCallbackProperties("Veh_Veh_Dywym", mVeh_Veh_Dywym);
            }

            if (currentModule == dpModule) {
                qxModule.getInput().getData().setValue(VimUtils.mVeh_Dphgzbh, mVeh_Zchgzbh);// 将底盘生成的合格证号写入到整车数据中

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