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
 * <p>Java class for SBalanceAdjustResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SBalanceAdjustResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="_book_item" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="_amount_after" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="_days_request" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="_days_adjust" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="_acct_id" minOccurs="0">
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
 *         &lt;element name="_valid_date" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="_expiredate_after" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="_expiredate_before" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="_amount_before" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="_amount_request" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="_amount_adjust" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SBalanceAdjustResponse", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd", propOrder = {
    "bookItem",
    "amountAfter",
    "daysRequest",
    "daysAdjust",
    "acctId",
    "userId",
    "phoneId",
    "validDate",
    "expiredateAfter",
    "expiredateBefore",
    "amountBefore",
    "amountRequest",
    "amountAdjust"
})
public class SBalanceAdjustResponse {

    @XmlElement(name = "_book_item", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected Integer bookItem;
    @XmlElement(name = "_amount_after", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected Long amountAfter;
    @XmlElement(name = "_days_request", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected Long daysRequest;
    @XmlElement(name = "_days_adjust", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected Long daysAdjust;
    @XmlElement(name = "_acct_id", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected String acctId;
    @XmlElement(name = "_user_id", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected Long userId;
    @XmlElement(name = "_phone_id", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected String phoneId;
    @XmlElement(name = "_valid_date", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar validDate;
    @XmlElement(name = "_expiredate_after", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar expiredateAfter;
    @XmlElement(name = "_expiredate_before", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar expiredateBefore;
    @XmlElement(name = "_amount_before", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected Long amountBefore;
    @XmlElement(name = "_amount_request", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected Long amountRequest;
    @XmlElement(name = "_amount_adjust", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected Long amountAdjust;

    /**
     * Gets the value of the bookItem property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getBookItem() {
        return bookItem;
    }

    /**
     * Sets the value of the bookItem property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setBookItem(Integer value) {
        this.bookItem = value;
    }

    /**
     * Gets the value of the amountAfter property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getAmountAfter() {
        return amountAfter;
    }

    /**
     * Sets the value of the amountAfter property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setAmountAfter(Long value) {
        this.amountAfter = value;
    }

    /**
     * Gets the value of the daysRequest property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getDaysRequest() {
        return daysRequest;
    }

    /**
     * Sets the value of the daysRequest property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setDaysRequest(Long value) {
        this.daysRequest = value;
    }

    /**
     * Gets the value of the daysAdjust property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getDaysAdjust() {
        return daysAdjust;
    }

    /**
     * Sets the value of the daysAdjust property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setDaysAdjust(Long value) {
        this.daysAdjust = value;
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
     * Gets the value of the expiredateAfter property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getExpiredateAfter() {
        return expiredateAfter;
    }

    /**
     * Sets the value of the expiredateAfter property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setExpiredateAfter(XMLGregorianCalendar value) {
        this.expiredateAfter = value;
    }

    /**
     * Gets the value of the expiredateBefore property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getExpiredateBefore() {
        return expiredateBefore;
    }

    /**
     * Sets the value of the expiredateBefore property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setExpiredateBefore(XMLGregorianCalendar value) {
        this.expiredateBefore = value;
    }

    /**
     * Gets the value of the amountBefore property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getAmountBefore() {
        return amountBefore;
    }

    /**
     * Sets the value of the amountBefore property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setAmountBefore(Long value) {
        this.amountBefore = value;
    }

    /**
     * Gets the value of the amountRequest property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getAmountRequest() {
        return amountRequest;
    }

    /**
     * Sets the value of the amountRequest property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setAmountRequest(Long value) {
        this.amountRequest = value;
    }

    /**
     * Gets the value of the amountAdjust property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getAmountAdjust() {
        return amountAdjust;
    }

    /**
     * Sets the value of the amountAdjust property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setAmountAdjust(Long value) {
        this.amountAdjust = value;
    }

}
