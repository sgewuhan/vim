package com.sg.sqldb.utility;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import com.sg.sqldb.DDB;

public class SQLUtil {

    public static int SQL_COUNT(String dataSource, String sql) {
        String uSql = sql.toUpperCase();
        int fieldStart = uSql.indexOf("SELECT") + 6;
        int fieldEnd = uSql.indexOf("FROM", fieldStart);
        String cSql = sql.substring(0, fieldStart) + " COUNT(*) AS Q$CNT "
                + sql.substring(fieldEnd);
        SQLResult result;
        try {
            result = SQL_QUERY(dataSource, cSql);
            return ((BigDecimal) result.getData().get(0).getValue(0)).intValue();
        } catch (Exception e) {
            return -1;
        }
    }

    /**
     * 
     * @param dataSource
     * @param sql
     * @param pageNo
     *            ,��0��ʼ��ҳ��
     * @param pageCount
     *            �� ÿҳ��ʾ����
     * @return
     * @throws Exception
     */
    public static SQLResult SQL_QUERY_PAGED(String dataSource, String sql, int pageNo,
            int rowCountEveryPage) throws Exception {
        return SQL_ORACLE_QUERY_PAGE(dataSource, sql, pageNo, rowCountEveryPage);// δ���迼�Ǽ���SQL
                                                                                 // SERVER�Լ�My
                                                                                 // SQL��֧��
    }

    private static SQLResult SQL_ORACLE_QUERY_PAGE(String dataSource, String sql, int pageNo,
            int rowCountEveryPage) throws Exception {
        String p1 = "SELECT * FROM (SELECT ROWNUM Q$RNM,Q$INPUT_1.* FROM (";
        String p2 = ") Q$INPUT_1 WHERE ROWNUM<=" + (pageNo + 1) * rowCountEveryPage
                + ") Q$INPUT_2 WHERE Q$INPUT_2.Q$RNM >" + pageNo * rowCountEveryPage;
        return SQL_QUERY(dataSource, p1 + sql + p2);
    }

    public static SQLResult SQL_QUERY(String dataSource, String sql) throws Exception {
        SQLResult result = new SQLResult();
        result.setDataSource(dataSource);
        Connection conn = DDB.getDefault().getConnection(dataSource);

        Statement stat = null;
        ResultSet rs = null;
        try {
            stat = conn.createStatement();
            rs = stat.executeQuery(sql);
            if (rs == null)
                return result;
            ResultSetMetaData meta = rs.getMetaData();

            int count = meta.getColumnCount();

            String[] columns = new String[count];
            for (int i = 0; i < count; i++) {
                columns[i] = meta.getColumnName(i + 1);
            }
            result.setColumns(columns);

            // int index = 0;
            while (rs.next()) {
                SQLRow row = new SQLRow(result.getColumns());// ,index++);
                for (int i = 0; i < count; i++) {
                    row.setValue(i, rs.getObject(i + 1));
                }
                result.add(row);
            }

        } catch (Exception e) {
            System.out.println("SQL��" + sql);
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

        return result;
    }

    public static void SQL_EXECUTE(String dataSource, String[] sqls) throws Exception {
        Connection conn = DDB.getDefault().getConnection(dataSource);
        Statement stat = null;
        boolean autoCommit = conn.getAutoCommit();
        try {
            conn.setAutoCommit(false);
            stat = conn.createStatement();
            for (int i = 0; i < sqls.length; i++) {
                stat.executeUpdate(sqls[i]);
            }
            conn.commit();
        } catch (Exception e) {
            conn.rollback();
            for (int i = 0; i < sqls.length; i++) {
                System.out.println("SQL��" + sqls[i]);
            }
            throw e;
        } finally {
            conn.setAutoCommit(autoCommit);
            try {
                if (stat != null)
                    stat.close();
            } catch (Exception e) {
                throw e;
            }
            DDB.getDefault().freeConnection(dataSource, conn);
        }
    }

    public static SQLResult SQL_EXECUTE_WITH_RETURN(String dataSource, String[] sqls,
            int returnLineNumber) throws Exception {
        Connection conn = DDB.getDefault().getConnection(dataSource);
        Statement stat = null;
        ResultSet rs = null;
        SQLResult result = new SQLResult();
        boolean autoCommit = conn.getAutoCommit();
        try {
            conn.setAutoCommit(false);
            stat = conn.createStatement();
            for (int i = 0; i < sqls.length; i++) {
                if (i != returnLineNumber) {
                    stat.executeUpdate(sqls[i]);
                } else {
                    rs = stat.executeQuery(sqls[i]);
                    if (rs == null)
                        continue;
                    ResultSetMetaData meta = rs.getMetaData();

                    int count = meta.getColumnCount();

                    String[] columns = new String[count];
                    for (int j = 0; j < count; j++) {
                        columns[j] = meta.getColumnName(j + 1);
                    }

                    result.setColumns(columns);

                    // int index = 0;
                    while (rs.next()) {
                        SQLRow row = new SQLRow(result.getColumns());// ,index++);
                        for (int k = 0; k < count; k++) {
                            row.setValue(k, rs.getObject(k + 1));
                        }
                        result.add(row);
                    }
                }
            }
            conn.commit();
        } catch (Exception e) {
            conn.rollback();
            for (int i = 0; i < sqls.length; i++) {
                System.out.println("SQL��" + sqls[i]);
            }
            throw e;
        } finally {
            conn.setAutoCommit(autoCommit);
            try {
                if (stat != null)
                    stat.close();
            } catch (Exception e) {
                throw e;
            }
            DDB.getDefault().freeConnection(dataSource, conn);
        }
        return result;
    }

    public static int SQL_UPDATE(String dataSource, String sql) throws Exception {
        Connection conn = DDB.getDefault().getConnection(dataSource);

        Statement stat = null;
        try {
            stat = conn.createStatement();
            int i = stat.executeUpdate(sql);
            return i;
        } catch (Exception e) {
            System.out.println("SQL��" + sql);
            throw e;
        } finally {
            try {
                stat.close();
            } catch (Exception e) {
                throw e;
            }
            DDB.getDefault().freeConnection(dataSource, conn);
        }
    }

    public static String SQL_GET_NEXTVAL_SQL(String seq_name) {
        return "SELECT " + seq_name + ".NEXTVAL FROM DUAL";
    }

}
