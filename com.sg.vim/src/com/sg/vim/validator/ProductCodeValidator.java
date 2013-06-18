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
                return "VIM���ݿ����޷����������ĳ�Ʒ�룬����ó�Ʒ�������뵼���Ʒ���ݺ�����";
            }
        } catch (Exception e) {
            return e.getMessage();
        }

        return null;
    }

}
