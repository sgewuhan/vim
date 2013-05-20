package com.sg.vim.print.module;

import java.util.Map;

import com.mobnut.commons.util.file.FileUtil;
import com.mongodb.DBObject;
import com.sg.ui.model.DataObjectEditorInput;
import com.sg.vim.datamodel.IVIMFields;
import com.sg.vim.datamodel.util.VimUtils;
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
        builder.append("<span style='FONT-FAMILY:微软雅黑;font-size:11pt'><b>车辆一致性证书</b></span><br/><small>");
        if (canPrintData()) {
            builder.append(getIcon("print_16.png", 16, 16));
            builder.append("<a href=\"" + _PRINT + "@" + getName() + "\" target=\"_rwt\">打印</a>   ");
        }
        
        if (lifecycle != null) {
            builder.append("<b>状态: " + lifecycle + "</b>");
        }

        builder.append("</small>");

        return builder.toString();
    }

    private DataObjectEditorInput input;


    @Override
    public DataObjectEditorInput getInput() {
        return input;
    }

    @Override
    public ModuleAction[] getActions() {
        COCPrintAction action1 = new COCPrintAction();

        return new ModuleAction[] { action1 };
    }

    @Override
    public String getHeadText() {
        return "<span style='FONT-FAMILY:微软雅黑;font-size:15pt'>车辆一致性证书</span>";
    }

    @Override
    public void fireEvent(String eventCode, String[] arg, PrintContent pc) {
        if (_OPENEDITOR.equals(eventCode)) {
            pc.showData(this);
        } else if(_PRINT.equals(eventCode)){
            pc.doPrint(this);
        }
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public boolean canPrintData() {
        return (!isHasPrint()) && (getInput() != null)
                && ((lifecycle == null) || (lifecycle.equals(IVIMFields.LC_ABANDON)&&VimUtils.FL_REPRINT));
    }

    @Override
    public void setInput(Map<String, Object> para) throws Exception {
        super.setInput(para);
        
        input = VimUtils.getCOCInput(cocData, confData, productCodeData, mesRawData,
                null, vin);
        
        DBObject dataItem = VimUtils.getCOCPaperDataByVin(vin);
        lifecycle =null;
        if (dataItem != null) {
            lifecycle = (String) dataItem.get(IVIMFields.LIFECYCLE);
            setLifecycle(lifecycle);
        }
    }

	@Override
	public void doPrint(PrintContent pc) {
        pc.doPrint(this);
	}
    
    

}
