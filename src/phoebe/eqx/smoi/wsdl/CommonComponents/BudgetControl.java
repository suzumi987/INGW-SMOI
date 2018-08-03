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
 * <p>Java class for BudgetControl complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BudgetControl">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="_busi_service_id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="_budget_mode" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="16"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="_budget_type" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="50"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="_budget" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="_remain_budget" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="_temp_budget" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="_action" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="Bar"/>
 *               &lt;enumeration value="Continue"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="ThresholdList" type="{http://www.asiainfo.com/obd/CommonComponents.obd}ThresholdList" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BudgetControl", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd", propOrder = {
    "busiServiceId",
    "budgetMode",
    "budgetType",
    "budget",
    "remainBudget",
    "tempBudget",
    "action",
    "thresholdList"
})
public class BudgetControl {

    @XmlElement(name = "_busi_service_id", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected int busiServiceId;
    @XmlElement(name = "_budget_mode", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected String budgetMode;
    @XmlElement(name = "_budget_type", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected String budgetType;
    @XmlElement(name = "_budget", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected long budget;
    @XmlElement(name = "_remain_budget", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected Long remainBudget;
    @XmlElement(name = "_temp_budget", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected Long tempBudget;
    @XmlElement(name = "_action", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected String action;
    @XmlElement(name = "ThresholdList", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected ThresholdList thresholdList;

    /**
     * Gets the value of the busiServiceId property.
     * 
     */
    public int getBusiServiceId() {
        return busiServiceId;
    }

    /**
     * Sets the value of the busiServiceId property.
     * 
     */
    public void setBusiServiceId(int value) {
        this.busiServiceId = value;
    }

    /**
     * Gets the value of the budgetMode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBudgetMode() {
        return budgetMode;
    }

    /**
     * Sets the value of the budgetMode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBudgetMode(String value) {
        this.budgetMode = value;
    }

    /**
     * Gets the value of the budgetType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBudgetType() {
        return budgetType;
    }

    /**
     * Sets the value of the budgetType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBudgetType(String value) {
        this.budgetType = value;
    }

    /**
     * Gets the value of the budget property.
     * 
     */
    public long getBudget() {
        return budget;
    }

    /**
     * Sets the value of the budget property.
     * 
     */
    public void setBudget(long value) {
        this.budget = value;
    }

    /**
     * Gets the value of the remainBudget property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getRemainBudget() {
        return remainBudget;
    }

    /**
     * Sets the value of the remainBudget property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setRemainBudget(Long value) {
        this.remainBudget = value;
    }

    /**
     * Gets the value of the tempBudget property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getTempBudget() {
        return tempBudget;
    }

    /**
     * Sets the value of the tempBudget property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setTempBudget(Long value) {
        this.tempBudget = value;
    }

    /**
     * Gets the value of the action property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAction() {
        return action;
    }

    /**
     * Sets the value of the action property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAction(String value) {
        this.action = value;
    }

    /**
     * Gets the value of the thresholdList property.
     * 
     * @return
     *     possible object is
     *     {@link ThresholdList }
     *     
     */
    public ThresholdList getThresholdList() {
        return thresholdList;
    }

    /**
     * Sets the value of the thresholdList property.
     * 
     * @param value
     *     allowed object is
     *     {@link ThresholdList }
     *     
     */
    public void setThresholdList(ThresholdList value) {
        this.thresholdList = value;
    }

}
