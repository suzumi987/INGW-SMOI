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
 * <p>Java class for DoSystemBarService complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DoSystemBarService">
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
 *         &lt;element name="_action" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *         &lt;element name="_acct_id" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="50"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="_user_id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="_phone_id">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="30"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="_service_id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="_service_name">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="100"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="_reason_code" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *         &lt;element name="_action_date" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DoSystemBarService", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd", propOrder = {
    "workOrderType",
    "transactionId",
    "action",
    "acctId",
    "userId",
    "phoneId",
    "serviceId",
    "serviceName",
    "reasonCode",
    "actionDate"
})
public class DoSystemBarService2 {

    @XmlElement(name = "WorkOrderType", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected int workOrderType;
    @XmlElement(name = "_transaction_id", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd", required = true)
    protected String transactionId;
    @XmlElement(name = "_action", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected short action;
    @XmlElement(name = "_acct_id", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected String acctId;
    @XmlElement(name = "_user_id", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected Long userId;
    @XmlElement(name = "_phone_id", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd", required = true)
    protected String phoneId;
    @XmlElement(name = "_service_id", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected int serviceId;
    @XmlElement(name = "_service_name", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd", required = true)
    protected String serviceName;
    @XmlElement(name = "_reason_code", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected short reasonCode;
    @XmlElement(name = "_action_date", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar actionDate;

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
     * Gets the value of the action property.
     * 
     */
    public short getAction() {
        return action;
    }

    /**
     * Sets the value of the action property.
     * 
     */
    public void setAction(short value) {
        this.action = value;
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
     * Gets the value of the serviceId property.
     * 
     */
    public int getServiceId() {
        return serviceId;
    }

    /**
     * Sets the value of the serviceId property.
     * 
     */
    public void setServiceId(int value) {
        this.serviceId = value;
    }

    /**
     * Gets the value of the serviceName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceName() {
        return serviceName;
    }

    /**
     * Sets the value of the serviceName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceName(String value) {
        this.serviceName = value;
    }

    /**
     * Gets the value of the reasonCode property.
     * 
     */
    public short getReasonCode() {
        return reasonCode;
    }

    /**
     * Sets the value of the reasonCode property.
     * 
     */
    public void setReasonCode(short value) {
        this.reasonCode = value;
    }

    /**
     * Gets the value of the actionDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getActionDate() {
        return actionDate;
    }

    /**
     * Sets the value of the actionDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setActionDate(XMLGregorianCalendar value) {
        this.actionDate = value;
    }

}