package ayu.edu.tr.iskolik.profil.application.model.request;

import ayu.edu.tr.iskolik.common.application.model.request.validation.PostValidation;
import ayu.edu.tr.iskolik.common.application.model.request.validation.PutValidation;
import ayu.edu.tr.iskolik.kategori.application.model.request.KategoriRequest;
import ayu.edu.tr.iskolik.kategori.domain.model.dto.KategoriDTO;
import javax.validation.constraints.NotEmpty;
import javax.validation.groups.Default;

public class YetenekRequest {

//	@NotNull(message = "validation.request.field.null", groups = {PostValidation.class, PutValidation.class, Default.class})
	private KategoriRequest kategori;

	@NotEmpty(message = "validation.request.field.null", groups = {PostValidation.class, PutValidation.class, Default.class})
	private String aciklama;

	/* getters-setters */
	public KategoriRequest getKategori() {
		return kategori;
	}

	public void setKategori(KategoriRequest kategori) {
		this.kategori = kategori;
	}

	public String getAciklama() {
		return aciklama;
	}

	public void setAciklama(String aciklama) {
		this.aciklama = aciklama;
	}
}
