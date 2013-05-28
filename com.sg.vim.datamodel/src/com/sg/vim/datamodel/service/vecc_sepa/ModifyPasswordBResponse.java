
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
 *         &lt;element name="modifyPasswordBResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "modifyPasswordBResult"
})
@XmlRootElement(name = "modifyPasswordBResponse")
public class ModifyPasswordBResponse {

    protected String modifyPasswordBResult;

    /**
     * Gets the value of the modifyPasswordBResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getModifyPasswordBResult() {
        return modifyPasswordBResult;
    }

    /**
     * Sets the value of the modifyPasswordBResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setModifyPasswordBResult(String value) {
        this.modifyPasswordBResult = value;
    }

}
