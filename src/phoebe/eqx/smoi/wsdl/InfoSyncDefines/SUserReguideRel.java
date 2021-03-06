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
 * <p>Java class for SUserReguideRel complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SUserReguideRel">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="_user_id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="_phone_id">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="30"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="_cust_id" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="50"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="_acct_id" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="50"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="_reguide_type">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}short">
 *               &lt;enumeration value="0"/>
 *               &lt;enumeration value="1"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="_busi_service_id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="_time_segment" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="60"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="SPayReguideInfoList" type="{http://www.asiainfo.com/obd/InfoSyncDefines.obd}SPayReguideInfoList" minOccurs="0"/>
 *         &lt;element name="_oper_type" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}short">
 *               &lt;enumeration value="0"/>
 *               &lt;enumeration value="1"/>
 *               &lt;enumeration value="2"/>
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
@XmlType(name = "SUserReguideRel", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd", propOrder = {
    "userId",
    "phoneId",
    "custId",
    "acctId",
    "reguideType",
    "busiServiceId",
    "timeSegment",
    "sPayReguideInfoList",
    "operType"
})
public class SUserReguideRel {

    @XmlElement(name = "_user_id", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected Long userId;
    @XmlElement(name = "_phone_id", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd", required = true)
    protected String phoneId;
    @XmlElement(name = "_cust_id", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected String custId;
    @XmlElement(name = "_acct_id", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected String acctId;
    @XmlElement(name = "_reguide_type", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected short reguideType;
    @XmlElement(name = "_busi_service_id", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected Long busiServiceId;
    @XmlElement(name = "_time_segment", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected String timeSegment;
    @XmlElement(name = "SPayReguideInfoList", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected SPayReguideInfoList sPayReguideInfoList;
    @XmlElement(name = "_oper_type", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected Short operType;

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
     * Gets the value of the custId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustId() {
        return custId;
    }

    /**
     * Sets the value of the custId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustId(String value) {
        this.custId = value;
    }

    /**
     * Gets the value of the acctId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAcctId() {
        return acctId;
    }

    /**
     * Sets the value of the acctId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAcctId(String value) {
        this.acctId = value;
    }

    /**
     * Gets the value of the reguideType property.
     * 
     */
    public short getReguideType() {
        return reguideType;
    }

    /**
     * Sets the value of the reguideType property.
     * 
     */
    public void setReguideType(short value) {
        this.reguideType = value;
    }

    /**
     * Gets the value of the busiServiceId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getBusiServiceId() {
        return busiServiceId;
    }

    /**
     * Sets the value of the busiServiceId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setBusiServiceId(Long value) {
        this.busiServiceId = value;
    }

    /**
     * Gets the value of the timeSegment property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTimeSegment() {
        return timeSegment;
    }

    /**
     * Sets the value of the timeSegment property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTimeSegment(String value) {
        this.timeSegment = value;
    }

    /**
     * Gets the value of the sPayReguideInfoList property.
     * 
     * @return
     *     possible object is
     *     {@link SPayReguideInfoList }
     *     
     */
    public SPayReguideInfoList getSPayReguideInfoList() {
        return sPayReguideInfoList;
    }

    /**
     * Sets the value of the sPayReguideInfoList property.
     * 
     * @param value
     *     allowed object is
     *     {@link SPayReguideInfoList }
     *     
     */
    public void setSPayReguideInfoList(SPayReguideInfoList value) {
        this.sPayReguideInfoList = value;
    }

    /**
     * Gets the value of the operType property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getOperType() {
        return operType;
    }

    /**
     * Sets the value of the operType property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setOperType(Short value) {
        this.operType = value;
    }

}
