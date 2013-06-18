package com.sg.vim.model;

import com.mobnut.db.collection.AuthCollectionService;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.sg.ui.model.input.BusinessObjectListInput;

public class COCInfoInputDP extends BusinessObjectListInput {

	@Override
	protected AuthCollectionService getService() {
		return new COCInfo();
	}


    @Override
    public DBObject getQuery() {
        return new BasicDBObject().append(IVIMFields.C_23, "DP");
    }

}
