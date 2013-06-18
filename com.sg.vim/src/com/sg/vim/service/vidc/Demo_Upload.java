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
		  //���ط������
        System.out.println(String.format("���ط�����Խ��:%s\r\n", service.helloWorld()));

        //Զ�̷������
        System.out.println(String.format("Զ�̷�����Խ��:%s\r\n", service.helloWorldRemote()));


        CertificateInfo certificateInfo = new CertificateInfo();
        certificateInfo.setWZHGZBH("...");
        //����ʱ�����ֶ�ʹ��ʾ��
        GregorianCalendar nowGregorianCalendar =new GregorianCalendar();
        XMLGregorianCalendar xmlDatetime= DatatypeFactory.newInstance().newXMLGregorianCalendar(nowGregorianCalendar);
        certificateInfo.setCLZZRQ(xmlDatetime);          //������������
        certificateInfo.setCZRQ(xmlDatetime);             //��������
        certificateInfo.setFZRQ(xmlDatetime);             //��֤����
        
        
        
        CertificateInfo certificateInfo2 = new CertificateInfo();
        certificateInfo2.setWZHGZBH("...");
        //����ʱ�����ֶ�ʹ��ʾ��
        certificateInfo2.setCLZZRQ(xmlDatetime);          //������������
        certificateInfo2.setCZRQ(xmlDatetime);             //��������
        certificateInfo2.setFZRQ(xmlDatetime);             //��֤����

        

        
        ArrayOfCertificateInfo cis=new ArrayOfCertificateInfo();
        cis.getCertificateInfo().add(certificateInfo);
        cis.getCertificateInfo().add(certificateInfo2);
        
        //�ϴ��ϸ�֤
        System.out.println(GetResultMessage(service.uploadInsertEnt(cis)));

        //�����ϸ�֤
        System.out.println(GetResultMessage(service.uploadOverTimeEnt(cis, "����ԭ��")));

        //�޸ĺϸ�֤
        System.out.println(GetResultMessage(service.uploadUpdateEnt(cis, "�޸�ԭ��")));

        //�����ϸ�֤
        ArrayOfString wzhgzbhs=new ArrayOfString();
        wzhgzbhs.getString().add("�ϸ�֤���1");
        wzhgzbhs.getString().add("�ϸ�֤���2");
        wzhgzbhs.getString().add("...");

        System.out.println(GetResultMessage(service.uploadDeleteEnt(wzhgzbhs, "����ԭ��")));

        //���ϸ�֤��Ų�ѯ
        System.out.println(GetQueryResultMessage(service.queryCertificateByWZHGZBH("�ϸ�֤���")));
        
        //�����ڲ�ѯ
        System.out.println(GetQueryResultMessage(service.queryCertificateByDate(1,"2009-1-1","2009-1-2",10,1)));

        
        System.out.println("�������");
        System.in.read();

    }

	
/**
 * 
 * �����������
 * 
 * @param oResult 
 *         �������  {@link OperateResult }
 *         
 * @return ��������ַ��� {@link String }
 */
    static String GetResultMessage(OperateResult oResult)
    {
        StringBuffer sb = new StringBuffer();

        sb.append(String.format("�������:%s\r\n", oResult.getResultCode()));

        for (NameValuePair nvp : oResult.getResultDetail().getNameValuePair())
        {
            sb.append(String.format("%s:%s\r\n", nvp.getName(), nvp.getValue()));
        }
        return sb.toString();
    }

    
    /**
     * 
     * ������ѯ���
     * 
     * @param qResult 
     *         ��ѯ���  {@link QueryResult }
     *         
     * @return ��������ַ��� {@link String }
     */
    static String GetQueryResultMessage(QueryResult qResult)
    {
        StringBuffer sb = new StringBuffer();

        sb.append(String.format("��ѯ�����:%s\r\n", qResult.getCount()));

        for (CertificateInfo ci : qResult.getData().getCertificateInfo())
        {
            sb.append(String.format("�ϸ�֤���:%s\r\n", ci.getWZHGZBH()));
        }
        return sb.toString();
    }
    
}
