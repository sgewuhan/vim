package com.sg.vim.print;

import org.eclipse.rap.rwt.RWT;
import org.eclipse.rap.rwt.application.AbstractEntryPoint;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;

public class PrintEntryPoint extends AbstractEntryPoint {

  public static final String PARAM_CODE = "vin"; //$NON-NLS-1$
  private String vin;

  public PrintEntryPoint() {
  }

  @Override
  public int createUI() {
    vin = RWT.getRequest().getParameter(PARAM_CODE);
    return super.createUI();
  }

  @Override
  protected void createContents(Composite parent) {
    parent.setLayout(new FillLayout());
    PrintPanel panel = new PrintPanel(parent);
    panel.setVin(vin);
  }

}
