package com.sg.vim.validator;

import com.sg.ui.model.DataObject;
import com.sg.ui.part.editor.field.value.UniqueValidator;
import com.sg.vim.VimUtils;

public class VINValidator extends UniqueValidator{


    @Override
    protected String getValidMessage(DataObject data) {
        Object valueForUpdate = getValueForUpdate();
        if ((valueForUpdate instanceof String)&&(VimUtils.checkVIN((String) valueForUpdate))) {
            return super.getValidMessage(data);
        }
        return "VIN输入不合法";
    }

}
