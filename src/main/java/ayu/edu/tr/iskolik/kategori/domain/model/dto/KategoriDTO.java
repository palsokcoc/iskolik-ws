package ayu.edu.tr.iskolik.kategori.domain.model.dto;

public class KategoriDTO {

	private Long kategoriId;
	private String ad;
	private String aciklama;
	private KategoriDTO ataKategori;

	/* getters-setters */
	public Long getKategoriId() {
		return kategoriId;
	}

	public void setKategoriId(Long kategoriId) {
		this.kategoriId = kategoriId;
	}

	public String getAd() {
		return ad;
	}

	public void setAd(String ad) {
		this.ad = ad;
	}

	public String getAciklama() {
		return aciklama;
	}

	public void setAciklama(String aciklama) {
		this.aciklama = aciklama;
	}

	public KategoriDTO getAtaKategori() {
		return ataKategori;
	}

	public void setAtaKategori(KategoriDTO ataKategori) {
		this.ataKategori = ataKategori;
	}
}
