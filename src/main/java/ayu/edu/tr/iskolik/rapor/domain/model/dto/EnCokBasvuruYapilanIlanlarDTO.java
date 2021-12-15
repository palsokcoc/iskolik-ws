package ayu.edu.tr.iskolik.rapor.domain.model.dto;

import ayu.edu.tr.iskolik.ilan.domain.model.entity.Ilan.Durum;
import java.time.LocalDate;

public class EnCokBasvuruYapilanIlanlarDTO {

	private long ilanId;
	private String kurum;
	private String unvan;
	private String yer;
	private Durum durum;
	private LocalDate yayinTarihi;
	private long adet;

	public EnCokBasvuruYapilanIlanlarDTO(long ilanId, String kurum, String unvan, String yer, Durum durum, LocalDate yayinTarihi, long adet) {
		this.ilanId = ilanId;
		this.kurum = kurum;
		this.unvan = unvan;
		this.yer = yer;
		this.durum = durum;
		this.yayinTarihi = yayinTarihi;
		this.adet = adet;
	}

	/* getters-setters */
	public long getIlanId() {
		return ilanId;
	}

	public void setIlanId(long ilanId) {
		this.ilanId = ilanId;
	}

	public String getKurum() {
		return kurum;
	}

	public void setKurum(String kurum) {
		this.kurum = kurum;
	}

	public String getUnvan() {
		return unvan;
	}

	public void setUnvan(String unvan) {
		this.unvan = unvan;
	}

	public String getYer() {
		return yer;
	}

	public void setYer(String yer) {
		this.yer = yer;
	}

	public Durum getDurum() {
		return durum;
	}

	public void setDurum(Durum durum) {
		this.durum = durum;
	}

	public LocalDate getYayinTarihi() {
		return yayinTarihi;
	}

	public void setYayinTarihi(LocalDate yayinTarihi) {
		this.yayinTarihi = yayinTarihi;
	}

	public long getAdet() {
		return adet;
	}

	public void setAdet(long adet) {
		this.adet = adet;
	}
}
