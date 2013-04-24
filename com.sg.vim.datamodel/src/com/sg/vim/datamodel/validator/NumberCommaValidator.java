package com.sg.vim.datamodel.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.forms.IMessageManager;

import com.sg.ui.model.DataObject;
import com.sg.ui.part.editor.field.value.IFieldInputValidator;
import com.sg.ui.registry.config.FieldConfigurator;

public class NumberCommaValidator implements IFieldInputValidator {

  /**
   * 检查是否符合逗号分割数字的校验器
   */
  public NumberCommaValidator() {
  }

  @Override
  public boolean validate(DataObject data, FieldConfigurator fieldConfigurator,
      Object valueForUpdate, IMessageManager messageManager, Control control) {
    if (valueForUpdate instanceof String) {
      Pattern p = Pattern.compile("(\\d+)(,\\d+)*");
      Matcher m = p.matcher((String) valueForUpdate);
      boolean matched = m.matches();
      if (!matched) {
        messageManager.addMessage(fieldConfigurator.getId(), "必须是逗号分割的数字", null,
            IMessageProvider.ERROR, control);
        return false;
      }
    }
    messageManager.removeMessage(fieldConfigurator.getId(), control);
    return true;
  }
}
