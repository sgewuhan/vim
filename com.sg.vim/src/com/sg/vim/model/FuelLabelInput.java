package com.sg.vim.model;

import com.mobnut.db.collection.AuthCollectionService;
import com.sg.ui.model.input.BusinessObjectListInput;

public class FuelLabelInput extends BusinessObjectListInput {

    @Override
    protected AuthCollectionService getService() {
        return new FuelLabelService();
    }

}
