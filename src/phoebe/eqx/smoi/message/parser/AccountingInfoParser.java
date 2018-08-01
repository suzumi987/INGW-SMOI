package phoebe.eqx.smoi.message.parser;

import ec02.utils.Log;

public class AccountingInfoParser {

    private boolean isSuccessParsing;
    private String err_msg;
    private String ds2cbpLocation;
    private String ds2serviceLocation;

    public AccountingInfoParser(String info) {
        this.setErr_msg("");
        this.setDs2cbpLocation("");
        this.setDs2serviceLocation("");
        this.setSuccessParsing(true);
        this.parseAccountingInfo(info);
    }

    public void setDs2cbpLocation(String ds2cbpLocation) {
        this.ds2cbpLocation = ds2cbpLocation;
    }

    public String getDs2cbpLocation() {
        return ds2cbpLocation;
    }

    public void setDs2serviceLocation(String ds2serviceLocation) {
        this.ds2serviceLocation = ds2serviceLocation;
    }

    public String getDs2serviceLocation() {
        return ds2serviceLocation;
    }

    public void setSuccessParsing(boolean isSuccessParsing) {
        this.isSuccessParsing = isSuccessParsing;
    }

    public boolean isSuccessParsing() {
        return isSuccessParsing;
    }

    public void setErr_msg(String err_msg) {
        this.err_msg = err_msg;
    }

    public String getErr_msg() {
        return err_msg;
    }

    private void parseAccountingInfo(String info) {
        String[] data = info.split(",");
        if (data.length > 5) {
            this.setDs2cbpLocation(data[5]);
        }
        if (data.length > 1) {
            this.setDs2serviceLocation(data[1]);
        }

        if (this.getDs2cbpLocation().equals("") && this.getDs2serviceLocation().equals("")) {
            this.setSuccessParsing(false);
            this.setErr_msg("AccountingInfo is invalid!! : " + info);
            Log.d("AccountingInfo is invalid!! : " + info);
        }

    }
}
