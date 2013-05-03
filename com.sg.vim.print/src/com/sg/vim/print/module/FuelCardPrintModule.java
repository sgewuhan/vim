package com.sg.vim.print.module;

import com.mobnut.commons.util.file.FileUtil;
import com.sg.ui.model.DataObjectEditorInput;
import com.sg.vim.print.PrintActivator;
import com.sg.vim.print.module.action.FualCardPrintAction;
import com.sg.vim.print.module.action.ModuleAction;

public class FuelCardPrintModule extends PrintModule {

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
            builder.append(FileUtil.getImageURL("fualcard_48.png", PrintActivator.PLUGIN_ID, "image"));
        }else{
            builder.append(FileUtil.getImageURL("fualcard_d_48.png", PrintActivator.PLUGIN_ID, "image"));
        }
        builder.append("\"  width='48' height='48' style='float:left;padding:5px'/>");
        builder.append("<br/><span style='FONT-FAMILY:微软雅黑;font-size:11pt'>汽车燃油消耗量标识</span>");
        return builder.toString();
    }

    @Override
    public DataObjectEditorInput getInput() {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public ModuleAction[] getActions() {
        FualCardPrintAction action1 = new FualCardPrintAction();
        
        return new ModuleAction[]{action1};
    }

}
