package phoebe.eqx.smoi.message.builder;

import java.util.Map;
import java.util.TreeMap;
import phoebe.eqx.abstracts.AEQXMsg;
import phoebe.eqx.enums.ldap.ds2a.EDS2A_InquirySubscriberRequest;
import phoebe.eqx.interfaces.ldap.ds2a.IDS2A_InquirySubscriberRequest;

public class InquirySubscriberRequest extends AEQXMsg implements IDS2A_InquirySubscriberRequest {

    private String methodVersion;
    private String msisdn;

    public void setMethodVersion(String methodVersion) {
        this.methodVersion = methodVersion;
    }

    public String getMethodVersion() {
        return methodVersion;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getMsisdn() {
        return msisdn;
    }

    @Override
    public Map<EDS2A_InquirySubscriberRequest, String> getInquirySubscriberReqBody() {
        Map<EDS2A_InquirySubscriberRequest, String> body = new TreeMap<EDS2A_InquirySubscriberRequest, String>();
        body.put(EDS2A_InquirySubscriberRequest.METHOD_VERSION, this.getMethodVersion());
        body.put(EDS2A_InquirySubscriberRequest.MSISDN_INFO, this.getMsisdn());
        return body;
    }
}
