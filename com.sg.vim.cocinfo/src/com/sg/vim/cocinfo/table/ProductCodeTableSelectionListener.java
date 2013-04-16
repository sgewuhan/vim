package com.sg.vim.cocinfo.table;

import org.bson.types.ObjectId;
import org.eclipse.rap.rwt.RWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;

import com.sg.ui.UIUtils;
import com.sg.util.Utils;

public class ProductCodeTableSelectionListener implements SelectionListener {

  public ProductCodeTableSelectionListener() {
  }

  @Override
  public void widgetSelected(SelectionEvent e) {
    if (e.detail == RWT.HYPERLINK) {
      String id = e.text;
      if (!Utils.isNullOrEmpty(id)) {
        try {
          ObjectId cocinfoId = new ObjectId(id);
          UIUtils.open(cocinfoId, "com.sg.vim.editor.cocinfo", false, false, null);
        } catch (Exception e1) {
          e1.printStackTrace();
        }
      }
    }
  }

  @Override
  public void widgetDefaultSelected(SelectionEvent e) {

  }

}
