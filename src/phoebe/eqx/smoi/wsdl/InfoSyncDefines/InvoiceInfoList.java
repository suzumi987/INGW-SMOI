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
 * <p>Java class for InvoiceInfoList complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="InvoiceInfoList">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="InvoiceInfoList_Item" type="{http://www.asiainfo.com/obd/InfoSyncDefines.obd}InvoiceInfo" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InvoiceInfoList", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd", propOrder = {
    "invoiceInfoListItem"
})
public class InvoiceInfoList {

    @XmlElement(name = "InvoiceInfoList_Item", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected List<InvoiceInfo> invoiceInfoListItem;

    /**
     * Gets the value of the invoiceInfoListItem property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the invoiceInfoListItem property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInvoiceInfoListItem().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link InvoiceInfo }
     * 
     * 
     */
    public List<InvoiceInfo> getInvoiceInfoListItem() {
        if (invoiceInfoListItem == null) {
            invoiceInfoListItem = new ArrayList<InvoiceInfo>();
        }
        return this.invoiceInfoListItem;
    }

}
