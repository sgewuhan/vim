package com.sg.vim.cocinfo.table;

import org.bson.types.ObjectId;
import org.eclipse.rap.rwt.RWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;

import com.sg.ui.UIUtils;

public class ProductCodeTableSelectionListener implements SelectionListener {

    public ProductCodeTableSelectionListener() {
    }

    @Override
    public void widgetSelected(SelectionEvent e) {
        if (e.detail == RWT.HYPERLINK) {
            String[] ids = e.text.split("@");
            try {
                ObjectId cocinfoId = new ObjectId(ids[1]);
                UIUtils.open(cocinfoId, ids[0], false, false, null);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    @Override
    public void widgetDefaultSelected(SelectionEvent e) {

    }

}
