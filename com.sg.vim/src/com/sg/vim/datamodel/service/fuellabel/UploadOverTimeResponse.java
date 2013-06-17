
package com.sg.vim.datamodel.service.fuellabel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.sg.vim.datamodel.service.OperateResult;


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
 *         &lt;element name="UploadOverTimeResult" type="{http://soap.catarc.info/}OperateResult" minOccurs="0"/>
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
    "uploadOverTimeResult"
})
@XmlRootElement(name = "UploadOverTimeResponse")
public class UploadOverTimeResponse {

    @XmlElement(name = "UploadOverTimeResult")
    protected OperateResult uploadOverTimeResult;

    /**
     * Gets the value of the uploadOverTimeResult property.
     * 
     * @return
     *     possible object is
     *     {@link OperateResult }
     *     
     */
    public OperateResult getUploadOverTimeResult() {
        return uploadOverTimeResult;
    }

    /**
     * Sets the value of the uploadOverTimeResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link OperateResult }
     *     
     */
    public void setUploadOverTimeResult(OperateResult value) {
        this.uploadOverTimeResult = value;
    }

}
