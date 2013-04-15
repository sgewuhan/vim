package com.sg.vim.datamodel.labelprovider;

import org.eclipse.jface.viewers.ColumnLabelProvider;

import com.mongodb.DBObject;
import com.sg.vim.datamodel.BasicInfo;

public class OverallSizeLabelProvider extends ColumnLabelProvider {

	public OverallSizeLabelProvider() {
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ColumnLabelProvider#getText(java.lang.Object)
	 */
	@Override
	public String getText(Object element) {
		if(element instanceof DBObject){
			Object length = ((DBObject) element).get(BasicInfo.F_6_1);
			Object width = ((DBObject) element).get(BasicInfo.F_8);
			Object height = ((DBObject) element).get(BasicInfo.F_7_1);
			return ""+length+"��"+width+"��"+height;
		}
		return super.getText(element);
	}

}
