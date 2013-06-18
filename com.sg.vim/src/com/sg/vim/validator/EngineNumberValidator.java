package com.sg.vim.validator;

import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.forms.IMessageManager;

import com.sg.ui.model.DataObject;
import com.sg.ui.part.editor.field.value.IFieldInputValidator;
import com.sg.ui.registry.config.FieldConfigurator;

public class EngineNumberValidator implements IFieldInputValidator {

    public EngineNumberValidator() {
    }

    @Override
    public boolean validate(DataObject data, FieldConfigurator fieldConfigurator,
            Object valueForUpdate, IMessageManager messageManager, Control control) {
        if (valueForUpdate instanceof String) {
            boolean matched = ((String) valueForUpdate).matches("^[A-Z0-9]{15}");
            if (!matched) {
                messageManager.addMessage(fieldConfigurator.getId(), "发动机号输入不合法，15位大写英文字母或数字", null,
                        IMessageProvider.ERROR, control);
                return false;
            }
        }
        messageManager.removeMessage(fieldConfigurator.getId(), control);
        return true;
    }

}
