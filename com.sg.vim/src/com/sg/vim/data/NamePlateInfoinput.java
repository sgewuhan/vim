package com.sg.vim.data;

import com.mobnut.db.collection.AuthCollectionService;
import com.sg.ui.model.input.BusinessObjectListInput;
import com.sg.vim.service.NamePlateInfo;

public class NamePlateInfoinput extends BusinessObjectListInput {

	@Override
	protected AuthCollectionService getService() {
		return new NamePlateInfo();
	}

}