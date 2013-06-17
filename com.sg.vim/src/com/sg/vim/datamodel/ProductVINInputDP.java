package com.sg.vim.datamodel;

import com.mobnut.db.collection.AuthCollectionService;
import com.mongodb.DBObject;
import com.sg.ui.model.input.BusinessObjectListInput;
import com.sg.ui.model.input.IInputProvider;
import com.sg.ui.model.input.IInputReceiver;

public class ProductVINInputDP extends BusinessObjectListInput {

    @Override
    protected AuthCollectionService getService() {
        return new ProductVIN();
    }

}