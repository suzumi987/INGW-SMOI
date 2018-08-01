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
 *         &lt;element name="_cust_id">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="50"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="_acct_id">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="50"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="_oper_type" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *         &lt;element name="SubCAList" type="{http://www.asiainfo.com/obd/InfoSyncDefines.obd}SubCAlist"/>
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
    "custId",
    "acctId",
    "operType",
    "subCAList"
})
@XmlRootElement(name = "do_ChangeCARelation", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
public class DoChangeCARelation {

    @XmlElement(namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd", required = true)
    protected SOperInfo sOper;
    @XmlElement(name = "_cust_id", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd", required = true)
    protected String custId;
    @XmlElement(name = "_acct_id", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd", required = true)
    protected String acctId;
    @XmlElement(name = "_oper_type", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected short operType;
    @XmlElement(name = "SubCAList", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd", required = true)
    protected SubCAlist subCAList;

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
     * Gets the value of the operType property.
     * 
     */
    public short getOperType() {
        return operType;
    }

    /**
     * Sets the value of the operType property.
     * 
     */
    public void setOperType(short value) {
        this.operType = value;
    }

    /**
     * Gets the value of the subCAList property.
     * 
     * @return
     *     possible object is
     *     {@link SubCAlist }
     *     
     */
    public SubCAlist getSubCAList() {
        return subCAList;
    }

    /**
     * Sets the value of the subCAList property.
     * 
     * @param value
     *     allowed object is
     *     {@link SubCAlist }
     *     
     */
    public void setSubCAList(SubCAlist value) {
        this.subCAList = value;
    }

}