package ayu.edu.tr.iskolik.rapor.domain.model.dto;

import ayu.edu.tr.iskolik.ilan.domain.model.entity.Ilan.Durum;

public class EnCokBasvuruYapilanIlanlarDTO {

	private String kurum;
	private String unvan;
	private String yer;
	private Durum durum;
	private long adet;

	public EnCokBasvuruYapilanIlanlarDTO(String kurum, String unvan, String yer, Durum durum, long adet) {
		this.kurum = kurum;
		this.unvan = unvan;
		this.yer = yer;
		this.durum = durum;
		this.adet = adet;
	}

	/* getters-setters */
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

	public long getAdet() {
		return adet;
	}

	public void setAdet(long adet) {
		this.adet = adet;
	}
}
