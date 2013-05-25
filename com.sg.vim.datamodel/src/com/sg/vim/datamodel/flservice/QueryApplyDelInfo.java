
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
 *         &lt;element name="pageNum" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="pagesize" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="vin" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="clxh" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="clzl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rllx" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="startTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="endTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="timeType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "pageNum",
    "pagesize",
    "vin",
    "clxh",
    "clzl",
    "rllx",
    "startTime",
    "endTime",
    "timeType",
    "oKey"
})
@XmlRootElement(name = "QueryApplyDelInfo")
public class QueryApplyDelInfo {

    protected String userName;
    protected String password;
    protected int pageNum;
    protected int pagesize;
    protected String vin;
    protected String clxh;
    protected String clzl;
    protected String rllx;
    protected String startTime;
    protected String endTime;
    protected String timeType;
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
     * Gets the value of the pageNum property.
     * 
     */
    public int getPageNum() {
        return pageNum;
    }

    /**
     * Sets the value of the pageNum property.
     * 
     */
    public void setPageNum(int value) {
        this.pageNum = value;
    }

    /**
     * Gets the value of the pagesize property.
     * 
     */
    public int getPagesize() {
        return pagesize;
    }

    /**
     * Sets the value of the pagesize property.
     * 
     */
    public void setPagesize(int value) {
        this.pagesize = value;
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
     * Gets the value of the clxh property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClxh() {
        return clxh;
    }

    /**
     * Sets the value of the clxh property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClxh(String value) {
        this.clxh = value;
    }

    /**
     * Gets the value of the clzl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClzl() {
        return clzl;
    }

    /**
     * Sets the value of the clzl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClzl(String value) {
        this.clzl = value;
    }

    /**
     * Gets the value of the rllx property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRllx() {
        return rllx;
    }

    /**
     * Sets the value of the rllx property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRllx(String value) {
        this.rllx = value;
    }

    /**
     * Gets the value of the startTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * Sets the value of the startTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStartTime(String value) {
        this.startTime = value;
    }

    /**
     * Gets the value of the endTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEndTime() {
        return endTime;
    }

    /**
     * Sets the value of the endTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEndTime(String value) {
        this.endTime = value;
    }

    /**
     * Gets the value of the timeType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTimeType() {
        return timeType;
    }

    /**
     * Sets the value of the timeType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTimeType(String value) {
        this.timeType = value;
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
