
package com.sg.vim.datamodel.service.vecc_sepa;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element name="sendVinDataCFDJResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "sendVinDataCFDJResult"
})
@XmlRootElement(name = "sendVinDataCFDJResponse")
public class SendVinDataCFDJResponse {

    protected String sendVinDataCFDJResult;

    /**
     * Gets the value of the sendVinDataCFDJResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSendVinDataCFDJResult() {
        return sendVinDataCFDJResult;
    }

    /**
     * Sets the value of the sendVinDataCFDJResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSendVinDataCFDJResult(String value) {
        this.sendVinDataCFDJResult = value;
    }

}
