package com.sg.vim.labelprovider;

import org.eclipse.jface.viewers.ColumnLabelProvider;

import com.mongodb.DBObject;
import com.sg.vim.model.IVIMFields;

public class OverHangLabelProvider extends ColumnLabelProvider {

	public OverHangLabelProvider() {
	}
	@Override
	public String getText(Object element) {
		if(element instanceof DBObject){
			Object foh = ((DBObject) element).get(IVIMFields.F_C1);
			Object roh = ((DBObject) element).get(IVIMFields.F_11);
			return ""+foh+"/"+roh;
		}
		return super.getText(element);
	}
}
