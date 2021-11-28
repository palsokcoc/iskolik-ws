package ayu.edu.tr.iskolik.kullanici.application.model.request;

import ayu.edu.tr.iskolik.common.application.model.request.validation.PostValidation;
import ayu.edu.tr.iskolik.common.application.model.request.validation.PutValidation;
import ayu.edu.tr.iskolik.kullanici.domain.model.dto.KullaniciDTO;
import ayu.edu.tr.iskolik.kullanici.domain.model.entity.BireyselKullanici.Cinsiyet;
import java.time.LocalDate;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;

public class BireyselKullaniciRequest extends KullaniciRequest {

	@NotNull(message = "validation.request.field.null", groups = {PostValidation.class, PutValidation.class, Default.class})
	private String ad;

	@NotNull(message = "validation.request.field.null", groups = {PostValidation.class, PutValidation.class, Default.class})
	private String soyad;

	@NotNull(message = "validation.request.field.null", groups = {PostValidation.class, PutValidation.class, Default.class})
	private Cinsiyet cinsiyet;

	@NotNull(message = "validation.request.field.null", groups = {PostValidation.class, PutValidation.class, Default.class})
	private LocalDate dogumTarihi;

	public BireyselKullaniciRequest() {
		setType("Bireysel");
	}

	/* getters-setters */
	public String getAd() {
		return ad;
	}

	public void setAd(String ad) {
		this.ad = ad;
	}

	public String getSoyad() {
		return soyad;
	}

	public void setSoyad(String soyad) {
		this.soyad = soyad;
	}

	public Cinsiyet getCinsiyet() {
		return cinsiyet;
	}

	public void setCinsiyet(Cinsiyet cinsiyet) {
		this.cinsiyet = cinsiyet;
	}

	public LocalDate getDogumTarihi() {
		return dogumTarihi;
	}

	public void setDogumTarihi(LocalDate dogumTarihi) {
		this.dogumTarihi = dogumTarihi;
	}
}
