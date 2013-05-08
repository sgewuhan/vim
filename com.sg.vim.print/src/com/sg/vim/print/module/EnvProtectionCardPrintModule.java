package com.sg.vim.print.module;

import com.mobnut.commons.util.file.FileUtil;
import com.sg.ui.model.DataObjectEditorInput;
import com.sg.vim.print.PrintActivator;
import com.sg.vim.print.control.PrintContent;
import com.sg.vim.print.module.action.EnvProtectionPrintAction;
import com.sg.vim.print.module.action.ModuleAction;

public class EnvProtectionCardPrintModule extends PrintModule {

    public static final String NAME = "EnvProtectionCardPrintModule";

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
        if(isEnable()){
            builder.append(FileUtil.getImageURL("doc_48.png", PrintActivator.PLUGIN_ID, "image"));
        }else{
            builder.append(FileUtil.getImageURL("doc_d_48.png", PrintActivator.PLUGIN_ID, "image"));
        }
        builder.append("\"  width='48' height='48' style='float:left;padding:5px'/>");
        builder.append("<span style='FONT-FAMILY:微软雅黑;font-size:11pt'><b>汽车环保标志</b></span><br/><small>");
        if(canPrintData()){
            builder.append(getIcon("print_16.png",16,16));
            builder.append("<a href=\"" + _PRINT +"@"+getName()+ "\" target=\"_rwt\">打印</a>   ");
        }
        builder.append("</small>");

        return builder.toString();
    }

    @Override
    public DataObjectEditorInput getInput() {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public ModuleAction[] getActions() {
        EnvProtectionPrintAction action1 = new EnvProtectionPrintAction();
        
        return new ModuleAction[]{action1};
    }
    
    @Override
    public String getHeadText() {
        return "<span style='FONT-FAMILY:微软雅黑;font-size:15pt'>汽车环保标志</span>";
    }

    @Override
    public void fireEvent(String eventCode,String[] arg,PrintContent pc) {
        if(_OPENEDITOR.equals(eventCode)){
            pc.showData(this);
        }
    }

    @Override
    public String getName() {
        return NAME;
    }

}
