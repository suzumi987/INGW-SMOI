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
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

import phoebe.eqx.smoi.wsdl.CommonComponents.OneTimeFee;
import phoebe.eqx.smoi.wsdl.CommonComponents.SOperInfo;
import phoebe.eqx.smoi.wsdl.CommonComponents.SProductOrderOperList;


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
 *         &lt;element name="_user_id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="_phone_id">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="30"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="_change_type">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}short">
 *               &lt;enumeration value="0"/>
 *               &lt;enumeration value="1"/>
 *               &lt;enumeration value="2"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="_old_acct_id" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="50"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="_billable_acct_id" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="50"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="_convert_flag" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}short">
 *               &lt;enumeration value="0"/>
 *               &lt;enumeration value="1"/>
 *               &lt;enumeration value="2"/>
 *               &lt;enumeration value="3"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="_last_term_flag" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="N"/>
 *               &lt;enumeration value="Y"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="_new_acct_id">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="50"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="_change_promotion_flag" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}short">
 *               &lt;enumeration value="0"/>
 *               &lt;enumeration value="1"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="ProductOrderList" type="{http://www.asiainfo.com/obd/CommonComponents.obd}SProductOrderOperList" minOccurs="0"/>
 *         &lt;element name="_reset_flag" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}short">
 *               &lt;enumeration value="0"/>
 *               &lt;enumeration value="1"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="OneTimeFee" type="{http://www.asiainfo.com/obd/CommonComponents.obd}OneTimeFee" minOccurs="0"/>
 *         &lt;element name="_brand_id" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="150"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="_user_segment" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="32"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="_user_valid_date" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="_accumulation_reset_flag" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}short">
 *               &lt;enumeration value="0"/>
 *               &lt;enumeration value="1"/>
 *               &lt;enumeration value="2"/>
 *               &lt;enumeration value="3"/>
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
    "userId",
    "phoneId",
    "changeType",
    "oldAcctId",
    "billableAcctId",
    "convertFlag",
    "lastTermFlag",
    "newAcctId",
    "changePromotionFlag",
    "productOrderList",
    "resetFlag",
    "oneTimeFee",
    "brandId",
    "userSegment",
    "userValidDate",
    "accumulationResetFlag"
})
@XmlRootElement(name = "do_ChangeOwner", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
public class DoChangeOwner {

    @XmlElement(namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd", required = true)
    protected SOperInfo sOper;
    @XmlElement(name = "_user_id", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected Long userId;
    @XmlElement(name = "_phone_id", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd", required = true)
    protected String phoneId;
    @XmlElement(name = "_change_type", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected short changeType;
    @XmlElement(name = "_old_acct_id", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected String oldAcctId;
    @XmlElement(name = "_billable_acct_id", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected String billableAcctId;
    @XmlElement(name = "_convert_flag", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected Short convertFlag;
    @XmlElement(name = "_last_term_flag", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected String lastTermFlag;
    @XmlElement(name = "_new_acct_id", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd", required = true)
    protected String newAcctId;
    @XmlElement(name = "_change_promotion_flag", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected Short changePromotionFlag;
    @XmlElement(name = "ProductOrderList", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected SProductOrderOperList productOrderList;
    @XmlElement(name = "_reset_flag", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected Short resetFlag;
    @XmlElement(name = "OneTimeFee", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected OneTimeFee oneTimeFee;
    @XmlElement(name = "_brand_id", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected String brandId;
    @XmlElement(name = "_user_segment", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected String userSegment;
    @XmlElement(name = "_user_valid_date", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar userValidDate;
    @XmlElement(name = "_accumulation_reset_flag", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected Short accumulationResetFlag;

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
     * Gets the value of the oldAcctId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOldAcctId() {
        return oldAcctId;
    }

    /**
     * Sets the value of the oldAcctId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOldAcctId(String value) {
        this.oldAcctId = value;
    }

    /**
     * Gets the value of the billableAcctId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBillableAcctId() {
        return billableAcctId;
    }

    /**
     * Sets the value of the billableAcctId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBillableAcctId(String value) {
        this.billableAcctId = value;
    }

    /**
     * Gets the value of the convertFlag property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getConvertFlag() {
        return convertFlag;
    }

    /**
     * Sets the value of the convertFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setConvertFlag(Short value) {
        this.convertFlag = value;
    }

    /**
     * Gets the value of the lastTermFlag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastTermFlag() {
        return lastTermFlag;
    }

    /**
     * Sets the value of the lastTermFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastTermFlag(String value) {
        this.lastTermFlag = value;
    }

    /**
     * Gets the value of the newAcctId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNewAcctId() {
        return newAcctId;
    }

    /**
     * Sets the value of the newAcctId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNewAcctId(String value) {
        this.newAcctId = value;
    }

    /**
     * Gets the value of the changePromotionFlag property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getChangePromotionFlag() {
        return changePromotionFlag;
    }

    /**
     * Sets the value of the changePromotionFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setChangePromotionFlag(Short value) {
        this.changePromotionFlag = value;
    }

    /**
     * Gets the value of the productOrderList property.
     * 
     * @return
     *     possible object is
     *     {@link SProductOrderOperList }
     *     
     */
    public SProductOrderOperList getProductOrderList() {
        return productOrderList;
    }

    /**
     * Sets the value of the productOrderList property.
     * 
     * @param value
     *     allowed object is
     *     {@link SProductOrderOperList }
     *     
     */
    public void setProductOrderList(SProductOrderOperList value) {
        this.productOrderList = value;
    }

    /**
     * Gets the value of the resetFlag property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getResetFlag() {
        return resetFlag;
    }

    /**
     * Sets the value of the resetFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setResetFlag(Short value) {
        this.resetFlag = value;
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
     * Gets the value of the brandId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBrandId() {
        return brandId;
    }

    /**
     * Sets the value of the brandId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBrandId(String value) {
        this.brandId = value;
    }

    /**
     * Gets the value of the userSegment property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserSegment() {
        return userSegment;
    }

    /**
     * Sets the value of the userSegment property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserSegment(String value) {
        this.userSegment = value;
    }

    /**
     * Gets the value of the userValidDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getUserValidDate() {
        return userValidDate;
    }

    /**
     * Sets the value of the userValidDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setUserValidDate(XMLGregorianCalendar value) {
        this.userValidDate = value;
    }

    /**
     * Gets the value of the accumulationResetFlag property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getAccumulationResetFlag() {
        return accumulationResetFlag;
    }

    /**
     * Sets the value of the accumulationResetFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setAccumulationResetFlag(Short value) {
        this.accumulationResetFlag = value;
    }

}
