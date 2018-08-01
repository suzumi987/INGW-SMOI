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
 * <p>Java class for SOverCreditLimit complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SOverCreditLimit">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="_accumulate_over_CL_Domestic_before" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="_accumulate_over_CL_Domestic_after" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="_accumulate_over_CL_IR_before" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="_accumulate_over_CL_IR_after" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="_amount_domestic_package" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="_amount_IR_package" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="_amount_over_CL_Domestic_before" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="_amount_over_CL_Domestic_after" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="_amount_over_CL_IR_before" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="_amount_over_CL_IR_after" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="_over_CL">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="32"/>
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
@XmlType(name = "SOverCreditLimit", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd", propOrder = {
    "accumulateOverCLDomesticBefore",
    "accumulateOverCLDomesticAfter",
    "accumulateOverCLIRBefore",
    "accumulateOverCLIRAfter",
    "amountDomesticPackage",
    "amountIRPackage",
    "amountOverCLDomesticBefore",
    "amountOverCLDomesticAfter",
    "amountOverCLIRBefore",
    "amountOverCLIRAfter",
    "overCL"
})
public class SOverCreditLimit {

    @XmlElement(name = "_accumulate_over_CL_Domestic_before", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected Integer accumulateOverCLDomesticBefore;
    @XmlElement(name = "_accumulate_over_CL_Domestic_after", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected Integer accumulateOverCLDomesticAfter;
    @XmlElement(name = "_accumulate_over_CL_IR_before", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected Integer accumulateOverCLIRBefore;
    @XmlElement(name = "_accumulate_over_CL_IR_after", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected Integer accumulateOverCLIRAfter;
    @XmlElement(name = "_amount_domestic_package", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected Long amountDomesticPackage;
    @XmlElement(name = "_amount_IR_package", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected Long amountIRPackage;
    @XmlElement(name = "_amount_over_CL_Domestic_before", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected Long amountOverCLDomesticBefore;
    @XmlElement(name = "_amount_over_CL_Domestic_after", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected Long amountOverCLDomesticAfter;
    @XmlElement(name = "_amount_over_CL_IR_before", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected Long amountOverCLIRBefore;
    @XmlElement(name = "_amount_over_CL_IR_after", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected Long amountOverCLIRAfter;
    @XmlElement(name = "_over_CL", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd", required = true)
    protected String overCL;

    /**
     * Gets the value of the accumulateOverCLDomesticBefore property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getAccumulateOverCLDomesticBefore() {
        return accumulateOverCLDomesticBefore;
    }

    /**
     * Sets the value of the accumulateOverCLDomesticBefore property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setAccumulateOverCLDomesticBefore(Integer value) {
        this.accumulateOverCLDomesticBefore = value;
    }

    /**
     * Gets the value of the accumulateOverCLDomesticAfter property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getAccumulateOverCLDomesticAfter() {
        return accumulateOverCLDomesticAfter;
    }

    /**
     * Sets the value of the accumulateOverCLDomesticAfter property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setAccumulateOverCLDomesticAfter(Integer value) {
        this.accumulateOverCLDomesticAfter = value;
    }

    /**
     * Gets the value of the accumulateOverCLIRBefore property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getAccumulateOverCLIRBefore() {
        return accumulateOverCLIRBefore;
    }

    /**
     * Sets the value of the accumulateOverCLIRBefore property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setAccumulateOverCLIRBefore(Integer value) {
        this.accumulateOverCLIRBefore = value;
    }

    /**
     * Gets the value of the accumulateOverCLIRAfter property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getAccumulateOverCLIRAfter() {
        return accumulateOverCLIRAfter;
    }

    /**
     * Sets the value of the accumulateOverCLIRAfter property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setAccumulateOverCLIRAfter(Integer value) {
        this.accumulateOverCLIRAfter = value;
    }

    /**
     * Gets the value of the amountDomesticPackage property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getAmountDomesticPackage() {
        return amountDomesticPackage;
    }

    /**
     * Sets the value of the amountDomesticPackage property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setAmountDomesticPackage(Long value) {
        this.amountDomesticPackage = value;
    }

    /**
     * Gets the value of the amountIRPackage property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getAmountIRPackage() {
        return amountIRPackage;
    }

    /**
     * Sets the value of the amountIRPackage property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setAmountIRPackage(Long value) {
        this.amountIRPackage = value;
    }

    /**
     * Gets the value of the amountOverCLDomesticBefore property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getAmountOverCLDomesticBefore() {
        return amountOverCLDomesticBefore;
    }

    /**
     * Sets the value of the amountOverCLDomesticBefore property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setAmountOverCLDomesticBefore(Long value) {
        this.amountOverCLDomesticBefore = value;
    }

    /**
     * Gets the value of the amountOverCLDomesticAfter property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getAmountOverCLDomesticAfter() {
        return amountOverCLDomesticAfter;
    }

    /**
     * Sets the value of the amountOverCLDomesticAfter property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setAmountOverCLDomesticAfter(Long value) {
        this.amountOverCLDomesticAfter = value;
    }

    /**
     * Gets the value of the amountOverCLIRBefore property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getAmountOverCLIRBefore() {
        return amountOverCLIRBefore;
    }

    /**
     * Sets the value of the amountOverCLIRBefore property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setAmountOverCLIRBefore(Long value) {
        this.amountOverCLIRBefore = value;
    }

    /**
     * Gets the value of the amountOverCLIRAfter property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getAmountOverCLIRAfter() {
        return amountOverCLIRAfter;
    }

    /**
     * Sets the value of the amountOverCLIRAfter property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setAmountOverCLIRAfter(Long value) {
        this.amountOverCLIRAfter = value;
    }

    /**
     * Gets the value of the overCL property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOverCL() {
        return overCL;
    }

    /**
     * Sets the value of the overCL property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOverCL(String value) {
        this.overCL = value;
    }

}
