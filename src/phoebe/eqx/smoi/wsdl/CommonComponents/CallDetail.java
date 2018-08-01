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
 * <p>Java class for CallDetail complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CallDetail">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="_event_type">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int">
 *               &lt;enumeration value="1"/>
 *               &lt;enumeration value="2"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="_real_total_event_type" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="_total_event_type" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="_real_duration">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="32"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="_total_charge_domestic" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="_total_charge_domestic_vat" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="_total_charge_international" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CallDetail", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd", propOrder = {
    "eventType",
    "realTotalEventType",
    "totalEventType",
    "realDuration",
    "totalChargeDomestic",
    "totalChargeDomesticVat",
    "totalChargeInternational"
})
public class CallDetail {

    @XmlElement(name = "_event_type", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected int eventType;
    @XmlElement(name = "_real_total_event_type", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected int realTotalEventType;
    @XmlElement(name = "_total_event_type", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected int totalEventType;
    @XmlElement(name = "_real_duration", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd", required = true)
    protected String realDuration;
    @XmlElement(name = "_total_charge_domestic", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected Long totalChargeDomestic;
    @XmlElement(name = "_total_charge_domestic_vat", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected Long totalChargeDomesticVat;
    @XmlElement(name = "_total_charge_international", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected Long totalChargeInternational;

    /**
     * Gets the value of the eventType property.
     * 
     */
    public int getEventType() {
        return eventType;
    }

    /**
     * Sets the value of the eventType property.
     * 
     */
    public void setEventType(int value) {
        this.eventType = value;
    }

    /**
     * Gets the value of the realTotalEventType property.
     * 
     */
    public int getRealTotalEventType() {
        return realTotalEventType;
    }

    /**
     * Sets the value of the realTotalEventType property.
     * 
     */
    public void setRealTotalEventType(int value) {
        this.realTotalEventType = value;
    }

    /**
     * Gets the value of the totalEventType property.
     * 
     */
    public int getTotalEventType() {
        return totalEventType;
    }

    /**
     * Sets the value of the totalEventType property.
     * 
     */
    public void setTotalEventType(int value) {
        this.totalEventType = value;
    }

    /**
     * Gets the value of the realDuration property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRealDuration() {
        return realDuration;
    }

    /**
     * Sets the value of the realDuration property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRealDuration(String value) {
        this.realDuration = value;
    }

    /**
     * Gets the value of the totalChargeDomestic property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getTotalChargeDomestic() {
        return totalChargeDomestic;
    }

    /**
     * Sets the value of the totalChargeDomestic property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setTotalChargeDomestic(Long value) {
        this.totalChargeDomestic = value;
    }

    /**
     * Gets the value of the totalChargeDomesticVat property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getTotalChargeDomesticVat() {
        return totalChargeDomesticVat;
    }

    /**
     * Sets the value of the totalChargeDomesticVat property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setTotalChargeDomesticVat(Long value) {
        this.totalChargeDomesticVat = value;
    }

    /**
     * Gets the value of the totalChargeInternational property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getTotalChargeInternational() {
        return totalChargeInternational;
    }

    /**
     * Sets the value of the totalChargeInternational property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setTotalChargeInternational(Long value) {
        this.totalChargeInternational = value;
    }

}