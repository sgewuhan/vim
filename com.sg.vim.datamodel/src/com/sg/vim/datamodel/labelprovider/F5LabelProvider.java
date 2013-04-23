package com.sg.vim.datamodel.labelprovider;

import org.eclipse.jface.viewers.ColumnLabelProvider;

import com.mongodb.DBObject;
import com.sg.vim.datamodel.BasicInfo;

public class F5LabelProvider extends ColumnLabelProvider {

  public F5LabelProvider() {
  }
  @Override
  public String getText(Object element) {
      if(element instanceof DBObject){
          Object a = ((DBObject) element).get(BasicInfo.F_5A);
          Object b = ((DBObject) element).get(BasicInfo.F_5B);
          return a+"/"+b;
      }
      return super.getText(element);
  }
}
