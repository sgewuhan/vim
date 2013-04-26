package com.sg.vim.print.control;

import org.eclipse.rap.rwt.RWT;
import org.eclipse.swt.SWT;
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

import com.mobnut.commons.Commons;
import com.mongodb.DBObject;
import com.sg.sqldb.utility.SQLRow;
import com.sg.ui.ImageResource;
import com.sg.ui.UI;
import com.sg.ui.UIUtils;
import com.sg.ui.model.DataObjectEditorInput;
import com.sg.ui.part.MultipageEditablePanel;
import com.sg.vim.datamodel.IVIMFields;
import com.sg.vim.datamodel.util.VimUtils;

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

    public PrintContent(ManagedForm mform, Composite parent, int style) {
        super(parent, style);
        
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
        fd.top = new FormAttachment(banner);
        fd.left = new FormAttachment();
        fd.right = new FormAttachment(100);
        fd.bottom = new FormAttachment(100);
        inputContent.setLayout(new FillLayout());
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

    protected void doQueryButtonPressed() {
        resetData();
        
        vin = vinInputText.getText();
        // ��������vin�Ƿ�Ϸ�
        boolean valid = VimUtils.checkVIN(vin);
        if (!valid) {
            UIUtils.showMessage(getShell(), "����VIN", "�����VIN���Ϸ������������롣", SWT.ERROR);
            return;
        }
        // ��ѯ���ݿ��Ƿ��ж�Ӧvin�ĳ�Ʒ���¼
        try {
            mesRawData = VimUtils.getProductCode(vin);
        } catch (Exception e) {
            UIUtils.showMessage(getShell(), "��ѯMES��Ʒ��¼", e.getMessage(), SWT.ERROR);
            return;
        }
        Object productCode = mesRawData.getValue(VimUtils.FIELD_PRODUCT_CODE);
        if (!(productCode instanceof String)) {
            UIUtils.showMessage(getShell(), "��ѯMES��Ʒ��¼", "��Ʒ�벻���ַ�������", SWT.ERROR);
            return;
        }

        Commons.LOGGER.info("��ó�Ʒ�룺" + productCode);

        // ��ȡ��Ʒ���Ӧ��Ʒ������ LNBMDLAA6CU000289
        // ���泵�� productcodeinfo.f_0_2c
        try {
            productCodeData = VimUtils.getProductCodeInfo((String) productCode);
        } catch (Exception e) {
            UIUtils.showMessage(getShell(), "��ѯ��Ʒ���¼", e.getMessage(), SWT.ERROR);
            return;
        }

        // ��ѯCOC������
        try {
            cocData = VimUtils.getCOCInfo(productCodeData);
        } catch (Exception e) {
            UIUtils.showMessage(getShell(), "��ѯ��Ʒ�복��һ������Ϣ", e.getMessage(), SWT.ERROR);
            return;
        }
        // ��ѯ��������
        try {
            confData = VimUtils.getConfInfo(productCodeData);
        } catch (Exception e) {
            UIUtils.showMessage(getShell(), "��ѯ��Ʒ�복��������Ϣ", e.getMessage(), SWT.ERROR);
            return;
        }
        // ��������й�����
        // �ж����޵���ID
        /**
         * ����VINʱ���е����ͺź͵���ID��Ϊ�գ����ȴ�ӡ���̺ϸ�֤�����ϵͳ������̨���ĵ��̺ϸ�֤�Ͳ��ô�ӡ���̺ϸ�֤����ֱ�Ӵ�ӡ�����ϸ�֤��
         * ����Ѵ��ڴ˳��������ϸ�֤�Ͳ��ô�ӡ������ӡ�˵��̺ϸ�֤���ٴ�ӡ�����ϸ�֤ʱ���������ϸ�֤λ�ã���ӡ�������ϸ�֤��ź͵��̺ϸ�֤��š�
         */
        Object dpId = cocData.get(IVIMFields.C_12);
        if(dpId instanceof String){
            //�ҵ���Ӧ�ĵ�����Ϣ
            try {
                dpcocData = VimUtils.getCOCInfoById((String) dpId);
                dpconfData = VimUtils.getConfInfoById((String)dpId);
            } catch (Exception e) {
                UIUtils.showMessage(getShell(), "��ѯ����������Ϣ", e.getMessage(), SWT.ERROR);
                return;
            }
            
        }
        
        try {
            resetInputContent();
        } catch (Exception e) {
            UIUtils.showMessage(getShell(), "���ɺϸ�֤����", e.getMessage(), SWT.ERROR);
        }

    }

    private void resetInputContent() throws Exception {
        Control[] children = inputContent.getChildren();
        for (int i = 0; i < children.length; i++) {
            children[i].dispose();
        }
        Composite editArea = inputContent;
        if(hasDP()){
            SashForm sf = new SashForm(inputContent, SWT.HORIZONTAL);
            Composite dpEditArea = new Composite(sf,SWT.NONE);
            DataObjectEditorInput dpinput = VimUtils.getCerfInput(dpcocData,dpconfData,productCodeData,mesRawData,null,vin,true);
            fillEditArea(dpEditArea, dpinput);
            editArea = new Composite(sf,SWT.NONE);
        }
        DataObjectEditorInput input= VimUtils.getCerfInput(cocData,confData,productCodeData,mesRawData,null,vin,false);
        fillEditArea(editArea, input);
    }

    private void fillEditArea(Composite parent, DataObjectEditorInput input) {
        MultipageEditablePanel folder = new MultipageEditablePanel(parent, SWT.TOP | SWT.FLAT);
        folder.setMessageManager(mform.getForm().getMessageManager());
        folder.createContents(mform,input);        
    }

    private boolean hasDP() {
        return dpcocData!=null||dpconfData!=null;
    }

    private void resetData() {
        vin = null;
        mesRawData = null;
        cocData = null;
        confData = null;
        dpcocData = null;
        dpconfData = null;
        productCodeData = null;        
    }

    private Composite createContent() {
        return new Composite(this, SWT.NONE);
    }

}
