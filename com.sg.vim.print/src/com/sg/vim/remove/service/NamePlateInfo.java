package com.sg.vim.remove.service;


import com.mobnut.db.collection.AuthCollectionService;
import com.mobnut.portal.Portal;
import com.mongodb.DB;
import com.mongodb.DBObject;

public class NamePlateInfo extends AuthCollectionService{
	public static final String  K_01 = "k_01" ;  // 英文品牌
	public static final String  G_01 = "g_01" ;  // VIN
	public static final String  F_C0_2 = "f_c0_2" ;  // 品牌
	public static final String  F_0_4 = "f_0_4" ;  // 整车型号
	public static final String  F_14_1 = "f_14_1" ;  // 最大允许总质量
	public static final String  F_C4 = "f_c4" ;  // 发动机型号
	public static final String  F_24 = "f_24" ;  // 发动机排量
	public static final String  F_26 = "f_26" ;  // 最大净功率
	public static final String  F_42_1 = "f_42_1" ;  // 乘员数
	public static final String  C_25 = "c_25" ;  // 制造年月

	@Override
	public DB getDB() {
		return Portal.getBasicDB();
	}

	/* (non-Javadoc)
	 * @see com.mobnut.db.collection.CollectionService#getCollectionName()
	 */
	@Override
	protected String getCollectionName() {
		return "nameplateinfo";
	}

	/* (non-Javadoc)
	 * @see com.mobnut.db.collection.AuthCollectionService#getDefaultSearchColumns()
	 */
	@Override
	public DBObject getDefaultSearchColumns() {
		return null;
	}
}
