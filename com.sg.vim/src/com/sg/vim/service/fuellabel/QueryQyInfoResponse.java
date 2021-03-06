
package com.sg.vim.service.fuellabel;

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
 *         &lt;element name="QueryQyInfoResult" type="{http://soap.catarc.info/}QyInfo" minOccurs="0"/>
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
    "queryQyInfoResult"
})
@XmlRootElement(name = "QueryQyInfoResponse")
public class QueryQyInfoResponse {

    @XmlElement(name = "QueryQyInfoResult")
    protected QyInfo queryQyInfoResult;

    /**
     * Gets the value of the queryQyInfoResult property.
     * 
     * @return
     *     possible object is
     *     {@link QyInfo }
     *     
     */
    public QyInfo getQueryQyInfoResult() {
        return queryQyInfoResult;
    }

    /**
     * Sets the value of the queryQyInfoResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link QyInfo }
     *     
     */
    public void setQueryQyInfoResult(QyInfo value) {
        this.queryQyInfoResult = value;
    }

}
