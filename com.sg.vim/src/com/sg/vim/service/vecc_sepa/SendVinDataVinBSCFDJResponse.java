
package com.sg.vim.service.vecc_sepa;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="sendVinData_VinBS_CFDJResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "sendVinDataVinBSCFDJResult"
})
@XmlRootElement(name = "sendVinData_VinBS_CFDJResponse")
public class SendVinDataVinBSCFDJResponse {

    @XmlElement(name = "sendVinData_VinBS_CFDJResult")
    protected String sendVinDataVinBSCFDJResult;

    /**
     * Gets the value of the sendVinDataVinBSCFDJResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSendVinDataVinBSCFDJResult() {
        return sendVinDataVinBSCFDJResult;
    }

    /**
     * Sets the value of the sendVinDataVinBSCFDJResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSendVinDataVinBSCFDJResult(String value) {
        this.sendVinDataVinBSCFDJResult = value;
    }

}
