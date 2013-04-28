package com.sg.vim.print.control;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.rap.rwt.RWT;
import org.eclipse.rap.rwt.service.ServerPushSession;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.internal.widgets.IWidgetGraphicsAdapter;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.ManagedForm;

import com.mongodb.DBObject;
import com.sg.sqldb.utility.SQLRow;
import com.sg.ui.ImageResource;
import com.sg.ui.UI;
import com.sg.ui.UIUtils;
import com.sg.ui.model.DataObjectEditorInput;
import com.sg.ui.part.MultipageEditablePanel;
import com.sg.vim.datamodel.IVIMFields;
import com.sg.vim.datamodel.util.VimUtils;
import com.sg.vim.print.PrintActivator;

@SuppressWarnings("restriction")
public class PrintContent extends Composite {

    private Image bannerImage;
    private int margin = 12;
    private Text vinInputText;
    private Button queryButton;
    private DBObject cocData;
    private DBObject confData;
    private DBObject dpcocData;
    private DBObject dpconfData;
    private DBObject productCodeData;
    private Composite inputContent;
    private ManagedForm mform;

    private SQLRow mesRawData;
    private String vin;
//    private Label messageLabel;
    private ServerPushSession pushSession;
private Button printButton;
private Browser browser;
private DataObjectEditorInput dpinput;
private DataObjectEditorInput input;

    public PrintContent(ManagedForm mform, Composite parent, int style) {
        super(parent, style);
        pushSession = new ServerPushSession();
        pushSession.start();

        this.mform = mform;
        setBackgroundMode(SWT.INHERIT_DEFAULT);
        setLayout(new FormLayout());
        bannerImage = UI.getImage(ImageResource.PRINT_96);
        Composite banner = createBanner(this);
        FormData fd = new FormData();
        banner.setLayoutData(fd);
        fd.top = new FormAttachment();
        fd.left = new FormAttachment();
        fd.height = bannerImage.getBounds().height + margin * 2;
        fd.right = new FormAttachment(100);

        inputContent = createContent();
        fd = new FormData();
        inputContent.setLayoutData(fd);
        fd.top = new FormAttachment(banner, margin * 2);
        fd.left = new FormAttachment();
        fd.right = new FormAttachment(100);
        fd.bottom = new FormAttachment(100);
        
        browser = new Browser(this,SWT.NONE);
        browser.setUrl("/vert");
        fd = new FormData();
        browser.setLayoutData(fd);
        fd.top = new FormAttachment(banner);
        fd.left = new FormAttachment();
        fd.width = margin;
        fd.height = margin;
    }

    private Composite createBanner(PrintContent printContent) {
        Composite banner = new Composite(this, SWT.NONE);
        banner.setLayout(new FormLayout());
        Label headImage = new Label(banner, SWT.NONE);
        headImage.setImage(bannerImage);
        FormData fd = new FormData();
        headImage.setLayoutData(fd);
        fd.top = new FormAttachment(0, margin);
        fd.left = new FormAttachment(0, margin);

        Label title = new Label(banner, SWT.NONE);
        title.setData(RWT.MARKUP_ENABLED, Boolean.TRUE);
        title.setText("<span style='color:rgb(255,100,0), FONT-FAMILY:΢���ź�;font-size:16pt'>������VIM���룺</span>");
        fd = new FormData();
        title.setLayoutData(fd);
        fd.left = new FormAttachment(headImage, margin * 2);

        vinInputText = new Text(banner, SWT.BORDER);
        vinInputText.setTextLimit(17);
        vinInputText.setData(RWT.CUSTOM_VARIANT, "big");
        FormData fd1 = new FormData();
        vinInputText.setLayoutData(fd1);
        fd1.left = new FormAttachment(headImage, margin * 2);
        fd1.bottom = new FormAttachment(100, -margin);
        fd1.width = 250;
        fd.bottom = new FormAttachment(vinInputText);

        queryButton = new Button(banner, SWT.PUSH);
        queryButton.setData(RWT.CUSTOM_VARIANT, "whitebutton_s");
        queryButton.setImage(UI.getImage(ImageResource.SEARCH_32));
        fd = new FormData();
        queryButton.setLayoutData(fd);
        fd.left = new FormAttachment(vinInputText, margin);
        fd.bottom = new FormAttachment(100, -margin);
        getShell().setDefaultButton(queryButton);
        queryButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                doQueryButtonPressed();
            }
        });
        
        printButton = new Button(banner,SWT.PUSH);
        printButton.setData(RWT.CUSTOM_VARIANT, "whitebutton_s");
        printButton.setImage(UI.getImage(ImageResource.PRINT_32));
        fd = new FormData();
        printButton.setLayoutData(fd);
        fd.left = new FormAttachment(queryButton, margin);
        fd.bottom = new FormAttachment(100, -margin);
        printButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                doPrintButtonPressed();
            }
        });
        printButton.setEnabled(false);

        Label line = new Label(banner, SWT.NONE);
        fd = new FormData();
        line.setLayoutData(fd);
        Color sepColor = UI.getColor(getDisplay(), 192, 192, 192);
        line.setBackground(sepColor);
        fd.bottom = new FormAttachment(100, 0);
        fd.left = new FormAttachment(0, 0);
        fd.right = new FormAttachment(100, 0);
        fd.height = 1;

        Object adapter = banner.getAdapter(IWidgetGraphicsAdapter.class);
        IWidgetGraphicsAdapter gfxAdapter = (IWidgetGraphicsAdapter) adapter;
        int[] percents = new int[] { 0, 30, 100 };
        Color[] gradientColors = new Color[] { new Color(getDisplay(), 255, 255, 255),
                new Color(getDisplay(), 245, 245, 250), new Color(getDisplay(), 220, 220, 240), };

        gfxAdapter.setBackgroundGradient(gradientColors, percents, true);
        return banner;
    }

    protected void doPrintButtonPressed() {
        VimUtils.print(browser,dpinput.getData().getData() );
    }

    protected void doQueryButtonPressed() {

        resetData();
        vin = vinInputText.getText();

        // ��������vin�Ƿ�Ϸ�
        boolean valid = VimUtils.checkVIN(vin);
        if (!valid) {
            UIUtils.showMessage(getShell(), "����VIN", "�����VIN���Ϸ������������롣", SWT.ERROR);
            return;
        }

        // inputContent.setLayout(new FormLayout());
        // Label messageIconLabel = new Label(inputContent,SWT.NONE);
        // messageIconLabel.setData(RWT.MARKUP_ENABLED, Boolean.TRUE);
        // StringBuilder builder = new StringBuilder();
        // builder.append("<img src=\"");
        // builder.append(FileUtil.getImageURL("loading.gif", UI.PLUGIN_ID, "icons"));
        // builder.append("\"  width='400' height='400'/><br/>ABCD");
        // String string = builder.toString();
        // messageIconLabel.setText(string);
        // FormData fd = new FormData();
        // messageIconLabel.setLayoutData(fd);
        // fd.top = new FormAttachment(50,200);
        // fd.left = new FormAttachment(50,200);
        //
        // messageLabel = new Label(inputContent,SWT.NONE);
        // messageLabel.setData(RWT.MARKUP_ENABLED, Boolean.TRUE);
        // messageLabel.setText("��װ�ϸ�֤����VIN");
        // fd = new FormData();
        // messageLabel.setLayoutData(fd);
        // fd.top = new FormAttachment(messageIconLabel,10);
        // fd.left = new FormAttachment(messageIconLabel,0);
        // inputContent.layout();

        Job job = new Job("��װ�ϸ�֤����VIN" + vin) {

            @Override
            protected IStatus run(IProgressMonitor monitor) {
                // ��ѯ���ݿ��Ƿ��ж�Ӧvin�ĳ�Ʒ���¼
                String message = "���ڲ�ѯ�ⲿ���ݿ��е�ƥ��VIN�ĳ�Ʒ��¼";
                setNotice(message);
                try {
                    mesRawData = VimUtils.getProductCode(vin);
                } catch (Exception e) {
                    return new Status(Status.ERROR, PrintActivator.PLUGIN_ID, message, e);
                }
                Object productCode = mesRawData.getValue(VimUtils.FIELD_PRODUCT_CODE);
                if (!(productCode instanceof String)) {
                    return new Status(Status.ERROR, PrintActivator.PLUGIN_ID,
                            "��ѯMES��Ʒ��¼,MES���ݿ�ĳ�Ʒ�벻���ַ�������");
                }

                // ��ȡ��Ʒ���Ӧ��Ʒ������ LNBMDLAA6CU000289
                // ���泵�� productcodeinfo.f_0_2c
                message = "���ڲ�ѯVIM���ݿ��еĳ�Ʒ���¼";
                setNotice(message);
                try {
                    productCodeData = VimUtils.getProductCodeInfo((String) productCode);
                } catch (Exception e) {
                    return new Status(Status.ERROR, PrintActivator.PLUGIN_ID, message, e);
                }

                // ��ѯCOC������
                message = "���ڲ�ѯ��Ʒ�복��һ������Ϣ";
                setNotice(message);
                try {
                    cocData = VimUtils.getCOCInfo(productCodeData);
                } catch (Exception e) {
                    return new Status(Status.ERROR, PrintActivator.PLUGIN_ID, message, e);
                }
                // ��ѯ��������
                message = "���ڲ�ѯ��Ʒ�복��������Ϣ";
                setNotice(message);
                try {
                    confData = VimUtils.getConfInfo(productCodeData);
                } catch (Exception e) {
                    return new Status(Status.ERROR, PrintActivator.PLUGIN_ID, message, e);
                }
                // ��������й�����
                // �ж����޵���ID
                /**
                 * ����VINʱ���е����ͺź͵���ID��Ϊ�գ����ȴ�ӡ���̺ϸ�֤�����ϵͳ������̨���ĵ��̺ϸ�֤�Ͳ��ô�ӡ���̺ϸ�֤����ֱ�Ӵ�ӡ�����ϸ�֤��
                 * ����Ѵ��ڴ˳��������ϸ�֤�Ͳ��ô�ӡ������ӡ�˵��̺ϸ�֤���ٴ�ӡ�����ϸ�֤ʱ���������ϸ�֤λ�ã���ӡ�������ϸ�֤��ź͵��̺ϸ�֤��š�
                 */
                Object dpId = cocData.get(IVIMFields.C_12);
                if (dpId instanceof String) {
                    message = "���ڲ�ѯ��Ʒ�������Ϣ";
                    setNotice(message);
                    // �ҵ���Ӧ�ĵ�����Ϣ
                    try {
                        dpcocData = VimUtils.getCOCInfoById((String) dpId);
                        dpconfData = VimUtils.getConfInfoById((String) dpId);
                    } catch (Exception e) {
                        return new Status(Status.ERROR, PrintActivator.PLUGIN_ID, message, e);
                    }

                }

                return Status.OK_STATUS;
            }

        };
        job.addJobChangeListener(new JobChangeAdapter() {

            @Override
            public void done(IJobChangeEvent event) {
                inputContent.getDisplay().asyncExec(new Runnable() {

                    @Override
                    public void run() {
                        try {
                            resetInputContent();
                            setOperationEnable(true);
                        } catch (Exception e) {
                            UIUtils.showMessage(getShell(), "��ʾ�ϸ�֤����", "����:" + e.getMessage(),
                                    SWT.ERROR);
                        }
                    }
                });

            }

        });
        job.setUser(true);
        setOperationEnable(false);
        job.schedule();

    }

    private void setOperationEnable(boolean b) {
        queryButton.setEnabled(b);
        printButton.setEnabled(b);
        vinInputText.setEnabled(b);
    }

    protected void setNotice(final String message) {
        // final Display display = inputContent.getDisplay();
        // display.asyncExec(new Runnable() {
        //
        // @Override
        // public void run() {
        // BusyIndicator.showWhile(display,new Runnable() {
        //
        // @Override
        // public void run() {
        // messageLabel.setText(message);
        // inputContent.layout();
        // }
        // });
        // }
        // });

    }

    private void resetInputContent() throws Exception {
        Control[] children = inputContent.getChildren();
        for (int i = 0; i < children.length; i++) {
            children[i].dispose();
        }

        inputContent.setLayout(new FillLayout());
        SashForm sf = new SashForm(inputContent, SWT.HORIZONTAL);
        Composite dpEditArea = new Composite(sf, SWT.NONE);
        dpEditArea.setLayout(new FillLayout());
        Composite editArea = new Composite(sf, SWT.NONE);
        editArea.setLayout(new FillLayout());

        if (hasDP()) {
            dpinput = VimUtils.getCerfInput(dpcocData, dpconfData,
                    productCodeData, mesRawData, null, vin, true);
            MultipageEditablePanel folder = fillEditArea(dpEditArea, dpinput);
            folder.getItem(0).setText("���� �ϸ�֤����I");
        }

        input = VimUtils.getCerfInput(cocData, confData, productCodeData,
                mesRawData, null, vin, false);
        MultipageEditablePanel folder = fillEditArea(editArea, input);
        folder.getItem(0).setText("���� �ϸ�֤����I");

        if (hasDP()) {
            sf.setWeights(new int[] { 50, 50 });
        } else {
            sf.setWeights(new int[] { 100, 0 });
        }
        inputContent.layout();

    }

    private MultipageEditablePanel fillEditArea(Composite parent, DataObjectEditorInput input) {
        MultipageEditablePanel folder = new MultipageEditablePanel(parent, SWT.TOP | SWT.FLAT);
        folder.setMessageManager(mform.getForm().getMessageManager());
        folder.createContents(mform, input);
        return folder;
    }

    private boolean hasDP() {
        return dpcocData != null || dpconfData != null;
    }

    private void resetData() {
        vin = null;
        mesRawData = null;
        cocData = null;
        confData = null;
        dpcocData = null;
        dpconfData = null;
        productCodeData = null;
        dpinput = null;
        input = null;
    }

    private Composite createContent() {
        return new Composite(this, SWT.NONE);
    }

    @Override
    public void dispose() {
        pushSession.stop();
        super.dispose();
    }

}
