package com.sg.vim.datamodel.vidcservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>
 * Java class for CertificateWithPrintingSystem complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name=&quot;CertificateWithPrintingSystem&quot;&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base=&quot;{http://www.vidc.info/certificate/operation/}Certificate&quot;&gt;
 *       &lt;sequence&gt;
 *         &lt;element name=&quot;CLZTXX&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;ZCHGZBH&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;CLLX&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;CZRQ&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}dateTime&quot;/&gt;
 *         &lt;element name=&quot;CLSCDWMC&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;QYID&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;YH&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;ZXZS&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;CDDBJ&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;ZZBH&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;DYWYM&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CertificateWithPrintingSystem", namespace = "http://www.vidc.info/certificate/operation/", propOrder = {
		"clztxx", "zchgzbh", "cllx", "czrq", "clscdwmc", "qyid", "yh", "zxzs",
		"cddbj", "zzbh", "dywym" })
public class CertificateWithPrintingSystem extends Certificate {

	@XmlElement(name = "CLZTXX")
	protected String clztxx;
	@XmlElement(name = "ZCHGZBH")
	protected String zchgzbh;
	@XmlElement(name = "CLLX")
	protected String cllx;
	@XmlElement(name = "CZRQ", required = true)
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar czrq;
	@XmlElement(name = "CLSCDWMC")
	protected String clscdwmc;
	@XmlElement(name = "QYID")
	protected String qyid;
	@XmlElement(name = "YH")
	protected String yh;
	@XmlElement(name = "ZXZS")
	protected String zxzs;
	@XmlElement(name = "CDDBJ")
	protected String cddbj;
	@XmlElement(name = "ZZBH")
	protected String zzbh;
	@XmlElement(name = "DYWYM")
	protected String dywym;

	/**
	 * Gets the value of the clztxx property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCLZTXX() {
		return clztxx;
	}

	/**
	 * Sets the value of the clztxx property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCLZTXX(String value) {
		this.clztxx = value;
	}

	/**
	 * Gets the value of the zchgzbh property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getZCHGZBH() {
		return zchgzbh;
	}

	/**
	 * Sets the value of the zchgzbh property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setZCHGZBH(String value) {
		this.zchgzbh = value;
	}

	/**
	 * Gets the value of the cllx property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCLLX() {
		return cllx;
	}

	/**
	 * Sets the value of the cllx property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCLLX(String value) {
		this.cllx = value;
	}

	/**
	 * Gets the value of the czrq property.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getCZRQ() {
		return czrq;
	}

	/**
	 * Sets the value of the czrq property.
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setCZRQ(XMLGregorianCalendar value) {
		this.czrq = value;
	}

	/**
	 * Gets the value of the clscdwmc property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCLSCDWMC() {
		return clscdwmc;
	}

	/**
	 * Sets the value of the clscdwmc property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCLSCDWMC(String value) {
		this.clscdwmc = value;
	}

	/**
	 * Gets the value of the qyid property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getQYID() {
		return qyid;
	}

	/**
	 * Sets the value of the qyid property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setQYID(String value) {
		this.qyid = value;
	}

	/**
	 * Gets the value of the yh property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getYH() {
		return yh;
	}

	/**
	 * Sets the value of the yh property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setYH(String value) {
		this.yh = value;
	}

	/**
	 * Gets the value of the zxzs property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getZXZS() {
		return zxzs;
	}

	/**
	 * Sets the value of the zxzs property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setZXZS(String value) {
		this.zxzs = value;
	}

	/**
	 * Gets the value of the cddbj property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCDDBJ() {
		return cddbj;
	}

	/**
	 * Sets the value of the cddbj property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCDDBJ(String value) {
		this.cddbj = value;
	}

	/**
	 * Gets the value of the zzbh property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getZZBH() {
		return zzbh;
	}

	/**
	 * Sets the value of the zzbh property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setZZBH(String value) {
		this.zzbh = value;
	}

	/**
	 * Gets the value of the dywym property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getDYWYM() {
		return dywym;
	}

	/**
	 * Sets the value of the dywym property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setDYWYM(String value) {
		this.dywym = value;
	}

}
