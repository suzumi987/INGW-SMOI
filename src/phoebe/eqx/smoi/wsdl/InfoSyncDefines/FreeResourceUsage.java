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
 * <p>Java class for FreeResourceUsage complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FreeResourceUsage">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="_resource_usage" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="30"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="_resource_remaining" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="30"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="_resource_unit" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="30"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="_resource_name" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="255"/>
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
@XmlType(name = "FreeResourceUsage", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd", propOrder = {
    "resourceUsage",
    "resourceRemaining",
    "resourceUnit",
    "resourceName"
})
public class FreeResourceUsage {

    @XmlElement(name = "_resource_usage", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected String resourceUsage;
    @XmlElement(name = "_resource_remaining", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected String resourceRemaining;
    @XmlElement(name = "_resource_unit", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected String resourceUnit;
    @XmlElement(name = "_resource_name", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected String resourceName;

    /**
     * Gets the value of the resourceUsage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResourceUsage() {
        return resourceUsage;
    }

    /**
     * Sets the value of the resourceUsage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResourceUsage(String value) {
        this.resourceUsage = value;
    }

    /**
     * Gets the value of the resourceRemaining property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResourceRemaining() {
        return resourceRemaining;
    }

    /**
     * Sets the value of the resourceRemaining property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResourceRemaining(String value) {
        this.resourceRemaining = value;
    }

    /**
     * Gets the value of the resourceUnit property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResourceUnit() {
        return resourceUnit;
    }

    /**
     * Sets the value of the resourceUnit property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResourceUnit(String value) {
        this.resourceUnit = value;
    }

    /**
     * Gets the value of the resourceName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResourceName() {
        return resourceName;
    }

    /**
     * Sets the value of the resourceName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResourceName(String value) {
        this.resourceName = value;
    }

}