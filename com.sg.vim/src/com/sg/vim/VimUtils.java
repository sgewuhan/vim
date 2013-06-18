package com.sg.vim;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.bson.types.ObjectId;
import org.eclipse.core.runtime.Assert;
import org.eclipse.rap.rwt.RWT;
import org.eclipse.rap.rwt.client.service.UrlLauncher;
import org.eclipse.swt.browser.Browser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.mobnut.commons.util.ITransferRule;
import com.mobnut.commons.util.ModelTransfer;
import com.mobnut.commons.util.Utils;
import com.mobnut.db.DBActivator;
import com.mobnut.db.utils.DBUtil;
import com.mobnut.portal.user.UserSessionContext;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;
import com.sg.sqldb.DDB;
import com.sg.sqldb.utility.SQLResult;
import com.sg.sqldb.utility.SQLRow;
import com.sg.sqldb.utility.SQLUtil;
import com.sg.ui.UI;
import com.sg.ui.UIUtils;
import com.sg.ui.model.DataObject;
import com.sg.ui.model.DataObjectCollectionService;
import com.sg.ui.model.DataObjectEditorInput;
import com.sg.ui.part.editor.IEditorSaveHandler;
import com.sg.ui.registry.config.DataEditorConfigurator;
import com.sg.vim.model.BasicInfo;
import com.sg.vim.model.COCInfo;
import com.sg.vim.model.IVIMFields;
import com.sg.vim.service.ArrayOfString;
import com.sg.vim.service.NameValuePair;
import com.sg.vim.service.OperateResult;
import com.sg.vim.service.fuellabel.ArrayOfRllxParamEntity;
import com.sg.vim.service.fuellabel.ArrayOfVehicleBasicInfo;
import com.sg.vim.service.fuellabel.FuelDataSysSTD;
import com.sg.vim.service.fuellabel.FuelDataSysSTDSoap;
import com.sg.vim.service.fuellabel.RllxParamEntity;
import com.sg.vim.service.fuellabel.VehicleBasicInfo;
import com.sg.vim.service.vecc_sepa.WSVin;
import com.sg.vim.service.vecc_sepa.WSVinSoap;
import com.sg.vim.service.vidc.ArrayOfCertificateInfo;
import com.sg.vim.service.vidc.CertificateInfo;
import com.sg.vim.service.vidc.CertificateRequestService;
import com.sg.vim.service.vidc.CertificateRequestServiceSoap;

public class VimUtils {

    public static boolean debug = false;

    public static String HARDWAREID;

    public static String HD_USER;

    public static boolean COC_REPRINT;

    public static boolean FL_REPRINT;

    public static String LOCAL_SERVER;

    public static final String MES_DB = "mes";

    private static final String MES_DB2 = "mes2";

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
    public static final String FIELD_PRODUCT_CODE = "erp_product_code";
    /**
     * ��������
     */
    protected static final String FIELD_ENGINEE_NUM = "safety_components_vin";
    /**
     * ��������
     */
    protected static final String FIELD_MFT_DATE = "manufacture_date";

    // *******/

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

    public static String LOCAL_CERT_ARRESS = null;

    public static String FUELLABEL_USERNAME;

    public static String FUELLABEL_PASSWORD;

    public static String FUELLABEL_OKEY;

    public static String ENV_USERNAME;

    public static String ENV_PASSWORD;

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
        // **********************************************����ʹ��
        if (debug || vin.equals("LMBC4EAE5DU012961")) {

            SQLRow row = new SQLRow(new String[] { FIELD_PRODUCT_CODE, FIELD_MFT_DATE,
                    FIELD_ENGINEE_NUM, "VIN" });
            row.setValue(FIELD_ENGINEE_NUM, "BJ413AC09D00042");
            row.setValue(FIELD_MFT_DATE, "2012-12-21");
            row.setValue(FIELD_PRODUCT_CODE, "88T151ACBA4-U3A1");
            row.setValue("VIN", vin);
            return row;
        }
        // **********************************************����ʹ��
        if (debug || vin.equals("LMBC4EAE5DU012961")) {

            SQLRow row = new SQLRow(new String[] { FIELD_PRODUCT_CODE, FIELD_MFT_DATE,
                    FIELD_ENGINEE_NUM, "VIN" });
            row.setValue(FIELD_ENGINEE_NUM, "BJ413AC09D00042");
            row.setValue(FIELD_MFT_DATE, "2012-12-21");
            row.setValue(FIELD_PRODUCT_CODE, "88T151ACBA4-U3A1");
            row.setValue("VIN", vin);
            return row;
        }
        // **********************************************************
        if (debug) {
            Connection conn = DDB.getDefault().getConnection("MES_DB");
            if (conn == null) {
                String[] arr1 = new String[] { "LMBC4EAE5DU012961", "LNBMDLAA4CU000484",
                        "LNBMDLAA6CU000485", "LNBMDLAA1CU000572", "LNBMDLAA3CU000573" };
                String[] arr2 = new String[] { "LNBMDLAA7CU000480" };
                if (Utils.inArray(vin, arr1)) {
                    SQLRow row = new SQLRow(new String[] { FIELD_PRODUCT_CODE, FIELD_MFT_DATE,
                            FIELD_ENGINEE_NUM, "VIN" });
                    row.setValue(FIELD_ENGINEE_NUM, "BJ413AC09D00042");
                    row.setValue(FIELD_MFT_DATE, "2012-12-21");
                    row.setValue(FIELD_PRODUCT_CODE, "88T151ACBA4-U3A1");
                    row.setValue("VIN", vin);
                    return row;
                }
                if (Utils.inArray(vin, arr2)) {
                    SQLRow row = new SQLRow(new String[] { FIELD_PRODUCT_CODE, FIELD_MFT_DATE,
                            FIELD_ENGINEE_NUM, "VIN" });
                    row.setValue(FIELD_ENGINEE_NUM, "BJ413AC09D00042");
                    row.setValue(FIELD_MFT_DATE, "2012-12-26");
                    row.setValue(FIELD_PRODUCT_CODE, "88T151ACBA4-U3C1");
                    row.setValue("VIN", vin);
                    return row;
                }
            }
        }
        // **********************************************************

        SQLResult res = SQLUtil.SQL_QUERY(MES_DB, SQL_GET_PRODUCINFOR + vin + "'");

        if (res.size() == 0) {
            // ��ѯ���ؿ��еĳ�Ʒ��¼
            DBCollection vinCol = DBActivator.getCollection(IVIMFields.DB_NAME, IVIMFields.COL_VIN);
            DBObject dbo = vinCol.findOne(new BasicDBObject().append("VIN", vin));
            if (dbo == null) {
                throw new Exception("�޷�ȷ��VIN��Ӧ�ĳ�Ʒ��¼��\nVIN:" + vin);
            } else {
                SQLRow row = new SQLRow(new String[] { FIELD_PRODUCT_CODE, FIELD_MFT_DATE,
                        FIELD_ENGINEE_NUM, "VIN" });
                row.setValue(FIELD_ENGINEE_NUM, dbo.get(FIELD_ENGINEE_NUM));
                row.setValue(FIELD_MFT_DATE, dbo.get(FIELD_MFT_DATE));
                row.setValue(FIELD_PRODUCT_CODE, dbo.get(FIELD_PRODUCT_CODE));
                row.setValue("VIN", vin);
                return row;
            }
        } else {
            SQLRow row = res.getData().get(0);
            String mftDate = row.getText(FIELD_MFT_DATE);
            try {
                Date date = new SimpleDateFormat(Utils.SDF_DATE).parse(mftDate);
                long intv = (new Date().getTime() - date.getTime()) / (24 * 60 * 60 * 1000);
                if (intv > 30 || intv < 0) {
                    throw new Exception("MES���ݿ���VIN��Ӧ�ĳ�Ʒ��¼,�������ڲ������ڵ�ǰ����30��,�Ҳ������ڵ�ǰ���ڡ�\nVIN:" + vin);
                }
            } catch (Exception e) {
                throw new Exception("MES���ݿ���VIN��Ӧ�ĳ�Ʒ��¼,�������ڲ��Ϲ档\nVIN:" + vin);
            }

            return row;

        }

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

    public static DBObject getCOCInfo2(DBObject productCodeData) {
        DBCollection c = DBActivator.getCollection(IVIMFields.DB_NAME, IVIMFields.COL_COCINFO);
        ObjectId id = (ObjectId) productCodeData.get(IVIMFields.COC_ID2);
        DBObject query = new BasicDBObject().append("_id", id);
        DBObject data = c.findOne(query);
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
            String vin, boolean isDP, ObjectId reassemblyId) throws Exception {
        DataEditorConfigurator conf = (DataEditorConfigurator) UI.getEditorRegistry()
                .getConfigurator(isDP ? DPCERT_EDITOR : QXCERT_EDITOR);
        DBCollection c = DBActivator.getCollection(IVIMFields.DB_NAME, IVIMFields.COL_CERF);
        DBObject dbObject = transferCerfData(cocData, confData, productCodeData, mesRawData, vin,
                isDP);
        DataObject data = new DataObject(c, dbObject);
        if (reassemblyId != null) {
            data.setValue("_id", reassemblyId);
        }
        DataObjectEditorInput editorInput = new DataObjectEditorInput(data, conf, saveHandler);
        editorInput.setEditable(!debug);
        return editorInput;
    }

    public static DataObjectEditorInput getCerfInput(DBObject cocData, DBObject confData,
            DBObject productCodeData, SQLRow mesRawData, IEditorSaveHandler saveHandler,
            String vin, boolean isDP) throws Exception {
        return getCerfInput(cocData, confData, productCodeData, mesRawData, saveHandler, vin, isDP,
                null);
    }

    public static DataObjectEditorInput getCOCInput(DBObject cocData, DBObject confData,
            DBObject productCodeData, SQLRow mesRawData, IEditorSaveHandler saveHandler,
            String vin, ObjectId reassemblyId) {
        DataEditorConfigurator conf = (DataEditorConfigurator) UI.getEditorRegistry()
                .getConfigurator(COCPAPER_EDITOR);
        DBCollection c = DBActivator.getCollection(IVIMFields.DB_NAME, IVIMFields.COL_COCPAPER);
        DBObject dbObject = transferCOCData(cocData, confData, productCodeData, mesRawData, vin);
        DataObject data = new DataObject(c, dbObject);
        if (reassemblyId != null) {
            data.setValue("_id", reassemblyId);
        }
        DataObjectEditorInput editorInput = new DataObjectEditorInput(data, conf, saveHandler);
        editorInput.setEditable(!debug);
        return editorInput;
    }

    public static DataObjectEditorInput getCOCInput(DBObject cocData, DBObject confData,
            DBObject productCodeData, SQLRow mesRawData, IEditorSaveHandler saveHandler, String vin) {
        return getCOCInput(cocData, confData, productCodeData, mesRawData, saveHandler, vin, null);
    }

    public static DataObjectEditorInput getFLInput(DBObject cocData, DBObject confData,
            DBObject productCodeData, SQLRow mesRawData, IEditorSaveHandler saveHandler,
            String vin, ObjectId reassemblyId) {
        DataEditorConfigurator conf = (DataEditorConfigurator) UI.getEditorRegistry()
                .getConfigurator(FUELLABEL_EDITOR);
        DBCollection c = DBActivator.getCollection(IVIMFields.DB_NAME, IVIMFields.COL_FUELABEL);
        DBObject dbObject = transferFuelLabelData(cocData, confData, productCodeData, mesRawData,
                vin);
        DataObject data = new DataObject(c, dbObject);
        if (reassemblyId != null) {
            data.setValue("_id", reassemblyId);
        }
        DataObjectEditorInput editorInput = new DataObjectEditorInput(data, conf, saveHandler);
        editorInput.setEditable(!debug);

        return editorInput;
    }

    public static DataObjectEditorInput getFLInput(DBObject cocData, DBObject confData,
            DBObject productCodeData, SQLRow mesRawData, IEditorSaveHandler saveHandler, String vin) {
        return getFLInput(cocData, confData, productCodeData, mesRawData, saveHandler, vin, null);
    }

    private static DBObject transferFuelLabelData(DBObject cocData, DBObject confData,
            DBObject productCodeData, SQLRow mesRawData, String vin) {
        BasicDBObject result = new BasicDBObject();

        // f_0_2c1 �����ͺ�
        result.put(IVIMFields.F_0_2C1, cocData.get(IVIMFields.F_0_2C1));
        // f_c4 �������ͺ�
        result.put(IVIMFields.F_C4, cocData.get(IVIMFields.F_C4));
        // f_25 ȼ������
        result.put(IVIMFields.F_25, cocData.get(IVIMFields.F_25));
        // f_24 ����
        result.put(IVIMFields.F_24, cocData.get(IVIMFields.F_24));
        // c_01 ����
        result.put(IVIMFields.C_01, cocData.get(IVIMFields.C_01));
        // f_28 ��������ʽ
        result.put(IVIMFields.F_28, cocData.get(IVIMFields.F_28));
        // d_22 ������ʽ
        result.put(IVIMFields.D_22, cocData.get(IVIMFields.D_22));
        // c_08 ��������
        result.put(IVIMFields.C_08, cocData.get(IVIMFields.C_08));
        // �������
        result.put(IVIMFields.F_14_1, cocData.get(IVIMFields.F_14_1));
        // g_32
        result.put(IVIMFields.G_32, cocData.get(IVIMFields.G_32));
        // d_14 �����ͺ�
        result.put(IVIMFields.D_14, cocData.get(IVIMFields.D_14));
        // d_15 �н��ͺ�
        result.put(IVIMFields.D_15, cocData.get(IVIMFields.D_15));
        // d_16 �ۺ��ͺ�
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

        // ��ӡ����Ҫ�����ϴ���Ҫ��
        // ����������ҵ
        result.put(IVIMFields.F_0_1, cocData.get(IVIMFields.F_0_1));
        // �������
        result.put(IVIMFields.F_0_4, cocData.get(IVIMFields.F_0_4));
        // ��߳���
        result.put(IVIMFields.F_44, cocData.get(IVIMFields.F_44));
        // ��̥���
        result.put(IVIMFields.F_32A, cocData.get(IVIMFields.F_32A));
        // ���
        result.put(IVIMFields.F_3, cocData.get(IVIMFields.F_3));
        // ��������
        result.put(IVIMFields.F_0_2_1, cocData.get(IVIMFields.F_0_2_1));
        // ��λ����
        result.put(IVIMFields.D_17, cocData.get(IVIMFields.D_17));
        // ��ؿ�
        result.put(IVIMFields.F_42_1, cocData.get(IVIMFields.F_42_1));
        // ǰ�־�
        result.put(IVIMFields.F_5A, cocData.get(IVIMFields.F_5A));
        // ���־�
        result.put(IVIMFields.F_5B, cocData.get(IVIMFields.F_5B));
        // ���۳���
        result.put(IVIMFields.D_20, cocData.get(IVIMFields.D_20));
        // coc id
        result.put(IVIMFields.COC_ID, cocData.get("_id"));

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
        if (!Utils.isNullOrEmptyString(qzj) && !Utils.isNullOrEmptyString(hzj)) {
            result.put(IVIMFields.F_4_1_1_O, "" + qzj + "/" + hzj);
        }

        // ������λ��
        Object zws = cocData.get(IVIMFields.F_42_1);
        if (Utils.isNullOrEmptyString(zws)) {
            zws = cocData.get(IVIMFields.C_02);
        }

        result.put(IVIMFields.F_42_1_O, zws);

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
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
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
        String colorName = getColorNameByCode(colorCode);
        result.put(IVIMFields.F_38, colorName);

        // ����ְ�ɣ��ڳ�Ʒ����ȡ
        Object fc6 = productCodeData.get(IVIMFields.F_C6);
        result.put(IVIMFields.F_C6, fc6);

        // coc id
        result.put(IVIMFields.COC_ID, cocData.get("_id"));
        return result;
    }

    public static DBObject transferCerfData(DBObject cocData, DBObject confData,
            DBObject productCodeData, SQLRow mesRawData, String vin, boolean isDP) throws Exception {
        BasicDBObject result = new BasicDBObject();
        // Veh_ClzzqymC F_0_1 ����������ҵ���� ӳ��
        Object value = cocData.get(IVIMFields.F_0_1);
        if (!Utils.isNullOrEmptyString(value) && (!"������".equals(value)))
            result.put(IVIMFields.mVeh_Clzzqymc, value);

        // Veh_ClmC F_0_2_1 �������� ӳ��
        value = cocData.get(IVIMFields.F_0_2_1);
        if (!Utils.isNullOrEmptyString(value) && (!"������".equals(value)))
            result.put(IVIMFields.mVeh_Clmc, value);

        // Veh_Clxh F_0_2C1 �����ͺ� ӳ��
        value = cocData.get(IVIMFields.F_0_2C1);
        if (!Utils.isNullOrEmptyString(value) && (!"������".equals(value)))
            result.put(IVIMFields.mVeh_Clxh, value);

        // Veh_Dpxh CCC_04 �����ͺ� ӳ��
        if (!isDP) {
            value = cocData.get(IVIMFields.CCC_04);
            if (!Utils.isNullOrEmptyString(value) && (!"������".equals(value)))
                result.put(IVIMFields.mVeh_Dpxh, value);
        }

        // Veh_Csys F_38 ������ɫ �ӳ�Ʒ�ֵ�ȡ
        // if (!isDP) {
        String sn = mesRawData.getText(FIELD_PRODUCT_CODE);
        String colorCode = sn.substring(14, 15);
        String colorName = getColorNameByCode(colorCode);
        if (Utils.isNullOrEmpty(colorName)) {
            throw new Exception("�޷�����ɫ���ձ����ҵ���Ʒ���Ӧ����ɫ\n��ɫidΪ:" + colorCode);
        }
        result.put(IVIMFields.mVeh_Csys, colorName);

        // Veh_FDjh F_21a �������� ӳ��
        String code = (String) mesRawData.getValue("SAFETY_COMPONENTS_VIN");
        result.put(IVIMFields.mVeh_Fdjh, code.substring(code.length() - 9));

        // Veh_Rlzl F_25 ȼ������ ӳ��
        value = cocData.get(IVIMFields.F_25);
        if (!Utils.isNullOrEmptyString(value) && (!"������".equals(value)))
            result.put(IVIMFields.mVeh_Rlzl, value);

        // Veh_Gl C_01 ���� ӳ��
        value = cocData.get(IVIMFields.C_01);
        if (!Utils.isNullOrEmptyString(value) && (!"������".equals(value)))
            result.put(IVIMFields.mVeh_Gl, value);

        // Veh_Pl F_24 ���� ӳ��
        value = cocData.get(IVIMFields.F_24);
        if (!Utils.isNullOrEmptyString(value) && (!"������".equals(value)))
            result.put(IVIMFields.mVeh_Pl, value);

        // Veh_PFbz C_06 �ŷű�׼ ӳ��
        value = cocData.get(IVIMFields.C_06);
        if (!Utils.isNullOrEmptyString(value) && (!"������".equals(value)))
            result.put(IVIMFields.mVeh_Pfbz, value);

        // Veh_Yh C_03 �ͺ� ӳ��
        value = cocData.get(IVIMFields.D_16);
        if (!Utils.isNullOrEmptyString(value) && (!"������".equals(value)))
            result.put(IVIMFields.mVeh_Yh, value);// ȡC_03����D_16?

        // Veh_WkC F_6_1 ������ ӳ��
        value = cocData.get(IVIMFields.F_6_1);
        if (!Utils.isNullOrEmptyString(value) && (!"������".equals(value)))
            result.put(IVIMFields.mVeh_Wkc, value);

        // Veh_Wkk F_7_1 ������ ӳ��
        value = cocData.get(IVIMFields.F_7_1);
        if (!Utils.isNullOrEmptyString(value) && (!"������".equals(value)))
            result.put(IVIMFields.mVeh_Wkk, value);

        // Veh_Wkg F_8 ������ ӳ��
        value = cocData.get(IVIMFields.F_8);
        if (!Utils.isNullOrEmptyString(value) && (!"������".equals(value)))
            result.put(IVIMFields.mVeh_Wkg, value);

        if (!isDP) {
            // Veh_HxnbC F_C7_1 �����ڲ��� ӳ��
            value = cocData.get(IVIMFields.F_C7_1);
            if (!Utils.isNullOrEmptyString(value) && (!"������".equals(value)))
                result.put(IVIMFields.mVeh_Hxnbc, value);

            // Veh_Hxnbk F_C7_2 �����ڲ��� ӳ��
            value = cocData.get(IVIMFields.F_C7_2);
            if (!Utils.isNullOrEmptyString(value) && (!"������".equals(value)))
                result.put(IVIMFields.mVeh_Hxnbk, value);

            // Veh_Hxnbg F_C7_3 �����ڲ��� ӳ��
            value = cocData.get(IVIMFields.F_C7_3);
            if (!Utils.isNullOrEmptyString(value) && (!"������".equals(value)))
                result.put(IVIMFields.mVeh_Hxnbg, value);
        }

        // Veh_Gbthps F_C6 �ְ嵯��Ƭ�� ӳ��
        // ����ְ�ɣ��ڳ�Ʒ����ȡ
        value = productCodeData.get(IVIMFields.F_C6);
        if (!Utils.isNullOrEmptyString(value) && (!"������".equals(value)))
            result.put(IVIMFields.mVeh_Gbthps, value);

        // Veh_FDjxh F_C4 �������ͺ� ӳ��
        value = cocData.get(IVIMFields.F_C4);
        if (!Utils.isNullOrEmptyString(value) && (!"������".equals(value)))
            result.put(IVIMFields.mVeh_Fdjxh, value);

        // Veh_Lts F_1_1 ��̥�� ӳ��
        value = cocData.get(IVIMFields.F_1_1);
        if (!Utils.isNullOrEmptyString(value) && (!"������".equals(value)))
            result.put(IVIMFields.mVeh_Lts, value);

        // Veh_Ltgg F_32A ��̥��� ӳ�� ��̥���ϸ�֤
        value = cocData.get(IVIMFields.F_32A);
        if (!Utils.isNullOrEmptyString(value) && (!"������".equals(value)))
            result.put(IVIMFields.mVeh_Ltgg, value);

        // Veh_Qlj F_5a ǰ�־� ӳ��
        value = cocData.get(IVIMFields.F_5A);
        if (!Utils.isNullOrEmptyString(value) && (!"������".equals(value)))
            result.put(IVIMFields.mVeh_Qlj, value);

        // Veh_Hlj F_5b ���־� ӳ��
        value = cocData.get(IVIMFields.F_5B);
        if (!Utils.isNullOrEmptyString(value) && (!"������".equals(value)))
            result.put(IVIMFields.mVeh_Hlj, value);

        // Veh_Zj F_3 ��� ӳ��
        value = cocData.get(IVIMFields.F_3);
        if (!Utils.isNullOrEmptyString(value) && (!"������".equals(value)))
            result.put(IVIMFields.mVeh_Zj, value);

        // Veh_Zh F_14_2 ��� ӳ��
        value = cocData.get(IVIMFields.F_14_2);
        if (!Utils.isNullOrEmptyString(value) && (!"������".equals(value)))
            result.put(IVIMFields.mVeh_Zh, value);

        // Veh_Zs F_1 ���� ӳ��
        value = cocData.get(IVIMFields.F_1);
        if (!Utils.isNullOrEmptyString(value) && (!"������".equals(value)))
            result.put(IVIMFields.mVeh_Zs, value);

        // Veh_Zxxs F_C34 ת����ʽ ӳ��
        value = cocData.get(IVIMFields.F_C34);
        if (!Utils.isNullOrEmptyString(value) && (!"������".equals(value)))
            result.put(IVIMFields.mVeh_Zxxs, value);

        // Veh_Zzl F_14_1 ������ ӳ��
        value = cocData.get(IVIMFields.F_14_1);
        if (!Utils.isNullOrEmptyString(value) && (!"������".equals(value)))
            result.put(IVIMFields.mVeh_Zzl, value);

        // Veh_Zbzl C_08 �������� ӳ��
        value = cocData.get(IVIMFields.C_08);
        if (!Utils.isNullOrEmptyString(value) && (!"������".equals(value)))
            result.put(IVIMFields.mVeh_Zbzl, value);

        // Veh_EDzzl C_09 ������� ӳ��
        value = cocData.get(IVIMFields.C_09);
        if (!Utils.isNullOrEmptyString(value) && (!"������".equals(value)))
            result.put(IVIMFields.mVeh_Edzzl, value);

        // Veh_Zzllyxs F_C5 ����������ϵ�� ӳ��
        value = cocData.get(IVIMFields.F_C5);
        if (!Utils.isNullOrEmptyString(value) && (!"������".equals(value)))
            result.put(IVIMFields.mVeh_Zzllyxs, value);

        // Veh_Zqyzzl C_10 ׼ǣ�������� ӳ��
        value = cocData.get(IVIMFields.C_10);
        if (!Utils.isNullOrEmptyString(value) && (!"������".equals(value)))
            result.put(IVIMFields.mVeh_Zqyzzl, value);

        // Veh_BgCazzDyxzzl C_04 ��ҳ������������������ ӳ��
        value = cocData.get(IVIMFields.C_04);
        if (!Utils.isNullOrEmptyString(value) && (!"������".equals(value)))
            result.put(IVIMFields.mVeh_Bgcazzdyxzzl, value);

        // Veh_JsszCrs C_02 ��ʻ��׼������ ӳ�� ȫ�����̱���
        if (isDP) {
            value = cocData.get(IVIMFields.C_02);
            // "��ʻ��׼�������ڵ��̺ϸ�֤�����в���Ϊ��";
            if (!Utils.isNullOrEmptyString(value) && (!"������".equals(value)))
                result.put(IVIMFields.mVeh_Jsszcrs, value);
        } else {
            if ("N1".equals(cocData.get(IVIMFields.F_0_4))) {
                value = cocData.get(IVIMFields.C_02);
                // ��ʻ��׼��������N1��ȫ��ϸ�֤�����в���Ϊ��
                if (!Utils.isNullOrEmptyString(value) && (!"������".equals(value)))
                    result.put(IVIMFields.mVeh_Jsszcrs, value);
            }
        }

        // Veh_EDzk F_42_1 ��ؿ� ӳ��
        if (!isDP) {
            // �����M1�࣬ȡ��ؿ�
            if ("M1".equals(cocData.get(IVIMFields.F_0_4))) {
                value = cocData.get(IVIMFields.F_42_1);
                // "��ؿ�����M1��ȫ��ϸ�֤�����в���Ϊ��");
                if (!Utils.isNullOrEmptyString(value) && (!"������".equals(value)))
                    result.put(IVIMFields.mVeh_Edzk, value);
            }
        }

        // Veh_ZgCs F_44 ��߳��� ӳ��
        value = cocData.get(IVIMFields.F_44);
        if (!Utils.isNullOrEmptyString(value) && (!"������".equals(value)))
            result.put(IVIMFields.mVeh_Zgcs, value);

        // Veh_Clpp F_C0_2 ����Ʒ�� ӳ��
        value = cocData.get(IVIMFields.F_C0_2);
        if (!Utils.isNullOrEmptyString(value) && (!"������".equals(value)))
            result.put(IVIMFields.mVeh_Clpp, value);

        // Veh_ClsbDh F_0_6b
        result.put(IVIMFields.mVeh_Clsbdh, vin);

        // Veh_DpiD C_12 ����ID ӳ��
        value = cocData.get(IVIMFields.C_12);
        if (!Utils.isNullOrEmptyString(value) && (!"������".equals(value)))
            result.put(IVIMFields.mVeh_Dpid, value);

        // Veh_Bz F_50 ��ע ӳ�� �ϸ�֤��ע
        value = cocData.get(IVIMFields.C_11);
        if (!Utils.isNullOrEmptyString(value) && (!"������".equals(value)))
            result.put(IVIMFields.mVeh_Bz, value);

        // Veh_Clztxx C_23 ����״̬��Ϣ ֵת��
        value = cocData.get(IVIMFields.C_23);
        if (!Utils.isNullOrEmptyString(value) && (!"������".equals(value)))
            result.put(IVIMFields.mVeh_Clztxx, value);

        // Veh_ClFl C_22 �������� ӳ��
        value = cocData.get(IVIMFields.C_22);
        if (!Utils.isNullOrEmptyString(value) && (!"������".equals(value)))
            result.put(IVIMFields.mVeh_Clfl, value);

        // Veh_Zxzs C_13 ת������� ӳ��
        value = cocData.get(IVIMFields.C_13);
        if (!Utils.isNullOrEmptyString(value) && (!"������".equals(value)))
            result.put(IVIMFields.mVeh_Zxzs, value);

        // Veh_CDDbj C_21 ���綯��� ֵת��
        value = cocData.get(IVIMFields.C_21);
        if ("��".equals(value)) {
            result.put(IVIMFields.mVeh_Cddbj, "1");
        } else {
            result.put(IVIMFields.mVeh_Cddbj, "2");
        }

        // Veh_ClsCDwmC F_0_1 ����������λ���� ӳ��
        value = cocData.get(IVIMFields.F_0_1);
        if (!Utils.isNullOrEmptyString(value) && (!"������".equals(value)))
            result.put(IVIMFields.mVeh_Clscdwmc, value);

        // Veh_CpsCDz D_04 ����������λ��ַ ӳ��
        value = cocData.get(IVIMFields.D_04);
        if (!Utils.isNullOrEmptyString(value) && (!"������".equals(value)))
            result.put(IVIMFields.mVeh_Cpscdz, value);

        // Veh_QyiD C_14 ��ҵID ӳ��
        value = (String) cocData.get(IVIMFields.C_24);
        if (!Utils.isNullOrEmptyString(value) && (!"������".equals(value)))
            result.put(IVIMFields.mVeh_Qyid, value);

        // Veh_Qybz C_17 ��ҵ��׼ ӳ��
        value = cocData.get(IVIMFields.C_17);
        if (!Utils.isNullOrEmptyString(value) && (!"������".equals(value)))
            result.put(IVIMFields.mVeh_Qybz, value);

        // Veh_Cpggh D_23 ��Ʒ����� ֵת�� �ɹ�����Ϣ���,11λ�ַ���������������к�14λ�ַ�����25λ
        String productPublicId = (String) cocData.get(IVIMFields.D_23);
        // �������
        String confid = (String) confData.get(IVIMFields.H_01);
        if (Utils.isNullOrEmpty(confid)) {
            confid = (String) confData.get(IVIMFields.H_02);
        }
        if (Utils.isNullOrEmpty(confid)) {
            confid = "";
        }
        value = productPublicId + confid;
        // "�޷�ȡ����ȷ�Ĳ�Ʒ����š�\nֵת��  �ɹ�����Ϣ���,11λ�ַ���������������к�14λ�ַ�����25λ");
        result.put(IVIMFields.mVeh_Cpggh, value);

        result.put(IVIMFields.D_23, productPublicId);
        result.put(IVIMFields.H_01, confid);

        // Veh_GgpC D_18 �������� ӳ��
        value = cocData.get(IVIMFields.D_18);
        if (!Utils.isNullOrEmptyString(value) && (!"������".equals(value)))
            result.put(IVIMFields.mVeh_Ggpc, value);

        // Veh_Ggsxrq D_01 ������Ч���� ӳ��
        value = cocData.get(IVIMFields.D_01);
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

        // DBCollection ids = DBActivator.getCollection(IVIMFields.DB_NAME,
        // "ids");
        // String seq = DBUtil.getIncreasedID(ids, IVIMFields.SEQ_GZBH, "0",
        // 10);
        // string = companyId + seq;
        // if (!debug && string.length() != 14) {
        // throw new Exception("�޷�ȡ����ȷ�������ϸ�֤��š�\n4λ��ҵ����+10λ˳���");
        // }
        // result.put(IVIMFields.mVeh_Zchgzbh, string);

        // Veh_Dphgzbh ȫ�ʽ15λ�����̷�ʽ���� ����
        // result.put(IVIMFields.mVeh_Dphgzbh, cocData.get(IVIMFields.F_0_1));

        // Veh_Fzrq ��֤���� �ַ� 14 YYYY��MM��DD��
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy��MM��dd��");
        value = sdf.format(new Date());
        result.put(IVIMFields.mVeh_Fzrq, value);

        // Veh_Cjh vin vin
        result.put(IVIMFields.mVeh_Cjh, "");

        // Veh_Clzzrq ������������
        String mftDate = (String) mesRawData.getValue(FIELD_MFT_DATE);
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        Date _date = sdf2.parse(mftDate);
        value = sdf.format(_date);
        result.put(IVIMFields.mVeh_Clzzrq, value);

        // Veh_Qyqtxx �����е�����
        value = cocData.get(IVIMFields.C_18);
        if (!Utils.isNullOrEmptyString(value) && (!"������".equals(value)))
            result.put(IVIMFields.mVeh_Qyqtxx, value);
        // ������
        // // // Veh_Tmxx ���� ����
        // // Veh_Jyw ���� У�鲻��
        // // Veh_PrinterName ����
        // // Veh_PrintPosLeFt ����
        // // Veh_PrintPosTop ����
        // // Veh_ConneCt ����
        // // Veh_BauD ����
        // // Veh_Parity ����
        // // Veh_Databits ����
        // // Veh_Stopbits ����
        // Veh_Dywym ����

        // Veh_Zzbh ֽ�ű��
        // *****************************�����ֵ������Ҫ���ݵ��ϸ�֤��ֵ��ͬʱ���ֶ�չ��ʱҲ������Ӧ�Ĵ���
        // ���磬����110��input�е�ֵ����110��������ʾΪ000110��ͬʱ��Ҫ�ڴ��ݵ��ϸ�֤��ӡOCX��ֵҲ��Ҫ�ı�Ϊ000110
        // �μ�doTransferBeforeInvokePrint()
        // result.put(IVIMFields.mVeh_Zzbh, "");

        // result.put(IVIMFields.mVeh_Dywym, "");// ����ֵ������

        /** �����Ǵ�ӡ����Ҫ�Ĳ������������ϴ�ʱ��Ҫ�Ĳ��� **/
        result.put(IVIMFields.mVeh__Hzdczfs, cocData.get(IVIMFields.C_15));
        result.put(IVIMFields.mVeh__Hzdfs, cocData.get(IVIMFields.C_16));
        result.put(IVIMFields.mVeh__Qzdczfs, cocData.get(IVIMFields.C_19));
        result.put(IVIMFields.mVeh__Qzdfs, cocData.get(IVIMFields.C_20));
        result.put(IVIMFields.mVeh__Pzxlh, confid);
        // �����ϴ���Ҫ
        result.put(IVIMFields.mVeh__Fdjscc, cocData.get(IVIMFields.F_20));
        // �����ϸ�֤�����Ҫ�ڴ�ӡ�󴫵�

        // coc id
        result.put(IVIMFields.COC_ID, cocData.get("_id"));

        return result;
    }

    public static void uploadCert(List<DBObject> certList) throws Exception {
        CertificateRequestServiceSoap vidService = getVIDCService();
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

    public static void uploadFuelLabel(List<DBObject> fuelLabelList) throws Exception {
        FuelDataSysSTDSoap service = getFUELDATAService();
        ArrayOfVehicleBasicInfo vehicleInfoList = new ArrayOfVehicleBasicInfo();

        for (int i = 0; i < fuelLabelList.size(); i++) {
            VehicleBasicInfo vbi = getVehicleBasicInfo(fuelLabelList.get(i));
            vehicleInfoList.getVehicleBasicInfo().add(vbi);
        }

        OperateResult r = service.uploadFuelData(FUELLABEL_USERNAME, FUELLABEL_PASSWORD,
                vehicleInfoList, FUELLABEL_OKEY);
        int rCode = r.getResultCode();
        if (rCode == 1) {
            throw new Exception(getResultMessage(r));
        }
    }

    public static void main(String[] args) throws ParserConfigurationException, SAXException,
            IOException {
        String res = "<result>\n" + "<succeed>\"true\"</succeed>\n"
                + "<data>\"HHVESGTOTHLDUVUMMCQUNCPURNSFQLTW\"</data>\n" + "</result>";
        DocumentBuilderFactory fac = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = fac.newDocumentBuilder();
        Document doc = builder.parse(new ByteArrayInputStream(res.getBytes()));
        Element element = doc.getDocumentElement();
        NodeList n = element.getChildNodes();

        if (n != null) {
            for (int i = 0; i < n.getLength(); i++) {
                Node book = n.item(i);
                for (Node node = book.getFirstChild(); node != null; node = node.getNextSibling()) {
                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        if (node.getNodeName().equals("succeed")) {
                            String name = node.getNodeValue();
                            String name1 = node.getFirstChild().getNodeValue();
                            System.out.println(name);
                            System.out.println(name1);
                        }
                        if (node.getNodeName().equals("data")) {
                            String price = node.getFirstChild().getNodeValue();
                            System.out.println(price);
                        }
                    }
                }
            }
        }

    }

    public static void uploadEnv(List<DBObject> list) throws Exception {
        WSVinSoap service = getWVINService();
        String res = service.loginW(ENV_USERNAME, ENV_PASSWORD);
        DocumentBuilderFactory fac = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = fac.newDocumentBuilder();
        Document doc = builder.parse(new ByteArrayInputStream(res.getBytes()));
        NodeList cnodes = doc.getChildNodes();

    }

    public static void uploadCert2(List<DBObject> certList, String memo) throws Exception {
        CertificateRequestServiceSoap vidService = getVIDCService();
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

    public static void uploadFuelLabel2(List<DBObject> fuelLabelList, String memo) throws Exception {
        FuelDataSysSTDSoap service = getFUELDATAService();
        ArrayOfVehicleBasicInfo vehicleInfoList = new ArrayOfVehicleBasicInfo();

        for (int i = 0; i < fuelLabelList.size(); i++) {
            VehicleBasicInfo vbi = getVehicleBasicInfo(fuelLabelList.get(i), true, memo);
            vehicleInfoList.getVehicleBasicInfo().add(vbi);
        }

        com.sg.vim.service.OperateResult r = service.uploadOverTime(FUELLABEL_USERNAME,
                FUELLABEL_PASSWORD, vehicleInfoList, FUELLABEL_OKEY);
        int rCode = r.getResultCode();
        if (rCode == 1) {
            throw new Exception(getResultMessage(r));
        }
    }

    public static void updateCert(List<DBObject> certList, String memo) throws Exception {
        CertificateRequestServiceSoap vidService = getVIDCService();
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

    public static void updateFuellabel(List<DBObject> fuelLabelList, String memo) throws Exception {
        FuelDataSysSTDSoap service = getFUELDATAService();
        ArrayOfVehicleBasicInfo vehicleInfoList = new ArrayOfVehicleBasicInfo();

        for (int i = 0; i < fuelLabelList.size(); i++) {
            VehicleBasicInfo vbi = getVehicleBasicInfo(fuelLabelList.get(i), true, memo);
            vehicleInfoList.getVehicleBasicInfo().add(vbi);
        }
        OperateResult r = service.applyUpdate(FUELLABEL_USERNAME, FUELLABEL_PASSWORD,
                vehicleInfoList, FUELLABEL_OKEY);
        int rCode = r.getResultCode();
        if (rCode == 1) {
            throw new Exception(getResultMessage(r));
        }
    }

    public static void deleteCert(List<String> certNumberList, String memo) throws Exception {
        CertificateRequestServiceSoap vidService = getVIDCService();
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

    public static void deleteFuelLabel(List<String> vinList, String memo) throws Exception {
        FuelDataSysSTDSoap service = getFUELDATAService();
        com.sg.vim.service.ArrayOfString vinStringList = new com.sg.vim.service.ArrayOfString();
        for (int i = 0; i < vinList.size(); i++) {
            vinStringList.getString().add(vinList.get(i));
        }
        OperateResult r = service.applyDelete(FUELLABEL_USERNAME, FUELLABEL_PASSWORD,
                vinStringList, memo, FUELLABEL_OKEY);
        int rCode = r.getResultCode();
        if (rCode == 1) {
            throw new Exception(getResultMessage(r));
        }
    }

    private static CertificateInfo getCertificateInfo(DBObject data) throws Exception {
        return getCertificateInfo(data, false);
    }

    private static VehicleBasicInfo getVehicleBasicInfo(DBObject data) throws Exception {
        return getVehicleBasicInfo(data, false, null);
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
        value = (String) data.get(IVIMFields.mVeh_Jsszcrs);
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

    private static VehicleBasicInfo getVehicleBasicInfo(DBObject data, boolean isUpdate, String memo)
            throws Exception {
        VehicleBasicInfo info = new VehicleBasicInfo();
        // ��� ���� �������� ��������
        // ��soap������ ˵��
        // 1 V_Id ������ s:string ϵͳ���ɣ����������

        // 2 Vin ���������ţ�VIN�룩 s:string �����������ţ�VIN�룩�ֶΣ��涨ʱ�����ϱ�ʱʹ�ã�
        info.setVin((String) data.get(IVIMFields.F_0_6b));

        // 3 App_Vin ���������ţ�VIN�룩 s:string ������������ţ�VIN�룩�ֶΣ����벹�����޸ġ�����ʱʹ�ã�
        if (isUpdate) {
            info.setAppVin((String) data.get(IVIMFields.F_0_6b));
        }

        // 4 User_Id ��ҵ��¼�û��� s:string
        info.setUserId(FUELLABEL_USERNAME);

        // 5 Qcscqy ����������ҵ s:string
        info.setQcscqy((String) data.get(IVIMFields.F_0_1));

        // 6 Jkqczjxs �������������� s:string ���������̴����Ϊ�գ���������ҵΪ������

        // 7 Clxh �����ͺ� s:string
        info.setClxh((String) data.get(IVIMFields.F_0_2C1));

        // 8 Clzl �������� s:string �ӡ����ó���M1�������Ϳͳ���M2�������ͻ�����N1������������ѡ�����У�M1��M2��N1 �Ķ��尴GB/T
        // 15089-2001�������������ҳ����ࡷ
        info.setClzl((String) data.get(IVIMFields.F_0_4));

        // 9 Rllx ȼ������ s:string ���͡����͡�����ȼ�ϡ�˫ȼ�ϡ����綯���ǲ��ʽ��϶��������ʽ��϶�����ȼ�ϵ��
        info.setRllx((String) data.get(IVIMFields.F_25));

        // Zczbzl ������������ s:string
        info.setZczbzl((String) data.get(IVIMFields.C_08));

        // Zgcs ��߳��� s:string
        info.setZgcs((String) data.get(IVIMFields.F_44));

        // Ltgg ��̥��� s:string
        info.setLtgg((String) data.get(IVIMFields.F_32A));

        // Zj ��� s:string
        info.setZj((String) data.get(IVIMFields.F_3));

        // Clzzrq ������������/�������� s:string
        String value = (String) data.get(IVIMFields.mVeh_Clzzrq);
        // Assert.isNotNull(value, "������������");
        Date dValue = new SimpleDateFormat("yyyy��MM��dd��").parse(value);
        GregorianCalendar nowGregorianCalendar = new GregorianCalendar();
        nowGregorianCalendar.setTime(dValue);
        XMLGregorianCalendar xmlDatetime = DatatypeFactory.newInstance().newXMLGregorianCalendar(
                nowGregorianCalendar);
        info.setClzzrq(xmlDatetime);

        // Tymc ͨ������ s:string //���۳���
        info.setTymc((String) data.get(IVIMFields.D_20));

        // Yyc ԽҰ����G�ࣩ s:string ��/��
        info.setTymc((String) data.get(IVIMFields.D_30));

        // Zwps ��λ���� s:string
        info.setZwps((String) data.get(IVIMFields.D_17));

        // Zdsjzzl ������������ s:string
        info.setZdsjzzl((String) data.get(IVIMFields.F_14_1));

        // Edzk ��ؿ� s:string
        info.setEdzk((String) data.get(IVIMFields.F_42_1));

        // Lj �־ࣨǰ/�� s:string
        String qlj = (String) data.get(IVIMFields.F_5A);
        String hlj = (String) data.get(IVIMFields.F_5B);
        info.setLj(qlj + "/" + hlj);

        // Qdxs ������ʽ s:string �ӡ�ǰ��������������������ʱȫ��������ȫʱȫ������������(��ʱ)ȫ����������������ѡ��
        info.setQdxs((String) data.get(IVIMFields.D_22));

        // CreateTime �ϴ�ʱ�� s:datetime �����Ϊ�գ�server�˴�����ֶ�
        // UpdateTime ����ʱ�� s:datetime �����Ϊ�գ�server�˴�����ֶ�
        // Status ״̬ s:string �����Ϊ�գ�server�˴�����ֶ�
        // ״̬˵��������

        // Jyjgmc ���������� s:string
        info.setJyjgmc((String) data.get(IVIMFields.D_31));

        // Jybgbh ��ⱨ���� s:string
        info.setJybgbh((String) data.get(IVIMFields.D_32));

        if (isUpdate && !Utils.isNullOrEmpty(memo)) {
            // ԭ��
            info.setReason(memo);
        }

        // Apply_Type ����������� s:string �����Ϊ�գ�server�˴�����ֶ�
        // Check s:string �����ֶΣ���ҵ����ʱ���Դ��ֶ�
        // Reason ����ԭ���ֶ� s:string �����벹���������޸�ʱ�������ýӿ�4: UploadOverTime��5 :ApplyUpdate��ʱ���ֶ�Ϊ������
        // EntityList ȼ�ϲ������� RllxParamEntity[] ���ݽṹ������
        ArrayOfRllxParamEntity arrayRllx = new ArrayOfRllxParamEntity();
        RllxParamEntity e = new RllxParamEntity();
        e.setParamCode("CT_BSRXS");
        e.setParamValue("�ֶ�");
        e.setVin((String) data.get(IVIMFields.F_0_6b));

        arrayRllx.getRllxParamEntity().add(e);
        info.setEntityList(arrayRllx);
        return info;
    }

    private static String getResultMessage(Object oResult) {
        StringBuffer sb = new StringBuffer();

        sb.append(String.format("�������:%s\r\n", ((OperateResult) oResult).getResultCode()));

        for (NameValuePair nvp : ((OperateResult) oResult).getResultDetail().getNameValuePair()) {
            sb.append(String.format("%s:%s\r\n", nvp.getName(), nvp.getValue()));
        }
        return sb.toString();
    }

    public static String getLifecycle(String vin, String collectionName) {
        DBCollection col = DBActivator.getCollection(IVIMFields.DB_NAME, collectionName);
        DBObject d = col.findOne(new BasicDBObject().append(IVIMFields.mVeh_Clsbdh, vin),
                new BasicDBObject().append(IVIMFields.LIFECYCLE, 1));
        if (d != null) {
            return (String) d.get(IVIMFields.LIFECYCLE);
        }
        return null;
    }

    public static DBObject getCertDataByVin(String vin, String clztxx) {
        DBCollection col = DBActivator.getCollection(IVIMFields.DB_NAME, IVIMFields.COL_CERF);
        DBCursor cur = col.find(new BasicDBObject().append(IVIMFields.mVeh_Clsbdh, vin).append(
                IVIMFields.mVeh_Clztxx, clztxx));
        DBObject ret = null;
        while(cur.hasNext()){
            DBObject certData = cur.next();
            if (IVIMFields.LC_ABANDON.equals((String) certData.get(IVIMFields.LIFECYCLE))&&ret!=null){
                continue;
            }
            ret = certData;
        }
        return ret;
    }

    public static DBObject getCOCPaperDataByVin(String vin) {
        DBCollection col = DBActivator.getCollection(IVIMFields.DB_NAME, IVIMFields.COL_COCPAPER);
        return col.findOne(new BasicDBObject().append(IVIMFields.F_0_6b, vin));
    }

    public static String getColorNameByCode(String colorCode) {
        DBCollection c = DBActivator.getCollection(IVIMFields.DB_NAME, "colors");
        DBObject d = c.findOne(new BasicDBObject().append(IVIMFields.color_code, colorCode));
        if (d != null) {
            return (String) d.get(IVIMFields.color_name);
        }
        return null;
    }

    public static DBObject getFuelLabelByVin(String vin) {
        DBCollection col = DBActivator.getCollection(IVIMFields.DB_NAME, IVIMFields.COL_FUELABEL);
        return col.findOne(new BasicDBObject().append(IVIMFields.F_0_6b, vin));
    }

    public static HashMap<String, String> getPrinterParameters(String printfunctionName) {
        DBCollection col = DBActivator.getCollection(IVIMFields.DB_NAME, "printers");
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

    public static int getCurrentMaxPaperOfDPCert() {
        DBCollection ids = DBActivator.getCollection(IVIMFields.DB_NAME, "ids");
        int id = DBUtil.getCurrentID(ids, "Veh_dpZzbh");
        return id;
    }

    public static int getCurrentMaxPaperOfZCCert() {
        DBCollection ids = DBActivator.getCollection(IVIMFields.DB_NAME, "ids");
        int id = DBUtil.getCurrentID(ids, "Veh_zcZzbh");
        return id;
    }

    public static int getMaxPaperOfDPCert() {
        DBCollection ids = DBActivator.getCollection(IVIMFields.DB_NAME, "ids");
        int id = DBUtil.getIncreasedID(ids, "Veh_dpZzbh");
        return id;
    }

    public static int getMaxPaperOfZCCert() {
        DBCollection ids = DBActivator.getCollection(IVIMFields.DB_NAME, "ids");
        int id = DBUtil.getIncreasedID(ids, "Veh_zcZzbh");
        return id;
    }

    public static void setCurrentPaperDPCert(int startNumber) {
        DBCollection ids = DBActivator.getCollection(IVIMFields.DB_NAME, "ids");
        DBUtil.setCurrentID(ids, "Veh_dpZzbh", startNumber);
    }

    public static void setCurrentPaperZCCert(int startNumber) {
        DBCollection ids = DBActivator.getCollection(IVIMFields.DB_NAME, "ids");
        DBUtil.setCurrentID(ids, "Veh_zcZzbh", startNumber);
    }

    public static DBObject saveUploadData(List<ObjectId> idList, String memo, String colname,
            String actionType) {
        Date date = new Date();
        DBObject accountInfo = UserSessionContext.getAccountInfo();
        BasicDBObject rec = new BasicDBObject().append(IVIMFields.ACTION_REC_DATE, date)
                .append(IVIMFields.ACTION_REC_ACCOUNT, accountInfo)
                .append(IVIMFields.ACTION_REC_TYPE, actionType)
                .append(IVIMFields.ACTION_REC_MEMO, memo);

        DBCollection col = DBActivator.getCollection(IVIMFields.DB_NAME, colname);
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

        DBObject dataItem = col.findOne(query,
                new BasicDBObject().append("_id", 1).append(IVIMFields.LIFECYCLE, 1));

        if (dataItem != null) {
            if (!IVIMFields.LC_ABANDON.equals(dataItem.get(IVIMFields.LIFECYCLE))) {
                throw new Exception("��COC֤���Ѿ���ӡ�������ظ���ӡ");
            } else {
                if (!COC_REPRINT) {
                    throw new Exception("���ò�����ֱ���ظ���ӡ���ϵ�COC֤�飬�����ظ���ӡ");
                }
            }
        }
        String vin = (String) data.get(IVIMFields.F_0_6b);
        DBObject cert = getCertDataByVin(vin, "QX");
        if (cert != null) {
            Object fzrq = cert.get(IVIMFields.mVeh_Fzrq);
            data.put(IVIMFields.CCC_21, fzrq == null ? "" : fzrq.toString());
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

        DBObject dataItem = col.findOne(query,
                new BasicDBObject().append("_id", 1).append(IVIMFields.LIFECYCLE, 1));

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
        url.append("&font=" + "simhei.ttf");
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
            if (key.startsWith("_")) {
                continue;
            }
            cocInfo.put(key, basicInfo.get(key));
            update.put(key, basicInfo.get(key));
        }

        // ����ȼ������
        StringBuffer sb = new StringBuffer();
        Object f251 = basicInfo.get(IVIMFields.F_25_1);
        sb.append(f251);
        Object f252 = basicInfo.get(IVIMFields.F_25_2);
        if (!Utils.isNullOrEmptyString(f252)) {
            sb.append("/");// ʹ��/�ָ�
            sb.append(f252);
        }
        Object f253 = basicInfo.get(IVIMFields.F_25_3);
        if (!Utils.isNullOrEmptyString(f253)) {
            sb.append("/");// ʹ��/�ָ�
            sb.append(f253);
        }
        update.put(IVIMFields.F_25, sb.toString());
        COCInfo cService = new COCInfo();
        ObjectId cocId = (ObjectId) cocInfo.get("_id");
        cService.update(cocId, update);

        markCOCDirty(cocId);
    }

    public static void mntMesProductInfo(DBObject product) throws Exception {
        Object cocid = product.get(IVIMFields.COC_ID);
        System.out.println();
        if (!(cocid instanceof ObjectId)) {
            return;
        }
        DBCollection col = DBActivator.getCollection(IVIMFields.DB_NAME, IVIMFields.COL_COCINFO);
        DBObject coc = col.findOne(new BasicDBObject().append("_id", cocid));

        // ��ѯ�Ƿ��Ѿ�����
        StringBuffer sqlquery = new StringBuffer();
        sqlquery.append("SELECT id FROM bqyx_mes.MES_PRODUCT_VEHICLE_CODE where id = ");
        sqlquery.append("'" + product.get("_id") + "'");
        int cnt = SQLUtil.SQL_COUNT(MES_DB2, sqlquery.toString());
        if (cnt != 0) {
            StringBuffer sqlUpdate = new StringBuffer();
            sqlUpdate.append("UPDATE bqyx_mes.MES_PRODUCT_VEHICLE_CODE SET ");
            sqlUpdate.append("NOTICE_CODE=");
            sqlUpdate.append("'" + product.get(IVIMFields.F_0_2C1) + "',");
            sqlUpdate.append("ERP_PRODUCT_CODE=");
            sqlUpdate.append("'" + product.get(IVIMFields.E_02) + "',");
            sqlUpdate.append("ENGINE_TYPE=");
            sqlUpdate.append("'" + coc.get(IVIMFields.F_C4) + "',");
            sqlUpdate.append("RATED_PASSENGER=");
            sqlUpdate.append("'" + coc.get(IVIMFields.F_42_1) + "',");
            sqlUpdate.append("FUEL_TYPE=");
            sqlUpdate.append("'" + coc.get(IVIMFields.F_25) + "',");
            sqlUpdate.append("DRIVER_TYPE=");
            sqlUpdate.append("'" + coc.get(IVIMFields.D_22) + "',");
            sqlUpdate.append("CURB_WEIGHT=");
            sqlUpdate.append("'" + coc.get(IVIMFields.C_08) + "',");
            sqlUpdate.append("VEHICLE_BRAND=");
            sqlUpdate.append("'" + coc.get(IVIMFields.F_C0_2) + "',");
            sqlUpdate.append("VEHICLE_TYPE=");
            sqlUpdate.append("'" + coc.get(IVIMFields.C_22) + "',");
            sqlUpdate.append("OUTPUT_VOLUME=");
            sqlUpdate.append("'" + coc.get(IVIMFields.F_24) + "',");
            sqlUpdate.append("POWER=");
            sqlUpdate.append("'" + coc.get(IVIMFields.C_01) + "',");
            sqlUpdate.append("TOTAL_QUALITY=");
            sqlUpdate.append("'" + coc.get(IVIMFields.F_14_1) + "',");
            sqlUpdate.append("BODY_COLOR=");
            sqlUpdate.append("'" + coc.get(IVIMFields.F_38) + "',");
            sqlUpdate.append("TYRE_SIZE=");
            sqlUpdate.append("'" + coc.get(IVIMFields.F_32A) + "',");
            sqlUpdate.append("MAXIMUN_NET_POWER=");
            sqlUpdate.append("'" + coc.get(IVIMFields.F_26) + "'");
            sqlUpdate.append(" WHERE ID=");
            sqlUpdate.append("'" + product.get("_id") + "'");

            SQLUtil.SQL_UPDATE(MES_DB2, sqlUpdate.toString());
        } else {
            StringBuffer sqlInsert = new StringBuffer();
            sqlInsert.append("insert into bqyx_mes.MES_PRODUCT_VEHICLE_CODE (");
            sqlInsert.append("ID,");
            sqlInsert.append("NOTICE_CODE,");
            sqlInsert.append("ERP_PRODUCT_CODE,");
            sqlInsert.append("ENGINE_TYPE,");
            sqlInsert.append("RATED_PASSENGER,");
            sqlInsert.append("FUEL_TYPE,");
            sqlInsert.append("DRIVER_TYPE,");
            sqlInsert.append("CURB_WEIGHT,");
            sqlInsert.append("VEHICLE_BRAND,");
            sqlInsert.append("VEHICLE_TYPE,");
            sqlInsert.append("OUTPUT_VOLUME,");
            sqlInsert.append("POWER,");
            sqlInsert.append("TOTAL_QUALITY,");
            sqlInsert.append("BODY_COLOR,");
            sqlInsert.append("TYRE_SIZE,");
            sqlInsert.append("MAXIMUN_NET_POWER");
            sqlInsert.append(") values (");

            // sqlInsert.append("ID,");
            sqlInsert.append("'" + product.get("_id") + "',");

            // sqlInsert.append("NOTICE_CODE,");
            sqlInsert.append("'" + product.get(IVIMFields.F_0_2C1) + "',");

            // sqlInsert.append("ERP_PRODUCT_CODE,");
            sqlInsert.append("'" + product.get(IVIMFields.E_02) + "',");

            // sqlInsert.append("ENGINE_TYPE,");
            sqlInsert.append("'" + coc.get(IVIMFields.F_C4) + "',");

            // sqlInsert.append("RATED_PASSENGER,");
            sqlInsert.append("'" + coc.get(IVIMFields.F_42_1) + "',");

            // sqlInsert.append("FUEL_TYPE,");
            sqlInsert.append("'" + coc.get(IVIMFields.F_25) + "',");

            // sqlInsert.append("DRIVER_TYPE,");
            sqlInsert.append("'" + coc.get(IVIMFields.D_22) + "',");

            // sqlInsert.append("CURB_WEIGHT,");
            sqlInsert.append("'" + coc.get(IVIMFields.C_08) + "',");

            // sqlInsert.append("VEHICLE_BRAND,");
            sqlInsert.append("'" + coc.get(IVIMFields.F_C0_2) + "',");

            // sqlInsert.append("VEHICLE_TYPE,");
            sqlInsert.append("'" + coc.get(IVIMFields.C_22) + "',");

            // sqlInsert.append("OUTPUT_VOLUME,");
            sqlInsert.append("'" + coc.get(IVIMFields.F_24) + "',");

            // sqlInsert.append("POWER,");
            sqlInsert.append("'" + coc.get(IVIMFields.C_01) + "',");

            // sqlInsert.append("TOTAL_QUALITY,");
            sqlInsert.append("'" + coc.get(IVIMFields.F_14_1) + "',");

            // sqlInsert.append("BODY_COLOR,");
            sqlInsert.append("'" + coc.get(IVIMFields.F_38) + "',");

            // sqlInsert.append("TYRE_SIZE,");
            sqlInsert.append("'" + coc.get(IVIMFields.F_32A) + "',");

            // sqlInsert.append("MAXIMUN_NET_POWER");
            sqlInsert.append("'" + coc.get(IVIMFields.F_26) + "'");

            sqlInsert.append(")");

            String[] sqls = new String[] { sqlInsert.toString() };
            SQLUtil.SQL_EXECUTE(MES_DB2, sqls);
        }

    }

    public static boolean markCOCDirty(ObjectId cocId) {
        DBCollection col = DBActivator.getCollection(IVIMFields.DB_NAME, IVIMFields.COL_CERF);
        WriteResult rs = col.update(new BasicDBObject().append(IVIMFields.COC_ID, cocId),
                new BasicDBObject().append("$set", new BasicDBObject(IVIMFields.IS_DIRTY,
                        Boolean.TRUE)));
        int cnt = rs.getN();

        col = DBActivator.getCollection(IVIMFields.DB_NAME, IVIMFields.COL_COCPAPER);
        rs = col.update(new BasicDBObject().append(IVIMFields.COC_ID, cocId), new BasicDBObject()
                .append("$set", new BasicDBObject(IVIMFields.IS_DIRTY, Boolean.TRUE)));
        if (cnt <= 0) {
            cnt = rs.getN();
        }

        col = DBActivator.getCollection(IVIMFields.DB_NAME, IVIMFields.COL_FUELABEL);
        rs = col.update(new BasicDBObject().append(IVIMFields.COC_ID, cocId), new BasicDBObject()
                .append("$set", new BasicDBObject(IVIMFields.IS_DIRTY, Boolean.TRUE)));
        if (cnt <= 0) {
            cnt = rs.getN();
        }

        return cnt > 0;

    }

    public static void createEnvData(DBObject certData) {

        BasicDBObject data = new BasicDBObject();
        // vin
        // �ַ���17λ
        // ����ʶ�����
        //
        data.put(IVIMFields.env_vin, certData.get(IVIMFields.mVeh_Clsbdh));
        // clxh
        // �ַ���200λ
        // �����ͺ�
        //
        data.put(IVIMFields.env_clxh, certData.get(IVIMFields.mVeh_Clxh));

        // fdjxh
        // �ַ���200λ
        // �����������ͺ�
        //
        data.put(IVIMFields.env_fdjxh, certData.get(IVIMFields.mVeh_Fdjxh));

        // fdjscc
        // �ַ���200λ
        // ����������������
        //
        data.put(IVIMFields.env_fdjscc, certData.get(IVIMFields.mVeh_Fdjxh));

        // mdate
        // ������
        // ��ʽΪyyyy-mm-dd����2011-9-1��2011-09-01����
        // ���޷�ȷ�������գ����Զ�Ϊ1��
        //
        data.put(IVIMFields.env_mdate, certData.get(IVIMFields.mVeh_Clzzrq));

        // qdate
        // ������
        // ��ʽΪyyyy-mm-dd����2011-9-11
        data.put(IVIMFields.env_qdate, certData.get(IVIMFields.mVeh_Clzzrq));

        DBCollection col = DBActivator.getCollection(IVIMFields.DB_NAME, IVIMFields.COL_ENV);
        Object vin = certData.get(IVIMFields.mVeh_Clsbdh);
        long count = col.count(new BasicDBObject().append(IVIMFields.env_vin, vin));
        if (count > 0) {
            col.update(new BasicDBObject().append(IVIMFields.env_vin, vin),
                    new BasicDBObject().append("$set", data));
        } else {
            col.insert(data);
        }

    }

    public static void copyCreateCOC(DBObject srcData) {
        DataObjectCollectionService dataObjectService = new DataObjectCollectionService();
        ITransferRule rule = new ITransferRule() {

            @Override
            public Object getValue(DBObject src, String key) {
                // �ӹ��泵���в�����Ӧ��COC��Ϣ
                // 2. �������ƴ�����Щ
                // 3. ����ǰ���־�ϲ�
                return src.get(key);
            }

            @Override
            public DBObject doPostTransfer(DBObject src, DBObject tgt) {
                // *********************************************************************************
                // 1. ���������ͣ����ֶ���BasicInfo��ΪC22���������¹���תΪCOCInfo�е�M1,N1��Щ����
                // GB 18352.3-2005 M�ǿͳ���M1��9�����£�M2��9�����ϣ�N1��������3500kg���µ��ػ�����
                // ��ȡC22
                Object c22 = src.get(IVIMFields.C_22);
                if ("���ó����ͳ�".equals(c22)) {
                    tgt.put(IVIMFields.F_0_4, "M1");
                } else {
                    tgt.put(IVIMFields.F_0_4, "N1");
                }
                // *********************************************************************************
                // 2. ����ȼ������
                StringBuffer sb = new StringBuffer();
                Object f251 = src.get(IVIMFields.F_25_1);
                sb.append(f251);
                Object f252 = src.get(IVIMFields.F_25_2);
                if (!Utils.isNullOrEmptyString(f252)) {
                    sb.append("/");// ʹ��/�ָ�
                    sb.append(f252);
                }
                Object f253 = src.get(IVIMFields.F_25_3);
                if (!Utils.isNullOrEmptyString(f253)) {
                    sb.append("/");// ʹ��/�ָ�
                    sb.append(f253);
                }
                tgt.put(IVIMFields.F_25, sb.toString());
                // *********************************************************************************
                // 3. ����ȼ������
                BasicDBList table = new BasicDBList();
                Object d14 = src.get(IVIMFields.D_14);// ����
                table.add(new BasicDBObject().append("location", "����").append("co2", "")
                        .append("fuelqty", d14));
                Object d15 = src.get(IVIMFields.D_15);// �н�
                table.add(new BasicDBObject().append("location", "�н�").append("co2", "")
                        .append("fuelqty", d15));
                Object d16 = src.get(IVIMFields.D_16);// �ۺ�
                table.add(new BasicDBObject().append("location", "�ۺ�").append("co2", "")
                        .append("fuelqty", d16));
                tgt.put(IVIMFields.F_46_3, table);
                return tgt;
            }

            @Override
            public DBObject doPrepareTransfer(DBObject src) {
                return null;
            }
        };
        DBObject tgtData = ModelTransfer
                .transfer(srcData, dataObjectService
                        .getReservedKeys(DataObjectCollectionService.CLONE_RESERVED), rule);

        tgtData.put("basicinfo_id", srcData.get("_id"));

        // ׼���༭������������
        DBCollection collection = DBActivator.getCollection("appportal", "cocinfo");
        DataObject editData = new DataObject(collection, tgtData);

        // ʹ����һ���༭����
        String editorId = "com.sg.vim.editor.cocinfo";
        try {
            UIUtils.create(editorId, editData, true, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void copyCreateCFG(DBObject srcData) {
        DataObjectCollectionService dataObjectService = new DataObjectCollectionService();
        DBObject tgtData = ModelTransfer.transfer(srcData,
                dataObjectService.getReservedKeys(DataObjectCollectionService.CLONE_RESERVED));
        tgtData.put("basicinfo_id", srcData.get("_id"));

        // ׼���༭������������
        DBCollection collection = DBActivator.getCollection("appportal", "configcodeinfo");
        DataObject editData = new DataObject(collection, tgtData);

        // ʹ����һ���༭����
        String editorId = "com.sg.vim.editor.configcode";
        try {
            UIUtils.create(editorId, editData, true, null);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    public static CertificateRequestServiceSoap getVIDCService() {
        CertificateRequestServiceSoap service = new CertificateRequestService()
                .getCertificateRequestServiceSoap();
        return service;
    }

    public static FuelDataSysSTDSoap getFUELDATAService() {
        FuelDataSysSTDSoap service = new FuelDataSysSTD().getFuelDataSysSTDSoap();
        return service;
    }

    public static WSVinSoap getWVINService() {
        WSVinSoap service = new WSVin().getWSVinSoap();
        return service;
    }
}
