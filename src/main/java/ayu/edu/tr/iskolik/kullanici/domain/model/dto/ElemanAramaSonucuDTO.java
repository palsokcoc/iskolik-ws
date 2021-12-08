	package ayu.edu.tr.iskolik.kullanici.domain.model.dto;

import ayu.edu.tr.iskolik.kullanici.domain.model.entity.BireyselKullanici.Cinsiyet;
import java.time.LocalDate;

public class ElemanAramaSonucuDTO {
	private Long kullaniciId;
	private String ad;
	private String soyad;
	private LocalDate dogumTarihi;
	private Cinsiyet cinsiyet;
	private String ozgecmis;

	public ElemanAramaSonucuDTO() {
		super();
	}

	public ElemanAramaSonucuDTO(Long kullaniciId, String ad, String soyad, LocalDate dogumTarihi, Cinsiyet cinsiyet) {
		this.kullaniciId = kullaniciId;
		this.ad = ad;
		this.soyad = soyad;
		this.dogumTarihi = dogumTarihi;
		this.cinsiyet = cinsiyet;
//		this.ozgecmis = ozgecmis;
	}

	/* getters-setters */
	public Long getKullaniciId() {
		return kullaniciId;
	}

	public void setKullaniciId(Long kullaniciId) {
		this.kullaniciId = kullaniciId;
	}

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

	public LocalDate getDogumTarihi() {
		return dogumTarihi;
	}

	public void setDogumTarihi(LocalDate dogumTarihi) {
		this.dogumTarihi = dogumTarihi;
	}

	public Cinsiyet getCinsiyet() {
		return cinsiyet;
	}

	public void setCinsiyet(Cinsiyet cinsiyet) {
		this.cinsiyet = cinsiyet;
	}

	public String getOzgecmis() {
		return ozgecmis;
	}

	public void setOzgecmis(String ozgecmis) {
		this.ozgecmis = ozgecmis;
	}
}
