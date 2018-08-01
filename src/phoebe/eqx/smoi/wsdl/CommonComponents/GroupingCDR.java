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
 * <p>Java class for GroupingCDR complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GroupingCDR">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="_grouping_name" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="30"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="_summary_usage" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="128"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="_summary_preDiscount" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="_summary_totalCharge" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="CallDetailRecordList" type="{http://www.asiainfo.com/obd/CommonComponents.obd}CallDetailRecordList" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GroupingCDR", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd", propOrder = {
    "groupingName",
    "summaryUsage",
    "summaryPreDiscount",
    "summaryTotalCharge",
    "callDetailRecordList"
})
public class GroupingCDR {

    @XmlElement(name = "_grouping_name", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected String groupingName;
    @XmlElement(name = "_summary_usage", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected String summaryUsage;
    @XmlElement(name = "_summary_preDiscount", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected Long summaryPreDiscount;
    @XmlElement(name = "_summary_totalCharge", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected Long summaryTotalCharge;
    @XmlElement(name = "CallDetailRecordList", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected CallDetailRecordList callDetailRecordList;

    /**
     * Gets the value of the groupingName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGroupingName() {
        return groupingName;
    }

    /**
     * Sets the value of the groupingName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGroupingName(String value) {
        this.groupingName = value;
    }

    /**
     * Gets the value of the summaryUsage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSummaryUsage() {
        return summaryUsage;
    }

    /**
     * Sets the value of the summaryUsage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSummaryUsage(String value) {
        this.summaryUsage = value;
    }

    /**
     * Gets the value of the summaryPreDiscount property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getSummaryPreDiscount() {
        return summaryPreDiscount;
    }

    /**
     * Sets the value of the summaryPreDiscount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setSummaryPreDiscount(Long value) {
        this.summaryPreDiscount = value;
    }

    /**
     * Gets the value of the summaryTotalCharge property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getSummaryTotalCharge() {
        return summaryTotalCharge;
    }

    /**
     * Sets the value of the summaryTotalCharge property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setSummaryTotalCharge(Long value) {
        this.summaryTotalCharge = value;
    }

    /**
     * Gets the value of the callDetailRecordList property.
     * 
     * @return
     *     possible object is
     *     {@link CallDetailRecordList }
     *     
     */
    public CallDetailRecordList getCallDetailRecordList() {
        return callDetailRecordList;
    }

    /**
     * Sets the value of the callDetailRecordList property.
     * 
     * @param value
     *     allowed object is
     *     {@link CallDetailRecordList }
     *     
     */
    public void setCallDetailRecordList(CallDetailRecordList value) {
        this.callDetailRecordList = value;
    }

}
