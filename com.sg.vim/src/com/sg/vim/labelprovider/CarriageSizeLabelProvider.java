package com.sg.vim.labelprovider;

import org.eclipse.jface.viewers.ColumnLabelProvider;

import com.mongodb.DBObject;
import com.sg.vim.model.IVIMFields;

public class CarriageSizeLabelProvider extends ColumnLabelProvider {

	public CarriageSizeLabelProvider() {
	}
	@Override
	public String getText(Object element) {
		if(element instanceof DBObject){
			Object length = ((DBObject) element).get(IVIMFields.F_C7_1);
			Object width = ((DBObject) element).get(IVIMFields.F_C7_2);
			Object height = ((DBObject) element).get(IVIMFields.F_C7_3);
			return ""+length+"¡Á"+width+"¡Á"+height;
		}
		return super.getText(element);
	}
}
