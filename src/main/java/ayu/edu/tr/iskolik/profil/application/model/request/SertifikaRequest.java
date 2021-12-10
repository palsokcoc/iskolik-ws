package ayu.edu.tr.iskolik.profil.application.model.request;

import ayu.edu.tr.iskolik.common.application.model.request.validation.PostValidation;
import ayu.edu.tr.iskolik.common.application.model.request.validation.PutValidation;
import ayu.edu.tr.iskolik.profil.domain.model.entity.Profil;
import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;

public class SertifikaRequest {

	@NotEmpty(message = "validation.request.field.null", groups = {PostValidation.class, PutValidation.class, Default.class})
	private String sertifikaAdi;

	/* getters-setters */
	public String getSertifikaAdi() {
		return sertifikaAdi;
	}

	public void setSertifikaAdi(String sertifikaAdi) {
		this.sertifikaAdi = sertifikaAdi;
	}
}
