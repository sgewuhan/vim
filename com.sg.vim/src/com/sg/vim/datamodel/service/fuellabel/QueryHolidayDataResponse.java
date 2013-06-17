
package com.sg.vim.datamodel.service.fuellabel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.sg.vim.datamodel.service.ArrayOfString;


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
 *         &lt;element name="QueryHolidayDataResult" type="{http://soap.catarc.info/}ArrayOfString" minOccurs="0"/>
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
    "queryHolidayDataResult"
})
@XmlRootElement(name = "QueryHolidayDataResponse")
public class QueryHolidayDataResponse {

    @XmlElement(name = "QueryHolidayDataResult")
    protected ArrayOfString queryHolidayDataResult;

    /**
     * Gets the value of the queryHolidayDataResult property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfString }
     *     
     */
    public ArrayOfString getQueryHolidayDataResult() {
        return queryHolidayDataResult;
    }

    /**
     * Sets the value of the queryHolidayDataResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfString }
     *     
     */
    public void setQueryHolidayDataResult(ArrayOfString value) {
        this.queryHolidayDataResult = value;
    }

}
