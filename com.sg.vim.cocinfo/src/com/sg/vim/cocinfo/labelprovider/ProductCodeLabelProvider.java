package com.sg.vim.cocinfo.labelprovider;

import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.swt.graphics.Image;

import com.mongodb.DBObject;
import com.sg.vim.cocinfo.Activator;

public class ProductCodeLabelProvider extends ColumnLabelProvider {

	public ProductCodeLabelProvider() {
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ColumnLabelProvider#getImage(java.lang.Object)
	 * 
	 */
	@Override
	public Image getImage(Object element) {
		if(element instanceof DBObject){
			if(((DBObject) element).get("cocinfo")!=null){
				return Activator.getImage("link_16.png");
			}
		}
		
		return super.getImage(element);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ColumnLabelProvider#getText(java.lang.Object)
	 */
	@Override
	public String getText(Object element) {
		if(element instanceof DBObject){
			return (String) ((DBObject) element).get("e_01");
		}
		return "";
	}

}
