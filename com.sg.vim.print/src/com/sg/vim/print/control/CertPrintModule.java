package com.sg.vim.print.control;

import java.util.Map;

import com.mobnut.commons.util.file.FileUtil;
import com.sg.ui.model.DataObjectEditorInput;
import com.sg.vim.datamodel.util.VimUtils;
import com.sg.vim.print.PrintActivator;

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
            builder.append("<br/><span style='FONT-FAMILY:微软雅黑;font-size:14pt'>整车合格证</span>");
            return builder.toString();
        }

        @Override
        public DataObjectEditorInput getInput() {
            return input;
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
            builder.append("<br/><span style='FONT-FAMILY:微软雅黑;font-size:14pt'>底盘合格证</span>");
            return builder.toString();

        }

        @Override
        public DataObjectEditorInput getInput() {
            return dpInput;
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
        builder.append("<br/><span style='FONT-FAMILY:微软雅黑;font-size:14pt'>合格证</span>");

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

}
