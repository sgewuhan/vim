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
     * ��Ʒ��
     */
    public static final String FIELD_PRODUCT_CODE = "erp_product_code";
    /**
     * ��������
     */
    public static final String FIELD_ENGINEE_NUM = "safety_components_vin";
    /**
     * ��������
     */
    public static final String FIELD_MFT_DATE = "manufacture_date";

    public static final String COL_CERF = "certificateinfo";

    public static final String mVeh_Clztxx = "Veh_Clztxx";// ����״̬��Ϣ �ַ� 2 ȡֵΪQX��DP
    public static final String mVeh_Zchgzbh = "Veh_Zchgzbh";// �����ϸ�֤��� �ַ� 14
                                                            // 4λ��ҵ����+10λ˳��ųɹ����ô�ӡ���������ͨ�������Ի��15λ�������ϸ�֤���
    public static final String mVeh_Dphgzbh = "Veh_Dphgzbh";// ���̺ϸ�֤��� �ַ� 15 ȫ�ʽ15λ�����̷�ʽ����
    public static final String mVeh_Fzrq = "Veh_Fzrq";// ��֤���� �ַ� 14 YYYY��MM��DD��
    public static final String mVeh_Clzzqymc = "Veh_Clzzqymc";// ����������ҵ���� �ַ� 64
    public static final String mVeh_Qyid = "Veh_Qyid";// ��ҵID �ַ� 8 8λ������ҵID
    public static final String mVeh_Clfl = "Veh_Clfl";// �������� �ַ� 26 ����ԭ����Veh_cllx���磺����
    public static final String mVeh_Clmc = "Veh_Clmc";// �������� �ַ� 54 �磺�γ�
    public static final String mVeh_Clpp = "Veh_Clpp";// ����Ʒ�� �ַ� 30
    public static final String mVeh_Clxh = "Veh_Clxh";// �����ͺ� �ַ� 30
    public static final String mVeh_Csys = "Veh_Csys";// ������ɫ �ַ� 70 ������ɫ֮���á�/���ָ�
    public static final String mVeh_Dpxh = "Veh_Dpxh";// �����ͺ� �ַ� 30 ����QX��ʽʱʹ��
    public static final String mVeh_Dpid = "Veh_Dpid";// ����ID �ַ� 7
    public static final String mVeh_Clsbdh = "Veh_Clsbdh";// ����ʶ����� �ַ� 17
    public static final String mVeh_Cjh = "Veh_Cjh";// ���ܺ� �ַ� 25
    public static final String mVeh_Fdjh = "Veh_Fdjh";// �������� �ַ� 30
    public static final String mVeh_Fdjxh = "Veh_Fdjxh";// �������ͺ� �ַ� 20
    public static final String mVeh_Rlzl = "Veh_Rlzl";// ȼ������ �ַ� 30 ����ȼ��֮���á�/���ָ�
    public static final String mVeh_Pfbz = "Veh_Pfbz";// �ŷű�׼ �ַ� 60
    public static final String mVeh_Pl = "Veh_Pl";// ���� �ַ� 5
    public static final String mVeh_Gl = "Veh_Gl";// ���� �ַ� 7 ���ֹ���֮���á�/���ָ�
    public static final String mVeh_Zxxs = "Veh_Zxxs";// ת����ʽ �ַ� 6 �磺������
    public static final String mVeh_Qlj = "Veh_Qlj";// ǰ�־� �ַ� 4
    public static final String mVeh_Hlj = "Veh_Hlj";// ���־� �ַ� 54
    public static final String mVeh_Lts = "Veh_Lts";// ��̥�� �ַ� 2
    public static final String mVeh_Ltgg = "Veh_Ltgg";// ��̥��� �ַ� 20
    public static final String mVeh_Gbthps = "Veh_Gbthps";// �ְ嵯��Ƭ�� �ַ� 30
    public static final String mVeh_Zj = "Veh_Zj";// ��� �ַ� 60
    public static final String mVeh_Zh = "Veh_Zh";// ��� �ַ� 30
    public static final String mVeh_Zs = "Veh_Zs";// ���� �ַ� 1
    public static final String mVeh_Wkc = "Veh_Wkc";// ������ �ַ� 5
    public static final String mVeh_Wkk = "Veh_Wkk";// ������ �ַ� 4
    public static final String mVeh_Wkg = "Veh_Wkg";// ������ �ַ� 4
    public static final String mVeh_Hxnbc = "Veh_Hxnbc";// �����ڲ��� �ַ� 5
    public static final String mVeh_Hxnbk = "Veh_Hxnbk";// �����ڲ��� �ַ� 4
    public static final String mVeh_Hxnbg = "Veh_Hxnbg";// �����ڲ��� �ַ� 4
    public static final String mVeh_Zzl = "Veh_Zzl";// ������ �ַ� 8
    public static final String mVeh_Edzzl = "Veh_Edzzl";// ������� �ַ� 8
    public static final String mVeh_Zbzl = "Veh_Zbzl";// �������� �ַ� 8
    public static final String mVeh_Zzllyxs = "Veh_Zzllyxs";// ����������ϵ�� �ַ� 30
    public static final String mVeh_Zqyzzl = "Veh_Zqyzzl";// ׼ǣ�������� �ַ� 8
    public static final String mVeh_Edzk = "Veh_Edzk";// ��ؿ� �ַ� 3
    public static final String mVeh_Bgcazzdyxzzl = "Veh_Bgcazzdyxzzl";// ��ҳ������������������ �ַ� 8
    public static final String mVeh_Jsszcrs = "Veh_Jsszcrs";// ��ʻ��׼������ �ַ� 3
    public static final String mVeh_Zgcs = "Veh_Zgcs";// ��߳��� �ַ� 5
    public static final String mVeh_Clzzrq = "Veh_Clzzrq";// ������������ �ַ� 14 YYYY��MM��DD��
    public static final String mVeh_Bz = "Veh_Bz";// ��ע �ַ� 300
    public static final String mVeh_Qybz = "Veh_Qybz";// ��ҵ��׼ �ַ� 200 ����xxxx-xxxx��xxxx�����ĸ�ʽ�����м䲿��Ϊ����
    public static final String mVeh_Clscdwmc = "Veh_Clscdwmc";// ����������λ���� �ַ� 64
    public static final String mVeh_Cpscdz = "Veh_Cpscdz";// ����������λ��ַ �ַ� 70
    public static final String mVeh_Qyqtxx = "Veh_Qyqtxx";// ��ҵ������Ϣ �ַ� 400 ����Ŀ������Ҫ�س����еĵط�ʹ�á�%%����ʾ��
    public static final String mVeh_Zxzs = "Veh_Zxzs";// ת������� �ַ� 1 ȡֵΪ1��2��3��Ĭ��Ϊ1������ת����ĳ�����д��
    public static final String mVeh_Tmxx = "Veh_Tmxx";// �ϸ�֤������Ϣ �ַ� 1000
                                                      // ����ViewBarcodeInfo����ȡ�ϸ�֤�����ͨ�������Կɻ��������Ϣ��������Ŀ��ʽ������һ��
    public static final String mVeh_Jyw = "Veh_Jyw";// У����Ϣ �ַ� 255+
                                                    // ����PrtParaTbl�ɹ���ͨ�������Կɻ��У����Ϣ�����ϸ�֤�ϴ��á��䳤����ϸ�֤��Ϣ�������仯,������ñ�ע�͵ȴ������������ʹ洢��
    public static final String mVeh_Yh = "Veh_Yh";// �ͺ� �ַ� 30
    public static final String mVeh_Cddbj = "Veh_Cddbj";// ���綯��� �ַ� 1
    public static final String mVeh_Cpggh = "Veh_Cpggh";// �����
    public static final String mVeh_Ggpc = "Veh_Ggpc";
    public static final String mVeh_Ggsxrq = "Veh_Ggsxrq";

    public static final String[] COLOR_CODE = new String[] { "A", "B", "C", "D", "E", "F", "G",
            "H", "I", "J", "K", "L", "M" };
    public static final String[] COLOR_NAME = new String[] { "��ɫ", "��ɫ", "��ɫ", "��ɫ", "��ɫ", "��ɫ",
            "��ɫ", "��ɫ", "��ɫ", "��ɫ", "��ɫ", "����ɫ" };

    /**
     * ���������Ҫ��html�к����Ĳ�������һ��
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
        DBCollection c = DBActivator.getCollection(DB_NAME, COL_PRODUCTCODEINFO);
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
        DBCollection c = DBActivator.getCollection(DB_NAME, COL_COCINFO);
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
        DBCollection c = DBActivator.getCollection(DB_NAME, COL_CONFIGCODEINFO);
        ObjectId id = (ObjectId) productCodeData.get(IVIMFields.CFG_ID);
        DBObject query = new BasicDBObject().append("_id", id);
        DBObject data = c.findOne(query);
        if (data == null) {
            throw new Exception("��Ʒ��󶨵ĳ���һ���Լ�¼�Ѳ����ڣ��������ѱ�ɾ����\n�����½��ó�Ʒ��󶨳���һ�������ݺ����ԡ�");
        }
        return data;
    }

    public static DBObject getCOCInfoById(String productId) throws Exception {
        DBCollection c = DBActivator.getCollection(DB_NAME, COL_COCINFO);
        DBObject o = new BasicDBObject().append(IVIMFields.D_02, productId);
        DBObject data = c.findOne(o);
        if (data == null) {
            throw new Exception("�޷���ö�Ӧ��ƷID�ĳ���һ������Ϣ��\n���ڳ���һ������Ϣ�м���Ӧ��ƷID�ļ�¼\n��ƷID:" + productId);
        }
        return data;
    }

    public static DBObject getConfInfoById(String productId) throws Exception {
        DBCollection c = DBActivator.getCollection(DB_NAME, COL_CONFIGCODEINFO);
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
        // Veh_ClzzqymC F_0_1 ����������ҵ���� ӳ��
        result.put(mVeh_Clzzqymc, cocData.get(IVIMFields.F_0_1));
        // Veh_ClmC F_0_2_1 �������� ӳ��
        result.put(mVeh_Clmc, cocData.get(IVIMFields.F_0_2_1));
        // Veh_Clxh F_0_2C1 �����ͺ� ӳ��
        result.put(mVeh_Clxh, cocData.get(IVIMFields.F_0_2C1));
        // Veh_Dpxh CCC_04 �����ͺ� ӳ��
        result.put(mVeh_Dpxh, cocData.get(IVIMFields.CCC_04));

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
            result.put(mVeh_Csys, colorName);
        }

        // Veh_FDjh F_21a �������� ӳ��
        result.put(mVeh_Fdjh, cocData.get(IVIMFields.F_21a));
        // Veh_Rlzl F_25 ȼ������ ӳ��
        result.put(mVeh_Rlzl, cocData.get(IVIMFields.F_25));
        // Veh_Gl C_01 ���� ӳ��
        result.put(mVeh_Gl, cocData.get(IVIMFields.C_01));
        // Veh_Pl F_24 ���� ӳ��
        result.put(mVeh_Pl, cocData.get(IVIMFields.F_24));
        // Veh_PFbz C_06 �ŷű�׼ ӳ��
        result.put(mVeh_Pfbz, cocData.get(IVIMFields.C_06));
        // Veh_Yh C_03 �ͺ� ӳ��
        result.put(mVeh_Yh, cocData.get(IVIMFields.C_03));
        // Veh_WkC F_6_1 ������ ӳ��
        result.put(mVeh_Wkc, cocData.get(IVIMFields.F_6_1));
        // Veh_Wkk F_7_1 ������ ӳ��
        result.put(mVeh_Wkk, cocData.get(IVIMFields.F_7_1));
        // Veh_Wkg F_8 ������ ӳ��
        result.put(mVeh_Wkg, cocData.get(IVIMFields.F_8));
        // Veh_HxnbC F_C7_1 �����ڲ��� ӳ��
        result.put(mVeh_Hxnbc, cocData.get(IVIMFields.F_C7_1));
        // Veh_Hxnbk F_C7_2 �����ڲ��� ӳ��
        result.put(mVeh_Hxnbk, cocData.get(IVIMFields.F_C7_2));
        // Veh_Hxnbg F_C7_3 �����ڲ��� ӳ��
        result.put(mVeh_Hxnbg, cocData.get(IVIMFields.F_C7_3));
        // Veh_Gbthps F_C6 �ְ嵯��Ƭ�� ӳ��
        result.put(mVeh_Gbthps, cocData.get(IVIMFields.F_C6));
        // Veh_FDjxh F_C4 �������ͺ� ӳ��
        result.put(mVeh_Fdjxh, cocData.get(IVIMFields.F_C4));
        // Veh_Lts F_1_1a ��̥�� ӳ��
        result.put(mVeh_Lts, cocData.get(IVIMFields.F_1_1A));
        // Veh_Ltgg F_32A ��̥��� ӳ�� ��̥���ϸ�֤
        result.put(mVeh_Ltgg, cocData.get(IVIMFields.F_32A));
        // Veh_Qlj F_5a ǰ�־� ӳ��
        result.put(mVeh_Qlj, cocData.get(IVIMFields.F_5A));
        // Veh_Hlj F_5b ���־� ӳ��
        result.put(mVeh_Hlj, cocData.get(IVIMFields.F_5B));
        // Veh_Zj F_3 ��� ӳ��
        result.put(mVeh_Zj, cocData.get(IVIMFields.F_3));
        // Veh_Zh F_14_2 ��� ӳ��
        result.put(mVeh_Zh, cocData.get(IVIMFields.F_14_2));
        // Veh_Zs F_1 ���� ӳ��
        result.put(mVeh_Zs, cocData.get(IVIMFields.F_1));
        // Veh_Zxxs F_C34 ת����ʽ ӳ��
        result.put(mVeh_Zxxs, cocData.get(IVIMFields.F_C34));
        // Veh_Zzl F_14_1 ������ ӳ��
        result.put(mVeh_Zzl, cocData.get(IVIMFields.F_14_1));
        // Veh_Zbzl C_08 �������� ӳ��
        result.put(mVeh_Zbzl, cocData.get(IVIMFields.C_08));
        // Veh_EDzzl C_09 ������� ӳ��
        result.put(mVeh_Edzzl, cocData.get(IVIMFields.C_09));
        // Veh_Zzllyxs F_C5 ����������ϵ�� ӳ��
        result.put(mVeh_Zzllyxs, cocData.get(IVIMFields.F_C5));
        // Veh_Zqyzzl C_10 ׼ǣ�������� ӳ��
        result.put(mVeh_Zqyzzl, cocData.get(IVIMFields.C_10));
        // Veh_BgCazzDyxzzl C_04 ��ҳ������������������ ӳ��
        result.put(mVeh_Bgcazzdyxzzl, cocData.get(IVIMFields.C_04));
        // Veh_JsszCrs C_02 ��ʻ��׼������ ӳ��
        result.put(mVeh_Jsszcrs, cocData.get(IVIMFields.C_02));
        // Veh_EDzk F_42_1 ��ؿ� ӳ��
        result.put(mVeh_Edzk, cocData.get(IVIMFields.F_42_1));
        // Veh_ZgCs F_44 ��߳��� ӳ��
        result.put(mVeh_Zgcs, cocData.get(IVIMFields.F_44));
        // Veh_Clpp F_C0_2 ����Ʒ�� ӳ��
        result.put(mVeh_Clpp, cocData.get(IVIMFields.F_C0_2));
        // Veh_ClsbDh F_0_6b
        result.put(mVeh_Clsbdh, vin);
        // Veh_DpiD C_12 ����ID ӳ��
        result.put(mVeh_Dpid, cocData.get(IVIMFields.C_12));
        // Veh_Bz F_50 ��ע ӳ�� �ϸ�֤��ע
        result.put(mVeh_Bz, cocData.get(IVIMFields.C_11));
        // Veh_Clztxx C_23 ����״̬��Ϣ ֵת��
        result.put(mVeh_Clztxx, cocData.get(IVIMFields.C_23));
        // Veh_ClFl C_22 �������� ӳ��
        result.put(mVeh_Clfl, cocData.get(IVIMFields.C_22));
        // Veh_Zxzs C_13 ת������� ӳ��
        result.put(mVeh_Zxzs, cocData.get(IVIMFields.C_13));
        // Veh_CDDbj C_21 ���綯��� ֵת��
        result.put(mVeh_Cddbj, cocData.get(IVIMFields.C_21));
        // Veh_ClsCDwmC F_0_1 ����������λ���� ӳ��
        result.put(mVeh_Clscdwmc, cocData.get(IVIMFields.F_0_1));
        // Veh_CpsCDz D_04 ����������λ��ַ ӳ��
        result.put(mVeh_Cpscdz, cocData.get(IVIMFields.D_04));
        // Veh_QyiD C_14 ��ҵID ӳ��
        String companyId = (String) cocData.get(IVIMFields.C_14);
        result.put(mVeh_Qyid, companyId);
        // Veh_Qybz C_17 ��ҵ��׼ ӳ��
        result.put(mVeh_Qybz, cocData.get(IVIMFields.C_17));
        // Veh_Cpggh D_02 ��Ʒ����� ֵת�� �ɹ�����Ϣ���,11λ�ַ���������������к�14λ�ַ�����25λ
        String productPublicId = (String) cocData.get(IVIMFields.D_02);
        // �������
        String confid = (String) confData.get(IVIMFields.H_01);
        if (Utils.isNullOrEmpty(confid)) {
            confid = (String) confData.get(IVIMFields.H_02);
        }
        String string = productPublicId + confid;
        if (!debug && string.length() != 25) {
            throw new Exception("�޷�ȡ����ȷ�Ĳ�Ʒ����š�\nֵת��  �ɹ�����Ϣ���,11λ�ַ���������������к�14λ�ַ�����25λ");
        }
        result.put(mVeh_Cpggh, string);
        // Veh_GgpC D_18 �������� ӳ��
        result.put(mVeh_Ggpc, cocData.get(IVIMFields.D_18));
        // Veh_Ggsxrq D_01 ������Ч���� ӳ��
        Object value = cocData.get(IVIMFields.D_01);
        try {
            Date date;
            date = Utils.getDateValue(value, true);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            result.put(mVeh_Ggsxrq, sdf.format(date));
        } catch (Exception e) {
            throw new Exception("�޷�ȡ����ȷ�Ĺ�����Ч���ڡ�\n" + e.getMessage());
        }

        // Veh_ZChgzbh �����ϸ�֤��� ���� ����ֵ4λ��ҵ����+10λ˳��ųɹ����ô�ӡ���������ͨ�������Ի��15λ�������ϸ�֤���

        // �༭ʱ����
        // DBCollection ids = DBActivator.getCollection(DB_NAME, "ids");
        // String seq = DBUtil.getIncreasedID(ids, "Veh_ZChgzbh", "0", 10);
        // string = companyId+seq;
        // if(string.length()!=14){
        // throw new Exception("�޷�ȡ����ȷ�������ϸ�֤��š�\n4λ��ҵ����+10λ˳���");
        // }
        // result.put(mVeh_Zchgzbh, );

        // Veh_Dphgzbh ȫ�ʽ15λ�����̷�ʽ���� ����
        // result.put(mVeh_Dphgzbh, cocData.get(IVIMFields.F_0_1));

        // Veh_Fzrq ��֤���� �ַ� 14 YYYY��MM��DD��
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy��MM��dd��");
        string = sdf.format(new Date());
        result.put(mVeh_Fzrq, string);
        // Veh_Cjh vin vin
        result.put(mVeh_Cjh, "");
        // Veh_Clzzrq ������������
        String mftDate = (String) mesRawData.getValue(FIELD_MFT_DATE);
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        Date _date = sdf2.parse(mftDate);
        string = sdf.format(_date);
        result.put(mVeh_Clzzrq, string);
        // Veh_Qyqtxx �����е�����
        result.put(mVeh_Qyqtxx, cocData.get(IVIMFields.C_18));

        // // Veh_Tmxx ����
        // result.put(mVeh_Tmxx, cocData.get(IVIMFields.F_0_1));
        // Veh_Jyw ����
        // result.put(mVeh_Jyw, cocData.get(IVIMFields.F_0_1));
        // Veh_PrinterName ����
        result.put(mVeh_PrinterName, "<����>");
        // Veh_PrintPosLeFt ����
        result.put(mVeh_PrintPosLeft, "15");
        // Veh_PrintPosTop ����
        result.put(mVeh_PrintPosTop, "15");
        // Veh_ConneCt ����
        result.put(mVeh_Connect, "com1");
        // Veh_BauD ����
        result.put(mVeh_Baud, "9600");
        // Veh_Parity ����
        result.put(mVeh_Parity, "N");
        // Veh_Databits ����
        result.put(mVeh_Databits, "8");
        // Veh_Stopbits ����
        result.put(mVeh_Stopbits, "1");
        // Veh_Zzbh ����
        result.put(mVeh_Zzbh, "<����>");
        // Veh_Dywym ����
        result.put(mVeh_Dywym, "");// ����ֵ������
        return result;
    }

}
