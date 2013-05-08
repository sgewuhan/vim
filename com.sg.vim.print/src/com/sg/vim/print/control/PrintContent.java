package com.sg.vim.print.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.jface.viewers.Viewer;
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
import org.eclipse.ui.forms.ManagedForm;
import org.eclipse.ui.forms.widgets.Form;

import com.mobnut.commons.util.Utils;
import com.mobnut.db.DBActivator;
import com.mobnut.db.utils.DBUtil;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.sg.sqldb.utility.SQLRow;
import com.sg.ui.ImageResource;
import com.sg.ui.UI;
import com.sg.ui.UIUtils;
import com.sg.ui.model.DataObject;
import com.sg.ui.model.DataObjectEditorInput;
import com.sg.ui.part.MultipageEditablePanel;
import com.sg.vim.datamodel.IVIMFields;
import com.sg.vim.datamodel.util.VimUtils;
import com.sg.vim.print.PrintActivator;
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
    private DBObject cocData;
    private DBObject confData;
    private DBObject dpcocData;
    private DBObject dpconfData;
    private DBObject productCodeData;

    private SQLRow mesRawData;
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

    public PrintContent(ManagedForm mform, Form form, int style) {
        super(form.getBody(), style);
        this.mform = mform;
        this.form = form;
        initModule();

        pushSession = new ServerPushSession();
        pushSession.start();

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

        contentPanel = createContent();
        fd = new FormData();
        contentPanel.setLayoutData(fd);
        fd.top = new FormAttachment(banner, margin * 2);
        fd.left = new FormAttachment(0, margin);
        fd.right = new FormAttachment(100, -margin);
        fd.bottom = new FormAttachment(100, -margin);

        certBrowser = new Browser(this, SWT.NONE);
        certBrowser.setUrl("/vert");
        printCertResultFunction = new BrowserFunction(certBrowser, "printCertResult") {

            // jsreturn,Veh_ErrorInfo,Veh_Clztxx,VehCert.Veh_Zchgzbh,VehCert.Veh_Jyw,
            // VehCert.Veh_Dywym
            public Object function(Object[] arguments) {
                if (arguments != null) {
                    // Object jsReturn = arguments[0];
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
                    if (!Utils.isNullOrEmptyString(mVeh_ErrorInfo)) {
                        currentModule.setError(mVeh_ErrorInfo.toString());
                    } else {
                        if (currentModule != null) {
                            DataObject data = currentModule.getInput().getData();
                            data.setValue(VimUtils.mVeh__Wzghzbh, mVeh__Wzghzbh);
                            data.setValue(VimUtils.mVeh_Jyw, mVeh_Jyw);
                            data.setValue(VimUtils.mVeh_Dywym, mVeh_Veh_Dywym);
                        }
                        if (currentModule == dpCertPrintModule) {
                            qxCertPrintModule.getInput().getData()
                                    .setValue(VimUtils.mVeh_Dphgzbh, mVeh__Wzghzbh);// 将底盘生成的合格证号写入到整车数据中
                        }
                        
                        currentModule.save();
                    }
                }
                return super.function(arguments);
            }

        };

        barResultFunction = new BrowserFunction(certBrowser, "barResult") {

            @Override
            public Object function(Object[] arguments) {
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
                        DataObject data = currentModule.getInput().getData();
                        data.setValue(VimUtils.mVeh_Tmxx, mVeh_Tmxx);
                    }

                }

                return super.function(arguments);
            }

        };
        fd = new FormData();
        certBrowser.setLayoutData(fd);
        fd.top = new FormAttachment(banner);
        fd.left = new FormAttachment();
        fd.width = margin;
        fd.height = margin;

    }

    private void initModule() {
        modules = new PrintModule[3];
        modules[0] = new CertPrintModule();
        modules[1] = new COCPrintModule();
        modules[2] = new FuelCardPrintModule();
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
                doQueryButtonPressed();
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
        // DBObject data = input.getData().getData();
        // VimUtils.setValues(certBrowser, data);
        // VimUtils.print(certBrowser);
    }

    protected void doQueryButtonPressed() {

        resetData();
        vin = vinInputText.getText();

        // 检查输入的vin是否合法
        boolean valid = VimUtils.checkVIN(vin);
        if (!valid) {
            UIUtils.showMessage(getShell(), "输入VIN", "输入的VIN不合法，请重新输入。", SWT.ERROR);
            return;
        }

        Job job = new Job("组装合格证数据VIN" + vin) {

            @Override
            protected IStatus run(IProgressMonitor monitor) {
                // 查询数据库是否有对应vin的成品码记录
                String message = "正在查询外部数据库中的匹配VIN的成品记录";
                try {
                    mesRawData = VimUtils.getProductCode(vin);
                } catch (Exception e) {
                    return new Status(Status.ERROR, PrintActivator.PLUGIN_ID, message, e);
                }
                Object productCode = mesRawData.getValue(VimUtils.FIELD_PRODUCT_CODE);
                if (!(productCode instanceof String)) {
                    return new Status(Status.ERROR, PrintActivator.PLUGIN_ID,
                            "查询MES成品记录,MES数据库的成品码不是字符串类型");
                }

                // 获取成品码对应成品码数据 LNBMDLAA6CU000289
                // 公告车型 productcodeinfo.f_0_2c
                message = "正在查询VIM数据库中的成品码记录";
                try {
                    productCodeData = VimUtils.getProductCodeInfo((String) productCode);
                } catch (Exception e) {
                    return new Status(Status.ERROR, PrintActivator.PLUGIN_ID, message, e);
                }

                // 查询COC绑定数据
                message = "正在查询成品码车型一致性信息";
                try {
                    cocData = VimUtils.getCOCInfo(productCodeData);
                } catch (Exception e) {
                    return new Status(Status.ERROR, PrintActivator.PLUGIN_ID, message, e);
                }
                // 查询配置数据
                message = "正在查询成品码车型配置信息";
                try {
                    confData = VimUtils.getConfInfo(productCodeData);
                } catch (Exception e) {
                    return new Status(Status.ERROR, PrintActivator.PLUGIN_ID, message, e);
                }
                // 处理底盘有关数据
                // 判断有无底盘ID
                /**
                 * 输入VIN时，有底盘型号和底盘ID不为空，而先打印底盘合格证。如果系统中有这台车的底盘合格证就不用打印底盘合格证， 就直接打印整车合格证，
                 * 如果已存在此车的整车合格证就不用打印，。打印了底盘合格证后，再打印整车合格证时，在整车合格证位置， 打印出整车合格证编号和底盘合格证编号。
                 */
                Object dpId = cocData.get(IVIMFields.C_12);
                if ((dpId instanceof String) && (dpId.toString().length() > 0)) {
                    message = "正在查询成品码底盘信息";
                    // 找到对应的底盘信息
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
                final IStatus result = event.getJob().getResult();
                getDisplay().asyncExec(new Runnable() {

                    @Override
                    public void run() {
                        if (result.isOK()) {
                            try {
                                setModuleInput();
                                printButton.setEnabled(true);
                            } catch (Exception e) {
                                UIUtils.showMessage(getShell(), "组合机动车完整数据",
                                        "错误:" + e.getMessage(), SWT.ERROR);
                                printButton.setEnabled(false);
                            }
                        }
                        setOperationEnable(true);
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
        vinInputText.setEnabled(b);
    }

    private void setModuleInput() throws Exception {

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
    }

    private void resetData() {
        vin = null;
        mesRawData = null;
        cocData = null;
        confData = null;
        dpcocData = null;
        dpconfData = null;
        productCodeData = null;
        ((CertPrintModule) modules[0]).setCanUploadData(false);

        // input = null;
    }

    private SashForm createContent() {

        SashForm sash = new SashForm(this, SWT.HORIZONTAL);
        Composite navigatorPanel = new Composite(sash, SWT.NONE);
        navigatorPanel.setLayout(new FillLayout());
        navigator = new TreeViewer(navigatorPanel, SWT.BORDER | SWT.FULL_SELECTION | SWT.CHECK);
        // navigator.getTree().setLinesVisible(true);
        navigator.getTree().setData(RWT.MARKUP_ENABLED, Boolean.TRUE);
        navigator.getTree().setData(RWT.CUSTOM_ITEM_HEIGHT, 58);
        navigator.setContentProvider(new ITreeContentProvider() {

            @Override
            public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
            }

            @Override
            public void dispose() {
            }

            @Override
            public boolean hasChildren(Object element) {
                return ((PrintModule) element).hasSubModules();
            }

            @Override
            public Object getParent(Object element) {
                return ((PrintModule) element).getParentModules();
            }

            @Override
            public Object[] getElements(Object inputElement) {
                return (Object[]) inputElement;
            }

            @Override
            public Object[] getChildren(Object parentElement) {
                return ((PrintModule) parentElement).getSubModules();
            }
        });
        TreeViewerColumn col = new TreeViewerColumn(navigator, SWT.LEFT);
        col.getColumn().setWidth(300);
        col.setLabelProvider(new ColumnLabelProvider() {
            @Override
            public String getText(Object element) {
                return ((PrintModule) element).getText();
            }
        });

        col = new TreeViewerColumn(navigator, SWT.LEFT);
        col.getColumn().setWidth(260);
        col.setLabelProvider(new ColumnLabelProvider() {
            @Override
            public String getText(Object element) {
                return ((PrintModule) element).getDisplayedPaperNumber();
            }
        });
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

    protected PrintModule getModulebyName(PrintModule[] modules, String className) {
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

        /** 暂时不提供此功能 **/
        // ModuleAction[] actions = printModule.getActions();
        // for (int i = 0; i < actions.length; i++) {
        // Button button = new Button(toolbar, SWT.PUSH);
        // final ModuleAction moduleAction = actions[i];
        // button.setImage(moduleAction.getImage());
        // button.setToolTipText(actions[i].getText());
        // button.setData(RWT.CUSTOM_VARIANT, "whitebutton_s");
        // button.addSelectionListener(new SelectionAdapter() {
        // @Override
        // public void widgetSelected(SelectionEvent e) {
        // moduleAction.run();
        // }
        // });
        // }

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

    public void doPrint(CertPrintModule certPrintModule) {

        if (!VimUtils.debug) {

            // 如果有底盘合格证数据先打印底盘合格证
            PrintModule dpmodule = getModulebyName(modules, DPCertPrintModule.NAME);
            PrintModule qxmodule = getModulebyName(modules, QXCertPrintModule.NAME);
            if (dpmodule.getInput() != null) {
                setHGZPaperNumber(dpmodule);
                DBObject data = dpmodule.getInput().getData().getData();
                VimUtils.setValues(certBrowser, data);
                VimUtils.print(certBrowser);
                if (dpmodule.getError() != null || qxmodule.getError() != null) {
                    UIUtils.showMessage(getShell(), "打印", "底盘合格证打印数据发生错误\n" + dpmodule.getError(),
                            SWT.ICON_ERROR);
                    return;
                }
            }

            DBObject data = qxmodule.getInput().getData().getData();
            VimUtils.setValues(certBrowser, data);
            VimUtils.print(certBrowser);
            if (dpmodule.getError() != null) {
                UIUtils.showMessage(getShell(), "打印", "整车合格证打印数据发生错误\n" + qxmodule.getError(),
                        SWT.ICON_ERROR);
                return;
            }

        }
        // 设置模块为可上传
        ((CertPrintModule) modules[0]).setHasPrint(true);
        ((CertPrintModule) modules[0]).setCanUploadData(true);
        navigator.update(certPrintModule, null);

    }

    private void setHGZPaperNumber(PrintModule module) {
        Integer inputPageNumber = module.getPaperNumber();
        DBCollection ids = DBActivator.getCollection("appportal", "ids");
        String pnum;
        if(inputPageNumber!=null){
            pnum = String.format("%07d", inputPageNumber.intValue());
        }else{
            pnum = DBUtil.getIncreasedID(ids, "Veh_Zzbh", "0", 7);
        }
        module.getInput().getData().setValue(VimUtils.mVeh_Zzbh, pnum);
    }

    public void doUpload(CertPrintModule certPrintModule) {
        if (!VimUtils.debug) {
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
                VimUtils.uploadCert(list);
            } catch (Exception e) {
                UIUtils.showMessage(getShell(), "上传", "合格证数据上传时发生错误\n" + e.getMessage(),
                        SWT.ICON_ERROR);
                return;
            }
        }

        ((CertPrintModule) modules[0]).setCanUploadData(false);
        navigator.update(certPrintModule, null);

    }

}
