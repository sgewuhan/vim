package com.sg.vim.datamodel.labelprovider;

import org.eclipse.jface.viewers.ColumnLabelProvider;

import com.mongodb.DBObject;
import com.sg.vim.datamodel.IVIMFields;

public class ApproachAndDepartureLabelProvider extends ColumnLabelProvider {

	public ApproachAndDepartureLabelProvider() {
	}
	@Override
	public String getText(Object element) {
		if(element instanceof DBObject){
			Object a = ((DBObject) element).get(IVIMFields.F_C2);
			Object d = ((DBObject) element).get(IVIMFields.F_C3);
			return ""+a+"/"+d;
		}
		return super.getText(element);
	}
}
