//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.04.01 at 04:26:27 PM ICT 
//


package phoebe.eqx.smoi.wsdl.InfoSyncDefines;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import phoebe.eqx.smoi.wsdl.CommonComponents.SResultDescription;


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
 *         &lt;element name="sResult" type="{http://www.asiainfo.com/obd/CommonComponents.obd}SResultDescription"/>
 *         &lt;element name="AcctNotifyList" type="{http://www.asiainfo.com/obd/InfoSyncDefines.obd}AcctNotifyList" minOccurs="0"/>
 *         &lt;element name="PhoneNotifyList" type="{http://www.asiainfo.com/obd/InfoSyncDefines.obd}PhoneNotifyList" minOccurs="0"/>
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
    "sResult",
    "acctNotifyList",
    "phoneNotifyList"
})
@XmlRootElement(name = "do_QuerySuppressNotificationResponse", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
public class DoQuerySuppressNotificationResponse {

    @XmlElement(namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd", required = true)
    protected SResultDescription sResult;
    @XmlElement(name = "AcctNotifyList", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected AcctNotifyList acctNotifyList;
    @XmlElement(name = "PhoneNotifyList", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected PhoneNotifyList phoneNotifyList;

    /**
     * Gets the value of the sResult property.
     * 
     * @return
     *     possible object is
     *     {@link SResultDescription }
     *     
     */
    public SResultDescription getSResult() {
        return sResult;
    }

    /**
     * Sets the value of the sResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link SResultDescription }
     *     
     */
    public void setSResult(SResultDescription value) {
        this.sResult = value;
    }

    /**
     * Gets the value of the acctNotifyList property.
     * 
     * @return
     *     possible object is
     *     {@link AcctNotifyList }
     *     
     */
    public AcctNotifyList getAcctNotifyList() {
        return acctNotifyList;
    }

    /**
     * Sets the value of the acctNotifyList property.
     * 
     * @param value
     *     allowed object is
     *     {@link AcctNotifyList }
     *     
     */
    public void setAcctNotifyList(AcctNotifyList value) {
        this.acctNotifyList = value;
    }

    /**
     * Gets the value of the phoneNotifyList property.
     * 
     * @return
     *     possible object is
     *     {@link PhoneNotifyList }
     *     
     */
    public PhoneNotifyList getPhoneNotifyList() {
        return phoneNotifyList;
    }

    /**
     * Sets the value of the phoneNotifyList property.
     * 
     * @param value
     *     allowed object is
     *     {@link PhoneNotifyList }
     *     
     */
    public void setPhoneNotifyList(PhoneNotifyList value) {
        this.phoneNotifyList = value;
    }

}
