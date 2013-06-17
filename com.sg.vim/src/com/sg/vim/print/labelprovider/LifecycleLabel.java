package com.sg.vim.print.labelprovider;

import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.swt.graphics.Image;

import com.mongodb.DBObject;
import com.sg.ui.ImageResource;
import com.sg.ui.UI;
import com.sg.vim.datamodel.IVIMFields;

public class LifecycleLabel extends ColumnLabelProvider {

    public LifecycleLabel() {
    }

    @Override
    public Image getImage(Object element) {
        if(Boolean.TRUE.equals( ((DBObject)element).get(IVIMFields.IS_DIRTY))){
            return UI.getImage(ImageResource.WARNING_16);
        }else{
            return null;
        }
    }

    @Override
    public String getText(Object element) {
        return (String) ((DBObject)element).get(IVIMFields.LIFECYCLE);
    }

}
