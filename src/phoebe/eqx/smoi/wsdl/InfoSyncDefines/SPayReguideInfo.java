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
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for SPayReguideInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SPayReguideInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="_pay_cust_id" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="50"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="_pay_acct_id" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="50"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="_pay_phone_id" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="30"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="_priority" type="{http://www.w3.org/2001/XMLSchema}short" minOccurs="0"/>
 *         &lt;element name="_part_type">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}long">
 *               &lt;enumeration value="0"/>
 *               &lt;enumeration value="1"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="_part_value" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="_max_value" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="_valid_date" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="_expire_date" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="_B_number" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="128"/>
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
@XmlType(name = "SPayReguideInfo", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd", propOrder = {
    "payCustId",
    "payAcctId",
    "payPhoneId",
    "priority",
    "partType",
    "partValue",
    "maxValue",
    "validDate",
    "expireDate",
    "bNumber"
})
public class SPayReguideInfo {

    @XmlElement(name = "_pay_cust_id", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected String payCustId;
    @XmlElement(name = "_pay_acct_id", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected String payAcctId;
    @XmlElement(name = "_pay_phone_id", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected String payPhoneId;
    @XmlElement(name = "_priority", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected Short priority;
    @XmlElement(name = "_part_type", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected long partType;
    @XmlElement(name = "_part_value", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected Long partValue;
    @XmlElement(name = "_max_value", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected Long maxValue;
    @XmlElement(name = "_valid_date", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar validDate;
    @XmlElement(name = "_expire_date", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar expireDate;
    @XmlElement(name = "_B_number", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected String bNumber;

    /**
     * Gets the value of the payCustId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPayCustId() {
        return payCustId;
    }

    /**
     * Sets the value of the payCustId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPayCustId(String value) {
        this.payCustId = value;
    }

    /**
     * Gets the value of the payAcctId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPayAcctId() {
        return payAcctId;
    }

    /**
     * Sets the value of the payAcctId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPayAcctId(String value) {
        this.payAcctId = value;
    }

    /**
     * Gets the value of the payPhoneId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPayPhoneId() {
        return payPhoneId;
    }

    /**
     * Sets the value of the payPhoneId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPayPhoneId(String value) {
        this.payPhoneId = value;
    }

    /**
     * Gets the value of the priority property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getPriority() {
        return priority;
    }

    /**
     * Sets the value of the priority property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setPriority(Short value) {
        this.priority = value;
    }

    /**
     * Gets the value of the partType property.
     * 
     */
    public long getPartType() {
        return partType;
    }

    /**
     * Sets the value of the partType property.
     * 
     */
    public void setPartType(long value) {
        this.partType = value;
    }

    /**
     * Gets the value of the partValue property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getPartValue() {
        return partValue;
    }

    /**
     * Sets the value of the partValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setPartValue(Long value) {
        this.partValue = value;
    }

    /**
     * Gets the value of the maxValue property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getMaxValue() {
        return maxValue;
    }

    /**
     * Sets the value of the maxValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setMaxValue(Long value) {
        this.maxValue = value;
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

    /**
     * Gets the value of the expireDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getExpireDate() {
        return expireDate;
    }

    /**
     * Sets the value of the expireDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setExpireDate(XMLGregorianCalendar value) {
        this.expireDate = value;
    }

    /**
     * Gets the value of the bNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBNumber() {
        return bNumber;
    }

    /**
     * Sets the value of the bNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBNumber(String value) {
        this.bNumber = value;
    }

}
