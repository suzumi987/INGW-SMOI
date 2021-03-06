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
 *         &lt;element name="_master_phone_id">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="30"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="_dummy_account">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="15"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="_real_account">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="15"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="_new_max_balance" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="_new_max_validity" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="_new_default_days" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="_old_max_balance" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="_old_max_validity" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="_old_default_days" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="_valid_date" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
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
    "masterPhoneId",
    "dummyAccount",
    "realAccount",
    "newMaxBalance",
    "newMaxValidity",
    "newDefaultDays",
    "oldMaxBalance",
    "oldMaxValidity",
    "oldDefaultDays",
    "validDate"
})
@XmlRootElement(name = "do_ChangeSingleBalanceRuleResponse", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
public class DoChangeSingleBalanceRuleResponse {

    @XmlElement(namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd", required = true)
    protected SResultDescription sResult;
    @XmlElement(name = "_master_phone_id", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd", required = true)
    protected String masterPhoneId;
    @XmlElement(name = "_dummy_account", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd", required = true)
    protected String dummyAccount;
    @XmlElement(name = "_real_account", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd", required = true)
    protected String realAccount;
    @XmlElement(name = "_new_max_balance", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected long newMaxBalance;
    @XmlElement(name = "_new_max_validity", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected long newMaxValidity;
    @XmlElement(name = "_new_default_days", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected long newDefaultDays;
    @XmlElement(name = "_old_max_balance", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected Long oldMaxBalance;
    @XmlElement(name = "_old_max_validity", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected Long oldMaxValidity;
    @XmlElement(name = "_old_default_days", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected Long oldDefaultDays;
    @XmlElement(name = "_valid_date", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar validDate;

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
     * Gets the value of the masterPhoneId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMasterPhoneId() {
        return masterPhoneId;
    }

    /**
     * Sets the value of the masterPhoneId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMasterPhoneId(String value) {
        this.masterPhoneId = value;
    }

    /**
     * Gets the value of the dummyAccount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDummyAccount() {
        return dummyAccount;
    }

    /**
     * Sets the value of the dummyAccount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDummyAccount(String value) {
        this.dummyAccount = value;
    }

    /**
     * Gets the value of the realAccount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRealAccount() {
        return realAccount;
    }

    /**
     * Sets the value of the realAccount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRealAccount(String value) {
        this.realAccount = value;
    }

    /**
     * Gets the value of the newMaxBalance property.
     * 
     */
    public long getNewMaxBalance() {
        return newMaxBalance;
    }

    /**
     * Sets the value of the newMaxBalance property.
     * 
     */
    public void setNewMaxBalance(long value) {
        this.newMaxBalance = value;
    }

    /**
     * Gets the value of the newMaxValidity property.
     * 
     */
    public long getNewMaxValidity() {
        return newMaxValidity;
    }

    /**
     * Sets the value of the newMaxValidity property.
     * 
     */
    public void setNewMaxValidity(long value) {
        this.newMaxValidity = value;
    }

    /**
     * Gets the value of the newDefaultDays property.
     * 
     */
    public long getNewDefaultDays() {
        return newDefaultDays;
    }

    /**
     * Sets the value of the newDefaultDays property.
     * 
     */
    public void setNewDefaultDays(long value) {
        this.newDefaultDays = value;
    }

    /**
     * Gets the value of the oldMaxBalance property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getOldMaxBalance() {
        return oldMaxBalance;
    }

    /**
     * Sets the value of the oldMaxBalance property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setOldMaxBalance(Long value) {
        this.oldMaxBalance = value;
    }

    /**
     * Gets the value of the oldMaxValidity property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getOldMaxValidity() {
        return oldMaxValidity;
    }

    /**
     * Sets the value of the oldMaxValidity property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setOldMaxValidity(Long value) {
        this.oldMaxValidity = value;
    }

    /**
     * Gets the value of the oldDefaultDays property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getOldDefaultDays() {
        return oldDefaultDays;
    }

    /**
     * Sets the value of the oldDefaultDays property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setOldDefaultDays(Long value) {
        this.oldDefaultDays = value;
    }

    /**
     * Gets the value of the validDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getValidDate() {
        return validDate;
    }

    /**
     * Sets the value of the validDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setValidDate(XMLGregorianCalendar value) {
        this.validDate = value;
    }

}
