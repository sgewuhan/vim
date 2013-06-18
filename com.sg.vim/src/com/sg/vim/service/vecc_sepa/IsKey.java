
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
 *         &lt;element name="MS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="strKey" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "ms",
    "strKey"
})
@XmlRootElement(name = "IsKey")
public class IsKey {

    @XmlElement(name = "MS")
    protected String ms;
    protected String strKey;

    /**
     * Gets the value of the ms property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMS() {
        return ms;
    }

    /**
     * Sets the value of the ms property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMS(String value) {
        this.ms = value;
    }

    /**
     * Gets the value of the strKey property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStrKey() {
        return strKey;
    }

    /**
     * Sets the value of the strKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStrKey(String value) {
        this.strKey = value;
    }

}
