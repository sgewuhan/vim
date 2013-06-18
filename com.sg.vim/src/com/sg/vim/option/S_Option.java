package com.sg.vim.option;

import com.sg.ui.model.DataObject;
import com.sg.ui.model.text.Enumerate;
import com.sg.ui.part.editor.field.value.IFieldOptionProvider;

public class S_Option implements IFieldOptionProvider {

    public S_Option() {
    }

    @Override
    public Enumerate getOption(Object input, Object data, String key, Object value) {
      Object srcValue = ((DataObject)data).getValue(key+"s");
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
