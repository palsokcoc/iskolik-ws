package ayu.edu.tr.iskolik.profil.domain.model.dto;

public class SinavDTO {

	private Long sinavId;
	private ProfilDTO profil;
	private String sinavAdi;
	private String sinavSonucu;

	/* getters-setters */
	public Long getSinavId() {
		return sinavId;
	}

	public void setSinavId(Long sinavId) {
		this.sinavId = sinavId;
	}

	public ProfilDTO getProfil() {
		return profil;
	}

	public void setProfil(ProfilDTO profil) {
		this.profil = profil;
	}

	public String getSinavAdi() {
		return sinavAdi;
	}

	public void setSinavAdi(String sinavAdi) {
		this.sinavAdi = sinavAdi;
	}

	public String getSinavSonucu() {
		return sinavSonucu;
	}

	public void setSinavSonucu(String sinavSonucu) {
		this.sinavSonucu = sinavSonucu;
	}
}