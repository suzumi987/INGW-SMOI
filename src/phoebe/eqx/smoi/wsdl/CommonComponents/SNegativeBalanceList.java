//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.04.01 at 04:26:27 PM ICT 
//


package phoebe.eqx.smoi.wsdl.CommonComponents;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SNegativeBalanceList complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SNegativeBalanceList">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SNegativeBalanceList_Item" type="{http://www.asiainfo.com/obd/CommonComponents.obd}SNegativeBalance" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SNegativeBalanceList", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd", propOrder = {
    "sNegativeBalanceListItem"
})
public class SNegativeBalanceList {

    @XmlElement(name = "SNegativeBalanceList_Item", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected List<SNegativeBalance> sNegativeBalanceListItem;

    /**
     * Gets the value of the sNegativeBalanceListItem property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the sNegativeBalanceListItem property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSNegativeBalanceListItem().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SNegativeBalance }
     * 
     * 
     */
    public List<SNegativeBalance> getSNegativeBalanceListItem() {
        if (sNegativeBalanceListItem == null) {
            sNegativeBalanceListItem = new ArrayList<SNegativeBalance>();
        }
        return this.sNegativeBalanceListItem;
    }

}