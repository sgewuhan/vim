
package com.sg.vim.datamodel.service.fuellabel;

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
 *         &lt;element name="QueryApplyUpdateInfoResult" type="{http://soap.catarc.info/}ArrayOfVehicleBasicInfo" minOccurs="0"/>
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
    "queryApplyUpdateInfoResult"
})
@XmlRootElement(name = "QueryApplyUpdateInfoResponse")
public class QueryApplyUpdateInfoResponse {

    @XmlElement(name = "QueryApplyUpdateInfoResult")
    protected ArrayOfVehicleBasicInfo queryApplyUpdateInfoResult;

    /**
     * Gets the value of the queryApplyUpdateInfoResult property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfVehicleBasicInfo }
     *     
     */
    public ArrayOfVehicleBasicInfo getQueryApplyUpdateInfoResult() {
        return queryApplyUpdateInfoResult;
    }

    /**
     * Sets the value of the queryApplyUpdateInfoResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfVehicleBasicInfo }
     *     
     */
    public void setQueryApplyUpdateInfoResult(ArrayOfVehicleBasicInfo value) {
        this.queryApplyUpdateInfoResult = value;
    }

}
