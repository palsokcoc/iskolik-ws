package ayu.edu.tr.iskolik.kullanici.application.model.request;

import ayu.edu.tr.iskolik.common.application.model.request.validation.PostValidation;
import ayu.edu.tr.iskolik.common.application.model.request.validation.PutValidation;
import ayu.edu.tr.iskolik.kullanici.domain.model.entity.BireyselKullanici.Cinsiyet;
import ayu.edu.tr.iskolik.profil.application.model.request.ProfilRequest;
import ayu.edu.tr.iskolik.profil.domain.model.entity.Profil;
import java.time.LocalDate;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;

public class BireyselKullaniciRequest extends KullaniciRequest {

	@NotNull(message = "validation.request.field.null", groups = {PostValidation.class, PutValidation.class, Default.class})
	private String soyad;

	@NotNull(message = "validation.request.field.null", groups = {PostValidation.class, PutValidation.class, Default.class})
	private Cinsiyet cinsiyet;

	@NotNull(message = "validation.request.field.null", groups = {PostValidation.class, PutValidation.class, Default.class})
	private LocalDate dogumTarihi;

	private ProfilRequest profil;

	/* getters-setters */
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

	public ProfilRequest getProfil() {
		return profil;
	}

	public void setProfil(ProfilRequest profil) {
		this.profil = profil;
	}
}
