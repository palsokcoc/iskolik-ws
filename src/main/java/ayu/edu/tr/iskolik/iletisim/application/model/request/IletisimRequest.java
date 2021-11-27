package ayu.edu.tr.iskolik.iletisim.application.model.request;

import ayu.edu.tr.iskolik.common.application.model.request.validation.PostValidation;
import ayu.edu.tr.iskolik.common.application.model.request.validation.PutValidation;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;

public class IletisimRequest {

	@NotNull(message = "validation.request.field.null", groups = {PutValidation.class, Default.class})
	private Long iletisimId;

	@NotNull(message = "validation.request.field.null", groups = {PostValidation.class, PutValidation.class, Default.class})
	@Valid
	private AdresRequest adres;

	@NotEmpty(message = "validation.request.field.null", groups = {PostValidation.class, PutValidation.class, Default.class})
	private String cepTelefonu;

	@NotEmpty(message = "validation.request.field.null", groups = {PostValidation.class, PutValidation.class, Default.class})
	private String sabitTelefon;

	/* getters-setters */
	public Long getIletisimId() {
		return iletisimId;
	}

	public void setIletisimId(Long iletisimId) {
		this.iletisimId = iletisimId;
	}

	public AdresRequest getAdres() {
		return adres;
	}

	public void setAdres(AdresRequest adres) {
		this.adres = adres;
	}

	public String getCepTelefonu() {
		return cepTelefonu;
	}

	public void setCepTelefonu(String cepTelefonu) {
		this.cepTelefonu = cepTelefonu;
	}

	public String getSabitTelefon() {
		return sabitTelefon;
	}

	public void setSabitTelefon(String sabitTelefon) {
		this.sabitTelefon = sabitTelefon;
	}
}
