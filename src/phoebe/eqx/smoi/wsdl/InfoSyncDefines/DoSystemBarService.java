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
 *         &lt;element name="SystemBarServiceReq" type="{http://www.asiainfo.com/obd/InfoSyncDefines.obd}DoSystemBarService"/>
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
    "systemBarServiceReq"
})
@XmlRootElement(name = "do_SystemBarService", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
public class DoSystemBarService {

    @XmlElement(name = "SystemBarServiceReq", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd", required = true)
    protected DoSystemBarService2 systemBarServiceReq;

    /**
     * Gets the value of the systemBarServiceReq property.
     * 
     * @return
     *     possible object is
     *     {@link DoSystemBarService2 }
     *     
     */
    public DoSystemBarService2 getSystemBarServiceReq() {
        return systemBarServiceReq;
    }

    /**
     * Sets the value of the systemBarServiceReq property.
     * 
     * @param value
     *     allowed object is
     *     {@link DoSystemBarService2 }
     *     
     */
    public void setSystemBarServiceReq(DoSystemBarService2 value) {
        this.systemBarServiceReq = value;
    }

}