package com.sg.vim.datamodel.vidcservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for anonymous complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base=&quot;{http://www.w3.org/2001/XMLSchema}anyType&quot;&gt;
 *       &lt;sequence&gt;
 *         &lt;element name=&quot;rqlx&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}int&quot;/&gt;
 *         &lt;element name=&quot;beginTime&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;endTime&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;pageSize&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}int&quot;/&gt;
 *         &lt;element name=&quot;pageSite&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}int&quot;/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "rqlx", "beginTime", "endTime", "pageSize",
		"pageSite" })
@XmlRootElement(name = "QueryCertificateByDate")
public class QueryCertificateByDate {

	protected int rqlx;
	protected String beginTime;
	protected String endTime;
	protected int pageSize;
	protected int pageSite;

	/**
	 * Gets the value of the rqlx property.
	 * 
	 */
	public int getRqlx() {
		return rqlx;
	}

	/**
	 * Sets the value of the rqlx property.
	 * 
	 */
	public void setRqlx(int value) {
		this.rqlx = value;
	}

	/**
	 * Gets the value of the beginTime property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getBeginTime() {
		return beginTime;
	}

	/**
	 * Sets the value of the beginTime property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setBeginTime(String value) {
		this.beginTime = value;
	}

	/**
	 * Gets the value of the endTime property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getEndTime() {
		return endTime;
	}

	/**
	 * Sets the value of the endTime property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setEndTime(String value) {
		this.endTime = value;
	}

	/**
	 * Gets the value of the pageSize property.
	 * 
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * Sets the value of the pageSize property.
	 * 
	 */
	public void setPageSize(int value) {
		this.pageSize = value;
	}

	/**
	 * Gets the value of the pageSite property.
	 * 
	 */
	public int getPageSite() {
		return pageSite;
	}

	/**
	 * Sets the value of the pageSite property.
	 * 
	 */
	public void setPageSite(int value) {
		this.pageSite = value;
	}

}
