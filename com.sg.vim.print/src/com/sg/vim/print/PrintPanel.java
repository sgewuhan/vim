package com.sg.vim.print;

import org.eclipse.rap.rwt.RWT;
import org.eclipse.rap.rwt.client.service.JavaScriptExecutor;
import org.eclipse.rap.rwt.client.service.JavaScriptLoader;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

import com.mongodb.BasicDBObject;
import com.sg.vim.datamodel.util.VimUtils;

public class PrintPanel extends Composite {
  private Text vinInput;
  private Button queryButton;
  private Button printCertButton;
  // private Button printFidButton;
  private Button printCOCButton;
  private Button printBrandButton;
  private Browser browser;

  public PrintPanel(Composite parent) {
    super(parent, SWT.NONE);
    setLayout(new GridLayout(4, true));
    createContent(this);
  }

  private void createContent(Composite parent) {
    vinInput = new Text(parent, SWT.BORDER);
    vinInput.setMessage("请输入vin代码");
    vinInput.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1));
    queryButton = new Button(parent, SWT.PUSH);
    queryButton.setText("查询");
    queryButton.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, true, false, 1, 1));

    printCertButton = new Button(parent, SWT.PUSH);
    printCertButton.setText("打印合格证");

    queryButton.addSelectionListener(new SelectionAdapter() {
      @Override
      public void widgetSelected(SelectionEvent e) {
          BasicDBObject dbo = new BasicDBObject();
          dbo.put(VimUtils.mVeh_Bgcazzdyxzzl, "a");
          VimUtils.print(browser,dbo);
      }
    });
    /**
     * printFidButton = new Button(parent,SWT.PUSH); printFidButton.setText("打印燃油标识");
     * printFidButton.addSelectionListener(new SelectionAdapter(){
     * 
     * @Override public void widgetSelected(SelectionEvent e) { SQLConnection.QueryUser();
     * 
     *           } });
     */
    printCOCButton = new Button(parent, SWT.PUSH);
    printCOCButton.setText("打印车辆一致性证书");
    printCOCButton.addSelectionListener(new SelectionAdapter() {
      @Override
      public void widgetSelected(SelectionEvent e) {
          VimUtils.test(browser);
      }
    });

    printBrandButton = new Button(parent, SWT.PUSH);
    printBrandButton.setText("打印燃油标识");
    printBrandButton.addSelectionListener(new SelectionAdapter() {
      @Override
      public void widgetSelected(SelectionEvent e) {
        doPrintBrand();
      }
    });
    
    browser = new Browser(parent, SWT.NONE);
    browser.setUrl("/vert");
  }

  protected void doPrintBrand() {
    // TODO Auto-generated method stub

  }

  protected void doPrintCOC() {
    // TODO Auto-generated method stub

  }

  protected void doPrintFid() {
    // TODO Auto-generated method stub

  }

  protected void doPrintCert() {
    // TODO Auto-generated method stub

  }
  
  public void setVin(String vin) {
    this.vinInput .setText(vin);
  }
}
