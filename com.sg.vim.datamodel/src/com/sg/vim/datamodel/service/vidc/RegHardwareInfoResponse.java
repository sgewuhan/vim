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
 *         &lt;element name=&quot;RegHardwareInfoResult&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}int&quot;/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "regHardwareInfoResult" })
@XmlRootElement(name = "RegHardwareInfoResponse")
public class RegHardwareInfoResponse {

	@XmlElement(name = "RegHardwareInfoResult")
	protected int regHardwareInfoResult;

	/**
	 * Gets the value of the regHardwareInfoResult property.
	 * 
	 */
	public int getRegHardwareInfoResult() {
		return regHardwareInfoResult;
	}

	/**
	 * Sets the value of the regHardwareInfoResult property.
	 * 
	 */
	public void setRegHardwareInfoResult(int value) {
		this.regHardwareInfoResult = value;
	}

}
