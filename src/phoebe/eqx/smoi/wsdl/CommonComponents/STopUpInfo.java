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
 * <p>Java class for STopUpInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="STopUpInfo">
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
 *         &lt;element name="SBalance" type="{http://www.asiainfo.com/obd/CommonComponents.obd}SBalance" minOccurs="0"/>
 *         &lt;element name="_amount" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="_recharge_method" type="{http://www.w3.org/2001/XMLSchema}short" minOccurs="0"/>
 *         &lt;element name="_recharge_partner_id" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="_recharge_service_id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="_serial_no" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="64"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="_batch_no" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="64"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="_remark" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="255"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="_etopup_session_id" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="8"/>
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
@XmlType(name = "STopUpInfo", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd", propOrder = {
    "userId",
    "phoneId",
    "sBalance",
    "amount",
    "rechargeMethod",
    "rechargePartnerId",
    "rechargeServiceId",
    "serialNo",
    "batchNo",
    "remark",
    "etopupSessionId"
})
public class STopUpInfo {

    @XmlElement(name = "_user_id", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected Long userId;
    @XmlElement(name = "_phone_id", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd", required = true)
    protected String phoneId;
    @XmlElement(name = "SBalance", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected SBalance sBalance;
    @XmlElement(name = "_amount", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected Long amount;
    @XmlElement(name = "_recharge_method", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected Short rechargeMethod;
    @XmlElement(name = "_recharge_partner_id", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected Integer rechargePartnerId;
    @XmlElement(name = "_recharge_service_id", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected int rechargeServiceId;
    @XmlElement(name = "_serial_no", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected String serialNo;
    @XmlElement(name = "_batch_no", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected String batchNo;
    @XmlElement(name = "_remark", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected String remark;
    @XmlElement(name = "_etopup_session_id", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected String etopupSessionId;

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
     * Gets the value of the sBalance property.
     * 
     * @return
     *     possible object is
     *     {@link SBalance }
     *     
     */
    public SBalance getSBalance() {
        return sBalance;
    }

    /**
     * Sets the value of the sBalance property.
     * 
     * @param value
     *     allowed object is
     *     {@link SBalance }
     *     
     */
    public void setSBalance(SBalance value) {
        this.sBalance = value;
    }

    /**
     * Gets the value of the amount property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getAmount() {
        return amount;
    }

    /**
     * Sets the value of the amount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setAmount(Long value) {
        this.amount = value;
    }

    /**
     * Gets the value of the rechargeMethod property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getRechargeMethod() {
        return rechargeMethod;
    }

    /**
     * Sets the value of the rechargeMethod property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setRechargeMethod(Short value) {
        this.rechargeMethod = value;
    }

    /**
     * Gets the value of the rechargePartnerId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getRechargePartnerId() {
        return rechargePartnerId;
    }

    /**
     * Sets the value of the rechargePartnerId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setRechargePartnerId(Integer value) {
        this.rechargePartnerId = value;
    }

    /**
     * Gets the value of the rechargeServiceId property.
     * 
     */
    public int getRechargeServiceId() {
        return rechargeServiceId;
    }

    /**
     * Sets the value of the rechargeServiceId property.
     * 
     */
    public void setRechargeServiceId(int value) {
        this.rechargeServiceId = value;
    }

    /**
     * Gets the value of the serialNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSerialNo() {
        return serialNo;
    }

    /**
     * Sets the value of the serialNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSerialNo(String value) {
        this.serialNo = value;
    }

    /**
     * Gets the value of the batchNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBatchNo() {
        return batchNo;
    }

    /**
     * Sets the value of the batchNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBatchNo(String value) {
        this.batchNo = value;
    }

    /**
     * Gets the value of the remark property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRemark() {
        return remark;
    }

    /**
     * Sets the value of the remark property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRemark(String value) {
        this.remark = value;
    }

    /**
     * Gets the value of the etopupSessionId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEtopupSessionId() {
        return etopupSessionId;
    }

    /**
     * Sets the value of the etopupSessionId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEtopupSessionId(String value) {
        this.etopupSessionId = value;
    }

}