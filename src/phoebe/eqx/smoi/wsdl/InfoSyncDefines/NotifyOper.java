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
 * <p>Java class for NotifyOper complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="NotifyOper">
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
 *         &lt;element name="NotifyList" type="{http://www.asiainfo.com/obd/InfoSyncDefines.obd}NotifyList" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NotifyOper", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd", propOrder = {
    "operType",
    "notifyList"
})
public class NotifyOper {

    @XmlElement(name = "_oper_type", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected short operType;
    @XmlElement(name = "NotifyList", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected NotifyList notifyList;

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
     * Gets the value of the notifyList property.
     * 
     * @return
     *     possible object is
     *     {@link NotifyList }
     *     
     */
    public NotifyList getNotifyList() {
        return notifyList;
    }

    /**
     * Sets the value of the notifyList property.
     * 
     * @param value
     *     allowed object is
     *     {@link NotifyList }
     *     
     */
    public void setNotifyList(NotifyList value) {
        this.notifyList = value;
    }

}
