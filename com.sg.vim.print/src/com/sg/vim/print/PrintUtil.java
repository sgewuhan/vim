package com.sg.vim.print;

import org.eclipse.swt.browser.Browser;

import com.mongodb.BasicDBObject;

public class PrintUtil {

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
    public static final String mVeh_Cpggh = "Veh_Cpggh";//�����

    public static final String[] paraSeq = new String[] { mVeh_Clztxx, mVeh_Zchgzbh, mVeh_Dphgzbh,
            mVeh_Fzrq, mVeh_Clzzqymc, mVeh_Qyid, mVeh_Clfl, mVeh_Clmc, mVeh_Clpp, mVeh_Clxh,
            mVeh_Csys, mVeh_Dpxh, mVeh_Dpid, mVeh_Clsbdh, mVeh_Cjh, mVeh_Fdjh, mVeh_Fdjxh,
            mVeh_Rlzl, mVeh_Pfbz, mVeh_Pl, mVeh_Gl, mVeh_Zxxs, mVeh_Qlj, mVeh_Hlj, mVeh_Lts,
            mVeh_Ltgg, mVeh_Gbthps, mVeh_Zj, mVeh_Zh, mVeh_Zs, mVeh_Wkc, mVeh_Wkk, mVeh_Wkg,
            mVeh_Hxnbc, mVeh_Hxnbk, mVeh_Hxnbg, mVeh_Zzl, mVeh_Edzzl, mVeh_Zbzl, mVeh_Zzllyxs,
            mVeh_Zqyzzl, mVeh_Edzk, mVeh_Bgcazzdyxzzl, mVeh_Jsszcrs, mVeh_Zgcs, mVeh_Clzzrq,
            mVeh_Bz, mVeh_Qybz, mVeh_Clscdwmc, mVeh_Cpscdz, mVeh_Qyqtxx, mVeh_Zxzs, mVeh_Tmxx,
            mVeh_Jyw, mVeh_Yh, mVeh_Cddbj,mVeh_Cpggh };

    public static void print(Browser browser, BasicDBObject dbo) {
        StringBuilder sb = new StringBuilder();
        sb.append("printVert(");
        
        for (int i = 0; i < paraSeq.length; i++) {
            Object value = dbo.get(paraSeq[i]);
            sb.append("\"");
            value = value==null?"":value;
            sb.append(value);
            sb.append("\"");
            if(i<paraSeq.length-1){
                sb.append(",");
            }
        }
        
        sb.append(");");
        System.out.println(sb.toString());
        browser.execute(sb.toString());
    }

    public static void test(Browser browser) {
        browser.execute("showbar()");
    }

}
