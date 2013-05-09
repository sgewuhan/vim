package com.sg.vim.print.module;

import java.util.Map;

import com.mobnut.commons.util.file.FileUtil;
import com.mobnut.db.DBActivator;
import com.mobnut.db.utils.DBUtil;
import com.mongodb.DBCollection;
import com.sg.ui.model.DataObjectEditorInput;
import com.sg.vim.datamodel.IVIMFields;
import com.sg.vim.datamodel.util.VimUtils;
import com.sg.vim.print.PrintActivator;
import com.sg.vim.print.control.PrintContent;
import com.sg.vim.print.module.action.AllCertPrintAction;
import com.sg.vim.print.module.action.AllCertUploadAction;
import com.sg.vim.print.module.action.ModuleAction;

public class CertPrintModule extends PrintModule {

    public static final String NAME = "CertPrintModule";
    private QXCertPrintModule qxCertPrintModule;
    private DPCertPrintModule dpCertPrintModule;
    private DataObjectEditorInput dpinput;
    private DataObjectEditorInput input;
    private boolean canUploadData;

    public CertPrintModule() {
        super();
        qxCertPrintModule = new QXCertPrintModule();
        dpCertPrintModule = new DPCertPrintModule();
    }

    @Override
    public PrintModule[] getSubModules() {
        return new PrintModule[] { qxCertPrintModule, dpCertPrintModule };
    }

    @Override
    public PrintModule getParentModules() {
        return null;
    }

    @Override
    public boolean hasSubModules() {
        return true;
    }

    @Override
    public String getText() {
        StringBuilder builder = new StringBuilder();
        builder.append("<img src=\"");
        if (isEnable()) {
            builder.append(FileUtil.getImageURL("folder_48.png", PrintActivator.PLUGIN_ID, "image"));
        } else {
            builder.append(FileUtil.getImageURL("folder_48.png", PrintActivator.PLUGIN_ID, "image"));
        }
        builder.append("\"  width='48' height='48' style='float:left;padding:5px'/>");
        builder.append("<span style='FONT-FAMILY:΢���ź�;font-size:11pt'><b>�ϸ�֤</b></span><br/><small>");
        if (canPrintData()) {
            builder.append(getIcon("print_16.png", 16, 16));
            builder.append("<a href=\"" + _PRINT + "@" + getName() + "\" target=\"_rwt\">��ӡ</a>   ");
        }
        if (canUploadData()) {
            builder.append(getIcon("upload_16.png", 16, 16));
            builder.append("<a href=\"" + _UPLOAD + "@" + getName()
                    + "\" target=\"_rwt\">�ϴ�</a>   ");
        }
        if(lifecycle!=null){
            builder.append("<b>״̬: "+lifecycle+"</b>");
        }
        builder.append("</small>");

        return builder.toString();
    }

    @Override
    public void setInput(Map<String, Object> para) throws Exception {
        super.setInput(para);
        if (dpcocData != null && dpconfData != null) {
            dpinput = VimUtils.getCerfInput(dpcocData, dpconfData, productCodeData, mesRawData,
                    null, vin, true);
            dpCertPrintModule.setInput(para);
            String lifecycle = VimUtils.getLifecycle(vin,VimUtils.COL_CERF);
            dpCertPrintModule.setLifecycle(lifecycle);
        } else {
            dpinput = null;
        }
        dpCertPrintModule.setInputData(dpinput);

        input = VimUtils.getCerfInput(cocData, confData, productCodeData, mesRawData, null, vin,
                false);
        qxCertPrintModule.setInput(para);
        String lifecycle = VimUtils.getLifecycle(vin,VimUtils.COL_CERF);
        qxCertPrintModule.setLifecycle(lifecycle);
        qxCertPrintModule.setInputData(input);
        canUploadData = false;
        setLifecycle(lifecycle);
    }

    @Override
    public DataObjectEditorInput getInput() {
        return input;
    }

    @Override
    public ModuleAction[] getActions() {
        AllCertPrintAction action1 = new AllCertPrintAction();
        AllCertUploadAction action2 = new AllCertUploadAction();

        return new ModuleAction[] { action1, action2 };
    }

    @Override
    public String getHeadText() {
        return "<span style='FONT-FAMILY:΢���ź�;font-size:15pt'>�ϸ�֤</span>";
    }

    @Override
    public void fireEvent(String eventCode, String[] arg, PrintContent pc) {
        if (eventCode.equals(_PRINT)) {
            doPrint(pc);
        } else if (eventCode.contains(_UPLOAD)) {
            pc.doUpload(this);
        }
    }

    @Override
    public void doPrint(PrintContent pc) {
        pc.doPrint(this);
    }

    /**
     * ���޵�������
     * 
     * @return
     */
    public boolean hasDPData() {
        return dpCertPrintModule.getInput() != null;
    }

    @Override
    public String getName() {
        return NAME;
    }

    public DPCertPrintModule getDpCertPrintModule() {
        return dpCertPrintModule;
    }

    public QXCertPrintModule getQxCertPrintModule() {
        return qxCertPrintModule;
    }

    @Override
    public String getDisplayedPaperNumber() {
        if (paperNumber == null) {
            if(canPrintData()){
                DBCollection ids = DBActivator.getCollection("appportal", "ids");
                int currentId = DBUtil.getCurrentID(ids, "Veh_Zzbh");
                String s = String.format("%" + 0 + 7 + "d", currentId);
                return "<span style='FONT-FAMILY:΢���ź�;font-size:11pt'><small>" + "ʹ���Զ�ֽ�ű��, ��ǰֵ:"+s
                        + "<br/>����<ins>˫��</ins>������ʼֽ�ű��</small></span>";
            }else{
                return "";
            }
        } else {
            return "<span style='FONT-FAMILY:΢���ź�;font-size:11pt'>��ʼֽ�ű��<br/><ins>No.</ins>: "
                    + getInputPaperNumber() + "</span>";
        }
    }

    @Override
    public void setInputPaperNumber(int i) {
        Integer no = i;
        if (hasDPData()) {
            dpCertPrintModule.setInputPaperNumber(no);
            no++;
        }
        qxCertPrintModule.setInputPaperNumber(no);
        DBCollection ids = DBActivator.getCollection("appportal", "ids");
        DBUtil.setCurrentID(ids, "Veh_Zzbh", no + 1);
        super.setInputPaperNumber(i);
    }

    @Override
    public String getInputPaperNumber() {
        if (paperNumber != null) {
            return String.format("%" + 0 + 7 + "d", paperNumber);
        } else {
            return "";
        }
    }

    public void setCanUploadData(boolean b) {
        canUploadData = b;
    }

    @Override
    public boolean canInputPaperNumber() {
        return canPrintData();
    }

    public boolean canUploadData() {//ֻ���Ѿ���ӡ��״̬�����ϴ�
        return canUploadData&&IVIMFields.LC_PRINTED.equals(lifecycle);
    }

    public boolean canPrintData() {//ֻ����״̬���ܴ�ӡ
        return (!isHasPrint())&&(getInput() != null)&&(lifecycle==null);
    }

 


}
