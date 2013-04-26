package com.sg.vim.datamodel.util;

import org.bson.types.ObjectId;

import com.mobnut.commons.util.Utils;
import com.mobnut.db.DBActivator;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.sg.sqldb.utility.SQLResult;
import com.sg.sqldb.utility.SQLRow;
import com.sg.sqldb.utility.SQLUtil;
import com.sg.vim.datamodel.IVIMFields;

public class VimUtils {

    private static final String SQL_GET_PRODUCINFOR = "select erp_product_code,safety_components_vin,manufacture_date "
            + "from bqyx_mes.mes_mp_erp_code_lot_view "
            + "where safety_components_vin is not null and manufacture_date is not null and vin ='";

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
        SQLResult res = SQLUtil.SQL_QUERY("mes", SQL_GET_PRODUCINFOR + vin+"'");
        if (res.size() == 0) {
            throw new Exception("MES���ݿ���û��VIN��Ӧ�ĳ�Ʒ��¼");
        }
        return res.getData().get(0);
    }

    public static DBObject getProductCodeInfo(String productCode) throws Exception {
        DBCollection c = DBActivator.getCollection("appportal", "productcodeinfo");
        DBObject query = new BasicDBObject().append(IVIMFields.E_02, productCode);
        DBObject data = c.findOne(query);
        if(data == null){
            throw new Exception("��VIM���ݿ����޷���ó�Ʒ�����ݣ������Ǹó�Ʒ�����ݻ�δ��ͬ��"
                    + "\n�������ֹ�ͬ�������ԡ�");
        }
        Object cocinfoId = data.get(IVIMFields.COC_ID);
        if(!(cocinfoId instanceof ObjectId)){
            throw new Exception("��VIM���ݿ����޷���ó�Ʒ����صĳ���һ��������"
                    + "\n�뽫�ó�Ʒ��󶨳���һ�������ݺ����ԡ�");
        }
        Object cfgInfoId = data.get(IVIMFields.CFG_ID);
        if(!(cfgInfoId instanceof ObjectId)){
            throw new Exception("��VIM���ݿ����޷���ó�Ʒ����ص�������Ϣ"
                    + "\n�뽫�ó�Ʒ����������ݺ����ԡ�");
        }
        
        return data;
    }

    public static DBObject getCOCInfo(DBObject productCodeData) throws Exception {
        DBCollection c = DBActivator.getCollection("appportal", "cocinfo");
        ObjectId id = (ObjectId) productCodeData.get(IVIMFields.COC_ID);
        DBObject query = new BasicDBObject().append("_id", id);
        DBObject data = c.findOne(query);
        if(data == null){
            throw new Exception("��Ʒ��󶨵ĳ���һ���Լ�¼�Ѳ����ڣ��������ѱ�ɾ����\n�����½��ó�Ʒ��󶨳���һ�������ݺ����ԡ�");
        }
        return data;
    }

    public static DBObject getConfInfo(DBObject productCodeData) throws Exception {
        DBCollection c = DBActivator.getCollection("appportal", "configcodeinfo");
        ObjectId id = (ObjectId) productCodeData.get(IVIMFields.CFG_ID);
        DBObject query = new BasicDBObject().append("_id", id);
        DBObject data = c.findOne(query);
        if(data == null){
            throw new Exception("��Ʒ��󶨵ĳ���һ���Լ�¼�Ѳ����ڣ��������ѱ�ɾ����\n�����½��ó�Ʒ��󶨳���һ�������ݺ����ԡ�");
        }
        return data;
    }

}
