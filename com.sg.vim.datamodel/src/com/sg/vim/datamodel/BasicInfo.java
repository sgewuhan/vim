package com.sg.vim.datamodel;

import com.mobnut.db.collection.AuthCollectionService;
import com.mobnut.portal.Portal;
import com.mongodb.DB;
import com.mongodb.DBObject;

public class BasicInfo extends AuthCollectionService {

	public static final String  F_0_1="f_0_1";  //车辆生产单位名
	public static final String  F_0_2C="f_0_2c";  //车辆型号
	public static final String  F_0_2_1="f_0_2_1";  //车辆名称
	public static final String  F_C0_2="f_c0_2";  //车辆品牌
	public static final String  F_0_5b="f_0_5b";  //生产地址
	public static final String  F_1="f_1";  //轴数
	public static final String  F_1_1="f_1_1";  //轮胎数
	public static final String  F_3="f_3";  //轴距
	public static final String  F_5="f_5";  //前轮距/后轮距
	public static final String  F_6_1="f_6_1";  //外廓长
	public static final String  F_7_1="f_7_1";  //外廓高
	public static final String  F_8="f_8";  //外廓宽
	public static final String  F_C1="f_c1";  //前悬
	public static final String  F_11="f_11";  //后悬
	public static final String  F_C2="f_c2";  //接近角
	public static final String  F_C3="f_c3";  //离去角
	public static final String  F_14_1="f_14_1";  //总质量
	public static final String  F_14_2="f_14_2";  //轴荷
	public static final String  F_14_3="f_14_3";  //轴荷（已有）
	public static final String  F_20="f_20";  //发动机生产企业
	public static final String  F_C4="f_c4";  //发动机型号
	public static final String  F_24="f_24";  //排量
	public static final String  F_25="f_25";  //燃料种类
	public static final String  F_32="f_32";  //轮胎规格
	public static final String  F_42_1="f_42_1";  //额定载客
	public static final String  F_44="f_44";  //最高车速
	public static final String  F_C5="f_c5";  //载质量利用系数
	public static final String  F_C6="f_c6";  //钢板弹簧片数
	public static final String  F_C34="f_c34";  //转向形式
	public static final String  F_C7_1="f_c7_1";  //货箱内部长
	public static final String  F_C7_2="f_c7_2";  //货箱内部高
	public static final String  F_C7_3="f_c7_3";  //货箱内部宽
	public static final String  C_01="c_01";  //功率
	public static final String  C_02="c_02";  //驾驶室准乘人数
	public static final String  C_03="c_03";  //油耗
	public static final String  C_04="c_04";  //半挂车鞍座最大允许总质量
	public static final String  C_05="c_05";  //其他
	public static final String  C_06="c_06";  //排放依据标准
	public static final String  CCC_04="ccc_04";  //底盘型号
	public static final String  C_08="c_08";  //整备质量
	public static final String  C_09="c_09";  //额定载质量
	public static final String  C_10="c_10";  //准牵引总质量
	public static final String  C_11="c_11";  //合格证＿备注
	public static final String  C_12="c_12";  //底盘ID
	public static final String  C_13="c_13";  //转向轴数
	public static final String  C_14="c_14";  //企业编号
	public static final String  C_15="c_15";  //后制动操作方式
	public static final String  C_16="c_16";  //后制动方式
	public static final String  C_17="c_17";  //企业标准
	public static final String  C_18="c_18";  //企业其它信息
	public static final String  C_19="c_19";  //前制动操作方式
	public static final String  C_20="c_20";  //前制动方式
	public static final String  C_21="c_21";  //纯电动标记
	public static final String  C_22="c_22";  //车辆类型
	public static final String  C_23="c_23";  //车辆状态信息
	public static final String  D_01="d_01";  //生效日期
//	public static final String  D_02="d_02";  //产品ID zhonghua 424
	public static final String  D_03="d_03";  //法人代表
	public static final String  D_04="d_04";  //注册地址
	public static final String  D_05="d_05";  //目录序号
	public static final String  D_06="d_06";  //电话号码
	public static final String  D_07="d_07";  //准拖挂车总质量
	public static final String  D_08="d_08";  //防抱死制动系统
	public static final String  CCC_05="ccc_05";  //底盘类别
	public static final String  CCC_06="ccc_06";  //底盘生产企业
	public static final String  D_11="d_11";  //车身反光标识企业
	public static final String  D_12="d_12";  //车身反光标识商标
	public static final String  D_13="d_13";  //车身反光标识型号
	public static final String  D_14="d_14";  //城市运转循环
	public static final String  D_15="d_15";  //市郊运转循环
	public static final String  D_16="d_16";  //综合运转循环
	public static final String  D_17="d_17";  //座椅排数
	public static final String  D_18="d_18";  //批次
	public static final String  D_19="d_19";  //内部车型
	public static final String  D_20="d_20";  //销售车型
	public static final String  D_21="d_21";  //变速箱形式
	public static final String  D_22="d_22";  //驱动形式
	public static final String  D_23="d_23";  //产品号
	public static final String  D_24="d_24";  //产品型号名称
	

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
