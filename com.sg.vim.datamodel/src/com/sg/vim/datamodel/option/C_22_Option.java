package com.sg.vim.datamodel.option;

import com.sg.ui.UI;
import com.sg.ui.model.DataObject;
import com.sg.ui.model.text.Enumerate;
import com.sg.ui.part.editor.field.value.IFieldOptionProvider;
import com.sg.vim.datamodel.IVIMFields;

public class C_22_Option implements IFieldOptionProvider {

    public C_22_Option() {
    }

    @Override
    public Enumerate getOption(Object input, Object data, String key, Object value) {
        Object srcValue = ((DataObject) data).getValue(IVIMFields.C_23);
        if ((srcValue instanceof String)) {
            if(srcValue.equals("DP")){
                return (Enumerate) UI.getEnumRegistry().getConfigurator("com.sg.vim.enum.type2.1");
            }else if(srcValue.equals("QX")){
                return (Enumerate) UI.getEnumRegistry().getConfigurator("com.sg.vim.enum.type2");
            }
        }
        return new Enumerate("root");
    }

}
