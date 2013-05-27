
package com.sg.vim.datamodel.service.fuellabel;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfRllxParamEntity complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfRllxParamEntity">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RllxParamEntity" type="{http://soap.catarc.info/}RllxParamEntity" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfRllxParamEntity", propOrder = {
    "rllxParamEntity"
})
public class ArrayOfRllxParamEntity {

    @XmlElement(name = "RllxParamEntity", nillable = true)
    protected List<RllxParamEntity> rllxParamEntity;

    /**
     * Gets the value of the rllxParamEntity property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the rllxParamEntity property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRllxParamEntity().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RllxParamEntity }
     * 
     * 
     */
    public List<RllxParamEntity> getRllxParamEntity() {
        if (rllxParamEntity == null) {
            rllxParamEntity = new ArrayList<RllxParamEntity>();
        }
        return this.rllxParamEntity;
    }

}
