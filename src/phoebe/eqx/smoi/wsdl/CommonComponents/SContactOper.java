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
 * <p>Java class for SContactOper complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SContactOper">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="_oper_type">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}short">
 *               &lt;enumeration value="0"/>
 *               &lt;enumeration value="1"/>
 *               &lt;enumeration value="2"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="SContactList" type="{http://www.asiainfo.com/obd/CommonComponents.obd}SContactList"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SContactOper", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd", propOrder = {
    "operType",
    "sContactList"
})
public class SContactOper {

    @XmlElement(name = "_oper_type", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected short operType;
    @XmlElement(name = "SContactList", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd", required = true)
    protected SContactList sContactList;

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
     * Gets the value of the sContactList property.
     * 
     * @return
     *     possible object is
     *     {@link SContactList }
     *     
     */
    public SContactList getSContactList() {
        return sContactList;
    }

    /**
     * Sets the value of the sContactList property.
     * 
     * @param value
     *     allowed object is
     *     {@link SContactList }
     *     
     */
    public void setSContactList(SContactList value) {
        this.sContactList = value;
    }

}
