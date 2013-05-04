package com.sg.vim.print.module;

import org.bson.types.ObjectId;

import com.mobnut.commons.util.file.FileUtil;
import com.sg.ui.UIUtils;
import com.sg.ui.model.DataObjectEditorInput;
import com.sg.vim.print.PrintActivator;
import com.sg.vim.print.control.PrintContent;
import com.sg.vim.print.module.action.COCPrintAction;
import com.sg.vim.print.module.action.ModuleAction;

public class COCPrintModule extends PrintModule {

    public static final String NAME = "COCPrintModule";

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
            builder.append(FileUtil.getImageURL("coc_48.png", PrintActivator.PLUGIN_ID, "image"));
        } else {
            builder.append(FileUtil.getImageURL("coc_d_48.png", PrintActivator.PLUGIN_ID, "image"));
        }
        builder.append("\"  width='48' height='48' style='float:left;padding:5px'/>");
        builder.append("<span style='FONT-FAMILY:΢���ź�;font-size:11pt'><b>����һ����֤��</b></span><br/><small>");
        if (canPrintData()) {
            builder.append(getIcon("print_16.png", 16, 16));
            builder.append("<a href=\"" + _PRINT + "@" + getName() + "\" target=\"_rwt\">��ӡ</a>   ");
        }

        builder.append("</small>");

        return builder.toString();
    }

    private boolean enable;

    @Override
    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    @Override
    public DataObjectEditorInput getInput() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ModuleAction[] getActions() {
        COCPrintAction action1 = new COCPrintAction();

        return new ModuleAction[] { action1 };
    }

    @Override
    public String getHeadText() {
        return "<span style='FONT-FAMILY:΢���ź�;font-size:15pt'>����һ����֤��</span>";
    }

    @Override
    public void fireEvent(String eventCode, String[] arg, PrintContent pc) {
        if (_OPENEDITOR.equals(eventCode)) {
            pc.showData(this);
        } 
    }

    @Override
    public String getName() {
        return NAME;
    }

}
