package com.sg.vim.datamodel;

import com.mobnut.db.collection.AuthCollectionService;
import com.mobnut.portal.Portal;
import com.mongodb.DB;
import com.mongodb.DBObject;

public class ProductCodeInfo extends AuthCollectionService {

	
	public static final String CCC_12="ccc_12";                            //���泵��
	public static final String E_01="e_01";                              //�ãϣ�
	public static final String E_02="e_02";              //��Ʒ��
	public static final String F_0_2C="f_0_2c";                          //��ɫ

	@Override
	public DB getDB() {
		return Portal.getBasicDB();
	}

	/* (non-Javadoc)
	 * @see com.mobnut.db.collection.CollectionService#getCollectionName()
	 */
	@Override
	protected String getCollectionName() {
		return "productcodeinfo";
	}

	/* (non-Javadoc)
	 * @see com.mobnut.db.collection.AuthCollectionService#getDefaultSearchColumns()
	 */
	@Override
	public DBObject getDefaultSearchColumns() {
		return null;
	}

}
