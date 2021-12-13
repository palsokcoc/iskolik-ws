package ayu.edu.tr.iskolik.basvuru.application.model.request;

import ayu.edu.tr.iskolik.basvuru.domain.model.entity.Basvuru.Durum;
import ayu.edu.tr.iskolik.common.application.model.request.validation.PostValidation;
import ayu.edu.tr.iskolik.common.application.model.request.validation.PutValidation;
import ayu.edu.tr.iskolik.ilan.application.model.request.IlanRequest;
import ayu.edu.tr.iskolik.kullanici.application.model.request.BireyselKullaniciRequest;
import java.time.LocalDate;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;

public class BasvuruRequest {

	private Long basvuruId;

	@NotNull(message = "validation.request.field.null", groups = {PostValidation.class, PutValidation.class, Default.class})
	private BireyselKullaniciRequest kullanici;

	@NotNull(message = "validation.request.field.null", groups = {PostValidation.class, PutValidation.class, Default.class})
	private IlanRequest ilan;

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

	public BireyselKullaniciRequest getKullanici() {
		return kullanici;
	}

	public void setKullanici(BireyselKullaniciRequest kullanici) {
		this.kullanici = kullanici;
	}

	public IlanRequest getIlan() {
		return ilan;
	}

	public void setIlan(IlanRequest ilan) {
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
