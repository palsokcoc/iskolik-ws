package ayu.edu.tr.iskolik.profil.domain.model.dto;

import java.util.List;

public class ProfilDTO {

	private Long kullaniciId;
	private String meslek;
	private String unvan;
	private String ozgecmis;
	private List<SertifikaDTO> sertifikalar;
	private List<SinavDTO> sinavlar;
	private List<YetenekDTO> yetenekler;

	/* getters-setters */
	public Long getKullaniciId() {
		return kullaniciId;
	}

	public void setKullaniciId(Long kullaniciId) {
		this.kullaniciId = kullaniciId;
	}

	public String getMeslek() {
		return meslek;
	}

	public void setMeslek(String meslek) {
		this.meslek = meslek;
	}

	public String getUnvan() {
		return unvan;
	}

	public void setUnvan(String unvan) {
		this.unvan = unvan;
	}

	public String getOzgecmis() {
		return ozgecmis;
	}

	public void setOzgecmis(String ozgecmis) {
		this.ozgecmis = ozgecmis;
	}

	public List<SertifikaDTO> getSertifikalar() {
		return sertifikalar;
	}

	public void setSertifikalar(List<SertifikaDTO> sertifikalar) {
		this.sertifikalar = sertifikalar;
	}

	public List<SinavDTO> getSinavlar() {
		return sinavlar;
	}

	public void setSinavlar(List<SinavDTO> sinavlar) {
		this.sinavlar = sinavlar;
	}

	public List<YetenekDTO> getYetenekler() {
		return yetenekler;
	}

	public void setYetenekler(List<YetenekDTO> yetenekler) {
		this.yetenekler = yetenekler;
	}
}
