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

import phoebe.eqx.smoi.wsdl.CommonComponents.SGroupList;
import phoebe.eqx.smoi.wsdl.CommonComponents.SProductOrderList;
import phoebe.eqx.smoi.wsdl.CommonComponents.SResultDescription;


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
 *         &lt;element name="sResult" type="{http://www.asiainfo.com/obd/CommonComponents.obd}SResultDescription"/>
 *         &lt;element name="SGroupList" type="{http://www.asiainfo.com/obd/CommonComponents.obd}SGroupList" minOccurs="0"/>
 *         &lt;element name="SubGroupList" type="{http://www.asiainfo.com/obd/InfoSyncDefines.obd}SubGroupList" minOccurs="0"/>
 *         &lt;element name="ProductOrderList" type="{http://www.asiainfo.com/obd/CommonComponents.obd}SProductOrderList" minOccurs="0"/>
 *         &lt;element name="_number_of_cug" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="_number_of_subgroup" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
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
    "sResult",
    "sGroupList",
    "subGroupList",
    "productOrderList",
    "numberOfCug",
    "numberOfSubgroup"
})
@XmlRootElement(name = "do_QueryGroupInfoResponse", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
public class DoQueryGroupInfoResponse {

    @XmlElement(namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd", required = true)
    protected SResultDescription sResult;
    @XmlElement(name = "SGroupList", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected SGroupList sGroupList;
    @XmlElement(name = "SubGroupList", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected SubGroupList subGroupList;
    @XmlElement(name = "ProductOrderList", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected SProductOrderList productOrderList;
    @XmlElement(name = "_number_of_cug", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected Integer numberOfCug;
    @XmlElement(name = "_number_of_subgroup", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected Integer numberOfSubgroup;

    /**
     * Gets the value of the sResult property.
     * 
     * @return
     *     possible object is
     *     {@link SResultDescription }
     *     
     */
    public SResultDescription getSResult() {
        return sResult;
    }

    /**
     * Sets the value of the sResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link SResultDescription }
     *     
     */
    public void setSResult(SResultDescription value) {
        this.sResult = value;
    }

    /**
     * Gets the value of the sGroupList property.
     * 
     * @return
     *     possible object is
     *     {@link SGroupList }
     *     
     */
    public SGroupList getSGroupList() {
        return sGroupList;
    }

    /**
     * Sets the value of the sGroupList property.
     * 
     * @param value
     *     allowed object is
     *     {@link SGroupList }
     *     
     */
    public void setSGroupList(SGroupList value) {
        this.sGroupList = value;
    }

    /**
     * Gets the value of the subGroupList property.
     * 
     * @return
     *     possible object is
     *     {@link SubGroupList }
     *     
     */
    public SubGroupList getSubGroupList() {
        return subGroupList;
    }

    /**
     * Sets the value of the subGroupList property.
     * 
     * @param value
     *     allowed object is
     *     {@link SubGroupList }
     *     
     */
    public void setSubGroupList(SubGroupList value) {
        this.subGroupList = value;
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
     * Gets the value of the numberOfCug property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNumberOfCug() {
        return numberOfCug;
    }

    /**
     * Sets the value of the numberOfCug property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNumberOfCug(Integer value) {
        this.numberOfCug = value;
    }

    /**
     * Gets the value of the numberOfSubgroup property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNumberOfSubgroup() {
        return numberOfSubgroup;
    }

    /**
     * Sets the value of the numberOfSubgroup property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNumberOfSubgroup(Integer value) {
        this.numberOfSubgroup = value;
    }

}
