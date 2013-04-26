package com.sg.vim.print.control;

import org.eclipse.rap.rwt.RWT;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.internal.widgets.IWidgetGraphicsAdapter;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.mongodb.DBObject;
import com.sg.sqldb.utility.SQLRow;
import com.sg.ui.ImageResource;
import com.sg.ui.UI;
import com.sg.ui.UIUtils;
import com.sg.vim.datamodel.util.VimUtils;

@SuppressWarnings("restriction")
public class PrintContent extends Composite {

    private Image bannerImage;
    private int margin = 12;
    private Text vinInputText;
    private Button queryButton;

    public PrintContent(Composite parent, int style) {
        super(parent, style);
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

        Composite content = createContent();
        fd = new FormData();
        content.setLayoutData(fd);
        fd.top = new FormAttachment(banner);
        fd.left = new FormAttachment();
        fd.right = new FormAttachment(100);
        fd.bottom = new FormAttachment(100);
    }

    private Composite createBanner(PrintContent printContent) {
        Composite banner = new Composite(this, SWT.NONE);
        banner.setLayout(new FormLayout());
        Label headImage = new Label(banner, SWT.NONE);
        headImage.setImage(bannerImage);
        FormData fd = new FormData();
        headImage.setLayoutData(fd);
        fd.top = new FormAttachment(0,margin);
        fd.left = new FormAttachment(0,margin);
        
        Label title = new Label(banner, SWT.NONE);
        title.setData(RWT.MARKUP_ENABLED, Boolean.TRUE);
        title.setText("<span style='color:rgb(255,100,0), FONT-FAMILY:微软雅黑;font-size:16pt'>请输入VIM代码：</span>");
        fd = new FormData();
        title.setLayoutData(fd);
        fd.left = new FormAttachment(headImage,margin*2);
        
        vinInputText = new Text(banner,SWT.BORDER);
        vinInputText.setTextLimit(17);
        vinInputText.setData(RWT.CUSTOM_VARIANT, "big");
        FormData fd1 = new FormData();
        vinInputText.setLayoutData(fd1);
        fd1.left = new FormAttachment(headImage,margin*2);
        fd1.bottom = new FormAttachment(100,-margin);
        fd1.width = 250;
        fd.bottom = new FormAttachment(vinInputText);
        
        queryButton = new Button(banner,SWT.PUSH);
        queryButton.setData(RWT.CUSTOM_VARIANT, "whitebutton_s");
        queryButton.setImage(UI.getImage(ImageResource.SEARCH_32));
        fd = new FormData();
        queryButton.setLayoutData(fd);
        fd.left = new FormAttachment(vinInputText,margin);
        fd.bottom = new FormAttachment(100,-margin);
        getShell().setDefaultButton(queryButton);
        queryButton.addSelectionListener(new SelectionAdapter() {
        @Override
        public void widgetSelected(SelectionEvent e) {
            doQueryButtonPressed();
        }});
        
        
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
        String vin = vinInputText.getText();
        //检查输入的vin是否合法
        boolean valid = VimUtils.checkVIN(vin);
        if(!valid){
            UIUtils.showMessage(getShell(),"输入VIN","输入的VIN不合法，请重新输入。",SWT.ERROR);
            return;
        }
        //查询数据库是否有对应vin的成品码记录
        SQLRow row = null;
        try {
            row = VimUtils.getProductCode(vin);
        } catch (Exception e) {
            UIUtils.showMessage(getShell(),"查询MES成品记录",e.getMessage(),SWT.ERROR);
            return;
        }
        Object productCode = row.getValue(VimUtils.FIELD_PRODUCT_CODE);
        if(!(productCode instanceof String)){
            UIUtils.showMessage(getShell(),"查询MES成品记录","成品码不是字符串类型",SWT.ERROR);
            return;
        }
        //获取成品码对应成品码数据
        //公告车型 productcodeinfo.f_0_2c
        DBObject productCodeData = null;
        try {
            productCodeData = VimUtils.getProductCodeInfo((String)productCode);
        } catch (Exception e) {
            UIUtils.showMessage(getShell(),"查询成品码记录",e.getMessage(),SWT.ERROR);
            return;
        }
        
        //查询COC绑定数据
        DBObject cocData = null;
        try {
            cocData = VimUtils.getCOCInfo(productCodeData);
        } catch (Exception e) {
            UIUtils.showMessage(getShell(),"查询成品码车型一致性信息",e.getMessage(),SWT.ERROR);
            return;
        }
        //查询配置数据
        DBObject confData = null;
        try {
            confData = VimUtils.getConfInfo(productCodeData);
        } catch (Exception e) {
            UIUtils.showMessage(getShell(),"查询成品码车型配置信息",e.getMessage(),SWT.ERROR);
            return;
        }
        //处理底盘有关数据
        
        
    }


    private Composite createContent() {
        return new Composite(this,SWT.NONE);
    }

}
