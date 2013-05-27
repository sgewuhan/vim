
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
 *         &lt;element name="QueryRllxParamDataResult" type="{http://soap.catarc.info/}ArrayOfRllxParam" minOccurs="0"/>
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
    "queryRllxParamDataResult"
})
@XmlRootElement(name = "QueryRllxParamDataResponse")
public class QueryRllxParamDataResponse {

    @XmlElement(name = "QueryRllxParamDataResult")
    protected ArrayOfRllxParam queryRllxParamDataResult;

    /**
     * Gets the value of the queryRllxParamDataResult property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfRllxParam }
     *     
     */
    public ArrayOfRllxParam getQueryRllxParamDataResult() {
        return queryRllxParamDataResult;
    }

    /**
     * Sets the value of the queryRllxParamDataResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfRllxParam }
     *     
     */
    public void setQueryRllxParamDataResult(ArrayOfRllxParam value) {
        this.queryRllxParamDataResult = value;
    }

}
