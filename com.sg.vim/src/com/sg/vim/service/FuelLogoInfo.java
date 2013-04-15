package com.sg.vim.service;

import com.mobnut.db.collection.AuthCollectionService;
import com.mobnut.portal.Portal;
import com.mongodb.DB;
import com.mongodb.DBObject;

public class FuelLogoInfo extends AuthCollectionService
{
	public static final String  F_0_1="f_0_1";   //������ҵ
	public static final String  G_01="g_01";   //������
	public static final String  G_02="g_02";   //��������
	public static final String  G_03="g_03";   //������Ϣ
	public static final String  G_04="g_04";   //����ȼ��������
	public static final String  G_05="g_05";   //�н�ȼ��������
	public static final String  G_06="g_06";   //�ۺ�ȼ��������
	public static final String  F_0_4="f_0_4";   //�����ͺ�
	public static final String  F_14_1="f_14_1";   //������������
	public static final String  F_C4="f_c4";   //�������ͺ�
	public static final String  F_24="f_24";   //����
	public static final String  F_25="f_25";   //ȼ������

	
	@Override
	public DB getDB() {
		return Portal.getBasicDB();
	}

	/* (non-Javadoc)
	 * @see com.mobnut.db.collection.CollectionService#getCollectionName()
	 */
	@Override
	protected String getCollectionName() {
		return "fuelinfo";
	}

	/* (non-Javadoc)
	 * @see com.mobnut.db.collection.AuthCollectionService#getDefaultSearchColumns()
	 */
	@Override
	public DBObject getDefaultSearchColumns() {
		return null;
	}

}
