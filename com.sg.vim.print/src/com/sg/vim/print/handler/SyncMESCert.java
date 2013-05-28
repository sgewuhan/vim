package com.sg.vim.print.handler;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.rap.rwt.service.ServerPushSession;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.handlers.HandlerUtil;

import com.mobnut.db.DBActivator;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.sg.sqldb.DDB;
import com.sg.ui.part.view.TableNavigator;
import com.sg.vim.datamodel.IVIMFields;
import com.sg.vim.datamodel.util.VimUtils;
import com.sg.vim.print.PrintActivator;

public class SyncMESCert extends AbstractHandler {

    private static final String MES_ID = "MES_ID";
    private final static String MES_VEH_HGZBH = "VEH_HGZBH";// "WAK130000000007",
    private final static String MES_VEH_FZRQ = "VEH_FZRQ";// "2012-12-06 00:00:00.0",
    private final static String MES_VEH_CLZZQYMC = "VEH_CLZZQYMC";// "���������������޹�˾",
    private final static String MES_VEH_CLMC = "VEH_CLMC";// "����;���ó�",
    private final static String MES_VEH_CSYS = "VEH_CSYS";// "��ɫ",
    private final static String MES_VEH_FDJH = "VEH_FDJH";// "C11D00103",
    private final static String MES_VEH_RLZL = "VEH_RLZL";// "����",
    private final static String MES_VEH_GL = "VEH_GL";// "45",
    private final static String MES_VEH_PL = "VEH_PL";// "1000",
    private final static String MES_VEH_PFBZ = "VEH_PFBZ";// "GB18352.3-2005��IV",
    private final static String MES_VEH_YH = "VEH_YH";// "7.3",
    private final static String MES_VEH_WKC = "VEH_WKC";// "3895",
    private final static String MES_VEH_WKK = "VEH_WKK";// "1600",
    private final static String MES_VEH_WKG = "VEH_WKG";// "1878",
    private final static String MES_VEH_GBTHPS = "VEH_GBTHPS";// "-/3+1",
    private final static String MES_VEH_FDJXH = "VEH_FDJXH";// "BJ410A1",
    private final static String MES_VEH_LTS = "VEH_LTS";// "4",
    private final static String MES_VEH_LTGG = "VEH_LTGG";// "165/70R13LT 88/86S",
    private final static String MES_VEH_QLJ = "VEH_QLJ";// "1310",
    private final static String MES_VEH_HLJ = "VEH_HLJ";// "1310",
    private final static String MES_VEH_ZJ = "VEH_ZJ";// "2500",
    private final static String MES_VEH_ZH = "VEH_ZH";// "612/1048",
    private final static String MES_VEH_ZS = "VEH_ZS";// "2",
    private final static String MES_VEH_ZXFS = "VEH_ZXFS";// "������",
    private final static String MES_VEH_ZZL = "VEH_ZZL";// "1660",
    private final static String MES_VEH_ZBZL = "VEH_ZBZL";// "1060",
    private final static String MES_VEH_EDZK = "VEH_EDZK";// "7",
    private final static String MES_VEH_ZGCS = "VEH_ZGCS";// "120",
    private final static String MES_VEH_CLZZRQ = "VEH_CLZZRQ";// "2012-12-05 00:00:00.0",
    private final static String MES_VEH_CLPP = "VEH_CLPP";// "������",
    private final static String MES_VEH_CLSBDH = "VEH_CLSBDH";// "LNBMDLAAXCU000487",
    private final static String MES_VEH_BZ = "VEH_BZ";// "��󾻹���42kW",
    private final static String MES_CLZTXX = "CLZTXX";// "QX",
    private final static String MES_CLLX = "CLLX";// "���ó����ͳ�",
    private final static String MES_ZXZS = "ZXZS";// "1",
    private final static String MES_VERCODE = "VERCODE";// "1SS+xzGzlv9rs4nWo7zCaYUgI+RFvT3wZdvk1QWB2sXVxHo9GUh5oi655TWuL3V8cvKHmexziHh6hMd0CyODVChO9wEGaMhYeaZL/2nkAIwmO7RLMAFtPvWWUj4ndPg/e9+27qYJYVBYOu6dtOGwxmNc1KFIB8F85WEyu4yf+GzLk1RUgVay/v2Jtn1GX0jTUHoz6dvNnyASLFenLkWDX7iLxIaymzqTaeSqp4fuX3bW5SdpmNaS9kk3zcKgn6T1a6kdbmdkwpaxLBzSY7KIcA==",
    private final static String MES_CDDBJ = "CDDBJ";// "2",
    private final static String MES_CLSCDWMC = "CLSCDWMC";// "���������������޹�˾",
    private final static String MES_CLSCDWDZ = "CLSCDWDZ";// "�����кϴ������������ڴ�",
    private final static String MES_DYWYM = "DYWYM";// "2C5B3D3DD52C465F899B3685D2089D80",
    private final static String MES_ZZBH = "ZZBH";// "0000107",
    private final static String MES_QYBZ = "QYBZ";// "Q/YX-501��2012��",
    private final static String MES_CPGGH = "CPGGH";// "ZKADE3XN01X",
    private final static String MES_GGPC = "GGPC";// "240",
    private final static String MES_GGSXRQ = "GGSXRQ";// "2012-09-19 00:00:00.0",
    private final static String MES_NOTICE_CODE = "NOTICE_CODE";// "BJ6390AHZ1A",
    private static final String MES_QYQTXX = "QYQTXX";
    private static final String MES_VEH_DPXH = "VEH_DPXH";
    private static final String MES_VEH_DPHGZBH = "VEH_DPHGZBH";

    // ������MES���е�VIM��û�е�
    private final static String MES_STATUSFLAG = "STATUSFLAG";// "1",
    private final static String MES_NOTICE_CODE_ID = "NOTICE_CODE_ID";// "2",
    private final static String MES_MOBAN = "MOBAN";// "0",
    private final static String MES_UPLOAD_STATUS = "UPLOAD_STATUS";// "1",
    private final static String MES_QDFS = "QDFS";// "��������",
    private final static String MES_UNCHANGE = "UNCHANGE";// "0"

    // ������MES���޷�ȡֵ��

    private static final String SQL = "select * from bqyx_mes.mes_certificate where upload_status=1";
    private static final String MES_VEH_HXNBC = "VEH_HXNBC";
    private static final String MES_VEH_HXNBK = "VEH_HXNBK";
    private static final String MES_VEH_HXNBG = "VEH_HXNBG";
    private static final String MES_VEH_EDZZL = "VEH_EDZZL";
    private static final String MES_VEH_ZZLLYXS = "VEH_ZZLLYXS";
    private static final String MES_VEH_ZQYZZL = "VEH_ZQYZZL";
    private static final String MES_VEH_BGCAZZDYXZZL = "VEH_BGCAZZDYXZZL";
    private static final String MES_VEH_JSSZCRS = "VEH_JSSZCRS";
    private static final String MES_VEH_CJH = "VEH_CJH";
    private static final String MES_VEH_DPID = "VEH_DPID";
    private static final String MES_QYID = "QYID";
    private static final String MES_HZDCZFS = "HZDCZFS";
    private static final String MES_HZDFS = "HZDFS";
    private static final String MES_QZDCZFS = "QZDCZFS";
    private static final String MES_QZDFS = "QZDFS";
    private static final String MES_VEH_TMXX = "VEH_TMXX";
    private static final String MES_PZXLH = "PZXLH";
    private static final String MES_VEH_ZCHGZBH = "VEH_ZCHGZBH";
    private static final String MES_CLIENT_HARDWARE_INFO = "CLIENT_HARDWARE_INFO";
    private static final String MES_ERP_CODE_ID = "ERP_CODE_ID";
    private static final String MES_HD_USER = "HD_USER";
    private static final String MES_CZRQ = "CZRQ";
    private static final String MES_CREATE_DATE = "CREATE_DATE";
    private static final String MES_CAUSE = "CAUSE";
    private static final String MES_UPDATE_CAUSE = "UPDATE_CAUSE";
    private static final String MES_PRINT_TIME = "PRINT_TIME";
    private static final String MES_ABOLISH_CAUSE = "ABOLISH_CAUSE";
    private static final String MES_DELETE_CAUSE = "DELETE_CAUSE";
    private DBCollection collection;
    private ServerPushSession pushSession;

    public SyncMESCert() {
        super();
        collection = DBActivator.getCollection(IVIMFields.DB_NAME, IVIMFields.COL_CERF);
        pushSession = new ServerPushSession();
        pushSession.start();
    }

    @Override
    public void dispose() {
        pushSession.stop();
        super.dispose();
    }

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        try {

            SQL_QUERY(VimUtils.MES_DB, (TableNavigator) HandlerUtil.getActivePart(event));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public void SQL_QUERY(final String dataSource, final TableNavigator part) throws Exception {
        final Display display = part.getSite().getShell().getDisplay();
        Job job = new Job("ͬ��MES���Ѿ��ϴ��ĺϸ�֤����") {

            @Override
            protected IStatus run(IProgressMonitor monitor) {
                try {
                    SyncMESCert.this.run(dataSource, monitor);
                } catch (Exception e) {
                    return new Status(IStatus.ERROR, PrintActivator.PLUGIN_ID, Status.OK,
                            "ͬ��ʱ��������", e);
                }
                return Status.OK_STATUS;
            }
        };
        job.setUser(true);
        job.addJobChangeListener(new JobChangeAdapter() {

            @Override
            public void done(IJobChangeEvent event) {
                display.syncExec(new Runnable() {

                    @Override
                    public void run() {
                        part.refresh2();
                    }
                });
            }

        });
        job.schedule();
    }

    protected void run(String dataSource, IProgressMonitor monitor) throws Exception {
        Connection conn = DDB.getDefault().getConnection(dataSource);

        Statement stat = null;
        ResultSet rs = null;
        try {
            stat = conn.createStatement();
            rs = stat.executeQuery(SQL);
            if (rs == null)
                return;
            ResultSetMetaData meta = rs.getMetaData();

            int count = meta.getColumnCount();
            monitor.beginTask("��ʼͬ������", count);

            String[] columns = new String[count];
            for (int i = 0; i < count; i++) {
                columns[i] = meta.getColumnName(i + 1);
            }

//            int index = 0;
            while (rs.next()) {
                BasicDBObject ist = new BasicDBObject();
                for (int i = 0; i < columns.length; i++) {
                    Object value = rs.getObject(i + 1);
                    if (value != null) {
                        ist.put(columns[i], value.toString());
                    }
                }

                monitor.setTaskName((String) ist.get(MES_VEH_CLMC));
                importMESItem(ist);
                monitor.worked(1);
            }

        } catch (Exception e) {
            System.out.println("SQL��" + SQL);
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

        return;

    }

    private void importMESItem(BasicDBObject ist) {
        // �ж��Ƿ��Ѿ�����
        Object mes_id = ist.get("ID");
        long cnt = collection.count(new BasicDBObject().append(MES_ID, mes_id));
        if (cnt != 0) {
            return;
        }

        BasicDBObject cert = new BasicDBObject();
        // ��Ӧ��

        // ID VARCHAR2(32)
        // VEH_HGZBH VARCHAR2(20) Y �ϸ�֤���
        cert.put(IVIMFields.mVeh__Wzghzbh, ist.get(MES_VEH_HGZBH));// Veh_Wzghzbh";

        // VEH_FZRQ DATE Y ��֤ʱ��
        cert.put(IVIMFields.mVeh_Fzrq, ist.get(MES_VEH_FZRQ));// Veh_Fzrq";//��֤�����ַ�14YYYY��MM��DD��

        // VEH_CLZZQYMC VARCHAR2(200) Y ����������ҵ����
        cert.put(IVIMFields.mVeh_Clzzqymc, ist.get(MES_VEH_CLZZQYMC));// Veh_Clzzqymc";//����������ҵ�����ַ�64

        // VEH_CLMC VARCHAR2(32) Y ��������
        cert.put(IVIMFields.mVeh_Clmc, ist.get(MES_VEH_CLMC));// Veh_Clmc";//���������ַ�54�磺�γ�

        // VEH_CLXH VARCHAR2(30) Y �����ͺ� NOTICE_CODE VARCHAR2(100) Y �����ͺ�
        cert.put(IVIMFields.mVeh_Clxh, ist.get(MES_NOTICE_CODE));// Veh_Clxh";//�����ͺ��ַ�30

        // VEH_DPXH VARCHAR2(32) Y �����ͺ�
        cert.put(IVIMFields.mVeh_Dpxh, ist.get(MES_VEH_DPXH));// Veh_Dpxh";//�����ͺ��ַ�30����QX��ʽʱʹ��

        // VEH_CSYS VARCHAR2(32) Y ������ɫ
        cert.put(IVIMFields.mVeh_Csys, ist.get(MES_VEH_CSYS));// Veh_Csys";//������ɫ�ַ�70������ɫ֮���á�/���ָ�

        // VEH_DPHGZBH VARCHAR2(32) Y ���̺ϸ�֤���
        cert.put(IVIMFields.mVeh_Dphgzbh, ist.get(MES_VEH_DPHGZBH));// Veh_Dphgzbh";//���̺ϸ�֤����ַ�15ȫ�ʽ15λ�����̷�ʽ����

        // VEH_FDJH VARCHAR2(32) Y ��������
        cert.put(IVIMFields.mVeh_Fdjh, ist.get(MES_VEH_FDJH));// Veh_Fdjh";//���������ַ�30

        // VEH_RLZL VARCHAR2(32) Y ȼ������
        cert.put(IVIMFields.mVeh_Rlzl, ist.get(MES_VEH_RLZL));// Veh_Rlzl";//ȼ�������ַ�30����ȼ��֮���á�/���ָ�

        // VEH_GL VARCHAR2(32) Y ����
        cert.put(IVIMFields.mVeh_Gl, ist.get(MES_VEH_GL));// Veh_Gl";//�����ַ�7���ֹ���֮���á�/���ָ�

        // VEH_PL VARCHAR2(32) Y ����
        cert.put(IVIMFields.mVeh_Pl, ist.get(MES_VEH_PL));// Veh_Pl";//�����ַ�5

        // VEH_PFBZ VARCHAR2(32) Y �ŷű�׼
        cert.put(IVIMFields.mVeh_Pfbz, ist.get(MES_VEH_PFBZ));// Veh_Pfbz";//�ŷű�׼�ַ�60

        // VEH_YH VARCHAR2(32) Y �ͺ�
        cert.put(IVIMFields.mVeh_Yh, ist.get(MES_VEH_YH));// Veh_Yh";//�ͺ��ַ�30

        // VEH_WKC VARCHAR2(32) Y �����ߴ糤
        cert.put(IVIMFields.mVeh_Wkc, ist.get(MES_VEH_WKC));// Veh_Wkc";//�������ַ�5

        // VEH_WKK VARCHAR2(32) Y �����ߴ��
        cert.put(IVIMFields.mVeh_Wkk, ist.get(MES_VEH_WKK));// Veh_Wkk";//�������ַ�4

        // VEH_WKG VARCHAR2(32) Y �����ߴ��
        cert.put(IVIMFields.mVeh_Wkg, ist.get(MES_VEH_WKG));// Veh_Wkg";//�������ַ�4

        // VEH_HXNBC VARCHAR2(32) Y �����ڲ��ߴ糤
        cert.put(IVIMFields.mVeh_Hxnbc, ist.get(MES_VEH_HXNBC));// Veh_Hxnbc";//�����ڲ����ַ�5

        // VEH_HXNBK VARCHAR2(32) Y �����ڲ��ߴ��
        cert.put(IVIMFields.mVeh_Hxnbk, ist.get(MES_VEH_HXNBK));// Veh_Hxnbk";//�����ڲ����ַ�4

        // VEH_HXNBG VARCHAR2(32) Y �����ڲ��ߴ��
        cert.put(IVIMFields.mVeh_Hxnbg, ist.get(MES_VEH_HXNBG));// Veh_Hxnbg";//�����ڲ����ַ�4

        // VEH_GBTHPS VARCHAR2(32) Y �ְ嵯��Ƭ��
        cert.put(IVIMFields.mVeh_Gbthps, ist.get(MES_VEH_GBTHPS));// Veh_Gbthps";//�ְ嵯��Ƭ���ַ�30

        // VEH_FDJXH VARCHAR2(32) Y �������ͺ�
        cert.put(IVIMFields.mVeh_Fdjxh, ist.get(MES_VEH_FDJXH));// Veh_Fdjxh";//�������ͺ��ַ�20

        // VEH_LTS VARCHAR2(32) Y ��̥����
        cert.put(IVIMFields.mVeh_Lts, ist.get(MES_VEH_LTS));// Veh_Lts";//��̥���ַ�2

        // VEH_LTGG VARCHAR2(70) Y ��̥���
        cert.put(IVIMFields.mVeh_Ltgg, ist.get(MES_VEH_LTGG));// Veh_Ltgg";//��̥����ַ�20

        // VEH_QLJ VARCHAR2(32) Y ǰ�־�
        cert.put(IVIMFields.mVeh_Qlj, ist.get(MES_VEH_QLJ));// Veh_Qlj";//ǰ�־��ַ�4

        // VEH_HLJ VARCHAR2(32) Y ���־�
        cert.put(IVIMFields.mVeh_Hlj, ist.get(MES_VEH_HLJ));// Veh_Hlj";//���־��ַ�54

        // VEH_ZJ VARCHAR2(32) Y ���
        cert.put(IVIMFields.mVeh_Zj, ist.get(MES_VEH_ZJ));// Veh_Zj";//����ַ�60

        // VEH_ZH VARCHAR2(32) Y ���
        cert.put(IVIMFields.mVeh_Zh, ist.get(MES_VEH_ZH));// Veh_Zh";//����ַ�30

        // VEH_ZS VARCHAR2(32) Y ����
        cert.put(IVIMFields.mVeh_Zs, ist.get(MES_VEH_ZS));// Veh_Zs";//�����ַ�1

        // VEH_ZXFS VARCHAR2(32) Y ת����ʽ
        cert.put(IVIMFields.mVeh_Zxxs, ist.get(MES_VEH_ZXFS));// Veh_Zxxs";//ת����ʽ�ַ�6�磺������

        // VEH_ZZL VARCHAR2(32) Y ������
        cert.put(IVIMFields.mVeh_Zzl, ist.get(MES_VEH_ZZL));// Veh_Zzl";//�������ַ�8

        // VEH_ZBZL VARCHAR2(32) Y ��������
        cert.put(IVIMFields.mVeh_Zbzl, ist.get(MES_VEH_ZBZL));// Veh_Zbzl";//���������ַ�8

        // VEH_EDZZL VARCHAR2(32) Y �������
        cert.put(IVIMFields.mVeh_Edzzl, ist.get(MES_VEH_EDZZL));// Veh_Edzzl";//��������ַ�8

        // VEH_ZZLLYXS VARCHAR2(32) Y ����������ϵ��
        cert.put(IVIMFields.mVeh_Zzllyxs, ist.get(MES_VEH_ZZLLYXS));// Veh_Zzllyxs";//����������ϵ���ַ�30

        // VEH_ZQYZZL VARCHAR2(32) Y ׼ǣ��������
        cert.put(IVIMFields.mVeh_Zqyzzl, ist.get(MES_VEH_ZQYZZL));// Veh_Zqyzzl";//׼ǣ���������ַ�8

        // VEH_BGCAZZDYXZZL VARCHAR2(32) Y ��ҳ������������������
        cert.put(IVIMFields.mVeh_Bgcazzdyxzzl, ist.get(MES_VEH_BGCAZZDYXZZL));// Veh_Bgcazzdyxzzl";//��ҳ�������������������ַ�8

        // VEH_JSSZCRS VARCHAR2(32) Y ��ʻ��׼������
        cert.put(IVIMFields.mVeh_Jsszcrs, ist.get(MES_VEH_JSSZCRS));// Veh_Jsszcrs";//��ʻ��׼�������ַ�3

        // VEH_EDZK VARCHAR2(32) Y ��ؿ�
        cert.put(IVIMFields.mVeh_Edzk, ist.get(MES_VEH_EDZK));// Veh_Edzk";//��ؿ��ַ�3

        // VEH_ZGCS VARCHAR2(32) Y ��߳���
        cert.put(IVIMFields.mVeh_Zgcs, ist.get(MES_VEH_ZGCS));// Veh_Zgcs";//��߳����ַ�5

        // VEH_CLZZRQ DATE Y ������������
        Object value = ist.get(MES_VEH_CLZZRQ);
        if (value instanceof Date) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy��MM��dd��");
            String mdate = sdf.format(value);
            cert.put(IVIMFields.mVeh_Clzzrq, mdate);// Veh_Clzzrq";//�������������ַ�14YYYY��MM��DD��
        }

        // VEH_ZCHGZBH VARCHAR2(20) Y �����ϸ�֤���
        cert.put(IVIMFields.mVeh_Zchgzbh, ist.get(MES_VEH_ZCHGZBH));// Veh_Zchgzbh";//�����ϸ�֤����ַ�14

        // VEH_CLPP VARCHAR2(50) Y ����Ʒ��
        cert.put(IVIMFields.mVeh_Clpp, ist.get(MES_VEH_CLPP));// Veh_Clpp";//����Ʒ���ַ�30

        // VEH_CLSBDH VARCHAR2(32) Y ����ʶ�����
        cert.put(IVIMFields.mVeh_Clsbdh, ist.get(MES_VEH_CLSBDH));// Veh_Clsbdh";//����ʶ������ַ�17

        // VEH_CJH VARCHAR2(32) Y ���ܺ�
        cert.put(IVIMFields.mVeh_Cjh, ist.get(MES_VEH_CJH));// Veh_Cjh";//���ܺ��ַ�25

        // VEH_DPID VARCHAR2(20) Y ����ID
        cert.put(IVIMFields.mVeh_Dpid, ist.get(MES_VEH_DPID));// Veh_Dpid";//����ID�ַ�7

        // VEH_BZ VARCHAR2(1000) Y ��ע
        cert.put(IVIMFields.mVeh_Bz, ist.get(MES_VEH_BZ));// Veh_Bz";//��ע�ַ�300

        // CLZTXX VARCHAR2(10) Y ����״̬��ϢQX/DP
        cert.put(IVIMFields.mVeh_Clztxx, ist.get(MES_CLZTXX));// Veh_Clztxx";//����״̬��Ϣ�ַ�2ȡֵΪQX��DP

        // CLLX VARCHAR2(26) Y ��������
        cert.put(IVIMFields.mVeh_Clfl, ist.get(MES_CLLX));// Veh_Clfl";//���������ַ�26����ԭ����Veh_cllx���磺����

        // ZXZS VARCHAR2(1) Y ת�������
        cert.put(IVIMFields.mVeh_Zxzs, ist.get(MES_ZXZS));// Veh_Zxzs";//ת��������ַ�1ȡֵΪ1��2��3��Ĭ��Ϊ1������ת����ĳ�����д��

        // VERCODE VARCHAR2(500) Y У���룬�ɴ�ӡ�ӿ�����
        cert.put(IVIMFields.mVeh_Jyw, ist.get(MES_VERCODE));// Veh_Jyw";//У����Ϣ�ַ�255+

        // CDDBJ VARCHAR2(1) Y ���綯��� 1�� 2��
        cert.put(IVIMFields.mVeh_Cddbj, ist.get(MES_CDDBJ));// Veh_Cddbj";

        // CLSCDWMC VARCHAR2(64) Y ����������λ����
        cert.put(IVIMFields.mVeh_Clscdwmc, ist.get(MES_CLSCDWMC));// Veh_Clscdwmc";//����������λ�����ַ�64

        // CLSCDWDZ VARCHAR2(70) Y ����������λ��ַ
        cert.put(IVIMFields.mVeh_Cpscdz, ist.get(MES_CLSCDWDZ));// Veh_Cpscdz";//����������λ��ַ�ַ�70

        // DYWYM VARCHAR2(50) Y ��ӡΨһ�룬�ɴ�ӡ�ӿ�����
        cert.put(IVIMFields.mVeh_Dywym, ist.get(MES_DYWYM));// Veh_Dywym";//��ӡΨһ��

        // QYID VARCHAR2(8) Y ��ҵ���
        cert.put(IVIMFields.mVeh_Qyid, ist.get(MES_QYID));// Veh_Qyid";//��ҵID�ַ�88λ������ҵID

        // ZZBH VARCHAR2(50) Y ֽ�ű��
        cert.put(IVIMFields.mVeh_Zzbh, ist.get(MES_ZZBH));// Veh_Zzbh";

        // HZDCZFS VARCHAR2(50) Y ���ƶ�������ʽ
        cert.put(IVIMFields.mVeh__Hzdczfs, ist.get(MES_HZDCZFS));// Veh_Hzdczfs";

        // HZDFS VARCHAR2(50) Y ���ƶ���ʽ
        cert.put(IVIMFields.mVeh__Hzdfs, ist.get(MES_HZDFS));// Veh_Hzdfs";

        // QZDCZFS VARCHAR2(50) Y ǰ�ƶ�������ʽ
        cert.put(IVIMFields.mVeh__Qzdczfs, ist.get(MES_QZDCZFS));// Veh_Qzdczfs";

        // QZDFS VARCHAR2(50) Y ǰ�ƶ���ʽ
        cert.put(IVIMFields.mVeh__Qzdfs, ist.get(MES_QZDFS));// Veh_Qzdfs";

        // QYBZ VARCHAR2(200) Y ��ҵ��׼
        cert.put(IVIMFields.mVeh_Qybz, ist.get(MES_QYBZ));// Veh_Qybz";//��ҵ��׼�ַ�200����xxxx-xxxx��xxxx�����ĸ�ʽ�����м䲿��Ϊ����

        // QYQTXX VARCHAR2(400) Y ��ҵ������Ϣ
        cert.put(IVIMFields.mVeh_Qyqtxx, ist.get(MES_QYQTXX));// Veh_Qyqtxx";//��ҵ������Ϣ�ַ�400����Ŀ������Ҫ�س����еĵط�ʹ�á�%%����ʾ��

        // VEH_TMXX VARCHAR2(1000) Y �ϸ�֤������Ϣ
        cert.put(IVIMFields.mVeh_Tmxx, ist.get(MES_VEH_TMXX));// Veh_Tmxx";//�ϸ�֤������Ϣ�ַ�1000

        // CPGGH VARCHAR2(50) Y ��Ʒ�����
        cert.put(IVIMFields.mVeh_Cpggh, ist.get(MES_CPGGH));// Veh_Cpggh";//�����

        // GGPC VARCHAR2(50) Y ��������
        cert.put(IVIMFields.mVeh_Ggpc, ist.get(MES_GGPC));// Veh_Ggpc";//��������

        // GGSXRQ DATE Y ������Ч����
        value = ist.get(MES_GGSXRQ);
        if (value instanceof Date) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            cert.put(IVIMFields.mVeh_Ggsxrq, sdf.format((Date) value));// Veh_Ggsxrq";//������Ч����
        }

        // PZXLH VARCHAR2(50) Y �������к�
        cert.put(IVIMFields.mVeh__Pzxlh, ist.get(MES_PZXLH));// Veh_Pzxlh";

        // ���豣���
        // cert.put(IVIMFields.mVeh_Stopbits, MES_EMPTY_VALUE);//Veh_Stopbits";
        // cert.put(IVIMFields.mVeh_Databits, MES_EMPTY_VALUE);//Veh_Databits";
        // cert.put(IVIMFields.mVeh_Parity, MES_EMPTY_VALUE);//Veh_Parity";
        // cert.put(IVIMFields.mVeh_Baud, MES_EMPTY_VALUE);//Veh_Baud";
        // cert.put(IVIMFields.mVeh_Connect, MES_EMPTY_VALUE);//Veh_Connect";
        // cert.put(IVIMFields.mVeh_PrintPosTop,
        // MES_EMPTY_VALUE);//Veh_PrintPosTop";
        // cert.put(IVIMFields.mVeh_PrintPosLeft,
        // MES_EMPTY_VALUE);//Veh_PrintPosLeft";
        // cert.put(IVIMFields.mVeh_PrinterName,
        // MES_EMPTY_VALUE);//Veh_PrinterName";

        // MES���е�����Ҫ��
        // STATUSFLAG NUMBER(2) Y ����/����״̬
        cert.put("MES_" + MES_STATUSFLAG, ist.get(MES_STATUSFLAG));
        // NOTICE_CODE_ID VARCHAR2(32) Y ���
        cert.put("MES_" + MES_NOTICE_CODE_ID, ist.get(MES_NOTICE_CODE_ID));
        // MOBAN NUMBER(2) Y Ĭ��0 ��ģ��
        cert.put("MES_" + MES_MOBAN, ist.get(MES_MOBAN));
        // UPLOAD_STATUS NUMBER(2) Y Ĭ��0 û�ϴ�
        cert.put("MES_" + MES_UPLOAD_STATUS, ist.get(MES_UPLOAD_STATUS));
        // QDFS VARCHAR2(50) Y ������ʽ��4��2������
        cert.put("MES_" + MES_QDFS, ist.get(MES_QDFS));
        // UNCHANGE NUMBER(2) Y ���״̬ 1����� 0δ���
        cert.put("MES_" + MES_UNCHANGE, ist.get(MES_UNCHANGE));
        // CLIENT_HARDWARE_INFO VARCHAR2(100) Y �ϴ�����Ӳ����Ϣ
        cert.put("MES_" + MES_CLIENT_HARDWARE_INFO, ist.get(MES_CLIENT_HARDWARE_INFO));
        // ERP_CODE_ID VARCHAR2(32) Y ���ͱ����
        cert.put("MES_" + MES_ERP_CODE_ID, ist.get(MES_ERP_CODE_ID));
        // HD_USER VARCHAR2(30) Y ��ҵ�û���
        cert.put("MES_" + MES_HD_USER, ist.get(MES_HD_USER));
        // CZRQ DATE Y ��������
        cert.put("MES_" + MES_CZRQ, ist.get(MES_CZRQ));
        // CREATE_DATE DATE Y ֤���������
        cert.put("MES_" + MES_CREATE_DATE, ist.get(MES_CREATE_DATE));
        // CAUSE VARCHAR2(100) Y ����ԭ��
        cert.put("MES_" + MES_CAUSE, ist.get(MES_CAUSE));
        // UPDATE_CAUSE VARCHAR2(100) Y ���ϴ��ĺϸ�֤�޸�ԭ��
        cert.put("MES_" + MES_UPDATE_CAUSE, ist.get(MES_UPDATE_CAUSE));
        // PRINT_TIME DATE Y ��ӡ����
        cert.put("MES_" + MES_PRINT_TIME, ist.get(MES_PRINT_TIME));
        // ABOLISH_CAUSE VARCHAR2(100) Y ����ԭ��
        cert.put("MES_" + MES_ABOLISH_CAUSE, ist.get(MES_ABOLISH_CAUSE));
        // DELETE_CAUSE VARCHAR2(100) Y ����ԭ��
        cert.put("MES_" + MES_DELETE_CAUSE, ist.get(MES_DELETE_CAUSE));

        // ����ͬ����,����ID�����ж��Ƿ���Ҫͬ��
        cert.put(MES_ID, mes_id);

        // VIM�е�״̬
        cert.put(IVIMFields.LIFECYCLE, IVIMFields.LC_UPLOADED);

        collection.insert(cert);
    }
}
