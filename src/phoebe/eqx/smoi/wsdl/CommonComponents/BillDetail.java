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
 * <p>Java class for BillDetail complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BillDetail">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="_fee_item" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="_unpay_fee" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="_discount_fee" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="_adjust_fee" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="_prim_fee" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="_phone_id" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="30"/>
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
@XmlType(name = "BillDetail", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd", propOrder = {
    "feeItem",
    "unpayFee",
    "discountFee",
    "adjustFee",
    "primFee",
    "phoneId"
})
public class BillDetail {

    @XmlElement(name = "_fee_item", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected int feeItem;
    @XmlElement(name = "_unpay_fee", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected long unpayFee;
    @XmlElement(name = "_discount_fee", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected long discountFee;
    @XmlElement(name = "_adjust_fee", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected long adjustFee;
    @XmlElement(name = "_prim_fee", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected long primFee;
    @XmlElement(name = "_phone_id", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected String phoneId;

    /**
     * Gets the value of the feeItem property.
     * 
     */
    public int getFeeItem() {
        return feeItem;
    }

    /**
     * Sets the value of the feeItem property.
     * 
     */
    public void setFeeItem(int value) {
        this.feeItem = value;
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

}
