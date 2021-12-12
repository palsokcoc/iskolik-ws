package ayu.edu.tr.iskolik.kullanici.domain.model.dto;

public class KurumsalKullaniciDTO extends KullaniciDTO {

	private String ad;
	private int calisanSayisi;

	public KurumsalKullaniciDTO() {
		setTip("Kurumsal");
	}

	/* getters-setters */
	public String getAd() {
		return ad;
	}

	public void setAd(String ad) {
		this.ad = ad;
	}

	public int getCalisanSayisi() {
		return calisanSayisi;
	}

	public void setCalisanSayisi(int calisanSayisi) {
		this.calisanSayisi = calisanSayisi;
	}
}
