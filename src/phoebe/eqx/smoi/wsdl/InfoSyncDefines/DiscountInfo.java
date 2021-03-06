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
 * <p>Java class for DiscountInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DiscountInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="_discount_id" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="_discount_type">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="256"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="_discount_amount" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="_billdiscount_product_id" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="_discount_eng_name">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="256"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="_discount_thai_name">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="256"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="_discount_start_date" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="_discount_end_date" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DiscountInfo", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd", propOrder = {
    "discountId",
    "discountType",
    "discountAmount",
    "billdiscountProductId",
    "discountEngName",
    "discountThaiName",
    "discountStartDate",
    "discountEndDate"
})
public class DiscountInfo {

    @XmlElement(name = "_discount_id", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected Integer discountId;
    @XmlElement(name = "_discount_type", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd", required = true)
    protected String discountType;
    @XmlElement(name = "_discount_amount", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected Long discountAmount;
    @XmlElement(name = "_billdiscount_product_id", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected Integer billdiscountProductId;
    @XmlElement(name = "_discount_eng_name", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd", required = true)
    protected String discountEngName;
    @XmlElement(name = "_discount_thai_name", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd", required = true)
    protected String discountThaiName;
    @XmlElement(name = "_discount_start_date", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar discountStartDate;
    @XmlElement(name = "_discount_end_date", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar discountEndDate;

    /**
     * Gets the value of the discountId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getDiscountId() {
        return discountId;
    }

    /**
     * Sets the value of the discountId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setDiscountId(Integer value) {
        this.discountId = value;
    }

    /**
     * Gets the value of the discountType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDiscountType() {
        return discountType;
    }

    /**
     * Sets the value of the discountType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDiscountType(String value) {
        this.discountType = value;
    }

    /**
     * Gets the value of the discountAmount property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getDiscountAmount() {
        return discountAmount;
    }

    /**
     * Sets the value of the discountAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setDiscountAmount(Long value) {
        this.discountAmount = value;
    }

    /**
     * Gets the value of the billdiscountProductId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getBilldiscountProductId() {
        return billdiscountProductId;
    }

    /**
     * Sets the value of the billdiscountProductId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setBilldiscountProductId(Integer value) {
        this.billdiscountProductId = value;
    }

    /**
     * Gets the value of the discountEngName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDiscountEngName() {
        return discountEngName;
    }

    /**
     * Sets the value of the discountEngName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDiscountEngName(String value) {
        this.discountEngName = value;
    }

    /**
     * Gets the value of the discountThaiName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDiscountThaiName() {
        return discountThaiName;
    }

    /**
     * Sets the value of the discountThaiName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDiscountThaiName(String value) {
        this.discountThaiName = value;
    }

    /**
     * Gets the value of the discountStartDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDiscountStartDate() {
        return discountStartDate;
    }

    /**
     * Sets the value of the discountStartDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDiscountStartDate(XMLGregorianCalendar value) {
        this.discountStartDate = value;
    }

    /**
     * Gets the value of the discountEndDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDiscountEndDate() {
        return discountEndDate;
    }

    /**
     * Sets the value of the discountEndDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDiscountEndDate(XMLGregorianCalendar value) {
        this.discountEndDate = value;
    }

}
