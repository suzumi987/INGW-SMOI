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
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AcctBasicInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AcctBasicInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SContactOperList" type="{http://www.asiainfo.com/obd/CommonComponents.obd}SContactOperList" minOccurs="0"/>
 *         &lt;element name="SPayChannel" type="{http://www.asiainfo.com/obd/CommonComponents.obd}SPayChannel" minOccurs="0"/>
 *         &lt;element name="_acct_id">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="50"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="_acct_name" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="255"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="_payment_currency" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="50"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="_due_day" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="30"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="_PrintBill" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="50"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="_AttrAcctMobile" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="30"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="_bill_language_id" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="50"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="_AcctBMCallDetail" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="50"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="_AcctBMSummary" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="50"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="_EmailAcctBMCall" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="255"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="_EmailAcctBMSummary" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="255"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="_AttrAcctEmail" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="255"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="_InvoicingCoID" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="50"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="_InvoiceType" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="30"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="_hierarchybillingflag" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="10"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="sBillCycle" type="{http://www.asiainfo.com/obd/CommonComponents.obd}SBillCycle" minOccurs="0"/>
 *         &lt;element name="_BillStyleId" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="50"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="_faxBillTo" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="64"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="_fax_acc_bm_call" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="255"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="_fax_acc_bm_sum" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="255"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="_special_acct" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="50"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="_charge_type" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="50"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="_SMSBillTo" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="32"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="_billhandlingcode" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="50"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="_EmailBillTo" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="64"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="_bill_name" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="128"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="_vat_name" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="255"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="_BudgetofCopyBilled" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AcctBasicInfo", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd", propOrder = {
    "sContactOperList",
    "sPayChannel",
    "acctId",
    "acctName",
    "paymentCurrency",
    "dueDay",
    "printBill",
    "attrAcctMobile",
    "billLanguageId",
    "acctBMCallDetail",
    "acctBMSummary",
    "emailAcctBMCall",
    "emailAcctBMSummary",
    "attrAcctEmail",
    "invoicingCoID",
    "invoiceType",
    "hierarchybillingflag",
    "sBillCycle",
    "billStyleId",
    "faxBillTo",
    "faxAccBmCall",
    "faxAccBmSum",
    "specialAcct",
    "chargeType",
    "smsBillTo",
    "billhandlingcode",
    "emailBillTo",
    "billName",
    "vatName",
    "budgetofCopyBilled"
})
public class AcctBasicInfo {

    @XmlElement(name = "SContactOperList", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected SContactOperList sContactOperList;
    @XmlElement(name = "SPayChannel", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected SPayChannel sPayChannel;
    @XmlElement(name = "_acct_id", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd", required = true)
    protected String acctId;
    @XmlElement(name = "_acct_name", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected String acctName;
    @XmlElement(name = "_payment_currency", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected String paymentCurrency;
    @XmlElement(name = "_due_day", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected String dueDay;
    @XmlElement(name = "_PrintBill", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected String printBill;
    @XmlElement(name = "_AttrAcctMobile", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected String attrAcctMobile;
    @XmlElement(name = "_bill_language_id", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected String billLanguageId;
    @XmlElement(name = "_AcctBMCallDetail", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected String acctBMCallDetail;
    @XmlElement(name = "_AcctBMSummary", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected String acctBMSummary;
    @XmlElement(name = "_EmailAcctBMCall", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected String emailAcctBMCall;
    @XmlElement(name = "_EmailAcctBMSummary", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected String emailAcctBMSummary;
    @XmlElement(name = "_AttrAcctEmail", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected String attrAcctEmail;
    @XmlElement(name = "_InvoicingCoID", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected String invoicingCoID;
    @XmlElement(name = "_InvoiceType", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected String invoiceType;
    @XmlElement(name = "_hierarchybillingflag", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected String hierarchybillingflag;
    @XmlElement(namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected SBillCycle sBillCycle;
    @XmlElement(name = "_BillStyleId", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected String billStyleId;
    @XmlElement(name = "_faxBillTo", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected String faxBillTo;
    @XmlElement(name = "_fax_acc_bm_call", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected String faxAccBmCall;
    @XmlElement(name = "_fax_acc_bm_sum", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected String faxAccBmSum;
    @XmlElement(name = "_special_acct", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected String specialAcct;
    @XmlElement(name = "_charge_type", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected String chargeType;
    @XmlElement(name = "_SMSBillTo", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected String smsBillTo;
    @XmlElement(name = "_billhandlingcode", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected String billhandlingcode;
    @XmlElement(name = "_EmailBillTo", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected String emailBillTo;
    @XmlElement(name = "_bill_name", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected String billName;
    @XmlElement(name = "_vat_name", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected String vatName;
    @XmlElement(name = "_BudgetofCopyBilled", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected Long budgetofCopyBilled;

    /**
     * Gets the value of the sContactOperList property.
     * 
     * @return
     *     possible object is
     *     {@link SContactOperList }
     *     
     */
    public SContactOperList getSContactOperList() {
        return sContactOperList;
    }

    /**
     * Sets the value of the sContactOperList property.
     * 
     * @param value
     *     allowed object is
     *     {@link SContactOperList }
     *     
     */
    public void setSContactOperList(SContactOperList value) {
        this.sContactOperList = value;
    }

    /**
     * Gets the value of the sPayChannel property.
     * 
     * @return
     *     possible object is
     *     {@link SPayChannel }
     *     
     */
    public SPayChannel getSPayChannel() {
        return sPayChannel;
    }

    /**
     * Sets the value of the sPayChannel property.
     * 
     * @param value
     *     allowed object is
     *     {@link SPayChannel }
     *     
     */
    public void setSPayChannel(SPayChannel value) {
        this.sPayChannel = value;
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
     * Gets the value of the acctName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAcctName() {
        return acctName;
    }

    /**
     * Sets the value of the acctName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAcctName(String value) {
        this.acctName = value;
    }

    /**
     * Gets the value of the paymentCurrency property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaymentCurrency() {
        return paymentCurrency;
    }

    /**
     * Sets the value of the paymentCurrency property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaymentCurrency(String value) {
        this.paymentCurrency = value;
    }

    /**
     * Gets the value of the dueDay property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDueDay() {
        return dueDay;
    }

    /**
     * Sets the value of the dueDay property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDueDay(String value) {
        this.dueDay = value;
    }

    /**
     * Gets the value of the printBill property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrintBill() {
        return printBill;
    }

    /**
     * Sets the value of the printBill property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrintBill(String value) {
        this.printBill = value;
    }

    /**
     * Gets the value of the attrAcctMobile property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAttrAcctMobile() {
        return attrAcctMobile;
    }

    /**
     * Sets the value of the attrAcctMobile property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAttrAcctMobile(String value) {
        this.attrAcctMobile = value;
    }

    /**
     * Gets the value of the billLanguageId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBillLanguageId() {
        return billLanguageId;
    }

    /**
     * Sets the value of the billLanguageId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBillLanguageId(String value) {
        this.billLanguageId = value;
    }

    /**
     * Gets the value of the acctBMCallDetail property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAcctBMCallDetail() {
        return acctBMCallDetail;
    }

    /**
     * Sets the value of the acctBMCallDetail property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAcctBMCallDetail(String value) {
        this.acctBMCallDetail = value;
    }

    /**
     * Gets the value of the acctBMSummary property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAcctBMSummary() {
        return acctBMSummary;
    }

    /**
     * Sets the value of the acctBMSummary property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAcctBMSummary(String value) {
        this.acctBMSummary = value;
    }

    /**
     * Gets the value of the emailAcctBMCall property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmailAcctBMCall() {
        return emailAcctBMCall;
    }

    /**
     * Sets the value of the emailAcctBMCall property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmailAcctBMCall(String value) {
        this.emailAcctBMCall = value;
    }

    /**
     * Gets the value of the emailAcctBMSummary property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmailAcctBMSummary() {
        return emailAcctBMSummary;
    }

    /**
     * Sets the value of the emailAcctBMSummary property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmailAcctBMSummary(String value) {
        this.emailAcctBMSummary = value;
    }

    /**
     * Gets the value of the attrAcctEmail property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAttrAcctEmail() {
        return attrAcctEmail;
    }

    /**
     * Sets the value of the attrAcctEmail property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAttrAcctEmail(String value) {
        this.attrAcctEmail = value;
    }

    /**
     * Gets the value of the invoicingCoID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInvoicingCoID() {
        return invoicingCoID;
    }

    /**
     * Sets the value of the invoicingCoID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInvoicingCoID(String value) {
        this.invoicingCoID = value;
    }

    /**
     * Gets the value of the invoiceType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInvoiceType() {
        return invoiceType;
    }

    /**
     * Sets the value of the invoiceType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInvoiceType(String value) {
        this.invoiceType = value;
    }

    /**
     * Gets the value of the hierarchybillingflag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHierarchybillingflag() {
        return hierarchybillingflag;
    }

    /**
     * Sets the value of the hierarchybillingflag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHierarchybillingflag(String value) {
        this.hierarchybillingflag = value;
    }

    /**
     * Gets the value of the sBillCycle property.
     * 
     * @return
     *     possible object is
     *     {@link SBillCycle }
     *     
     */
    public SBillCycle getSBillCycle() {
        return sBillCycle;
    }

    /**
     * Sets the value of the sBillCycle property.
     * 
     * @param value
     *     allowed object is
     *     {@link SBillCycle }
     *     
     */
    public void setSBillCycle(SBillCycle value) {
        this.sBillCycle = value;
    }

    /**
     * Gets the value of the billStyleId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBillStyleId() {
        return billStyleId;
    }

    /**
     * Sets the value of the billStyleId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBillStyleId(String value) {
        this.billStyleId = value;
    }

    /**
     * Gets the value of the faxBillTo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFaxBillTo() {
        return faxBillTo;
    }

    /**
     * Sets the value of the faxBillTo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFaxBillTo(String value) {
        this.faxBillTo = value;
    }

    /**
     * Gets the value of the faxAccBmCall property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFaxAccBmCall() {
        return faxAccBmCall;
    }

    /**
     * Sets the value of the faxAccBmCall property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFaxAccBmCall(String value) {
        this.faxAccBmCall = value;
    }

    /**
     * Gets the value of the faxAccBmSum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFaxAccBmSum() {
        return faxAccBmSum;
    }

    /**
     * Sets the value of the faxAccBmSum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFaxAccBmSum(String value) {
        this.faxAccBmSum = value;
    }

    /**
     * Gets the value of the specialAcct property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSpecialAcct() {
        return specialAcct;
    }

    /**
     * Sets the value of the specialAcct property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSpecialAcct(String value) {
        this.specialAcct = value;
    }

    /**
     * Gets the value of the chargeType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChargeType() {
        return chargeType;
    }

    /**
     * Sets the value of the chargeType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChargeType(String value) {
        this.chargeType = value;
    }

    /**
     * Gets the value of the smsBillTo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSMSBillTo() {
        return smsBillTo;
    }

    /**
     * Sets the value of the smsBillTo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSMSBillTo(String value) {
        this.smsBillTo = value;
    }

    /**
     * Gets the value of the billhandlingcode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBillhandlingcode() {
        return billhandlingcode;
    }

    /**
     * Sets the value of the billhandlingcode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBillhandlingcode(String value) {
        this.billhandlingcode = value;
    }

    /**
     * Gets the value of the emailBillTo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmailBillTo() {
        return emailBillTo;
    }

    /**
     * Sets the value of the emailBillTo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmailBillTo(String value) {
        this.emailBillTo = value;
    }

    /**
     * Gets the value of the billName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBillName() {
        return billName;
    }

    /**
     * Sets the value of the billName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBillName(String value) {
        this.billName = value;
    }

    /**
     * Gets the value of the vatName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVatName() {
        return vatName;
    }

    /**
     * Sets the value of the vatName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVatName(String value) {
        this.vatName = value;
    }

    /**
     * Gets the value of the budgetofCopyBilled property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getBudgetofCopyBilled() {
        return budgetofCopyBilled;
    }

    /**
     * Sets the value of the budgetofCopyBilled property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setBudgetofCopyBilled(Long value) {
        this.budgetofCopyBilled = value;
    }

}
