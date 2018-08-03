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
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for STopUpResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="STopUpResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="_user_id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="_phone_id">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="30"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="_account_balance" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="_reward_balance" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="_topup_amount" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="_new_active_stop" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="_validity_period" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="_extra_validity" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="RewardBalanceList" type="{http://www.asiainfo.com/obd/CommonComponents.obd}SBalanceList" minOccurs="0"/>
 *         &lt;element name="FreeResourceGroupList" type="{http://www.asiainfo.com/obd/CommonComponents.obd}FreeResourceGroupList" minOccurs="0"/>
 *         &lt;element name="ProductOrderResultList" type="{http://www.asiainfo.com/obd/CommonComponents.obd}SProductOrderResultList" minOccurs="0"/>
 *         &lt;element name="_recharge_time" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "STopUpResponse", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd", propOrder = {
    "userId",
    "phoneId",
    "accountBalance",
    "rewardBalance",
    "topupAmount",
    "newActiveStop",
    "validityPeriod",
    "extraValidity",
    "rewardBalanceList",
    "freeResourceGroupList",
    "productOrderResultList",
    "rechargeTime"
})
public class STopUpResponse {

    @XmlElement(name = "_user_id", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected long userId;
    @XmlElement(name = "_phone_id", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd", required = true)
    protected String phoneId;
    @XmlElement(name = "_account_balance", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected long accountBalance;
    @XmlElement(name = "_reward_balance", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected Long rewardBalance;
    @XmlElement(name = "_topup_amount", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected Long topupAmount;
    @XmlElement(name = "_new_active_stop", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar newActiveStop;
    @XmlElement(name = "_validity_period", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected Integer validityPeriod;
    @XmlElement(name = "_extra_validity", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected Integer extraValidity;
    @XmlElement(name = "RewardBalanceList", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected SBalanceList rewardBalanceList;
    @XmlElement(name = "FreeResourceGroupList", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected FreeResourceGroupList freeResourceGroupList;
    @XmlElement(name = "ProductOrderResultList", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected SProductOrderResultList productOrderResultList;
    @XmlElement(name = "_recharge_time", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar rechargeTime;

    /**
     * Gets the value of the userId property.
     * 
     */
    public long getUserId() {
        return userId;
    }

    /**
     * Sets the value of the userId property.
     * 
     */
    public void setUserId(long value) {
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
     * Gets the value of the accountBalance property.
     * 
     */
    public long getAccountBalance() {
        return accountBalance;
    }

    /**
     * Sets the value of the accountBalance property.
     * 
     */
    public void setAccountBalance(long value) {
        this.accountBalance = value;
    }

    /**
     * Gets the value of the rewardBalance property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getRewardBalance() {
        return rewardBalance;
    }

    /**
     * Sets the value of the rewardBalance property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setRewardBalance(Long value) {
        this.rewardBalance = value;
    }

    /**
     * Gets the value of the topupAmount property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getTopupAmount() {
        return topupAmount;
    }

    /**
     * Sets the value of the topupAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setTopupAmount(Long value) {
        this.topupAmount = value;
    }

    /**
     * Gets the value of the newActiveStop property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getNewActiveStop() {
        return newActiveStop;
    }

    /**
     * Sets the value of the newActiveStop property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setNewActiveStop(XMLGregorianCalendar value) {
        this.newActiveStop = value;
    }

    /**
     * Gets the value of the validityPeriod property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getValidityPeriod() {
        return validityPeriod;
    }

    /**
     * Sets the value of the validityPeriod property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setValidityPeriod(Integer value) {
        this.validityPeriod = value;
    }

    /**
     * Gets the value of the extraValidity property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getExtraValidity() {
        return extraValidity;
    }

    /**
     * Sets the value of the extraValidity property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setExtraValidity(Integer value) {
        this.extraValidity = value;
    }

    /**
     * Gets the value of the rewardBalanceList property.
     * 
     * @return
     *     possible object is
     *     {@link SBalanceList }
     *     
     */
    public SBalanceList getRewardBalanceList() {
        return rewardBalanceList;
    }

    /**
     * Sets the value of the rewardBalanceList property.
     * 
     * @param value
     *     allowed object is
     *     {@link SBalanceList }
     *     
     */
    public void setRewardBalanceList(SBalanceList value) {
        this.rewardBalanceList = value;
    }

    /**
     * Gets the value of the freeResourceGroupList property.
     * 
     * @return
     *     possible object is
     *     {@link FreeResourceGroupList }
     *     
     */
    public FreeResourceGroupList getFreeResourceGroupList() {
        return freeResourceGroupList;
    }

    /**
     * Sets the value of the freeResourceGroupList property.
     * 
     * @param value
     *     allowed object is
     *     {@link FreeResourceGroupList }
     *     
     */
    public void setFreeResourceGroupList(FreeResourceGroupList value) {
        this.freeResourceGroupList = value;
    }

    /**
     * Gets the value of the productOrderResultList property.
     * 
     * @return
     *     possible object is
     *     {@link SProductOrderResultList }
     *     
     */
    public SProductOrderResultList getProductOrderResultList() {
        return productOrderResultList;
    }

    /**
     * Sets the value of the productOrderResultList property.
     * 
     * @param value
     *     allowed object is
     *     {@link SProductOrderResultList }
     *     
     */
    public void setProductOrderResultList(SProductOrderResultList value) {
        this.productOrderResultList = value;
    }

    /**
     * Gets the value of the rechargeTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getRechargeTime() {
        return rechargeTime;
    }

    /**
     * Sets the value of the rechargeTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setRechargeTime(XMLGregorianCalendar value) {
        this.rechargeTime = value;
    }

}