package com.sg.vim.labelprovider;

import org.eclipse.jface.viewers.ColumnLabelProvider;

import com.mongodb.DBObject;
import com.sg.vim.model.IVIMFields;

public class VehicleModelNameLabelProvider extends ColumnLabelProvider {

	public VehicleModelNameLabelProvider() {
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ColumnLabelProvider#getText(java.lang.Object)
	 */
	@Override
	public String getText(Object element) {
		String model = (String)((DBObject)element).get(IVIMFields.F_0_2C1);
		String name =  (String)((DBObject)element).get(IVIMFields.F_0_2_1);
		return model+" / "+name;
	}

}
