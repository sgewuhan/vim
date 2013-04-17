package com.sg.vim.print;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

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

    printCertButton.addSelectionListener(new SelectionAdapter() {
      @Override
      public void widgetSelected(SelectionEvent e) {
        // doPrintCert();
        browser.execute("ReadTest()");// 执行Javascript
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
        doPrintCOC();
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
    /**
     * System.out.println("-----------------------------------1s"); browser = new
     * Browser(parent,SWT.NONE); //final Text txtURL = new Text( parent, SWT.BORDER );
     * //txtURL.setText( "http://eclipse.org/rap" ); String str=createBrowserFunctionHTML();
     * 
     * //final Text txtHTML = new Text( parent, SWT.BORDER | SWT.MULTI ); //txtHTML.setText( str );
     * //txtHTML.setLayoutData( new GridData( 600, 700 ) ); //设置文本框大小 browser.setLayoutData( new
     * GridData( 300,300 ));//设 置所嵌入的浏览器大小 browser.setText(str);
     **/
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
  
  private String createBrowserFunctionHTML() {
    StringBuffer buffer = new StringBuffer();
    buffer.append("<html>\n");
    buffer.append("<head>\n");
    buffer.append("<script language=\"JavaScript\">\n");
    buffer.append("function ReadTest() {\n");
    buffer.append("alert(\"2\")\n");
    buffer.append(" var VehCert= new ActiveXObject(\"VCertificate.VehCert\");\n");
    buffer.append(" VehCert.ViewBarcodeInfo(\"XXXXX\");\n");
    buffer.append("    }\n");
    buffer.append(" function PrintTest(){\n");
    buffer.append("    var iRtn;\n");
    buffer.append("    var VehCert= new ActiveXObject(\"VCertificate.VehCert\");\n");
    buffer.append("    VehCert.Clztxx=\"QX\";\n");
    buffer.append("    iRtn=VehCert.PrtParaTbl(1,\"XXXXX\");\n");
    buffer.append("    if (iRtn==-1){\n");
    buffer.append("    alert(VehCert.Veh_ErrorInfo);\n");
    buffer.append("    }\n");
    buffer.append("}\n");
    buffer.append("</script>\n");
    buffer.append("</head>\n");
    buffer.append("<body>\n");
    buffer.append(" <table align=\"center\" width=\"200\" border=0 >\n");
    buffer.append("     <tr><td>\n");
    // buffer.append("      <input type=\"button\" name=\"read\" value=\"read\" onclick=\"ReadTest()\">\n");
    buffer.append("     </td></tr><tr><td>\n");
    // buffer.append("      <input type=\"button\" name=\"print\" value=\"print\" onclick=\"PrintTest()\">\n");
    buffer.append("     </td></tr>\n");
    buffer.append("     </table></body></html>\n");
    return buffer.toString();
  }

  public void setVin(String vin) {
    this.vinInput .setText(vin);
  }
}
