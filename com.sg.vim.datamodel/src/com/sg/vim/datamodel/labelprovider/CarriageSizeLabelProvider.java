package com.sg.vim.datamodel.labelprovider;

import org.eclipse.jface.viewers.ColumnLabelProvider;

import com.mongodb.DBObject;
import com.sg.vim.datamodel.BasicInfo;

public class CarriageSizeLabelProvider extends ColumnLabelProvider {

	public CarriageSizeLabelProvider() {
	}
	@Override
	public String getText(Object element) {
		if(element instanceof DBObject){
			Object length = ((DBObject) element).get(BasicInfo.F_C7_1);
			Object width = ((DBObject) element).get(BasicInfo.F_C7_2);
			Object height = ((DBObject) element).get(BasicInfo.F_C7_3);
			return ""+length+"��"+width+"��"+height;
		}
		return super.getText(element);
	}
}
