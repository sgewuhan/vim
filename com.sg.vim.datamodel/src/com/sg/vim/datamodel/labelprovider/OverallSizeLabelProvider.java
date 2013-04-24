package com.sg.vim.datamodel.labelprovider;

import org.eclipse.jface.viewers.ColumnLabelProvider;

import com.mongodb.DBObject;
import com.sg.vim.datamodel.IVIMFields;

public class OverallSizeLabelProvider extends ColumnLabelProvider {

	public OverallSizeLabelProvider() {
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ColumnLabelProvider#getText(java.lang.Object)
	 */
	@Override
	public String getText(Object element) {
		if(element instanceof DBObject){
			Object length = ((DBObject) element).get(IVIMFields.F_6_1);
			Object width = ((DBObject) element).get(IVIMFields.F_8);
			Object height = ((DBObject) element).get(IVIMFields.F_7_1);
			return ""+length+"¡Á"+width+"¡Á"+height;
		}
		return super.getText(element);
	}

}
