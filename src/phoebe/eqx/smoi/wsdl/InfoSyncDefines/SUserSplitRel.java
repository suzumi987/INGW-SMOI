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
 * <p>Java class for SUserSplitRel complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SUserSplitRel">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="_user_id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="_phone_id" minOccurs="0">
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
 *         &lt;element name="_product_sequence_id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="SPaySplitInfoList" type="{http://www.asiainfo.com/obd/InfoSyncDefines.obd}SPaySplitInfoList"/>
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
@XmlType(name = "SUserSplitRel", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd", propOrder = {
    "userId",
    "phoneId",
    "custId",
    "acctId",
    "productSequenceId",
    "sPaySplitInfoList",
    "operType"
})
public class SUserSplitRel {

    @XmlElement(name = "_user_id", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected Long userId;
    @XmlElement(name = "_phone_id", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected String phoneId;
    @XmlElement(name = "_cust_id", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected String custId;
    @XmlElement(name = "_acct_id", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected String acctId;
    @XmlElement(name = "_product_sequence_id", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected long productSequenceId;
    @XmlElement(name = "SPaySplitInfoList", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd", required = true)
    protected SPaySplitInfoList sPaySplitInfoList;
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
     * Gets the value of the productSequenceId property.
     * 
     */
    public long getProductSequenceId() {
        return productSequenceId;
    }

    /**
     * Sets the value of the productSequenceId property.
     * 
     */
    public void setProductSequenceId(long value) {
        this.productSequenceId = value;
    }

    /**
     * Gets the value of the sPaySplitInfoList property.
     * 
     * @return
     *     possible object is
     *     {@link SPaySplitInfoList }
     *     
     */
    public SPaySplitInfoList getSPaySplitInfoList() {
        return sPaySplitInfoList;
    }

    /**
     * Sets the value of the sPaySplitInfoList property.
     * 
     * @param value
     *     allowed object is
     *     {@link SPaySplitInfoList }
     *     
     */
    public void setSPaySplitInfoList(SPaySplitInfoList value) {
        this.sPaySplitInfoList = value;
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
