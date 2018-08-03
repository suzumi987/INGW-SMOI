package phoebe.eqx.smoi.bean.bmpManageSCPProfile;


/**
 * Created by Mote'Sarut on 25/11/2559.
 */
public class ManageSCPProfileRes {

    private String so_nbr;
    private String result_code;
    private String finish_date;
    private String bos_so_nbr;
    private String error_msg;
    private String hint;
    
	public String getBos_so_nbr() {
		return bos_so_nbr;
	}
	public void setBos_so_nbr(String bos_so_nbr) {
		this.bos_so_nbr = bos_so_nbr;
	}
	public String getError_msg() {
		return error_msg;
	}
	public void setError_msg(String error_msg) {
		this.error_msg = error_msg;
	}
	public String getHint() {
		return hint;
	}
	public void setHint(String hint) {
		this.hint = hint;
	}
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
		builder.append("ManageSCPProfileRes [so_nbr=");
		builder.append(so_nbr);
		builder.append(", result_code=");
		builder.append(result_code);
		builder.append(", finish_date=");
		builder.append(finish_date);
		builder.append(", bos_so_nbr=");
		builder.append(bos_so_nbr);
		builder.append(", error_msg=");
		builder.append(error_msg);
		builder.append(", hint=");
		builder.append(hint);
		builder.append("]");
		return builder.toString();
	}
    
}
