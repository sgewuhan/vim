package com.sg.vim.remove.service;

import com.mobnut.db.collection.AuthCollectionService;
import com.mobnut.portal.Portal;
import com.mongodb.DB;
import com.mongodb.DBObject;
@Deprecated
public class CertificateInfo extends AuthCollectionService {
	
	
	public static final String  F_0_1="f_0_1";  //  ����������ҵ����
	public static final String  E_02="e_02";  //  ������ɫ
	public static final String  F_0_2_1="f_0_2_1";  //  ��������
	public static final String  F_C0_2="f_c0_2";  //  ����Ʒ��
	public static final String  F_0_4="f_0_4";  //  �����ͺ�(���)
	public static final String  F_0_5b="f_0_5b";  //  ����������λ��ַ
	public static final String  F_1="f_1";  //  ����
	public static final String  F_1_1="f_1_1";  //  ��̥����
	public static final String  F_3="f_3";  //  ���
	public static final String  F_5="f_5";  //  ǰ�־�/���־�
	public static final String  F_6_1="f_6_1";  //  �����ߴ糤
	public static final String  F_7_1="f_7_1";  //  �����ߴ��
	public static final String  F_8="f_8";  //  �����ߴ��
	public static final String  F_14_1="f_14_1";  //  ������
	public static final String  F_14_2="f_14_2";  //  ���
	public static final String  F_C4="f_c4";  //  �������ͺ�
	public static final String  F_24="f_24";  //  ����
	public static final String  F_25="f_25";  //  ȼ������
	public static final String  F_32="f_32";  //  ��̥���
	public static final String  F_42_1="f_42_1";  //  ��ؿ�
	public static final String  F_44="f_44";  //  �����Ƴ���
	public static final String  F_C5="f_c5";  //  ����������ϵ��
	public static final String  F_C6="f_c6";  //  �ְ嵯��Ƭ��
	public static final String  F_C34="f_c34";  //  ת����ʽ
	public static final String  F_C7_1="f_c7_1";  //  �����ڲ��ߴ糤
	public static final String  F_C7_2="f_c7_2";  //  �����ڲ��ߴ��
	public static final String  F_C7_3="f_c7_3";  //  �����ڲ��ߴ��
	public static final String  C_01="c_01";  //  ����
	public static final String  C_02="c_02";  //  ��ʻ��׼������
	public static final String  C_03="c_03";  //  �ͺ�
	public static final String  C_04="c_04";  //  ��ҳ������������������
	public static final String  C_05="c_05";  //  ����
	public static final String  C_06="c_06";  //  �ŷű�׼
	public static final String  C_07="c_07";  //  �����ͺ�
	public static final String  C_08="c_08";  //  ��������
	public static final String  C_09="c_09";  //  �������
	public static final String  C_10="c_10";  //  ׼ǣ��������
	public static final String  C_11="c_11";  //  �ϸ�֤�߱�ע
	public static final String  C_12="c_12";  //  ����ID
	public static final String  C_13="c_13";  //  ת������
	public static final String  C_14="c_14";  //  ��ҵ���
	public static final String  C_15="c_15";  //  ���ƶ�������ʽ
	public static final String  C_16="c_16";  //  ���ƶ���ʽ
	public static final String  C_17="c_17";  //  ��ҵ��׼
	public static final String  C_18="c_18";  //  ��ҵ������Ϣ
	public static final String  C_19="c_19";  //  ǰ�ƶ�������ʽ
	public static final String  C_20="c_20";  //  ǰ�ƶ���ʽ
	public static final String  C_21="c_21";  //  ���綯���
	public static final String  C_22="c_22";  //  ��������
	public static final String  C_23="c_23";  //  ����״̬��Ϣ
	public static final String  C_25="c_25";  //  ������������
	public static final String  C_26="c_26";  //  ����ʶ�����/���ܺ�(VIN)
	public static final String  C_27="c_27";  //  ״̬
	public static final String  C_28="c_28";  //  �ϴ�����Ӳ����Ϣ
	public static final String  C_29="c_29";  //  �ϴ�����
	public static final String  C_30="c_30";  //  ��������ʱ��
	public static final String  C_31="c_31";  //  ������
	public static final String  C_32="c_32";  //  �ϴ�������ַ
	public static final String  C_33="c_33";  //  ��ҵ�û���
	public static final String  C_34="c_34";  //  U�ܱ�ʶ
	public static final String  C_35="c_35";  //  ��������
	public static final String  C_36="c_36";  //  �ϴ����
	public static final String  C_37="c_37";  //  У����
	public static final String  C_38="c_38";  //  �ϴ�ϵͳ�汾��Ϣ
	public static final String  C_39="c_39";  //  �����ϸ�֤���
	public static final String  C_40="c_40";  //  ���򣺸Ķ���������
	public static final String  C_41="c_41";  //  ��������
	public static final String  C_42="c_42";  //  ��ӡΨһ��
	public static final String  C_43="c_43";  //  �����ϸ�֤���
	public static final String  C_44="c_44";  //  ֽ�ű�� 
	public static final String  C_45="c_45";  //  ��֤����
	public static final String  C_46="c_46";  //  ���̺ϸ�֤���
	public static final String  C_47="c_47";  //  ��ά��
	public static final String  C_48="c_48";  //  �ϸ�֤��ţ��̶������ң�������
	public static final String  C_49="c_49";  //  ��֤ʱ��
	public static final String  C_50="c_50";  //  ��������
	public static final String  C_51="c_51";  //  �������к�


	
	@Override
	public DB getDB() {
		return Portal.getBasicDB();
	}

	/* (non-Javadoc)
	 * @see com.mobnut.db.collection.CollectionService#getCollectionName()
	 */
	@Override
	protected String getCollectionName() {
		return "certificateinfo";
	}

	/* (non-Javadoc)
	 * @see com.mobnut.db.collection.AuthCollectionService#getDefaultSearchColumns()
	 */
	@Override
	public DBObject getDefaultSearchColumns() {
		return null;
	}

}
