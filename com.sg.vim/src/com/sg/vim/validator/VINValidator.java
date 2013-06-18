package com.sg.vim.validator;

import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.forms.IMessageManager;

import com.sg.ui.model.DataObject;
import com.sg.ui.part.editor.field.value.IFieldInputValidator;
import com.sg.ui.registry.config.FieldConfigurator;
import com.sg.vim.VimUtils;

public class VINValidator implements IFieldInputValidator {

    public VINValidator() {
    }

    @Override
    public boolean validate(DataObject data, FieldConfigurator fieldConfigurator,
            Object valueForUpdate, IMessageManager messageManager, Control control) {
        if (valueForUpdate instanceof String) {
            boolean matched = VimUtils.checkVIN((String) valueForUpdate);
            if (!matched) {
                messageManager.addMessage(fieldConfigurator.getId(), "VIN输入不合法", null,
                        IMessageProvider.ERROR, control);
                return false;
            }
        }
        messageManager.removeMessage(fieldConfigurator.getId(), control);
        return true;
    }

}
