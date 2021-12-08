package ayu.edu.tr.iskolik.profil.domain.model.dto;

public class ProfilDTO {

	private Long kullaniciId;
	private String ozgecmis;

	/* getters-setters */
	public Long getKullaniciId() {
		return kullaniciId;
	}

	public void setKullaniciId(Long kullaniciId) {
		this.kullaniciId = kullaniciId;
	}

	public String getOzgecmis() {
		return ozgecmis;
	}

	public void setOzgecmis(String ozgecmis) {
		this.ozgecmis = ozgecmis;
	}
}
