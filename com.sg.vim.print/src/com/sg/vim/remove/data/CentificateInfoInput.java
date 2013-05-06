package com.sg.vim.remove.data;

import com.mobnut.db.collection.AuthCollectionService;
import com.sg.ui.model.input.BusinessObjectListInput;
import com.sg.vim.remove.service.CertificateInfo;
@Deprecated
public class CentificateInfoInput extends BusinessObjectListInput {

	@Override
	protected AuthCollectionService getService() {
		return new CertificateInfo();
	}

}
