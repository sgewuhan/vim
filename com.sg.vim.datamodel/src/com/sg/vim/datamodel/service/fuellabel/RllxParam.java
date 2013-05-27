
package com.sg.vim.datamodel.service.fuellabel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RllxParam complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RllxParam">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Param_Code" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Param_Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Fuel_Type" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Param_Remark" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Control_Type" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Control_Value" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Order_Rule" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RllxParam", propOrder = {
    "paramCode",
    "paramName",
    "fuelType",
    "paramRemark",
    "controlType",
    "controlValue",
    "status",
    "orderRule"
})
public class RllxParam {

    @XmlElement(name = "Param_Code")
    protected String paramCode;
    @XmlElement(name = "Param_Name")
    protected String paramName;
    @XmlElement(name = "Fuel_Type")
    protected String fuelType;
    @XmlElement(name = "Param_Remark")
    protected String paramRemark;
    @XmlElement(name = "Control_Type")
    protected String controlType;
    @XmlElement(name = "Control_Value")
    protected String controlValue;
    @XmlElement(name = "Status")
    protected String status;
    @XmlElement(name = "Order_Rule")
    protected String orderRule;

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
     * Gets the value of the paramName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParamName() {
        return paramName;
    }

    /**
     * Sets the value of the paramName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParamName(String value) {
        this.paramName = value;
    }

    /**
     * Gets the value of the fuelType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFuelType() {
        return fuelType;
    }

    /**
     * Sets the value of the fuelType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFuelType(String value) {
        this.fuelType = value;
    }

    /**
     * Gets the value of the paramRemark property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParamRemark() {
        return paramRemark;
    }

    /**
     * Sets the value of the paramRemark property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParamRemark(String value) {
        this.paramRemark = value;
    }

    /**
     * Gets the value of the controlType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getControlType() {
        return controlType;
    }

    /**
     * Sets the value of the controlType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setControlType(String value) {
        this.controlType = value;
    }

    /**
     * Gets the value of the controlValue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getControlValue() {
        return controlValue;
    }

    /**
     * Sets the value of the controlValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setControlValue(String value) {
        this.controlValue = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatus(String value) {
        this.status = value;
    }

    /**
     * Gets the value of the orderRule property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderRule() {
        return orderRule;
    }

    /**
     * Sets the value of the orderRule property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderRule(String value) {
        this.orderRule = value;
    }

}
