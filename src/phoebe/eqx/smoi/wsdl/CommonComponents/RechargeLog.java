//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.04.01 at 04:26:27 PM ICT 
//


package phoebe.eqx.smoi.wsdl.CommonComponents;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for RechargeLog complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RechargeLog">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="_phone_id" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="30"/>
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
 *         &lt;element name="_status">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="10"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="_recharge_time" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="_amount" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="_recharge_method" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}short">
 *               &lt;enumeration value="1"/>
 *               &lt;enumeration value="2"/>
 *               &lt;enumeration value="3"/>
 *               &lt;enumeration value="4"/>
 *               &lt;enumeration value="5"/>
 *               &lt;enumeration value="6"/>
 *               &lt;enumeration value="7"/>
 *               &lt;enumeration value="8"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="_recharge_partner_id" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="20"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="_recharge_service_id" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="_service_name" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="255"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="_service_name_eng" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="255"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="_payment_mode" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="50"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="_remark" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="255"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="_serial_no" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="64"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="_batch_no" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="64"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="OldBalance" type="{http://www.asiainfo.com/obd/CommonComponents.obd}SBalanceList" minOccurs="0"/>
 *         &lt;element name="NewBalance" type="{http://www.asiainfo.com/obd/CommonComponents.obd}SBalanceList" minOccurs="0"/>
 *         &lt;element name="_so_mode" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="30"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="_source_system" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="32"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="_transaction_id" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="64"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="_fail_reason" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="255"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="ProductOrderResultList" type="{http://www.asiainfo.com/obd/CommonComponents.obd}SProductOrderResultList" minOccurs="0"/>
 *         &lt;element name="FreeResourceGroupList" type="{http://www.asiainfo.com/obd/CommonComponents.obd}FreeResourceGroupList" minOccurs="0"/>
 *         &lt;element name="_validity_period" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="_extra_validity" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="_etopup_session_id" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="8"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="_operation_date" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RechargeLog", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd", propOrder = {
    "phoneId",
    "acctId",
    "status",
    "rechargeTime",
    "amount",
    "rechargeMethod",
    "rechargePartnerId",
    "rechargeServiceId",
    "serviceName",
    "serviceNameEng",
    "paymentMode",
    "remark",
    "serialNo",
    "batchNo",
    "oldBalance",
    "newBalance",
    "soMode",
    "sourceSystem",
    "transactionId",
    "failReason",
    "productOrderResultList",
    "freeResourceGroupList",
    "validityPeriod",
    "extraValidity",
    "etopupSessionId",
    "operationDate"
})
public class RechargeLog {

    @XmlElement(name = "_phone_id", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected String phoneId;
    @XmlElement(name = "_acct_id", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd", required = true)
    protected String acctId;
    @XmlElement(name = "_status", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd", required = true)
    protected String status;
    @XmlElement(name = "_recharge_time", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar rechargeTime;
    @XmlElement(name = "_amount", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected long amount;
    @XmlElement(name = "_recharge_method", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected Short rechargeMethod;
    @XmlElement(name = "_recharge_partner_id", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected String rechargePartnerId;
    @XmlElement(name = "_recharge_service_id", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected Integer rechargeServiceId;
    @XmlElement(name = "_service_name", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected String serviceName;
    @XmlElement(name = "_service_name_eng", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected String serviceNameEng;
    @XmlElement(name = "_payment_mode", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected String paymentMode;
    @XmlElement(name = "_remark", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected String remark;
    @XmlElement(name = "_serial_no", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected String serialNo;
    @XmlElement(name = "_batch_no", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected String batchNo;
    @XmlElement(name = "OldBalance", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected SBalanceList oldBalance;
    @XmlElement(name = "NewBalance", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected SBalanceList newBalance;
    @XmlElement(name = "_so_mode", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected String soMode;
    @XmlElement(name = "_source_system", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected String sourceSystem;
    @XmlElement(name = "_transaction_id", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected String transactionId;
    @XmlElement(name = "_fail_reason", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected String failReason;
    @XmlElement(name = "ProductOrderResultList", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected SProductOrderResultList productOrderResultList;
    @XmlElement(name = "FreeResourceGroupList", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected FreeResourceGroupList freeResourceGroupList;
    @XmlElement(name = "_validity_period", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected Integer validityPeriod;
    @XmlElement(name = "_extra_validity", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected Integer extraValidity;
    @XmlElement(name = "_etopup_session_id", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected String etopupSessionId;
    @XmlElement(name = "_operation_date", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar operationDate;

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
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatus(String value) {
        this.status = value;
    }

    /**
     * Gets the value of the rechargeTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getRechargeTime() {
        return rechargeTime;
    }

    /**
     * Sets the value of the rechargeTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setRechargeTime(XMLGregorianCalendar value) {
        this.rechargeTime = value;
    }

    /**
     * Gets the value of the amount property.
     * 
     */
    public long getAmount() {
        return amount;
    }

    /**
     * Sets the value of the amount property.
     * 
     */
    public void setAmount(long value) {
        this.amount = value;
    }

    /**
     * Gets the value of the rechargeMethod property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getRechargeMethod() {
        return rechargeMethod;
    }

    /**
     * Sets the value of the rechargeMethod property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setRechargeMethod(Short value) {
        this.rechargeMethod = value;
    }

    /**
     * Gets the value of the rechargePartnerId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRechargePartnerId() {
        return rechargePartnerId;
    }

    /**
     * Sets the value of the rechargePartnerId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRechargePartnerId(String value) {
        this.rechargePartnerId = value;
    }

    /**
     * Gets the value of the rechargeServiceId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getRechargeServiceId() {
        return rechargeServiceId;
    }

    /**
     * Sets the value of the rechargeServiceId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setRechargeServiceId(Integer value) {
        this.rechargeServiceId = value;
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
     * Gets the value of the serviceNameEng property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceNameEng() {
        return serviceNameEng;
    }

    /**
     * Sets the value of the serviceNameEng property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceNameEng(String value) {
        this.serviceNameEng = value;
    }

    /**
     * Gets the value of the paymentMode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaymentMode() {
        return paymentMode;
    }

    /**
     * Sets the value of the paymentMode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaymentMode(String value) {
        this.paymentMode = value;
    }

    /**
     * Gets the value of the remark property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRemark() {
        return remark;
    }

    /**
     * Sets the value of the remark property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRemark(String value) {
        this.remark = value;
    }

    /**
     * Gets the value of the serialNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSerialNo() {
        return serialNo;
    }

    /**
     * Sets the value of the serialNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSerialNo(String value) {
        this.serialNo = value;
    }

    /**
     * Gets the value of the batchNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBatchNo() {
        return batchNo;
    }

    /**
     * Sets the value of the batchNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBatchNo(String value) {
        this.batchNo = value;
    }

    /**
     * Gets the value of the oldBalance property.
     * 
     * @return
     *     possible object is
     *     {@link SBalanceList }
     *     
     */
    public SBalanceList getOldBalance() {
        return oldBalance;
    }

    /**
     * Sets the value of the oldBalance property.
     * 
     * @param value
     *     allowed object is
     *     {@link SBalanceList }
     *     
     */
    public void setOldBalance(SBalanceList value) {
        this.oldBalance = value;
    }

    /**
     * Gets the value of the newBalance property.
     * 
     * @return
     *     possible object is
     *     {@link SBalanceList }
     *     
     */
    public SBalanceList getNewBalance() {
        return newBalance;
    }

    /**
     * Sets the value of the newBalance property.
     * 
     * @param value
     *     allowed object is
     *     {@link SBalanceList }
     *     
     */
    public void setNewBalance(SBalanceList value) {
        this.newBalance = value;
    }

    /**
     * Gets the value of the soMode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSoMode() {
        return soMode;
    }

    /**
     * Sets the value of the soMode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSoMode(String value) {
        this.soMode = value;
    }

    /**
     * Gets the value of the sourceSystem property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSourceSystem() {
        return sourceSystem;
    }

    /**
     * Sets the value of the sourceSystem property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSourceSystem(String value) {
        this.sourceSystem = value;
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
     * Gets the value of the failReason property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFailReason() {
        return failReason;
    }

    /**
     * Sets the value of the failReason property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFailReason(String value) {
        this.failReason = value;
    }

    /**
     * Gets the value of the productOrderResultList property.
     * 
     * @return
     *     possible object is
     *     {@link SProductOrderResultList }
     *     
     */
    public SProductOrderResultList getProductOrderResultList() {
        return productOrderResultList;
    }

    /**
     * Sets the value of the productOrderResultList property.
     * 
     * @param value
     *     allowed object is
     *     {@link SProductOrderResultList }
     *     
     */
    public void setProductOrderResultList(SProductOrderResultList value) {
        this.productOrderResultList = value;
    }

    /**
     * Gets the value of the freeResourceGroupList property.
     * 
     * @return
     *     possible object is
     *     {@link FreeResourceGroupList }
     *     
     */
    public FreeResourceGroupList getFreeResourceGroupList() {
        return freeResourceGroupList;
    }

    /**
     * Sets the value of the freeResourceGroupList property.
     * 
     * @param value
     *     allowed object is
     *     {@link FreeResourceGroupList }
     *     
     */
    public void setFreeResourceGroupList(FreeResourceGroupList value) {
        this.freeResourceGroupList = value;
    }

    /**
     * Gets the value of the validityPeriod property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getValidityPeriod() {
        return validityPeriod;
    }

    /**
     * Sets the value of the validityPeriod property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setValidityPeriod(Integer value) {
        this.validityPeriod = value;
    }

    /**
     * Gets the value of the extraValidity property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getExtraValidity() {
        return extraValidity;
    }

    /**
     * Sets the value of the extraValidity property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setExtraValidity(Integer value) {
        this.extraValidity = value;
    }

    /**
     * Gets the value of the etopupSessionId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEtopupSessionId() {
        return etopupSessionId;
    }

    /**
     * Sets the value of the etopupSessionId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEtopupSessionId(String value) {
        this.etopupSessionId = value;
    }

    /**
     * Gets the value of the operationDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getOperationDate() {
        return operationDate;
    }

    /**
     * Sets the value of the operationDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setOperationDate(XMLGregorianCalendar value) {
        this.operationDate = value;
    }

}