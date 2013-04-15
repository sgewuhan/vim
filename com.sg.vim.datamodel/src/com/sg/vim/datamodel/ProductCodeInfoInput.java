package com.sg.vim.datamodel;

import com.mobnut.db.collection.CollectionService;
import com.mobnut.portal.user.UserSessionContext;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.sg.ui.model.input.InputProvider;

public class ProductCodeInfoInput extends InputProvider {

	private ProductCodeInfo service;

	public ProductCodeInfoInput() {
		service = new ProductCodeInfo();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sg.ui.viewer.InputProvider#getInitialInput()
	 */
	@Override
	protected Object getInitialInput() {
		DBObject projection = service.getDefaultSearchColumns();
		try {
			String accountName = UserSessionContext.getSession().getUserId();
			DBCursor cursor = service.findWithAuthorize(accountName,
					projection);
			return cursor.sort(new BasicDBObject().append(CollectionService.FIELD_SYSID, -1));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


}
