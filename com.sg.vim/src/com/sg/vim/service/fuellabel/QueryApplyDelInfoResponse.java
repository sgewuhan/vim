
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
 *         &lt;element name="QueryApplyDelInfoResult" type="{http://soap.catarc.info/}ArrayOfVehicleBasicInfo" minOccurs="0"/>
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
    "queryApplyDelInfoResult"
})
@XmlRootElement(name = "QueryApplyDelInfoResponse")
public class QueryApplyDelInfoResponse {

    @XmlElement(name = "QueryApplyDelInfoResult")
    protected ArrayOfVehicleBasicInfo queryApplyDelInfoResult;

    /**
     * Gets the value of the queryApplyDelInfoResult property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfVehicleBasicInfo }
     *     
     */
    public ArrayOfVehicleBasicInfo getQueryApplyDelInfoResult() {
        return queryApplyDelInfoResult;
    }

    /**
     * Sets the value of the queryApplyDelInfoResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfVehicleBasicInfo }
     *     
     */
    public void setQueryApplyDelInfoResult(ArrayOfVehicleBasicInfo value) {
        this.queryApplyDelInfoResult = value;
    }

}
