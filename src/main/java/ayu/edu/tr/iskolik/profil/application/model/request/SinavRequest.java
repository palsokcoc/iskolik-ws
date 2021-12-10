package ayu.edu.tr.iskolik.profil.application.model.request;

import ayu.edu.tr.iskolik.common.application.model.request.validation.PostValidation;
import ayu.edu.tr.iskolik.common.application.model.request.validation.PutValidation;
import javax.validation.constraints.NotEmpty;
import javax.validation.groups.Default;

public class SinavRequest {

	@NotEmpty(message = "validation.request.field.null", groups = {PostValidation.class, PutValidation.class, Default.class})
	private String sinavAdi;

	@NotEmpty(message = "validation.request.field.null", groups = {PostValidation.class, PutValidation.class, Default.class})
	private String sinavSonucu;

	/* getters-setters */
	public String getSinavAdi() {
		return sinavAdi;
	}

	public void setSinavAdi(String sinavAdi) {
		this.sinavAdi = sinavAdi;
	}

	public String getSinavSonucu() {
		return sinavSonucu;
	}

	public void setSinavSonucu(String sinavSonucu) {
		this.sinavSonucu = sinavSonucu;
	}
}
