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

import phoebe.eqx.smoi.wsdl.CommonComponents.SResultDescription;


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
 *         &lt;element name="MailGroup" type="{http://www.asiainfo.com/obd/InfoSyncDefines.obd}MailGroupResp" minOccurs="0"/>
 *         &lt;element name="MailGroupMemberList" type="{http://www.asiainfo.com/obd/InfoSyncDefines.obd}MailGroupMemberRespList" minOccurs="0"/>
 *         &lt;element name="MailGroupPost" type="{http://www.asiainfo.com/obd/InfoSyncDefines.obd}MailGroupPostResp" minOccurs="0"/>
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
    "mailGroup",
    "mailGroupMemberList",
    "mailGroupPost"
})
@XmlRootElement(name = "do_QueryMailGroupResponse", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
public class DoQueryMailGroupResponse {

    @XmlElement(namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd", required = true)
    protected SResultDescription sResult;
    @XmlElement(name = "MailGroup", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected MailGroupResp mailGroup;
    @XmlElement(name = "MailGroupMemberList", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected MailGroupMemberRespList mailGroupMemberList;
    @XmlElement(name = "MailGroupPost", namespace = "http://www.asiainfo.com/obd/InfoSyncDefines.obd")
    protected MailGroupPostResp mailGroupPost;

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
     * Gets the value of the mailGroup property.
     * 
     * @return
     *     possible object is
     *     {@link MailGroupResp }
     *     
     */
    public MailGroupResp getMailGroup() {
        return mailGroup;
    }

    /**
     * Sets the value of the mailGroup property.
     * 
     * @param value
     *     allowed object is
     *     {@link MailGroupResp }
     *     
     */
    public void setMailGroup(MailGroupResp value) {
        this.mailGroup = value;
    }

    /**
     * Gets the value of the mailGroupMemberList property.
     * 
     * @return
     *     possible object is
     *     {@link MailGroupMemberRespList }
     *     
     */
    public MailGroupMemberRespList getMailGroupMemberList() {
        return mailGroupMemberList;
    }

    /**
     * Sets the value of the mailGroupMemberList property.
     * 
     * @param value
     *     allowed object is
     *     {@link MailGroupMemberRespList }
     *     
     */
    public void setMailGroupMemberList(MailGroupMemberRespList value) {
        this.mailGroupMemberList = value;
    }

    /**
     * Gets the value of the mailGroupPost property.
     * 
     * @return
     *     possible object is
     *     {@link MailGroupPostResp }
     *     
     */
    public MailGroupPostResp getMailGroupPost() {
        return mailGroupPost;
    }

    /**
     * Sets the value of the mailGroupPost property.
     * 
     * @param value
     *     allowed object is
     *     {@link MailGroupPostResp }
     *     
     */
    public void setMailGroupPost(MailGroupPostResp value) {
        this.mailGroupPost = value;
    }

}
