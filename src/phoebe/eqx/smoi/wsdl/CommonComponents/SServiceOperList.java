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
 * <p>Java class for SServiceOperList complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SServiceOperList">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SServiceOperList_Item" type="{http://www.asiainfo.com/obd/CommonComponents.obd}SServiceOper" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SServiceOperList", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd", propOrder = {
    "sServiceOperListItem"
})
public class SServiceOperList {

    @XmlElement(name = "SServiceOperList_Item", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected List<SServiceOper> sServiceOperListItem;

    /**
     * Gets the value of the sServiceOperListItem property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the sServiceOperListItem property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSServiceOperListItem().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SServiceOper }
     * 
     * 
     */
    public List<SServiceOper> getSServiceOperListItem() {
        if (sServiceOperListItem == null) {
            sServiceOperListItem = new ArrayList<SServiceOper>();
        }
        return this.sServiceOperListItem;
    }

}