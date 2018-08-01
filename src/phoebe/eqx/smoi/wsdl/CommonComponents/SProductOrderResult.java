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
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for SProductOrderResult complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SProductOrderResult">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="_product_sequence_id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="_product_id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="_so_product_id" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="32"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="SParamList" type="{http://www.asiainfo.com/obd/CommonComponents.obd}SParamList" minOccurs="0"/>
 *         &lt;element name="_valid_date" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="_expire_date" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SProductOrderResult", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd", propOrder = {
    "productSequenceId",
    "productId",
    "soProductId",
    "sParamList",
    "validDate",
    "expireDate"
})
public class SProductOrderResult {

    @XmlElement(name = "_product_sequence_id", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected long productSequenceId;
    @XmlElement(name = "_product_id", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected int productId;
    @XmlElement(name = "_so_product_id", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected String soProductId;
    @XmlElement(name = "SParamList", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    protected SParamList sParamList;
    @XmlElement(name = "_valid_date", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar validDate;
    @XmlElement(name = "_expire_date", namespace = "http://www.asiainfo.com/obd/CommonComponents.obd")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar expireDate;

    /**
     * Gets the value of the productSequenceId property.
     * 
     */
    public long getProductSequenceId() {
        return productSequenceId;
    }

    /**
     * Sets the value of the productSequenceId property.
     * 
     */
    public void setProductSequenceId(long value) {
        this.productSequenceId = value;
    }

    /**
     * Gets the value of the productId property.
     * 
     */
    public int getProductId() {
        return productId;
    }

    /**
     * Sets the value of the productId property.
     * 
     */
    public void setProductId(int value) {
        this.productId = value;
    }

    /**
     * Gets the value of the soProductId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSoProductId() {
        return soProductId;
    }

    /**
     * Sets the value of the soProductId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSoProductId(String value) {
        this.soProductId = value;
    }

    /**
     * Gets the value of the sParamList property.
     * 
     * @return
     *     possible object is
     *     {@link SParamList }
     *     
     */
    public SParamList getSParamList() {
        return sParamList;
    }

    /**
     * Sets the value of the sParamList property.
     * 
     * @param value
     *     allowed object is
     *     {@link SParamList }
     *     
     */
    public void setSParamList(SParamList value) {
        this.sParamList = value;
    }

    /**
     * Gets the value of the validDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getValidDate() {
        return validDate;
    }

    /**
     * Sets the value of the validDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setValidDate(XMLGregorianCalendar value) {
        this.validDate = value;
    }

    /**
     * Gets the value of the expireDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getExpireDate() {
        return expireDate;
    }

    /**
     * Sets the value of the expireDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setExpireDate(XMLGregorianCalendar value) {
        this.expireDate = value;
    }

}
