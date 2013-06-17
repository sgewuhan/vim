
package com.sg.vim.datamodel.service.fuellabel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RllxParamEntity complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RllxParamEntity">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Param_Entity_Id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="Param_Code" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="V_Id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Vin" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Param_Value" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RllxParamEntity", propOrder = {
    "paramEntityId",
    "paramCode",
    "vId",
    "vin",
    "paramValue"
})
public class RllxParamEntity {

    @XmlElement(name = "Param_Entity_Id")
    protected long paramEntityId;
    @XmlElement(name = "Param_Code")
    protected String paramCode;
    @XmlElement(name = "V_Id")
    protected String vId;
    @XmlElement(name = "Vin")
    protected String vin;
    @XmlElement(name = "Param_Value")
    protected String paramValue;

    /**
     * Gets the value of the paramEntityId property.
     * 
     */
    public long getParamEntityId() {
        return paramEntityId;
    }

    /**
     * Sets the value of the paramEntityId property.
     * 
     */
    public void setParamEntityId(long value) {
        this.paramEntityId = value;
    }

    /**
     * Gets the value of the paramCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParamCode() {
        return paramCode;
    }

    /**
     * Sets the value of the paramCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParamCode(String value) {
        this.paramCode = value;
    }

    /**
     * Gets the value of the vId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVId() {
        return vId;
    }

    /**
     * Sets the value of the vId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVId(String value) {
        this.vId = value;
    }

    /**
     * Gets the value of the vin property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVin() {
        return vin;
    }

    /**
     * Sets the value of the vin property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVin(String value) {
        this.vin = value;
    }

    /**
     * Gets the value of the paramValue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParamValue() {
        return paramValue;
    }

    /**
     * Sets the value of the paramValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParamValue(String value) {
        this.paramValue = value;
    }

}
