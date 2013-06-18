package com.sg.vim.print.module;

import java.util.Map;

import com.mobnut.commons.util.file.FileUtil;
import com.mobnut.db.DBActivator;
import com.mobnut.db.utils.DBUtil;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.sg.ui.model.DataObjectEditorInput;
import com.sg.vim.Vim;
import com.sg.vim.VimUtils;
import com.sg.vim.model.IVIMFields;
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
    @SuppressWarnings("unused")
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
            builder.append(FileUtil.getImageURL("folder_48.png", Vim.PLUGIN_ID, "image"));
        } else {
            builder.append(FileUtil.getImageURL("folder_48.png", Vim.PLUGIN_ID, "image"));
        }
        builder.append("\"  width='48' height='48' style='float:left;padding:5px'/>");
        builder.append("<span style='FONT-FAMILY:΢���ź�;font-size:11pt'><b>�ϸ�֤</b></span><br/><small>");
        if (canPrintData()) {
            builder.append(getIcon("print_16.png", 16, 16));
            if (IVIMFields.LC_ABANDON.equals(lifecycle)) {
                builder.append("<a href=\"" + _PRINT + "@" + getName()
                        + "\" target=\"_rwt\">�����ϣ����´�ӡ</a>   ");
            } else {
                builder.append("<a href=\"" + _PRINT + "@" + getName()
                        + "\" target=\"_rwt\">��ӡ</a>   ");
            }
        }
        if (canUploadData()) {
            builder.append(getIcon("upload_16.png", 16, 16));
            builder.append("<a href=\"" + _UPLOAD + "@" + getName()
                    + "\" target=\"_rwt\">�ϴ�</a>   ");
        }
        if (lifecycle != null) {
            builder.append("<b>״̬: " + lifecycle + "</b>");
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
            // ������������״̬
            DBObject certData = VimUtils.getCertDataByVin(vin, "DP");
            if (certData != null) {
                String lifecycle = (String) certData.get(IVIMFields.LIFECYCLE);
                dpCertPrintModule.setLifecycle(lifecycle);
                if (IVIMFields.LC_ABANDON.equals(lifecycle)) {// ����������ϣ���Ҫ�������ϵĺϸ�֤��Ÿ��ƹ���
                    Object custCertNum = certData.get(IVIMFields.mVeh_Zchgzbh);
                    dpCertPrintModule.setValue(IVIMFields.mVeh_Zchgzbh, custCertNum);
                }
            }
        } else {
            dpinput = null;
        }
        dpCertPrintModule.setInputData(dpinput);

        input = VimUtils.getCerfInput(cocData, confData, productCodeData, mesRawData, null, vin,
                false);
        qxCertPrintModule.setInput(para);

        // ������������״̬
        DBObject certData = VimUtils.getCertDataByVin(vin, "QX");
        lifecycle =null;
        if (certData != null) {
            lifecycle = (String) certData.get(IVIMFields.LIFECYCLE);
            qxCertPrintModule.setLifecycle(lifecycle);
            if (IVIMFields.LC_ABANDON.equals(lifecycle)) {// ����������ϣ���Ҫ�������ϵĺϸ�֤��Ÿ��ƹ���
                Object custCertNum = certData.get(IVIMFields.mVeh_Zchgzbh);
                qxCertPrintModule.setValue(IVIMFields.mVeh_Zchgzbh, custCertNum);
            }
        }
        
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
        return false;
    }

    public boolean canUploadData() {// ֻ���Ѿ���ӡ��״̬�����ϴ�,���δ˹���
        return false;
//        return canUploadData && IVIMFields.LC_PRINTED.equals(lifecycle);
    }

    public boolean canPrintData() {// ֻ����״̬���������ϲ��ܴ�ӡ
        return (!isHasPrint()) && (getInput() != null)
                && ((lifecycle == null) || (lifecycle.equals(IVIMFields.LC_ABANDON)));
    }

}