package ayu.edu.tr.iskolik.common.model.response;

/**
 * Kurum içi standartları uymak için platform.web2 paketinde tanımlanmış
 * olan tr.gov.tcmb.web.model.ValidationError modelinden kopyalandı.
 */
public class ValidationError {
	public String id;
	public String msg;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMsg() {
		return this.msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public ValidationError(String id, String msg) {
		this.id = id;
		this.msg = msg;
	}
}
