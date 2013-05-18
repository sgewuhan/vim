package com.sg.vim.datamodel.util;

import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.bson.types.ObjectId;
import org.eclipse.core.runtime.Assert;
import org.eclipse.rap.rwt.RWT;
import org.eclipse.rap.rwt.client.service.UrlLauncher;
import org.eclipse.swt.browser.Browser;

import com.mobnut.commons.util.Utils;
import com.mobnut.db.DBActivator;
import com.mobnut.db.utils.DBUtil;
import com.mobnut.portal.user.UserSessionContext;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.sg.sqldb.DDB;
import com.sg.sqldb.utility.SQLResult;
import com.sg.sqldb.utility.SQLRow;
import com.sg.sqldb.utility.SQLUtil;
import com.sg.ui.UI;
import com.sg.ui.UIUtils;
import com.sg.ui.model.DataObject;
import com.sg.ui.model.DataObjectEditorInput;
import com.sg.ui.part.editor.IEditorSaveHandler;
import com.sg.ui.registry.config.DataEditorConfigurator;
import com.sg.vim.datamodel.BasicInfo;
import com.sg.vim.datamodel.COCInfo;
import com.sg.vim.datamodel.DataModelActivator;
import com.sg.vim.datamodel.IVIMFields;
import com.sg.vim.datamodel.vidcservice.ArrayOfCertificateInfo;
import com.sg.vim.datamodel.vidcservice.ArrayOfString;
import com.sg.vim.datamodel.vidcservice.CertificateInfo;
import com.sg.vim.datamodel.vidcservice.CertificateRequestServiceSoap;
import com.sg.vim.datamodel.vidcservice.NameValuePair;
import com.sg.vim.datamodel.vidcservice.OperateResult;

public class VimUtils {

    public static boolean debug = false;

    public static String HARDWAREID;

    public static String HD_USER;

    public static boolean COC_REPRINT;

    public static boolean FL_REPRINT;

    private static final String MES_DB = "mes";

    private static final String SQL_GET_PRODUCINFOR = "select erp_product_code,safety_components_vin,manufacture_date "
            + "from bqyx_mes.mes_mp_erp_code_lot_view "
            + "where safety_components_vin is not null and manufacture_date is not null and vin ='";

    private final static String DPCERT_EDITOR = "com.sg.vim.print.editor.certificate2";

    private final static String QXCERT_EDITOR = "com.sg.vim.print.editor.certificate";

    private final static String COCPAPER_EDITOR = "com.sg.vim.print.editor.cocpaper";

    private static final String FUELLABEL_EDITOR = "com.sg.vim.print.editor.fuellabel";

    /**
     * 成品码
     */
    protected static final String FIELD_PRODUCT_CODE = "erp_product_code";
    /**
     * 发动机号
     */
    protected static final String FIELD_ENGINEE_NUM = "safety_components_vin";
    /**
     * 制造日期
     */
    protected static final String FIELD_MFT_DATE = "manufacture_date";

    // *******/

    public static final String[] COLOR_CODE = new String[] { "A", "B", "C", "D", "E", "F", "G",
            "H", "I", "J", "K", "L", "M" };
    public static final String[] COLOR_NAME = new String[] { "银色", "红色", "白色", "紫色", "蓝色", "金色",
            "灰色", "绿色", "褐色", "黑色", "黄色", "其他色" };

    /**
     * 这个序列需要和html中函数的参数序列一致
     */
    public static final String[] paraSeq = new String[] { IVIMFields.mVeh_Clztxx,
            IVIMFields.mVeh_Zchgzbh, IVIMFields.mVeh_Dphgzbh, IVIMFields.mVeh_Fzrq,
            IVIMFields.mVeh_Clzzqymc, IVIMFields.mVeh_Qyid, IVIMFields.mVeh_Clfl,
            IVIMFields.mVeh_Clmc, IVIMFields.mVeh_Clpp, IVIMFields.mVeh_Clxh, IVIMFields.mVeh_Csys,
            IVIMFields.mVeh_Dpxh, IVIMFields.mVeh_Dpid, IVIMFields.mVeh_Clsbdh,
            IVIMFields.mVeh_Cjh, IVIMFields.mVeh_Fdjh, IVIMFields.mVeh_Fdjxh, IVIMFields.mVeh_Rlzl,
            IVIMFields.mVeh_Pfbz, IVIMFields.mVeh_Pl, IVIMFields.mVeh_Gl, IVIMFields.mVeh_Zxxs,
            IVIMFields.mVeh_Qlj, IVIMFields.mVeh_Hlj, IVIMFields.mVeh_Lts, IVIMFields.mVeh_Ltgg,
            IVIMFields.mVeh_Gbthps, IVIMFields.mVeh_Zj, IVIMFields.mVeh_Zh, IVIMFields.mVeh_Zs,
            IVIMFields.mVeh_Wkc, IVIMFields.mVeh_Wkk, IVIMFields.mVeh_Wkg, IVIMFields.mVeh_Hxnbc,
            IVIMFields.mVeh_Hxnbk, IVIMFields.mVeh_Hxnbg, IVIMFields.mVeh_Zzl,
            IVIMFields.mVeh_Edzzl, IVIMFields.mVeh_Zbzl, IVIMFields.mVeh_Zzllyxs,
            IVIMFields.mVeh_Zqyzzl, IVIMFields.mVeh_Edzk, IVIMFields.mVeh_Bgcazzdyxzzl,
            IVIMFields.mVeh_Jsszcrs, IVIMFields.mVeh_Zgcs, IVIMFields.mVeh_Clzzrq,
            IVIMFields.mVeh_Bz, IVIMFields.mVeh_Qybz, IVIMFields.mVeh_Clscdwmc,
            IVIMFields.mVeh_Cpscdz, IVIMFields.mVeh_Qyqtxx, IVIMFields.mVeh_Zxzs,
            IVIMFields.mVeh_Yh, IVIMFields.mVeh_Cpggh, IVIMFields.mVeh_Ggpc,
            IVIMFields.mVeh_Ggsxrq, IVIMFields.mVeh_PrinterName, IVIMFields.mVeh_PrintPosLeft,
            IVIMFields.mVeh_PrintPosTop, IVIMFields.mVeh_Connect, IVIMFields.mVeh_Baud,
            IVIMFields.mVeh_Parity, IVIMFields.mVeh_Databits, IVIMFields.mVeh_Stopbits,
            IVIMFields.mVeh_Zzbh, IVIMFields.mVeh_Cddbj };

    public static void setValues(Browser browser, DBObject dbo) {
        StringBuilder sb = new StringBuilder();
        sb.append("setValues(");

        for (int i = 0; i < paraSeq.length; i++) {
            Object value = dbo.get(paraSeq[i]);
            value = value == null ? "" : value;
            value = value.toString().replaceAll("\n", "%%");
            value = value.toString().replaceAll("\r", "%%");
            sb.append("\"");
            sb.append(value);
            sb.append("\"");
            if (i < paraSeq.length - 1) {
                sb.append(",");
            }
        }

        sb.append(");");
        if (debug)
            System.out.println(sb.toString());
        browser.execute(sb.toString());
    }

    public static void print(Browser browser) {
        browser.execute("printCert()");
    }

    public static void rePrint(Browser browser) {
        browser.execute("printCert()");
    }

    public static void showBar(Browser browser) {
        if (!debug) {
            browser.execute("showBar()");
        } else {
            browser.execute("showBarDummy()");
        }
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
        DBCollection c = DBActivator.getCollection(IVIMFields.DB_NAME,
                IVIMFields.COL_PRODUCTCODEINFO);
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
        DBCollection c = DBActivator.getCollection(IVIMFields.DB_NAME, IVIMFields.COL_COCINFO);
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
        DBCollection c = DBActivator.getCollection(IVIMFields.DB_NAME,
                IVIMFields.COL_CONFIGCODEINFO);
        ObjectId id = (ObjectId) productCodeData.get(IVIMFields.CFG_ID);
        DBObject query = new BasicDBObject().append("_id", id);
        DBObject data = c.findOne(query);
        if (data == null) {
            throw new Exception("成品码绑定的车型一致性记录已不存在，可能是已被删除。\n请重新将该成品码绑定车型一致性数据后重试。");
        }
        return data;
    }

    public static DBObject getCOCInfoById(String productId) throws Exception {
        DBCollection c = DBActivator.getCollection(IVIMFields.DB_NAME, IVIMFields.COL_COCINFO);
        DBObject o = new BasicDBObject().append(IVIMFields.D_02, productId);
        DBObject data = c.findOne(o);
        if (data == null) {
            throw new Exception("无法获得对应产品ID的车型一致性信息。\n请在车型一致性信息中检查对应产品ID的记录\n产品ID:" + productId);
        }
        return data;
    }

    public static DBObject getConfInfoById(String productId) throws Exception {
        DBCollection c = DBActivator.getCollection(IVIMFields.DB_NAME,
                IVIMFields.COL_CONFIGCODEINFO);
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
                .getConfigurator(isDP ? DPCERT_EDITOR : QXCERT_EDITOR);
        DBCollection c = DBActivator.getCollection(IVIMFields.DB_NAME, IVIMFields.COL_CERF);
        DBObject dbObject = transferCerfData(cocData, confData, productCodeData, mesRawData, vin,
                isDP);
        DataObject data = new DataObject(c, dbObject);
        DataObjectEditorInput editorInput = new DataObjectEditorInput(data, conf, saveHandler);
        if (debug) {
            editorInput.setEditable(true);
        }
        return editorInput;
    }

    public static DataObjectEditorInput getCOCInput(DBObject cocData, DBObject confData,
            DBObject productCodeData, SQLRow mesRawData, IEditorSaveHandler saveHandler, String vin) {
        DataEditorConfigurator conf = (DataEditorConfigurator) UI.getEditorRegistry()
                .getConfigurator(COCPAPER_EDITOR);
        DBCollection c = DBActivator.getCollection(IVIMFields.DB_NAME, IVIMFields.COL_COCPAPER);
        DBObject dbObject = transferCOCData(cocData, confData, productCodeData, mesRawData, vin);
        DataObject data = new DataObject(c, dbObject);
        DataObjectEditorInput editorInput = new DataObjectEditorInput(data, conf, saveHandler);
        if (debug) {
            editorInput.setEditable(true);
        }
        return editorInput;
    }

    public static DataObjectEditorInput getFLInput(DBObject cocData, DBObject confData,
            DBObject productCodeData, SQLRow mesRawData, IEditorSaveHandler saveHandler, String vin) {
        DataEditorConfigurator conf = (DataEditorConfigurator) UI.getEditorRegistry()
                .getConfigurator(FUELLABEL_EDITOR);
        DBCollection c = DBActivator.getCollection(IVIMFields.DB_NAME, IVIMFields.COL_FUELABEL);
        DBObject dbObject = transferFuelLabelData(cocData, confData, productCodeData, mesRawData,
                vin);
        DataObject data = new DataObject(c, dbObject);
        DataObjectEditorInput editorInput = new DataObjectEditorInput(data, conf, saveHandler);
        if (debug) {
            editorInput.setEditable(true);
        }
        return editorInput;
    }

    private static DBObject transferFuelLabelData(DBObject cocData, DBObject confData,
            DBObject productCodeData, SQLRow mesRawData, String vin) {
        BasicDBObject result = new BasicDBObject();

        // f_0_2c1
        result.put(IVIMFields.F_0_2C1, cocData.get(IVIMFields.F_0_2C1));
        // f_c4
        result.put(IVIMFields.F_C4, cocData.get(IVIMFields.F_C4));
        // f_25
        result.put(IVIMFields.F_25, cocData.get(IVIMFields.F_25));
        // f_24
        result.put(IVIMFields.F_24, cocData.get(IVIMFields.F_24));
        // c_01
        result.put(IVIMFields.C_01, cocData.get(IVIMFields.C_01));
        // f_28
        result.put(IVIMFields.F_28, cocData.get(IVIMFields.F_28));
        // d_22
        result.put(IVIMFields.D_22, cocData.get(IVIMFields.D_22));
        // c_08
        result.put(IVIMFields.C_08, cocData.get(IVIMFields.C_08));
        result.put(IVIMFields.F_14_1, cocData.get(IVIMFields.F_14_1));
        // g_32
        result.put(IVIMFields.G_32, cocData.get(IVIMFields.G_32));
        // d_14
        result.put(IVIMFields.D_14, cocData.get(IVIMFields.D_14));
        // d_15
        result.put(IVIMFields.D_15, cocData.get(IVIMFields.D_15));
        // d_16
        result.put(IVIMFields.D_16, cocData.get(IVIMFields.D_16));
        // g_33
        result.put(IVIMFields.G_33, cocData.get(IVIMFields.G_33));
        // g_34 //取制造日期
        try {
            String mftDate = (String) mesRawData.getValue(FIELD_MFT_DATE);
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
            Date _date;
            _date = sdf2.parse(mftDate);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
            String mdate = sdf.format(_date);
            result.put(IVIMFields.G_34, mdate);
        } catch (ParseException e) {
        }
        // f_0_6b //取vin
        result.put(IVIMFields.F_0_6b, vin);

        return result;
    }

    private static DBObject transferCOCData(DBObject cocData, DBObject confData,
            DBObject productCodeData, SQLRow mesRawData, String vin) {
        BasicDBObject result = new BasicDBObject();

        // 复制cocData的值
        Iterator<String> iter1 = cocData.keySet().iterator();
        while (iter1.hasNext()) {
            String key = iter1.next();
            if (key.startsWith("_")) {
                continue;
            } else if (key.equals("photo")) {
                continue;
            } else if (key.equals("basicinfo_id")) {
                continue;
            }
            result.put(key, cocData.get(key));
        }

        // 处理轮距合并
        Object qlj = cocData.get(IVIMFields.F_5A);
        Object hlj = cocData.get(IVIMFields.F_5B);
        result.put(IVIMFields.F_5A_O, "" + qlj + "/" + hlj);

        // 处理4.1
        Object qzj = cocData.get(IVIMFields.F_4_1_1);
        Object hzj = cocData.get(IVIMFields.F_4_1_2);
        if (Utils.isNullOrEmptyString(qzj) && Utils.isNullOrEmptyString(hzj)) {
            result.put(IVIMFields.F_4_1_1_O, "" + qzj + "/" + hzj);
        }

        // 处理发动机编号

        // Veh_FDjh F_21a 发动机号 映射
        String code = (String) mesRawData.getValue("SAFETY_COMPONENTS_VIN");
        result.put(IVIMFields.F_21a, code.substring(code.length() - 9));

        // 处理排放
        Object f46_3 = cocData.get(IVIMFields.F_46_3);
        // 市区，Co2, 油耗
        // f_46_3_o11,f_46_3_o12
        // 市郊，Co2,油耗
        // f_46_3_o21,f_46_3_o22
        // 综合,Co2,油耗
        // f_46_3_o31,f_46_3_o32
        if (f46_3 instanceof BasicDBList) {
            Iterator<Object> iter = ((BasicDBList) f46_3).iterator();
            while (iter.hasNext()) {
                DBObject dbo = (DBObject) iter.next();
                if ("市区".equals(dbo.get("location"))) {
                    result.put(IVIMFields.F_46_3_o11, dbo.get("co2"));
                    result.put(IVIMFields.F_46_3_o12, dbo.get("fuelqty"));
                } else if ("市郊".equals(dbo.get("location"))) {
                    result.put(IVIMFields.F_46_3_o21, dbo.get("co2"));
                    result.put(IVIMFields.F_46_3_o22, dbo.get("fuelqty"));
                } else if ("综合".equals(dbo.get("location"))) {
                    result.put(IVIMFields.F_46_3_o31, dbo.get("co2"));
                    result.put(IVIMFields.F_46_3_o32, dbo.get("fuelqty"));
                }
            }
        }

        // 处理VIN
        result.put(IVIMFields.F_0_6b, vin);

        // 处理签发日期
        Object ccc03 = cocData.get(IVIMFields.CCC_03);
        if (ccc03 instanceof Date) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
            result.put(IVIMFields.CCC_03, sdf.format((Date) ccc03));
        }

        // 处理合格证日期
        // 1.根据查找合格证
        DBObject cert = getCertDataByVin(vin, "QX");
        if (cert != null) {
            Object fzrq = cert.get(IVIMFields.mVeh_Fzrq);
            result.put(IVIMFields.CCC_21, fzrq == null ? "" : fzrq.toString());

        }

        // 处理颜色
        String sn = mesRawData.getText(FIELD_PRODUCT_CODE);
        String colorCode = sn.substring(14, 15);
        String colorName = (String) cocData.get(IVIMFields.F_38);
        for (int i = 0; i < COLOR_CODE.length; i++) {
            if (COLOR_CODE[i].equalsIgnoreCase(colorCode)) {
                colorName = COLOR_NAME[i];
                break;
            }
        }
        result.put(IVIMFields.F_38, colorName);

        // 处理钢板簧，在成品码中取
        Object fc6 = productCodeData.get(IVIMFields.F_C6);
        result.put(IVIMFields.F_C6, fc6);

        return result;
    }

    public static DBObject transferCerfData(DBObject cocData, DBObject confData,
            DBObject productCodeData, SQLRow mesRawData, String vin, boolean isDP) throws Exception {
        BasicDBObject result = new BasicDBObject();
        // Veh_ClzzqymC F_0_1 车辆制造企业名称 映射
        result.put(IVIMFields.mVeh_Clzzqymc, cocData.get(IVIMFields.F_0_1));
        // Veh_ClmC F_0_2_1 车辆名称 映射
        result.put(IVIMFields.mVeh_Clmc, cocData.get(IVIMFields.F_0_2_1));
        // Veh_Clxh F_0_2C1 车辆型号 映射
        result.put(IVIMFields.mVeh_Clxh, cocData.get(IVIMFields.F_0_2C1));

        // Veh_Dpxh CCC_04 底盘型号 映射
        if (!isDP) {
            result.put(IVIMFields.mVeh_Dpxh, cocData.get(IVIMFields.CCC_04));
        }

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
            result.put(IVIMFields.mVeh_Csys, colorName);
        } else {
            String colorName = (String) cocData.get(IVIMFields.F_38);
            result.put(IVIMFields.mVeh_Csys, colorName);
        }

        // Veh_FDjh F_21a 发动机号 映射
        String code = (String) mesRawData.getValue("SAFETY_COMPONENTS_VIN");

        result.put(IVIMFields.mVeh_Fdjh, code.substring(code.length() - 9));
        // Veh_Rlzl F_25 燃料种类 映射
        result.put(IVIMFields.mVeh_Rlzl, cocData.get(IVIMFields.F_25));
        // Veh_Gl C_01 功率 映射
        result.put(IVIMFields.mVeh_Gl, cocData.get(IVIMFields.C_01));
        // Veh_Pl F_24 排量 映射
        result.put(IVIMFields.mVeh_Pl, cocData.get(IVIMFields.F_24));
        // Veh_PFbz C_06 排放标准 映射
        result.put(IVIMFields.mVeh_Pfbz, cocData.get(IVIMFields.C_06));
        // Veh_Yh C_03 油耗 映射
        result.put(IVIMFields.mVeh_Yh, cocData.get(IVIMFields.D_16));// 取C_03还是D_16?
        // Veh_WkC F_6_1 外廓长 映射
        result.put(IVIMFields.mVeh_Wkc, cocData.get(IVIMFields.F_6_1));
        // Veh_Wkk F_7_1 外廓宽 映射
        result.put(IVIMFields.mVeh_Wkk, cocData.get(IVIMFields.F_7_1));
        // Veh_Wkg F_8 外廓高 映射
        result.put(IVIMFields.mVeh_Wkg, cocData.get(IVIMFields.F_8));
        // Veh_HxnbC F_C7_1 货厢内部长 映射
        result.put(IVIMFields.mVeh_Hxnbc, cocData.get(IVIMFields.F_C7_1));
        // Veh_Hxnbk F_C7_2 货厢内部宽 映射
        result.put(IVIMFields.mVeh_Hxnbk, cocData.get(IVIMFields.F_C7_2));
        // Veh_Hxnbg F_C7_3 货厢内部高 映射
        result.put(IVIMFields.mVeh_Hxnbg, cocData.get(IVIMFields.F_C7_3));
        // Veh_Gbthps F_C6 钢板弹簧片数 映射
        result.put(IVIMFields.mVeh_Gbthps, cocData.get(IVIMFields.F_C6));
        // Veh_FDjxh F_C4 发动机型号 映射
        result.put(IVIMFields.mVeh_Fdjxh, cocData.get(IVIMFields.F_C4));
        // Veh_Lts F_1_1 轮胎数 映射
        result.put(IVIMFields.mVeh_Lts, cocData.get(IVIMFields.F_1_1));
        // Veh_Ltgg F_32A 轮胎规格 映射 轮胎规格合格证
        result.put(IVIMFields.mVeh_Ltgg, cocData.get(IVIMFields.F_32A));
        // Veh_Qlj F_5a 前轮距 映射
        result.put(IVIMFields.mVeh_Qlj, cocData.get(IVIMFields.F_5A));
        // Veh_Hlj F_5b 后轮距 映射
        result.put(IVIMFields.mVeh_Hlj, cocData.get(IVIMFields.F_5B));
        // Veh_Zj F_3 轴距 映射
        result.put(IVIMFields.mVeh_Zj, cocData.get(IVIMFields.F_3));
        // Veh_Zh F_14_2 轴荷 映射
        result.put(IVIMFields.mVeh_Zh, cocData.get(IVIMFields.F_14_2));
        // Veh_Zs F_1 轴数 映射
        result.put(IVIMFields.mVeh_Zs, cocData.get(IVIMFields.F_1));
        // Veh_Zxxs F_C34 转向形式 映射
        result.put(IVIMFields.mVeh_Zxxs, cocData.get(IVIMFields.F_C34));
        // Veh_Zzl F_14_1 总质量 映射
        result.put(IVIMFields.mVeh_Zzl, cocData.get(IVIMFields.F_14_1));
        // Veh_Zbzl C_08 整备质量 映射
        result.put(IVIMFields.mVeh_Zbzl, cocData.get(IVIMFields.C_08));
        // Veh_EDzzl C_09 额定载质量 映射
        result.put(IVIMFields.mVeh_Edzzl, cocData.get(IVIMFields.C_09));
        // Veh_Zzllyxs F_C5 载质量利用系数 映射
        result.put(IVIMFields.mVeh_Zzllyxs, cocData.get(IVIMFields.F_C5));
        // Veh_Zqyzzl C_10 准牵引总质量 映射
        result.put(IVIMFields.mVeh_Zqyzzl, cocData.get(IVIMFields.C_10));
        // Veh_BgCazzDyxzzl C_04 半挂车鞍座最大允许总质量 映射
        result.put(IVIMFields.mVeh_Bgcazzdyxzzl, cocData.get(IVIMFields.C_04));
        // Veh_JsszCrs C_02 驾驶室准乘人数 映射 全项不填，底盘必填
        Object c_02 = cocData.get(IVIMFields.C_02);
        if (isDP) {
            if (!debug && Utils.isNullOrEmptyString(c_02)) {
                throw new Exception("驾驶室准乘人数在底盘合格证数据中不可为空");
            }
            result.put(IVIMFields.mVeh_Jsszcrs, c_02);
        }
        // Veh_EDzk F_42_1 额定载客 映射
        Object f_42_1 = cocData.get(IVIMFields.F_42_1);
        if (!isDP) {
            if (!debug && Utils.isNullOrEmptyString(f_42_1)) {
                throw new Exception("额定载客数在全项合格证数据中不可为空");
            }
            result.put(IVIMFields.mVeh_Edzk, f_42_1);
        }
        // Veh_ZgCs F_44 最高车速 映射
        result.put(IVIMFields.mVeh_Zgcs, cocData.get(IVIMFields.F_44));
        // Veh_Clpp F_C0_2 车辆品牌 映射
        result.put(IVIMFields.mVeh_Clpp, cocData.get(IVIMFields.F_C0_2));
        // Veh_ClsbDh F_0_6b
        result.put(IVIMFields.mVeh_Clsbdh, vin);
        // Veh_DpiD C_12 底盘ID 映射
        result.put(IVIMFields.mVeh_Dpid, cocData.get(IVIMFields.C_12));
        // Veh_Bz F_50 备注 映射 合格证备注
        result.put(IVIMFields.mVeh_Bz, cocData.get(IVIMFields.C_11));
        // Veh_Clztxx C_23 车辆状态信息 值转换
        result.put(IVIMFields.mVeh_Clztxx, cocData.get(IVIMFields.C_23));
        // Veh_ClFl C_22 车辆分类 映射
        result.put(IVIMFields.mVeh_Clfl, cocData.get(IVIMFields.C_22));
        // Veh_Zxzs C_13 转向轴个数 映射
        result.put(IVIMFields.mVeh_Zxzs, cocData.get(IVIMFields.C_13));
        // Veh_CDDbj C_21 纯电动标记 值转换
        Object object = cocData.get(IVIMFields.C_21);
        if ("是".equals(object)) {
            result.put(IVIMFields.mVeh_Cddbj, "1");
        } else {
            result.put(IVIMFields.mVeh_Cddbj, "2");
        }
        // Veh_ClsCDwmC F_0_1 车辆生产单位名称 映射
        result.put(IVIMFields.mVeh_Clscdwmc, cocData.get(IVIMFields.F_0_1));
        // Veh_CpsCDz D_04 车辆生产单位地址 映射
        result.put(IVIMFields.mVeh_Cpscdz, cocData.get(IVIMFields.D_04));
        // Veh_QyiD C_14 企业ID 映射
        String companyId = (String) cocData.get(IVIMFields.C_14);
        result.put(IVIMFields.mVeh_Qyid, companyId + "0000");
        // Veh_Qybz C_17 企业标准 映射
        result.put(IVIMFields.mVeh_Qybz, cocData.get(IVIMFields.C_17));
        // Veh_Cpggh D_23 产品公告号 值转换 由公告信息获得,11位字符，其后串联配置序列号14位字符，共25位
        String productPublicId = (String) cocData.get(IVIMFields.D_23);
        // 配置序号
        String confid = (String) confData.get(IVIMFields.H_01);
        if (Utils.isNullOrEmpty(confid)) {
            confid = (String) confData.get(IVIMFields.H_02);
        }
        String string = productPublicId + confid;
        if (!debug && string.length() != 25) {
            throw new Exception("无法取得正确的产品公告号。\n值转换  由公告信息获得,11位字符，其后串联配置序列号14位字符，共25位");
        }
        result.put(IVIMFields.mVeh_Cpggh, string);
        result.put(IVIMFields.D_23, productPublicId);
        result.put(IVIMFields.H_01, confid);
        
        // Veh_GgpC D_18 公告批次 映射
        result.put(IVIMFields.mVeh_Ggpc, cocData.get(IVIMFields.D_18));
        // Veh_Ggsxrq D_01 公告生效日期 映射
        Object value = cocData.get(IVIMFields.D_01);
        try {
            Date date;
            date = Utils.getDateValue(value, true);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            result.put(IVIMFields.mVeh_Ggsxrq, sdf.format(date));
        } catch (Exception e) {
            throw new Exception("无法取得正确的公告生效日期。\n" + e.getMessage());
        }

        // Veh_ZChgzbh 整车合格证编号 返回 返回值4位企业代码+10位顺序号成功调用打印方法后可以通过该属性获得15位的整车合格证编号

        // 打印前时处理

        // DBCollection ids = DBActivator.getCollection(IVIMFields.DB_NAME, "ids");
        // String seq = DBUtil.getIncreasedID(ids, IVIMFields.SEQ_GZBH, "0", 10);
        // string = companyId + seq;
        // if (!debug && string.length() != 14) {
        // throw new Exception("无法取得正确的整车合格证编号。\n4位企业代码+10位顺序号");
        // }
        // result.put(IVIMFields.mVeh_Zchgzbh, string);

        // Veh_Dphgzbh 全项方式15位；底盘方式不填 级联
        // result.put(IVIMFields.mVeh_Dphgzbh, cocData.get(IVIMFields.F_0_1));

        // Veh_Fzrq 发证日期 字符 14 YYYY年MM月DD日
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        string = sdf.format(new Date());
        result.put(IVIMFields.mVeh_Fzrq, string);
        // Veh_Cjh vin vin
        result.put(IVIMFields.mVeh_Cjh, "");
        // Veh_Clzzrq 车辆制造日期
        String mftDate = (String) mesRawData.getValue(FIELD_MFT_DATE);
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        Date _date = sdf2.parse(mftDate);
        string = sdf.format(_date);
        result.put(IVIMFields.mVeh_Clzzrq, string);
        // Veh_Qyqtxx 公告中的其他
        result.put(IVIMFields.mVeh_Qyqtxx, cocData.get(IVIMFields.C_18));

        // // Veh_Tmxx 条码 不填
        // result.put(IVIMFields.mVeh_Tmxx, cocData.get(IVIMFields.F_0_1));
        // Veh_Jyw 待测 校验不填
        // result.put(IVIMFields.mVeh_Jyw, cocData.get(IVIMFields.F_0_1));
        // Veh_PrinterName 待测
        result.put(IVIMFields.mVeh_PrinterName, "");
        // Veh_PrintPosLeFt 待测
        result.put(IVIMFields.mVeh_PrintPosLeft, "15");
        // Veh_PrintPosTop 待测
        result.put(IVIMFields.mVeh_PrintPosTop, "15");
        // Veh_ConneCt 待测
        result.put(IVIMFields.mVeh_Connect, "com1");
        // Veh_BauD 待测
        result.put(IVIMFields.mVeh_Baud, "9600");
        // Veh_Parity 待测
        result.put(IVIMFields.mVeh_Parity, "N");
        // Veh_Databits 待测
        result.put(IVIMFields.mVeh_Databits, "8");
        // Veh_Stopbits 待测
        result.put(IVIMFields.mVeh_Stopbits, "1");

        // Veh_Zzbh 纸张编号
        // *****************************输入的值并不是要传递到合格证的值，同时在字段展现时也作了相应的处理
        // 比如，输入110，input中的值还是110，但是显示为000110，同时需要在传递到合格证打印OCX的值也需要改变为000110
        // 参见doTransferBeforeInvokePrint()
        // result.put(IVIMFields.mVeh_Zzbh, "");

        // Veh_Dywym 待测
        // result.put(IVIMFields.mVeh_Dywym, "");// 返回值，不填

        /** 以下是打印不需要的参数，但是在上传时需要的参数 **/
        result.put(IVIMFields.mVeh__Hzdczfs, cocData.get(IVIMFields.C_15));
        result.put(IVIMFields.mVeh__Hzdfs, cocData.get(IVIMFields.C_16));
        result.put(IVIMFields.mVeh__Qzdczfs, cocData.get(IVIMFields.C_19));
        result.put(IVIMFields.mVeh__Qzdfs, cocData.get(IVIMFields.C_20));
        result.put(IVIMFields.mVeh__Pzxlh, confid);
        // 完整合格证编号需要在打印后传递

        return result;
    }

    public static void uploadCert(List<DBObject> certList) throws Exception {
        CertificateRequestServiceSoap vidService = DataModelActivator.getVIDCService();
        ArrayOfCertificateInfo cis = new ArrayOfCertificateInfo();

        for (int i = 0; i < certList.size(); i++) {
            CertificateInfo certificateInfo = getCertificateInfo(certList.get(i));
            cis.getCertificateInfo().add(certificateInfo);
        }

        OperateResult r = vidService.uploadInsertEnt(cis);
        int rCode = r.getResultCode();
        if (rCode == 1) {
            throw new Exception(getResultMessage(r));
        }
    }

    static String getResultMessage(OperateResult oResult) {
        StringBuffer sb = new StringBuffer();

        sb.append(String.format("操作结果:%s\r\n", oResult.getResultCode()));

        for (NameValuePair nvp : oResult.getResultDetail().getNameValuePair()) {
            sb.append(String.format("%s:%s\r\n", nvp.getName(), nvp.getValue()));
        }
        return sb.toString();
    }

    public static void uploadCert2(List<DBObject> certList, String memo) throws Exception {
        CertificateRequestServiceSoap vidService = DataModelActivator.getVIDCService();
        ArrayOfCertificateInfo cis = new ArrayOfCertificateInfo();

        for (int i = 0; i < certList.size(); i++) {
            CertificateInfo certificateInfo = getCertificateInfo(certList.get(i));
            cis.getCertificateInfo().add(certificateInfo);
        }

        OperateResult r = vidService.uploadOverTimeEnt(cis, memo);
        int rCode = r.getResultCode();
        if (rCode == 1) {
            throw new Exception(getResultMessage(r));
        }
    }

    public static void updateCert(List<DBObject> certList, String memo) throws Exception {
        CertificateRequestServiceSoap vidService = DataModelActivator.getVIDCService();
        ArrayOfCertificateInfo cis = new ArrayOfCertificateInfo();

        for (int i = 0; i < certList.size(); i++) {
            CertificateInfo certificateInfo = getCertificateInfo(certList.get(i));
            cis.getCertificateInfo().add(certificateInfo);
        }

        OperateResult r = vidService.uploadUpdateEnt(cis, memo);
        int rCode = r.getResultCode();
        if (rCode == 1) {
            throw new Exception(getResultMessage(r));
        }
    }

    public static void deleteCert(List<String> certNumberList, String memo) throws Exception {
        CertificateRequestServiceSoap vidService = DataModelActivator.getVIDCService();
        ArrayOfString wzhgzbhs = new ArrayOfString();
        for (int i = 0; i < certNumberList.size(); i++) {
            wzhgzbhs.getString().add(certNumberList.get(i));
        }

        OperateResult r = vidService.uploadDeleteEnt(wzhgzbhs, memo);
        int rCode = r.getResultCode();
        if (rCode == 1) {
            throw new Exception(getResultMessage(r));
        }
    }

    private static CertificateInfo getCertificateInfo(DBObject data) throws Exception {
        return getCertificateInfo(data, false);
    }

    private static CertificateInfo getCertificateInfo(DBObject data, boolean isUpdate)
            throws Exception {
        CertificateInfo info = new CertificateInfo();

        // 序号
        // 属性
        // 中文名称
        // 数据类型
        //
        // （soap描述）
        // 说明
        //
        // 1
        // VEHICLE_STATUS
        // 状态
        // s:string
        // 系统生成，无需操作，状态说明见表六
        //
        // 2
        // CLIENT_HARDWARE_INFO
        // 上传主机硬件信息
        // s:string
        Assert.isNotNull(HARDWAREID, "HARDWAREID不可为空，请检查vim.properties文件是否设置了属性vidc.hardware");
        info.setCLIENTHARDWAREINFO(HARDWAREID);
        //
        //
        // 3
        // CREATETIME
        // 上传日期
        // s:dateTime
        // 系统生成，无需操作。
        //
        // 4
        // FEEDBACK_TIME
        // 公安反馈时间
        // s:dateTime
        // 系统生成，无需操作。
        //
        // 5
        // H_ID
        // 反馈码
        // s:string
        // 系统生成，无需操作。
        //
        // 6
        // HD_HOST
        // 上传主机地址
        // s:string
        // 系统获取，无需操作。
        //
        // 7
        // HD_USER
        // 企业用户名
        // s:string
        //
        Assert.isNotNull(HD_USER, "HD_USER不可为空，请检查vim.properties文件是否设置了属性vidc.user");
        info.setHDUSER(HD_USER);
        //
        // 8
        // UKEY
        // U盾标识
        // s:string
        // 系统自动获取，要求上传主机上已经插入U盾。
        //
        // 9
        // UPDATETIME
        // 更新日期
        // s:dateTime
        // 系统生成，无需操作。
        //
        // 10
        // UPSEND_TAG
        // 上传标记
        // s:string
        // 系统生成，无需操作。
        //
        // 11
        // VERCODE
        // 校验码
        // s:string
        // 校验码，由打印接口生成
        String value = (String) data.get(IVIMFields.mVeh_Jyw);
        // Assert.isNotNull(value, "打印校验码不可为空");
        info.setVERCODE(value);

        // 12
        // VERSION
        // 上传系统版本信息
        // s:string
        // 系统生成，无需操作。
        //
        // 13
        // CDDBJ
        // 纯电动标记
        // s:string
        //
        value = (String) data.get(IVIMFields.mVeh_Cddbj);
        // Assert.isNotNull(value, "纯电动标记不可为空");
        info.setCDDBJ(value);
        //
        // 14
        // CLLX
        // 车辆类型
        // s:string
        //
        value = (String) data.get(IVIMFields.mVeh_Clfl);
        // Assert.isNotNull(value, "车辆类型不可为空");
        info.setCLLX(value);

        //
        // 15
        // CLSCDWMC
        // 车辆生产单位名称
        // s:string
        //
        value = (String) data.get(IVIMFields.mVeh_Clscdwmc);
        // Assert.isNotNull(value, "车辆生产单位名称不可为空");
        info.setCLSCDWMC(value);
        //
        // 16
        // CLZTXX
        // 车辆状态信息
        // s:string
        //
        value = (String) data.get(IVIMFields.mVeh_Clztxx);
        // Assert.isNotNull(value, "车辆状态信息不可为空");
        info.setCLZTXX(value);
        //
        // 17
        // CZRQ
        // 操作日期
        // s:string
        //
        GregorianCalendar nowGregorianCalendar = new GregorianCalendar();
        XMLGregorianCalendar xmlDatetime = DatatypeFactory.newInstance().newXMLGregorianCalendar(
                nowGregorianCalendar);
        info.setCZRQ(xmlDatetime); // 操作日期

        //
        // 18
        // DYWYM
        // 打印唯一码
        // s:string
        // 由打印接口生成
        //
        value = (String) data.get(IVIMFields.mVeh_Dywym);
        // Assert.isNotNull(value, "打印唯一码不可为空");
        info.setDYWYM(value);
        // 19
        // QYID
        // 企业编号
        // s:string
        //
        value = (String) data.get(IVIMFields.mVeh_Qyid);
        // Assert.isNotNull(value, "企业编号不可为空");
        info.setQYID(value);
        //
        // 20
        // YH
        // 油耗
        // s:string
        //
        value = (String) data.get(IVIMFields.mVeh_Yh);
        // Assert.isNotNull(value, "油耗不可为空");
        info.setYH(value);
        //
        // 21
        // ZCHGZBH
        // 整车合格证编号
        // s:string
        //
        value = (String) data.get(IVIMFields.mVeh_Zchgzbh);
        // Assert.isNotNull(value, "整车合格证编号不可为空");
        info.setZCHGZBH(value);
        //
        // 22
        // ZXZS
        // 转向轴数
        // s:string
        //
        value = (String) data.get(IVIMFields.mVeh_Zxzs);
        // Assert.isNotNull(value, "转向轴数不可为空");
        info.setZXZS(value);
        //
        // 23
        // ZZBH
        // 纸张编号
        // s:string
        //
        value = (String) data.get(IVIMFields.mVeh_Zzbh);
        // Assert.isNotNull(value, "纸张编号不可为空");
        info.setZZBH(value);
        //
        // 24
        // BGCAZZDYXZZL
        // 外挂车鞍座最大允许总质量
        // s:string
        //
        value = (String) data.get(IVIMFields.mVeh_Bgcazzdyxzzl);
        // Assert.isNotNull(value, "外挂车鞍座最大允许总质量不可为空");
        info.setBGCAZZDYXZZL(value);
        //
        // 25
        // BZ
        // 备注
        // s:string
        //
        value = (String) data.get(IVIMFields.mVeh_Bz);
        // Assert.isNotNull(value, "备注不可为空");
        info.setBZ(value);
        //
        // 26
        // CJH
        // 车架号
        // s:string
        //
        value = (String) data.get(IVIMFields.mVeh_Cjh);
        // Assert.isNotNull(value, "车架号不可为空");
        info.setCJH(value);
        //
        // 27
        // CLMC
        // 车辆名称
        // s:string
        //
        value = (String) data.get(IVIMFields.mVeh_Clmc);
        // Assert.isNotNull(value, "车辆名称不可为空");
        info.setCLMC(value);
        //
        // 28
        // CLPP
        // 车辆品牌
        // s:string
        //
        value = (String) data.get(IVIMFields.mVeh_Clpp);
        // Assert.isNotNull(value, "车辆品牌不可为空");
        info.setCLPP(value);
        //
        // 29
        // CLSBDH
        // 车辆识别代号
        // s:string
        //
        value = (String) data.get(IVIMFields.mVeh_Clsbdh);
        // Assert.isNotNull(value, "车辆识别代号不可为空");
        info.setCLSBDH(value);
        //
        // 30
        // CLXH
        // 车辆型号
        // s:string
        //
        value = (String) data.get(IVIMFields.mVeh_Clxh);
        // Assert.isNotNull(value, "车辆型号不可为空");
        info.setCLXH(value);
        //
        // 31
        // CLZZQYMC
        // 车辆制造企业名称
        // s:string
        //
        value = (String) data.get(IVIMFields.mVeh_Clzzqymc);
        // Assert.isNotNull(value, "车辆制造企业名称不可为空");
        info.setCLZZQYMC(value);
        //
        // 32
        // CLZZRQ
        // 车辆制造日期
        // s:dateTime
        //
        value = (String) data.get(IVIMFields.mVeh_Clzzrq);
        // Assert.isNotNull(value, "车辆制造日期");
        Date dValue = new SimpleDateFormat("yyyy年MM月dd日").parse(value);
        nowGregorianCalendar = new GregorianCalendar();
        nowGregorianCalendar.setTime(dValue);
        xmlDatetime = DatatypeFactory.newInstance().newXMLGregorianCalendar(nowGregorianCalendar);
        info.setCLZZRQ(xmlDatetime);
        //
        // 33
        // CPH
        // 产品号
        // s:string
        //
        value = (String) data.get(IVIMFields.D_23);
        // Assert.isNotNull(value, "产品号不可为空");
        info.setCPH(value);
        //
        // 34
        // CPSCDZ
        // 产品生产地址
        // s:string
        //
        value = (String) data.get(IVIMFields.mVeh_Cpscdz);
        // Assert.isNotNull(value, "产品生产地址不可为空");
        info.setCPSCDZ(value);
        //
        // 35
        // CSYS
        // 车身颜色
        // s:string
        //
        value = (String) data.get(IVIMFields.mVeh_Csys);
        // Assert.isNotNull(value, "车身颜色不可为空");
        info.setCSYS(value);
        //
        // 36
        // DPHGZBH
        // 底盘合格证编号
        // s:string
        //
        value = (String) data.get(IVIMFields.mVeh_Dphgzbh);
        // Assert.isNotNull(value, "底盘合格证编号不可为空");
        info.setDPHGZBH(value);
        //
        // 37
        // DPID
        // 底盘ID
        // s:string
        //
        value = (String) data.get(IVIMFields.mVeh_Dpid);
        // Assert.isNotNull(value, "底盘ID不可为空");
        info.setDPID(value);
        //
        // 38
        // DPXH
        // 底盘型号
        // s:string
        //
        value = (String) data.get(IVIMFields.mVeh_Dpxh);
        // Assert.isNotNull(value, "底盘型号不可为空");
        info.setDPXH(value);
        //
        // 39
        // EDZK
        // 额定载客
        // s:string
        //
        value = (String) data.get(IVIMFields.mVeh_Edzk);
        // Assert.isNotNull(value, "额定载客不可为空");
        info.setEDZK(value);
        //
        // 40
        // EDZZL
        // 额定载质量
        // s:string
        //
        value = (String) data.get(IVIMFields.mVeh_Edzzl);
        // Assert.isNotNull(value, "额定载质量不可为空");
        info.setEDZZL(value);
        //
        // 41
        // FDJH
        // 发动机号
        // s:string
        //
        value = (String) data.get(IVIMFields.mVeh_Fdjh);
        // Assert.isNotNull(value, "发动机号不可为空");
        info.setFDJH(value);
        //
        // 42
        // FDJXH
        // 发动机型号
        // s:string
        //
        value = (String) data.get(IVIMFields.mVeh_Fdjxh);
        // Assert.isNotNull(value, "发动机型号不可为空");
        info.setFDJXH(value);
        //
        // 43
        // FZRQ
        // 发证日期
        // s:dateTime
        //
        value = (String) data.get(IVIMFields.mVeh_Fzrq);
        // Assert.isNotNull(value, "发证日期不可为空");
        dValue = new SimpleDateFormat("yyyy年MM月dd日").parse(value);
        nowGregorianCalendar = new GregorianCalendar();
        nowGregorianCalendar.setTime(dValue);
        xmlDatetime = DatatypeFactory.newInstance().newXMLGregorianCalendar(nowGregorianCalendar);
        info.setFZRQ(xmlDatetime);
        //
        // 44
        // GBTHPS
        // 钢板弹簧片数
        // s:string
        //
        value = (String) data.get(IVIMFields.mVeh_Gbthps);
        // Assert.isNotNull(value, "钢板弹簧片数不可为空");
        info.setGBTHPS(value);
        //
        // 45
        // GGSXRQ
        // 公告生效日期
        // s:dateTime
        //
        value = (String) data.get(IVIMFields.mVeh_Ggsxrq);
        // Assert.isNotNull(value, "公告生效日期不可为空");
        dValue = new SimpleDateFormat("yyyy-MM-dd").parse(value);
        nowGregorianCalendar = new GregorianCalendar();
        nowGregorianCalendar.setTime(dValue);
        xmlDatetime = DatatypeFactory.newInstance().newXMLGregorianCalendar(nowGregorianCalendar);
        info.setGGSXRQ(xmlDatetime);
        //
        // 46
        // GL
        // 功率
        // s:string
        //
        value = (String) data.get(IVIMFields.mVeh_Gl);
        // Assert.isNotNull(value, "功率不可为空");
        info.setGL(value);
        //
        // 47
        // HLJ
        // 后轮距
        // s:string
        //
        value = (String) data.get(IVIMFields.mVeh_Hlj);
        // Assert.isNotNull(value, "后轮距不可为空");
        info.setHLJ(value);
        //
        // 48
        // HXNBC
        // 货箱内部长
        // s:string
        //
        value = (String) data.get(IVIMFields.mVeh_Hxnbc);
        // Assert.isNotNull(value, "货箱内部长不可为空");
        info.setHXNBC(value);
        //
        // 49
        // HXNBG
        // 货箱内部高
        // s:string
        //
        value = (String) data.get(IVIMFields.mVeh_Hxnbg);
        // Assert.isNotNull(value, "货箱内部高");
        info.setHXNBG(value);
        //
        // 50
        // HXNBK
        // 货箱内部宽
        // s:string
        //
        value = (String) data.get(IVIMFields.mVeh_Hxnbk);
        // Assert.isNotNull(value, "货箱内部宽");
        info.setHXNBK(value);
        //
        // 51
        // HZDCZFS
        // 后制动操作方式
        // s:string
        //
        value = (String) data.get(IVIMFields.mVeh__Hzdczfs);
        // Assert.isNotNull(value, "后制动操作方式");
        info.setHZDCZFS(value);
        //
        // 52
        // HZDFS
        // 后制动方式
        // s:string
        //
        value = (String) data.get(IVIMFields.mVeh__Hzdfs);
        // Assert.isNotNull(value, "后制动方式");
        info.setHZDFS(value);
        //
        // 53
        // JSSZCRS
        // 驾驶室准乘人数
        // s:string
        //
        value = (String) data.get(IVIMFields.mVeh_Clxh);
        // Assert.isNotNull(value, "驾驶室准乘人数不可为空");
        info.setJSSZCRS(value);
        //
        // 54
        // LTGG
        // 轮胎规格
        // s:string
        //
        value = (String) data.get(IVIMFields.mVeh_Ltgg);
        // Assert.isNotNull(value, "轮胎规格不可为空");
        info.setLTGG(value);
        //
        // 55
        // LTS
        // 轮胎数
        // s:string
        //
        value = (String) data.get(IVIMFields.mVeh_Lts);
        // Assert.isNotNull(value, "轮胎数不可为空");
        info.setLTS(value);
        //
        // 56
        // PC
        // 批次
        // s:string
        //
        value = (String) data.get(IVIMFields.mVeh_Ggpc);
        // Assert.isNotNull(value, "批次不可为空");
        info.setPC(value);
        //
        // 57
        // PFBZ
        // 排放标准
        // s:string
        //
        value = (String) data.get(IVIMFields.mVeh_Pfbz);
        // Assert.isNotNull(value, "排放标准不可为空");
        info.setPFBZ(value);
        //
        // 58
        // PL
        // 排量
        // s:string
        //
        value = (String) data.get(IVIMFields.mVeh_Pl);
        // Assert.isNotNull(value, "排量不可为空");
        info.setPL(value);
        //
        // 59
        // QLJ
        // 前轮距
        // s:string
        //
        value = (String) data.get(IVIMFields.mVeh_Qlj);
        // Assert.isNotNull(value, "前轮距不可为空");
        info.setQLJ(value);
        //
        // 60
        // QYBZ
        // 企业标准
        // s:string
        //
        value = (String) data.get(IVIMFields.mVeh_Qybz);
        // Assert.isNotNull(value, " 企业标准不可为空");
        info.setQYBZ(value);
        //
        // 61
        // QYQTXX
        // 企业其他信息
        // s:string
        //
        value = (String) data.get(IVIMFields.mVeh_Qyqtxx);
        // Assert.isNotNull(value, "企业其他信息不可为空");
        info.setQYQTXX(value);
        //
        // 62
        // QZDCZFS
        // 前制动操作方式
        // s:string
        //
        value = (String) data.get(IVIMFields.mVeh__Qzdczfs);
        // Assert.isNotNull(value, "前制动操作方式不可为空");
        info.setQZDCZFS(value);
        //
        // 63
        // QZDFS
        // 前制动方式
        // s:string
        //
        value = (String) data.get(IVIMFields.mVeh__Qzdfs);
        // Assert.isNotNull(value, "前制动方式不可为空");
        info.setQZDFS(value);
        //
        // 64
        // RLZL
        // 燃料种类
        // s:string
        //
        value = (String) data.get(IVIMFields.mVeh_Rlzl);
        // Assert.isNotNull(value, "燃料种类不可为空");
        info.setRLZL(value);
        //
        // 65
        // WKC
        // 外廓长
        // s:string
        //
        value = (String) data.get(IVIMFields.mVeh_Wkc);
        // Assert.isNotNull(value, "外廓长不可为空");
        info.setWKC(value);
        //
        // 66
        // WKG
        // 外廓高
        // s:string
        //
        value = (String) data.get(IVIMFields.mVeh_Wkg);
        // Assert.isNotNull(value, "外廓高不可为空");
        info.setWKG(value);
        //
        // 67
        // WKK
        // 外廓宽
        // s:string
        //
        value = (String) data.get(IVIMFields.mVeh_Wkk);
        // Assert.isNotNull(value, "外廓宽不可为空");
        info.setWKK(value);
        //
        // 68
        // ZBZL
        // 整备质量
        // s:string
        //
        value = (String) data.get(IVIMFields.mVeh_Zbzl);
        // Assert.isNotNull(value, "整备质量不可为空");
        info.setZBZL(value);
        //
        // 69
        // ZGCS
        // 最高车速
        // s:string
        //
        value = (String) data.get(IVIMFields.mVeh_Zgcs);
        // Assert.isNotNull(value, "最高车速不可为空");
        info.setZGCS(value);
        //
        // 70
        // ZH
        // 轴荷
        // s:string
        //
        value = (String) data.get(IVIMFields.mVeh_Zh);
        // Assert.isNotNull(value, "轴荷不可为空");
        info.setZH(value);
        //
        // 71
        // ZJ
        // 轴距
        // s:string
        //
        value = (String) data.get(IVIMFields.mVeh_Zj);
        // Assert.isNotNull(value, "轴距不可为空");
        info.setZJ(value);
        //
        // 72
        // ZQYZZL
        // 准牵引总质量
        // s:string
        //
        value = (String) data.get(IVIMFields.mVeh_Zqyzzl);
        // Assert.isNotNull(value, "准牵引总质量不可为空");
        info.setZQYZZL(value);
        //
        // 73
        // ZS
        // 轴数
        // s:string
        //
        value = (String) data.get(IVIMFields.mVeh_Zs);
        // Assert.isNotNull(value, "轴数不可为空");
        info.setZS(value);
        //
        // 74
        // ZXXS
        // 转向形式
        // s:string
        //
        value = (String) data.get(IVIMFields.mVeh_Zxxs);
        // Assert.isNotNull(value, "转向形式不可为空");
        info.setZXXS(value);
        //
        // 75
        // ZZL
        // 总质量
        // s:string
        //
        value = (String) data.get(IVIMFields.mVeh_Zzl);
        // Assert.isNotNull(value, "总质量不可为空");
        info.setZZL(value);
        //
        // 76
        // ZZLLYXS
        // 载质量利用系数
        // s:string
        //
        value = (String) data.get(IVIMFields.mVeh_Zzllyxs);
        // Assert.isNotNull(value, "载质量利用系数不可为空");
        info.setZZLLYXS(value);
        //
        // 77
        // WZHGZBH
        // 完整合格证编号
        // s:string
        //
        value = (String) data.get(IVIMFields.mVeh__Wzghzbh);
        // Assert.isNotNull(value, "完整合格证编号不可为空");
        info.setWZHGZBH(value);
        //
        // 78
        // PZXLH
        // 配置序列号
        // s:string

        value = (String) data.get(IVIMFields.mVeh__Pzxlh);
        // Assert.isNotNull(value, "配置序列号不可为空");
        info.setPZXLH(value);
        return info;
    }

    public static String getLifecycle(String vin, String collectionName) {
        DBCollection col = DBActivator.getCollection("appportal", collectionName);
        DBObject d = col.findOne(new BasicDBObject().append(IVIMFields.mVeh_Clsbdh, vin),
                new BasicDBObject().append(IVIMFields.LIFECYCLE, 1));
        if (d != null) {
            return (String) d.get(IVIMFields.LIFECYCLE);
        }
        return null;
    }

    public static DBObject getCertDataByVin(String vin, String clztxx) {
        DBCollection col = DBActivator.getCollection("appportal", IVIMFields.COL_CERF);
        return col.findOne(new BasicDBObject().append(IVIMFields.mVeh_Clsbdh, vin).append(
                IVIMFields.mVeh_Clztxx, clztxx));
    }

    public static DBObject getCOCPaperDataByVin(String vin) {
        DBCollection col = DBActivator.getCollection("appportal", IVIMFields.COL_COCPAPER);
        return col.findOne(new BasicDBObject().append(IVIMFields.F_0_6b, vin));
    }

    public static DBObject getFuelLabelByVin(String vin) {
        DBCollection col = DBActivator.getCollection("appportal", IVIMFields.COL_FUELABEL);
        return col.findOne(new BasicDBObject().append(IVIMFields.F_0_6b, vin));
    }

    public static HashMap<String, String> getPrinterParameters(String printfunctionName) {
        DBCollection col = DBActivator.getCollection("appportal", "printers");
        DBObject data = col.findOne(new BasicDBObject().append(IVIMFields.mVeh_A_PrinterFunction,
                printfunctionName));
        if (data != null) {
            HashMap<String, String> result = new HashMap<String, String>();
            result.put(IVIMFields.mVeh_PrinterName, (String) data.get(IVIMFields.mVeh_PrinterName));
            result.put(IVIMFields.mVeh_PrintPosLeft,
                    (String) data.get(IVIMFields.mVeh_PrintPosLeft));
            result.put(IVIMFields.mVeh_PrintPosTop, (String) data.get(IVIMFields.mVeh_PrintPosTop));
            result.put(IVIMFields.mVeh_Connect, (String) data.get(IVIMFields.mVeh_Connect));
            result.put(IVIMFields.mVeh_Baud, (String) data.get(IVIMFields.mVeh_Baud));
            result.put(IVIMFields.mVeh_Parity, (String) data.get(IVIMFields.mVeh_Parity));
            result.put(IVIMFields.mVeh_Databits, (String) data.get(IVIMFields.mVeh_Databits));
            result.put(IVIMFields.mVeh_Stopbits, (String) data.get(IVIMFields.mVeh_Stopbits));
            return result;
        }
        return null;
    }

    public static int getCurrentMaxPaperOfCert() {
        DBCollection ids = DBActivator.getCollection("appportal", "ids");
        int id = DBUtil.getCurrentID(ids, "Veh_Zzbh");
        return id;
    }

    public static int getMaxPaperOfCert() {
        DBCollection ids = DBActivator.getCollection("appportal", "ids");
        int id = DBUtil.getIncreasedID(ids, "Veh_Zzbh");
        return id;
    }

    public static void setCurrentPaperCert(int startNumber) {
        DBCollection ids = DBActivator.getCollection("appportal", "ids");
        DBUtil.setCurrentID(ids, "Veh_Zzbh", startNumber);
    }

    public static DBObject saveUploadData(List<ObjectId> idList, String memo) {
        Date date = new Date();
        DBObject accountInfo = UserSessionContext.getAccountInfo();
        BasicDBObject rec = new BasicDBObject().append(IVIMFields.ACTION_REC_DATE, date)
                .append(IVIMFields.ACTION_REC_ACCOUNT, accountInfo)
                .append(IVIMFields.ACTION_REC_TYPE, IVIMFields.ACTION_REC_TYPE_VALUE_UPLOAD)
                .append(IVIMFields.ACTION_REC_MEMO, memo);

        DBCollection col = DBActivator.getCollection(IVIMFields.DB_NAME, IVIMFields.COL_CERF);
        DBObject query = new BasicDBObject().append("_id",
                new BasicDBObject().append("$in", idList));
        DBObject setting = new BasicDBObject().append(IVIMFields.UPLOADACCOUNT, accountInfo)
                .append(IVIMFields.UPLOADDATE, date)
                .append(IVIMFields.LIFECYCLE, IVIMFields.LC_UPLOADED);
        BasicDBObject update = new BasicDBObject().append("$set", setting).append("$push",
                new BasicDBObject().append(IVIMFields.ACTION_REC, rec));

        col.update(query, update, false, true);

        return setting;

    }

    public static DBObject saveUpload2Data(List<ObjectId> idList, String memo) {
        Date date = new Date();
        DBObject accountInfo = UserSessionContext.getAccountInfo();
        BasicDBObject rec = new BasicDBObject().append(IVIMFields.ACTION_REC_DATE, date)
                .append(IVIMFields.ACTION_REC_ACCOUNT, accountInfo)
                .append(IVIMFields.ACTION_REC_TYPE, IVIMFields.ACTION_REC_TYPE_VALUE_UPLOAD2)
                .append(IVIMFields.ACTION_REC_MEMO, memo);

        DBCollection col = DBActivator.getCollection(IVIMFields.DB_NAME, IVIMFields.COL_CERF);
        DBObject query = new BasicDBObject().append("_id",
                new BasicDBObject().append("$in", idList));
        DBObject setting = new BasicDBObject().append(IVIMFields.UPLOADACCOUNT, accountInfo)
                .append(IVIMFields.UPLOADDATE, date)
                .append(IVIMFields.LIFECYCLE, IVIMFields.LC_UPLOADED);
        BasicDBObject update = new BasicDBObject().append("$set", setting).append("$push",
                new BasicDBObject().append(IVIMFields.ACTION_REC, rec));

        col.update(query, update, false, true);
        return setting;
    }

    public static void saveRePrintData(DBObject data, DBObject info) {
        Date date = new Date();
        DBObject accountInfo = UserSessionContext.getAccountInfo();
        BasicDBObject rec = new BasicDBObject().append(IVIMFields.ACTION_REC_DATE, date)
                .append(IVIMFields.ACTION_REC_ACCOUNT, accountInfo)
                .append(IVIMFields.ACTION_REC_TYPE, IVIMFields.ACTION_REC_TYPE_VALUE_REPRINT)
                .append(IVIMFields.ACTION_REC_MEMO, "No.:" + data.get(IVIMFields.mVeh_Zzbh));

        DBCollection col = DBActivator.getCollection(IVIMFields.DB_NAME, IVIMFields.COL_CERF);
        DBObject query = new BasicDBObject().append("_id", data.get("_id"));
        BasicDBObject update = new BasicDBObject().append("$push",
                new BasicDBObject().append(IVIMFields.ACTION_REC, rec));
        if (info != null) {
            update.append("$set", info);
        }

        col.update(query, update, false, true);
    }

    public static DBObject saveCancelData(List<ObjectId> idList, String memo) {
        Date date = new Date();
        DBObject accountInfo = UserSessionContext.getAccountInfo();
        BasicDBObject rec = new BasicDBObject().append(IVIMFields.ACTION_REC_DATE, date)
                .append(IVIMFields.ACTION_REC_ACCOUNT, accountInfo)
                .append(IVIMFields.ACTION_REC_TYPE, IVIMFields.ACTION_REC_TYPE_VALUE_CANCEL)
                .append(IVIMFields.ACTION_REC_MEMO, memo);

        DBCollection col = DBActivator.getCollection(IVIMFields.DB_NAME, IVIMFields.COL_CERF);
        DBObject query = new BasicDBObject().append("_id",
                new BasicDBObject().append("$in", idList));
        DBObject setting = new BasicDBObject().append(IVIMFields.CANCELACCOUNT, accountInfo)
                .append(IVIMFields.CANCELDATE, date)
                .append(IVIMFields.LIFECYCLE, IVIMFields.LC_CANCELED);
        BasicDBObject update = new BasicDBObject().append("$set", setting).append("$push",
                new BasicDBObject().append(IVIMFields.ACTION_REC, rec));

        col.update(query, update, false, true);
        return setting;
    }

    public static DBObject saveUpdateData(ObjectId id, String memo) {
        Date date = new Date();
        DBObject accountInfo = UserSessionContext.getAccountInfo();
        BasicDBObject rec = new BasicDBObject().append(IVIMFields.ACTION_REC_DATE, date)
                .append(IVIMFields.ACTION_REC_ACCOUNT, accountInfo)
                .append(IVIMFields.ACTION_REC_TYPE, IVIMFields.ACTION_REC_TYPE_VALUE_UPDATE)
                .append(IVIMFields.ACTION_REC_MEMO, memo);

        DBCollection col = DBActivator.getCollection(IVIMFields.DB_NAME, IVIMFields.COL_CERF);
        DBObject query = new BasicDBObject().append("_id", id);
        DBObject setting = new BasicDBObject().append(IVIMFields.UPDATEACCOUNT, accountInfo)
                .append(IVIMFields.UPDATEDATE, date);
        BasicDBObject update = new BasicDBObject().append("$set", setting).append("$push",
                new BasicDBObject().append(IVIMFields.ACTION_REC, rec));

        col.update(query, update, false, true);
        return setting;
    }

    public static DBObject saveRemoveData(List<ObjectId> idList, String memo, String collectionName) {
        Date date = new Date();
        DBObject accountInfo = UserSessionContext.getAccountInfo();
        BasicDBObject rec = new BasicDBObject().append(IVIMFields.ACTION_REC_DATE, date)
                .append(IVIMFields.ACTION_REC_ACCOUNT, accountInfo)
                .append(IVIMFields.ACTION_REC_TYPE, IVIMFields.ACTION_REC_TYPE_VALUE_ABANDON)
                .append(IVIMFields.ACTION_REC_MEMO, memo);

        DBCollection col = DBActivator.getCollection(IVIMFields.DB_NAME, collectionName);
        DBObject query = new BasicDBObject().append("_id",
                new BasicDBObject().append("$in", idList));
        DBObject setting = new BasicDBObject().append(IVIMFields.ABANDONACCOUNT, accountInfo)
                .append(IVIMFields.ABANDONDATE, date)
                .append(IVIMFields.LIFECYCLE, IVIMFields.LC_ABANDON);
        BasicDBObject update = new BasicDBObject().append("$set", setting).append("$push",
                new BasicDBObject().append(IVIMFields.ACTION_REC, rec));

        col.update(query, update, false, true);
        return setting;
    }

    public static void saveCOCPrintData(DBObject data) throws Exception {
        Date date = new Date();
        DBObject accountInfo = UserSessionContext.getAccountInfo();
        BasicDBObject rec = new BasicDBObject().append(IVIMFields.ACTION_REC_DATE, date)
                .append(IVIMFields.ACTION_REC_ACCOUNT, accountInfo)
                .append(IVIMFields.ACTION_REC_TYPE, IVIMFields.ACTION_REC_TYPE_VALUE_PRINT)
                .append(IVIMFields.ACTION_REC_MEMO, "");

        DBCollection col = DBActivator.getCollection(IVIMFields.DB_NAME, IVIMFields.COL_COCPAPER);
        DBObject query = new BasicDBObject().append(IVIMFields.F_0_6b, data.get(IVIMFields.F_0_6b));

        DBObject dataItem = col.findOne(query, new BasicDBObject().append("_id", 1).append(IVIMFields.LIFECYCLE, 1));

        if (dataItem != null) {
            if (!IVIMFields.LC_ABANDON.equals(dataItem.get(IVIMFields.LIFECYCLE))) {
                throw new Exception("该COC证书已经打印，不可重复打印");
            } else {
                if (!COC_REPRINT) {
                    throw new Exception("设置不允许直接重复打印作废的COC证书，不可重复打印");
                }
            }
        }
        data.put(IVIMFields.PRINTACCOUNT, accountInfo);
        data.put(IVIMFields.PRINTDATE, date);
        data.put(IVIMFields.LIFECYCLE, IVIMFields.LC_PRINTED);
        ObjectId oid;
        if (dataItem == null || IVIMFields.LC_ABANDON.equals(dataItem.get(IVIMFields.LIFECYCLE))) {// 没有此VIM的COC证书或者COC证书已经作废
            oid = new ObjectId();
            data.put("_id", oid);
            List<DBObject> reclist = new ArrayList<DBObject>();
            reclist.add(rec);
            data.put(IVIMFields.ACTION_REC, reclist);

            // 处理系统字段
            UIUtils.addInsertInfo(data);

            col.insert(data);
        } else {
            oid = (ObjectId) dataItem.get("_id");
            data.removeField("_id");
            UIUtils.addmodifyInfo(data);

            BasicDBObject update = new BasicDBObject().append("$push",
                    new BasicDBObject().append(IVIMFields.ACTION_REC, rec));
            update.append("$set", data);

            col.update(query, update, false, true);
            data.put("_id", oid);
        }

    }

    private static void saveFuelLabelPrintData(DBObject data) throws Exception {
        Date date = new Date();
        DBObject accountInfo = UserSessionContext.getAccountInfo();
        BasicDBObject rec = new BasicDBObject().append(IVIMFields.ACTION_REC_DATE, date)
                .append(IVIMFields.ACTION_REC_ACCOUNT, accountInfo)
                .append(IVIMFields.ACTION_REC_TYPE, IVIMFields.ACTION_REC_TYPE_VALUE_PRINT)
                .append(IVIMFields.ACTION_REC_MEMO, "");

        DBCollection col = DBActivator.getCollection(IVIMFields.DB_NAME, IVIMFields.COL_FUELABEL);
        DBObject query = new BasicDBObject().append(IVIMFields.F_0_6b, data.get(IVIMFields.F_0_6b));

        DBObject dataItem = col.findOne(query, new BasicDBObject().append("_id", 1).append(IVIMFields.LIFECYCLE, 1));

        if (dataItem != null) {
            if (!IVIMFields.LC_ABANDON.equals(dataItem.get(IVIMFields.LIFECYCLE))) {
                throw new Exception("该燃油标识已经打印，不可重复打印");
            } else {
                if (!FL_REPRINT) {
                    throw new Exception("设置不允许直接重复打印作废的燃油标识，不可重复打印");
                }
            }
        }

        data.put(IVIMFields.PRINTACCOUNT, accountInfo);
        data.put(IVIMFields.PRINTDATE, date);
        data.put(IVIMFields.LIFECYCLE, IVIMFields.LC_PRINTED);
        ObjectId oid;
        if (dataItem == null || IVIMFields.LC_ABANDON.equals(dataItem.get(IVIMFields.LIFECYCLE))) {// 没有此VIM的COC证书或者COC证书已经作废
            oid = new ObjectId();
            data.put("_id", oid);
            List<DBObject> reclist = new ArrayList<DBObject>();
            reclist.add(rec);
            data.put(IVIMFields.ACTION_REC, reclist);

            // 处理系统字段
            UIUtils.addInsertInfo(data);

            col.insert(data);
        } else {
            oid = (ObjectId) dataItem.get("_id");
            data.removeField("_id");
            UIUtils.addmodifyInfo(data);

            BasicDBObject update = new BasicDBObject().append("$push",
                    new BasicDBObject().append(IVIMFields.ACTION_REC, rec));
            update.append("$set", data);

            col.update(query, update, false, true);
            data.put("_id", oid);
        }
    }

    public static void printFuelLabel(Browser browser, DBObject dbObject) throws Exception {
        HashMap<String, String> printerdata = getPrinterParameters(IVIMFields.PRINTER_FUNCTIONS[2]);
        if (printerdata == null) {
            throw new Exception("没有设置打印燃油标识的打印机");
        }
        String printerName = printerdata.get(IVIMFields.mVeh_PrinterName);

        String template = "fl.pdf";

        saveFuelLabelPrintData(dbObject);

        StringBuilder url = new StringBuilder();
        url.append("/form/printdata");
        url.append("?form=" + template);
        url.append("&printer=" + printerName);
        url.append("&font=" + "simhei.ttc");
        url.append("&db=appportal");
        url.append("&col=" + IVIMFields.COL_FUELABEL);
        url.append("&id=" + dbObject.get("_id"));

        String encodedURL = RWT.getResponse().encodeURL(url.toString());
        if (browser != null) {
            browser.setUrl(encodedURL);
        } else {
            UrlLauncher launcher = RWT.getClient().getService(UrlLauncher.class);
            launcher.openURL(encodedURL);
        }
    }

    public static void printCOC(Browser browser, DBObject dbObject) throws Exception {
        HashMap<String, String> printerdata = getPrinterParameters(IVIMFields.PRINTER_FUNCTIONS[1]);
        if (printerdata == null) {
            throw new Exception("没有设置打印COC证书的打印机");
        }
        String printerName = printerdata.get(IVIMFields.mVeh_PrinterName);

        String template = null;
        if (dbObject.get(IVIMFields.F_0_4).equals("M1")) {
            template = "cocm1.pdf";
        } else if (dbObject.get(IVIMFields.F_0_4).equals("N1")) {
            template = "cocn1.pdf";
        }
        if (template == null) {
            throw new Exception("没有对应的打印模板");
        }

        saveCOCPrintData(dbObject);

        StringBuilder url = new StringBuilder();
        url.append("/form/printdata");
        url.append("?form=" + template);
        url.append("&printer=" + printerName);
        url.append("&db=appportal");
        url.append("&col=" + IVIMFields.COL_COCPAPER);
        url.append("&id=" + dbObject.get("_id"));

        String encodedURL = RWT.getResponse().encodeURL(url.toString());
        if (browser != null) {
            browser.setUrl(encodedURL);
        } else {
            UrlLauncher launcher = RWT.getClient().getService(UrlLauncher.class);
            launcher.openURL(encodedURL);
        }

    }

    /**
     * 将公告数据同步到COC信息中
     * 
     * @param cocInfo
     * @throws Exception
     */
    public static void syncPublicDataToCOC(DBObject cocInfo) throws Exception {
        ObjectId bcid = (ObjectId) cocInfo.get(IVIMFields.BASICINFO_ID);
        BasicInfo bService = new BasicInfo();
        DBObject basicInfo = bService.get(bcid);
        Iterator<String> iter = basicInfo.keySet().iterator();
        
        BasicDBObject update = new BasicDBObject();
        while (iter.hasNext()) {
            String key = iter.next();
            if(key.startsWith("_")){
                continue;
            }
            cocInfo.put(key, basicInfo.get(key));
            update.put(key, basicInfo.get(key));
        }
        
        COCInfo cService = new COCInfo();
        cService.update((ObjectId) cocInfo.get("_id"), update);
    }

}
