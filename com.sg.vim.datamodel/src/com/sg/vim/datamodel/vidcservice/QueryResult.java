package com.sg.vim.datamodel.vidcservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for QueryResult complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name=&quot;QueryResult&quot;&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base=&quot;{http://www.w3.org/2001/XMLSchema}anyType&quot;&gt;
 *       &lt;sequence&gt;
 *         &lt;element name=&quot;Data&quot; type=&quot;{http://service.vidc.info/certificaterequest}ArrayOfCertificateInfo&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;Count&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}int&quot;/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "QueryResult", propOrder = { "data", "count" })
public class QueryResult {

	@XmlElement(name = "Data")
	protected ArrayOfCertificateInfo data;
	@XmlElement(name = "Count")
	protected int count;

	/**
	 * Gets the value of the data property.
	 * 
	 * @return possible object is {@link ArrayOfCertificateInfo }
	 * 
	 */
	public ArrayOfCertificateInfo getData() {
		return data;
	}

	/**
	 * Sets the value of the data property.
	 * 
	 * @param value
	 *            allowed object is {@link ArrayOfCertificateInfo }
	 * 
	 */
	public void setData(ArrayOfCertificateInfo value) {
		this.data = value;
	}

	/**
	 * Gets the value of the count property.
	 * 
	 */
	public int getCount() {
		return count;
	}

	/**
	 * Sets the value of the count property.
	 * 
	 */
	public void setCount(int value) {
		this.count = value;
	}

}
