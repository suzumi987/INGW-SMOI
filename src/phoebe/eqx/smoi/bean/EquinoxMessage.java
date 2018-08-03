/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package phoebe.eqx.smoi.bean;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author pavarisa
 */
public class EquinoxMessage {
    /*
     * RawDataAttributes
     *  to
     *  name
     *  type
     *  ctype
     *  invoke
     *  val
     *  oid
     *  dn
     *  other
     */

    private Map<String, String> rawDataAttributes = new HashMap<String, String>();
    private String message="";

    public void setEqxAttribute_To(String _to) {
        this.rawDataAttributes.put("to", _to);
    }

    public void setEqxAttribute_Name(String _name) {
        this.rawDataAttributes.put("name", _name);
    }

    public void setEqxAttribute_Type(String _type) {
        this.rawDataAttributes.put("type", _type);
    }

    public void setEqxAttribute_Ctype(String _ctype) {
        this.rawDataAttributes.put("ctype", _ctype);
    }

    public void setEqxAttribute_Invoke(String _invoke) {
        this.rawDataAttributes.put("invoke", _invoke);
    }

    public void setEqxAttribute_Val(String _val) {
        this.rawDataAttributes.put("val", _val);
    }

    public void setEqxAttribute_Oid(String _oid) {
        this.rawDataAttributes.put("oid", _oid);
    }

    public void setEqxAttribute_Dn(String _dn) {
        this.rawDataAttributes.put("dn", _dn);
    }

    public void setEqxAttribute_Other(String attributeName, String attributeValue) {
        this.rawDataAttributes.put(attributeName, attributeValue);
    }

    /**
     * @return the rawDataAttributes
     */
    public Map<String, String> getRawDataAttributes() {
        return rawDataAttributes;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
