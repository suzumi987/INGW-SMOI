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
 * <p>Java class for SNewGroupReq complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SNewGroupReq">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="GroupInfo" type="{http://www.asiainfo.com/obd/CommonComponents.obd}SGroup"/>
 *         &lt;element name="ProductOrderList" type="{http://www.asiainfo.com/obd/CommonComponents.obd}SProductOrderList"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SNewGroupReq", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd", propOrder = {
    "groupInfo",
    "productOrderList"
})
public class SNewGroupReq {

    @XmlElement(name = "GroupInfo", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd", required = true)
    protected SGroup groupInfo;
    @XmlElement(name = "ProductOrderList", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd", required = true)
    protected SProductOrderList productOrderList;

    /**
     * Gets the value of the groupInfo property.
     * 
     * @return
     *     possible object is
     *     {@link SGroup }
     *     
     */
    public SGroup getGroupInfo() {
        return groupInfo;
    }

    /**
     * Sets the value of the groupInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link SGroup }
     *     
     */
    public void setGroupInfo(SGroup value) {
        this.groupInfo = value;
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

}
