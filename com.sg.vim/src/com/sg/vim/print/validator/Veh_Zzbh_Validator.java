package com.sg.vim.print.validator;

import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.forms.IMessageManager;

import com.mobnut.commons.util.Utils;
import com.sg.ui.model.DataObject;
import com.sg.ui.part.editor.field.value.IFieldInputValidator;
import com.sg.ui.registry.config.FieldConfigurator;

public class Veh_Zzbh_Validator implements IFieldInputValidator {

    public Veh_Zzbh_Validator() {
    }

    @Override
    public boolean validate(DataObject data, FieldConfigurator fieldConfigurator,
            Object valueForUpdate, IMessageManager messageManager, Control control) {
        // 7位，数字类型
        if (valueForUpdate instanceof String) {
            boolean matched = Utils.isPatternMatched(valueForUpdate, "(\\d+)");
            if (!matched) {
                messageManager.addMessage(fieldConfigurator.getId(), "必须长度7位数字", null,
                        IMessageProvider.ERROR, control);
                return false;
            }
        }
        messageManager.removeMessage(fieldConfigurator.getId(), control);
        return true;
    }
}
