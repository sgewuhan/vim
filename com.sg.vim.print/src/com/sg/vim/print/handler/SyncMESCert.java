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

    // ������MES���е�VIM��û�е�
    private final static String MES_STATUSFLAG = "STATUSFLAG";// "1",
    private final static String MES_NOTICE_CODE_ID = "NOTICE_CODE_ID";// "2",
    private final static String MES_MOBAN = "MOBAN";// "0",
    private final static String MES_UPLOAD_STATUS = "UPLOAD_STATUS";// "1",
    private final static String MES_QDFS = "QDFS";// "��������",
    private final static String MES_UNCHANGE = "UNCHANGE";// "0"

    // ������MES���޷�ȡֵ��
    private final static String MES_EMPTY_VALUE = "";

    private static final String SQL = "select * from bqyx_mes.mes_certificate where upload_status=1";
    private DBCollection collection;

    public SyncMESCert() {
        super();
        collection = DBActivator.getCollection(IVIMFields.DB_NAME, IVIMFields.COL_CERF);
    }

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        try {
            SQL_QUERY(VimUtils.MES_DB, SQL);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public void SQL_QUERY(String dataSource, String sql) throws Exception {
        Connection conn = DDB.getDefault().getConnection(dataSource);

        Statement stat = null;
        ResultSet rs = null;
        try {
            stat = conn.createStatement();
            rs = stat.executeQuery(sql);
            if (rs == null)
                return;
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
                    if (value != null) {
                        ist.put(columns[i], value.toString());
                    }
                }

                importMESItem(ist);
                System.out.println(index++);
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

        return;
    }

    private void importMESItem(BasicDBObject ist) {
        //�ж��Ƿ��Ѿ�����
        Object mes_id = ist.get("ID");
        long cnt = collection.count(new BasicDBObject().append(MES_ID, mes_id));
        if(cnt!=0){
            return;
        }
        
        BasicDBObject cert = new BasicDBObject();
        // ��Ӧ��
        cert.put(IVIMFields.mVeh_Clztxx, ist.get(MES_CLZTXX));// Veh_Clztxx";//����״̬��Ϣ�ַ�2ȡֵΪQX��DP
        cert.put(IVIMFields.mVeh_Fzrq, ist.get(MES_VEH_FZRQ));// Veh_Fzrq";//��֤�����ַ�14YYYY��MM��DD��
        cert.put(IVIMFields.mVeh_Clzzqymc, ist.get(MES_VEH_CLZZQYMC));// Veh_Clzzqymc";//����������ҵ�����ַ�64
        cert.put(IVIMFields.mVeh_Clfl, ist.get(MES_CLLX));// Veh_Clfl";//���������ַ�26����ԭ����Veh_cllx���磺����
        cert.put(IVIMFields.mVeh_Clmc, ist.get(MES_VEH_CLMC));// Veh_Clmc";//���������ַ�54�磺�γ�
        cert.put(IVIMFields.mVeh_Clpp, ist.get(MES_VEH_CLPP));// Veh_Clpp";//����Ʒ���ַ�30
        cert.put(IVIMFields.mVeh_Clxh, ist.get(MES_NOTICE_CODE));// Veh_Clxh";//�����ͺ��ַ�30
        cert.put(IVIMFields.mVeh_Csys, ist.get(MES_VEH_CSYS));// Veh_Csys";//������ɫ�ַ�70������ɫ֮���á�/���ָ�
        cert.put(IVIMFields.mVeh_Clsbdh, ist.get(MES_VEH_CLSBDH));// Veh_Clsbdh";//����ʶ������ַ�17
        cert.put(IVIMFields.mVeh_Fdjh, ist.get(MES_VEH_FDJH));// Veh_Fdjh";//���������ַ�30
        cert.put(IVIMFields.mVeh_Fdjxh, ist.get(MES_VEH_FDJXH));// Veh_Fdjxh";//�������ͺ��ַ�20
        cert.put(IVIMFields.mVeh_Rlzl, ist.get(MES_VEH_RLZL));// Veh_Rlzl";//ȼ�������ַ�30����ȼ��֮���á�/���ָ�
        cert.put(IVIMFields.mVeh_Pfbz, ist.get(MES_VEH_PFBZ));// Veh_Pfbz";//�ŷű�׼�ַ�60
        cert.put(IVIMFields.mVeh_Pl, ist.get(MES_VEH_PL));// Veh_Pl";//�����ַ�5
        cert.put(IVIMFields.mVeh_Gl, ist.get(MES_VEH_GL));// Veh_Gl";//�����ַ�7���ֹ���֮���á�/���ָ�
        cert.put(IVIMFields.mVeh_Zxxs, ist.get(MES_VEH_ZXFS));// Veh_Zxxs";//ת����ʽ�ַ�6�磺������
        cert.put(IVIMFields.mVeh_Qlj, ist.get(MES_VEH_QLJ));// Veh_Qlj";//ǰ�־��ַ�4
        cert.put(IVIMFields.mVeh_Hlj, ist.get(MES_VEH_HLJ));// Veh_Hlj";//���־��ַ�54
        cert.put(IVIMFields.mVeh_Lts, ist.get(MES_VEH_LTS));// Veh_Lts";//��̥���ַ�2
        cert.put(IVIMFields.mVeh_Ltgg, ist.get(MES_VEH_LTGG));// Veh_Ltgg";//��̥����ַ�20
        cert.put(IVIMFields.mVeh_Gbthps, ist.get(MES_VEH_GBTHPS));// Veh_Gbthps";//�ְ嵯��Ƭ���ַ�30
        cert.put(IVIMFields.mVeh_Zj, ist.get(MES_VEH_ZJ));// Veh_Zj";//����ַ�60
        cert.put(IVIMFields.mVeh_Zh, ist.get(MES_VEH_ZH));// Veh_Zh";//����ַ�30
        cert.put(IVIMFields.mVeh_Zs, ist.get(MES_VEH_ZS));// Veh_Zs";//�����ַ�1
        cert.put(IVIMFields.mVeh_Wkc, ist.get(MES_VEH_WKC));// Veh_Wkc";//�������ַ�5
        cert.put(IVIMFields.mVeh_Wkk, ist.get(MES_VEH_WKK));// Veh_Wkk";//�������ַ�4
        cert.put(IVIMFields.mVeh_Wkg, ist.get(MES_VEH_WKG));// Veh_Wkg";//�������ַ�4
        cert.put(IVIMFields.mVeh_Zzl, ist.get(MES_VEH_ZZL));// Veh_Zzl";//�������ַ�8
        cert.put(IVIMFields.mVeh_Zbzl, ist.get(MES_VEH_ZBZL));// Veh_Zbzl";//���������ַ�8
        cert.put(IVIMFields.mVeh_Edzk, ist.get(MES_VEH_EDZK));// Veh_Edzk";//��ؿ��ַ�3
        cert.put(IVIMFields.mVeh_Zgcs, ist.get(MES_VEH_ZGCS));// Veh_Zgcs";//��߳����ַ�5
        cert.put(IVIMFields.mVeh_Clzzrq, ist.get(MES_VEH_CLZZRQ));// Veh_Clzzrq";//�������������ַ�14YYYY��MM��DD��
        cert.put(IVIMFields.mVeh_Bz, ist.get(MES_VEH_BZ));// Veh_Bz";//��ע�ַ�300
        cert.put(IVIMFields.mVeh_Qybz, ist.get(MES_QYBZ));// Veh_Qybz";//��ҵ��׼�ַ�200����xxxx-xxxx��xxxx�����ĸ�ʽ�����м䲿��Ϊ����
        cert.put(IVIMFields.mVeh_Clscdwmc, ist.get(MES_CLSCDWMC));// Veh_Clscdwmc";//����������λ�����ַ�64
        cert.put(IVIMFields.mVeh_Cpscdz, ist.get(MES_CLSCDWDZ));// Veh_Cpscdz";//����������λ��ַ�ַ�70
        cert.put(IVIMFields.mVeh_Qyqtxx, ist.get(MES_ZXZS));// Veh_Qyqtxx";//��ҵ������Ϣ�ַ�400����Ŀ������Ҫ�س����еĵط�ʹ�á�%%����ʾ��
        cert.put(IVIMFields.mVeh_Zxzs, ist.get(MES_ZXZS));// Veh_Zxzs";//ת��������ַ�1ȡֵΪ1��2��3��Ĭ��Ϊ1������ת����ĳ�����д��
        cert.put(IVIMFields.mVeh_Jyw, ist.get(MES_VERCODE));// Veh_Jyw";//У����Ϣ�ַ�255+
        cert.put(IVIMFields.mVeh_Yh, ist.get(MES_VEH_YH));// Veh_Yh";//�ͺ��ַ�30
        cert.put(IVIMFields.mVeh_Cpggh, ist.get(MES_CPGGH));// Veh_Cpggh";//�����
        cert.put(IVIMFields.mVeh_Ggpc, ist.get(MES_GGPC));// Veh_Ggpc";//��������
        cert.put(IVIMFields.mVeh_Ggsxrq, ist.get(MES_GGSXRQ));// Veh_Ggsxrq";//������Ч����
        cert.put(IVIMFields.mVeh_Dywym, ist.get(MES_DYWYM));// Veh_Dywym";//��ӡΨһ��
        cert.put(IVIMFields.mVeh__Wzghzbh, ist.get(MES_VEH_HGZBH));// Veh_Wzghzbh";
        cert.put(IVIMFields.mVeh_Zzbh, ist.get(MES_ZZBH));// Veh_Zzbh";
        cert.put(IVIMFields.mVeh_Cddbj, ist.get(MES_CDDBJ));// Veh_Cddbj";

        // ���豣���
        // cert.put(IVIMFields.mVeh_Stopbits, MES_EMPTY_VALUE);//Veh_Stopbits";
        // cert.put(IVIMFields.mVeh_Databits, MES_EMPTY_VALUE);//Veh_Databits";
        // cert.put(IVIMFields.mVeh_Parity, MES_EMPTY_VALUE);//Veh_Parity";
        // cert.put(IVIMFields.mVeh_Baud, MES_EMPTY_VALUE);//Veh_Baud";
        // cert.put(IVIMFields.mVeh_Connect, MES_EMPTY_VALUE);//Veh_Connect";
        // cert.put(IVIMFields.mVeh_PrintPosTop, MES_EMPTY_VALUE);//Veh_PrintPosTop";
        // cert.put(IVIMFields.mVeh_PrintPosLeft, MES_EMPTY_VALUE);//Veh_PrintPosLeft";
        // cert.put(IVIMFields.mVeh_PrinterName, MES_EMPTY_VALUE);//Veh_PrinterName";

        // MES���޷�ȡֵ��
        cert.put(IVIMFields.mVeh_Zchgzbh, MES_EMPTY_VALUE);// Veh_Zchgzbh";//�����ϸ�֤����ַ�14
        cert.put(IVIMFields.mVeh_Dphgzbh, MES_EMPTY_VALUE);// Veh_Dphgzbh";//���̺ϸ�֤����ַ�15ȫ�ʽ15λ�����̷�ʽ����
        cert.put(IVIMFields.mVeh_Qyid, MES_EMPTY_VALUE);// Veh_Qyid";//��ҵID�ַ�88λ������ҵID
        cert.put(IVIMFields.mVeh_Dpxh, MES_EMPTY_VALUE);// Veh_Dpxh";//�����ͺ��ַ�30����QX��ʽʱʹ��
        cert.put(IVIMFields.mVeh_Dpid, MES_EMPTY_VALUE);// Veh_Dpid";//����ID�ַ�7
        cert.put(IVIMFields.mVeh_Cjh, MES_EMPTY_VALUE);// Veh_Cjh";//���ܺ��ַ�25
        cert.put(IVIMFields.mVeh_Hxnbc, MES_EMPTY_VALUE);// Veh_Hxnbc";//�����ڲ����ַ�5
        cert.put(IVIMFields.mVeh_Hxnbk, MES_EMPTY_VALUE);// Veh_Hxnbk";//�����ڲ����ַ�4
        cert.put(IVIMFields.mVeh_Hxnbg, MES_EMPTY_VALUE);// Veh_Hxnbg";//�����ڲ����ַ�4
        cert.put(IVIMFields.mVeh_Edzzl, MES_EMPTY_VALUE);// Veh_Edzzl";//��������ַ�8
        cert.put(IVIMFields.mVeh_Zzllyxs, MES_EMPTY_VALUE);// Veh_Zzllyxs";//����������ϵ���ַ�30
        cert.put(IVIMFields.mVeh_Zqyzzl, MES_EMPTY_VALUE);// Veh_Zqyzzl";//׼ǣ���������ַ�8
        cert.put(IVIMFields.mVeh_Bgcazzdyxzzl, MES_EMPTY_VALUE);// Veh_Bgcazzdyxzzl";//��ҳ�������������������ַ�8
        cert.put(IVIMFields.mVeh_Jsszcrs, MES_EMPTY_VALUE);// Veh_Jsszcrs";//��ʻ��׼�������ַ�3
        cert.put(IVIMFields.mVeh_Tmxx, MES_EMPTY_VALUE);// Veh_Tmxx";//�ϸ�֤������Ϣ�ַ�1000
        cert.put(IVIMFields.mVeh__Hzdfs, MES_EMPTY_VALUE);// Veh_Hzdfs";
        cert.put(IVIMFields.mVeh__Hzdczfs, MES_EMPTY_VALUE);// Veh_Hzdczfs";
        cert.put(IVIMFields.mVeh__Qzdczfs, MES_EMPTY_VALUE);// Veh_Qzdczfs";
        cert.put(IVIMFields.mVeh__Qzdfs, MES_EMPTY_VALUE);// Veh_Qzdfs";
        cert.put(IVIMFields.mVeh__Pzxlh, MES_EMPTY_VALUE);// Veh_Pzxlh";

        // MES���е�����Ҫ��
        cert.put("MES_"+MES_STATUSFLAG, ist.get(MES_STATUSFLAG));
        cert.put("MES_"+MES_NOTICE_CODE_ID, ist.get(MES_NOTICE_CODE_ID));
        cert.put("MES_"+MES_MOBAN, ist.get(MES_MOBAN));
        cert.put("MES_"+MES_UPLOAD_STATUS, ist.get(MES_UPLOAD_STATUS));
        cert.put("MES_"+MES_STATUSFLAG, ist.get(MES_STATUSFLAG));
        cert.put("MES_"+MES_QDFS, ist.get(MES_QDFS));
        cert.put("MES_"+MES_UNCHANGE, ist.get(MES_UNCHANGE));

        //����ͬ����,����ID�����ж��Ƿ���Ҫͬ��
        cert.put(MES_ID, mes_id);
        
        //VIM�е�״̬
        cert.put(IVIMFields.LIFECYCLE, IVIMFields.LC_UPLOADED);
    
        collection.insert(cert);
    }
}
