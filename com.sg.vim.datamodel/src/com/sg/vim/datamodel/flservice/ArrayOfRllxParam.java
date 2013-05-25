
package com.sg.vim.datamodel.flservice;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfRllxParam complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfRllxParam">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RllxParam" type="{http://soap.catarc.info/}RllxParam" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfRllxParam", propOrder = {
    "rllxParam"
})
public class ArrayOfRllxParam {

    @XmlElement(name = "RllxParam", nillable = true)
    protected List<RllxParam> rllxParam;

    /**
     * Gets the value of the rllxParam property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the rllxParam property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRllxParam().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RllxParam }
     * 
     * 
     */
    public List<RllxParam> getRllxParam() {
        if (rllxParam == null) {
            rllxParam = new ArrayList<RllxParam>();
        }
        return this.rllxParam;
    }

}
