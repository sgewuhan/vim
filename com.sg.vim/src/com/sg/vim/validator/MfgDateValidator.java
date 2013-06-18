package com.sg.vim.validator;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.sg.ui.model.DataObject;
import com.sg.ui.part.editor.field.value.AbstractValidator;

public class MfgDateValidator extends AbstractValidator {


    private static final String ERR_MSG1 = "�����������벻�Ϸ���Ҫ��yyyy-MM-dd,���磺2012-03-12";
    private static final String ERR_MSG2 = "�������ڲ������ڵ�ǰ����";

    @Override
    protected String getValidMessage(DataObject data) {
        Object valueForUpdate = getValueForUpdate();
        if(!(valueForUpdate instanceof String)){
            return ERR_MSG1;
        }
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date inputDate = null;
        try{
            inputDate = sdf.parse((String)valueForUpdate);
        }catch(Exception e){
            return ERR_MSG1;
        }
        
        Date today = new Date();
        if(inputDate.after(today)){
           return ERR_MSG2; 
        }
        
        
        return null;
    }

}
