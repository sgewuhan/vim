package com.sg.vim.validator;

import com.sg.ui.model.DataObject;
import com.sg.ui.part.editor.field.value.AbstractValidator;

public class EngineNumberValidator extends AbstractValidator {

    private static final String ERR_MSG = "发动机号输入不合法，15位大写英文字母或数字";

    @Override
    protected String getValidMessage(DataObject data) {
        Object valueForUpdate = getValueForUpdate();
        if ((valueForUpdate instanceof String)
                && ((String) valueForUpdate).matches("^[A-Z0-9]{15}")) {
            return null;
        }
        return ERR_MSG;
    }

}
