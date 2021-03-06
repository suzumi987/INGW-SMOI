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
 *         &lt;element name="_phone_id">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="30"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="_acct_id">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="50"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="AdjustBalanceList">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{http://www.asiainfo.com/obd/InfoSyncDefines.obd}do_AdjustBalance" maxOccurs="unbounded"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
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
@XmlType(name = "", propOrder = {
    "phoneId",
    "acctId",
    "adjustBalanceList"
})
@XmlRootElement(name = "do_groupAdjustBalance", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
public class DoGroupAdjustBalance {

    @XmlElement(name = "_phone_id", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd", required = true)
    protected String phoneId;
    @XmlElement(name = "_acct_id", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd", required = true)
    protected String acctId;
    @XmlElement(name = "AdjustBalanceList", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd", required = true)
    protected DoGroupAdjustBalance.AdjustBalanceList adjustBalanceList;

    /**
     * Gets the value of the phoneId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPhoneId() {
        return phoneId;
    }

    /**
     * Sets the value of the phoneId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPhoneId(String value) {
        this.phoneId = value;
    }

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
     * Gets the value of the adjustBalanceList property.
     * 
     * @return
     *     possible object is
     *     {@link DoGroupAdjustBalance.AdjustBalanceList }
     *     
     */
    public DoGroupAdjustBalance.AdjustBalanceList getAdjustBalanceList() {
        return adjustBalanceList;
    }

    /**
     * Sets the value of the adjustBalanceList property.
     * 
     * @param value
     *     allowed object is
     *     {@link DoGroupAdjustBalance.AdjustBalanceList }
     *     
     */
    public void setAdjustBalanceList(DoGroupAdjustBalance.AdjustBalanceList value) {
        this.adjustBalanceList = value;
    }


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
     *         &lt;element ref="{http://www.asiainfo.com/obd/InfoSyncDefines.obd}do_AdjustBalance" maxOccurs="unbounded"/>
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
        "doAdjustBalance"
    })
    public static class AdjustBalanceList {

        @XmlElement(name = "do_AdjustBalance", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd", required = true)
        protected List<DoAdjustBalance> doAdjustBalance;

        /**
         * Gets the value of the doAdjustBalance property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the doAdjustBalance property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getDoAdjustBalance().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link DoAdjustBalance }
         * 
         * 
         */
        public List<DoAdjustBalance> getDoAdjustBalance() {
            if (doAdjustBalance == null) {
                doAdjustBalance = new ArrayList<DoAdjustBalance>();
            }
            return this.doAdjustBalance;
        }

    }

}
