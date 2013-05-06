package com.sg.vim.datamodel.vidcservice;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for ArrayOfNameValuePair complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name=&quot;ArrayOfNameValuePair&quot;&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base=&quot;{http://www.w3.org/2001/XMLSchema}anyType&quot;&gt;
 *       &lt;sequence&gt;
 *         &lt;element name=&quot;NameValuePair&quot; type=&quot;{http://www.vidc.info/certificate/operation/}NameValuePair&quot; maxOccurs=&quot;unbounded&quot; minOccurs=&quot;0&quot;/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfNameValuePair", namespace = "http://www.vidc.info/certificate/operation/", propOrder = { "nameValuePair" })
public class ArrayOfNameValuePair {

	@XmlElement(name = "NameValuePair", nillable = true)
	protected List<NameValuePair> nameValuePair;

	/**
	 * Gets the value of the nameValuePair property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the nameValuePair property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getNameValuePair().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link NameValuePair }
	 * 
	 * 
	 */
	public List<NameValuePair> getNameValuePair() {
		if (nameValuePair == null) {
			nameValuePair = new ArrayList<NameValuePair>();
		}
		return this.nameValuePair;
	}

}
