
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
 *         &lt;element name="getVinCountByXshzhResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "getVinCountByXshzhResult"
})
@XmlRootElement(name = "getVinCountByXshzhResponse")
public class GetVinCountByXshzhResponse {

    protected String getVinCountByXshzhResult;

    /**
     * Gets the value of the getVinCountByXshzhResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGetVinCountByXshzhResult() {
        return getVinCountByXshzhResult;
    }

    /**
     * Sets the value of the getVinCountByXshzhResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGetVinCountByXshzhResult(String value) {
        this.getVinCountByXshzhResult = value;
    }

}
