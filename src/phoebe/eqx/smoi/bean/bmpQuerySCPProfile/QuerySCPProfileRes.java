package phoebe.eqx.smoi.bean.bmpQuerySCPProfile;

/**
 * Created by Mote'Sarut on 26/5/2560.
 */
public class QuerySCPProfileRes {

    private String so_nbr;
    private String result_code;
    private String finish_date;

    public String getInterIntraflag() {
        return interIntraflag;
    }

    public void setInterIntraflag(String interIntraflag) {
        this.interIntraflag = interIntraflag;
    }

    private String interIntraflag;

    public String getSo_nbr() {
        return so_nbr;
    }

    public void setSo_nbr(String so_nbr) {
        this.so_nbr = so_nbr;
    }

    public String getResult_code() {
        return result_code;
    }

    public void setResult_code(String result_code) {
        this.result_code = result_code;
    }

    public String getFinish_date() {
        return finish_date;
    }

    public void setFinish_date(String finish_date) {
        this.finish_date = finish_date;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("QuerySCPProfileRes [so_nbr = ");
        builder.append(so_nbr);
        builder.append(" , result_code = ");
        builder.append(result_code);
        builder.append(" , finish_date = ");
        builder.append(finish_date);
        builder.append("]");
        return builder.toString();
    }
}
