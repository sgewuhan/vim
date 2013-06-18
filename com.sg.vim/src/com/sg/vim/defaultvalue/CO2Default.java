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
        value.add(new BasicDBObject().append("location", "市区").append("co2","").append("fuelqty", ""));
        value.add(new BasicDBObject().append("location", "市郊").append("co2","").append("fuelqty", ""));
        value.add(new BasicDBObject().append("location", "综合").append("co2","").append("fuelqty", ""));
        
        return value;
    }

}
