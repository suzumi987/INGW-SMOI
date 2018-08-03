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

import phoebe.eqx.smoi.wsdl.CommonComponents.SBalanceList;
import phoebe.eqx.smoi.wsdl.CommonComponents.SProductOrderList;
import phoebe.eqx.smoi.wsdl.CommonComponents.SProductOrderResultList;
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
 *         &lt;element name="SProductOrderResultList" type="{http://www.asiainfo.com/obd/CommonComponents.obd}SProductOrderResultList" minOccurs="0"/>
 *         &lt;element name="ProductOrderErrorList" type="{http://www.asiainfo.com/obd/CommonComponents.obd}SProductOrderList" minOccurs="0"/>
 *         &lt;element name="SBalanceList" type="{http://www.asiainfo.com/obd/CommonComponents.obd}SBalanceList" minOccurs="0"/>
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
    "sProductOrderResultList",
    "productOrderErrorList",
    "sBalanceList"
})
@XmlRootElement(name = "do_ChangeOwnerResponse", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
public class DoChangeOwnerResponse {

    @XmlElement(namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd", required = true)
    protected SResultDescription sResult;
    @XmlElement(name = "SProductOrderResultList", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected SProductOrderResultList sProductOrderResultList;
    @XmlElement(name = "ProductOrderErrorList", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected SProductOrderList productOrderErrorList;
    @XmlElement(name = "SBalanceList", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected SBalanceList sBalanceList;

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
     * Gets the value of the sProductOrderResultList property.
     * 
     * @return
     *     possible object is
     *     {@link SProductOrderResultList }
     *     
     */
    public SProductOrderResultList getSProductOrderResultList() {
        return sProductOrderResultList;
    }

    /**
     * Sets the value of the sProductOrderResultList property.
     * 
     * @param value
     *     allowed object is
     *     {@link SProductOrderResultList }
     *     
     */
    public void setSProductOrderResultList(SProductOrderResultList value) {
        this.sProductOrderResultList = value;
    }

    /**
     * Gets the value of the productOrderErrorList property.
     * 
     * @return
     *     possible object is
     *     {@link SProductOrderList }
     *     
     */
    public SProductOrderList getProductOrderErrorList() {
        return productOrderErrorList;
    }

    /**
     * Sets the value of the productOrderErrorList property.
     * 
     * @param value
     *     allowed object is
     *     {@link SProductOrderList }
     *     
     */
    public void setProductOrderErrorList(SProductOrderList value) {
        this.productOrderErrorList = value;
    }

    /**
     * Gets the value of the sBalanceList property.
     * 
     * @return
     *     possible object is
     *     {@link SBalanceList }
     *     
     */
    public SBalanceList getSBalanceList() {
        return sBalanceList;
    }

    /**
     * Sets the value of the sBalanceList property.
     * 
     * @param value
     *     allowed object is
     *     {@link SBalanceList }
     *     
     */
    public void setSBalanceList(SBalanceList value) {
        this.sBalanceList = value;
    }

}
