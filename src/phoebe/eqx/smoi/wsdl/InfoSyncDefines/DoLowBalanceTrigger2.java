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
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DoLowBalanceTrigger complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DoLowBalanceTrigger">
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
 *         &lt;element name="_cust_id" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="50"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="_acct_id" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="50"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="_phone_id">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="30"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="_auto_type" type="{http://www.w3.org/2001/XMLSchema}short" minOccurs="0"/>
 *         &lt;element name="_pay_channel" type="{http://www.w3.org/2001/XMLSchema}short" minOccurs="0"/>
 *         &lt;element name="_low_balance_amount" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="_current_balance_amount" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="_topup_amount" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DoLowBalanceTrigger", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd", propOrder = {
    "workOrderType",
    "transactionId",
    "custId",
    "acctId",
    "phoneId",
    "autoType",
    "payChannel",
    "lowBalanceAmount",
    "currentBalanceAmount",
    "topupAmount"
})
public class DoLowBalanceTrigger2 {

    @XmlElement(name = "WorkOrderType", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected int workOrderType;
    @XmlElement(name = "_transaction_id", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd", required = true)
    protected String transactionId;
    @XmlElement(name = "_cust_id", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected String custId;
    @XmlElement(name = "_acct_id", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected String acctId;
    @XmlElement(name = "_phone_id", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd", required = true)
    protected String phoneId;
    @XmlElement(name = "_auto_type", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected Short autoType;
    @XmlElement(name = "_pay_channel", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected Short payChannel;
    @XmlElement(name = "_low_balance_amount", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected Long lowBalanceAmount;
    @XmlElement(name = "_current_balance_amount", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected Long currentBalanceAmount;
    @XmlElement(name = "_topup_amount", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected Long topupAmount;

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
     * Gets the value of the custId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustId() {
        return custId;
    }

    /**
     * Sets the value of the custId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustId(String value) {
        this.custId = value;
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
     * Gets the value of the autoType property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getAutoType() {
        return autoType;
    }

    /**
     * Sets the value of the autoType property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setAutoType(Short value) {
        this.autoType = value;
    }

    /**
     * Gets the value of the payChannel property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getPayChannel() {
        return payChannel;
    }

    /**
     * Sets the value of the payChannel property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setPayChannel(Short value) {
        this.payChannel = value;
    }

    /**
     * Gets the value of the lowBalanceAmount property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getLowBalanceAmount() {
        return lowBalanceAmount;
    }

    /**
     * Sets the value of the lowBalanceAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setLowBalanceAmount(Long value) {
        this.lowBalanceAmount = value;
    }

    /**
     * Gets the value of the currentBalanceAmount property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getCurrentBalanceAmount() {
        return currentBalanceAmount;
    }

    /**
     * Sets the value of the currentBalanceAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setCurrentBalanceAmount(Long value) {
        this.currentBalanceAmount = value;
    }

    /**
     * Gets the value of the topupAmount property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getTopupAmount() {
        return topupAmount;
    }

    /**
     * Sets the value of the topupAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setTopupAmount(Long value) {
        this.topupAmount = value;
    }

}
