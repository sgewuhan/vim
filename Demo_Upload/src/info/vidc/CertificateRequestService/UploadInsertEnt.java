package info.vidc.CertificateRequestService;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for anonymous complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base=&quot;{http://www.w3.org/2001/XMLSchema}anyType&quot;&gt;
 *       &lt;sequence&gt;
 *         &lt;element name=&quot;data&quot; type=&quot;{http://service.vidc.info/certificaterequest}ArrayOfCertificateInfo&quot; minOccurs=&quot;0&quot;/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "data" })
@XmlRootElement(name = "UploadInsert_Ent")
public class UploadInsertEnt {

	protected ArrayOfCertificateInfo data;

	/**
	 * Gets the value of the data property.
	 * 
	 * @return possible object is {@link ArrayOfCertificateInfo }
	 * 
	 */
	public ArrayOfCertificateInfo getData() {
		return data;
	}

	/**
	 * Sets the value of the data property.
	 * 
	 * @param value
	 *            allowed object is {@link ArrayOfCertificateInfo }
	 * 
	 */
	public void setData(ArrayOfCertificateInfo value) {
		this.data = value;
	}

}
