
package com.sg.vim.datamodel.flservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for QyInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="QyInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="QyId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Qymc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Qydz" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Qyyb" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Is_Import" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "QyInfo", propOrder = {
    "qyId",
    "qymc",
    "qydz",
    "qyyb",
    "isImport"
})
public class QyInfo {

    @XmlElement(name = "QyId")
    protected String qyId;
    @XmlElement(name = "Qymc")
    protected String qymc;
    @XmlElement(name = "Qydz")
    protected String qydz;
    @XmlElement(name = "Qyyb")
    protected String qyyb;
    @XmlElement(name = "Is_Import")
    protected String isImport;

    /**
     * Gets the value of the qyId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQyId() {
        return qyId;
    }

    /**
     * Sets the value of the qyId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQyId(String value) {
        this.qyId = value;
    }

    /**
     * Gets the value of the qymc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQymc() {
        return qymc;
    }

    /**
     * Sets the value of the qymc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQymc(String value) {
        this.qymc = value;
    }

    /**
     * Gets the value of the qydz property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQydz() {
        return qydz;
    }

    /**
     * Sets the value of the qydz property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQydz(String value) {
        this.qydz = value;
    }

    /**
     * Gets the value of the qyyb property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQyyb() {
        return qyyb;
    }

    /**
     * Sets the value of the qyyb property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQyyb(String value) {
        this.qyyb = value;
    }

    /**
     * Gets the value of the isImport property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsImport() {
        return isImport;
    }

    /**
     * Sets the value of the isImport property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsImport(String value) {
        this.isImport = value;
    }

}
