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
 * <p>Java class for Billinfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Billinfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="_invoice_no" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="64"/>
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
 *         &lt;element name="_pay_acct_id">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="50"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="_user_id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="_phone_id" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="30"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="_bill_cycle_no" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="32"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="_cycle_begin_date" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="_cycle_end_date" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="_unpay_fee" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="_discount_fee" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="_adjust_fee" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="_prim_fee" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="BillItemList" type="{http://www.asiainfo.com/obd/CommonComponents.obd}BillItemList"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Billinfo", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd", propOrder = {
    "invoiceNo",
    "custId",
    "acctId",
    "payAcctId",
    "userId",
    "phoneId",
    "billCycleNo",
    "cycleBeginDate",
    "cycleEndDate",
    "unpayFee",
    "discountFee",
    "adjustFee",
    "primFee",
    "billItemList"
})
public class Billinfo {

    @XmlElement(name = "_invoice_no", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected String invoiceNo;
    @XmlElement(name = "_cust_id", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected String custId;
    @XmlElement(name = "_acct_id", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected String acctId;
    @XmlElement(name = "_pay_acct_id", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd", required = true)
    protected String payAcctId;
    @XmlElement(name = "_user_id", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected Long userId;
    @XmlElement(name = "_phone_id", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected String phoneId;
    @XmlElement(name = "_bill_cycle_no", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected String billCycleNo;
    @XmlElement(name = "_cycle_begin_date", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar cycleBeginDate;
    @XmlElement(name = "_cycle_end_date", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar cycleEndDate;
    @XmlElement(name = "_unpay_fee", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected Long unpayFee;
    @XmlElement(name = "_discount_fee", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected Long discountFee;
    @XmlElement(name = "_adjust_fee", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected Long adjustFee;
    @XmlElement(name = "_prim_fee", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected Long primFee;
    @XmlElement(name = "BillItemList", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd", required = true)
    protected BillItemList billItemList;

    /**
     * Gets the value of the invoiceNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInvoiceNo() {
        return invoiceNo;
    }

    /**
     * Sets the value of the invoiceNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInvoiceNo(String value) {
        this.invoiceNo = value;
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
     * Gets the value of the billCycleNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBillCycleNo() {
        return billCycleNo;
    }

    /**
     * Sets the value of the billCycleNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBillCycleNo(String value) {
        this.billCycleNo = value;
    }

    /**
     * Gets the value of the cycleBeginDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCycleBeginDate() {
        return cycleBeginDate;
    }

    /**
     * Sets the value of the cycleBeginDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCycleBeginDate(XMLGregorianCalendar value) {
        this.cycleBeginDate = value;
    }

    /**
     * Gets the value of the cycleEndDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCycleEndDate() {
        return cycleEndDate;
    }

    /**
     * Sets the value of the cycleEndDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCycleEndDate(XMLGregorianCalendar value) {
        this.cycleEndDate = value;
    }

    /**
     * Gets the value of the unpayFee property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getUnpayFee() {
        return unpayFee;
    }

    /**
     * Sets the value of the unpayFee property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setUnpayFee(Long value) {
        this.unpayFee = value;
    }

    /**
     * Gets the value of the discountFee property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getDiscountFee() {
        return discountFee;
    }

    /**
     * Sets the value of the discountFee property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setDiscountFee(Long value) {
        this.discountFee = value;
    }

    /**
     * Gets the value of the adjustFee property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getAdjustFee() {
        return adjustFee;
    }

    /**
     * Sets the value of the adjustFee property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setAdjustFee(Long value) {
        this.adjustFee = value;
    }

    /**
     * Gets the value of the primFee property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getPrimFee() {
        return primFee;
    }

    /**
     * Sets the value of the primFee property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setPrimFee(Long value) {
        this.primFee = value;
    }

    /**
     * Gets the value of the billItemList property.
     * 
     * @return
     *     possible object is
     *     {@link BillItemList }
     *     
     */
    public BillItemList getBillItemList() {
        return billItemList;
    }

    /**
     * Sets the value of the billItemList property.
     * 
     * @param value
     *     allowed object is
     *     {@link BillItemList }
     *     
     */
    public void setBillItemList(BillItemList value) {
        this.billItemList = value;
    }

}
