package com.sg.vim.datamodel;

import com.mobnut.db.collection.AuthCollectionService;
import com.mobnut.portal.Portal;
import com.mongodb.DB;
import com.mongodb.DBObject;

public class BasicInfo extends AuthCollectionService {

	public static final String  F_0_1="f_0_1";  //����������λ��
	public static final String  F_0_2C="f_0_2c";  //�����ͺ�
	public static final String  F_0_2_1="f_0_2_1";  //��������
	public static final String  F_C0_2="f_c0_2";  //����Ʒ��
	public static final String  F_0_5b="f_0_5b";  //������ַ
	public static final String  F_1="f_1";  //����
	public static final String  F_1_1="f_1_1";  //��̥��
	public static final String  F_3="f_3";  //���
	public static final String  F_5="f_5";  //ǰ�־�/���־�
	public static final String  F_6_1="f_6_1";  //������
	public static final String  F_7_1="f_7_1";  //������
	public static final String  F_8="f_8";  //������
	public static final String  F_C1="f_c1";  //ǰ��
	public static final String  F_11="f_11";  //����
	public static final String  F_C2="f_c2";  //�ӽ���
	public static final String  F_C3="f_c3";  //��ȥ��
	public static final String  F_14_1="f_14_1";  //������
	public static final String  F_14_2="f_14_2";  //���
	public static final String  F_14_3="f_14_3";  //��ɣ����У�
	public static final String  F_20="f_20";  //������������ҵ
	public static final String  F_C4="f_c4";  //�������ͺ�
	public static final String  F_24="f_24";  //����
	public static final String  F_25="f_25";  //ȼ������
	public static final String  F_32="f_32";  //��̥���
	public static final String  F_42_1="f_42_1";  //��ؿ�
	public static final String  F_44="f_44";  //��߳���
	public static final String  F_C5="f_c5";  //����������ϵ��
	public static final String  F_C6="f_c6";  //�ְ嵯��Ƭ��
	public static final String  F_C34="f_c34";  //ת����ʽ
	public static final String  F_C7_1="f_c7_1";  //�����ڲ���
	public static final String  F_C7_2="f_c7_2";  //�����ڲ���
	public static final String  F_C7_3="f_c7_3";  //�����ڲ���
	public static final String  C_01="c_01";  //����
	public static final String  C_02="c_02";  //��ʻ��׼������
	public static final String  C_03="c_03";  //�ͺ�
	public static final String  C_04="c_04";  //��ҳ������������������
	public static final String  C_05="c_05";  //����
	public static final String  C_06="c_06";  //�ŷ����ݱ�׼
	public static final String  CCC_04="ccc_04";  //�����ͺ�
	public static final String  C_08="c_08";  //��������
	public static final String  C_09="c_09";  //�������
	public static final String  C_10="c_10";  //׼ǣ��������
	public static final String  C_11="c_11";  //�ϸ�֤�߱�ע
	public static final String  C_12="c_12";  //����ID
	public static final String  C_13="c_13";  //ת������
	public static final String  C_14="c_14";  //��ҵ���
	public static final String  C_15="c_15";  //���ƶ�������ʽ
	public static final String  C_16="c_16";  //���ƶ���ʽ
	public static final String  C_17="c_17";  //��ҵ��׼
	public static final String  C_18="c_18";  //��ҵ������Ϣ
	public static final String  C_19="c_19";  //ǰ�ƶ�������ʽ
	public static final String  C_20="c_20";  //ǰ�ƶ���ʽ
	public static final String  C_21="c_21";  //���綯���
	public static final String  C_22="c_22";  //��������
	public static final String  C_23="c_23";  //����״̬��Ϣ
	public static final String  D_01="d_01";  //��Ч����
//	public static final String  D_02="d_02";  //��ƷID zhonghua 424
	public static final String  D_03="d_03";  //���˴���
	public static final String  D_04="d_04";  //ע���ַ
	public static final String  D_05="d_05";  //Ŀ¼���
	public static final String  D_06="d_06";  //�绰����
	public static final String  D_07="d_07";  //׼�Ϲҳ�������
	public static final String  D_08="d_08";  //�������ƶ�ϵͳ
	public static final String  CCC_05="ccc_05";  //�������
	public static final String  CCC_06="ccc_06";  //����������ҵ
	public static final String  D_11="d_11";  //�������ʶ��ҵ
	public static final String  D_12="d_12";  //�������ʶ�̱�
	public static final String  D_13="d_13";  //�������ʶ�ͺ�
	public static final String  D_14="d_14";  //������תѭ��
	public static final String  D_15="d_15";  //�н���תѭ��
	public static final String  D_16="d_16";  //�ۺ���תѭ��
	public static final String  D_17="d_17";  //��������
	public static final String  D_18="d_18";  //����
	public static final String  D_19="d_19";  //�ڲ�����
	public static final String  D_20="d_20";  //���۳���
	public static final String  D_21="d_21";  //��������ʽ
	public static final String  D_22="d_22";  //������ʽ
	public static final String  D_23="d_23";  //��Ʒ��
	public static final String  D_24="d_24";  //��Ʒ�ͺ�����
	

	@Override
	public DB getDB() {
		return Portal.getBasicDB();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.mobnut.db.collection.CollectionService#getCollectionName()
	 */
	@Override
	protected String getCollectionName() {
		return "basicinfo";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.mobnut.db.collection.AuthCollectionService#getDefaultSearchColumns()
	 */
	@Override
	public DBObject getDefaultSearchColumns() {
		return null;
	}

}
