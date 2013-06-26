package com.sg.vim.model;

import com.mobnut.db.collection.AuthCollectionService;
import com.sg.ui.model.input.BusinessObjectListInput;

public class EnvLabelInput extends BusinessObjectListInput {

    @Override
    protected AuthCollectionService getService() {
        return new EnvLabelService();
    }

}
