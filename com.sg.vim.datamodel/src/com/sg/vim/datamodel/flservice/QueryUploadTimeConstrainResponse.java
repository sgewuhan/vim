
package com.sg.vim.datamodel.flservice;

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
 *         &lt;element name="QueryUploadTimeConstrainResult" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
    "queryUploadTimeConstrainResult"
})
@XmlRootElement(name = "QueryUploadTimeConstrainResponse")
public class QueryUploadTimeConstrainResponse {

    @XmlElement(name = "QueryUploadTimeConstrainResult")
    protected int queryUploadTimeConstrainResult;

    /**
     * Gets the value of the queryUploadTimeConstrainResult property.
     * 
     */
    public int getQueryUploadTimeConstrainResult() {
        return queryUploadTimeConstrainResult;
    }

    /**
     * Sets the value of the queryUploadTimeConstrainResult property.
     * 
     */
    public void setQueryUploadTimeConstrainResult(int value) {
        this.queryUploadTimeConstrainResult = value;
    }

}
