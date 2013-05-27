
package com.sg.vim.datamodel.service.fuellabel;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfVehicleBasicInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfVehicleBasicInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="VehicleBasicInfo" type="{http://soap.catarc.info/}VehicleBasicInfo" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfVehicleBasicInfo", propOrder = {
    "vehicleBasicInfo"
})
public class ArrayOfVehicleBasicInfo {

    @XmlElement(name = "VehicleBasicInfo", nillable = true)
    protected List<VehicleBasicInfo> vehicleBasicInfo;

    /**
     * Gets the value of the vehicleBasicInfo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the vehicleBasicInfo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getVehicleBasicInfo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link VehicleBasicInfo }
     * 
     * 
     */
    public List<VehicleBasicInfo> getVehicleBasicInfo() {
        if (vehicleBasicInfo == null) {
            vehicleBasicInfo = new ArrayList<VehicleBasicInfo>();
        }
        return this.vehicleBasicInfo;
    }

}
