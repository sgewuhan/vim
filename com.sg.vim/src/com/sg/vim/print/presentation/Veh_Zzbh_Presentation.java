package com.sg.vim.print.presentation;

import com.sg.ui.part.editor.field.AbstractFieldPart;
import com.sg.ui.part.editor.field.value.IValuePresentation;

public class Veh_Zzbh_Presentation implements IValuePresentation {

    public Veh_Zzbh_Presentation() {
    }

    @Override
    public String getPresentValue(AbstractFieldPart part) {
        Object value = part.getValue();
        if(value instanceof String){
            try{
                int i = Integer.parseInt(value.toString());
                return String.format("%07d",i);
            }catch(Exception e){}
        }
        if(value !=null){
            return value.toString();
        }
        return "";
    }

}
