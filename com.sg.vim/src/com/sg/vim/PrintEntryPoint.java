package com.sg.vim;

import org.eclipse.rap.rwt.RWT;
import org.eclipse.rap.rwt.application.AbstractEntryPoint;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

public class PrintEntryPoint extends AbstractEntryPoint {

    public static final String PARAM_CODE = "vin"; //$NON-NLS-1$

    public PrintEntryPoint() {
    }


    @Override
    protected void createContents(Composite parent) {
      String vin = RWT.getRequest().getParameter(PARAM_CODE);
      Text text = new Text(parent,SWT.BORDER);
      text.setText(vin);
    }

}
