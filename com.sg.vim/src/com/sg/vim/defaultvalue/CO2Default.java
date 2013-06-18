package com.sg.vim.defaultvalue;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.sg.ui.part.editor.field.value.IFieldDefaultValue;

public class CO2Default implements IFieldDefaultValue {

    public CO2Default() {
    }

    @Override
    public Object getDefaultValue(Object data, String key) {
        BasicDBList value = new BasicDBList();
        value.add(new BasicDBObject().append("location", "����").append("co2","").append("fuelqty", ""));
        value.add(new BasicDBObject().append("location", "�н�").append("co2","").append("fuelqty", ""));
        value.add(new BasicDBObject().append("location", "�ۺ�").append("co2","").append("fuelqty", ""));
        
        return value;
    }

}
