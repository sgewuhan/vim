package com.sg.vim.print.control;

import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.BrowserFunction;

public class BarResultFunction extends BrowserFunction {

    public BarResultFunction(Browser browser, String name) {
        super(browser, name);
    }

    @Override
    public Object function(Object[] arguments) {
        // VehCert.Veh_Tmxx
        System.out.println(arguments);
        if (arguments != null) {
            for (int i = 0; i < arguments.length; i++) {
                System.out.println(arguments[i]);
            }
        }
        return null;
    }
}