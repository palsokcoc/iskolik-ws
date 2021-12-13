package ayu.edu.tr.iskolik.basvuru.application.model.request;

import ayu.edu.tr.iskolik.basvuru.domain.model.entity.Basvuru.Durum;
import ayu.edu.tr.iskolik.common.application.model.request.validation.PostValidation;
import ayu.edu.tr.iskolik.common.application.model.request.validation.PutValidation;
import java.time.LocalDate;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;

public class BasvuruRequest {

	private Long basvuruId;

	@NotNull(message = "validation.request.field.null", groups = {PostValidation.class, PutValidation.class, Default.class})
	private Long kullaniciId;

	@NotNull(message = "validation.request.field.null", groups = {PostValidation.class, PutValidation.class, Default.class})
	private Long ilanId;

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

	public Long getKullaniciId() {
		return kullaniciId;
	}

	public void setKullaniciId(Long kullaniciId) {
		this.kullaniciId = kullaniciId;
	}

	public Long getIlanId() {
		return ilanId;
	}

	public void setIlanId(Long ilanId) {
		this.ilanId = ilanId;
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
