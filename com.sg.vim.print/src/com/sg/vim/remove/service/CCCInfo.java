package com.sg.vim.remove.service;

import com.mobnut.db.collection.AuthCollectionService;
import com.mobnut.portal.Portal;
import com.mongodb.DB;
import com.mongodb.DBObject;
@Deprecated
public class CCCInfo extends AuthCollectionService {

	public static final String  CCC_12="ccc_12";   //车辆一致性证书ID
	public static final String  CCC_00="ccc_00";   //车辆一致性证书编号
	public static final String  F_0_1="f_0_1";   //0.1 车辆制造企业名称
	public static final String  F_C0_1="f_c0_1";   //C0.1车辆制造国
	public static final String  F_0_2a="f_0_2a";   //0.2 车型系列代号
	public static final String  F_0_2a1="f_0_2a1";   //名称
	public static final String  F_0_2b="f_0_2b";   //单元代号
	public static final String  F_0_2b1="f_0_2b1";   //名称
	public static final String  F_0_2C="f_0_2c";   //车型代号
	public static final String  F_0_2C1="f_0_2c1";   //名称
	public static final String  F_0_2_1="f_0_2_1";   //0.2.1 车型名称
	public static final String  F_C0_2="f_c0_2";   //C 0.2 中文品牌
	public static final String  F_C0_3="f_c0_3";   //C0.3 英文品牌
	public static final String  F_0_4="f_0_4";   //0.4 车辆类别 
	public static final String  F_0_5a="f_0_5a";   //0.5 名称
	public static final String  F_0_5b="f_0_5b";   //地址
	public static final String  F_0_6a="f_0_6a";   //0.6 法定铭牌的位置
	public static final String  F_0_6b="f_0_6b";   //车辆识别代号
	public static final String  F_0_6C="f_0_6c";   //车辆识别代号的打刻位置
	public static final String  F_C0_7="f_c0_7";   //0.7 车辆注册类型
	public static final String  F_21a="f_21a";   //21 发动机编号
	public static final String  F_21b="f_21b";   //发动机编号在发动机上的打刻位置
	public static final String  CCC_01="ccc_01";   //CCC认证过程中车辆的制造阶段
	public static final String  CCC_02="ccc_02";   //CCC证书编号(版本号)
	public static final String  CCC_03="ccc_03";   //签发日期
	public static final String  F_1="f_1";   //1 车轴数量
	public static final String  F_1_1="f_1_1";   //车轮数量
	public static final String  F_2="f_2";   //2 驱动轴位置
	public static final String  F_3="f_3";   //3 轴距(mm)
	public static final String  F_5="f_5";   //5 轮距(mm)
	public static final String  F_6_1="f_6_1";   //6.1 长度(mm)
	public static final String  F_7_1="f_7_1";   //7.1 宽度(mm)
	public static final String  F_8="f_8";   //8 高度(mm)
	public static final String  F_C1="f_c1";   //C1 前悬(mm)
	public static final String  F_11="f_11";   //l1 后悬(mm)
	public static final String  F_C2="f_c2";   //C2 接近角(°)
	public static final String  F_C3="f_c3";   //C3 离去角(°)
	public static final String  F_12_1="f_12_1";   //12.1 行驶状态下带车身的车辆质量（kg）
	public static final String  F_14_1="f_14_1";   //14.1 额定总质量(kg)
	public static final String  F_14_2="f_14_2";   //14.2 该质量的轴荷分配(kg)
	public static final String  F_14_3="f_14_3";   //14.3 各车轴或车轴组技术上允许的最大质量(kg)
	public static final String  F_16="f_16";   //16 车顶最大允许载荷(kg)
	public static final String  F_17="f_17";   //17 挂车的最大质量(制动下)(kg)
	public static final String  F_17_4="f_17_4";   //非制动下
	public static final String  F_18="f_18";   //18 牵引车与挂车的最大组合质量(kg)
	public static final String  F_19_1="f_19_1";   //19.1 牵引车与挂车连接点处最大垂直负荷(kg)
	public static final String  F_20="f_20";   //20 发动机制造商名称
	public static final String  F_C4="f_c4";   //C4 发动机型号
	public static final String  F_22="f_22";   //22 发动机工作原理
	public static final String  F_22_1="f_22_1";   //22.1 直接喷射
	public static final String  F_23="f_23";   //23 缸数量
	public static final String  F_23a="f_23a";   //排列形式
	public static final String  F_24="f_24";   //24 排量(ml)
	public static final String  F_25="f_25";   //25 燃料种类
	public static final String  F_26="f_26";   //26 最大净功率(kw)
	public static final String  F_26a="f_26a";   //对应转速(min-1)
	public static final String  F_27="f_27";   //27 离合器形式
	public static final String  F_28="f_28";   //28 变速器形式
	public static final String  F_29="f_29";   //29 速比
	public static final String  F_30="f_30";   //30 主传动比
	public static final String  F_32="f_32";   //32 轮胎规格
	public static final String  F_34="f_34";   //34 转向助力形式
	public static final String  F_35="f_35";   //35 制动装置简要说明
	public static final String  F_37="f_37";   //37 车身型式
	public static final String  F_38="f_38";   //38 车辆颜色
	public static final String  F_41="f_41";   //41 车门数量
	public static final String  F_41a="f_41a";   //车门结构
	public static final String  F_42_1="f_42_1";   //42.1 座位数(包括驾驶员座)
	public static final String  F_42_1a="f_42_1a";   //布置方式
	public static final String  F_43_1="f_43_1";   //43.1 如装有牵引装置CCC证书编号
	public static final String  F_43_1a="f_43_1a";   //或试验报告编号
	public static final String  F_44="f_44";   //44 最高车速(km/h)
	public static final String  F_45="f_45";   //45 声级
	public static final String  F_46_1="f_46_1";   //46.1 排气排放物
	public static final String  F_46_2="f_46_2";   //46.2 CO2排放量/燃油消耗量
	public static final String  F_50="f_50";   //50 备注
	public static final String  F_C5="f_c5";   //C5 载质量利用系数(kg)
	public static final String  F_C6="f_c6";   //C6 钢板弹簧片数
	public static final String  F_C34="f_c34";   //转向形式
	public static final String  F_C7_1="f_c7_1";   //C7 货厢内部尺寸(mm) 长
	public static final String  F_C7_2="f_c7_2";   //宽
	public static final String  F_C7_3="f_c7_3";   //高
	public static final String  F_15="f_15";   //15 可伸缩轴或可装载轴的位置
	public static final String  F_17_1="f_17_1";   //17.1 牵引杆式挂车
	public static final String  F_17_2="f_17_2";   //17.2 半挂车
	public static final String  F_17_3="f_17_3";   //17.3 中心轴式挂车
	public static final String  F_33_1="f_33_1";   //33.1 驱动轴是否装空气悬挂或等效装置
	public static final String  F_36="f_36";   //36 挂车制动系统供气管内压力(bar)
	public static final String  F_39="f_39";   //39 罐式车罐容量(m3)
	public static final String  F_40="f_40";   //40 起重机的最大力矩能力(kNm)
	public static final String  F_48_1="f_48_1";   //48.1 按照运输危险货物的结构要求的试验报告编号
	public static final String  F_48_2="f_48_2";   //48.2 按照运输某些动物的结构要求的试验报告编号
	public static final String  F_42_3="f_42_3";   //42.3 站位数
	public static final String  F_4_1="f_4_1";   //4.1 支承转盘导程
	public static final String  F_6_3="f_6_3";   //6.3 车辆前端与牵引装置中心之间的距离(mm)
	public static final String  F_6_5="f_6_5";   //6.5 装载区长度(mm)
	public static final String  CCC04="ccc04";   //型号
	public static final String  CCC05="ccc05";   //类别
	public static final String  CCC06="ccc06";   //制造商
	public static final String  CCC07="ccc07";   //CCC证书编号(版本号)
	public static final String  CCC08="ccc08";   //签发日期
	public static final String  CCC09="ccc09";   //制造商
	public static final String  CCC10="ccc10";   //CCC证书编号(版本号)
	public static final String  CCC1="ccc1";   //签发日期
	public static final String  F_10_1="f_10_1";   //10.1 车辆在地面上的投影面积(m2)

	
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
