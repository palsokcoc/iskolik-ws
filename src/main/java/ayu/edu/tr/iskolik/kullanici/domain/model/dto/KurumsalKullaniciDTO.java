package ayu.edu.tr.iskolik.kullanici.domain.model.dto;

public class KurumsalKullaniciDTO extends KullaniciDTO {

	private int calisanSayisi;

	public KurumsalKullaniciDTO() {
		setTip("Kurumsal");
	}

	/* getters-setters */
	public int getCalisanSayisi() {
		return calisanSayisi;
	}

	public void setCalisanSayisi(int calisanSayisi) {
		this.calisanSayisi = calisanSayisi;
	}
}
