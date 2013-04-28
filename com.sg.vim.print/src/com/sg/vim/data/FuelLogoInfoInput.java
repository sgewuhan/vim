package com.sg.vim.data;

import com.mobnut.db.collection.AuthCollectionService;
import com.sg.ui.model.input.BusinessObjectListInput;
import com.sg.vim.remove.service.FuelLogoInfo;

public class FuelLogoInfoInput extends BusinessObjectListInput {

	@Override
	protected AuthCollectionService getService() {
		return new FuelLogoInfo();
	}

}