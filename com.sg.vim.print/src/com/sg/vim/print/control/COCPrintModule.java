package com.sg.vim.print.control;

import com.mobnut.commons.util.file.FileUtil;
import com.sg.ui.model.DataObjectEditorInput;
import com.sg.vim.print.PrintActivator;

public class COCPrintModule extends PrintModule {

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
        builder.append("<br/><span style='FONT-FAMILY:微软雅黑;font-size:14pt'>车辆一致性证书</span>");
        return builder.toString();
    }

    private boolean enable;

    @Override
    public boolean isEnable() {
        return enable;
    }
    
    public void setEnable(boolean enable){
        this.enable = enable;
    }

    @Override
    public DataObjectEditorInput getInput() {
        // TODO Auto-generated method stub
        return null;
    }

}
