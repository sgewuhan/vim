package com.sg.vim.model;

import com.mobnut.db.DBActivator;
import com.mobnut.db.collection.AuthCollectionService;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.sg.ui.model.input.BusinessObjectListInput;

public class NameplateInput extends BusinessObjectListInput {

    @Override
    protected AuthCollectionService getService() {
        return new ProductCodeInfo();
    }

    @Override
    protected DBObject getRow(DBObject dataItem) {
        // 根据成品码读取绑定的COC
        Object cocId = dataItem.get(IVIMFields.COC_ID);
        DBCollection c = DBActivator.getCollection(IVIMFields.DB_NAME,
                IVIMFields.COL_COCINFO);

        DBObject cocData = c.findOne(new BasicDBObject().append("_id", cocId));
        if (cocData != null) {
            dataItem.put(IVIMFields.F_C4, cocData.get(IVIMFields.F_C4));
            dataItem.put(IVIMFields.F_24, cocData.get(IVIMFields.F_24));
            dataItem.put(IVIMFields.F_26, cocData.get(IVIMFields.F_26));
            dataItem.put(IVIMFields.C_01, cocData.get(IVIMFields.C_01));
            dataItem.put(IVIMFields.C_04, cocData.get(IVIMFields.C_04));
            dataItem.put(IVIMFields.F_42_1, cocData.get(IVIMFields.F_42_1));
            dataItem.put(IVIMFields.C_02, cocData.get(IVIMFields.C_02));
            dataItem.put(IVIMFields.F_C0_2, cocData.get(IVIMFields.F_C0_2));
            dataItem.put(IVIMFields.C_08, cocData.get(IVIMFields.C_08));
        }

        return dataItem;
    }

    @Override
    public DBObject getQuery() {
        return new BasicDBObject().append(IVIMFields.COC_ID,
                new BasicDBObject().append("$ne", null));
    }

}
