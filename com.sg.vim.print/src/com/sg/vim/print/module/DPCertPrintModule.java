package com.sg.vim.print.module;

import com.mobnut.commons.util.file.FileUtil;
import com.sg.ui.model.DataObjectEditorInput;
import com.sg.vim.print.PrintActivator;
import com.sg.vim.print.control.PrintContent;
import com.sg.vim.print.module.action.DPCertPrintAction;
import com.sg.vim.print.module.action.DPCertUploadAction;
import com.sg.vim.print.module.action.ModuleAction;

public class DPCertPrintModule extends PrintModule {

    public static final String NAME = "DPCertPrintModule";
    private DataObjectEditorInput input;

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
    public String getHeadText() {
        return "<span style='FONT-FAMILY:΢���ź�;font-size:16pt'>���̺ϸ�֤</span>";
    }

    @Override
    public String getText() {
        StringBuilder builder = new StringBuilder();
        builder.append("<img src=\"");
        if (isEnable()) {
            builder.append(FileUtil.getImageURL("doc_48.png", PrintActivator.PLUGIN_ID, "image"));
        } else {
            builder.append(FileUtil.getImageURL("doc_d_48.png", PrintActivator.PLUGIN_ID, "image"));
        }
        builder.append("\"  width='48' height='48' style='float:left;padding:5px'/>");
        builder.append("<span style='FONT-FAMILY:΢���ź�;font-size:11pt'><b>���̺ϸ�֤</b></span><br/><small>");

        if (canShowData()) {
            builder.append(getIcon("edit_16.png", 16, 16));
            builder.append("<a href=\"" + _OPENEDITOR + "@" + getName()
                    + "\" target=\"_rwt\">�鿴</a>   ");
        }
        // if(canPrintData()){
        // builder.append(getIcon("print_16.png",16,16));
        // builder.append("<a href=\"" + _PRINT + "\" target=\"_rwt\">��ӡ</a>   ");
        // }
        // if(canUploadData()){
        // builder.append(getIcon("upload_16.png",16,16));
        // builder.append("<a href=\"" + _UPLOAD + "\" target=\"_rwt\">�ϴ�</a>   ");
        // }
        builder.append("</small>");

        return builder.toString();

    }

    // private boolean canUploadData() {
    // return true;
    // }

    @Override
    public DataObjectEditorInput getInput() {
        return input;
    }

    public void setInputData(DataObjectEditorInput input) {
        this.input = input;
    }

    @Override
    public ModuleAction[] getActions() {
        DPCertPrintAction action1 = new DPCertPrintAction();
        DPCertUploadAction action2 = new DPCertUploadAction();

        return new ModuleAction[] { action1, action2 };
    }

    @Override
    public void fireEvent(String eventCode, PrintContent pc) {
        if (_OPENEDITOR.equals(eventCode)) {
            pc.showData(this);
        }
    }

    @Override
    public String getName() {
        return NAME;
    }


}