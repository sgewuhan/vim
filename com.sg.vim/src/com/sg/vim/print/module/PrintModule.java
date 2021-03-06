package com.sg.vim.print.module;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import com.mobnut.commons.util.file.FileUtil;
import com.mongodb.DBObject;
import com.sg.sqldb.utility.SQLRow;
import com.sg.ui.model.DataObjectEditorInput;
import com.sg.vim.Vim;
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
    protected static final String _OPENCOC = "_OPEN_COC";
    protected static final String _PRINT = "_PRINT";
    protected static final String _UPLOAD = "_UPLOAD";

    public String vin;
    public SQLRow mesRawData;
    public DBObject productCodeData;
    public DBObject dpconfData;
    public DBObject dpcocData;
    public DBObject confData;
    public DBObject cocData;
    private HashMap<String, Object> callbackProperties;
    private String error;

    protected Integer paperNumber;
    private boolean hasPrint = false;
    public String lifecycle;

    public void setCallbackProperties(String key, Object value) {
        if (callbackProperties == null) {
            callbackProperties = new HashMap<String, Object>();
        }
        callbackProperties.put(key, value);
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
        paperNumber = null;
        hasPrint = false;
    }

    public abstract DataObjectEditorInput getInput();

    public abstract ModuleAction[] getActions();

    public String getHeadText() {
        return getText();
    }

    public boolean canShowData() {
        return getInput() != null;
    }

    public boolean canPrintData() {
        return getInput() != null;
    }

    public void setLifecycle(String lifecycle) {
        this.lifecycle = lifecycle;
        PrintModule[] sm = getSubModules();
        if (sm != null) {
            for (int i = 0; i < sm.length; i++) {
                sm[i].setLifecycle(lifecycle);
            }
        }
    }

    public abstract void fireEvent(String eventCode, String[] args, PrintContent printContent);

    protected String getIcon(String filename, int width, int height) {
        StringBuffer builder = new StringBuffer();
        builder.append("<img src=\"");
        builder.append(FileUtil.getImageURL(filename, Vim.PLUGIN_ID, "image"));
        builder.append("\"  width='" + width + "' height='" + height + "' />");
        return builder.toString();
    }

    public abstract String getName();

    public void setError(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public String getInputPaperNumber() {
        return "";
    }

    public String getDisplayedPaperNumber() {
        return "";
    }

    public void setInputPaperNumber(int i) {
        paperNumber = i;

    }

    public Integer getPaperNumber() {
        return paperNumber;
    }

    public boolean canInputPaperNumber() {
        return false;
    }

    public boolean isHasPrint() {
        return hasPrint;
    }

    public void setHasPrint(boolean hasPrint) {
        this.hasPrint = hasPrint;
    }

    public void save() {
        DataObjectEditorInput input = getInput();
        if (input != null) {
            input.save();
        }
    }

    public static CellLabelProvider getNameLabelProvider() {
        return new ColumnLabelProvider() {
            @Override
            public String getText(Object element) {
                return ((PrintModule) element).getText();
            }
        };
    }

    public static CellLabelProvider getPaperNumberLabelProvider() {
        return new ColumnLabelProvider() {
            @Override
            public String getText(Object element) {
                return ((PrintModule) element).getDisplayedPaperNumber();
            }
        };
    }

    public static IContentProvider getContentProvider() {
        return new ITreeContentProvider() {

            @Override
            public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
            }

            @Override
            public void dispose() {
            }

            @Override
            public boolean hasChildren(Object element) {
                return ((PrintModule) element).hasSubModules();
            }

            @Override
            public Object getParent(Object element) {
                return ((PrintModule) element).getParentModules();
            }

            @Override
            public Object[] getElements(Object inputElement) {
                return (Object[]) inputElement;
            }

            @Override
            public Object[] getChildren(Object parentElement) {
                return ((PrintModule) parentElement).getSubModules();
            }
        };
    }

    public void doPrint(PrintContent pc) {

    }

    public DBObject getData() {
        return getInput().getData().getData();
    }

    public void setValue(String key, Object value) {
    	if(getInput()!=null){
    		getInput().getData().setValue(key, value);
    	}
    }
}
