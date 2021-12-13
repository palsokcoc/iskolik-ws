package ayu.edu.tr.iskolik.kullanici.domain.model.dto;

import ayu.edu.tr.iskolik.kullanici.domain.model.entity.BireyselKullanici.Cinsiyet;
import ayu.edu.tr.iskolik.profil.domain.model.dto.ProfilDTO;
import ayu.edu.tr.iskolik.profil.domain.model.entity.Profil;
import java.time.LocalDate;

public class BireyselKullaniciDTO extends KullaniciDTO {

	private String soyad;
	private Cinsiyet cinsiyet;
	private LocalDate dogumTarihi;
	private ProfilDTO profil;

	public BireyselKullaniciDTO() {
		setTip("Bireysel");
	}

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

	public ProfilDTO getProfil() {
		return profil;
	}

	public void setProfil(ProfilDTO profil) {
		this.profil = profil;
	}
}
