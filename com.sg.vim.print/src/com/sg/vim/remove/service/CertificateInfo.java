package com.sg.vim.remove.service;

import com.mobnut.db.collection.AuthCollectionService;
import com.mobnut.portal.Portal;
import com.mongodb.DB;
import com.mongodb.DBObject;
@Deprecated
public class CertificateInfo extends AuthCollectionService {
	
	
	public static final String  F_0_1="f_0_1";  //  车辆制造企业名称
	public static final String  E_02="e_02";  //  车身颜色
	public static final String  F_0_2_1="f_0_2_1";  //  车辆名称
	public static final String  F_C0_2="f_c0_2";  //  车辆品牌
	public static final String  F_0_4="f_0_4";  //  车辆型号(类别)
	public static final String  F_0_5b="f_0_5b";  //  车辆生产单位地址
	public static final String  F_1="f_1";  //  轴数
	public static final String  F_1_1="f_1_1";  //  轮胎数量
	public static final String  F_3="f_3";  //  轴距
	public static final String  F_5="f_5";  //  前轮距/后轮距
	public static final String  F_6_1="f_6_1";  //  外廓尺寸长
	public static final String  F_7_1="f_7_1";  //  外廓尺寸高
	public static final String  F_8="f_8";  //  外廓尺寸宽
	public static final String  F_14_1="f_14_1";  //  总质量
	public static final String  F_14_2="f_14_2";  //  轴荷
	public static final String  F_C4="f_c4";  //  发动机型号
	public static final String  F_24="f_24";  //  排量
	public static final String  F_25="f_25";  //  燃料种类
	public static final String  F_32="f_32";  //  轮胎规格
	public static final String  F_42_1="f_42_1";  //  额定载客
	public static final String  F_44="f_44";  //  最高设计车速
	public static final String  F_C5="f_c5";  //  载质量利用系数
	public static final String  F_C6="f_c6";  //  钢板弹簧片数
	public static final String  F_C34="f_c34";  //  转向形式
	public static final String  F_C7_1="f_c7_1";  //  货箱内部尺寸长
	public static final String  F_C7_2="f_c7_2";  //  货箱内部尺寸高
	public static final String  F_C7_3="f_c7_3";  //  货箱内部尺寸宽
	public static final String  C_01="c_01";  //  功率
	public static final String  C_02="c_02";  //  驾驶室准乘人数
	public static final String  C_03="c_03";  //  油耗
	public static final String  C_04="c_04";  //  半挂车鞍座最大允许总质量
	public static final String  C_05="c_05";  //  其他
	public static final String  C_06="c_06";  //  排放标准
	public static final String  C_07="c_07";  //  底盘型号
	public static final String  C_08="c_08";  //  整备质量
	public static final String  C_09="c_09";  //  额定载质量
	public static final String  C_10="c_10";  //  准牵引总质量
	public static final String  C_11="c_11";  //  合格证＿备注
	public static final String  C_12="c_12";  //  底盘ID
	public static final String  C_13="c_13";  //  转向轴数
	public static final String  C_14="c_14";  //  企业编号
	public static final String  C_15="c_15";  //  后制动操作方式
	public static final String  C_16="c_16";  //  后制动方式
	public static final String  C_17="c_17";  //  企业标准
	public static final String  C_18="c_18";  //  企业其它信息
	public static final String  C_19="c_19";  //  前制动操作方式
	public static final String  C_20="c_20";  //  前制动方式
	public static final String  C_21="c_21";  //  纯电动标记
	public static final String  C_22="c_22";  //  车辆类型
	public static final String  C_23="c_23";  //  车辆状态信息
	public static final String  C_25="c_25";  //  车辆制造日期
	public static final String  C_26="c_26";  //  车辆识别代码/车架号(VIN)
	public static final String  C_27="c_27";  //  状态
	public static final String  C_28="c_28";  //  上传主机硬件信息
	public static final String  C_29="c_29";  //  上传日期
	public static final String  C_30="c_30";  //  公安反馈时间
	public static final String  C_31="c_31";  //  反馈码
	public static final String  C_32="c_32";  //  上传主机地址
	public static final String  C_33="c_33";  //  企业用户名
	public static final String  C_34="c_34";  //  U盾标识
	public static final String  C_35="c_35";  //  更新日期
	public static final String  C_36="c_36";  //  上传标记
	public static final String  C_37="c_37";  //  校验码
	public static final String  C_38="c_38";  //  上传系统版本信息
	public static final String  C_39="c_39";  //  完整合格证编号
	public static final String  C_40="c_40";  //  规则：改动车辆日期
	public static final String  C_41="c_41";  //  操作日期
	public static final String  C_42="c_42";  //  打印唯一码
	public static final String  C_43="c_43";  //  整车合格证编号
	public static final String  C_44="c_44";  //  纸张编号 
	public static final String  C_45="c_45";  //  发证日期
	public static final String  C_46="c_46";  //  底盘合格证编号
	public static final String  C_47="c_47";  //  二维码
	public static final String  C_48="c_48";  //  合格证编号（固定＋国家＋递增）
	public static final String  C_49="c_49";  //  发证时间
	public static final String  C_50="c_50";  //  发动机号
	public static final String  C_51="c_51";  //  配置序列号


	
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
