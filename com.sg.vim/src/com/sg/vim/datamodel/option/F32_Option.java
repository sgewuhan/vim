package com.sg.vim.datamodel.option;

import com.sg.ui.model.DataObject;
import com.sg.ui.model.text.Enumerate;
import com.sg.ui.part.editor.field.value.IFieldOptionProvider;
import com.sg.vim.datamodel.IVIMFields;

public class F32_Option implements IFieldOptionProvider {

  public F32_Option() {
  }

  @Override
  public Enumerate getOption(Object input, Object data, String key, Object value) {
    Object srcValue = ((DataObject)data).getValue(IVIMFields.F_32S);
    if((srcValue instanceof String)){
      String[] values = ((String)srcValue).split(",");
      Enumerate[] children = new Enumerate[values.length];
      Enumerate root = new Enumerate("root","", "", children);
      for (int i = 0; i < children.length; i++) {
        children[i] = new Enumerate(values[i], values[i]);
      }
      return root;
    }
    return new Enumerate("root");
  }


}
