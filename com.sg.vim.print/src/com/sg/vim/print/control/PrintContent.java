package com.sg.vim.print.control;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.rap.rwt.RWT;
import org.eclipse.rap.rwt.service.ServerPushSession;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.BrowserFunction;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.internal.widgets.IWidgetGraphicsAdapter;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.forms.ManagedForm;
import org.eclipse.ui.forms.widgets.Form;

import com.mobnut.commons.util.Utils;
import com.mobnut.db.DBActivator;
import com.mobnut.db.utils.DBUtil;
import com.mobnut.portal.user.UserSessionContext;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.sg.sqldb.utility.SQLRow;
import com.sg.ui.ImageResource;
import com.sg.ui.UI;
import com.sg.ui.UIUtils;
import com.sg.ui.model.DataObjectEditorInput;
import com.sg.ui.part.MultipageEditablePanel;
import com.sg.vim.datamodel.IVIMFields;
import com.sg.vim.datamodel.util.DataAssembly;
import com.sg.vim.datamodel.util.VimUtils;
import com.sg.vim.print.module.COCPrintModule;
import com.sg.vim.print.module.CertPrintModule;
import com.sg.vim.print.module.DPCertPrintModule;
import com.sg.vim.print.module.FuelCardPrintModule;
import com.sg.vim.print.module.PrintModule;
import com.sg.vim.print.module.QXCertPrintModule;

@SuppressWarnings("restriction")
public class PrintContent extends Composite {

    private Image bannerImage;
    private int margin = 12;
    private Text vinInputText;
    private Button queryButton;
    private String vin;
    private ServerPushSession pushSession;
    private Button printButton;
    private Browser certBrowser;
    private BrowserFunction printCertResultFunction;
    private BrowserFunction barResultFunction;
    private ScrolledComposite dataPreview;
    private PrintModule[] modules;
    private ManagedForm mform;
    private TreeViewer navigator;
    private SashForm contentPanel;
    private Composite editorArea;
    private Composite toolbar;
    private MultipageEditablePanel folder;
    private Form form;
    private CertPrintModuleSeqEditingSupport editingSupport;
    private Browser cocBrowser;
    private Browser fuelBrowser;

    public PrintContent(ManagedForm mform, Form form, int style) {
        super(form.getBody(), style);
        this.mform = mform;
        this.form = form;

        modules = new PrintModule[3];
        modules[0] = new CertPrintModule();
        modules[1] = new COCPrintModule();
        modules[2] = new FuelCardPrintModule();

        pushSession = new ServerPushSession();
        pushSession.start();

        setBackgroundMode(SWT.INHERIT_DEFAULT);
        setLayout(new FormLayout());

        Composite banner = createBanner(this);
        FormData fd = new FormData();
        banner.setLayoutData(fd);
        fd.top = new FormAttachment();
        fd.left = new FormAttachment();
        bannerImage = UI.getImage(ImageResource.PRINT_96);
        fd.height = bannerImage.getBounds().height + margin * 2;
        fd.right = new FormAttachment(100);

        contentPanel = createContent(this);
        fd = new FormData();
        contentPanel.setLayoutData(fd);
        fd.top = new FormAttachment(banner, margin * 2);
        fd.left = new FormAttachment(0, margin);
        fd.right = new FormAttachment(100, -margin);
        fd.bottom = new FormAttachment(100, -margin);

        certBrowser = createCertBrowser(this);
        fd = new FormData();
        certBrowser.setLayoutData(fd);
        fd.top = new FormAttachment(banner);
        fd.left = new FormAttachment();
        fd.width = 1;
        fd.height = 1;

        cocBrowser = createCOCBrowser(this);
        fd = new FormData();
        cocBrowser.setLayoutData(fd);
        fd.top = new FormAttachment(banner);
        fd.left = new FormAttachment();
        fd.width = 1;
        fd.height = 1;

        fuelBrowser = createFuelBrowser(this);
        fd = new FormData();
        fuelBrowser.setLayoutData(fd);
        fd.top = new FormAttachment(banner);
        fd.left = new FormAttachment();
        fd.width = 1;
        fd.height = 1;
    }

    private Browser createFuelBrowser(PrintContent printContent) {
        return new Browser(this, SWT.NONE);
    }

    private Browser createCOCBrowser(PrintContent printContent) {
        return new Browser(this, SWT.NONE);
    }

    private Browser createCertBrowser(PrintContent printContent) {
        Browser certBrowser = new Browser(this, SWT.NONE);
        certBrowser.setUrl("/vert");
        printCertResultFunction = new BrowserFunction(certBrowser, "printCertResult") {
            public Object function(Object[] arguments) {
                doPrintCertResultCallback(arguments);
                return super.function(arguments);
            }
        };

        barResultFunction = new BrowserFunction(certBrowser, "barResult") {
            @Override
            public Object function(Object[] arguments) {
                doBarResultCallback(arguments);
                return super.function(arguments);
            }
        };
        return certBrowser;
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
        title.setText("<span style='color:rgb(255,100,0), FONT-FAMILY:微软雅黑;font-size:16pt'>请输入VIN代码：</span>");
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
                queryButtonPressed();
            }
        });

        printButton = new Button(banner, SWT.PUSH);
        printButton.setData(RWT.CUSTOM_VARIANT, "whitebutton_s");
        printButton.setImage(UI.getImage(ImageResource.PRINT_32));
        fd = new FormData();
        printButton.setLayoutData(fd);
        fd.left = new FormAttachment(queryButton, margin);
        fd.bottom = new FormAttachment(100, -margin);
        printButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                printButtonPressed();
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

    private SashForm createContent(PrintContent printContent) {
        SashForm sash = new SashForm(printContent, SWT.HORIZONTAL);
        Composite navigatorPanel = new Composite(sash, SWT.NONE);
        navigatorPanel.setLayout(new FillLayout());

        navigator = new TreeViewer(navigatorPanel, SWT.BORDER | SWT.FULL_SELECTION | SWT.CHECK);
        // navigator.getTree().setLinesVisible(true);
        navigator.getTree().setData(RWT.MARKUP_ENABLED, Boolean.TRUE);
        navigator.getTree().setData(RWT.CUSTOM_ITEM_HEIGHT, 58);
        navigator.setContentProvider(PrintModule.getContentProvider());
        TreeViewerColumn col = new TreeViewerColumn(navigator, SWT.LEFT);
        col.getColumn().setWidth(300);
        col.setLabelProvider(PrintModule.getNameLabelProvider());

        col = new TreeViewerColumn(navigator, SWT.LEFT);
        col.getColumn().setWidth(260);
        col.setLabelProvider(PrintModule.getPaperNumberLabelProvider());
        editingSupport = new CertPrintModuleSeqEditingSupport(navigator);
        col.setEditingSupport(editingSupport);

        navigator.getTree().addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                if (e.detail == RWT.HYPERLINK) {
                    String[] args = e.text.split("@");

                    PrintModule module = getModulebyName(modules, args[1]);
                    if (module != null) {
                        module.fireEvent(args[0], args, PrintContent.this);
                    }

                } else if (e.detail == SWT.CHECK) {
                    TreeItem item = (TreeItem) e.item;
                    TreeItem[] subitems = item.getItems();
                    for (int i = 0; i < subitems.length; i++) {
                        subitems[i].setChecked(item.getChecked());
                    }
                    updateDoPrintButtonStatus();
                }
            }
        });

        navigator.setInput(modules);
        navigator.expandAll();
        UIUtils.enableTreeViewerEditing(navigator);

        Composite rightpanel = new Composite(sash, SWT.BORDER | SWT.V_SCROLL);
        rightpanel.setLayout(new FormLayout());
        toolbar = new Composite(rightpanel, SWT.NONE);
        FormData fd = new FormData();
        toolbar.setLayoutData(fd);
        fd.top = new FormAttachment();
        fd.left = new FormAttachment();
        fd.right = new FormAttachment(100);
        fd.height = 46;
        toolbar.setLayout(new RowLayout(SWT.HORIZONTAL));

        dataPreview = new ScrolledComposite(rightpanel, SWT.V_SCROLL);
        fd = new FormData();
        dataPreview.setLayoutData(fd);
        fd.top = new FormAttachment(toolbar, margin);
        fd.left = new FormAttachment();
        fd.right = new FormAttachment(100);
        fd.bottom = new FormAttachment(100);

        editorArea = new Composite(dataPreview, SWT.NONE);
        editorArea.setLayout(new FillLayout());

        dataPreview.setContent(editorArea);
        dataPreview.setExpandVertical(true);
        dataPreview.setExpandHorizontal(true);
        dataPreview.setMinSize(editorArea.computeSize(SWT.DEFAULT, SWT.DEFAULT));
        return sash;
    }

    private void printButtonPressed() {
        TreeItem[] items = navigator.getTree().getItems();
        for (int i = 0; i < items.length; i++) {
            PrintModule printModule = (PrintModule) items[i].getData();

            if (items[i].getChecked() && printModule.canPrintData()) {
                printModule.doPrint(this);
            }
        }
    }

    private void queryButtonPressed() {

        ((CertPrintModule) modules[0]).setCanUploadData(false);
        vin = vinInputText.getText();

        // 检查输入的vin是否合法
        boolean valid = VimUtils.checkVIN(vin);
        if (!valid) {
            UIUtils.showMessage(getShell(), "输入VIN", "输入的VIN不合法，请重新输入。", SWT.ERROR);
            return;
        }
        setOperationEnable(false);
        printButton.setEnabled(false);
        clearSelection(navigator.getTree().getItems());

        DataAssembly j = new DataAssembly(vin) {

            @Override
            protected void asyncAssemblyDone(IStatus result, SQLRow mesRawData,
                    DBObject productCodeData, DBObject cocData, DBObject confData,
                    DBObject dpcocData, DBObject dpconfData) {
                if (result.isOK()) {
                    try {
                        Map<String, Object> para = new HashMap<String, Object>();
                        para.put(PrintModule.PARA_COC_DATA, cocData);
                        para.put(PrintModule.PARA_CONF_DATA, confData);
                        para.put(PrintModule.PARA_DPCOC_DATA, dpcocData);
                        para.put(PrintModule.PARA_DPCONF_DATA, dpconfData);
                        para.put(PrintModule.PARA_PRODUCT_CODE_DATA, productCodeData);
                        para.put(PrintModule.PARA_MES_RAW_DATA, mesRawData);
                        para.put(PrintModule.PARA_VIN, vin);

                        for (int i = 0; i < modules.length; i++) {
                            modules[i].setInput(para);
                        }
                        navigator.refresh(true);
                    } catch (Exception e) {
                        UIUtils.showMessage(getShell(), "组合机动车完整数据", "错误:" + e.getMessage(),
                                SWT.ERROR);
                    }
                } else {
                    Throwable e = result.getException();
                    if (e != null) {
                        UIUtils.showMessage(getShell(), "组合机动车完整数据", "错误:" + e.getMessage(),
                                SWT.ERROR);
                    }
                }
                setOperationEnable(true);
            }

        };
        j.start(getDisplay());
    }

    private void clearSelection(TreeItem[] items) {
        if (items != null) {
            for (int i = 0; i < items.length; i++) {
                items[i].setChecked(false);
                clearSelection(items[i].getItems());
            }
        }
    }

    private void setOperationEnable(boolean b) {
        queryButton.setEnabled(b);
        vinInputText.setEnabled(b);
    }

    private void updateDoPrintButtonStatus() {
        // 判断选择的内容是否可以打印
        TreeItem[] items = navigator.getTree().getItems();
        boolean can = checkItemsCanPrint(items);
        printButton.setEnabled(can);
    }

    private boolean checkItemsCanPrint(TreeItem[] items) {
        for (int i = 0; i < items.length; i++) {
            boolean checked = items[i].getChecked();
            if (checked) {
                Object module = items[i].getData();
                if (module instanceof PrintModule) {
                    if (((PrintModule) module).canPrintData()) {
                        return true;
                    }
                }
            }
            boolean subCanPrint = checkItemsCanPrint(items[i].getItems());
            if (subCanPrint) {
                return true;
            }
        }
        return false;
    }

    private PrintModule getModulebyName(PrintModule[] modules, String className) {
        if (modules == null) {
            return null;
        }
        for (int i = 0; i < modules.length; i++) {
            if (modules[i].getName().equals(className)) {
                return modules[i];
            } else {
                PrintModule child = getModulebyName(modules[i].getSubModules(), className);
                if (child != null) {
                    return child;
                }
            }

        }
        return null;
    }

    @Override
    public void dispose() {
        printCertResultFunction.dispose();
        barResultFunction.dispose();
        pushSession.stop();
        super.dispose();
    }

    public void showData(PrintModule printModule) {

        Control[] children = editorArea.getChildren();
        for (int i = 0; i < children.length; i++) {
            children[i].dispose();
        }
        children = toolbar.getChildren();
        for (int i = 0; i < children.length; i++) {
            children[i].dispose();
        }

        Label title = new Label(toolbar, SWT.NONE);
        title.setData(RWT.MARKUP_ENABLED, Boolean.TRUE);
        title.setText(printModule.getHeadText());
        new Label(toolbar, SWT.SEPARATOR | SWT.VERTICAL);

        if (VimUtils.debug) {

            Button commitButton = new Button(toolbar, SWT.PUSH);
            commitButton.setData(RWT.CUSTOM_VARIANT, "whitebutton_s");

            commitButton.setImage(UI.getImage(ImageResource.SAVE_32));
            commitButton.addSelectionListener(new SelectionAdapter() {
                @Override
                public void widgetSelected(SelectionEvent e) {
                    if (folder != null && (!folder.isDisposed())) {

                        mform.commit(false);

                        boolean checked = folder.validCheck();
                        if (!checked) {

                        }

                        mform.commit(true);
                    }
                }
            });

        }
        DataObjectEditorInput input = printModule.getInput();
        if (input != null) {
            folder = new MultipageEditablePanel(editorArea, SWT.TOP | SWT.FLAT);
            folder.setMessageManager(form.getMessageManager());
            folder.createContents(mform, printModule.getInput());
            dataPreview.setMinSize(editorArea.computeSize(SWT.DEFAULT, SWT.DEFAULT));
        }

        toolbar.layout();
        editorArea.layout();
        contentPanel.setWeights(new int[] { 1, 3 });
    }

    public void doPrint(COCPrintModule cocPrintModule) {
        try {
            // 检查有无对应的已打印或已上传的合格证
            DBObject certData = VimUtils.getCertDataByVin(vin, "QX");
            if (certData == null) {
                throw new Exception("无法获取VIN对应的合格证，无法打印COC证书");
            }
            if (!IVIMFields.LC_PRINTED.equals(certData.get(IVIMFields.LIFECYCLE))
                    && !IVIMFields.LC_UPLOADED.equals(certData.get(IVIMFields.LIFECYCLE))) {
                throw new Exception("VIN对应的合格证已经失效，无法打印COC证书");
            }
            // 检查钢板弹簧是否有数据
            if (cocPrintModule.productCodeData == null
                    || Utils.isNullOrEmptyString(cocPrintModule.productCodeData
                            .get(IVIMFields.F_C6))) {
                throw new Exception("VIN对应的成品码缺少钢板弹簧的数据，无法打印COC证书");

            }

            VimUtils.printCOC(cocBrowser, cocPrintModule.getInput().getData().getData());

            // 设置模printed
            modules[1].setLifecycle(IVIMFields.LC_PRINTED);
            navigator.update(cocPrintModule, null);
        } catch (Exception e) {
            UIUtils.showMessage(getShell(), "打印", "打印COC发生错误\n" + e.getMessage(), SWT.ICON_ERROR);
        }

    }

    public void doPrint(CertPrintModule certPrintModule) {
        // 如果有底盘合格证数据先打印底盘合格证

        PrintModule dpmodule = getModulebyName(modules, DPCertPrintModule.NAME);
        PrintModule qxmodule = getModulebyName(modules, QXCertPrintModule.NAME);
        if (dpmodule.getInput() != null) {

            // 设置纸张编号
            setHGZPaperNumber(dpmodule);
            try {
                setZCHGZNumber(dpmodule);
                setPrinter(dpmodule, IVIMFields.PRINTER_FUNCTIONS[3]);
            } catch (Exception e) {
                UIUtils.showMessage(getShell(), "打印", "底盘合格证打印数据发生错误\n" + e.getMessage(),
                        SWT.ICON_ERROR);
                return;
            }
            DBObject data = dpmodule.getData();
            VimUtils.setValues(certBrowser, data);
            VimUtils.print(certBrowser);
            if (dpmodule.getError() != null) {
                UIUtils.showMessage(getShell(), "打印", "底盘合格证打印数据发生错误\n" + dpmodule.getError(),
                        SWT.ICON_ERROR);
                return;
            }
        }
        setHGZPaperNumber(qxmodule);
        try {
            setZCHGZNumber(qxmodule);
            setPrinter(qxmodule, IVIMFields.PRINTER_FUNCTIONS[0]);
        } catch (Exception e) {
            UIUtils.showMessage(getShell(), "打印", "整车合格证打印数据发生错误\n" + e.getMessage(),
                    SWT.ICON_ERROR);
            return;
        }
        DBObject data = qxmodule.getData();
        VimUtils.setValues(certBrowser, data);
        VimUtils.print(certBrowser);
        if (qxmodule.getError() != null) {
            UIUtils.showMessage(getShell(), "打印", "整车合格证打印数据发生错误\n" + qxmodule.getError(),
                    SWT.ICON_ERROR);
            return;
        }
        // }
        // 设置模块为可上传
        modules[0].setLifecycle(IVIMFields.LC_PRINTED);
        ((CertPrintModule) modules[0]).setHasPrint(true);
        ((CertPrintModule) modules[0]).setCanUploadData(true);
        navigator.update(certPrintModule, null);
    }

    public void doPrint(FuelCardPrintModule fuelCardPrintModule) {
        try {
            // 检查有无对应的已打印或已上传的合格证
            DBObject certData = VimUtils.getCertDataByVin(vin, "QX");
            if (certData == null) {
                throw new Exception("无法获取VIN对应的合格证，无法打印燃油标识");
            }
            if (!IVIMFields.LC_PRINTED.equals(certData.get(IVIMFields.LIFECYCLE))
                    && !IVIMFields.LC_UPLOADED.equals(certData.get(IVIMFields.LIFECYCLE))) {
                throw new Exception("VIN对应的合格证已经失效，无法打印燃油标识");
            }

            VimUtils.printFuelLabel(fuelBrowser, fuelCardPrintModule.getData());
            // 设置模printed
            modules[2].setLifecycle(IVIMFields.LC_PRINTED);
            navigator.update(fuelCardPrintModule, null);
        } catch (Exception e) {
            UIUtils.showMessage(getShell(), "打印", "打印燃油标识发生错误\n" + e.getMessage(), SWT.ICON_ERROR);
        }
    }

    private void setZCHGZNumber(PrintModule dpmodule) throws Exception {
        DBCollection ids = DBActivator.getCollection("appportal", "ids");
        String seq = DBUtil.getIncreasedID(ids, IVIMFields.SEQ_GZBH, "0", 10);
        // String companyId = (String) dpmodule.getData().get(IVIMFields.C_14);

        String string = "WAK1" + seq;
        if (!VimUtils.debug && string.length() != 14) {
            throw new Exception("无法取得正确的整车合格证编号。\n4位企业代码+10位顺序号");
        }
        dpmodule.getData().put(IVIMFields.mVeh_Zchgzbh, string);

    }

    private void setHGZPaperNumber(PrintModule module) {
        Integer inputPageNumber = module.getPaperNumber();
        if (inputPageNumber == null) {
            if (module instanceof DPCertPrintModule) {
                inputPageNumber = VimUtils.getMaxPaperOfDPCert();
            } else {
                inputPageNumber = VimUtils.getMaxPaperOfZCCert();
            }
        }
        module.setValue(IVIMFields.mVeh_Zzbh, String.format("%07d", inputPageNumber.intValue()));
    }

    private void setPrinter(PrintModule module, String printfunctionName) throws Exception {
        HashMap<String, String> printerPara = VimUtils.getPrinterParameters(printfunctionName);
        if (printerPara == null) {
            throw new Exception("没有对" + printfunctionName + "设置打印机");
        }
        Iterator<String> iter = printerPara.keySet().iterator();
        while (iter.hasNext()) {
            String key = iter.next();
            Object value = printerPara.get(key);
            module.setValue(key, value);
        }
    }

    private void doBarResultCallback(Object[] arguments) {
        if (arguments != null) {
            Object mVeh_Tmxx = arguments[0];
            Object mVeh_Clztxx = arguments[1];
            DPCertPrintModule dpCertPrintModule = ((CertPrintModule) modules[0])
                    .getDpCertPrintModule();
            QXCertPrintModule qxCertPrintModule = ((CertPrintModule) modules[0])
                    .getQxCertPrintModule();
            PrintModule currentModule = null;
            if (mVeh_Clztxx != null && "dp".equalsIgnoreCase(mVeh_Clztxx.toString())) {
                currentModule = dpCertPrintModule;
            } else if (mVeh_Clztxx != null && "qx".equalsIgnoreCase(mVeh_Clztxx.toString())) {
                currentModule = qxCertPrintModule;
            }
            if (currentModule != null) {
                currentModule.setValue(IVIMFields.mVeh_Tmxx, mVeh_Tmxx);
            }
        }
    }

    private void doPrintCertResultCallback(Object[] arguments) {
        if (VimUtils.debug) {
            Object jsReturn = 0;
            Object mVeh_ErrorInfo = 0;
            Object mVeh_Clztxx = arguments[2];
            Object mVeh__Wzghzbh = arguments[3];
            Object mVeh_Jyw = arguments[4];
            Object mVeh_Veh_Dywym = arguments[5];
            PrintModule currentModule = null;
            DPCertPrintModule dpCertPrintModule = ((CertPrintModule) modules[0])
                    .getDpCertPrintModule();
            QXCertPrintModule qxCertPrintModule = ((CertPrintModule) modules[0])
                    .getQxCertPrintModule();
            if (mVeh_Clztxx != null && "dp".equalsIgnoreCase(mVeh_Clztxx.toString())) {
                currentModule = dpCertPrintModule;
            } else if (mVeh_Clztxx != null && "qx".equalsIgnoreCase(mVeh_Clztxx.toString())) {
                currentModule = qxCertPrintModule;
            }
            if ("-1".equals(jsReturn.toString()) && !Utils.isNullOrEmptyString(mVeh_ErrorInfo)) {
                currentModule.setError(mVeh_ErrorInfo.toString());
            } else {
                currentModule.setError(null);
                if (currentModule != null) {
                    currentModule.setValue(IVIMFields.mVeh__Wzghzbh, mVeh__Wzghzbh);
                    currentModule.setValue(IVIMFields.mVeh_Jyw, mVeh_Jyw);
                    currentModule.setValue(IVIMFields.mVeh_Dywym, mVeh_Veh_Dywym);
                }
                if (currentModule == dpCertPrintModule) {
                    qxCertPrintModule.setValue(IVIMFields.mVeh_Dphgzbh, mVeh__Wzghzbh);// 将底盘生成的合格证号写入到整车数据中
                }

                savePrintData(currentModule);

                if (currentModule == qxCertPrintModule) {
                    // 生成环保数据
                    VimUtils.createEnvData(currentModule.getData());
                }

            }
            return;
        }
        if (arguments != null) {
            // jsreturn,Veh_ErrorInfo,Veh_Clztxx,VehCert.Veh_Zchgzbh,VehCert.Veh_Jyw,
            // VehCert.Veh_Dywym

            Object jsReturn = arguments[0];
            Object mVeh_ErrorInfo = arguments[1];
            Object mVeh_Clztxx = arguments[2];
            Object mVeh__Wzghzbh = arguments[3];
            Object mVeh_Jyw = arguments[4];
            Object mVeh_Veh_Dywym = arguments[5];
            PrintModule currentModule = null;
            DPCertPrintModule dpCertPrintModule = ((CertPrintModule) modules[0])
                    .getDpCertPrintModule();
            QXCertPrintModule qxCertPrintModule = ((CertPrintModule) modules[0])
                    .getQxCertPrintModule();
            if (mVeh_Clztxx != null && "dp".equalsIgnoreCase(mVeh_Clztxx.toString())) {
                currentModule = dpCertPrintModule;
            } else if (mVeh_Clztxx != null && "qx".equalsIgnoreCase(mVeh_Clztxx.toString())) {
                currentModule = qxCertPrintModule;
            }
            if ("-1".equals(jsReturn.toString()) && !Utils.isNullOrEmptyString(mVeh_ErrorInfo)) {
                currentModule.setError(mVeh_ErrorInfo.toString());
            } else {
                currentModule.setError(null);
                if (currentModule != null) {
                    currentModule.setValue(IVIMFields.mVeh__Wzghzbh, mVeh__Wzghzbh);
                    currentModule.setValue(IVIMFields.mVeh_Jyw, mVeh_Jyw);
                    currentModule.setValue(IVIMFields.mVeh_Dywym, mVeh_Veh_Dywym);
                }
                if (currentModule == dpCertPrintModule) {
                    qxCertPrintModule.setValue(IVIMFields.mVeh_Dphgzbh, mVeh__Wzghzbh);// 将底盘生成的合格证号写入到整车数据中
                }
                savePrintData(currentModule);
            }
        }

    }

    private void savePrintData(PrintModule module) {
        // 设置打印日期，打印人，以及当前的状态
        DBObject moduleData = module.getData();
        Date date = new Date();
        DBObject accountInfo = UserSessionContext.getAccountInfo();
        moduleData.put(IVIMFields.PRINTDATE, date);
        moduleData.put(IVIMFields.PRINTACCOUNT, accountInfo);
        moduleData.put(IVIMFields.LIFECYCLE, IVIMFields.LC_PRINTED);

        List<DBObject> actionList = new ArrayList<DBObject>();
        actionList.add(new BasicDBObject().append(IVIMFields.ACTION_REC_DATE, date)
                .append(IVIMFields.ACTION_REC_ACCOUNT, accountInfo)
                .append(IVIMFields.ACTION_REC_TYPE, IVIMFields.ACTION_REC_TYPE_VALUE_PRINT)
                .append(IVIMFields.ACTION_REC_MEMO, "No.:" + moduleData.get(IVIMFields.mVeh_Zzbh)));
        moduleData.put(IVIMFields.ACTION_REC, actionList);
        module.save();
    }

    public void doUpload(CertPrintModule certPrintModule) {
        List<DBObject> list = new ArrayList<DBObject>();
        PrintModule[] sb = certPrintModule.getSubModules();
        for (int i = 0; i < sb.length; i++) {
            DataObjectEditorInput input = sb[i].getInput();
            if (input != null) {
                DBObject moduleData = input.getData().getData();
                if (moduleData != null) {
                    list.add(moduleData);
                }
            }
        }
        try {
            if (!VimUtils.debug) {
                VimUtils.uploadCert(list);
            }
            saveUploadData(sb);
        } catch (Exception e) {
            UIUtils.showMessage(getShell(), "上传", "合格证数据上传时发生错误\n" + e.getMessage(), SWT.ICON_ERROR);
            return;
        }
        modules[0].setLifecycle(IVIMFields.LC_UPLOADED);
        ((CertPrintModule) modules[0]).setCanUploadData(false);
        navigator.update(certPrintModule, null);

    }

    private void saveUploadData(PrintModule[] sb) {
        List<ObjectId> idList = new ArrayList<ObjectId>();
        for (int i = 0; i < sb.length; i++) {
            idList.add((ObjectId) sb[i].getData().get("_id"));
        }

        VimUtils.saveUploadData(idList, "", IVIMFields.COL_CERF,
                IVIMFields.ACTION_REC_TYPE_VALUE_UPLOAD);
    }

}
