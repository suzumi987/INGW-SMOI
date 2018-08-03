/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package phoebe.eqx.smoi.bean.instance;

/**
 *
 * @author pavarisa
 */
public class ClientRequestInfo {
    private String invokeid;
    private long requestTime;
    private String requestMessage;
    private String resourceName;
    private String sessionId;
    /**
     * @return the invokeid
     */
    public String getInvokeid() {
        return invokeid;
    }

    /**
     * @param invokeid the invokeid to set
     */
    public void setInvokeid(String invokeid) {
        this.invokeid = invokeid;
    }

    /**
     * @return the requestTime
     */
    public long getRequestTime() {
        return requestTime;
    }

    /**
     * @param requestTime the requestTime to set
     */
    public void setRequestTime(long requestTime) {
        this.requestTime = requestTime;
    }

    /**
     * @return the requestMessage
     */
    public String getRequestMessage() {
        return requestMessage;
    }

    /**
     * @param requestMessage the requestMessage to set
     */
    public void setRequestMessage(String requestMessage) {
        this.requestMessage = requestMessage;
    }

    /**
     * @return the resourceName
     */
    public String getResourceName() {
        return resourceName;
    }

    /**
     * @param resourceName the resourceName to set
     */
    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    /**
     * @return the sessionId
     */
    public String getSessionId() {
        return sessionId;
    }

    /**
     * @param sessionId the sessionId to set
     */
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
