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
 * <p>Java class for MailGroupResp complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MailGroupResp">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="_mail_group_id">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="100"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="_name" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="128"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="_description" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="255"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="_bill_format_id">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}short">
 *               &lt;enumeration value="1"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="_file_type" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}short">
 *               &lt;enumeration value="1"/>
 *               &lt;enumeration value="2"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="_summaryBillHandlingCode" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="50"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="_summaryBillLanguage" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="50"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="_summaryCurrency" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="50"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="_summaryStyleId" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="50"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="_valid_date" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="_expire_date" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MailGroupResp", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd", propOrder = {
    "mailGroupId",
    "name",
    "description",
    "billFormatId",
    "fileType",
    "summaryBillHandlingCode",
    "summaryBillLanguage",
    "summaryCurrency",
    "summaryStyleId",
    "validDate",
    "expireDate"
})
public class MailGroupResp {

    @XmlElement(name = "_mail_group_id", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd", required = true)
    protected String mailGroupId;
    @XmlElement(name = "_name", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected String name;
    @XmlElement(name = "_description", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected String description;
    @XmlElement(name = "_bill_format_id", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected short billFormatId;
    @XmlElement(name = "_file_type", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected Short fileType;
    @XmlElement(name = "_summaryBillHandlingCode", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected String summaryBillHandlingCode;
    @XmlElement(name = "_summaryBillLanguage", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected String summaryBillLanguage;
    @XmlElement(name = "_summaryCurrency", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected String summaryCurrency;
    @XmlElement(name = "_summaryStyleId", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected String summaryStyleId;
    @XmlElement(name = "_valid_date", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar validDate;
    @XmlElement(name = "_expire_date", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar expireDate;

    /**
     * Gets the value of the mailGroupId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMailGroupId() {
        return mailGroupId;
    }

    /**
     * Sets the value of the mailGroupId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMailGroupId(String value) {
        this.mailGroupId = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the billFormatId property.
     * 
     */
    public short getBillFormatId() {
        return billFormatId;
    }

    /**
     * Sets the value of the billFormatId property.
     * 
     */
    public void setBillFormatId(short value) {
        this.billFormatId = value;
    }

    /**
     * Gets the value of the fileType property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getFileType() {
        return fileType;
    }

    /**
     * Sets the value of the fileType property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setFileType(Short value) {
        this.fileType = value;
    }

    /**
     * Gets the value of the summaryBillHandlingCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSummaryBillHandlingCode() {
        return summaryBillHandlingCode;
    }

    /**
     * Sets the value of the summaryBillHandlingCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSummaryBillHandlingCode(String value) {
        this.summaryBillHandlingCode = value;
    }

    /**
     * Gets the value of the summaryBillLanguage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSummaryBillLanguage() {
        return summaryBillLanguage;
    }

    /**
     * Sets the value of the summaryBillLanguage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSummaryBillLanguage(String value) {
        this.summaryBillLanguage = value;
    }

    /**
     * Gets the value of the summaryCurrency property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSummaryCurrency() {
        return summaryCurrency;
    }

    /**
     * Sets the value of the summaryCurrency property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSummaryCurrency(String value) {
        this.summaryCurrency = value;
    }

    /**
     * Gets the value of the summaryStyleId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSummaryStyleId() {
        return summaryStyleId;
    }

    /**
     * Sets the value of the summaryStyleId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSummaryStyleId(String value) {
        this.summaryStyleId = value;
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

}