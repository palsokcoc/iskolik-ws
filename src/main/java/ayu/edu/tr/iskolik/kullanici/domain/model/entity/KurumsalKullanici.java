package ayu.edu.tr.iskolik.kullanici.domain.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(schema = "iskolik", name = "kurumsal_kullanici")
public class KurumsalKullanici extends Kullanici {

	@Column(name = "ad", nullable = false)
	private String ad;

	@Column(name = "calisan_sayisi", nullable = false)
	private int calisanSayisi;

	public KurumsalKullanici() {
		super();
		setTip("Kurumsal");
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof KurumsalKullanici)) {
			return false;
		}

		return super.equals(obj);
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	public String toString() {
		StringBuilder sb = new StringBuilder("Kurumsal Kullanıcı[");
		sb.append("Ad=").append(getAd()).append(",");
		sb.append("Çalışan Sayısı=").append(getCalisanSayisi()).append(",");
		sb.append("Email=").append(getEmail()).append(",");
		sb.append("Durum=").append(getDurum().getAciklama()).append(",");
		sb.append("Kayıt Tarihi=").append(getKayitTarihi()).append(",");
		sb.append("İletişim=").append(getIletisim()).append(",");
		sb.append("]");
		return sb.toString();
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
