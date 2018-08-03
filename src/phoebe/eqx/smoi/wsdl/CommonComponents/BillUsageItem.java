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
 * <p>Java class for BillUsageItem complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BillUsageItem">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="_bill_item" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="_unpay_fee" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="_discount_fee" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="_adjust_fee" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="_prim_fee" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="_usage" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="_unit" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="32"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BillUsageItem", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd", propOrder = {
    "billItem",
    "unpayFee",
    "discountFee",
    "adjustFee",
    "primFee",
    "usage",
    "unit"
})
public class BillUsageItem {

    @XmlElement(name = "_bill_item", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected int billItem;
    @XmlElement(name = "_unpay_fee", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected long unpayFee;
    @XmlElement(name = "_discount_fee", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected long discountFee;
    @XmlElement(name = "_adjust_fee", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected long adjustFee;
    @XmlElement(name = "_prim_fee", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected long primFee;
    @XmlElement(name = "_usage", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected long usage;
    @XmlElement(name = "_unit", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected String unit;

    /**
     * Gets the value of the billItem property.
     * 
     */
    public int getBillItem() {
        return billItem;
    }

    /**
     * Sets the value of the billItem property.
     * 
     */
    public void setBillItem(int value) {
        this.billItem = value;
    }

    /**
     * Gets the value of the unpayFee property.
     * 
     */
    public long getUnpayFee() {
        return unpayFee;
    }

    /**
     * Sets the value of the unpayFee property.
     * 
     */
    public void setUnpayFee(long value) {
        this.unpayFee = value;
    }

    /**
     * Gets the value of the discountFee property.
     * 
     */
    public long getDiscountFee() {
        return discountFee;
    }

    /**
     * Sets the value of the discountFee property.
     * 
     */
    public void setDiscountFee(long value) {
        this.discountFee = value;
    }

    /**
     * Gets the value of the adjustFee property.
     * 
     */
    public long getAdjustFee() {
        return adjustFee;
    }

    /**
     * Sets the value of the adjustFee property.
     * 
     */
    public void setAdjustFee(long value) {
        this.adjustFee = value;
    }

    /**
     * Gets the value of the primFee property.
     * 
     */
    public long getPrimFee() {
        return primFee;
    }

    /**
     * Sets the value of the primFee property.
     * 
     */
    public void setPrimFee(long value) {
        this.primFee = value;
    }

    /**
     * Gets the value of the usage property.
     * 
     */
    public long getUsage() {
        return usage;
    }

    /**
     * Sets the value of the usage property.
     * 
     */
    public void setUsage(long value) {
        this.usage = value;
    }

    /**
     * Gets the value of the unit property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnit() {
        return unit;
    }

    /**
     * Sets the value of the unit property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnit(String value) {
        this.unit = value;
    }

}