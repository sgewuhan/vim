package com.sg.vim.print;

import org.eclipse.swt.browser.Browser;

import com.mongodb.BasicDBObject;

public class PrintUtil {

    public static final String mVeh_Clztxx = "Veh_Clztxx";// 车辆状态信息 字符 2 取值为QX和DP
    public static final String mVeh_Zchgzbh = "Veh_Zchgzbh";// 整车合格证编号 字符 14
                                                            // 4位企业代码+10位顺序号成功调用打印方法后可以通过该属性获得15位的整车合格证编号
    public static final String mVeh_Dphgzbh = "Veh_Dphgzbh";// 底盘合格证编号 字符 15 全项方式15位；底盘方式不填
    public static final String mVeh_Fzrq = "Veh_Fzrq";// 发证日期 字符 14 YYYY年MM月DD日
    public static final String mVeh_Clzzqymc = "Veh_Clzzqymc";// 车辆制造企业名称 字符 64
    public static final String mVeh_Qyid = "Veh_Qyid";// 企业ID 字符 8 8位公告企业ID
    public static final String mVeh_Clfl = "Veh_Clfl";// 车辆分类 字符 26 代替原属性Veh_cllx。如：货车
    public static final String mVeh_Clmc = "Veh_Clmc";// 车辆名称 字符 54 如：轿车
    public static final String mVeh_Clpp = "Veh_Clpp";// 车辆品牌 字符 30
    public static final String mVeh_Clxh = "Veh_Clxh";// 车辆型号 字符 30
    public static final String mVeh_Csys = "Veh_Csys";// 车身颜色 字符 70 多种颜色之间用“/”分隔
    public static final String mVeh_Dpxh = "Veh_Dpxh";// 底盘型号 字符 30 对于QX方式时使用
    public static final String mVeh_Dpid = "Veh_Dpid";// 底盘ID 字符 7
    public static final String mVeh_Clsbdh = "Veh_Clsbdh";// 车辆识别代号 字符 17
    public static final String mVeh_Cjh = "Veh_Cjh";// 车架号 字符 25
    public static final String mVeh_Fdjh = "Veh_Fdjh";// 发动机号 字符 30
    public static final String mVeh_Fdjxh = "Veh_Fdjxh";// 发动机型号 字符 20
    public static final String mVeh_Rlzl = "Veh_Rlzl";// 燃料种类 字符 30 多种燃料之间用“/”分隔
    public static final String mVeh_Pfbz = "Veh_Pfbz";// 排放标准 字符 60
    public static final String mVeh_Pl = "Veh_Pl";// 排量 字符 5
    public static final String mVeh_Gl = "Veh_Gl";// 功率 字符 7 多种功率之间用“/”分隔
    public static final String mVeh_Zxxs = "Veh_Zxxs";// 转向形式 字符 6 如：方向盘
    public static final String mVeh_Qlj = "Veh_Qlj";// 前轮距 字符 4
    public static final String mVeh_Hlj = "Veh_Hlj";// 后轮距 字符 54
    public static final String mVeh_Lts = "Veh_Lts";// 轮胎数 字符 2
    public static final String mVeh_Ltgg = "Veh_Ltgg";// 轮胎规格 字符 20
    public static final String mVeh_Gbthps = "Veh_Gbthps";// 钢板弹簧片数 字符 30
    public static final String mVeh_Zj = "Veh_Zj";// 轴距 字符 60
    public static final String mVeh_Zh = "Veh_Zh";// 轴荷 字符 30
    public static final String mVeh_Zs = "Veh_Zs";// 轴数 字符 1
    public static final String mVeh_Wkc = "Veh_Wkc";// 外廓长 字符 5
    public static final String mVeh_Wkk = "Veh_Wkk";// 外廓宽 字符 4
    public static final String mVeh_Wkg = "Veh_Wkg";// 外廓高 字符 4
    public static final String mVeh_Hxnbc = "Veh_Hxnbc";// 货厢内部长 字符 5
    public static final String mVeh_Hxnbk = "Veh_Hxnbk";// 货厢内部宽 字符 4
    public static final String mVeh_Hxnbg = "Veh_Hxnbg";// 货厢内部高 字符 4
    public static final String mVeh_Zzl = "Veh_Zzl";// 总质量 字符 8
    public static final String mVeh_Edzzl = "Veh_Edzzl";// 额定载质量 字符 8
    public static final String mVeh_Zbzl = "Veh_Zbzl";// 整备质量 字符 8
    public static final String mVeh_Zzllyxs = "Veh_Zzllyxs";// 载质量利用系数 字符 30
    public static final String mVeh_Zqyzzl = "Veh_Zqyzzl";// 准牵引总质量 字符 8
    public static final String mVeh_Edzk = "Veh_Edzk";// 额定载客 字符 3
    public static final String mVeh_Bgcazzdyxzzl = "Veh_Bgcazzdyxzzl";// 半挂车鞍座最大允许总质量 字符 8
    public static final String mVeh_Jsszcrs = "Veh_Jsszcrs";// 驾驶室准乘人数 字符 3
    public static final String mVeh_Zgcs = "Veh_Zgcs";// 最高车速 字符 5
    public static final String mVeh_Clzzrq = "Veh_Clzzrq";// 车辆制造日期 字符 14 YYYY年MM月DD日
    public static final String mVeh_Bz = "Veh_Bz";// 备注 字符 300
    public static final String mVeh_Qybz = "Veh_Qybz";// 企业标准 字符 200 按“xxxx-xxxx《xxxx》”的格式，其中间部分为数字
    public static final String mVeh_Clscdwmc = "Veh_Clscdwmc";// 车辆生产单位名称 字符 64
    public static final String mVeh_Cpscdz = "Veh_Cpscdz";// 车辆生产单位地址 字符 70
    public static final String mVeh_Qyqtxx = "Veh_Qyqtxx";// 企业其它信息 字符 400 该项目内容需要回车换行的地方使用“%%”表示。
    public static final String mVeh_Zxzs = "Veh_Zxzs";// 转向轴个数 字符 1 取值为1、2、3，默认为1。供多转向轴的车辆填写。
    public static final String mVeh_Tmxx = "Veh_Tmxx";// 合格证条码信息 字符 1000
                                                      // 调用ViewBarcodeInfo并读取合格证条码后，通过该属性可获得条码信息，具体项目格式见附件一。
    public static final String mVeh_Jyw = "Veh_Jyw";// 校验信息 字符 255+
                                                    // 调用PrtParaTbl成功后，通过该属性可获得校验信息，供合格证上传用。其长度随合格证信息量发生变化,建议采用备注型等大容量数据类型存储。
    public static final String mVeh_Yh = "Veh_Yh";// 油耗 字符 30
    public static final String mVeh_Cddbj = "Veh_Cddbj";// 纯电动标记 字符 1
    public static final String mVeh_Cpggh = "Veh_Cpggh";//公告号

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
