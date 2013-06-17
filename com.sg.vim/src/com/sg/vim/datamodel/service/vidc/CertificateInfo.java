package com.sg.vim.datamodel.service.vidc;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for CertificateInfo complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name=&quot;CertificateInfo&quot;&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base=&quot;{http://www.vidc.info/certificate/operation/}CertificateWithSystemInfo&quot;&gt;
 *       &lt;sequence&gt;
 *         &lt;element name=&quot;VEHICLE_STATUS&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CertificateInfo", namespace = "http://www.vidc.info/certificate/operation/", propOrder = { "vehiclestatus" })
public class CertificateInfo extends CertificateWithSystemInfo {

	@XmlElement(name = "VEHICLE_STATUS")
	protected String vehiclestatus;

	/**
	 * Gets the value of the vehiclestatus property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getVEHICLESTATUS() {
		return vehiclestatus;
	}

	/**
	 * Sets the value of the vehiclestatus property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setVEHICLESTATUS(String value) {
		this.vehiclestatus = value;
	}

}
