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
 * <p>Java class for AcctNotify complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AcctNotify">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="_acct_id">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="30"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="NotifyList" type="{http://www.asiainfo.com/obd/InfoSyncDefines.obd}NotifyList2"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AcctNotify", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd", propOrder = {
    "acctId",
    "notifyList"
})
public class AcctNotify {

    @XmlElement(name = "_acct_id", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd", required = true)
    protected String acctId;
    @XmlElement(name = "NotifyList", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd", required = true)
    protected NotifyList2 notifyList;

    /**
     * Gets the value of the acctId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAcctId() {
        return acctId;
    }

    /**
     * Sets the value of the acctId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAcctId(String value) {
        this.acctId = value;
    }

    /**
     * Gets the value of the notifyList property.
     * 
     * @return
     *     possible object is
     *     {@link NotifyList2 }
     *     
     */
    public NotifyList2 getNotifyList() {
        return notifyList;
    }

    /**
     * Sets the value of the notifyList property.
     * 
     * @param value
     *     allowed object is
     *     {@link NotifyList2 }
     *     
     */
    public void setNotifyList(NotifyList2 value) {
        this.notifyList = value;
    }

}
