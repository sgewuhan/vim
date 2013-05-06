package com.sg.vim.remove.data;

import com.mobnut.db.collection.AuthCollectionService;
import com.sg.ui.model.input.BusinessObjectListInput;
import com.sg.vim.remove.service.FuelLogoInfo;
@Deprecated
public class FuelLogoInfoInput extends BusinessObjectListInput {

	@Override
	protected AuthCollectionService getService() {
		return new FuelLogoInfo();
	}

}