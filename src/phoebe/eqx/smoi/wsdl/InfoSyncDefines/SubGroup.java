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

import phoebe.eqx.smoi.wsdl.CommonComponents.SProductOrderOperList;


/**
 * <p>Java class for SubGroup complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SubGroup">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="_sub_group_id">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="100"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="ProductOrderOperList" type="{http://www.asiainfo.com/obd/CommonComponents.obd}SProductOrderOperList" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SubGroup", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd", propOrder = {
    "subGroupId",
    "productOrderOperList"
})
public class SubGroup {

    @XmlElement(name = "_sub_group_id", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd", required = true)
    protected String subGroupId;
    @XmlElement(name = "ProductOrderOperList", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected SProductOrderOperList productOrderOperList;

    /**
     * Gets the value of the subGroupId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubGroupId() {
        return subGroupId;
    }

    /**
     * Sets the value of the subGroupId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubGroupId(String value) {
        this.subGroupId = value;
    }

    /**
     * Gets the value of the productOrderOperList property.
     * 
     * @return
     *     possible object is
     *     {@link SProductOrderOperList }
     *     
     */
    public SProductOrderOperList getProductOrderOperList() {
        return productOrderOperList;
    }

    /**
     * Sets the value of the productOrderOperList property.
     * 
     * @param value
     *     allowed object is
     *     {@link SProductOrderOperList }
     *     
     */
    public void setProductOrderOperList(SProductOrderOperList value) {
        this.productOrderOperList = value;
    }

}