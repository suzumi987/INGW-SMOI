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
 * <p>Java class for DAAHistory complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DAAHistory">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="_action_datetime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
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
 *         &lt;element name="_loan_amount_before" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="_loan_amount_after" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="_fee_amount_before" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="_fee_amount_after" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DAAHistory", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd", propOrder = {
    "actionDatetime",
    "phoneId",
    "loanId",
    "loanAmountBefore",
    "loanAmountAfter",
    "feeAmountBefore",
    "feeAmountAfter"
})
public class DAAHistory {

    @XmlElement(name = "_action_datetime", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar actionDatetime;
    @XmlElement(name = "_phone_id", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd", required = true)
    protected String phoneId;
    @XmlElement(name = "_loan_id", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd", required = true)
    protected String loanId;
    @XmlElement(name = "_loan_amount_before", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected int loanAmountBefore;
    @XmlElement(name = "_loan_amount_after", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected int loanAmountAfter;
    @XmlElement(name = "_fee_amount_before", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected int feeAmountBefore;
    @XmlElement(name = "_fee_amount_after", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected int feeAmountAfter;

    /**
     * Gets the value of the actionDatetime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getActionDatetime() {
        return actionDatetime;
    }

    /**
     * Sets the value of the actionDatetime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setActionDatetime(XMLGregorianCalendar value) {
        this.actionDatetime = value;
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
     * Gets the value of the loanAmountBefore property.
     * 
     */
    public int getLoanAmountBefore() {
        return loanAmountBefore;
    }

    /**
     * Sets the value of the loanAmountBefore property.
     * 
     */
    public void setLoanAmountBefore(int value) {
        this.loanAmountBefore = value;
    }

    /**
     * Gets the value of the loanAmountAfter property.
     * 
     */
    public int getLoanAmountAfter() {
        return loanAmountAfter;
    }

    /**
     * Sets the value of the loanAmountAfter property.
     * 
     */
    public void setLoanAmountAfter(int value) {
        this.loanAmountAfter = value;
    }

    /**
     * Gets the value of the feeAmountBefore property.
     * 
     */
    public int getFeeAmountBefore() {
        return feeAmountBefore;
    }

    /**
     * Sets the value of the feeAmountBefore property.
     * 
     */
    public void setFeeAmountBefore(int value) {
        this.feeAmountBefore = value;
    }

    /**
     * Gets the value of the feeAmountAfter property.
     * 
     */
    public int getFeeAmountAfter() {
        return feeAmountAfter;
    }

    /**
     * Sets the value of the feeAmountAfter property.
     * 
     */
    public void setFeeAmountAfter(int value) {
        this.feeAmountAfter = value;
    }

}
