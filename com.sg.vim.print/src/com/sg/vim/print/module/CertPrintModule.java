package com.sg.vim.print.module;

import java.util.Map;

import com.mobnut.commons.util.file.FileUtil;
import com.sg.ui.model.DataObjectEditorInput;
import com.sg.vim.datamodel.util.VimUtils;
import com.sg.vim.print.PrintActivator;
import com.sg.vim.print.module.action.AllCertPrintAction;
import com.sg.vim.print.module.action.AllCertUploadAction;
import com.sg.vim.print.module.action.DPCertPrintAction;
import com.sg.vim.print.module.action.DPCertUploadAction;
import com.sg.vim.print.module.action.ModuleAction;
import com.sg.vim.print.module.action.QxCertPrintAction;
import com.sg.vim.print.module.action.QxCertUploadAction;

public class CertPrintModule extends PrintModule {

    public class QXCertPrintModule extends PrintModule {

        @Override
        public PrintModule[] getSubModules() {
            return null;
        }

        @Override
        public PrintModule getParentModules() {
            return null;
        }

        @Override
        public boolean hasSubModules() {
            return false;
        }

        @Override
        public String getText() {
            StringBuilder builder = new StringBuilder();
            builder.append("<img src=\"");
            if (isEnable()) {
                builder.append(FileUtil.getImageURL("certqx_48.png", PrintActivator.PLUGIN_ID,
                        "image"));
            } else {
                builder.append(FileUtil.getImageURL("certqx_d_48.png", PrintActivator.PLUGIN_ID,
                        "image"));
            }
            builder.append("\"  width='48' height='48' style='float:left;padding:5px'/>");
            builder.append("<br/><span style='FONT-FAMILY:微软雅黑;font-size:11pt'>机动车出厂合格证</span>");
            return builder.toString();
        }

        @Override
        public DataObjectEditorInput getInput() {
            return input;
        }

        @Override
        public ModuleAction[] getActions() {
            QxCertPrintAction action1 = new QxCertPrintAction();
            QxCertUploadAction action2 = new QxCertUploadAction();
            
            return new ModuleAction[]{action1,action2};
        }

    }

    public class DPCertPrintModule extends PrintModule {

        @Override
        public PrintModule[] getSubModules() {
            return null;
        }

        @Override
        public PrintModule getParentModules() {
            return null;
        }

        @Override
        public boolean hasSubModules() {
            return false;
        }

        @Override
        public String getText() {
            StringBuilder builder = new StringBuilder();
            builder.append("<img src=\"");
            if (isEnable()) {
                builder.append(FileUtil
                        .getImageURL("doc_48.png", PrintActivator.PLUGIN_ID, "image"));
            } else {
                builder.append(FileUtil.getImageURL("doc_d_48.png", PrintActivator.PLUGIN_ID,
                        "image"));
            }
            builder.append("\"  width='48' height='48' style='float:left;padding:5px'/>");
            builder.append("<br/><span style='FONT-FAMILY:微软雅黑;font-size:11pt'>底盘合格证</span>");
            return builder.toString();

        }

        @Override
        public DataObjectEditorInput getInput() {
            return dpInput;
        }

        @Override
        public ModuleAction[] getActions() {
            DPCertPrintAction action1 = new DPCertPrintAction();
            DPCertUploadAction action2 = new DPCertUploadAction();
            
            return new ModuleAction[]{action1,action2};
        }

    }

    @Override
    public PrintModule[] getSubModules() {
        QXCertPrintModule qxCertPrintModule = new QXCertPrintModule();
        DPCertPrintModule dpCertPrintModule = new DPCertPrintModule();
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
        builder.append("<br/><span style='FONT-FAMILY:微软雅黑;font-size:11pt'>合格证</span>");

        return builder.toString();
    }

    private DataObjectEditorInput dpInput;
    private DataObjectEditorInput input;


    @Override
    public void setInput(Map<String, Object> para) throws Exception {
        super.setInput(para);
        if (hasDP()) {
            dpInput = VimUtils.getCerfInput(dpcocData, dpconfData, productCodeData, mesRawData,
                    null, vin, true);
        } else {
            dpInput = null;
        }

        input = VimUtils.getCerfInput(cocData, confData, productCodeData, mesRawData, null, vin,
                false);
    }

    private boolean hasDP() {
        return dpcocData != null && dpconfData != null;
    }

    @Override
    public DataObjectEditorInput getInput() {
        return input;
    }

    @Override
    public ModuleAction[] getActions() {
        AllCertPrintAction action1 = new AllCertPrintAction();
        AllCertUploadAction action2 = new AllCertUploadAction();
        
        return new ModuleAction[]{action1,action2};
    }

}
