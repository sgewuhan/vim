package com.sg.vim.print.handler;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

import com.mobnut.db.DBActivator;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.sg.sqldb.DDB;
import com.sg.vim.datamodel.IVIMFields;
import com.sg.vim.datamodel.util.VimUtils;

public class SyncMESCert extends AbstractHandler {

    private static final String SQL = "select * from bqyx_mes.mes_certificate where upload_status=1";
    
    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        try {
            SQL_QUERY(VimUtils.MES_DB, SQL );
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;
    }

    
    public void SQL_QUERY(String dataSource, String sql) throws Exception {
        DBCollection c = DBActivator.getCollection(IVIMFields.DB_NAME, "certtemp");

        Connection conn = DDB.getDefault().getConnection(dataSource);

        Statement stat = null;
        ResultSet rs = null;
        try {
            stat = conn.createStatement();
            rs = stat.executeQuery(sql);
            if (rs == null)
                return ;
            ResultSetMetaData meta = rs.getMetaData();

            int count = meta.getColumnCount();

            String[] columns = new String[count];
            for (int i = 0; i < count; i++) {
                columns[i] = meta.getColumnName(i + 1);
            }

             int index = 0;
            while (rs.next()) {
                BasicDBObject ist = new BasicDBObject();
                for (int i = 0; i < columns.length; i++) {
                    Object value = rs.getObject(i + 1);
                    if(value!=null){
                        ist.put(columns[i], value.toString());
                    }
                }
                c.insert(ist);
                System.out.println(index++);
            }

        } catch (Exception e) {
            System.out.println("SQL£º" + sql);
            throw e;
        } finally {
            try {
                if (stat != null)
                    stat.close();
            } catch (Exception e) {
                throw e;
            }
            DDB.getDefault().freeConnection(dataSource, conn);
        }

        return ;
    }
}
