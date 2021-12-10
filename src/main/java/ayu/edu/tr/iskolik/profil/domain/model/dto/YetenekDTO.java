package ayu.edu.tr.iskolik.profil.domain.model.dto;

import ayu.edu.tr.iskolik.kategori.domain.model.dto.KategoriDTO;

public class YetenekDTO {

	private Long yetenekId;
	private ProfilDTO profil;
	private KategoriDTO kategori;
	private String aciklama;

	/* getters-setters */
	public Long getYetenekId() {
		return yetenekId;
	}

	public void setYetenekId(Long yetenekId) {
		this.yetenekId = yetenekId;
	}

	public ProfilDTO getProfil() {
		return profil;
	}

	public void setProfil(ProfilDTO profil) {
		this.profil = profil;
	}

	public KategoriDTO getKategori() {
		return kategori;
	}

	public void setKategori(KategoriDTO kategori) {
		this.kategori = kategori;
	}

	public String getAciklama() {
		return aciklama;
	}

	public void setAciklama(String aciklama) {
		this.aciklama = aciklama;
	}
}
