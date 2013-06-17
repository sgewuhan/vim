package com.sg.vim.job;

import java.util.Date;
import java.util.Iterator;

import com.mobnut.commons.Commons;
import com.mobnut.commons.job.ScheduleRepeatJob;
import com.mobnut.commons.util.Utils;
import com.mobnut.db.DBActivator;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.sg.sqldb.utility.SQLResult;
import com.sg.sqldb.utility.SQLRow;
import com.sg.sqldb.utility.SQLUtil;

public class SyncProductCodeData extends ScheduleRepeatJob {

    private static final String query = "select materialcode,materialname,vehicletype "
            + "from VI_CBO_ITEMMASTER where factorycode='001' and materialcode like '88%'";

    public SyncProductCodeData() {
        super("Sync-ProductCode");
    }

    protected void doJob() throws Exception {
        // 测试查询语句
        DBCollection c = DBActivator.getCollection("appportal", "productcodeinfo");
        SQLResult result = SQLUtil.SQL_QUERY("erp", query);
        Iterator<SQLRow> iter = result.iterator();
        while (iter.hasNext()) {
            SQLRow row = iter.next();
            Object materialcode = row.getValue("materialcode");// 成品码
            Object materialname = row.getValue("materialname");// 车型名称
            Object vehicletype = row.getValue("vehicletype");// 公告车型
            if (Utils.isNullOrEmptyString(vehicletype)) {
                continue;
            }
            syncItem(c, materialcode, materialname, vehicletype);
        }
    }

    private void syncItem(DBCollection c, Object materialcode, Object materialname,
            Object vehicletype) {
        try {
            // 查询有无记录

            DBObject q = new BasicDBObject().append("e_02", materialcode);
            DBCursor cursor = c.find(q);
            int cnt = cursor.count();
            if (cnt == 0) {
                c.insert(new BasicDBObject()
                        .append("f_0_2c1", vehicletype)
                        .append("e_03", materialname)
                        .append("e_02", materialcode)
                        .append("_cdate", new Date())
                        .append("_caccount",
                                new BasicDBObject().append("username", "robot-sync").append(
                                        "userid", "robot-sync")));

            }
        } catch (Exception e) {
            Commons.LOGGER.error("error sync product code info", e);
        }
    }

    public void stop() {
        removeJobChangeListener(this);
        cancel();
    }

}
