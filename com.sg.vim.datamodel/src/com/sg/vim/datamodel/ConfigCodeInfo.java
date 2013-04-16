package com.sg.vim.datamodel;

import com.mobnut.db.collection.AuthCollectionService;
import com.mobnut.portal.Portal;
import com.mongodb.DB;
import com.mongodb.DBObject;

public class ConfigCodeInfo extends AuthCollectionService {


	public static final String  H_01="h_01";   //’˝ Ω≈‰÷√–Ú¡–∫≈
	public static final String  H_02="h_02";   //¡Ÿ ±≈‰÷√–Ú¡–∫≈
	public static final String  H_03="h_03";   //≈‰÷√√Ë ˆ
	public static final String  H_04="h_04";   //≈‰÷√
	public static final String  F_0_5a="f_0_5a";   //

	
	@Override
	public DB getDB() {
		return Portal.getBasicDB();
	}

	/* (non-Javadoc)
	 * @see com.mobnut.db.collection.CollectionService#getCollectionName()
	 */
	@Override
	protected String getCollectionName() {
		return "configcodeinfo";
	}

	/* (non-Javadoc)
	 * @see com.mobnut.db.collection.AuthCollectionService#getDefaultSearchColumns()
	 */
	@Override
	public DBObject getDefaultSearchColumns() {
		return null;
	}

}
