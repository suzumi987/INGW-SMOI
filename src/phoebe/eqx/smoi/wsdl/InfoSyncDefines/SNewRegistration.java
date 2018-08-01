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

import phoebe.eqx.smoi.wsdl.CommonComponents.SAccountList;
import phoebe.eqx.smoi.wsdl.CommonComponents.SBalanceList;
import phoebe.eqx.smoi.wsdl.CommonComponents.SContactList;
import phoebe.eqx.smoi.wsdl.CommonComponents.SCustomer;
import phoebe.eqx.smoi.wsdl.CommonComponents.SPayChannelList;
import phoebe.eqx.smoi.wsdl.CommonComponents.SProductOrderList;
import phoebe.eqx.smoi.wsdl.CommonComponents.SUserAuthList;
import phoebe.eqx.smoi.wsdl.CommonComponents.SUserList;


/**
 * <p>Java class for SNewRegistration complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SNewRegistration">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Customer" type="{http://www.asiainfo.com/obd/CommonComponents.obd}SCustomer" minOccurs="0"/>
 *         &lt;element name="AccountList" type="{http://www.asiainfo.com/obd/CommonComponents.obd}SAccountList" minOccurs="0"/>
 *         &lt;element name="UserList" type="{http://www.asiainfo.com/obd/CommonComponents.obd}SUserList" minOccurs="0"/>
 *         &lt;element name="ProductOrderList" type="{http://www.asiainfo.com/obd/CommonComponents.obd}SProductOrderList" minOccurs="0"/>
 *         &lt;element name="ContactList" type="{http://www.asiainfo.com/obd/CommonComponents.obd}SContactList" minOccurs="0"/>
 *         &lt;element name="BalanceList" type="{http://www.asiainfo.com/obd/CommonComponents.obd}SBalanceList" minOccurs="0"/>
 *         &lt;element name="PayChannelList" type="{http://www.asiainfo.com/obd/CommonComponents.obd}SPayChannelList" minOccurs="0"/>
 *         &lt;element name="UserAuthList" type="{http://www.asiainfo.com/obd/CommonComponents.obd}SUserAuthList" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SNewRegistration", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd", propOrder = {
    "customer",
    "accountList",
    "userList",
    "productOrderList",
    "contactList",
    "balanceList",
    "payChannelList",
    "userAuthList"
})
public class SNewRegistration {

    @XmlElement(name = "Customer", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected SCustomer customer;
    @XmlElement(name = "AccountList", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected SAccountList accountList;
    @XmlElement(name = "UserList", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected SUserList userList;
    @XmlElement(name = "ProductOrderList", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected SProductOrderList productOrderList;
    @XmlElement(name = "ContactList", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected SContactList contactList;
    @XmlElement(name = "BalanceList", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected SBalanceList balanceList;
    @XmlElement(name = "PayChannelList", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected SPayChannelList payChannelList;
    @XmlElement(name = "UserAuthList", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected SUserAuthList userAuthList;

    /**
     * Gets the value of the customer property.
     * 
     * @return
     *     possible object is
     *     {@link SCustomer }
     *     
     */
    public SCustomer getCustomer() {
        return customer;
    }

    /**
     * Sets the value of the customer property.
     * 
     * @param value
     *     allowed object is
     *     {@link SCustomer }
     *     
     */
    public void setCustomer(SCustomer value) {
        this.customer = value;
    }

    /**
     * Gets the value of the accountList property.
     * 
     * @return
     *     possible object is
     *     {@link SAccountList }
     *     
     */
    public SAccountList getAccountList() {
        return accountList;
    }

    /**
     * Sets the value of the accountList property.
     * 
     * @param value
     *     allowed object is
     *     {@link SAccountList }
     *     
     */
    public void setAccountList(SAccountList value) {
        this.accountList = value;
    }

    /**
     * Gets the value of the userList property.
     * 
     * @return
     *     possible object is
     *     {@link SUserList }
     *     
     */
    public SUserList getUserList() {
        return userList;
    }

    /**
     * Sets the value of the userList property.
     * 
     * @param value
     *     allowed object is
     *     {@link SUserList }
     *     
     */
    public void setUserList(SUserList value) {
        this.userList = value;
    }

    /**
     * Gets the value of the productOrderList property.
     * 
     * @return
     *     possible object is
     *     {@link SProductOrderList }
     *     
     */
    public SProductOrderList getProductOrderList() {
        return productOrderList;
    }

    /**
     * Sets the value of the productOrderList property.
     * 
     * @param value
     *     allowed object is
     *     {@link SProductOrderList }
     *     
     */
    public void setProductOrderList(SProductOrderList value) {
        this.productOrderList = value;
    }

    /**
     * Gets the value of the contactList property.
     * 
     * @return
     *     possible object is
     *     {@link SContactList }
     *     
     */
    public SContactList getContactList() {
        return contactList;
    }

    /**
     * Sets the value of the contactList property.
     * 
     * @param value
     *     allowed object is
     *     {@link SContactList }
     *     
     */
    public void setContactList(SContactList value) {
        this.contactList = value;
    }

    /**
     * Gets the value of the balanceList property.
     * 
     * @return
     *     possible object is
     *     {@link SBalanceList }
     *     
     */
    public SBalanceList getBalanceList() {
        return balanceList;
    }

    /**
     * Sets the value of the balanceList property.
     * 
     * @param value
     *     allowed object is
     *     {@link SBalanceList }
     *     
     */
    public void setBalanceList(SBalanceList value) {
        this.balanceList = value;
    }

    /**
     * Gets the value of the payChannelList property.
     * 
     * @return
     *     possible object is
     *     {@link SPayChannelList }
     *     
     */
    public SPayChannelList getPayChannelList() {
        return payChannelList;
    }

    /**
     * Sets the value of the payChannelList property.
     * 
     * @param value
     *     allowed object is
     *     {@link SPayChannelList }
     *     
     */
    public void setPayChannelList(SPayChannelList value) {
        this.payChannelList = value;
    }

    /**
     * Gets the value of the userAuthList property.
     * 
     * @return
     *     possible object is
     *     {@link SUserAuthList }
     *     
     */
    public SUserAuthList getUserAuthList() {
        return userAuthList;
    }

    /**
     * Sets the value of the userAuthList property.
     * 
     * @param value
     *     allowed object is
     *     {@link SUserAuthList }
     *     
     */
    public void setUserAuthList(SUserAuthList value) {
        this.userAuthList = value;
    }

}
