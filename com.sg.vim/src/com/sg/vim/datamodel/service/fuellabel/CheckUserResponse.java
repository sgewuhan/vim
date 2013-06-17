
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
 *         &lt;element name="CheckUserResult" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
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
    "checkUserResult"
})
@XmlRootElement(name = "CheckUserResponse")
public class CheckUserResponse {

    @XmlElement(name = "CheckUserResult")
    protected boolean checkUserResult;

    /**
     * Gets the value of the checkUserResult property.
     * 
     */
    public boolean isCheckUserResult() {
        return checkUserResult;
    }

    /**
     * Sets the value of the checkUserResult property.
     * 
     */
    public void setCheckUserResult(boolean value) {
        this.checkUserResult = value;
    }

}
