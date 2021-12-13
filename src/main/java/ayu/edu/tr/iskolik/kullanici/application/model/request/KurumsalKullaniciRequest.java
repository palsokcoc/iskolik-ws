package ayu.edu.tr.iskolik.kullanici.application.model.request;

import ayu.edu.tr.iskolik.common.application.model.request.validation.PostValidation;
import ayu.edu.tr.iskolik.common.application.model.request.validation.PutValidation;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;

public class KurumsalKullaniciRequest extends KullaniciRequest {

	@NotNull(message = "validation.request.field.null", groups = {PostValidation.class, PutValidation.class, Default.class})
	private int calisanSayisi;

	/* getters-setters */
	public int getCalisanSayisi() {
		return calisanSayisi;
	}

	public void setCalisanSayisi(int calisanSayisi) {
		this.calisanSayisi = calisanSayisi;
	}
}
