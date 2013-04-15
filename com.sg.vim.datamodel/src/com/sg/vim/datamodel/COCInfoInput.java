package com.sg.vim.datamodel;

import com.mobnut.db.collection.AuthCollectionService;
import com.sg.ui.model.input.BusinessObjectListInput;

public class COCInfoInput extends BusinessObjectListInput {

	@Override
	protected AuthCollectionService getService() {
		return new COCInfo();
	}

}
