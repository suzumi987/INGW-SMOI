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
 *         &lt;element name="MonthlyFeeDeductReq" type="{http://www.asiainfo.com/obd/InfoSyncDefines.obd}DoMonthlyFeeDeduct"/>
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
    "monthlyFeeDeductReq"
})
@XmlRootElement(name = "do_MonthlyFeeDeduct", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
public class DoMonthlyFeeDeduct {

    @XmlElement(name = "MonthlyFeeDeductReq", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd", required = true)
    protected DoMonthlyFeeDeduct2 monthlyFeeDeductReq;

    /**
     * Gets the value of the monthlyFeeDeductReq property.
     * 
     * @return
     *     possible object is
     *     {@link DoMonthlyFeeDeduct2 }
     *     
     */
    public DoMonthlyFeeDeduct2 getMonthlyFeeDeductReq() {
        return monthlyFeeDeductReq;
    }

    /**
     * Sets the value of the monthlyFeeDeductReq property.
     * 
     * @param value
     *     allowed object is
     *     {@link DoMonthlyFeeDeduct2 }
     *     
     */
    public void setMonthlyFeeDeductReq(DoMonthlyFeeDeduct2 value) {
        this.monthlyFeeDeductReq = value;
    }

}
