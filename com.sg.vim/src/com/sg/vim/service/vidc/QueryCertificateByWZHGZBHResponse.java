package com.sg.vim.service.vidc;

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
 *         &lt;element name=&quot;QueryCertificateByWZHGZBHResult&quot; type=&quot;{http://service.vidc.info/certificaterequest}QueryResult&quot; minOccurs=&quot;0&quot;/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "queryCertificateByWZHGZBHResult" })
@XmlRootElement(name = "QueryCertificateByWZHGZBHResponse")
public class QueryCertificateByWZHGZBHResponse {

	@XmlElement(name = "QueryCertificateByWZHGZBHResult")
	protected QueryResult queryCertificateByWZHGZBHResult;

	/**
	 * Gets the value of the queryCertificateByWZHGZBHResult property.
	 * 
	 * @return possible object is {@link QueryResult }
	 * 
	 */
	public QueryResult getQueryCertificateByWZHGZBHResult() {
		return queryCertificateByWZHGZBHResult;
	}

	/**
	 * Sets the value of the queryCertificateByWZHGZBHResult property.
	 * 
	 * @param value
	 *            allowed object is {@link QueryResult }
	 * 
	 */
	public void setQueryCertificateByWZHGZBHResult(QueryResult value) {
		this.queryCertificateByWZHGZBHResult = value;
	}

}
