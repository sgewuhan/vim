package com.sg.vim.validator;

import com.mongodb.BasicDBObject;
import com.sg.ui.model.DataObject;
import com.sg.ui.part.editor.field.value.AbstractValidator;
import com.sg.vim.model.IVIMFields;
import com.sg.vim.model.ProductCodeInfo;

public class ProductCodeValidator extends AbstractValidator {

    private ProductCodeInfo service;

    public ProductCodeValidator() {
        service = new ProductCodeInfo();
    }

    @Override
    protected String getValidMessage(DataObject data) {
        Object value = getValueForUpdate();
        try {
            long cnt = service.count(new BasicDBObject().append(IVIMFields.E_02, value));
            if(cnt==0){
                return "VIM数据库中无法获得您输入的成品码，如果该成品码无误，请导入成品数据后重试";
            }
        } catch (Exception e) {
            return e.getMessage();
        }

        return null;
    }

}
