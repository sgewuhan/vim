
package com.sg.vim.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for OperateResult complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OperateResult">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ResultCode" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="ResultDetail" type="{http://soap.catarc.info/}ArrayOfNameValuePair" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OperateResult", propOrder = {
    "resultCode",
    "resultDetail"
})
public class OperateResult {

    @XmlElement(name = "ResultCode")
    protected int resultCode;
    @XmlElement(name = "ResultDetail")
    protected ArrayOfNameValuePair resultDetail;

    /**
     * Gets the value of the resultCode property.
     * 
     */
    public int getResultCode() {
        return resultCode;
    }

    /**
     * Sets the value of the resultCode property.
     * 
     */
    public void setResultCode(int value) {
        this.resultCode = value;
    }

    /**
     * Gets the value of the resultDetail property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfNameValuePair }
     *     
     */
    public ArrayOfNameValuePair getResultDetail() {
        return resultDetail;
    }

    /**
     * Sets the value of the resultDetail property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfNameValuePair }
     *     
     */
    public void setResultDetail(ArrayOfNameValuePair value) {
        this.resultDetail = value;
    }

}
