package com.sg.vim.restful;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.types.ObjectId;

import com.mobnut.db.DBActivator;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;
import com.sg.vim.model.IVIMFields;
import com.sg.webdb.service.HTTPRequestException;

public class ProductInformationService extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {

        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json");
        // Map<String, String> parameters = getInputParameters(req);
        DBCollection productCodeCol = DBActivator.getCollection(IVIMFields.DB_NAME,
                IVIMFields.COL_PRODUCTCODEINFO);
        // 配置需邦定
        // COC需绑定
        // 如果DPid非空，coc2需邦定
        // BasicDBObject query = new BasicDBObject().append(IVIMFields.CFG_ID,
        // new BasicDBObject().append("$ne", null)).append(IVIMFields.COC_ID,
        // new BasicDBObject().append("$ne", null));
        DBCursor cur = productCodeCol.find(new BasicDBObject().append(IVIMFields.COC_ID,
                new BasicDBObject().append("$ne", null)));
        BasicDBList result = new BasicDBList();
        while (cur.hasNext()) {
            DBObject product = cur.next();
            addrow(result, product);
        }
        BasicDBObject res = new BasicDBObject().append("count", result.size()).append("result", result);
        try {
            String rspData = serialize(res);
            resp.getWriter().write(rspData);
        } catch (HTTPRequestException e) {
            resp.sendError(e.getErrorCode(), e.getMessage());
        }
    }

    private void addrow(BasicDBList result, DBObject product) {
        Object cocid = product.get(IVIMFields.COC_ID);
        if (!(cocid instanceof ObjectId)) {
            return;
        }
        BasicDBObject row = new BasicDBObject();
        DBCollection col = DBActivator.getCollection(IVIMFields.DB_NAME, IVIMFields.COL_COCINFO);
        DBObject coc = col.findOne(new BasicDBObject().append("_id", cocid));
        // NOTICE_CODE
        row.put("noticeCode", product.get(IVIMFields.F_0_2C1));
        // ERP_PRODUCT_CODE
        row.put("erpProductCode", product.get(IVIMFields.E_02));
        // ENGINE_TYPE
        row.put("engineType", coc.get(IVIMFields.F_C4));
        // RATED_PASSENGER
        row.put("ratedPassenger", coc.get(IVIMFields.F_42_1));
        // FUEL_TYPE
        row.put("fuelType", coc.get(IVIMFields.F_25));
        // DRIVER_TYPE
        row.put("driverType", coc.get(IVIMFields.D_22));
        // CURB_WEIGHT
        row.put("curbWeight", coc.get(IVIMFields.C_08));
        // VEHICLE_BRAND
        row.put("vehicleBrand", coc.get(IVIMFields.F_C0_2));
        // VEHICLE_TYPE
        row.put("vehicleType", coc.get(IVIMFields.C_22));
        // OUTPUT_VOLUME
        row.put("outputVolume", coc.get(IVIMFields.F_24));
        // POWER
        row.put("power", coc.get(IVIMFields.C_01));
        // TOTAL_QUALITY
        row.put("totalWeight", coc.get(IVIMFields.F_14_1));
        // BODY_COLOR
        row.put("bodyColor", coc.get(IVIMFields.F_38));
        // TYRE_SIZE
        row.put("tyreSize", coc.get(IVIMFields.F_32A));
        // MAXIMUN_NET_POWER
        row.put("maximunNetPower", coc.get(IVIMFields.F_26));

        result.add(row);
    }

    protected Map<String, String> getInputParameters(HttpServletRequest req) {
        Map<String, String[]> reqParaMap = req.getParameterMap();
        Enumeration<String> paraNames = req.getParameterNames();

        Map<String, String> result = new HashMap<String, String>();

        while (paraNames.hasMoreElements()) {
            String paraName = paraNames.nextElement();

            String[] value = reqParaMap.get(paraName);
            if (value != null && value.length > 0) {
                result.put(paraName, value[0]);
            }
        }

        return result;
    }

    protected String serialize(DBObject data) throws HTTPRequestException {
        try {
            return JSON.serialize(data);
        } catch (Exception e) {
            throw new HTTPRequestException(HTTPRequestException.SERVER_ERROR + e.getMessage(),
                    HttpServletResponse.SC_NOT_FOUND);
        }
    }

}
