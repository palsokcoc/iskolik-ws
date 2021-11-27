package ayu.edu.tr.iskolik.iletisim.domain.model.dto;

import ayu.edu.tr.iskolik.iletisim.domain.model.entity.Adres;

public class IletisimDTO {

	private Long iletisimId;
	private Adres adres;
	private String cepTelefonu;
	private String sabitTelefon;

	/* getters-setters */
	public Long getIletisimId() {
		return iletisimId;
	}

	public void setIletisimId(Long iletisimId) {
		this.iletisimId = iletisimId;
	}

	public Adres getAdres() {
		return adres;
	}

	public void setAdres(Adres adres) {
		this.adres = adres;
	}

	public String getCepTelefonu() {
		return cepTelefonu;
	}

	public void setCepTelefonu(String cepTelefonu) {
		this.cepTelefonu = cepTelefonu;
	}

	public String getSabitTelefon() {
		return sabitTelefon;
	}

	public void setSabitTelefon(String sabitTelefon) {
		this.sabitTelefon = sabitTelefon;
	}
}
