package ayu.edu.tr.iskolik.kullanici.domain.model.dto;

import ayu.edu.tr.iskolik.kullanici.domain.model.entity.BireyselKullanici.Cinsiyet;
import java.time.LocalDate;

public class BireyselKullaniciDTO extends KullaniciDTO {

	private String ad;
	private String soyad;
	private Cinsiyet cinsiyet;
	private LocalDate dogumTarihi;

	public BireyselKullaniciDTO() {
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
