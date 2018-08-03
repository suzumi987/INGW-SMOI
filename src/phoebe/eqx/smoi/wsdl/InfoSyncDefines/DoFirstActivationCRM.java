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
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

import phoebe.eqx.smoi.wsdl.CommonComponents.OneTimeFee;
import phoebe.eqx.smoi.wsdl.CommonComponents.SBalance;
import phoebe.eqx.smoi.wsdl.CommonComponents.SOperInfo;


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
 *         &lt;element name="sOper" type="{http://www.asiainfo.com/obd/CommonComponents.obd}SOperInfo"/>
 *         &lt;element name="_user_id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="_phone_id">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="30"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="_activation_time" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="_SMS_language" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="32"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="_USSD_language" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="32"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="_IVR_language" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="32"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="_Email_language" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="32"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="SBalance" type="{http://www.asiainfo.com/obd/CommonComponents.obd}SBalance" minOccurs="0"/>
 *         &lt;element name="_location_code" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="30"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="_notification_flag" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}short">
 *               &lt;enumeration value="0"/>
 *               &lt;enumeration value="1"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="OneTimeFee" type="{http://www.asiainfo.com/obd/CommonComponents.obd}OneTimeFee" minOccurs="0"/>
 *         &lt;element name="_site_id" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="10"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
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
    "sOper",
    "userId",
    "phoneId",
    "activationTime",
    "smsLanguage",
    "ussdLanguage",
    "ivrLanguage",
    "emailLanguage",
    "sBalance",
    "locationCode",
    "notificationFlag",
    "oneTimeFee",
    "siteId"
})
@XmlRootElement(name = "do_FirstActivationCRM", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
public class DoFirstActivationCRM {

    @XmlElement(namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd", required = true)
    protected SOperInfo sOper;
    @XmlElement(name = "_user_id", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected Long userId;
    @XmlElement(name = "_phone_id", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd", required = true)
    protected String phoneId;
    @XmlElement(name = "_activation_time", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar activationTime;
    @XmlElement(name = "_SMS_language", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected String smsLanguage;
    @XmlElement(name = "_USSD_language", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected String ussdLanguage;
    @XmlElement(name = "_IVR_language", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected String ivrLanguage;
    @XmlElement(name = "_Email_language", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected String emailLanguage;
    @XmlElement(name = "SBalance", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected SBalance sBalance;
    @XmlElement(name = "_location_code", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected String locationCode;
    @XmlElement(name = "_notification_flag", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected Short notificationFlag;
    @XmlElement(name = "OneTimeFee", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected OneTimeFee oneTimeFee;
    @XmlElement(name = "_site_id", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected String siteId;

    /**
     * Gets the value of the sOper property.
     * 
     * @return
     *     possible object is
     *     {@link SOperInfo }
     *     
     */
    public SOperInfo getSOper() {
        return sOper;
    }

    /**
     * Sets the value of the sOper property.
     * 
     * @param value
     *     allowed object is
     *     {@link SOperInfo }
     *     
     */
    public void setSOper(SOperInfo value) {
        this.sOper = value;
    }

    /**
     * Gets the value of the userId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * Sets the value of the userId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setUserId(Long value) {
        this.userId = value;
    }

    /**
     * Gets the value of the phoneId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPhoneId() {
        return phoneId;
    }

    /**
     * Sets the value of the phoneId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPhoneId(String value) {
        this.phoneId = value;
    }

    /**
     * Gets the value of the activationTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getActivationTime() {
        return activationTime;
    }

    /**
     * Sets the value of the activationTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setActivationTime(XMLGregorianCalendar value) {
        this.activationTime = value;
    }

    /**
     * Gets the value of the smsLanguage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSMSLanguage() {
        return smsLanguage;
    }

    /**
     * Sets the value of the smsLanguage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSMSLanguage(String value) {
        this.smsLanguage = value;
    }

    /**
     * Gets the value of the ussdLanguage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUSSDLanguage() {
        return ussdLanguage;
    }

    /**
     * Sets the value of the ussdLanguage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUSSDLanguage(String value) {
        this.ussdLanguage = value;
    }

    /**
     * Gets the value of the ivrLanguage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIVRLanguage() {
        return ivrLanguage;
    }

    /**
     * Sets the value of the ivrLanguage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIVRLanguage(String value) {
        this.ivrLanguage = value;
    }

    /**
     * Gets the value of the emailLanguage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmailLanguage() {
        return emailLanguage;
    }

    /**
     * Sets the value of the emailLanguage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmailLanguage(String value) {
        this.emailLanguage = value;
    }

    /**
     * Gets the value of the sBalance property.
     * 
     * @return
     *     possible object is
     *     {@link SBalance }
     *     
     */
    public SBalance getSBalance() {
        return sBalance;
    }

    /**
     * Sets the value of the sBalance property.
     * 
     * @param value
     *     allowed object is
     *     {@link SBalance }
     *     
     */
    public void setSBalance(SBalance value) {
        this.sBalance = value;
    }

    /**
     * Gets the value of the locationCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocationCode() {
        return locationCode;
    }

    /**
     * Sets the value of the locationCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocationCode(String value) {
        this.locationCode = value;
    }

    /**
     * Gets the value of the notificationFlag property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getNotificationFlag() {
        return notificationFlag;
    }

    /**
     * Sets the value of the notificationFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setNotificationFlag(Short value) {
        this.notificationFlag = value;
    }

    /**
     * Gets the value of the oneTimeFee property.
     * 
     * @return
     *     possible object is
     *     {@link OneTimeFee }
     *     
     */
    public OneTimeFee getOneTimeFee() {
        return oneTimeFee;
    }

    /**
     * Sets the value of the oneTimeFee property.
     * 
     * @param value
     *     allowed object is
     *     {@link OneTimeFee }
     *     
     */
    public void setOneTimeFee(OneTimeFee value) {
        this.oneTimeFee = value;
    }

    /**
     * Gets the value of the siteId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSiteId() {
        return siteId;
    }

    /**
     * Sets the value of the siteId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSiteId(String value) {
        this.siteId = value;
    }

}
