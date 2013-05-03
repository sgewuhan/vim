package com.sg.vim.print.module;

import java.util.Map;

import com.mongodb.DBObject;
import com.sg.sqldb.utility.SQLRow;
import com.sg.ui.model.DataObjectEditorInput;
import com.sg.vim.print.module.action.ModuleAction;

public abstract class PrintModule {
    public static final String PARA_VIN = "vin";
    public static final String PARA_MES_RAW_DATA = "mesRawData";
    public static final String PARA_PRODUCT_CODE_DATA = "productCodeData";
    public static final String PARA_DPCONF_DATA = "dpconfData";
    public static final String PARA_DPCOC_DATA = "dpcocData";
    public static final String PARA_CONF_DATA = "confData";
    public static final String PARA_COC_DATA = "cocData";
    protected String vin;
    protected SQLRow mesRawData;
    protected DBObject productCodeData;
    protected DBObject dpconfData;
    protected DBObject dpcocData;
    protected DBObject confData;
    protected DBObject cocData;
    

    public abstract PrintModule[] getSubModules();

    public abstract PrintModule getParentModules();

    public abstract boolean hasSubModules();

    public abstract String getText();
    
    public  boolean isEnable(){
        return getInput()!=null;
    }

    public void setInput(Map<String, Object> para) throws Exception{
        this.vin = (String) para.get(PARA_VIN);
        this.mesRawData = (SQLRow) para.get(PARA_MES_RAW_DATA);
        this.productCodeData = (DBObject) para.get(PARA_PRODUCT_CODE_DATA);
        this.dpconfData = (DBObject) para.get(PARA_DPCONF_DATA);
        this.dpcocData = (DBObject) para.get(PARA_DPCOC_DATA);
        this.confData = (DBObject) para.get(PARA_CONF_DATA);
        this.cocData = (DBObject) para.get(PARA_COC_DATA);
    }

    public abstract DataObjectEditorInput getInput();

    public abstract ModuleAction[] getActions() ;
    
}
