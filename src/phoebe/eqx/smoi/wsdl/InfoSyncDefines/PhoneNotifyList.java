//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.04.01 at 04:26:27 PM ICT 
//


package phoebe.eqx.smoi.wsdl.InfoSyncDefines;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PhoneNotifyList complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PhoneNotifyList">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PhoneNotifyList_Item" type="{http://www.asiainfo.com/obd/InfoSyncDefines.obd}PhoneNotify" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PhoneNotifyList", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd", propOrder = {
    "phoneNotifyListItem"
})
public class PhoneNotifyList {

    @XmlElement(name = "PhoneNotifyList_Item", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected List<PhoneNotify> phoneNotifyListItem;

    /**
     * Gets the value of the phoneNotifyListItem property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the phoneNotifyListItem property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPhoneNotifyListItem().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PhoneNotify }
     * 
     * 
     */
    public List<PhoneNotify> getPhoneNotifyListItem() {
        if (phoneNotifyListItem == null) {
            phoneNotifyListItem = new ArrayList<PhoneNotify>();
        }
        return this.phoneNotifyListItem;
    }

}
