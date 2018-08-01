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
 * <p>Java class for DAAUser complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DAAUser">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="_phone_id">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="30"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="_loan_id">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="30"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="_loan_amount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="_loan_amount_remaining" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="_fee_amount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="_fee_amount_remaining" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="_loan_date" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="_loan_payoff_date" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="_second_balance" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="_second_balance_record_date" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DAAUser", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd", propOrder = {
    "phoneId",
    "loanId",
    "loanAmount",
    "loanAmountRemaining",
    "feeAmount",
    "feeAmountRemaining",
    "loanDate",
    "loanPayoffDate",
    "secondBalance",
    "secondBalanceRecordDate"
})
public class DAAUser {

    @XmlElement(name = "_phone_id", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd", required = true)
    protected String phoneId;
    @XmlElement(name = "_loan_id", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd", required = true)
    protected String loanId;
    @XmlElement(name = "_loan_amount", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected int loanAmount;
    @XmlElement(name = "_loan_amount_remaining", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected int loanAmountRemaining;
    @XmlElement(name = "_fee_amount", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected int feeAmount;
    @XmlElement(name = "_fee_amount_remaining", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected int feeAmountRemaining;
    @XmlElement(name = "_loan_date", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar loanDate;
    @XmlElement(name = "_loan_payoff_date", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar loanPayoffDate;
    @XmlElement(name = "_second_balance", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected Integer secondBalance;
    @XmlElement(name = "_second_balance_record_date", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar secondBalanceRecordDate;

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
     * Gets the value of the loanId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLoanId() {
        return loanId;
    }

    /**
     * Sets the value of the loanId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLoanId(String value) {
        this.loanId = value;
    }

    /**
     * Gets the value of the loanAmount property.
     * 
     */
    public int getLoanAmount() {
        return loanAmount;
    }

    /**
     * Sets the value of the loanAmount property.
     * 
     */
    public void setLoanAmount(int value) {
        this.loanAmount = value;
    }

    /**
     * Gets the value of the loanAmountRemaining property.
     * 
     */
    public int getLoanAmountRemaining() {
        return loanAmountRemaining;
    }

    /**
     * Sets the value of the loanAmountRemaining property.
     * 
     */
    public void setLoanAmountRemaining(int value) {
        this.loanAmountRemaining = value;
    }

    /**
     * Gets the value of the feeAmount property.
     * 
     */
    public int getFeeAmount() {
        return feeAmount;
    }

    /**
     * Sets the value of the feeAmount property.
     * 
     */
    public void setFeeAmount(int value) {
        this.feeAmount = value;
    }

    /**
     * Gets the value of the feeAmountRemaining property.
     * 
     */
    public int getFeeAmountRemaining() {
        return feeAmountRemaining;
    }

    /**
     * Sets the value of the feeAmountRemaining property.
     * 
     */
    public void setFeeAmountRemaining(int value) {
        this.feeAmountRemaining = value;
    }

    /**
     * Gets the value of the loanDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getLoanDate() {
        return loanDate;
    }

    /**
     * Sets the value of the loanDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setLoanDate(XMLGregorianCalendar value) {
        this.loanDate = value;
    }

    /**
     * Gets the value of the loanPayoffDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getLoanPayoffDate() {
        return loanPayoffDate;
    }

    /**
     * Sets the value of the loanPayoffDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setLoanPayoffDate(XMLGregorianCalendar value) {
        this.loanPayoffDate = value;
    }

    /**
     * Gets the value of the secondBalance property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getSecondBalance() {
        return secondBalance;
    }

    /**
     * Sets the value of the secondBalance property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setSecondBalance(Integer value) {
        this.secondBalance = value;
    }

    /**
     * Gets the value of the secondBalanceRecordDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getSecondBalanceRecordDate() {
        return secondBalanceRecordDate;
    }

    /**
     * Sets the value of the secondBalanceRecordDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setSecondBalanceRecordDate(XMLGregorianCalendar value) {
        this.secondBalanceRecordDate = value;
    }

}