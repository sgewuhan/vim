package com.sg.vim.datamodel.service.vidc;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.sg.vim.datamodel.service.OperateResult;

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
 *         &lt;element name=&quot;UploadDelete_EntResult&quot; type=&quot;{http://www.vidc.info/certificate/operation/}OperateResult&quot; minOccurs=&quot;0&quot;/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "uploadDeleteEntResult" })
@XmlRootElement(name = "UploadDelete_EntResponse")
public class UploadDeleteEntResponse {

	@XmlElement(name = "UploadDelete_EntResult")
	protected OperateResult uploadDeleteEntResult;

	/**
	 * Gets the value of the uploadDeleteEntResult property.
	 * 
	 * @return possible object is {@link OperateResult }
	 * 
	 */
	public OperateResult getUploadDeleteEntResult() {
		return uploadDeleteEntResult;
	}

	/**
	 * Sets the value of the uploadDeleteEntResult property.
	 * 
	 * @param value
	 *            allowed object is {@link OperateResult }
	 * 
	 */
	public void setUploadDeleteEntResult(OperateResult value) {
		this.uploadDeleteEntResult = value;
	}

}
