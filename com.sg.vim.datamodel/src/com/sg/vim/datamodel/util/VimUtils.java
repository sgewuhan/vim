package com.sg.vim.datamodel.util;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.bson.types.ObjectId;
import org.eclipse.swt.browser.Browser;

import com.mobnut.commons.util.Utils;
import com.mobnut.db.DBActivator;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.sg.sqldb.DDB;
import com.sg.sqldb.utility.SQLResult;
import com.sg.sqldb.utility.SQLRow;
import com.sg.sqldb.utility.SQLUtil;
import com.sg.ui.UI;
import com.sg.ui.model.DataObject;
import com.sg.ui.model.DataObjectEditorInput;
import com.sg.ui.part.editor.IEditorSaveHandler;
import com.sg.ui.registry.config.DataEditorConfigurator;
import com.sg.vim.datamodel.IVIMFields;

public class VimUtils {

    public static boolean debug = false;

    private static final String COL_CONFIGCODEINFO = "configcodeinfo";

    private static final String MES_DB = "mes";

    private static final String COL_COCINFO = "cocinfo";

    private static final String COL_PRODUCTCODEINFO = "productcodeinfo";

    private static final String DB_NAME = "appportal";

    private static final String SQL_GET_PRODUCINFOR = "select erp_product_code,safety_components_vin,manufacture_date "
            + "from bqyx_mes.mes_mp_erp_code_lot_view "
            + "where safety_components_vin is not null and manufacture_date is not null and vin ='";

    private final static String CERT_EDITOR = "com.sg.vim.print.editor.certificate";

    /**
     * 成品码
     */
    public static final String FIELD_PRODUCT_CODE = "erp_product_code";
    /**
     * 发动机号
     */
    public static final String FIELD_ENGINEE_NUM = "safety_components_vin";
    /**
     * 制造日期
     */
    public static final String FIELD_MFT_DATE = "manufacture_date";

    public static final String COL_CERF = "certificateinfo";

    public static final String mVeh_Clztxx = "Veh_Clztxx";// 车辆状态信息 字符 2 取值为QX和DP
    public static final String mVeh_Zchgzbh = "Veh_Zchgzbh";// 整车合格证编号 字符 14
                                                            // 4位企业代码+10位顺序号成功调用打印方法后可以通过该属性获得15位的整车合格证编号
    public static final String mVeh_Dphgzbh = "Veh_Dphgzbh";// 底盘合格证编号 字符 15 全项方式15位；底盘方式不填
    public static final String mVeh_Fzrq = "Veh_Fzrq";// 发证日期 字符 14 YYYY年MM月DD日
    public static final String mVeh_Clzzqymc = "Veh_Clzzqymc";// 车辆制造企业名称 字符 64
    public static final String mVeh_Qyid = "Veh_Qyid";// 企业ID 字符 8 8位公告企业ID
    public static final String mVeh_Clfl = "Veh_Clfl";// 车辆分类 字符 26 代替原属性Veh_cllx。如：货车
    public static final String mVeh_Clmc = "Veh_Clmc";// 车辆名称 字符 54 如：轿车
    public static final String mVeh_Clpp = "Veh_Clpp";// 车辆品牌 字符 30
    public static final String mVeh_Clxh = "Veh_Clxh";// 车辆型号 字符 30
    public static final String mVeh_Csys = "Veh_Csys";// 车身颜色 字符 70 多种颜色之间用“/”分隔
    public static final String mVeh_Dpxh = "Veh_Dpxh";// 底盘型号 字符 30 对于QX方式时使用
    public static final String mVeh_Dpid = "Veh_Dpid";// 底盘ID 字符 7
    public static final String mVeh_Clsbdh = "Veh_Clsbdh";// 车辆识别代号 字符 17
    public static final String mVeh_Cjh = "Veh_Cjh";// 车架号 字符 25
    public static final String mVeh_Fdjh = "Veh_Fdjh";// 发动机号 字符 30
    public static final String mVeh_Fdjxh = "Veh_Fdjxh";// 发动机型号 字符 20
    public static final String mVeh_Rlzl = "Veh_Rlzl";// 燃料种类 字符 30 多种燃料之间用“/”分隔
    public static final String mVeh_Pfbz = "Veh_Pfbz";// 排放标准 字符 60
    public static final String mVeh_Pl = "Veh_Pl";// 排量 字符 5
    public static final String mVeh_Gl = "Veh_Gl";// 功率 字符 7 多种功率之间用“/”分隔
    public static final String mVeh_Zxxs = "Veh_Zxxs";// 转向形式 字符 6 如：方向盘
    public static final String mVeh_Qlj = "Veh_Qlj";// 前轮距 字符 4
    public static final String mVeh_Hlj = "Veh_Hlj";// 后轮距 字符 54
    public static final String mVeh_Lts = "Veh_Lts";// 轮胎数 字符 2
    public static final String mVeh_Ltgg = "Veh_Ltgg";// 轮胎规格 字符 20
    public static final String mVeh_Gbthps = "Veh_Gbthps";// 钢板弹簧片数 字符 30
    public static final String mVeh_Zj = "Veh_Zj";// 轴距 字符 60
    public static final String mVeh_Zh = "Veh_Zh";// 轴荷 字符 30
    public static final String mVeh_Zs = "Veh_Zs";// 轴数 字符 1
    public static final String mVeh_Wkc = "Veh_Wkc";// 外廓长 字符 5
    public static final String mVeh_Wkk = "Veh_Wkk";// 外廓宽 字符 4
    public static final String mVeh_Wkg = "Veh_Wkg";// 外廓高 字符 4
    public static final String mVeh_Hxnbc = "Veh_Hxnbc";// 货厢内部长 字符 5
    public static final String mVeh_Hxnbk = "Veh_Hxnbk";// 货厢内部宽 字符 4
    public static final String mVeh_Hxnbg = "Veh_Hxnbg";// 货厢内部高 字符 4
    public static final String mVeh_Zzl = "Veh_Zzl";// 总质量 字符 8
    public static final String mVeh_Edzzl = "Veh_Edzzl";// 额定载质量 字符 8
    public static final String mVeh_Zbzl = "Veh_Zbzl";// 整备质量 字符 8
    public static final String mVeh_Zzllyxs = "Veh_Zzllyxs";// 载质量利用系数 字符 30
    public static final String mVeh_Zqyzzl = "Veh_Zqyzzl";// 准牵引总质量 字符 8
    public static final String mVeh_Edzk = "Veh_Edzk";// 额定载客 字符 3
    public static final String mVeh_Bgcazzdyxzzl = "Veh_Bgcazzdyxzzl";// 半挂车鞍座最大允许总质量 字符 8
    public static final String mVeh_Jsszcrs = "Veh_Jsszcrs";// 驾驶室准乘人数 字符 3
    public static final String mVeh_Zgcs = "Veh_Zgcs";// 最高车速 字符 5
    public static final String mVeh_Clzzrq = "Veh_Clzzrq";// 车辆制造日期 字符 14 YYYY年MM月DD日
    public static final String mVeh_Bz = "Veh_Bz";// 备注 字符 300
    public static final String mVeh_Qybz = "Veh_Qybz";// 企业标准 字符 200 按“xxxx-xxxx《xxxx》”的格式，其中间部分为数字
    public static final String mVeh_Clscdwmc = "Veh_Clscdwmc";// 车辆生产单位名称 字符 64
    public static final String mVeh_Cpscdz = "Veh_Cpscdz";// 车辆生产单位地址 字符 70
    public static final String mVeh_Qyqtxx = "Veh_Qyqtxx";// 企业其它信息 字符 400 该项目内容需要回车换行的地方使用“%%”表示。
    public static final String mVeh_Zxzs = "Veh_Zxzs";// 转向轴个数 字符 1 取值为1、2、3，默认为1。供多转向轴的车辆填写。
    public static final String mVeh_Tmxx = "Veh_Tmxx";// 合格证条码信息 字符 1000
                                                      // 调用ViewBarcodeInfo并读取合格证条码后，通过该属性可获得条码信息，具体项目格式见附件一。
    public static final String mVeh_Jyw = "Veh_Jyw";// 校验信息 字符 255+
                                                    // 调用PrtParaTbl成功后，通过该属性可获得校验信息，供合格证上传用。其长度随合格证信息量发生变化,建议采用备注型等大容量数据类型存储。
    public static final String mVeh_Yh = "Veh_Yh";// 油耗 字符 30
    public static final String mVeh_Cddbj = "Veh_Cddbj";// 纯电动标记 字符 1
    public static final String mVeh_Cpggh = "Veh_Cpggh";// 公告号
    public static final String mVeh_Ggpc = "Veh_Ggpc";
    public static final String mVeh_Ggsxrq = "Veh_Ggsxrq";

    public static final String[] COLOR_CODE = new String[] { "A", "B", "C", "D", "E", "F", "G",
            "H", "I", "J", "K", "L", "M" };
    public static final String[] COLOR_NAME = new String[] { "银色", "红色", "白色", "紫色", "蓝色", "金色",
            "灰色", "绿色", "褐色", "黑色", "黄色", "其他色" };

    /**
     * 这个序列需要和html中函数的参数序列一致
     */
    public static final String[] paraSeq = new String[] { mVeh_Clztxx, mVeh_Zchgzbh, mVeh_Dphgzbh,
            mVeh_Fzrq, mVeh_Clzzqymc, mVeh_Qyid, mVeh_Clfl, mVeh_Clmc, mVeh_Clpp, mVeh_Clxh,
            mVeh_Csys, mVeh_Dpxh, mVeh_Dpid, mVeh_Clsbdh, mVeh_Cjh, mVeh_Fdjh, mVeh_Fdjxh,
            mVeh_Rlzl, mVeh_Pfbz, mVeh_Pl, mVeh_Gl, mVeh_Zxxs, mVeh_Qlj, mVeh_Hlj, mVeh_Lts,
            mVeh_Ltgg, mVeh_Gbthps, mVeh_Zj, mVeh_Zh, mVeh_Zs, mVeh_Wkc, mVeh_Wkk, mVeh_Wkg,
            mVeh_Hxnbc, mVeh_Hxnbk, mVeh_Hxnbg, mVeh_Zzl, mVeh_Edzzl, mVeh_Zbzl, mVeh_Zzllyxs,
            mVeh_Zqyzzl, mVeh_Edzk, mVeh_Bgcazzdyxzzl, mVeh_Jsszcrs, mVeh_Zgcs, mVeh_Clzzrq,
            mVeh_Bz, mVeh_Qybz, mVeh_Clscdwmc, mVeh_Cpscdz, mVeh_Qyqtxx, mVeh_Zxzs, mVeh_Tmxx,
            mVeh_Jyw, mVeh_Yh, mVeh_Cddbj, mVeh_Cpggh, mVeh_Ggpc, mVeh_Ggsxrq };

    private static final String mVeh_Dywym = null;

    private static final String mVeh_Stopbits = null;

    private static final String mVeh_Databits = null;

    private static final String mVeh_Parity = null;

    private static final String mVeh_Baud = null;

    private static final String mVeh_Connect = null;

    private static final String mVeh_PrintPosTop = null;

    private static final String mVeh_PrintPosLeft = null;

    private static final String mVeh_PrinterName = null;

    private static String mVeh_Zzbh;

    public static void print(Browser browser, DBObject dbo) {
        StringBuilder sb = new StringBuilder();
        sb.append("printVert(");

        for (int i = 0; i < paraSeq.length; i++) {
            Object value = dbo.get(paraSeq[i]);
            sb.append("\"");
            value = value == null ? "" : value;
            sb.append(value);
            sb.append("\"");
            if (i < paraSeq.length - 1) {
                sb.append(",");
            }
        }

        sb.append(");");
        // System.out.println(sb.toString());
        browser.execute(sb.toString());
    }

    public static void test(Browser browser) {
        browser.execute("showbar()");
    }

    /**
     * 检查输入的VIN是否合法
     * 
     * @param inputVin
     */
    public static boolean checkVIN(String vin) {
        // 17位字母和数字组成：LDC913L2240000023
        // 0-9,A-Z但是不能有字母O,I,Q
        String pattern = "[0-9ABCDEFGHJKLMNPQRSTUVWXYZ]{17}";
        return Utils.isPatternMatched(vin, pattern);
    }

    /**
     * 查询数据库字段名 意思 vin VIN erp_product_code 成品码 safety_components_vin 发动机号 manufacture_date 制造日期
     * 是否需要打印合格证 此字段暂无，待ERP确定后增加
     * 
     * @param vin
     * @return
     * @throws Exception
     */
    public static SQLRow getProductCode(String vin) throws Exception {

        if (debug) {
            Connection conn = DDB.getDefault().getConnection("MES_DB");
            if (conn == null) {
                String[] arr1 = new String[] { "LNBMDLAA0CU000319", "LNBMDLAA4CU000484",
                        "LNBMDLAA6CU000485", "LNBMDLAA1CU000572", "LNBMDLAA3CU000573" };
                String[] arr2 = new String[] { "LNBMDLAA7CU000480" };
                if (Utils.inArray(vin, arr1)) {
                    SQLRow row = new SQLRow(new String[] { FIELD_PRODUCT_CODE, FIELD_MFT_DATE,
                            FIELD_ENGINEE_NUM, "VIN" });
                    row.setValue(FIELD_ENGINEE_NUM, "BJ410A1C10D00129");
                    row.setValue(FIELD_MFT_DATE, "2012-12-21");
                    row.setValue(FIELD_PRODUCT_CODE, "88M321ACB01-U3F1");
                    row.setValue("VIN", vin);
                    return row;
                }
                if (Utils.inArray(vin, arr2)) {
                    SQLRow row = new SQLRow(new String[] { FIELD_PRODUCT_CODE, FIELD_MFT_DATE,
                            FIELD_ENGINEE_NUM, "VIN" });
                    row.setValue(FIELD_ENGINEE_NUM, "BJ413AC09D00042");
                    row.setValue(FIELD_MFT_DATE, "2012-12-26");
                    row.setValue(FIELD_PRODUCT_CODE, "88M333ACE01-U3G1");
                    row.setValue("VIN", vin);
                    return row;
                }
            }
        }

        SQLResult res = SQLUtil.SQL_QUERY(MES_DB, SQL_GET_PRODUCINFOR + vin + "'");
        if (res.size() == 0) {
            throw new Exception("MES数据库中没有VIN对应的成品记录。\nVIN:" + vin);
        }
        return res.getData().get(0);
    }

    public static DBObject getProductCodeInfo(String productCode) throws Exception {
        DBCollection c = DBActivator.getCollection(DB_NAME, COL_PRODUCTCODEINFO);
        DBObject query = new BasicDBObject().append(IVIMFields.E_02, productCode);
        DBObject data = c.findOne(query);
        if (data == null) {
            throw new Exception("在VIM数据库中无法获得成品码数据，可能是该成品码数据还未被同步" + "\n请自行手工同步后重试。\n成品码："
                    + productCode);
        }
        Object cocinfoId = data.get(IVIMFields.COC_ID);
        if (!(cocinfoId instanceof ObjectId)) {
            throw new Exception("在VIM数据库中无法获得成品码相关的车型一致性数据" + "\n请将该成品码绑定车型一致性数据后重试。\n成品码："
                    + productCode);
        }
        Object cfgInfoId = data.get(IVIMFields.CFG_ID);
        if (!(cfgInfoId instanceof ObjectId)) {
            throw new Exception("在VIM数据库中无法获得成品码相关的配置信息" + "\n请将该成品码绑定配置数据后重试。\n成品码：" + productCode);
        }

        return data;
    }

    public static DBObject getCOCInfo(DBObject productCodeData) throws Exception {
        DBCollection c = DBActivator.getCollection(DB_NAME, COL_COCINFO);
        ObjectId id = (ObjectId) productCodeData.get(IVIMFields.COC_ID);
        DBObject query = new BasicDBObject().append("_id", id);
        DBObject data = c.findOne(query);
        if (data == null) {
            throw new Exception("成品码绑定的车型一致性记录已不存在，可能是已被删除。\n请重新将该成品码绑定车型一致性数据后重试。\n成品码:"
                    + productCodeData.get(IVIMFields.E_02));
        }
        return data;
    }

    public static DBObject getConfInfo(DBObject productCodeData) throws Exception {
        DBCollection c = DBActivator.getCollection(DB_NAME, COL_CONFIGCODEINFO);
        ObjectId id = (ObjectId) productCodeData.get(IVIMFields.CFG_ID);
        DBObject query = new BasicDBObject().append("_id", id);
        DBObject data = c.findOne(query);
        if (data == null) {
            throw new Exception("成品码绑定的车型一致性记录已不存在，可能是已被删除。\n请重新将该成品码绑定车型一致性数据后重试。");
        }
        return data;
    }

    public static DBObject getCOCInfoById(String productId) throws Exception {
        DBCollection c = DBActivator.getCollection(DB_NAME, COL_COCINFO);
        DBObject o = new BasicDBObject().append(IVIMFields.D_02, productId);
        DBObject data = c.findOne(o);
        if (data == null) {
            throw new Exception("无法获得对应产品ID的车型一致性信息。\n请在车型一致性信息中检查对应产品ID的记录\n产品ID:" + productId);
        }
        return data;
    }

    public static DBObject getConfInfoById(String productId) throws Exception {
        DBCollection c = DBActivator.getCollection(DB_NAME, COL_CONFIGCODEINFO);
        DBObject o = new BasicDBObject().append(IVIMFields.D_02, productId);
        DBObject data = c.findOne(o);
        if (data == null) {
            throw new Exception("无法获得对应产品ID的配置信息。\n请在车型一致性信息中检查对应配置信息的记录\n产品ID:" + productId);
        }
        return data;
    }

    public static DataObjectEditorInput getCerfInput(DBObject cocData, DBObject confData,
            DBObject productCodeData, SQLRow mesRawData, IEditorSaveHandler saveHandler,
            String vin, boolean isDP) throws Exception {
        DataEditorConfigurator conf = (DataEditorConfigurator) UI.getEditorRegistry()
                .getConfigurator(CERT_EDITOR);
        DBCollection c = DBActivator.getCollection(DB_NAME, COL_CERF);
        DBObject dbObject = transferData(cocData, confData, productCodeData, mesRawData, vin, isDP);
        DataObject data = new DataObject(c, dbObject);
        DataObjectEditorInput editorInput = new DataObjectEditorInput(data, conf, saveHandler);
        return editorInput;
    }

    public static DBObject transferData(DBObject cocData, DBObject confData,
            DBObject productCodeData, SQLRow mesRawData, String vin, boolean isDP) throws Exception {
        BasicDBObject result = new BasicDBObject();
        // Veh_ClzzqymC F_0_1 车辆制造企业名称 映射
        result.put(mVeh_Clzzqymc, cocData.get(IVIMFields.F_0_1));
        // Veh_ClmC F_0_2_1 车辆名称 映射
        result.put(mVeh_Clmc, cocData.get(IVIMFields.F_0_2_1));
        // Veh_Clxh F_0_2C1 车辆型号 映射
        result.put(mVeh_Clxh, cocData.get(IVIMFields.F_0_2C1));
        // Veh_Dpxh CCC_04 底盘型号 映射
        result.put(mVeh_Dpxh, cocData.get(IVIMFields.CCC_04));

        // Veh_Csys F_38 车身颜色 从成品字典取
        if (!isDP) {
            String sn = mesRawData.getText(FIELD_PRODUCT_CODE);
            String colorCode = sn.substring(14, 15);
            String colorName = (String) cocData.get(IVIMFields.F_38);
            for (int i = 0; i < COLOR_CODE.length; i++) {
                if (COLOR_CODE[i].equalsIgnoreCase(colorCode)) {
                    colorName = COLOR_NAME[i];
                    break;
                }
            }
            result.put(mVeh_Csys, colorName);
        }

        // Veh_FDjh F_21a 发动机号 映射
        result.put(mVeh_Fdjh, cocData.get(IVIMFields.F_21a));
        // Veh_Rlzl F_25 燃料种类 映射
        result.put(mVeh_Rlzl, cocData.get(IVIMFields.F_25));
        // Veh_Gl C_01 功率 映射
        result.put(mVeh_Gl, cocData.get(IVIMFields.C_01));
        // Veh_Pl F_24 排量 映射
        result.put(mVeh_Pl, cocData.get(IVIMFields.F_24));
        // Veh_PFbz C_06 排放标准 映射
        result.put(mVeh_Pfbz, cocData.get(IVIMFields.C_06));
        // Veh_Yh C_03 油耗 映射
        result.put(mVeh_Yh, cocData.get(IVIMFields.C_03));
        // Veh_WkC F_6_1 外廓长 映射
        result.put(mVeh_Wkc, cocData.get(IVIMFields.F_6_1));
        // Veh_Wkk F_7_1 外廓宽 映射
        result.put(mVeh_Wkk, cocData.get(IVIMFields.F_7_1));
        // Veh_Wkg F_8 外廓高 映射
        result.put(mVeh_Wkg, cocData.get(IVIMFields.F_8));
        // Veh_HxnbC F_C7_1 货厢内部长 映射
        result.put(mVeh_Hxnbc, cocData.get(IVIMFields.F_C7_1));
        // Veh_Hxnbk F_C7_2 货厢内部宽 映射
        result.put(mVeh_Hxnbk, cocData.get(IVIMFields.F_C7_2));
        // Veh_Hxnbg F_C7_3 货厢内部高 映射
        result.put(mVeh_Hxnbg, cocData.get(IVIMFields.F_C7_3));
        // Veh_Gbthps F_C6 钢板弹簧片数 映射
        result.put(mVeh_Gbthps, cocData.get(IVIMFields.F_C6));
        // Veh_FDjxh F_C4 发动机型号 映射
        result.put(mVeh_Fdjxh, cocData.get(IVIMFields.F_C4));
        // Veh_Lts F_1_1a 轮胎数 映射
        result.put(mVeh_Lts, cocData.get(IVIMFields.F_1_1A));
        // Veh_Ltgg F_32A 轮胎规格 映射 轮胎规格合格证
        result.put(mVeh_Ltgg, cocData.get(IVIMFields.F_32A));
        // Veh_Qlj F_5a 前轮距 映射
        result.put(mVeh_Qlj, cocData.get(IVIMFields.F_5A));
        // Veh_Hlj F_5b 后轮距 映射
        result.put(mVeh_Hlj, cocData.get(IVIMFields.F_5B));
        // Veh_Zj F_3 轴距 映射
        result.put(mVeh_Zj, cocData.get(IVIMFields.F_3));
        // Veh_Zh F_14_2 轴荷 映射
        result.put(mVeh_Zh, cocData.get(IVIMFields.F_14_2));
        // Veh_Zs F_1 轴数 映射
        result.put(mVeh_Zs, cocData.get(IVIMFields.F_1));
        // Veh_Zxxs F_C34 转向形式 映射
        result.put(mVeh_Zxxs, cocData.get(IVIMFields.F_C34));
        // Veh_Zzl F_14_1 总质量 映射
        result.put(mVeh_Zzl, cocData.get(IVIMFields.F_14_1));
        // Veh_Zbzl C_08 整备质量 映射
        result.put(mVeh_Zbzl, cocData.get(IVIMFields.C_08));
        // Veh_EDzzl C_09 额定载质量 映射
        result.put(mVeh_Edzzl, cocData.get(IVIMFields.C_09));
        // Veh_Zzllyxs F_C5 载质量利用系数 映射
        result.put(mVeh_Zzllyxs, cocData.get(IVIMFields.F_C5));
        // Veh_Zqyzzl C_10 准牵引总质量 映射
        result.put(mVeh_Zqyzzl, cocData.get(IVIMFields.C_10));
        // Veh_BgCazzDyxzzl C_04 半挂车鞍座最大允许总质量 映射
        result.put(mVeh_Bgcazzdyxzzl, cocData.get(IVIMFields.C_04));
        // Veh_JsszCrs C_02 驾驶室准乘人数 映射
        result.put(mVeh_Jsszcrs, cocData.get(IVIMFields.C_02));
        // Veh_EDzk F_42_1 额定载客 映射
        result.put(mVeh_Edzk, cocData.get(IVIMFields.F_42_1));
        // Veh_ZgCs F_44 最高车速 映射
        result.put(mVeh_Zgcs, cocData.get(IVIMFields.F_44));
        // Veh_Clpp F_C0_2 车辆品牌 映射
        result.put(mVeh_Clpp, cocData.get(IVIMFields.F_C0_2));
        // Veh_ClsbDh F_0_6b
        result.put(mVeh_Clsbdh, vin);
        // Veh_DpiD C_12 底盘ID 映射
        result.put(mVeh_Dpid, cocData.get(IVIMFields.C_12));
        // Veh_Bz F_50 备注 映射 合格证备注
        result.put(mVeh_Bz, cocData.get(IVIMFields.C_11));
        // Veh_Clztxx C_23 车辆状态信息 值转换
        result.put(mVeh_Clztxx, cocData.get(IVIMFields.C_23));
        // Veh_ClFl C_22 车辆分类 映射
        result.put(mVeh_Clfl, cocData.get(IVIMFields.C_22));
        // Veh_Zxzs C_13 转向轴个数 映射
        result.put(mVeh_Zxzs, cocData.get(IVIMFields.C_13));
        // Veh_CDDbj C_21 纯电动标记 值转换
        result.put(mVeh_Cddbj, cocData.get(IVIMFields.C_21));
        // Veh_ClsCDwmC F_0_1 车辆生产单位名称 映射
        result.put(mVeh_Clscdwmc, cocData.get(IVIMFields.F_0_1));
        // Veh_CpsCDz D_04 车辆生产单位地址 映射
        result.put(mVeh_Cpscdz, cocData.get(IVIMFields.D_04));
        // Veh_QyiD C_14 企业ID 映射
        String companyId = (String) cocData.get(IVIMFields.C_14);
        result.put(mVeh_Qyid, companyId);
        // Veh_Qybz C_17 企业标准 映射
        result.put(mVeh_Qybz, cocData.get(IVIMFields.C_17));
        // Veh_Cpggh D_02 产品公告号 值转换 由公告信息获得,11位字符，其后串联配置序列号14位字符，共25位
        String productPublicId = (String) cocData.get(IVIMFields.D_02);
        // 配置序号
        String confid = (String) confData.get(IVIMFields.H_01);
        if (Utils.isNullOrEmpty(confid)) {
            confid = (String) confData.get(IVIMFields.H_02);
        }
        String string = productPublicId + confid;
        if (!debug && string.length() != 25) {
            throw new Exception("无法取得正确的产品公告号。\n值转换  由公告信息获得,11位字符，其后串联配置序列号14位字符，共25位");
        }
        result.put(mVeh_Cpggh, string);
        // Veh_GgpC D_18 公告批次 映射
        result.put(mVeh_Ggpc, cocData.get(IVIMFields.D_18));
        // Veh_Ggsxrq D_01 公告生效日期 映射
        Object value = cocData.get(IVIMFields.D_01);
        try {
            Date date;
            date = Utils.getDateValue(value, true);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            result.put(mVeh_Ggsxrq, sdf.format(date));
        } catch (Exception e) {
            throw new Exception("无法取得正确的公告生效日期。\n" + e.getMessage());
        }

        // Veh_ZChgzbh 整车合格证编号 返回 返回值4位企业代码+10位顺序号成功调用打印方法后可以通过该属性获得15位的整车合格证编号

        // 编辑时处理
        // DBCollection ids = DBActivator.getCollection(DB_NAME, "ids");
        // String seq = DBUtil.getIncreasedID(ids, "Veh_ZChgzbh", "0", 10);
        // string = companyId+seq;
        // if(string.length()!=14){
        // throw new Exception("无法取得正确的整车合格证编号。\n4位企业代码+10位顺序号");
        // }
        // result.put(mVeh_Zchgzbh, );

        // Veh_Dphgzbh 全项方式15位；底盘方式不填 级联
        // result.put(mVeh_Dphgzbh, cocData.get(IVIMFields.F_0_1));

        // Veh_Fzrq 发证日期 字符 14 YYYY年MM月DD日
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        string = sdf.format(new Date());
        result.put(mVeh_Fzrq, string);
        // Veh_Cjh vin vin
        result.put(mVeh_Cjh, "");
        // Veh_Clzzrq 车辆制造日期
        String mftDate = (String) mesRawData.getValue(FIELD_MFT_DATE);
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        Date _date = sdf2.parse(mftDate);
        string = sdf.format(_date);
        result.put(mVeh_Clzzrq, string);
        // Veh_Qyqtxx 公告中的其他
        result.put(mVeh_Qyqtxx, cocData.get(IVIMFields.C_18));

        // // Veh_Tmxx 条码
        // result.put(mVeh_Tmxx, cocData.get(IVIMFields.F_0_1));
        // Veh_Jyw 待测
        // result.put(mVeh_Jyw, cocData.get(IVIMFields.F_0_1));
        // Veh_PrinterName 待测
        result.put(mVeh_PrinterName, "<输入>");
        // Veh_PrintPosLeFt 待测
        result.put(mVeh_PrintPosLeft, "15");
        // Veh_PrintPosTop 待测
        result.put(mVeh_PrintPosTop, "15");
        // Veh_ConneCt 待测
        result.put(mVeh_Connect, "com1");
        // Veh_BauD 待测
        result.put(mVeh_Baud, "9600");
        // Veh_Parity 待测
        result.put(mVeh_Parity, "N");
        // Veh_Databits 待测
        result.put(mVeh_Databits, "8");
        // Veh_Stopbits 待测
        result.put(mVeh_Stopbits, "1");
        // Veh_Zzbh 待测
        result.put(mVeh_Zzbh, "<输入>");
        // Veh_Dywym 待测
        result.put(mVeh_Dywym, "");// 返回值，不填
        return result;
    }

}
