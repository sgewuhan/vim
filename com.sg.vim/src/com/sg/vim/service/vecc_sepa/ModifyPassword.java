
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
 *         &lt;element name="key" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="oldpasswd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="newpasswd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "key",
    "oldpasswd",
    "newpasswd"
})
@XmlRootElement(name = "modifyPassword")
public class ModifyPassword {

    protected String key;
    protected String oldpasswd;
    protected String newpasswd;

    /**
     * Gets the value of the key property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKey() {
        return key;
    }

    /**
     * Sets the value of the key property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKey(String value) {
        this.key = value;
    }

    /**
     * Gets the value of the oldpasswd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOldpasswd() {
        return oldpasswd;
    }

    /**
     * Sets the value of the oldpasswd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOldpasswd(String value) {
        this.oldpasswd = value;
    }

    /**
     * Gets the value of the newpasswd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNewpasswd() {
        return newpasswd;
    }

    /**
     * Sets the value of the newpasswd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNewpasswd(String value) {
        this.newpasswd = value;
    }

}
