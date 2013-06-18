package com.sg.vim.labelprovider;

import org.eclipse.jface.viewers.ColumnLabelProvider;

import com.mongodb.DBObject;
import com.sg.vim.model.IVIMFields;

public class C23Labelprovider extends ColumnLabelProvider {

    public C23Labelprovider() {
    }
    @Override
    public String getText(Object element) {
        if(element instanceof DBObject){
            Object a = ((DBObject) element).get(IVIMFields.C_23);
            if("QX".equals(a)){
                return "Õû³µ";
            }
            if("DP".equals(a)){
                return "µ×ÅÌ";
            }
            
        }
        return "";
    }
}
