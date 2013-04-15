package com.sg.vim.datamodel.labelprovider;

import org.eclipse.jface.viewers.ColumnLabelProvider;

import com.mongodb.DBObject;
import com.sg.vim.datamodel.BasicInfo;

public class OverHangLabelProvider extends ColumnLabelProvider {

	public OverHangLabelProvider() {
	}
	@Override
	public String getText(Object element) {
		if(element instanceof DBObject){
			Object foh = ((DBObject) element).get(BasicInfo.F_C1);
			Object roh = ((DBObject) element).get(BasicInfo.F_11);
			return ""+foh+"/"+roh;
		}
		return super.getText(element);
	}
}
