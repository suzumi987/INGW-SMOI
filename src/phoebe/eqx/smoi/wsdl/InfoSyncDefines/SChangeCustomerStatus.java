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
import javax.xml.bind.annotation.XmlType;

import phoebe.eqx.smoi.wsdl.CommonComponents.SUserStatus;


/**
 * <p>Java class for SChangeCustomerStatus complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SChangeCustomerStatus">
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
 *         &lt;element name="_change_type" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *         &lt;element name="sUesrStatus" type="{http://www.asiainfo.com/obd/CommonComponents.obd}SUserStatus"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SChangeCustomerStatus", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd", propOrder = {
    "phoneId",
    "changeType",
    "sUesrStatus"
})
public class SChangeCustomerStatus {

    @XmlElement(name = "_phone_id", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd", required = true)
    protected String phoneId;
    @XmlElement(name = "_change_type", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected short changeType;
    @XmlElement(namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd", required = true)
    protected SUserStatus sUesrStatus;

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
     * Gets the value of the changeType property.
     * 
     */
    public short getChangeType() {
        return changeType;
    }

    /**
     * Sets the value of the changeType property.
     * 
     */
    public void setChangeType(short value) {
        this.changeType = value;
    }

    /**
     * Gets the value of the sUesrStatus property.
     * 
     * @return
     *     possible object is
     *     {@link SUserStatus }
     *     
     */
    public SUserStatus getSUesrStatus() {
        return sUesrStatus;
    }

    /**
     * Sets the value of the sUesrStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link SUserStatus }
     *     
     */
    public void setSUesrStatus(SUserStatus value) {
        this.sUesrStatus = value;
    }

}
