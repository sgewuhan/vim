package com.sg.vim.service.vidc;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for DataObjectBase complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name=&quot;DataObjectBase&quot;&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base=&quot;{http://www.w3.org/2001/XMLSchema}anyType&quot;&gt;
 *       &lt;sequence&gt;
 *         &lt;element name=&quot;WZHGZBH&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DataObjectBase", namespace = "http://www.vidc.info/certificate/operation/", propOrder = { "wzhgzbh" })
public class DataObjectBase {

	@XmlElement(name = "WZHGZBH")
	protected String wzhgzbh;

	/**
	 * Gets the value of the wzhgzbh property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getWZHGZBH() {
		return wzhgzbh;
	}

	/**
	 * Sets the value of the wzhgzbh property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setWZHGZBH(String value) {
		this.wzhgzbh = value;
	}

}
