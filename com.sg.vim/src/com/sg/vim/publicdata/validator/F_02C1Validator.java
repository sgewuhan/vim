package com.sg.vim.publicdata.validator;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.sg.ui.model.DataObject;
import com.sg.ui.part.editor.field.value.AbstractValidator;
import com.sg.ui.registry.config.FieldConfigurator;
import com.sg.vim.datamodel.IVIMFields;

public class F_02C1Validator extends AbstractValidator {

    @Override
    protected String getValidMessage(DataObject data) {
        try {
            FieldConfigurator f = getFieldConfigurator();
            String name = f.getName();
            Object val = getValueForUpdate();
            BasicDBObject query = new BasicDBObject().append(name, val);
            if (!data.isNewObject()) {
                ObjectId id = data.getSystemId();
                query.append("_id", new BasicDBObject().append("$ne", id))
                .append(IVIMFields.C_23, data.getValue(IVIMFields.C_23));
            }

            long count = data.getService().count(query);
            if (count != 0) {
                return "输入值不同通过唯一性检查";
            }
        } catch (Exception e) {
            return e.getMessage();
        }
        return null;
    }

}
