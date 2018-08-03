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
 * <p>Java class for DoOverUsageLimit complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DoOverUsageLimit">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="WorkOrderType" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="_transaction_id">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="32"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="_acct_id">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="50"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="_flag" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *         &lt;element name="_old_status" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="10"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="_old_sub_type" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}short">
 *               &lt;enumeration value="0"/>
 *               &lt;enumeration value="1"/>
 *               &lt;enumeration value="2"/>
 *               &lt;enumeration value="3"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="_new_status" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="10"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="_new_sub_type" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}short">
 *               &lt;enumeration value="0"/>
 *               &lt;enumeration value="1"/>
 *               &lt;enumeration value="2"/>
 *               &lt;enumeration value="3"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="_credit_limit" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="_temp_credit_limit" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="_over_usage" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="_trigger_date" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DoOverUsageLimit", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd", propOrder = {
    "workOrderType",
    "transactionId",
    "acctId",
    "flag",
    "oldStatus",
    "oldSubType",
    "newStatus",
    "newSubType",
    "creditLimit",
    "tempCreditLimit",
    "overUsage",
    "triggerDate"
})
public class DoOverUsageLimit2 {

    @XmlElement(name = "WorkOrderType", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected int workOrderType;
    @XmlElement(name = "_transaction_id", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd", required = true)
    protected String transactionId;
    @XmlElement(name = "_acct_id", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd", required = true)
    protected String acctId;
    @XmlElement(name = "_flag", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected short flag;
    @XmlElement(name = "_old_status", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected String oldStatus;
    @XmlElement(name = "_old_sub_type", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected Short oldSubType;
    @XmlElement(name = "_new_status", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected String newStatus;
    @XmlElement(name = "_new_sub_type", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected Short newSubType;
    @XmlElement(name = "_credit_limit", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected Long creditLimit;
    @XmlElement(name = "_temp_credit_limit", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected Long tempCreditLimit;
    @XmlElement(name = "_over_usage", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected Long overUsage;
    @XmlElement(name = "_trigger_date", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar triggerDate;

    /**
     * Gets the value of the workOrderType property.
     * 
     */
    public int getWorkOrderType() {
        return workOrderType;
    }

    /**
     * Sets the value of the workOrderType property.
     * 
     */
    public void setWorkOrderType(int value) {
        this.workOrderType = value;
    }

    /**
     * Gets the value of the transactionId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransactionId() {
        return transactionId;
    }

    /**
     * Sets the value of the transactionId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransactionId(String value) {
        this.transactionId = value;
    }

    /**
     * Gets the value of the acctId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAcctId() {
        return acctId;
    }

    /**
     * Sets the value of the acctId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAcctId(String value) {
        this.acctId = value;
    }

    /**
     * Gets the value of the flag property.
     * 
     */
    public short getFlag() {
        return flag;
    }

    /**
     * Sets the value of the flag property.
     * 
     */
    public void setFlag(short value) {
        this.flag = value;
    }

    /**
     * Gets the value of the oldStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOldStatus() {
        return oldStatus;
    }

    /**
     * Sets the value of the oldStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOldStatus(String value) {
        this.oldStatus = value;
    }

    /**
     * Gets the value of the oldSubType property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getOldSubType() {
        return oldSubType;
    }

    /**
     * Sets the value of the oldSubType property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setOldSubType(Short value) {
        this.oldSubType = value;
    }

    /**
     * Gets the value of the newStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNewStatus() {
        return newStatus;
    }

    /**
     * Sets the value of the newStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNewStatus(String value) {
        this.newStatus = value;
    }

    /**
     * Gets the value of the newSubType property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getNewSubType() {
        return newSubType;
    }

    /**
     * Sets the value of the newSubType property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setNewSubType(Short value) {
        this.newSubType = value;
    }

    /**
     * Gets the value of the creditLimit property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getCreditLimit() {
        return creditLimit;
    }

    /**
     * Sets the value of the creditLimit property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setCreditLimit(Long value) {
        this.creditLimit = value;
    }

    /**
     * Gets the value of the tempCreditLimit property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getTempCreditLimit() {
        return tempCreditLimit;
    }

    /**
     * Sets the value of the tempCreditLimit property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setTempCreditLimit(Long value) {
        this.tempCreditLimit = value;
    }

    /**
     * Gets the value of the overUsage property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getOverUsage() {
        return overUsage;
    }

    /**
     * Sets the value of the overUsage property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setOverUsage(Long value) {
        this.overUsage = value;
    }

    /**
     * Gets the value of the triggerDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getTriggerDate() {
        return triggerDate;
    }

    /**
     * Sets the value of the triggerDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setTriggerDate(XMLGregorianCalendar value) {
        this.triggerDate = value;
    }

}