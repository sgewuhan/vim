
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
 *         &lt;element name="ApplyDeleteResult" type="{http://soap.catarc.info/}OperateResult" minOccurs="0"/>
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
    "applyDeleteResult"
})
@XmlRootElement(name = "ApplyDeleteResponse")
public class ApplyDeleteResponse {

    @XmlElement(name = "ApplyDeleteResult")
    protected OperateResult applyDeleteResult;

    /**
     * Gets the value of the applyDeleteResult property.
     * 
     * @return
     *     possible object is
     *     {@link OperateResult }
     *     
     */
    public OperateResult getApplyDeleteResult() {
        return applyDeleteResult;
    }

    /**
     * Sets the value of the applyDeleteResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link OperateResult }
     *     
     */
    public void setApplyDeleteResult(OperateResult value) {
        this.applyDeleteResult = value;
    }

}
