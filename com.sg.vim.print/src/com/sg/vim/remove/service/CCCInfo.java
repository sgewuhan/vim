package com.sg.vim.remove.service;

import com.mobnut.db.collection.AuthCollectionService;
import com.mobnut.portal.Portal;
import com.mongodb.DB;
import com.mongodb.DBObject;
@Deprecated
public class CCCInfo extends AuthCollectionService {

	public static final String  CCC_12="ccc_12";   //����һ����֤��ID
	public static final String  CCC_00="ccc_00";   //����һ����֤����
	public static final String  F_0_1="f_0_1";   //0.1 ����������ҵ����
	public static final String  F_C0_1="f_c0_1";   //C0.1���������
	public static final String  F_0_2a="f_0_2a";   //0.2 ����ϵ�д���
	public static final String  F_0_2a1="f_0_2a1";   //����
	public static final String  F_0_2b="f_0_2b";   //��Ԫ����
	public static final String  F_0_2b1="f_0_2b1";   //����
	public static final String  F_0_2C="f_0_2c";   //���ʹ���
	public static final String  F_0_2C1="f_0_2c1";   //����
	public static final String  F_0_2_1="f_0_2_1";   //0.2.1 ��������
	public static final String  F_C0_2="f_c0_2";   //C 0.2 ����Ʒ��
	public static final String  F_C0_3="f_c0_3";   //C0.3 Ӣ��Ʒ��
	public static final String  F_0_4="f_0_4";   //0.4 ������� 
	public static final String  F_0_5a="f_0_5a";   //0.5 ����
	public static final String  F_0_5b="f_0_5b";   //��ַ
	public static final String  F_0_6a="f_0_6a";   //0.6 �������Ƶ�λ��
	public static final String  F_0_6b="f_0_6b";   //����ʶ�����
	public static final String  F_0_6C="f_0_6c";   //����ʶ����ŵĴ��λ��
	public static final String  F_C0_7="f_c0_7";   //0.7 ����ע������
	public static final String  F_21a="f_21a";   //21 ���������
	public static final String  F_21b="f_21b";   //����������ڷ������ϵĴ��λ��
	public static final String  CCC_01="ccc_01";   //CCC��֤�����г���������׶�
	public static final String  CCC_02="ccc_02";   //CCC֤����(�汾��)
	public static final String  CCC_03="ccc_03";   //ǩ������
	public static final String  F_1="f_1";   //1 ��������
	public static final String  F_1_1="f_1_1";   //��������
	public static final String  F_2="f_2";   //2 ������λ��
	public static final String  F_3="f_3";   //3 ���(mm)
	public static final String  F_5="f_5";   //5 �־�(mm)
	public static final String  F_6_1="f_6_1";   //6.1 ����(mm)
	public static final String  F_7_1="f_7_1";   //7.1 ���(mm)
	public static final String  F_8="f_8";   //8 �߶�(mm)
	public static final String  F_C1="f_c1";   //C1 ǰ��(mm)
	public static final String  F_11="f_11";   //l1 ����(mm)
	public static final String  F_C2="f_c2";   //C2 �ӽ���(��)
	public static final String  F_C3="f_c3";   //C3 ��ȥ��(��)
	public static final String  F_12_1="f_12_1";   //12.1 ��ʻ״̬�´�����ĳ���������kg��
	public static final String  F_14_1="f_14_1";   //14.1 �������(kg)
	public static final String  F_14_2="f_14_2";   //14.2 ����������ɷ���(kg)
	public static final String  F_14_3="f_14_3";   //14.3 ����������鼼����������������(kg)
	public static final String  F_16="f_16";   //16 ������������غ�(kg)
	public static final String  F_17="f_17";   //17 �ҳ����������(�ƶ���)(kg)
	public static final String  F_17_4="f_17_4";   //���ƶ���
	public static final String  F_18="f_18";   //18 ǣ������ҳ�������������(kg)
	public static final String  F_19_1="f_19_1";   //19.1 ǣ������ҳ����ӵ㴦���ֱ����(kg)
	public static final String  F_20="f_20";   //20 ����������������
	public static final String  F_C4="f_c4";   //C4 �������ͺ�
	public static final String  F_22="f_22";   //22 ����������ԭ��
	public static final String  F_22_1="f_22_1";   //22.1 ֱ������
	public static final String  F_23="f_23";   //23 ������
	public static final String  F_23a="f_23a";   //������ʽ
	public static final String  F_24="f_24";   //24 ����(ml)
	public static final String  F_25="f_25";   //25 ȼ������
	public static final String  F_26="f_26";   //26 ��󾻹���(kw)
	public static final String  F_26a="f_26a";   //��Ӧת��(min-1)
	public static final String  F_27="f_27";   //27 �������ʽ
	public static final String  F_28="f_28";   //28 ��������ʽ
	public static final String  F_29="f_29";   //29 �ٱ�
	public static final String  F_30="f_30";   //30 ��������
	public static final String  F_32="f_32";   //32 ��̥���
	public static final String  F_34="f_34";   //34 ת��������ʽ
	public static final String  F_35="f_35";   //35 �ƶ�װ�ü�Ҫ˵��
	public static final String  F_37="f_37";   //37 ������ʽ
	public static final String  F_38="f_38";   //38 ������ɫ
	public static final String  F_41="f_41";   //41 ��������
	public static final String  F_41a="f_41a";   //���Žṹ
	public static final String  F_42_1="f_42_1";   //42.1 ��λ��(������ʻԱ��)
	public static final String  F_42_1a="f_42_1a";   //���÷�ʽ
	public static final String  F_43_1="f_43_1";   //43.1 ��װ��ǣ��װ��CCC֤����
	public static final String  F_43_1a="f_43_1a";   //�����鱨����
	public static final String  F_44="f_44";   //44 ��߳���(km/h)
	public static final String  F_45="f_45";   //45 ����
	public static final String  F_46_1="f_46_1";   //46.1 �����ŷ���
	public static final String  F_46_2="f_46_2";   //46.2 CO2�ŷ���/ȼ��������
	public static final String  F_50="f_50";   //50 ��ע
	public static final String  F_C5="f_c5";   //C5 ����������ϵ��(kg)
	public static final String  F_C6="f_c6";   //C6 �ְ嵯��Ƭ��
	public static final String  F_C34="f_c34";   //ת����ʽ
	public static final String  F_C7_1="f_c7_1";   //C7 �����ڲ��ߴ�(mm) ��
	public static final String  F_C7_2="f_c7_2";   //��
	public static final String  F_C7_3="f_c7_3";   //��
	public static final String  F_15="f_15";   //15 ����������װ�����λ��
	public static final String  F_17_1="f_17_1";   //17.1 ǣ����ʽ�ҳ�
	public static final String  F_17_2="f_17_2";   //17.2 ��ҳ�
	public static final String  F_17_3="f_17_3";   //17.3 ������ʽ�ҳ�
	public static final String  F_33_1="f_33_1";   //33.1 �������Ƿ�װ�������һ��Чװ��
	public static final String  F_36="f_36";   //36 �ҳ��ƶ�ϵͳ��������ѹ��(bar)
	public static final String  F_39="f_39";   //39 ��ʽ��������(m3)
	public static final String  F_40="f_40";   //40 ���ػ��������������(kNm)
	public static final String  F_48_1="f_48_1";   //48.1 ��������Σ�ջ���ĽṹҪ������鱨����
	public static final String  F_48_2="f_48_2";   //48.2 ��������ĳЩ����ĽṹҪ������鱨����
	public static final String  F_42_3="f_42_3";   //42.3 վλ��
	public static final String  F_4_1="f_4_1";   //4.1 ֧��ת�̵���
	public static final String  F_6_3="f_6_3";   //6.3 ����ǰ����ǣ��װ������֮��ľ���(mm)
	public static final String  F_6_5="f_6_5";   //6.5 װ��������(mm)
	public static final String  CCC04="ccc04";   //�ͺ�
	public static final String  CCC05="ccc05";   //���
	public static final String  CCC06="ccc06";   //������
	public static final String  CCC07="ccc07";   //CCC֤����(�汾��)
	public static final String  CCC08="ccc08";   //ǩ������
	public static final String  CCC09="ccc09";   //������
	public static final String  CCC10="ccc10";   //CCC֤����(�汾��)
	public static final String  CCC1="ccc1";   //ǩ������
	public static final String  F_10_1="f_10_1";   //10.1 �����ڵ����ϵ�ͶӰ���(m2)

	
	@Override
	public DB getDB() {
		return Portal.getBasicDB();
	}

	/* (non-Javadoc)
	 * @see com.mobnut.db.collection.CollectionService#getCollectionName()
	 */
	@Override
	protected String getCollectionName() {
		return "cccinfo";
	}

	/* (non-Javadoc)
	 * @see com.mobnut.db.collection.AuthCollectionService#getDefaultSearchColumns()
	 * 
	 */
	@Override
	public DBObject getDefaultSearchColumns() {
		return null;
	}

}
