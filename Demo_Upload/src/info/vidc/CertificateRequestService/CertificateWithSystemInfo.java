package info.vidc.CertificateRequestService;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>
 * Java class for CertificateWithSystemInfo complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name=&quot;CertificateWithSystemInfo&quot;&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base=&quot;{http://www.vidc.info/certificate/operation/}CertificateWithPrintingSystem&quot;&gt;
 *       &lt;sequence&gt;
 *         &lt;element name=&quot;H_ID&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;VERCODE&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;RESPONSE_CODE&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;CREATETIME&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}dateTime&quot;/&gt;
 *         &lt;element name=&quot;HD_HOST&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;HD_USER&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;UPDATETIME&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}dateTime&quot;/&gt;
 *         &lt;element name=&quot;VERSION&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;CLIENT_HARDWARE_INFO&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;UPSEND_TAG&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;FEEDBACK_TIME&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}dateTime&quot;/&gt;
 *         &lt;element name=&quot;UKEY&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CertificateWithSystemInfo", namespace = "http://www.vidc.info/certificate/operation/", propOrder = {
		"hid", "vercode", "responsecode", "createtime", "hdhost", "hduser",
		"updatetime", "version", "clienthardwareinfo", "upsendtag",
		"feedbacktime", "ukey" })
public class CertificateWithSystemInfo extends CertificateWithPrintingSystem {

	@XmlElement(name = "H_ID")
	protected String hid;
	@XmlElement(name = "VERCODE")
	protected String vercode;
	@XmlElement(name = "RESPONSE_CODE")
	protected String responsecode;
	@XmlElement(name = "CREATETIME", required = true)
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar createtime;
	@XmlElement(name = "HD_HOST")
	protected String hdhost;
	@XmlElement(name = "HD_USER")
	protected String hduser;
	@XmlElement(name = "UPDATETIME", required = true)
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar updatetime;
	@XmlElement(name = "VERSION")
	protected String version;
	@XmlElement(name = "CLIENT_HARDWARE_INFO")
	protected String clienthardwareinfo;
	@XmlElement(name = "UPSEND_TAG")
	protected String upsendtag;
	@XmlElement(name = "FEEDBACK_TIME", required = true)
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar feedbacktime;
	@XmlElement(name = "UKEY")
	protected String ukey;

	/**
	 * Gets the value of the hid property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getHID() {
		return hid;
	}

	/**
	 * Sets the value of the hid property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setHID(String value) {
		this.hid = value;
	}

	/**
	 * Gets the value of the vercode property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getVERCODE() {
		return vercode;
	}

	/**
	 * Sets the value of the vercode property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setVERCODE(String value) {
		this.vercode = value;
	}

	/**
	 * Gets the value of the responsecode property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getRESPONSECODE() {
		return responsecode;
	}

	/**
	 * Sets the value of the responsecode property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setRESPONSECODE(String value) {
		this.responsecode = value;
	}

	/**
	 * Gets the value of the createtime property.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getCREATETIME() {
		return createtime;
	}

	/**
	 * Sets the value of the createtime property.
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setCREATETIME(XMLGregorianCalendar value) {
		this.createtime = value;
	}

	/**
	 * Gets the value of the hdhost property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getHDHOST() {
		return hdhost;
	}

	/**
	 * Sets the value of the hdhost property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setHDHOST(String value) {
		this.hdhost = value;
	}

	/**
	 * Gets the value of the hduser property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getHDUSER() {
		return hduser;
	}

	/**
	 * Sets the value of the hduser property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setHDUSER(String value) {
		this.hduser = value;
	}

	/**
	 * Gets the value of the updatetime property.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getUPDATETIME() {
		return updatetime;
	}

	/**
	 * Sets the value of the updatetime property.
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setUPDATETIME(XMLGregorianCalendar value) {
		this.updatetime = value;
	}

	/**
	 * Gets the value of the version property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getVERSION() {
		return version;
	}

	/**
	 * Sets the value of the version property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setVERSION(String value) {
		this.version = value;
	}

	/**
	 * Gets the value of the clienthardwareinfo property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCLIENTHARDWAREINFO() {
		return clienthardwareinfo;
	}

	/**
	 * Sets the value of the clienthardwareinfo property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCLIENTHARDWAREINFO(String value) {
		this.clienthardwareinfo = value;
	}

	/**
	 * Gets the value of the upsendtag property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getUPSENDTAG() {
		return upsendtag;
	}

	/**
	 * Sets the value of the upsendtag property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setUPSENDTAG(String value) {
		this.upsendtag = value;
	}

	/**
	 * Gets the value of the feedbacktime property.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getFEEDBACKTIME() {
		return feedbacktime;
	}

	/**
	 * Sets the value of the feedbacktime property.
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setFEEDBACKTIME(XMLGregorianCalendar value) {
		this.feedbacktime = value;
	}

	/**
	 * Gets the value of the ukey property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getUKEY() {
		return ukey;
	}

	/**
	 * Sets the value of the ukey property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setUKEY(String value) {
		this.ukey = value;
	}

}
