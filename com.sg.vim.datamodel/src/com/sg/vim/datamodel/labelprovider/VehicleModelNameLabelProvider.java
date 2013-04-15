package com.sg.vim.datamodel.labelprovider;

import org.eclipse.jface.viewers.ColumnLabelProvider;

import com.mongodb.DBObject;
import com.sg.vim.datamodel.BasicInfo;

public class VehicleModelNameLabelProvider extends ColumnLabelProvider {

	public VehicleModelNameLabelProvider() {
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ColumnLabelProvider#getText(java.lang.Object)
	 */
	@Override
	public String getText(Object element) {
		String model = (String)((DBObject)element).get(BasicInfo.F_0_2C);
		String name =  (String)((DBObject)element).get(BasicInfo.F_0_2_1);
		return model+" / "+name;
	}

}
