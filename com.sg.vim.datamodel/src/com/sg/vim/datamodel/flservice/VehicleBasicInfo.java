
package com.sg.vim.datamodel.flservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for VehicleBasicInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="VehicleBasicInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="EntityList" type="{http://soap.catarc.info/}ArrayOfRllxParamEntity" minOccurs="0"/>
 *         &lt;element name="V_Id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Vin" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="App_Vin" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Hgspbm" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="User_Id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Qcscqy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Jkqczjxs" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Clxh" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Clzl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Rllx" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Zczbzl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Zgcs" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Ltgg" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Zj" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Clzzrq" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="Tymc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Yyc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Zwps" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Zdsjzzl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Edzk" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Lj" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Qdxs" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CreateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="UpdateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="Status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Jyjgmc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Jybgbh" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Qtxx" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Apply_Type" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Check" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="Reason" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "VehicleBasicInfo", propOrder = {
    "entityList",
    "vId",
    "vin",
    "appVin",
    "hgspbm",
    "userId",
    "qcscqy",
    "jkqczjxs",
    "clxh",
    "clzl",
    "rllx",
    "zczbzl",
    "zgcs",
    "ltgg",
    "zj",
    "clzzrq",
    "tymc",
    "yyc",
    "zwps",
    "zdsjzzl",
    "edzk",
    "lj",
    "qdxs",
    "createTime",
    "updateTime",
    "status",
    "jyjgmc",
    "jybgbh",
    "qtxx",
    "applyType",
    "check",
    "reason"
})
public class VehicleBasicInfo {

    @XmlElement(name = "EntityList")
    protected ArrayOfRllxParamEntity entityList;
    @XmlElement(name = "V_Id")
    protected String vId;
    @XmlElement(name = "Vin")
    protected String vin;
    @XmlElement(name = "App_Vin")
    protected String appVin;
    @XmlElement(name = "Hgspbm")
    protected String hgspbm;
    @XmlElement(name = "User_Id")
    protected String userId;
    @XmlElement(name = "Qcscqy")
    protected String qcscqy;
    @XmlElement(name = "Jkqczjxs")
    protected String jkqczjxs;
    @XmlElement(name = "Clxh")
    protected String clxh;
    @XmlElement(name = "Clzl")
    protected String clzl;
    @XmlElement(name = "Rllx")
    protected String rllx;
    @XmlElement(name = "Zczbzl")
    protected String zczbzl;
    @XmlElement(name = "Zgcs")
    protected String zgcs;
    @XmlElement(name = "Ltgg")
    protected String ltgg;
    @XmlElement(name = "Zj")
    protected String zj;
    @XmlElement(name = "Clzzrq", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar clzzrq;
    @XmlElement(name = "Tymc")
    protected String tymc;
    @XmlElement(name = "Yyc")
    protected String yyc;
    @XmlElement(name = "Zwps")
    protected String zwps;
    @XmlElement(name = "Zdsjzzl")
    protected String zdsjzzl;
    @XmlElement(name = "Edzk")
    protected String edzk;
    @XmlElement(name = "Lj")
    protected String lj;
    @XmlElement(name = "Qdxs")
    protected String qdxs;
    @XmlElement(name = "CreateTime", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar createTime;
    @XmlElement(name = "UpdateTime", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar updateTime;
    @XmlElement(name = "Status")
    protected String status;
    @XmlElement(name = "Jyjgmc")
    protected String jyjgmc;
    @XmlElement(name = "Jybgbh")
    protected String jybgbh;
    @XmlElement(name = "Qtxx")
    protected String qtxx;
    @XmlElement(name = "Apply_Type")
    protected String applyType;
    @XmlElement(name = "Check")
    protected boolean check;
    @XmlElement(name = "Reason")
    protected String reason;

    /**
     * Gets the value of the entityList property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfRllxParamEntity }
     *     
     */
    public ArrayOfRllxParamEntity getEntityList() {
        return entityList;
    }

    /**
     * Sets the value of the entityList property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfRllxParamEntity }
     *     
     */
    public void setEntityList(ArrayOfRllxParamEntity value) {
        this.entityList = value;
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
     * Gets the value of the appVin property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAppVin() {
        return appVin;
    }

    /**
     * Sets the value of the appVin property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAppVin(String value) {
        this.appVin = value;
    }

    /**
     * Gets the value of the hgspbm property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHgspbm() {
        return hgspbm;
    }

    /**
     * Sets the value of the hgspbm property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHgspbm(String value) {
        this.hgspbm = value;
    }

    /**
     * Gets the value of the userId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Sets the value of the userId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserId(String value) {
        this.userId = value;
    }

    /**
     * Gets the value of the qcscqy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQcscqy() {
        return qcscqy;
    }

    /**
     * Sets the value of the qcscqy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQcscqy(String value) {
        this.qcscqy = value;
    }

    /**
     * Gets the value of the jkqczjxs property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJkqczjxs() {
        return jkqczjxs;
    }

    /**
     * Sets the value of the jkqczjxs property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJkqczjxs(String value) {
        this.jkqczjxs = value;
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
     * Gets the value of the zczbzl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZczbzl() {
        return zczbzl;
    }

    /**
     * Sets the value of the zczbzl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZczbzl(String value) {
        this.zczbzl = value;
    }

    /**
     * Gets the value of the zgcs property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZgcs() {
        return zgcs;
    }

    /**
     * Sets the value of the zgcs property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZgcs(String value) {
        this.zgcs = value;
    }

    /**
     * Gets the value of the ltgg property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLtgg() {
        return ltgg;
    }

    /**
     * Sets the value of the ltgg property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLtgg(String value) {
        this.ltgg = value;
    }

    /**
     * Gets the value of the zj property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZj() {
        return zj;
    }

    /**
     * Sets the value of the zj property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZj(String value) {
        this.zj = value;
    }

    /**
     * Gets the value of the clzzrq property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getClzzrq() {
        return clzzrq;
    }

    /**
     * Sets the value of the clzzrq property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setClzzrq(XMLGregorianCalendar value) {
        this.clzzrq = value;
    }

    /**
     * Gets the value of the tymc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTymc() {
        return tymc;
    }

    /**
     * Sets the value of the tymc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTymc(String value) {
        this.tymc = value;
    }

    /**
     * Gets the value of the yyc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getYyc() {
        return yyc;
    }

    /**
     * Sets the value of the yyc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setYyc(String value) {
        this.yyc = value;
    }

    /**
     * Gets the value of the zwps property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZwps() {
        return zwps;
    }

    /**
     * Sets the value of the zwps property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZwps(String value) {
        this.zwps = value;
    }

    /**
     * Gets the value of the zdsjzzl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZdsjzzl() {
        return zdsjzzl;
    }

    /**
     * Sets the value of the zdsjzzl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZdsjzzl(String value) {
        this.zdsjzzl = value;
    }

    /**
     * Gets the value of the edzk property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEdzk() {
        return edzk;
    }

    /**
     * Sets the value of the edzk property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEdzk(String value) {
        this.edzk = value;
    }

    /**
     * Gets the value of the lj property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLj() {
        return lj;
    }

    /**
     * Sets the value of the lj property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLj(String value) {
        this.lj = value;
    }

    /**
     * Gets the value of the qdxs property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQdxs() {
        return qdxs;
    }

    /**
     * Sets the value of the qdxs property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQdxs(String value) {
        this.qdxs = value;
    }

    /**
     * Gets the value of the createTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCreateTime() {
        return createTime;
    }

    /**
     * Sets the value of the createTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCreateTime(XMLGregorianCalendar value) {
        this.createTime = value;
    }

    /**
     * Gets the value of the updateTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getUpdateTime() {
        return updateTime;
    }

    /**
     * Sets the value of the updateTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setUpdateTime(XMLGregorianCalendar value) {
        this.updateTime = value;
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
     * Gets the value of the jyjgmc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJyjgmc() {
        return jyjgmc;
    }

    /**
     * Sets the value of the jyjgmc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJyjgmc(String value) {
        this.jyjgmc = value;
    }

    /**
     * Gets the value of the jybgbh property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJybgbh() {
        return jybgbh;
    }

    /**
     * Sets the value of the jybgbh property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJybgbh(String value) {
        this.jybgbh = value;
    }

    /**
     * Gets the value of the qtxx property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQtxx() {
        return qtxx;
    }

    /**
     * Sets the value of the qtxx property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQtxx(String value) {
        this.qtxx = value;
    }

    /**
     * Gets the value of the applyType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApplyType() {
        return applyType;
    }

    /**
     * Sets the value of the applyType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApplyType(String value) {
        this.applyType = value;
    }

    /**
     * Gets the value of the check property.
     * 
     */
    public boolean isCheck() {
        return check;
    }

    /**
     * Sets the value of the check property.
     * 
     */
    public void setCheck(boolean value) {
        this.check = value;
    }

    /**
     * Gets the value of the reason property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReason() {
        return reason;
    }

    /**
     * Sets the value of the reason property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReason(String value) {
        this.reason = value;
    }

}
