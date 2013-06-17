
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
 *         &lt;element name="ApplyUpdateResult" type="{http://soap.catarc.info/}OperateResult" minOccurs="0"/>
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
    "applyUpdateResult"
})
@XmlRootElement(name = "ApplyUpdateResponse")
public class ApplyUpdateResponse {

    @XmlElement(name = "ApplyUpdateResult")
    protected OperateResult applyUpdateResult;

    /**
     * Gets the value of the applyUpdateResult property.
     * 
     * @return
     *     possible object is
     *     {@link OperateResult }
     *     
     */
    public OperateResult getApplyUpdateResult() {
        return applyUpdateResult;
    }

    /**
     * Sets the value of the applyUpdateResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link OperateResult }
     *     
     */
    public void setApplyUpdateResult(OperateResult value) {
        this.applyUpdateResult = value;
    }

}
