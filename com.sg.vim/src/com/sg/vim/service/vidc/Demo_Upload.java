package com.sg.vim.service.vidc;

import java.io.IOException;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import com.sg.vim.service.ArrayOfString;
import com.sg.vim.service.NameValuePair;
import com.sg.vim.service.OperateResult;

public class Demo_Upload {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws DatatypeConfigurationException 
	 */
	public static void main(String[] args) throws IOException, DatatypeConfigurationException {

		
	
		CertificateRequestServiceSoap service=new CertificateRequestService().getCertificateRequestServiceSoap();
		service.helloWorld();
		  //本地服务测试
        System.out.println(String.format("本地服务测试结果:%s\r\n", service.helloWorld()));

        //远程服务测试
        System.out.println(String.format("远程服务测试结果:%s\r\n", service.helloWorldRemote()));


        CertificateInfo certificateInfo = new CertificateInfo();
        certificateInfo.setWZHGZBH("...");
        //日期时间型字段使用示例
        GregorianCalendar nowGregorianCalendar =new GregorianCalendar();
        XMLGregorianCalendar xmlDatetime= DatatypeFactory.newInstance().newXMLGregorianCalendar(nowGregorianCalendar);
        certificateInfo.setCLZZRQ(xmlDatetime);          //车辆制造日期
        certificateInfo.setCZRQ(xmlDatetime);             //操作日期
        certificateInfo.setFZRQ(xmlDatetime);             //发证日期
        
        
        
        CertificateInfo certificateInfo2 = new CertificateInfo();
        certificateInfo2.setWZHGZBH("...");
        //日期时间型字段使用示例
        certificateInfo2.setCLZZRQ(xmlDatetime);          //车辆制造日期
        certificateInfo2.setCZRQ(xmlDatetime);             //操作日期
        certificateInfo2.setFZRQ(xmlDatetime);             //发证日期

        

        
        ArrayOfCertificateInfo cis=new ArrayOfCertificateInfo();
        cis.getCertificateInfo().add(certificateInfo);
        cis.getCertificateInfo().add(certificateInfo2);
        
        //上传合格证
        System.out.println(GetResultMessage(service.uploadInsertEnt(cis)));

        //补传合格证
        System.out.println(GetResultMessage(service.uploadOverTimeEnt(cis, "补传原因")));

        //修改合格证
        System.out.println(GetResultMessage(service.uploadUpdateEnt(cis, "修改原因")));

        //撤销合格证
        ArrayOfString wzhgzbhs=new ArrayOfString();
        wzhgzbhs.getString().add("合格证编号1");
        wzhgzbhs.getString().add("合格证编号2");
        wzhgzbhs.getString().add("...");

        System.out.println(GetResultMessage(service.uploadDeleteEnt(wzhgzbhs, "撤销原因")));

        //按合格证编号查询
        System.out.println(GetQueryResultMessage(service.queryCertificateByWZHGZBH("合格证编号")));
        
        //按日期查询
        System.out.println(GetQueryResultMessage(service.queryCertificateByDate(1,"2009-1-1","2009-1-2",10,1)));

        
        System.out.println("操作完成");
        System.in.read();

    }

	
/**
 * 
 * 解析操作结果
 * 
 * @param oResult 
 *         操作结果  {@link OperateResult }
 *         
 * @return 解析结果字符串 {@link String }
 */
    static String GetResultMessage(OperateResult oResult)
    {
        StringBuffer sb = new StringBuffer();

        sb.append(String.format("操作结果:%s\r\n", oResult.getResultCode()));

        for (NameValuePair nvp : oResult.getResultDetail().getNameValuePair())
        {
            sb.append(String.format("%s:%s\r\n", nvp.getName(), nvp.getValue()));
        }
        return sb.toString();
    }

    
    /**
     * 
     * 解析查询结果
     * 
     * @param qResult 
     *         查询结果  {@link QueryResult }
     *         
     * @return 解析结果字符串 {@link String }
     */
    static String GetQueryResultMessage(QueryResult qResult)
    {
        StringBuffer sb = new StringBuffer();

        sb.append(String.format("查询结果数:%s\r\n", qResult.getCount()));

        for (CertificateInfo ci : qResult.getData().getCertificateInfo())
        {
            sb.append(String.format("合格证编号:%s\r\n", ci.getWZHGZBH()));
        }
        return sb.toString();
    }
    
}
