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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import phoebe.eqx.smoi.wsdl.CommonComponents.SOperInfo;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="sOper" type="{http://www.asiainfo.com/obd/CommonComponents.obd}SOperInfo"/>
 *         &lt;element name="_phone_id_A" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="30"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="_short_phone_id_A" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="30"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="_phone_id_B" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="30"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="_short_phone_id_B" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="30"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="_group_id" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="100"/>
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
@XmlType(name = "", propOrder = {
    "sOper",
    "phoneIdA",
    "shortPhoneIdA",
    "phoneIdB",
    "shortPhoneIdB",
    "groupId"
})
@XmlRootElement(name = "do_QueryMultiCommunity", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
public class DoQueryMultiCommunity {

    @XmlElement(namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd", required = true)
    protected SOperInfo sOper;
    @XmlElement(name = "_phone_id_A", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected String phoneIdA;
    @XmlElement(name = "_short_phone_id_A", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected String shortPhoneIdA;
    @XmlElement(name = "_phone_id_B", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected String phoneIdB;
    @XmlElement(name = "_short_phone_id_B", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected String shortPhoneIdB;
    @XmlElement(name = "_group_id", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected String groupId;

    /**
     * Gets the value of the sOper property.
     * 
     * @return
     *     possible object is
     *     {@link SOperInfo }
     *     
     */
    public SOperInfo getSOper() {
        return sOper;
    }

    /**
     * Sets the value of the sOper property.
     * 
     * @param value
     *     allowed object is
     *     {@link SOperInfo }
     *     
     */
    public void setSOper(SOperInfo value) {
        this.sOper = value;
    }

    /**
     * Gets the value of the phoneIdA property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPhoneIdA() {
        return phoneIdA;
    }

    /**
     * Sets the value of the phoneIdA property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPhoneIdA(String value) {
        this.phoneIdA = value;
    }

    /**
     * Gets the value of the shortPhoneIdA property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShortPhoneIdA() {
        return shortPhoneIdA;
    }

    /**
     * Sets the value of the shortPhoneIdA property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShortPhoneIdA(String value) {
        this.shortPhoneIdA = value;
    }

    /**
     * Gets the value of the phoneIdB property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPhoneIdB() {
        return phoneIdB;
    }

    /**
     * Sets the value of the phoneIdB property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPhoneIdB(String value) {
        this.phoneIdB = value;
    }

    /**
     * Gets the value of the shortPhoneIdB property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShortPhoneIdB() {
        return shortPhoneIdB;
    }

    /**
     * Sets the value of the shortPhoneIdB property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShortPhoneIdB(String value) {
        this.shortPhoneIdB = value;
    }

    /**
     * Gets the value of the groupId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGroupId() {
        return groupId;
    }

    /**
     * Sets the value of the groupId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGroupId(String value) {
        this.groupId = value;
    }

}
