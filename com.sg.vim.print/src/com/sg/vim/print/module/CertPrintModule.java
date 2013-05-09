package com.sg.vim.print.module;

import java.util.Map;

import com.mobnut.commons.util.file.FileUtil;
import com.mobnut.db.DBActivator;
import com.mobnut.db.utils.DBUtil;
import com.mongodb.DBCollection;
import com.sg.ui.model.DataObjectEditorInput;
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
        builder.append("<span style='FONT-FAMILY:微软雅黑;font-size:11pt'><b>合格证</b></span><br/><small>");
        if (canPrintData()) {
            builder.append(getIcon("print_16.png", 16, 16));
            builder.append("<a href=\"" + _PRINT + "@" + getName() + "\" target=\"_rwt\">打印</a>   ");
        }
        if (canUploadData()) {
            builder.append(getIcon("upload_16.png", 16, 16));
            builder.append("<a href=\"" + _UPLOAD + "@" + getName()
                    + "\" target=\"_rwt\">上传</a>   ");
        }
        builder.append("</small>");

        return builder.toString();
    }

    public boolean canUploadData() {
        return canUploadData;
    }

    public void setCanUploadData(boolean b) {
        canUploadData = b;
    }

    @Override
    public void setInput(Map<String, Object> para) throws Exception {
        super.setInput(para);
        if (dpcocData != null && dpconfData != null) {
            dpinput = VimUtils.getCerfInput(dpcocData, dpconfData, productCodeData, mesRawData,
                    null, vin, true);
            dpCertPrintModule.setInput(para);
        } else {
            dpinput = null;
        }
        dpCertPrintModule.setInputData(dpinput);

        input = VimUtils.getCerfInput(cocData, confData, productCodeData, mesRawData, null, vin,
                false);
        qxCertPrintModule.setInput(para);
        qxCertPrintModule.setInputData(input);
        canUploadData = false;
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
        return "<span style='FONT-FAMILY:微软雅黑;font-size:15pt'>合格证</span>";
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
     * 有无底盘数据
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
                return "<span style='FONT-FAMILY:微软雅黑;font-size:11pt'><small>" + "使用自动纸张编号, 当前值:"+s
                        + "<br/>或者<ins>双击</ins>设置起始纸张编号</small></span>";
            }else{
                return "";
            }
        } else {
            return "<span style='FONT-FAMILY:微软雅黑;font-size:11pt'>起始纸张编号<br/><ins>No.</ins>: "
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
    public boolean canInputPaperNumber() {
        return canPrintData();
    }

    @Override
    public String getInputPaperNumber() {
        if (paperNumber != null) {
            return String.format("%" + 0 + 7 + "d", paperNumber);
        } else {
            return "";
        }
    }

    public boolean canPrintData() {
        return !isHasPrint()&&getInput() != null;
    }

 


}
