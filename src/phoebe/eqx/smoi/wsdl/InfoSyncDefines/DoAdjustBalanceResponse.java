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

import phoebe.eqx.smoi.wsdl.CommonComponents.FreeResourceList;
import phoebe.eqx.smoi.wsdl.CommonComponents.SBalanceAdjustResponse;
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
 *         &lt;element name="SBalanceAdjustResponse" type="{http://www.asiainfo.com/obd/CommonComponents.obd}SBalanceAdjustResponse" minOccurs="0"/>
 *         &lt;element name="FreeResourceListAdjustResponse" type="{http://www.asiainfo.com/obd/CommonComponents.obd}FreeResourceList" minOccurs="0"/>
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
    "sBalanceAdjustResponse",
    "freeResourceListAdjustResponse"
})
@XmlRootElement(name = "do_AdjustBalanceResponse", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
public class DoAdjustBalanceResponse {

    @XmlElement(namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd", required = true)
    protected SResultDescription sResult;
    @XmlElement(name = "SBalanceAdjustResponse", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected SBalanceAdjustResponse sBalanceAdjustResponse;
    @XmlElement(name = "FreeResourceListAdjustResponse", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected FreeResourceList freeResourceListAdjustResponse;

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
     * Gets the value of the sBalanceAdjustResponse property.
     * 
     * @return
     *     possible object is
     *     {@link SBalanceAdjustResponse }
     *     
     */
    public SBalanceAdjustResponse getSBalanceAdjustResponse() {
        return sBalanceAdjustResponse;
    }

    /**
     * Sets the value of the sBalanceAdjustResponse property.
     * 
     * @param value
     *     allowed object is
     *     {@link SBalanceAdjustResponse }
     *     
     */
    public void setSBalanceAdjustResponse(SBalanceAdjustResponse value) {
        this.sBalanceAdjustResponse = value;
    }

    /**
     * Gets the value of the freeResourceListAdjustResponse property.
     * 
     * @return
     *     possible object is
     *     {@link FreeResourceList }
     *     
     */
    public FreeResourceList getFreeResourceListAdjustResponse() {
        return freeResourceListAdjustResponse;
    }

    /**
     * Sets the value of the freeResourceListAdjustResponse property.
     * 
     * @param value
     *     allowed object is
     *     {@link FreeResourceList }
     *     
     */
    public void setFreeResourceListAdjustResponse(FreeResourceList value) {
        this.freeResourceListAdjustResponse = value;
    }

}
