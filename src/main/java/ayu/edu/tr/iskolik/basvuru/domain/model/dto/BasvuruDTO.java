package ayu.edu.tr.iskolik.basvuru.domain.model.dto;

import ayu.edu.tr.iskolik.basvuru.domain.model.entity.Basvuru.Durum;
import ayu.edu.tr.iskolik.ilan.domain.model.dto.IlanDTO;
import ayu.edu.tr.iskolik.kullanici.domain.model.dto.BireyselKullaniciDTO;
import java.time.LocalDate;

public class BasvuruDTO {

	private Long basvuruId;
	private BireyselKullaniciDTO kullanici;
	private IlanDTO ilan;
	private LocalDate basvuruTarihi;
	private LocalDate iptalTarihi;
	private Durum durum;

	/* getters-setters */
	public Long getBasvuruId() {
		return basvuruId;
	}

	public void setBasvuruId(Long basvuruId) {
		this.basvuruId = basvuruId;
	}

	public BireyselKullaniciDTO getKullanici() {
		return kullanici;
	}

	public void setKullanici(BireyselKullaniciDTO kullanici) {
		this.kullanici = kullanici;
	}

	public IlanDTO getIlan() {
		return ilan;
	}

	public void setIlan(IlanDTO ilan) {
		this.ilan = ilan;
	}

	public LocalDate getBasvuruTarihi() {
		return basvuruTarihi;
	}

	public void setBasvuruTarihi(LocalDate basvuruTarihi) {
		this.basvuruTarihi = basvuruTarihi;
	}

	public LocalDate getIptalTarihi() {
		return iptalTarihi;
	}

	public void setIptalTarihi(LocalDate iptalTarihi) {
		this.iptalTarihi = iptalTarihi;
	}

	public Durum getDurum() {
		return durum;
	}

	public void setDurum(Durum durum) {
		this.durum = durum;
	}
}
