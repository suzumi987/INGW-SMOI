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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import phoebe.eqx.smoi.wsdl.CommonComponents.AdjustLogList;
import phoebe.eqx.smoi.wsdl.CommonComponents.MaxFreeResourceLogList;
import phoebe.eqx.smoi.wsdl.CommonComponents.NegativeBalanceLogList;
import phoebe.eqx.smoi.wsdl.CommonComponents.PaymentLogList;
import phoebe.eqx.smoi.wsdl.CommonComponents.RechargeLogList;
import phoebe.eqx.smoi.wsdl.CommonComponents.SResultDescription;
import phoebe.eqx.smoi.wsdl.CommonComponents.SingleBalanceLogList;
import phoebe.eqx.smoi.wsdl.CommonComponents.TransferLogList;


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
 *         &lt;element name="sResult" type="{http://www.asiainfo.com/obd/CommonComponents.obd}SResultDescription"/>
 *         &lt;element name="RechargeLogList" type="{http://www.asiainfo.com/obd/CommonComponents.obd}RechargeLogList" minOccurs="0"/>
 *         &lt;element name="AdjustLogList" type="{http://www.asiainfo.com/obd/CommonComponents.obd}AdjustLogList" minOccurs="0"/>
 *         &lt;element name="TransferLogList" type="{http://www.asiainfo.com/obd/CommonComponents.obd}TransferLogList" minOccurs="0"/>
 *         &lt;element name="PaymentLogList" type="{http://www.asiainfo.com/obd/CommonComponents.obd}PaymentLogList" minOccurs="0"/>
 *         &lt;element name="NegativeBalanceLogList" type="{http://www.asiainfo.com/obd/CommonComponents.obd}NegativeBalanceLogList" minOccurs="0"/>
 *         &lt;element name="MaxFreeResourceLogList" type="{http://www.asiainfo.com/obd/CommonComponents.obd}MaxFreeResourceLogList" minOccurs="0"/>
 *         &lt;element name="SingleBalanceLogList" type="{http://www.asiainfo.com/obd/CommonComponents.obd}SingleBalanceLogList" minOccurs="0"/>
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
    "sResult",
    "rechargeLogList",
    "adjustLogList",
    "transferLogList",
    "paymentLogList",
    "negativeBalanceLogList",
    "maxFreeResourceLogList",
    "singleBalanceLogList"
})
@XmlRootElement(name = "do_QueryWorkLogResponse", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
public class DoQueryWorkLogResponse {

    @XmlElement(namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd", required = true)
    protected SResultDescription sResult;
    @XmlElement(name = "RechargeLogList", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected RechargeLogList rechargeLogList;
    @XmlElement(name = "AdjustLogList", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected AdjustLogList adjustLogList;
    @XmlElement(name = "TransferLogList", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected TransferLogList transferLogList;
    @XmlElement(name = "PaymentLogList", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected PaymentLogList paymentLogList;
    @XmlElement(name = "NegativeBalanceLogList", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected NegativeBalanceLogList negativeBalanceLogList;
    @XmlElement(name = "MaxFreeResourceLogList", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected MaxFreeResourceLogList maxFreeResourceLogList;
    @XmlElement(name = "SingleBalanceLogList", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected SingleBalanceLogList singleBalanceLogList;

    /**
     * Gets the value of the sResult property.
     * 
     * @return
     *     possible object is
     *     {@link SResultDescription }
     *     
     */
    public SResultDescription getSResult() {
        return sResult;
    }

    /**
     * Sets the value of the sResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link SResultDescription }
     *     
     */
    public void setSResult(SResultDescription value) {
        this.sResult = value;
    }

    /**
     * Gets the value of the rechargeLogList property.
     * 
     * @return
     *     possible object is
     *     {@link RechargeLogList }
     *     
     */
    public RechargeLogList getRechargeLogList() {
        return rechargeLogList;
    }

    /**
     * Sets the value of the rechargeLogList property.
     * 
     * @param value
     *     allowed object is
     *     {@link RechargeLogList }
     *     
     */
    public void setRechargeLogList(RechargeLogList value) {
        this.rechargeLogList = value;
    }

    /**
     * Gets the value of the adjustLogList property.
     * 
     * @return
     *     possible object is
     *     {@link AdjustLogList }
     *     
     */
    public AdjustLogList getAdjustLogList() {
        return adjustLogList;
    }

    /**
     * Sets the value of the adjustLogList property.
     * 
     * @param value
     *     allowed object is
     *     {@link AdjustLogList }
     *     
     */
    public void setAdjustLogList(AdjustLogList value) {
        this.adjustLogList = value;
    }

    /**
     * Gets the value of the transferLogList property.
     * 
     * @return
     *     possible object is
     *     {@link TransferLogList }
     *     
     */
    public TransferLogList getTransferLogList() {
        return transferLogList;
    }

    /**
     * Sets the value of the transferLogList property.
     * 
     * @param value
     *     allowed object is
     *     {@link TransferLogList }
     *     
     */
    public void setTransferLogList(TransferLogList value) {
        this.transferLogList = value;
    }

    /**
     * Gets the value of the paymentLogList property.
     * 
     * @return
     *     possible object is
     *     {@link PaymentLogList }
     *     
     */
    public PaymentLogList getPaymentLogList() {
        return paymentLogList;
    }

    /**
     * Sets the value of the paymentLogList property.
     * 
     * @param value
     *     allowed object is
     *     {@link PaymentLogList }
     *     
     */
    public void setPaymentLogList(PaymentLogList value) {
        this.paymentLogList = value;
    }

    /**
     * Gets the value of the negativeBalanceLogList property.
     * 
     * @return
     *     possible object is
     *     {@link NegativeBalanceLogList }
     *     
     */
    public NegativeBalanceLogList getNegativeBalanceLogList() {
        return negativeBalanceLogList;
    }

    /**
     * Sets the value of the negativeBalanceLogList property.
     * 
     * @param value
     *     allowed object is
     *     {@link NegativeBalanceLogList }
     *     
     */
    public void setNegativeBalanceLogList(NegativeBalanceLogList value) {
        this.negativeBalanceLogList = value;
    }

    /**
     * Gets the value of the maxFreeResourceLogList property.
     * 
     * @return
     *     possible object is
     *     {@link MaxFreeResourceLogList }
     *     
     */
    public MaxFreeResourceLogList getMaxFreeResourceLogList() {
        return maxFreeResourceLogList;
    }

    /**
     * Sets the value of the maxFreeResourceLogList property.
     * 
     * @param value
     *     allowed object is
     *     {@link MaxFreeResourceLogList }
     *     
     */
    public void setMaxFreeResourceLogList(MaxFreeResourceLogList value) {
        this.maxFreeResourceLogList = value;
    }

    /**
     * Gets the value of the singleBalanceLogList property.
     * 
     * @return
     *     possible object is
     *     {@link SingleBalanceLogList }
     *     
     */
    public SingleBalanceLogList getSingleBalanceLogList() {
        return singleBalanceLogList;
    }

    /**
     * Sets the value of the singleBalanceLogList property.
     * 
     * @param value
     *     allowed object is
     *     {@link SingleBalanceLogList }
     *     
     */
    public void setSingleBalanceLogList(SingleBalanceLogList value) {
        this.singleBalanceLogList = value;
    }

}
