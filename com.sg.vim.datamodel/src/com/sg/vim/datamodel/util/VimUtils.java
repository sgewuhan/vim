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
     * ��Ʒ��
     */
    protected static final String FIELD_PRODUCT_CODE = "erp_product_code";
    /**
     * ��������
     */
    protected static final String FIELD_ENGINEE_NUM = "safety_components_vin";
    /**
     * ��������
     */
    protected static final String FIELD_MFT_DATE = "manufacture_date";

    // *******/

    public static final String[] COLOR_CODE = new String[] { "A", "B", "C", "D", "E", "F", "G",
            "H", "I", "J", "K", "L", "M" };
    public static final String[] COLOR_NAME = new String[] { "��ɫ", "��ɫ", "��ɫ", "��ɫ", "��ɫ", "��ɫ",
            "��ɫ", "��ɫ", "��ɫ", "��ɫ", "��ɫ", "����ɫ" };

    /**
     * ���������Ҫ��html�к����Ĳ�������һ��
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
     * ��������VIN�Ƿ�Ϸ�
     * 
     * @param inputVin
     */
    public static boolean checkVIN(String vin) {
        // 17λ��ĸ��������ɣ�LDC913L2240000023
        // 0-9,A-Z���ǲ�������ĸO,I,Q
        String pattern = "[0-9ABCDEFGHJKLMNPQRSTUVWXYZ]{17}";
        return Utils.isPatternMatched(vin, pattern);
    }

    /**
     * ��ѯ���ݿ��ֶ��� ��˼ vin VIN erp_product_code ��Ʒ�� safety_components_vin �������� manufacture_date ��������
     * �Ƿ���Ҫ��ӡ�ϸ�֤ ���ֶ����ޣ���ERPȷ��������
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
            throw new Exception("MES���ݿ���û��VIN��Ӧ�ĳ�Ʒ��¼��\nVIN:" + vin);
        }
        return res.getData().get(0);
    }

    public static DBObject getProductCodeInfo(String productCode) throws Exception {
        DBCollection c = DBActivator.getCollection(IVIMFields.DB_NAME,
                IVIMFields.COL_PRODUCTCODEINFO);
        DBObject query = new BasicDBObject().append(IVIMFields.E_02, productCode);
        DBObject data = c.findOne(query);
        if (data == null) {
            throw new Exception("��VIM���ݿ����޷���ó�Ʒ�����ݣ������Ǹó�Ʒ�����ݻ�δ��ͬ��" + "\n�������ֹ�ͬ�������ԡ�\n��Ʒ�룺"
                    + productCode);
        }
        Object cocinfoId = data.get(IVIMFields.COC_ID);
        if (!(cocinfoId instanceof ObjectId)) {
            throw new Exception("��VIM���ݿ����޷���ó�Ʒ����صĳ���һ��������" + "\n�뽫�ó�Ʒ��󶨳���һ�������ݺ����ԡ�\n��Ʒ�룺"
                    + productCode);
        }
        Object cfgInfoId = data.get(IVIMFields.CFG_ID);
        if (!(cfgInfoId instanceof ObjectId)) {
            throw new Exception("��VIM���ݿ����޷���ó�Ʒ����ص�������Ϣ" + "\n�뽫�ó�Ʒ����������ݺ����ԡ�\n��Ʒ�룺" + productCode);
        }

        return data;
    }

    public static DBObject getCOCInfo(DBObject productCodeData) throws Exception {
        DBCollection c = DBActivator.getCollection(IVIMFields.DB_NAME, IVIMFields.COL_COCINFO);
        ObjectId id = (ObjectId) productCodeData.get(IVIMFields.COC_ID);
        DBObject query = new BasicDBObject().append("_id", id);
        DBObject data = c.findOne(query);
        if (data == null) {
            throw new Exception("��Ʒ��󶨵ĳ���һ���Լ�¼�Ѳ����ڣ��������ѱ�ɾ����\n�����½��ó�Ʒ��󶨳���һ�������ݺ����ԡ�\n��Ʒ��:"
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
            throw new Exception("��Ʒ��󶨵ĳ���һ���Լ�¼�Ѳ����ڣ��������ѱ�ɾ����\n�����½��ó�Ʒ��󶨳���һ�������ݺ����ԡ�");
        }
        return data;
    }

    public static DBObject getCOCInfoById(String productId) throws Exception {
        DBCollection c = DBActivator.getCollection(IVIMFields.DB_NAME, IVIMFields.COL_COCINFO);
        DBObject o = new BasicDBObject().append(IVIMFields.D_02, productId);
        DBObject data = c.findOne(o);
        if (data == null) {
            throw new Exception("�޷���ö�Ӧ��ƷID�ĳ���һ������Ϣ��\n���ڳ���һ������Ϣ�м���Ӧ��ƷID�ļ�¼\n��ƷID:" + productId);
        }
        return data;
    }

    public static DBObject getConfInfoById(String productId) throws Exception {
        DBCollection c = DBActivator.getCollection(IVIMFields.DB_NAME,
                IVIMFields.COL_CONFIGCODEINFO);
        DBObject o = new BasicDBObject().append(IVIMFields.D_02, productId);
        DBObject data = c.findOne(o);
        if (data == null) {
            throw new Exception("�޷���ö�Ӧ��ƷID��������Ϣ��\n���ڳ���һ������Ϣ�м���Ӧ������Ϣ�ļ�¼\n��ƷID:" + productId);
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
        // g_34 //ȡ��������
        try {
            String mftDate = (String) mesRawData.getValue(FIELD_MFT_DATE);
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
            Date _date;
            _date = sdf2.parse(mftDate);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy��MM��dd��");
            String mdate = sdf.format(_date);
            result.put(IVIMFields.G_34, mdate);
        } catch (ParseException e) {
        }
        // f_0_6b //ȡvin
        result.put(IVIMFields.F_0_6b, vin);

        return result;
    }

    private static DBObject transferCOCData(DBObject cocData, DBObject confData,
            DBObject productCodeData, SQLRow mesRawData, String vin) {
        BasicDBObject result = new BasicDBObject();

        // ����cocData��ֵ
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

        // �����־�ϲ�
        Object qlj = cocData.get(IVIMFields.F_5A);
        Object hlj = cocData.get(IVIMFields.F_5B);
        result.put(IVIMFields.F_5A_O, "" + qlj + "/" + hlj);

        // ����4.1
        Object qzj = cocData.get(IVIMFields.F_4_1_1);
        Object hzj = cocData.get(IVIMFields.F_4_1_2);
        if (Utils.isNullOrEmptyString(qzj) && Utils.isNullOrEmptyString(hzj)) {
            result.put(IVIMFields.F_4_1_1_O, "" + qzj + "/" + hzj);
        }

        // �����������

        // Veh_FDjh F_21a �������� ӳ��
        String code = (String) mesRawData.getValue("SAFETY_COMPONENTS_VIN");
        result.put(IVIMFields.F_21a, code.substring(code.length() - 9));

        // �����ŷ�
        Object f46_3 = cocData.get(IVIMFields.F_46_3);
        // ������Co2, �ͺ�
        // f_46_3_o11,f_46_3_o12
        // �н���Co2,�ͺ�
        // f_46_3_o21,f_46_3_o22
        // �ۺ�,Co2,�ͺ�
        // f_46_3_o31,f_46_3_o32
        if (f46_3 instanceof BasicDBList) {
            Iterator<Object> iter = ((BasicDBList) f46_3).iterator();
            while (iter.hasNext()) {
                DBObject dbo = (DBObject) iter.next();
                if ("����".equals(dbo.get("location"))) {
                    result.put(IVIMFields.F_46_3_o11, dbo.get("co2"));
                    result.put(IVIMFields.F_46_3_o12, dbo.get("fuelqty"));
                } else if ("�н�".equals(dbo.get("location"))) {
                    result.put(IVIMFields.F_46_3_o21, dbo.get("co2"));
                    result.put(IVIMFields.F_46_3_o22, dbo.get("fuelqty"));
                } else if ("�ۺ�".equals(dbo.get("location"))) {
                    result.put(IVIMFields.F_46_3_o31, dbo.get("co2"));
                    result.put(IVIMFields.F_46_3_o32, dbo.get("fuelqty"));
                }
            }
        }

        // ����VIN
        result.put(IVIMFields.F_0_6b, vin);

        // ����ǩ������
        Object ccc03 = cocData.get(IVIMFields.CCC_03);
        if (ccc03 instanceof Date) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy��MM��dd��");
            result.put(IVIMFields.CCC_03, sdf.format((Date) ccc03));
        }

        // ����ϸ�֤����
        // 1.���ݲ��Һϸ�֤
        DBObject cert = getCertDataByVin(vin, "QX");
        if (cert != null) {
            Object fzrq = cert.get(IVIMFields.mVeh_Fzrq);
            result.put(IVIMFields.CCC_21, fzrq == null ? "" : fzrq.toString());

        }

        // ������ɫ
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

        // ����ְ�ɣ��ڳ�Ʒ����ȡ
        Object fc6 = productCodeData.get(IVIMFields.F_C6);
        result.put(IVIMFields.F_C6, fc6);

        return result;
    }

    public static DBObject transferCerfData(DBObject cocData, DBObject confData,
            DBObject productCodeData, SQLRow mesRawData, String vin, boolean isDP) throws Exception {
        BasicDBObject result = new BasicDBObject();
        // Veh_ClzzqymC F_0_1 ����������ҵ���� ӳ��
        result.put(IVIMFields.mVeh_Clzzqymc, cocData.get(IVIMFields.F_0_1));
        // Veh_ClmC F_0_2_1 �������� ӳ��
        result.put(IVIMFields.mVeh_Clmc, cocData.get(IVIMFields.F_0_2_1));
        // Veh_Clxh F_0_2C1 �����ͺ� ӳ��
        result.put(IVIMFields.mVeh_Clxh, cocData.get(IVIMFields.F_0_2C1));

        // Veh_Dpxh CCC_04 �����ͺ� ӳ��
        if (!isDP) {
            result.put(IVIMFields.mVeh_Dpxh, cocData.get(IVIMFields.CCC_04));
        }

        // Veh_Csys F_38 ������ɫ �ӳ�Ʒ�ֵ�ȡ
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

        // Veh_FDjh F_21a �������� ӳ��
        String code = (String) mesRawData.getValue("SAFETY_COMPONENTS_VIN");

        result.put(IVIMFields.mVeh_Fdjh, code.substring(code.length() - 9));
        // Veh_Rlzl F_25 ȼ������ ӳ��
        result.put(IVIMFields.mVeh_Rlzl, cocData.get(IVIMFields.F_25));
        // Veh_Gl C_01 ���� ӳ��
        result.put(IVIMFields.mVeh_Gl, cocData.get(IVIMFields.C_01));
        // Veh_Pl F_24 ���� ӳ��
        result.put(IVIMFields.mVeh_Pl, cocData.get(IVIMFields.F_24));
        // Veh_PFbz C_06 �ŷű�׼ ӳ��
        result.put(IVIMFields.mVeh_Pfbz, cocData.get(IVIMFields.C_06));
        // Veh_Yh C_03 �ͺ� ӳ��
        result.put(IVIMFields.mVeh_Yh, cocData.get(IVIMFields.D_16));// ȡC_03����D_16?
        // Veh_WkC F_6_1 ������ ӳ��
        result.put(IVIMFields.mVeh_Wkc, cocData.get(IVIMFields.F_6_1));
        // Veh_Wkk F_7_1 ������ ӳ��
        result.put(IVIMFields.mVeh_Wkk, cocData.get(IVIMFields.F_7_1));
        // Veh_Wkg F_8 ������ ӳ��
        result.put(IVIMFields.mVeh_Wkg, cocData.get(IVIMFields.F_8));
        // Veh_HxnbC F_C7_1 �����ڲ��� ӳ��
        result.put(IVIMFields.mVeh_Hxnbc, cocData.get(IVIMFields.F_C7_1));
        // Veh_Hxnbk F_C7_2 �����ڲ��� ӳ��
        result.put(IVIMFields.mVeh_Hxnbk, cocData.get(IVIMFields.F_C7_2));
        // Veh_Hxnbg F_C7_3 �����ڲ��� ӳ��
        result.put(IVIMFields.mVeh_Hxnbg, cocData.get(IVIMFields.F_C7_3));
        // Veh_Gbthps F_C6 �ְ嵯��Ƭ�� ӳ��
        result.put(IVIMFields.mVeh_Gbthps, cocData.get(IVIMFields.F_C6));
        // Veh_FDjxh F_C4 �������ͺ� ӳ��
        result.put(IVIMFields.mVeh_Fdjxh, cocData.get(IVIMFields.F_C4));
        // Veh_Lts F_1_1 ��̥�� ӳ��
        result.put(IVIMFields.mVeh_Lts, cocData.get(IVIMFields.F_1_1));
        // Veh_Ltgg F_32A ��̥��� ӳ�� ��̥���ϸ�֤
        result.put(IVIMFields.mVeh_Ltgg, cocData.get(IVIMFields.F_32A));
        // Veh_Qlj F_5a ǰ�־� ӳ��
        result.put(IVIMFields.mVeh_Qlj, cocData.get(IVIMFields.F_5A));
        // Veh_Hlj F_5b ���־� ӳ��
        result.put(IVIMFields.mVeh_Hlj, cocData.get(IVIMFields.F_5B));
        // Veh_Zj F_3 ��� ӳ��
        result.put(IVIMFields.mVeh_Zj, cocData.get(IVIMFields.F_3));
        // Veh_Zh F_14_2 ��� ӳ��
        result.put(IVIMFields.mVeh_Zh, cocData.get(IVIMFields.F_14_2));
        // Veh_Zs F_1 ���� ӳ��
        result.put(IVIMFields.mVeh_Zs, cocData.get(IVIMFields.F_1));
        // Veh_Zxxs F_C34 ת����ʽ ӳ��
        result.put(IVIMFields.mVeh_Zxxs, cocData.get(IVIMFields.F_C34));
        // Veh_Zzl F_14_1 ������ ӳ��
        result.put(IVIMFields.mVeh_Zzl, cocData.get(IVIMFields.F_14_1));
        // Veh_Zbzl C_08 �������� ӳ��
        result.put(IVIMFields.mVeh_Zbzl, cocData.get(IVIMFields.C_08));
        // Veh_EDzzl C_09 ������� ӳ��
        result.put(IVIMFields.mVeh_Edzzl, cocData.get(IVIMFields.C_09));
        // Veh_Zzllyxs F_C5 ����������ϵ�� ӳ��
        result.put(IVIMFields.mVeh_Zzllyxs, cocData.get(IVIMFields.F_C5));
        // Veh_Zqyzzl C_10 ׼ǣ�������� ӳ��
        result.put(IVIMFields.mVeh_Zqyzzl, cocData.get(IVIMFields.C_10));
        // Veh_BgCazzDyxzzl C_04 ��ҳ������������������ ӳ��
        result.put(IVIMFields.mVeh_Bgcazzdyxzzl, cocData.get(IVIMFields.C_04));
        // Veh_JsszCrs C_02 ��ʻ��׼������ ӳ�� ȫ�����̱���
        Object c_02 = cocData.get(IVIMFields.C_02);
        if (isDP) {
            if (!debug && Utils.isNullOrEmptyString(c_02)) {
                throw new Exception("��ʻ��׼�������ڵ��̺ϸ�֤�����в���Ϊ��");
            }
            result.put(IVIMFields.mVeh_Jsszcrs, c_02);
        }
        // Veh_EDzk F_42_1 ��ؿ� ӳ��
        Object f_42_1 = cocData.get(IVIMFields.F_42_1);
        if (!isDP) {
            if (!debug && Utils.isNullOrEmptyString(f_42_1)) {
                throw new Exception("��ؿ�����ȫ��ϸ�֤�����в���Ϊ��");
            }
            result.put(IVIMFields.mVeh_Edzk, f_42_1);
        }
        // Veh_ZgCs F_44 ��߳��� ӳ��
        result.put(IVIMFields.mVeh_Zgcs, cocData.get(IVIMFields.F_44));
        // Veh_Clpp F_C0_2 ����Ʒ�� ӳ��
        result.put(IVIMFields.mVeh_Clpp, cocData.get(IVIMFields.F_C0_2));
        // Veh_ClsbDh F_0_6b
        result.put(IVIMFields.mVeh_Clsbdh, vin);
        // Veh_DpiD C_12 ����ID ӳ��
        result.put(IVIMFields.mVeh_Dpid, cocData.get(IVIMFields.C_12));
        // Veh_Bz F_50 ��ע ӳ�� �ϸ�֤��ע
        result.put(IVIMFields.mVeh_Bz, cocData.get(IVIMFields.C_11));
        // Veh_Clztxx C_23 ����״̬��Ϣ ֵת��
        result.put(IVIMFields.mVeh_Clztxx, cocData.get(IVIMFields.C_23));
        // Veh_ClFl C_22 �������� ӳ��
        result.put(IVIMFields.mVeh_Clfl, cocData.get(IVIMFields.C_22));
        // Veh_Zxzs C_13 ת������� ӳ��
        result.put(IVIMFields.mVeh_Zxzs, cocData.get(IVIMFields.C_13));
        // Veh_CDDbj C_21 ���綯��� ֵת��
        Object object = cocData.get(IVIMFields.C_21);
        if ("��".equals(object)) {
            result.put(IVIMFields.mVeh_Cddbj, "1");
        } else {
            result.put(IVIMFields.mVeh_Cddbj, "2");
        }
        // Veh_ClsCDwmC F_0_1 ����������λ���� ӳ��
        result.put(IVIMFields.mVeh_Clscdwmc, cocData.get(IVIMFields.F_0_1));
        // Veh_CpsCDz D_04 ����������λ��ַ ӳ��
        result.put(IVIMFields.mVeh_Cpscdz, cocData.get(IVIMFields.D_04));
        // Veh_QyiD C_14 ��ҵID ӳ��
        String companyId = (String) cocData.get(IVIMFields.C_14);
        result.put(IVIMFields.mVeh_Qyid, companyId + "0000");
        // Veh_Qybz C_17 ��ҵ��׼ ӳ��
        result.put(IVIMFields.mVeh_Qybz, cocData.get(IVIMFields.C_17));
        // Veh_Cpggh D_23 ��Ʒ����� ֵת�� �ɹ�����Ϣ���,11λ�ַ���������������к�14λ�ַ�����25λ
        String productPublicId = (String) cocData.get(IVIMFields.D_23);
        // �������
        String confid = (String) confData.get(IVIMFields.H_01);
        if (Utils.isNullOrEmpty(confid)) {
            confid = (String) confData.get(IVIMFields.H_02);
        }
        String string = productPublicId + confid;
        if (!debug && string.length() != 25) {
            throw new Exception("�޷�ȡ����ȷ�Ĳ�Ʒ����š�\nֵת��  �ɹ�����Ϣ���,11λ�ַ���������������к�14λ�ַ�����25λ");
        }
        result.put(IVIMFields.mVeh_Cpggh, string);
        result.put(IVIMFields.D_23, productPublicId);
        result.put(IVIMFields.H_01, confid);
        
        // Veh_GgpC D_18 �������� ӳ��
        result.put(IVIMFields.mVeh_Ggpc, cocData.get(IVIMFields.D_18));
        // Veh_Ggsxrq D_01 ������Ч���� ӳ��
        Object value = cocData.get(IVIMFields.D_01);
        try {
            Date date;
            date = Utils.getDateValue(value, true);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            result.put(IVIMFields.mVeh_Ggsxrq, sdf.format(date));
        } catch (Exception e) {
            throw new Exception("�޷�ȡ����ȷ�Ĺ�����Ч���ڡ�\n" + e.getMessage());
        }

        // Veh_ZChgzbh �����ϸ�֤��� ���� ����ֵ4λ��ҵ����+10λ˳��ųɹ����ô�ӡ���������ͨ�������Ի��15λ�������ϸ�֤���

        // ��ӡǰʱ����

        // DBCollection ids = DBActivator.getCollection(IVIMFields.DB_NAME, "ids");
        // String seq = DBUtil.getIncreasedID(ids, IVIMFields.SEQ_GZBH, "0", 10);
        // string = companyId + seq;
        // if (!debug && string.length() != 14) {
        // throw new Exception("�޷�ȡ����ȷ�������ϸ�֤��š�\n4λ��ҵ����+10λ˳���");
        // }
        // result.put(IVIMFields.mVeh_Zchgzbh, string);

        // Veh_Dphgzbh ȫ�ʽ15λ�����̷�ʽ���� ����
        // result.put(IVIMFields.mVeh_Dphgzbh, cocData.get(IVIMFields.F_0_1));

        // Veh_Fzrq ��֤���� �ַ� 14 YYYY��MM��DD��
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy��MM��dd��");
        string = sdf.format(new Date());
        result.put(IVIMFields.mVeh_Fzrq, string);
        // Veh_Cjh vin vin
        result.put(IVIMFields.mVeh_Cjh, "");
        // Veh_Clzzrq ������������
        String mftDate = (String) mesRawData.getValue(FIELD_MFT_DATE);
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        Date _date = sdf2.parse(mftDate);
        string = sdf.format(_date);
        result.put(IVIMFields.mVeh_Clzzrq, string);
        // Veh_Qyqtxx �����е�����
        result.put(IVIMFields.mVeh_Qyqtxx, cocData.get(IVIMFields.C_18));

        // // Veh_Tmxx ���� ����
        // result.put(IVIMFields.mVeh_Tmxx, cocData.get(IVIMFields.F_0_1));
        // Veh_Jyw ���� У�鲻��
        // result.put(IVIMFields.mVeh_Jyw, cocData.get(IVIMFields.F_0_1));
        // Veh_PrinterName ����
        result.put(IVIMFields.mVeh_PrinterName, "");
        // Veh_PrintPosLeFt ����
        result.put(IVIMFields.mVeh_PrintPosLeft, "15");
        // Veh_PrintPosTop ����
        result.put(IVIMFields.mVeh_PrintPosTop, "15");
        // Veh_ConneCt ����
        result.put(IVIMFields.mVeh_Connect, "com1");
        // Veh_BauD ����
        result.put(IVIMFields.mVeh_Baud, "9600");
        // Veh_Parity ����
        result.put(IVIMFields.mVeh_Parity, "N");
        // Veh_Databits ����
        result.put(IVIMFields.mVeh_Databits, "8");
        // Veh_Stopbits ����
        result.put(IVIMFields.mVeh_Stopbits, "1");

        // Veh_Zzbh ֽ�ű��
        // *****************************�����ֵ������Ҫ���ݵ��ϸ�֤��ֵ��ͬʱ���ֶ�չ��ʱҲ������Ӧ�Ĵ���
        // ���磬����110��input�е�ֵ����110��������ʾΪ000110��ͬʱ��Ҫ�ڴ��ݵ��ϸ�֤��ӡOCX��ֵҲ��Ҫ�ı�Ϊ000110
        // �μ�doTransferBeforeInvokePrint()
        // result.put(IVIMFields.mVeh_Zzbh, "");

        // Veh_Dywym ����
        // result.put(IVIMFields.mVeh_Dywym, "");// ����ֵ������

        /** �����Ǵ�ӡ����Ҫ�Ĳ������������ϴ�ʱ��Ҫ�Ĳ��� **/
        result.put(IVIMFields.mVeh__Hzdczfs, cocData.get(IVIMFields.C_15));
        result.put(IVIMFields.mVeh__Hzdfs, cocData.get(IVIMFields.C_16));
        result.put(IVIMFields.mVeh__Qzdczfs, cocData.get(IVIMFields.C_19));
        result.put(IVIMFields.mVeh__Qzdfs, cocData.get(IVIMFields.C_20));
        result.put(IVIMFields.mVeh__Pzxlh, confid);
        // �����ϸ�֤�����Ҫ�ڴ�ӡ�󴫵�

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

        sb.append(String.format("�������:%s\r\n", oResult.getResultCode()));

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

        // ���
        // ����
        // ��������
        // ��������
        //
        // ��soap������
        // ˵��
        //
        // 1
        // VEHICLE_STATUS
        // ״̬
        // s:string
        // ϵͳ���ɣ����������״̬˵��������
        //
        // 2
        // CLIENT_HARDWARE_INFO
        // �ϴ�����Ӳ����Ϣ
        // s:string
        Assert.isNotNull(HARDWAREID, "HARDWAREID����Ϊ�գ�����vim.properties�ļ��Ƿ�����������vidc.hardware");
        info.setCLIENTHARDWAREINFO(HARDWAREID);
        //
        //
        // 3
        // CREATETIME
        // �ϴ�����
        // s:dateTime
        // ϵͳ���ɣ����������
        //
        // 4
        // FEEDBACK_TIME
        // ��������ʱ��
        // s:dateTime
        // ϵͳ���ɣ����������
        //
        // 5
        // H_ID
        // ������
        // s:string
        // ϵͳ���ɣ����������
        //
        // 6
        // HD_HOST
        // �ϴ�������ַ
        // s:string
        // ϵͳ��ȡ�����������
        //
        // 7
        // HD_USER
        // ��ҵ�û���
        // s:string
        //
        Assert.isNotNull(HD_USER, "HD_USER����Ϊ�գ�����vim.properties�ļ��Ƿ�����������vidc.user");
        info.setHDUSER(HD_USER);
        //
        // 8
        // UKEY
        // U�ܱ�ʶ
        // s:string
        // ϵͳ�Զ���ȡ��Ҫ���ϴ��������Ѿ�����U�ܡ�
        //
        // 9
        // UPDATETIME
        // ��������
        // s:dateTime
        // ϵͳ���ɣ����������
        //
        // 10
        // UPSEND_TAG
        // �ϴ����
        // s:string
        // ϵͳ���ɣ����������
        //
        // 11
        // VERCODE
        // У����
        // s:string
        // У���룬�ɴ�ӡ�ӿ�����
        String value = (String) data.get(IVIMFields.mVeh_Jyw);
        // Assert.isNotNull(value, "��ӡУ���벻��Ϊ��");
        info.setVERCODE(value);

        // 12
        // VERSION
        // �ϴ�ϵͳ�汾��Ϣ
        // s:string
        // ϵͳ���ɣ����������
        //
        // 13
        // CDDBJ
        // ���綯���
        // s:string
        //
        value = (String) data.get(IVIMFields.mVeh_Cddbj);
        // Assert.isNotNull(value, "���綯��ǲ���Ϊ��");
        info.setCDDBJ(value);
        //
        // 14
        // CLLX
        // ��������
        // s:string
        //
        value = (String) data.get(IVIMFields.mVeh_Clfl);
        // Assert.isNotNull(value, "�������Ͳ���Ϊ��");
        info.setCLLX(value);

        //
        // 15
        // CLSCDWMC
        // ����������λ����
        // s:string
        //
        value = (String) data.get(IVIMFields.mVeh_Clscdwmc);
        // Assert.isNotNull(value, "����������λ���Ʋ���Ϊ��");
        info.setCLSCDWMC(value);
        //
        // 16
        // CLZTXX
        // ����״̬��Ϣ
        // s:string
        //
        value = (String) data.get(IVIMFields.mVeh_Clztxx);
        // Assert.isNotNull(value, "����״̬��Ϣ����Ϊ��");
        info.setCLZTXX(value);
        //
        // 17
        // CZRQ
        // ��������
        // s:string
        //
        GregorianCalendar nowGregorianCalendar = new GregorianCalendar();
        XMLGregorianCalendar xmlDatetime = DatatypeFactory.newInstance().newXMLGregorianCalendar(
                nowGregorianCalendar);
        info.setCZRQ(xmlDatetime); // ��������

        //
        // 18
        // DYWYM
        // ��ӡΨһ��
        // s:string
        // �ɴ�ӡ�ӿ�����
        //
        value = (String) data.get(IVIMFields.mVeh_Dywym);
        // Assert.isNotNull(value, "��ӡΨһ�벻��Ϊ��");
        info.setDYWYM(value);
        // 19
        // QYID
        // ��ҵ���
        // s:string
        //
        value = (String) data.get(IVIMFields.mVeh_Qyid);
        // Assert.isNotNull(value, "��ҵ��Ų���Ϊ��");
        info.setQYID(value);
        //
        // 20
        // YH
        // �ͺ�
        // s:string
        //
        value = (String) data.get(IVIMFields.mVeh_Yh);
        // Assert.isNotNull(value, "�ͺĲ���Ϊ��");
        info.setYH(value);
        //
        // 21
        // ZCHGZBH
        // �����ϸ�֤���
        // s:string
        //
        value = (String) data.get(IVIMFields.mVeh_Zchgzbh);
        // Assert.isNotNull(value, "�����ϸ�֤��Ų���Ϊ��");
        info.setZCHGZBH(value);
        //
        // 22
        // ZXZS
        // ת������
        // s:string
        //
        value = (String) data.get(IVIMFields.mVeh_Zxzs);
        // Assert.isNotNull(value, "ת����������Ϊ��");
        info.setZXZS(value);
        //
        // 23
        // ZZBH
        // ֽ�ű��
        // s:string
        //
        value = (String) data.get(IVIMFields.mVeh_Zzbh);
        // Assert.isNotNull(value, "ֽ�ű�Ų���Ϊ��");
        info.setZZBH(value);
        //
        // 24
        // BGCAZZDYXZZL
        // ��ҳ������������������
        // s:string
        //
        value = (String) data.get(IVIMFields.mVeh_Bgcazzdyxzzl);
        // Assert.isNotNull(value, "��ҳ����������������������Ϊ��");
        info.setBGCAZZDYXZZL(value);
        //
        // 25
        // BZ
        // ��ע
        // s:string
        //
        value = (String) data.get(IVIMFields.mVeh_Bz);
        // Assert.isNotNull(value, "��ע����Ϊ��");
        info.setBZ(value);
        //
        // 26
        // CJH
        // ���ܺ�
        // s:string
        //
        value = (String) data.get(IVIMFields.mVeh_Cjh);
        // Assert.isNotNull(value, "���ܺŲ���Ϊ��");
        info.setCJH(value);
        //
        // 27
        // CLMC
        // ��������
        // s:string
        //
        value = (String) data.get(IVIMFields.mVeh_Clmc);
        // Assert.isNotNull(value, "�������Ʋ���Ϊ��");
        info.setCLMC(value);
        //
        // 28
        // CLPP
        // ����Ʒ��
        // s:string
        //
        value = (String) data.get(IVIMFields.mVeh_Clpp);
        // Assert.isNotNull(value, "����Ʒ�Ʋ���Ϊ��");
        info.setCLPP(value);
        //
        // 29
        // CLSBDH
        // ����ʶ�����
        // s:string
        //
        value = (String) data.get(IVIMFields.mVeh_Clsbdh);
        // Assert.isNotNull(value, "����ʶ����Ų���Ϊ��");
        info.setCLSBDH(value);
        //
        // 30
        // CLXH
        // �����ͺ�
        // s:string
        //
        value = (String) data.get(IVIMFields.mVeh_Clxh);
        // Assert.isNotNull(value, "�����ͺŲ���Ϊ��");
        info.setCLXH(value);
        //
        // 31
        // CLZZQYMC
        // ����������ҵ����
        // s:string
        //
        value = (String) data.get(IVIMFields.mVeh_Clzzqymc);
        // Assert.isNotNull(value, "����������ҵ���Ʋ���Ϊ��");
        info.setCLZZQYMC(value);
        //
        // 32
        // CLZZRQ
        // ������������
        // s:dateTime
        //
        value = (String) data.get(IVIMFields.mVeh_Clzzrq);
        // Assert.isNotNull(value, "������������");
        Date dValue = new SimpleDateFormat("yyyy��MM��dd��").parse(value);
        nowGregorianCalendar = new GregorianCalendar();
        nowGregorianCalendar.setTime(dValue);
        xmlDatetime = DatatypeFactory.newInstance().newXMLGregorianCalendar(nowGregorianCalendar);
        info.setCLZZRQ(xmlDatetime);
        //
        // 33
        // CPH
        // ��Ʒ��
        // s:string
        //
        value = (String) data.get(IVIMFields.D_23);
        // Assert.isNotNull(value, "��Ʒ�Ų���Ϊ��");
        info.setCPH(value);
        //
        // 34
        // CPSCDZ
        // ��Ʒ������ַ
        // s:string
        //
        value = (String) data.get(IVIMFields.mVeh_Cpscdz);
        // Assert.isNotNull(value, "��Ʒ������ַ����Ϊ��");
        info.setCPSCDZ(value);
        //
        // 35
        // CSYS
        // ������ɫ
        // s:string
        //
        value = (String) data.get(IVIMFields.mVeh_Csys);
        // Assert.isNotNull(value, "������ɫ����Ϊ��");
        info.setCSYS(value);
        //
        // 36
        // DPHGZBH
        // ���̺ϸ�֤���
        // s:string
        //
        value = (String) data.get(IVIMFields.mVeh_Dphgzbh);
        // Assert.isNotNull(value, "���̺ϸ�֤��Ų���Ϊ��");
        info.setDPHGZBH(value);
        //
        // 37
        // DPID
        // ����ID
        // s:string
        //
        value = (String) data.get(IVIMFields.mVeh_Dpid);
        // Assert.isNotNull(value, "����ID����Ϊ��");
        info.setDPID(value);
        //
        // 38
        // DPXH
        // �����ͺ�
        // s:string
        //
        value = (String) data.get(IVIMFields.mVeh_Dpxh);
        // Assert.isNotNull(value, "�����ͺŲ���Ϊ��");
        info.setDPXH(value);
        //
        // 39
        // EDZK
        // ��ؿ�
        // s:string
        //
        value = (String) data.get(IVIMFields.mVeh_Edzk);
        // Assert.isNotNull(value, "��ؿͲ���Ϊ��");
        info.setEDZK(value);
        //
        // 40
        // EDZZL
        // �������
        // s:string
        //
        value = (String) data.get(IVIMFields.mVeh_Edzzl);
        // Assert.isNotNull(value, "�����������Ϊ��");
        info.setEDZZL(value);
        //
        // 41
        // FDJH
        // ��������
        // s:string
        //
        value = (String) data.get(IVIMFields.mVeh_Fdjh);
        // Assert.isNotNull(value, "�������Ų���Ϊ��");
        info.setFDJH(value);
        //
        // 42
        // FDJXH
        // �������ͺ�
        // s:string
        //
        value = (String) data.get(IVIMFields.mVeh_Fdjxh);
        // Assert.isNotNull(value, "�������ͺŲ���Ϊ��");
        info.setFDJXH(value);
        //
        // 43
        // FZRQ
        // ��֤����
        // s:dateTime
        //
        value = (String) data.get(IVIMFields.mVeh_Fzrq);
        // Assert.isNotNull(value, "��֤���ڲ���Ϊ��");
        dValue = new SimpleDateFormat("yyyy��MM��dd��").parse(value);
        nowGregorianCalendar = new GregorianCalendar();
        nowGregorianCalendar.setTime(dValue);
        xmlDatetime = DatatypeFactory.newInstance().newXMLGregorianCalendar(nowGregorianCalendar);
        info.setFZRQ(xmlDatetime);
        //
        // 44
        // GBTHPS
        // �ְ嵯��Ƭ��
        // s:string
        //
        value = (String) data.get(IVIMFields.mVeh_Gbthps);
        // Assert.isNotNull(value, "�ְ嵯��Ƭ������Ϊ��");
        info.setGBTHPS(value);
        //
        // 45
        // GGSXRQ
        // ������Ч����
        // s:dateTime
        //
        value = (String) data.get(IVIMFields.mVeh_Ggsxrq);
        // Assert.isNotNull(value, "������Ч���ڲ���Ϊ��");
        dValue = new SimpleDateFormat("yyyy-MM-dd").parse(value);
        nowGregorianCalendar = new GregorianCalendar();
        nowGregorianCalendar.setTime(dValue);
        xmlDatetime = DatatypeFactory.newInstance().newXMLGregorianCalendar(nowGregorianCalendar);
        info.setGGSXRQ(xmlDatetime);
        //
        // 46
        // GL
        // ����
        // s:string
        //
        value = (String) data.get(IVIMFields.mVeh_Gl);
        // Assert.isNotNull(value, "���ʲ���Ϊ��");
        info.setGL(value);
        //
        // 47
        // HLJ
        // ���־�
        // s:string
        //
        value = (String) data.get(IVIMFields.mVeh_Hlj);
        // Assert.isNotNull(value, "���־಻��Ϊ��");
        info.setHLJ(value);
        //
        // 48
        // HXNBC
        // �����ڲ���
        // s:string
        //
        value = (String) data.get(IVIMFields.mVeh_Hxnbc);
        // Assert.isNotNull(value, "�����ڲ�������Ϊ��");
        info.setHXNBC(value);
        //
        // 49
        // HXNBG
        // �����ڲ���
        // s:string
        //
        value = (String) data.get(IVIMFields.mVeh_Hxnbg);
        // Assert.isNotNull(value, "�����ڲ���");
        info.setHXNBG(value);
        //
        // 50
        // HXNBK
        // �����ڲ���
        // s:string
        //
        value = (String) data.get(IVIMFields.mVeh_Hxnbk);
        // Assert.isNotNull(value, "�����ڲ���");
        info.setHXNBK(value);
        //
        // 51
        // HZDCZFS
        // ���ƶ�������ʽ
        // s:string
        //
        value = (String) data.get(IVIMFields.mVeh__Hzdczfs);
        // Assert.isNotNull(value, "���ƶ�������ʽ");
        info.setHZDCZFS(value);
        //
        // 52
        // HZDFS
        // ���ƶ���ʽ
        // s:string
        //
        value = (String) data.get(IVIMFields.mVeh__Hzdfs);
        // Assert.isNotNull(value, "���ƶ���ʽ");
        info.setHZDFS(value);
        //
        // 53
        // JSSZCRS
        // ��ʻ��׼������
        // s:string
        //
        value = (String) data.get(IVIMFields.mVeh_Clxh);
        // Assert.isNotNull(value, "��ʻ��׼����������Ϊ��");
        info.setJSSZCRS(value);
        //
        // 54
        // LTGG
        // ��̥���
        // s:string
        //
        value = (String) data.get(IVIMFields.mVeh_Ltgg);
        // Assert.isNotNull(value, "��̥��񲻿�Ϊ��");
        info.setLTGG(value);
        //
        // 55
        // LTS
        // ��̥��
        // s:string
        //
        value = (String) data.get(IVIMFields.mVeh_Lts);
        // Assert.isNotNull(value, "��̥������Ϊ��");
        info.setLTS(value);
        //
        // 56
        // PC
        // ����
        // s:string
        //
        value = (String) data.get(IVIMFields.mVeh_Ggpc);
        // Assert.isNotNull(value, "���β���Ϊ��");
        info.setPC(value);
        //
        // 57
        // PFBZ
        // �ŷű�׼
        // s:string
        //
        value = (String) data.get(IVIMFields.mVeh_Pfbz);
        // Assert.isNotNull(value, "�ŷű�׼����Ϊ��");
        info.setPFBZ(value);
        //
        // 58
        // PL
        // ����
        // s:string
        //
        value = (String) data.get(IVIMFields.mVeh_Pl);
        // Assert.isNotNull(value, "��������Ϊ��");
        info.setPL(value);
        //
        // 59
        // QLJ
        // ǰ�־�
        // s:string
        //
        value = (String) data.get(IVIMFields.mVeh_Qlj);
        // Assert.isNotNull(value, "ǰ�־಻��Ϊ��");
        info.setQLJ(value);
        //
        // 60
        // QYBZ
        // ��ҵ��׼
        // s:string
        //
        value = (String) data.get(IVIMFields.mVeh_Qybz);
        // Assert.isNotNull(value, " ��ҵ��׼����Ϊ��");
        info.setQYBZ(value);
        //
        // 61
        // QYQTXX
        // ��ҵ������Ϣ
        // s:string
        //
        value = (String) data.get(IVIMFields.mVeh_Qyqtxx);
        // Assert.isNotNull(value, "��ҵ������Ϣ����Ϊ��");
        info.setQYQTXX(value);
        //
        // 62
        // QZDCZFS
        // ǰ�ƶ�������ʽ
        // s:string
        //
        value = (String) data.get(IVIMFields.mVeh__Qzdczfs);
        // Assert.isNotNull(value, "ǰ�ƶ�������ʽ����Ϊ��");
        info.setQZDCZFS(value);
        //
        // 63
        // QZDFS
        // ǰ�ƶ���ʽ
        // s:string
        //
        value = (String) data.get(IVIMFields.mVeh__Qzdfs);
        // Assert.isNotNull(value, "ǰ�ƶ���ʽ����Ϊ��");
        info.setQZDFS(value);
        //
        // 64
        // RLZL
        // ȼ������
        // s:string
        //
        value = (String) data.get(IVIMFields.mVeh_Rlzl);
        // Assert.isNotNull(value, "ȼ�����಻��Ϊ��");
        info.setRLZL(value);
        //
        // 65
        // WKC
        // ������
        // s:string
        //
        value = (String) data.get(IVIMFields.mVeh_Wkc);
        // Assert.isNotNull(value, "����������Ϊ��");
        info.setWKC(value);
        //
        // 66
        // WKG
        // ������
        // s:string
        //
        value = (String) data.get(IVIMFields.mVeh_Wkg);
        // Assert.isNotNull(value, "�����߲���Ϊ��");
        info.setWKG(value);
        //
        // 67
        // WKK
        // ������
        // s:string
        //
        value = (String) data.get(IVIMFields.mVeh_Wkk);
        // Assert.isNotNull(value, "��������Ϊ��");
        info.setWKK(value);
        //
        // 68
        // ZBZL
        // ��������
        // s:string
        //
        value = (String) data.get(IVIMFields.mVeh_Zbzl);
        // Assert.isNotNull(value, "������������Ϊ��");
        info.setZBZL(value);
        //
        // 69
        // ZGCS
        // ��߳���
        // s:string
        //
        value = (String) data.get(IVIMFields.mVeh_Zgcs);
        // Assert.isNotNull(value, "��߳��ٲ���Ϊ��");
        info.setZGCS(value);
        //
        // 70
        // ZH
        // ���
        // s:string
        //
        value = (String) data.get(IVIMFields.mVeh_Zh);
        // Assert.isNotNull(value, "��ɲ���Ϊ��");
        info.setZH(value);
        //
        // 71
        // ZJ
        // ���
        // s:string
        //
        value = (String) data.get(IVIMFields.mVeh_Zj);
        // Assert.isNotNull(value, "��಻��Ϊ��");
        info.setZJ(value);
        //
        // 72
        // ZQYZZL
        // ׼ǣ��������
        // s:string
        //
        value = (String) data.get(IVIMFields.mVeh_Zqyzzl);
        // Assert.isNotNull(value, "׼ǣ������������Ϊ��");
        info.setZQYZZL(value);
        //
        // 73
        // ZS
        // ����
        // s:string
        //
        value = (String) data.get(IVIMFields.mVeh_Zs);
        // Assert.isNotNull(value, "��������Ϊ��");
        info.setZS(value);
        //
        // 74
        // ZXXS
        // ת����ʽ
        // s:string
        //
        value = (String) data.get(IVIMFields.mVeh_Zxxs);
        // Assert.isNotNull(value, "ת����ʽ����Ϊ��");
        info.setZXXS(value);
        //
        // 75
        // ZZL
        // ������
        // s:string
        //
        value = (String) data.get(IVIMFields.mVeh_Zzl);
        // Assert.isNotNull(value, "����������Ϊ��");
        info.setZZL(value);
        //
        // 76
        // ZZLLYXS
        // ����������ϵ��
        // s:string
        //
        value = (String) data.get(IVIMFields.mVeh_Zzllyxs);
        // Assert.isNotNull(value, "����������ϵ������Ϊ��");
        info.setZZLLYXS(value);
        //
        // 77
        // WZHGZBH
        // �����ϸ�֤���
        // s:string
        //
        value = (String) data.get(IVIMFields.mVeh__Wzghzbh);
        // Assert.isNotNull(value, "�����ϸ�֤��Ų���Ϊ��");
        info.setWZHGZBH(value);
        //
        // 78
        // PZXLH
        // �������к�
        // s:string

        value = (String) data.get(IVIMFields.mVeh__Pzxlh);
        // Assert.isNotNull(value, "�������кŲ���Ϊ��");
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
                throw new Exception("��COC֤���Ѿ���ӡ�������ظ���ӡ");
            } else {
                if (!COC_REPRINT) {
                    throw new Exception("���ò�����ֱ���ظ���ӡ���ϵ�COC֤�飬�����ظ���ӡ");
                }
            }
        }
        data.put(IVIMFields.PRINTACCOUNT, accountInfo);
        data.put(IVIMFields.PRINTDATE, date);
        data.put(IVIMFields.LIFECYCLE, IVIMFields.LC_PRINTED);
        ObjectId oid;
        if (dataItem == null || IVIMFields.LC_ABANDON.equals(dataItem.get(IVIMFields.LIFECYCLE))) {// û�д�VIM��COC֤�����COC֤���Ѿ�����
            oid = new ObjectId();
            data.put("_id", oid);
            List<DBObject> reclist = new ArrayList<DBObject>();
            reclist.add(rec);
            data.put(IVIMFields.ACTION_REC, reclist);

            // ����ϵͳ�ֶ�
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
                throw new Exception("��ȼ�ͱ�ʶ�Ѿ���ӡ�������ظ���ӡ");
            } else {
                if (!FL_REPRINT) {
                    throw new Exception("���ò�����ֱ���ظ���ӡ���ϵ�ȼ�ͱ�ʶ�������ظ���ӡ");
                }
            }
        }

        data.put(IVIMFields.PRINTACCOUNT, accountInfo);
        data.put(IVIMFields.PRINTDATE, date);
        data.put(IVIMFields.LIFECYCLE, IVIMFields.LC_PRINTED);
        ObjectId oid;
        if (dataItem == null || IVIMFields.LC_ABANDON.equals(dataItem.get(IVIMFields.LIFECYCLE))) {// û�д�VIM��COC֤�����COC֤���Ѿ�����
            oid = new ObjectId();
            data.put("_id", oid);
            List<DBObject> reclist = new ArrayList<DBObject>();
            reclist.add(rec);
            data.put(IVIMFields.ACTION_REC, reclist);

            // ����ϵͳ�ֶ�
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
            throw new Exception("û�����ô�ӡȼ�ͱ�ʶ�Ĵ�ӡ��");
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
            throw new Exception("û�����ô�ӡCOC֤��Ĵ�ӡ��");
        }
        String printerName = printerdata.get(IVIMFields.mVeh_PrinterName);

        String template = null;
        if (dbObject.get(IVIMFields.F_0_4).equals("M1")) {
            template = "cocm1.pdf";
        } else if (dbObject.get(IVIMFields.F_0_4).equals("N1")) {
            template = "cocn1.pdf";
        }
        if (template == null) {
            throw new Exception("û�ж�Ӧ�Ĵ�ӡģ��");
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
     * ����������ͬ����COC��Ϣ��
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
