package com.sg.vim.print.module;

import java.util.HashMap;
import java.util.Map;

import com.mobnut.commons.util.file.FileUtil;
import com.mongodb.DBObject;
import com.sg.sqldb.utility.SQLRow;
import com.sg.ui.model.DataObjectEditorInput;
import com.sg.vim.print.PrintActivator;
import com.sg.vim.print.control.PrintContent;
import com.sg.vim.print.module.action.ModuleAction;

public abstract class PrintModule {
    public static final String PARA_VIN = "vin";
    public static final String PARA_MES_RAW_DATA = "mesRawData";
    public static final String PARA_PRODUCT_CODE_DATA = "productCodeData";
    public static final String PARA_DPCONF_DATA = "dpconfData";
    public static final String PARA_DPCOC_DATA = "dpcocData";
    public static final String PARA_CONF_DATA = "confData";
    public static final String PARA_COC_DATA = "cocData";

    protected static final String _OPENEDITOR = "_OPENEDITOR";
    protected static final String _PRINT = "_PRINT";
    protected static final String _UPLOAD = "_UPLOAD";

    protected String vin;
    protected SQLRow mesRawData;
    protected DBObject productCodeData;
    protected DBObject dpconfData;
    protected DBObject dpcocData;
    protected DBObject confData;
    protected DBObject cocData;
    private HashMap<String, Object> callbackProperties;
	private String error;
    
    public void setCallbackProperties(String key, Object value) {
        if(callbackProperties==null){
            callbackProperties = new HashMap<String,Object>();
        }
        callbackProperties.put(key, value);
    }
    public Object getCallbackProperties(String key){
        if(callbackProperties!=null){
            return callbackProperties.get(key);
        } 
        return null;
    }

    public abstract PrintModule[] getSubModules();

    public abstract PrintModule getParentModules();

    public abstract boolean hasSubModules();

    public abstract String getText();

    public boolean isEnable() {
        return getInput() != null;
    }

    public void setInput(Map<String, Object> para) throws Exception {
        this.vin = (String) para.get(PARA_VIN);
        this.mesRawData = (SQLRow) para.get(PARA_MES_RAW_DATA);
        this.productCodeData = (DBObject) para.get(PARA_PRODUCT_CODE_DATA);
        this.dpconfData = (DBObject) para.get(PARA_DPCONF_DATA);
        this.dpcocData = (DBObject) para.get(PARA_DPCOC_DATA);
        this.confData = (DBObject) para.get(PARA_CONF_DATA);
        this.cocData = (DBObject) para.get(PARA_COC_DATA);
    }

    public abstract DataObjectEditorInput getInput();

    public abstract ModuleAction[] getActions();

    public String getHeadText() {
        return getText();
    }

    public boolean canShowData() {
        return getInput() != null;
    }

    public boolean canPrintData(){
        return getInput() != null;
    }

    public abstract void fireEvent(String eventCode, PrintContent printContent);

    protected String getIcon(String filename, int width, int height) {
        StringBuffer builder = new StringBuffer();
        builder.append("<img src=\"");
        builder.append(FileUtil.getImageURL(filename, PrintActivator.PLUGIN_ID, "image"));
        builder.append("\"  width='"+width+"' height='"+height+"' />");
        return builder.toString();
    }

    public abstract String getName();
	public void setError(String error){
		this.error = error;
	}
	public String getError(){
		return error;
	}
    
}
