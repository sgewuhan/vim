
package com.sg.vim.datamodel.flservice;

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
 *         &lt;element name="userName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="password" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="vehicleInfoList" type="{http://soap.catarc.info/}ArrayOfVehicleBasicInfo" minOccurs="0"/>
 *         &lt;element name="oKey" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "userName",
    "password",
    "vehicleInfoList",
    "oKey"
})
@XmlRootElement(name = "ApplyUpdate")
public class ApplyUpdate {

    protected String userName;
    protected String password;
    protected ArrayOfVehicleBasicInfo vehicleInfoList;
    protected String oKey;

    /**
     * Gets the value of the userName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets the value of the userName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserName(String value) {
        this.userName = value;
    }

    /**
     * Gets the value of the password property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the value of the password property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPassword(String value) {
        this.password = value;
    }

    /**
     * Gets the value of the vehicleInfoList property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfVehicleBasicInfo }
     *     
     */
    public ArrayOfVehicleBasicInfo getVehicleInfoList() {
        return vehicleInfoList;
    }

    /**
     * Sets the value of the vehicleInfoList property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfVehicleBasicInfo }
     *     
     */
    public void setVehicleInfoList(ArrayOfVehicleBasicInfo value) {
        this.vehicleInfoList = value;
    }

    /**
     * Gets the value of the oKey property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOKey() {
        return oKey;
    }

    /**
     * Sets the value of the oKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOKey(String value) {
        this.oKey = value;
    }

}
