package com.sg.vim.datamodel.service.vidc;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
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
 *         &lt;element name=&quot;GetHardwareInfoFromServerResult&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "getHardwareInfoFromServerResult" })
@XmlRootElement(name = "GetHardwareInfoFromServerResponse")
public class GetHardwareInfoFromServerResponse {

	@XmlElement(name = "GetHardwareInfoFromServerResult")
	protected String getHardwareInfoFromServerResult;

	/**
	 * Gets the value of the getHardwareInfoFromServerResult property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getGetHardwareInfoFromServerResult() {
		return getHardwareInfoFromServerResult;
	}

	/**
	 * Sets the value of the getHardwareInfoFromServerResult property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setGetHardwareInfoFromServerResult(String value) {
		this.getHardwareInfoFromServerResult = value;
	}

}
