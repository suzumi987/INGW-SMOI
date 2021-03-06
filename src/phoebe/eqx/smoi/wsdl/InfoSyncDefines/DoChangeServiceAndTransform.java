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

import phoebe.eqx.smoi.wsdl.CommonComponents.OneTimeFee;
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
 *         &lt;element name="ChangeService" type="{http://www.asiainfo.com/obd/InfoSyncDefines.obd}ChangeService" minOccurs="0"/>
 *         &lt;element name="STransferReq" type="{http://www.asiainfo.com/obd/InfoSyncDefines.obd}STransferReq" minOccurs="0"/>
 *         &lt;element name="OneTimeFee" type="{http://www.asiainfo.com/obd/CommonComponents.obd}OneTimeFee" minOccurs="0"/>
 *         &lt;element name="_notification_flag" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}short">
 *               &lt;enumeration value="0"/>
 *               &lt;enumeration value="1"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="_transparent_data_1" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="64"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="_transparent_data_2" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="64"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="_transparent_data_3" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="64"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="_site_id" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="10"/>
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
    "changeService",
    "sTransferReq",
    "oneTimeFee",
    "notificationFlag",
    "transparentData1",
    "transparentData2",
    "transparentData3",
    "siteId"
})
@XmlRootElement(name = "do_ChangeServiceAndTransform", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
public class DoChangeServiceAndTransform {

    @XmlElement(namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd", required = true)
    protected SOperInfo sOper;
    @XmlElement(name = "ChangeService", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected ChangeService changeService;
    @XmlElement(name = "STransferReq", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected STransferReq sTransferReq;
    @XmlElement(name = "OneTimeFee", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected OneTimeFee oneTimeFee;
    @XmlElement(name = "_notification_flag", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected Short notificationFlag;
    @XmlElement(name = "_transparent_data_1", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected String transparentData1;
    @XmlElement(name = "_transparent_data_2", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected String transparentData2;
    @XmlElement(name = "_transparent_data_3", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected String transparentData3;
    @XmlElement(name = "_site_id", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected String siteId;

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
     * Gets the value of the changeService property.
     * 
     * @return
     *     possible object is
     *     {@link ChangeService }
     *     
     */
    public ChangeService getChangeService() {
        return changeService;
    }

    /**
     * Sets the value of the changeService property.
     * 
     * @param value
     *     allowed object is
     *     {@link ChangeService }
     *     
     */
    public void setChangeService(ChangeService value) {
        this.changeService = value;
    }

    /**
     * Gets the value of the sTransferReq property.
     * 
     * @return
     *     possible object is
     *     {@link STransferReq }
     *     
     */
    public STransferReq getSTransferReq() {
        return sTransferReq;
    }

    /**
     * Sets the value of the sTransferReq property.
     * 
     * @param value
     *     allowed object is
     *     {@link STransferReq }
     *     
     */
    public void setSTransferReq(STransferReq value) {
        this.sTransferReq = value;
    }

    /**
     * Gets the value of the oneTimeFee property.
     * 
     * @return
     *     possible object is
     *     {@link OneTimeFee }
     *     
     */
    public OneTimeFee getOneTimeFee() {
        return oneTimeFee;
    }

    /**
     * Sets the value of the oneTimeFee property.
     * 
     * @param value
     *     allowed object is
     *     {@link OneTimeFee }
     *     
     */
    public void setOneTimeFee(OneTimeFee value) {
        this.oneTimeFee = value;
    }

    /**
     * Gets the value of the notificationFlag property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getNotificationFlag() {
        return notificationFlag;
    }

    /**
     * Sets the value of the notificationFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setNotificationFlag(Short value) {
        this.notificationFlag = value;
    }

    /**
     * Gets the value of the transparentData1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransparentData1() {
        return transparentData1;
    }

    /**
     * Sets the value of the transparentData1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransparentData1(String value) {
        this.transparentData1 = value;
    }

    /**
     * Gets the value of the transparentData2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransparentData2() {
        return transparentData2;
    }

    /**
     * Sets the value of the transparentData2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransparentData2(String value) {
        this.transparentData2 = value;
    }

    /**
     * Gets the value of the transparentData3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransparentData3() {
        return transparentData3;
    }

    /**
     * Sets the value of the transparentData3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransparentData3(String value) {
        this.transparentData3 = value;
    }

    /**
     * Gets the value of the siteId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSiteId() {
        return siteId;
    }

    /**
     * Sets the value of the siteId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSiteId(String value) {
        this.siteId = value;
    }

}
