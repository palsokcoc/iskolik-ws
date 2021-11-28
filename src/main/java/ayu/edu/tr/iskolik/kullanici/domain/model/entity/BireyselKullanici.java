package ayu.edu.tr.iskolik.kullanici.domain.model.entity;

import java.time.LocalDate;
import java.util.Arrays;
import javax.persistence.AttributeConverter;
import javax.persistence.Column;
import javax.persistence.Converter;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(schema = "iskolik", name = "bireysel_kullanici")
public class BireyselKullanici extends Kullanici {

	@Column(name = "ad", nullable = false)
	private String ad;

	@Column(name = "soyad", nullable = false)
	private String soyad;

	@Column(name = "cinsiyet", nullable = false)
	private Cinsiyet cinsiyet;

	@Column(name = "dogum_tarihi", nullable = false)
	private LocalDate dogumTarihi;

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof BireyselKullanici)) {
			return false;
		}

		return super.equals(obj);
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	public String toString() {
		StringBuilder sb = new StringBuilder("Bireysel Kullanıcı[");
		sb.append("Ad=").append(getAd()).append(",");
		sb.append("Soyad=").append(getSoyad()).append(",");
		sb.append("Cinsiyet=").append(getCinsiyet().getKisaltma()).append(",");
		sb.append("Doğum Tarihi=").append(getDogumTarihi()).append(",");
		sb.append("Email=").append(getEmail()).append(",");
		sb.append("Durum=").append(getDurum().getAciklama()).append(",");
		sb.append("Kayıt Tarihi=").append(getKayitTarihi()).append(",");
		sb.append("İletişim=").append(getIletisim()).append(",");
		sb.append("]");
		return sb.toString();
	}

	public enum Cinsiyet {
		ERKEK("E"), KADIN("K");

		private String kisaltma;

		private Cinsiyet(String kisaltma) {
			this.kisaltma = kisaltma;
		}

		public String getKisaltma() {
			return kisaltma;
		}
	}

	/* getters-setters */
	public String getAd() {
		return ad;
	}

	public void setAd(String ad) {
		this.ad = ad;
	}

	public String getSoyad() {
		return soyad;
	}

	public void setSoyad(String soyad) {
		this.soyad = soyad;
	}

	public Cinsiyet getCinsiyet() {
		return cinsiyet;
	}

	public void setCinsiyet(Cinsiyet cinsiyet) {
		this.cinsiyet = cinsiyet;
	}

	public LocalDate getDogumTarihi() {
		return dogumTarihi;
	}

	public void setDogumTarihi(LocalDate dogumTarihi) {
		this.dogumTarihi = dogumTarihi;
	}
}
