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
 * <p>Java class for SUserRelationInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SUserRelationInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="_user_id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="_phone_id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="_region_code" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *         &lt;element name="_ruser_id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="_rphone_id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="_rregion_code" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *         &lt;element name="_valid_date" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="_expire_date" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SUserRelationInfo", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd", propOrder = {
    "userId",
    "phoneId",
    "regionCode",
    "ruserId",
    "rphoneId",
    "rregionCode",
    "validDate",
    "expireDate"
})
public class SUserRelationInfo {

    @XmlElement(name = "_user_id", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected Long userId;
    @XmlElement(name = "_phone_id", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd", required = true)
    protected String phoneId;
    @XmlElement(name = "_region_code", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected short regionCode;
    @XmlElement(name = "_ruser_id", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected Long ruserId;
    @XmlElement(name = "_rphone_id", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd", required = true)
    protected String rphoneId;
    @XmlElement(name = "_rregion_code", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected short rregionCode;
    @XmlElement(name = "_valid_date", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar validDate;
    @XmlElement(name = "_expire_date", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar expireDate;

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
     * Gets the value of the regionCode property.
     * 
     */
    public short getRegionCode() {
        return regionCode;
    }

    /**
     * Sets the value of the regionCode property.
     * 
     */
    public void setRegionCode(short value) {
        this.regionCode = value;
    }

    /**
     * Gets the value of the ruserId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getRuserId() {
        return ruserId;
    }

    /**
     * Sets the value of the ruserId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setRuserId(Long value) {
        this.ruserId = value;
    }

    /**
     * Gets the value of the rphoneId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRphoneId() {
        return rphoneId;
    }

    /**
     * Sets the value of the rphoneId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRphoneId(String value) {
        this.rphoneId = value;
    }

    /**
     * Gets the value of the rregionCode property.
     * 
     */
    public short getRregionCode() {
        return rregionCode;
    }

    /**
     * Sets the value of the rregionCode property.
     * 
     */
    public void setRregionCode(short value) {
        this.rregionCode = value;
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
