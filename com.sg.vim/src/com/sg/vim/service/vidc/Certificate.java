package com.sg.vim.service.vidc;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>
 * Java class for Certificate complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name=&quot;Certificate&quot;&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base=&quot;{http://www.vidc.info/certificate/operation/}DataObjectBase&quot;&gt;
 *       &lt;sequence&gt;
 *         &lt;element name=&quot;DPHGZBH&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;FZRQ&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}dateTime&quot;/&gt;
 *         &lt;element name=&quot;CLZZQYMC&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;CLPP&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;CLMC&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;CLXH&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;CSYS&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;DPXH&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;DPID&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;CLSBDH&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;CJH&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;FDJH&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;FDJXH&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;RLZL&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;PFBZ&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;PL&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;GL&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;ZXXS&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;QLJ&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;HLJ&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;LTS&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;LTGG&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;GBTHPS&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;ZJ&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;ZH&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;ZS&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;WKC&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;WKK&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;WKG&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;HXNBC&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;HXNBK&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;HXNBG&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;ZZL&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;EDZZL&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;ZBZL&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;ZZLLYXS&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;ZQYZZL&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;EDZK&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;BGCAZZDYXZZL&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;JSSZCRS&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;QZDFS&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;HZDFS&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;QZDCZFS&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;HZDCZFS&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;ZGCS&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;CLZZRQ&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}dateTime&quot;/&gt;
 *         &lt;element name=&quot;BZ&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;QYBZ&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;CPSCDZ&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;QYQTXX&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;PZXLH&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;CPH&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;PC&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;GGSXRQ&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}dateTime&quot;/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Certificate", namespace = "http://www.vidc.info/certificate/operation/", propOrder = {
		"dphgzbh", "fzrq", "clzzqymc", "clpp", "clmc", "clxh", "csys", "dpxh",
		"dpid", "clsbdh", "cjh", "fdjh", "fdjxh", "rlzl", "pfbz", "pl", "gl",
		"zxxs", "qlj", "hlj", "lts", "ltgg", "gbthps", "zj", "zh", "zs", "wkc",
		"wkk", "wkg", "hxnbc", "hxnbk", "hxnbg", "zzl", "edzzl", "zbzl",
		"zzllyxs", "zqyzzl", "edzk", "bgcazzdyxzzl", "jsszcrs", "qzdfs",
		"hzdfs", "qzdczfs", "hzdczfs", "zgcs", "clzzrq", "bz", "qybz",
		"cpscdz", "qyqtxx", "pzxlh","cph", "pc", "ggsxrq" })
public class Certificate extends DataObjectBase {

	@XmlElement(name = "DPHGZBH")
	protected String dphgzbh;
	@XmlElement(name = "FZRQ", required = true)
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar fzrq;
	@XmlElement(name = "CLZZQYMC")
	protected String clzzqymc;
	@XmlElement(name = "CLPP")
	protected String clpp;
	@XmlElement(name = "CLMC")
	protected String clmc;
	@XmlElement(name = "CLXH")
	protected String clxh;
	@XmlElement(name = "CSYS")
	protected String csys;
	@XmlElement(name = "DPXH")
	protected String dpxh;
	@XmlElement(name = "DPID")
	protected String dpid;
	@XmlElement(name = "CLSBDH")
	protected String clsbdh;
	@XmlElement(name = "CJH")
	protected String cjh;
	@XmlElement(name = "FDJH")
	protected String fdjh;
	@XmlElement(name = "FDJXH")
	protected String fdjxh;
	@XmlElement(name = "RLZL")
	protected String rlzl;
	@XmlElement(name = "PFBZ")
	protected String pfbz;
	@XmlElement(name = "PL")
	protected String pl;
	@XmlElement(name = "GL")
	protected String gl;
	@XmlElement(name = "ZXXS")
	protected String zxxs;
	@XmlElement(name = "QLJ")
	protected String qlj;
	@XmlElement(name = "HLJ")
	protected String hlj;
	@XmlElement(name = "LTS")
	protected String lts;
	@XmlElement(name = "LTGG")
	protected String ltgg;
	@XmlElement(name = "GBTHPS")
	protected String gbthps;
	@XmlElement(name = "ZJ")
	protected String zj;
	@XmlElement(name = "ZH")
	protected String zh;
	@XmlElement(name = "ZS")
	protected String zs;
	@XmlElement(name = "WKC")
	protected String wkc;
	@XmlElement(name = "WKK")
	protected String wkk;
	@XmlElement(name = "WKG")
	protected String wkg;
	@XmlElement(name = "HXNBC")
	protected String hxnbc;
	@XmlElement(name = "HXNBK")
	protected String hxnbk;
	@XmlElement(name = "HXNBG")
	protected String hxnbg;
	@XmlElement(name = "ZZL")
	protected String zzl;
	@XmlElement(name = "EDZZL")
	protected String edzzl;
	@XmlElement(name = "ZBZL")
	protected String zbzl;
	@XmlElement(name = "ZZLLYXS")
	protected String zzllyxs;
	@XmlElement(name = "ZQYZZL")
	protected String zqyzzl;
	@XmlElement(name = "EDZK")
	protected String edzk;
	@XmlElement(name = "BGCAZZDYXZZL")
	protected String bgcazzdyxzzl;
	@XmlElement(name = "JSSZCRS")
	protected String jsszcrs;
	@XmlElement(name = "QZDFS")
	protected String qzdfs;
	@XmlElement(name = "HZDFS")
	protected String hzdfs;
	@XmlElement(name = "QZDCZFS")
	protected String qzdczfs;
	@XmlElement(name = "HZDCZFS")
	protected String hzdczfs;
	@XmlElement(name = "ZGCS")
	protected String zgcs;
	@XmlElement(name = "CLZZRQ", required = true)
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar clzzrq;
	@XmlElement(name = "BZ")
	protected String bz;
	@XmlElement(name = "QYBZ")
	protected String qybz;
	@XmlElement(name = "CPSCDZ")
	protected String cpscdz;
    @XmlElement(name = "QYQTXX")
    protected String qyqtxx;
    @XmlElement(name = "PZXLH")
    protected String pzxlh;
	@XmlElement(name = "CPH")
	protected String cph;
	@XmlElement(name = "PC")
	protected String pc;
	@XmlElement(name = "GGSXRQ", required = true)
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar ggsxrq;

	/**
	 * Gets the value of the dphgzbh property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getDPHGZBH() {
		return dphgzbh;
	}

	/**
	 * Sets the value of the dphgzbh property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setDPHGZBH(String value) {
		this.dphgzbh = value;
	}

	/**
	 * Gets the value of the fzrq property.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getFZRQ() {
		return fzrq;
	}

	/**
	 * Sets the value of the fzrq property.
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setFZRQ(XMLGregorianCalendar value) {
		this.fzrq = value;
	}

	/**
	 * Gets the value of the clzzqymc property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCLZZQYMC() {
		return clzzqymc;
	}

	/**
	 * Sets the value of the clzzqymc property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCLZZQYMC(String value) {
		this.clzzqymc = value;
	}

	/**
	 * Gets the value of the clpp property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCLPP() {
		return clpp;
	}

	/**
	 * Sets the value of the clpp property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCLPP(String value) {
		this.clpp = value;
	}

	/**
	 * Gets the value of the clmc property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCLMC() {
		return clmc;
	}

	/**
	 * Sets the value of the clmc property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCLMC(String value) {
		this.clmc = value;
	}

	/**
	 * Gets the value of the clxh property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCLXH() {
		return clxh;
	}

	/**
	 * Sets the value of the clxh property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCLXH(String value) {
		this.clxh = value;
	}

	/**
	 * Gets the value of the csys property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCSYS() {
		return csys;
	}

	/**
	 * Sets the value of the csys property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCSYS(String value) {
		this.csys = value;
	}

	/**
	 * Gets the value of the dpxh property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getDPXH() {
		return dpxh;
	}

	/**
	 * Sets the value of the dpxh property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setDPXH(String value) {
		this.dpxh = value;
	}

	/**
	 * Gets the value of the dpid property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getDPID() {
		return dpid;
	}

	/**
	 * Sets the value of the dpid property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setDPID(String value) {
		this.dpid = value;
	}

	/**
	 * Gets the value of the clsbdh property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCLSBDH() {
		return clsbdh;
	}

	/**
	 * Sets the value of the clsbdh property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCLSBDH(String value) {
		this.clsbdh = value;
	}

	/**
	 * Gets the value of the cjh property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCJH() {
		return cjh;
	}

	/**
	 * Sets the value of the cjh property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCJH(String value) {
		this.cjh = value;
	}

	/**
	 * Gets the value of the fdjh property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getFDJH() {
		return fdjh;
	}

	/**
	 * Sets the value of the fdjh property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setFDJH(String value) {
		this.fdjh = value;
	}

	/**
	 * Gets the value of the fdjxh property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getFDJXH() {
		return fdjxh;
	}

	/**
	 * Sets the value of the fdjxh property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setFDJXH(String value) {
		this.fdjxh = value;
	}

	/**
	 * Gets the value of the rlzl property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getRLZL() {
		return rlzl;
	}

	/**
	 * Sets the value of the rlzl property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setRLZL(String value) {
		this.rlzl = value;
	}

	/**
	 * Gets the value of the pfbz property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getPFBZ() {
		return pfbz;
	}

	/**
	 * Sets the value of the pfbz property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setPFBZ(String value) {
		this.pfbz = value;
	}

	/**
	 * Gets the value of the pl property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getPL() {
		return pl;
	}

	/**
	 * Sets the value of the pl property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setPL(String value) {
		this.pl = value;
	}

	/**
	 * Gets the value of the gl property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getGL() {
		return gl;
	}

	/**
	 * Sets the value of the gl property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setGL(String value) {
		this.gl = value;
	}

	/**
	 * Gets the value of the zxxs property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getZXXS() {
		return zxxs;
	}

	/**
	 * Sets the value of the zxxs property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setZXXS(String value) {
		this.zxxs = value;
	}

	/**
	 * Gets the value of the qlj property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getQLJ() {
		return qlj;
	}

	/**
	 * Sets the value of the qlj property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setQLJ(String value) {
		this.qlj = value;
	}

	/**
	 * Gets the value of the hlj property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getHLJ() {
		return hlj;
	}

	/**
	 * Sets the value of the hlj property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setHLJ(String value) {
		this.hlj = value;
	}

	/**
	 * Gets the value of the lts property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getLTS() {
		return lts;
	}

	/**
	 * Sets the value of the lts property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setLTS(String value) {
		this.lts = value;
	}

	/**
	 * Gets the value of the ltgg property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getLTGG() {
		return ltgg;
	}

	/**
	 * Sets the value of the ltgg property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setLTGG(String value) {
		this.ltgg = value;
	}

	/**
	 * Gets the value of the gbthps property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getGBTHPS() {
		return gbthps;
	}

	/**
	 * Sets the value of the gbthps property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setGBTHPS(String value) {
		this.gbthps = value;
	}

	/**
	 * Gets the value of the zj property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getZJ() {
		return zj;
	}

	/**
	 * Sets the value of the zj property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setZJ(String value) {
		this.zj = value;
	}

	/**
	 * Gets the value of the zh property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getZH() {
		return zh;
	}

	/**
	 * Sets the value of the zh property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setZH(String value) {
		this.zh = value;
	}

	/**
	 * Gets the value of the zs property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getZS() {
		return zs;
	}

	/**
	 * Sets the value of the zs property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setZS(String value) {
		this.zs = value;
	}

	/**
	 * Gets the value of the wkc property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getWKC() {
		return wkc;
	}

	/**
	 * Sets the value of the wkc property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setWKC(String value) {
		this.wkc = value;
	}

	/**
	 * Gets the value of the wkk property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getWKK() {
		return wkk;
	}

	/**
	 * Sets the value of the wkk property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setWKK(String value) {
		this.wkk = value;
	}

	/**
	 * Gets the value of the wkg property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getWKG() {
		return wkg;
	}

	/**
	 * Sets the value of the wkg property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setWKG(String value) {
		this.wkg = value;
	}

	/**
	 * Gets the value of the hxnbc property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getHXNBC() {
		return hxnbc;
	}

	/**
	 * Sets the value of the hxnbc property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setHXNBC(String value) {
		this.hxnbc = value;
	}

	/**
	 * Gets the value of the hxnbk property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getHXNBK() {
		return hxnbk;
	}

	/**
	 * Sets the value of the hxnbk property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setHXNBK(String value) {
		this.hxnbk = value;
	}

	/**
	 * Gets the value of the hxnbg property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getHXNBG() {
		return hxnbg;
	}

	/**
	 * Sets the value of the hxnbg property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setHXNBG(String value) {
		this.hxnbg = value;
	}

	/**
	 * Gets the value of the zzl property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getZZL() {
		return zzl;
	}

	/**
	 * Sets the value of the zzl property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setZZL(String value) {
		this.zzl = value;
	}

	/**
	 * Gets the value of the edzzl property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getEDZZL() {
		return edzzl;
	}

	/**
	 * Sets the value of the edzzl property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setEDZZL(String value) {
		this.edzzl = value;
	}

	/**
	 * Gets the value of the zbzl property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getZBZL() {
		return zbzl;
	}

	/**
	 * Sets the value of the zbzl property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setZBZL(String value) {
		this.zbzl = value;
	}

	/**
	 * Gets the value of the zzllyxs property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getZZLLYXS() {
		return zzllyxs;
	}

	/**
	 * Sets the value of the zzllyxs property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setZZLLYXS(String value) {
		this.zzllyxs = value;
	}

	/**
	 * Gets the value of the zqyzzl property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getZQYZZL() {
		return zqyzzl;
	}

	/**
	 * Sets the value of the zqyzzl property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setZQYZZL(String value) {
		this.zqyzzl = value;
	}

	/**
	 * Gets the value of the edzk property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getEDZK() {
		return edzk;
	}

	/**
	 * Sets the value of the edzk property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setEDZK(String value) {
		this.edzk = value;
	}

	/**
	 * Gets the value of the bgcazzdyxzzl property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getBGCAZZDYXZZL() {
		return bgcazzdyxzzl;
	}

	/**
	 * Sets the value of the bgcazzdyxzzl property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setBGCAZZDYXZZL(String value) {
		this.bgcazzdyxzzl = value;
	}

	/**
	 * Gets the value of the jsszcrs property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getJSSZCRS() {
		return jsszcrs;
	}

	/**
	 * Sets the value of the jsszcrs property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setJSSZCRS(String value) {
		this.jsszcrs = value;
	}

	/**
	 * Gets the value of the qzdfs property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getQZDFS() {
		return qzdfs;
	}

	/**
	 * Sets the value of the qzdfs property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setQZDFS(String value) {
		this.qzdfs = value;
	}

	/**
	 * Gets the value of the hzdfs property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getHZDFS() {
		return hzdfs;
	}

	/**
	 * Sets the value of the hzdfs property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setHZDFS(String value) {
		this.hzdfs = value;
	}

	/**
	 * Gets the value of the qzdczfs property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getQZDCZFS() {
		return qzdczfs;
	}

	/**
	 * Sets the value of the qzdczfs property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setQZDCZFS(String value) {
		this.qzdczfs = value;
	}

	/**
	 * Gets the value of the hzdczfs property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getHZDCZFS() {
		return hzdczfs;
	}

	/**
	 * Sets the value of the hzdczfs property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setHZDCZFS(String value) {
		this.hzdczfs = value;
	}

	/**
	 * Gets the value of the zgcs property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getZGCS() {
		return zgcs;
	}

	/**
	 * Sets the value of the zgcs property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setZGCS(String value) {
		this.zgcs = value;
	}

	/**
	 * Gets the value of the clzzrq property.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getCLZZRQ() {
		return clzzrq;
	}

	/**
	 * Sets the value of the clzzrq property.
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setCLZZRQ(XMLGregorianCalendar value) {
		this.clzzrq = value;
	}

	/**
	 * Gets the value of the bz property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getBZ() {
		return bz;
	}

	/**
	 * Sets the value of the bz property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setBZ(String value) {
		this.bz = value;
	}

	/**
	 * Gets the value of the qybz property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getQYBZ() {
		return qybz;
	}

	/**
	 * Sets the value of the qybz property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setQYBZ(String value) {
		this.qybz = value;
	}

	/**
	 * Gets the value of the cpscdz property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCPSCDZ() {
		return cpscdz;
	}

	/**
	 * Sets the value of the cpscdz property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCPSCDZ(String value) {
		this.cpscdz = value;
	}

	/**
	 * Gets the value of the qyqtxx property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getQYQTXX() {
		return qyqtxx;
	}

	/**
	 * Sets the value of the qyqtxx property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setQYQTXX(String value) {
		this.qyqtxx = value;
	}

	/**
	 * Gets the value of the cph property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCPH() {
		return cph;
	}

	/**
	 * Sets the value of the cph property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCPH(String value) {
		this.cph = value;
	}

	/**
	 * Gets the value of the pc property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getPC() {
		return pc;
	}

	/**
	 * Sets the value of the pc property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setPC(String value) {
		this.pc = value;
	}

	/**
	 * Gets the value of the ggsxrq property.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getGGSXRQ() {
		return ggsxrq;
	}

	/**
	 * Sets the value of the ggsxrq property.
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setGGSXRQ(XMLGregorianCalendar value) {
		this.ggsxrq = value;
	}

    public String getPZXLH() {
        return pzxlh;
    }

    public void setPZXLH(String value) {
        this.pzxlh = value;
    }

}
