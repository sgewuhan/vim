package com.sg.vim.option;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.sg.ui.model.text.Enumerate;
import com.sg.ui.part.editor.field.value.IFieldOptionProvider;
import com.sg.vim.model.IVIMFields;
import com.sg.vim.model.ProductCodeInfo;

public class ProductCodeOption implements IFieldOptionProvider {

    private ProductCodeInfo service;

    public ProductCodeOption() {
        service = new ProductCodeInfo();
    }

    @Override
    public Enumerate getOption(Object input, Object data, String key, Object value) {
        // ²éÑ¯³ÉÆ·Âë
        try {
            DBCursor cur = service.find(new BasicDBObject(),
                    new BasicDBObject().append(IVIMFields.E_02, 1).append(IVIMFields.E_03, 1).append(IVIMFields.F_0_2C1, 1));
            Enumerate[] children = new Enumerate[cur.size()];
            int i=0;
            while(cur.hasNext()){
                DBObject dbo = cur.next();
                String v = (String) dbo.get(IVIMFields.E_02);
                String label = ""+dbo.get(IVIMFields.E_03)+"["+dbo.get(IVIMFields.F_0_2C1)+"]";
                children[i++] = new Enumerate(v,label,v,null);
            }
            
            Enumerate root = new Enumerate("root","", "", children);
            return root;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return new Enumerate("root");
    }

}
