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
 * <p>Java class for ProductSeqOper complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ProductSeqOper">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="_oper_type" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}short">
 *               &lt;enumeration value="0"/>
 *               &lt;enumeration value="1"/>
 *               &lt;enumeration value="2"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="_product_sequence_id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="_resource_id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="_resource_value" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="_resource_unit">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="32"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="_effective_type" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}short">
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
@XmlType(name = "ProductSeqOper", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd", propOrder = {
    "operType",
    "productSequenceId",
    "resourceId",
    "resourceValue",
    "resourceUnit",
    "effectiveType"
})
public class ProductSeqOper {

    @XmlElement(name = "_oper_type", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected Short operType;
    @XmlElement(name = "_product_sequence_id", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected long productSequenceId;
    @XmlElement(name = "_resource_id", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected int resourceId;
    @XmlElement(name = "_resource_value", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected Long resourceValue;
    @XmlElement(name = "_resource_unit", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd", required = true)
    protected String resourceUnit;
    @XmlElement(name = "_effective_type", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected Short effectiveType;

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
     * Gets the value of the resourceId property.
     * 
     */
    public int getResourceId() {
        return resourceId;
    }

    /**
     * Sets the value of the resourceId property.
     * 
     */
    public void setResourceId(int value) {
        this.resourceId = value;
    }

    /**
     * Gets the value of the resourceValue property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getResourceValue() {
        return resourceValue;
    }

    /**
     * Sets the value of the resourceValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setResourceValue(Long value) {
        this.resourceValue = value;
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
     * Gets the value of the effectiveType property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getEffectiveType() {
        return effectiveType;
    }

    /**
     * Sets the value of the effectiveType property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setEffectiveType(Short value) {
        this.effectiveType = value;
    }

}
