package ayu.edu.tr.iskolik.kategori.application.model.request;

import ayu.edu.tr.iskolik.common.application.model.request.validation.PostValidation;
import ayu.edu.tr.iskolik.common.application.model.request.validation.PutValidation;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;

public class KategoriRequest {

//	@NotNull(message = "validation.request.field.null", groups = {PutValidation.class, Default.class})
	private Long kategoriId;

//	@NotEmpty(message = "validation.request.field.null", groups = {PostValidation.class, PutValidation.class, Default.class})
	private String ad;

//	@NotEmpty(message = "validation.request.field.null", groups = {PostValidation.class, PutValidation.class, Default.class})
	private String aciklama;

	private KategoriRequest ataKategori;

	/* getters-setters */
	public Long getKategoriId() {
		return kategoriId;
	}

	public void setKategoriId(Long kategoriId) {
		this.kategoriId = kategoriId;
	}

	public String getAd() {
		return ad;
	}

	public void setAd(String ad) {
		this.ad = ad;
	}

	public String getAciklama() {
		return aciklama;
	}

	public void setAciklama(String aciklama) {
		this.aciklama = aciklama;
	}

	public KategoriRequest getAtaKategori() {
		return ataKategori;
	}

	public void setAtaKategori(KategoriRequest ataKategori) {
		this.ataKategori = ataKategori;
	}
}
