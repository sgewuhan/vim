package com.sg.vim.print;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

public class PrintView extends ViewPart {

  private PrintPanel panel;

  public PrintView() {
  }

  @Override
  public void createPartControl(Composite parent) {
    panel = new PrintPanel(parent);
  }

  @Override
  public void setFocus() {
    panel.setFocus();
  }

}
