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
        SQLResult res = SQLUtil.SQL_QUERY("mes", SQL_GET_PRODUCINFOR + vin+"'");
        if (res.size() == 0) {
            throw new Exception("MES数据库中没有VIN对应的成品记录");
        }
        return res.getData().get(0);
    }

    public static DBObject getProductCodeInfo(String productCode) throws Exception {
        DBCollection c = DBActivator.getCollection("appportal", "productcodeinfo");
        DBObject query = new BasicDBObject().append(IVIMFields.E_02, productCode);
        DBObject data = c.findOne(query);
        if(data == null){
            throw new Exception("在VIM数据库中无法获得成品码数据，可能是该成品码数据还未被同步"
                    + "\n请自行手工同步后重试。");
        }
        Object cocinfoId = data.get(IVIMFields.COC_ID);
        if(!(cocinfoId instanceof ObjectId)){
            throw new Exception("在VIM数据库中无法获得成品码相关的车型一致性数据"
                    + "\n请将该成品码绑定车型一致性数据后重试。");
        }
        Object cfgInfoId = data.get(IVIMFields.CFG_ID);
        if(!(cfgInfoId instanceof ObjectId)){
            throw new Exception("在VIM数据库中无法获得成品码相关的配置信息"
                    + "\n请将该成品码绑定配置数据后重试。");
        }
        
        return data;
    }

    public static DBObject getCOCInfo(DBObject productCodeData) throws Exception {
        DBCollection c = DBActivator.getCollection("appportal", "cocinfo");
        ObjectId id = (ObjectId) productCodeData.get(IVIMFields.COC_ID);
        DBObject query = new BasicDBObject().append("_id", id);
        DBObject data = c.findOne(query);
        if(data == null){
            throw new Exception("成品码绑定的车型一致性记录已不存在，可能是已被删除。\n请重新将该成品码绑定车型一致性数据后重试。");
        }
        return data;
    }

    public static DBObject getConfInfo(DBObject productCodeData) throws Exception {
        DBCollection c = DBActivator.getCollection("appportal", "configcodeinfo");
        ObjectId id = (ObjectId) productCodeData.get(IVIMFields.CFG_ID);
        DBObject query = new BasicDBObject().append("_id", id);
        DBObject data = c.findOne(query);
        if(data == null){
            throw new Exception("成品码绑定的车型一致性记录已不存在，可能是已被删除。\n请重新将该成品码绑定车型一致性数据后重试。");
        }
        return data;
    }

}
