package info.vidc.CertificateRequestService;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
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
 *         &lt;element name=&quot;UploadUpdate_EntResult&quot; type=&quot;{http://www.vidc.info/certificate/operation/}OperateResult&quot; minOccurs=&quot;0&quot;/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "uploadUpdateEntResult" })
@XmlRootElement(name = "UploadUpdate_EntResponse")
public class UploadUpdateEntResponse {

	@XmlElement(name = "UploadUpdate_EntResult")
	protected OperateResult uploadUpdateEntResult;

	/**
	 * Gets the value of the uploadUpdateEntResult property.
	 * 
	 * @return possible object is {@link OperateResult }
	 * 
	 */
	public OperateResult getUploadUpdateEntResult() {
		return uploadUpdateEntResult;
	}

	/**
	 * Sets the value of the uploadUpdateEntResult property.
	 * 
	 * @param value
	 *            allowed object is {@link OperateResult }
	 * 
	 */
	public void setUploadUpdateEntResult(OperateResult value) {
		this.uploadUpdateEntResult = value;
	}

}
