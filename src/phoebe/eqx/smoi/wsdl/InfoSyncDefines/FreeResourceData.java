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


/**
 * <p>Java class for FreeResourceData complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FreeResourceData">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="_phone_id" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="30"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="FreeResourceGroupRecordList" type="{http://www.asiainfo.com/obd/InfoSyncDefines.obd}FreeResourceGroupRecordList" minOccurs="0"/>
 *         &lt;element name="FreeResourceHeaderList" type="{http://www.asiainfo.com/obd/InfoSyncDefines.obd}FreeResourceHeaderList" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FreeResourceData", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd", propOrder = {
    "phoneId",
    "freeResourceGroupRecordList",
    "freeResourceHeaderList"
})
public class FreeResourceData {

    @XmlElement(name = "_phone_id", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected String phoneId;
    @XmlElement(name = "FreeResourceGroupRecordList", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected FreeResourceGroupRecordList freeResourceGroupRecordList;
    @XmlElement(name = "FreeResourceHeaderList", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected FreeResourceHeaderList freeResourceHeaderList;

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
     * Gets the value of the freeResourceGroupRecordList property.
     * 
     * @return
     *     possible object is
     *     {@link FreeResourceGroupRecordList }
     *     
     */
    public FreeResourceGroupRecordList getFreeResourceGroupRecordList() {
        return freeResourceGroupRecordList;
    }

    /**
     * Sets the value of the freeResourceGroupRecordList property.
     * 
     * @param value
     *     allowed object is
     *     {@link FreeResourceGroupRecordList }
     *     
     */
    public void setFreeResourceGroupRecordList(FreeResourceGroupRecordList value) {
        this.freeResourceGroupRecordList = value;
    }

    /**
     * Gets the value of the freeResourceHeaderList property.
     * 
     * @return
     *     possible object is
     *     {@link FreeResourceHeaderList }
     *     
     */
    public FreeResourceHeaderList getFreeResourceHeaderList() {
        return freeResourceHeaderList;
    }

    /**
     * Sets the value of the freeResourceHeaderList property.
     * 
     * @param value
     *     allowed object is
     *     {@link FreeResourceHeaderList }
     *     
     */
    public void setFreeResourceHeaderList(FreeResourceHeaderList value) {
        this.freeResourceHeaderList = value;
    }

}