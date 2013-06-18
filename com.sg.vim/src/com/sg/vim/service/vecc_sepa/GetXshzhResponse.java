
package com.sg.vim.service.vecc_sepa;

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
 *         &lt;element name="getXshzhResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "getXshzhResult"
})
@XmlRootElement(name = "getXshzhResponse")
public class GetXshzhResponse {

    protected String getXshzhResult;

    /**
     * Gets the value of the getXshzhResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGetXshzhResult() {
        return getXshzhResult;
    }

    /**
     * Sets the value of the getXshzhResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGetXshzhResult(String value) {
        this.getXshzhResult = value;
    }

}
