/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package phoebe.eqx.smoi.bean;

import java.util.ArrayList;
import phoebe.eqx.smoi.bean.instance.SmoiInstance;

/**
 *
 * @author Pawarich A.
 */
public class MyAppData {

    private String ret;
    private long timeout = 0;
    private ArrayList<EquinoxMessage> equinoxMessagelists;
    private SmoiInstance smoiIns = null;
    private String input_Msg;
    private String input_InvokeId;
    private String input_state;
    private String input_resultcode;
    private String input_desc;
    private String output_Msg;
    
    public MyAppData() {
        equinoxMessagelists = new ArrayList<EquinoxMessage>();
    }

    /**
     * @return the equinoxMessagelists
     */
    public ArrayList<EquinoxMessage> getEquinoxMessagelists() {
        return equinoxMessagelists;
    }

    /**
     * @param equinoxMessagelists the equinoxMessagelists to set
     */
    public void setEquinoxMessagelists(ArrayList<EquinoxMessage> equinoxMessagelists) {
        this.equinoxMessagelists = equinoxMessagelists;
    }

    /**
     * @return the smoiIns
     */
    public SmoiInstance getSmoiIns() {
        return smoiIns;
    }

    /**
     * @param smoiIns the smoiIns to set
     */
    public void setSmoiIns(SmoiInstance smoiIns) {
        this.smoiIns = smoiIns;
    }

    /**
     * @return the ret
     */
    public String getRet() {
        return ret;
    }

    /**
     * @param ret the ret to set
     */
    public void setRet(String ret) {
        this.ret = ret;
    }

    /**
     * @return the timeout
     */
    public long getTimeout() {
        return timeout;
    }

    /**
     * @param timeout the timeout to set
     */
    public void setTimeout(long _timeout) {
        if (_timeout > this.timeout) {
            this.timeout = _timeout;
        }
    }

    /**
     * @return the input_Msg
     */
    public String getInput_Msg() {
        return input_Msg;
    }

    /**
     * @param input_Msg the input_Msg to set
     */
    public void setInput_Msg(String input_Msg) {
        this.input_Msg = input_Msg;
    }

    /**
     * @return the input_InvokeId
     */
    public String getInput_InvokeId() {
        return input_InvokeId;
    }

    /**
     * @param input_InvokeId the input_InvokeId to set
     */
    public void setInput_InvokeId(String input_InvokeId) {
        this.input_InvokeId = input_InvokeId;
    }

    /**
     * @return the input_state
     */
    public String getInput_state() {
        return input_state;
    }

    /**
     * @param input_state the input_state to set
     */
    public void setInput_state(String input_state) {
        this.input_state = input_state;
    }

    /**
     * @return the input_resultcode
     */
    public String getInput_resultcode() {
        return input_resultcode;
    }

    /**
     * @param input_resultcode the input_resultcode to set
     */
    public void setInput_resultcode(String input_resultcode) {
        this.input_resultcode = input_resultcode;
    }

    /**
     * @return the input_desc
     */
    public String getInput_desc() {
        return input_desc;
    }

    /**
     * @param input_desc the input_desc to set
     */
    public void setInput_desc(String input_desc) {
        this.input_desc = input_desc;
    }

    /**
     * @return the output_Msg
     */
    public String getOutput_Msg() {
        return output_Msg;
    }

    /**
     * @param output_Msg the output_Msg to set
     */
    public void setOutput_Msg(String output_Msg) {
        this.output_Msg = output_Msg;
    }

}
