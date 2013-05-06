package com.sg.vim.datamodel.vidcservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for OperateResult complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name=&quot;OperateResult&quot;&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base=&quot;{http://www.w3.org/2001/XMLSchema}anyType&quot;&gt;
 *       &lt;sequence&gt;
 *         &lt;element name=&quot;ResultCode&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}int&quot;/&gt;
 *         &lt;element name=&quot;ResultDetail&quot; type=&quot;{http://www.vidc.info/certificate/operation/}ArrayOfNameValuePair&quot; minOccurs=&quot;0&quot;/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OperateResult", namespace = "http://www.vidc.info/certificate/operation/", propOrder = {
		"resultCode", "resultDetail" })
public class OperateResult {

	@XmlElement(name = "ResultCode")
	protected int resultCode;
	@XmlElement(name = "ResultDetail")
	protected ArrayOfNameValuePair resultDetail;

	/**
	 * Gets the value of the resultCode property.
	 * 
	 */
	public int getResultCode() {
		return resultCode;
	}

	/**
	 * Sets the value of the resultCode property.
	 * 
	 */
	public void setResultCode(int value) {
		this.resultCode = value;
	}

	/**
	 * Gets the value of the resultDetail property.
	 * 
	 * @return possible object is {@link ArrayOfNameValuePair }
	 * 
	 */
	public ArrayOfNameValuePair getResultDetail() {
		return resultDetail;
	}

	/**
	 * Sets the value of the resultDetail property.
	 * 
	 * @param value
	 *            allowed object is {@link ArrayOfNameValuePair }
	 * 
	 */
	public void setResultDetail(ArrayOfNameValuePair value) {
		this.resultDetail = value;
	}

}
