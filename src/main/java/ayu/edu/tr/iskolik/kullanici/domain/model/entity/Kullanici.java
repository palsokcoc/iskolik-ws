package ayu.edu.tr.iskolik.kullanici.domain.model.entity;

import ayu.edu.tr.iskolik.iletisim.domain.model.entity.Iletisim;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(schema = "iskolik", name = "kullanici")
public class Kullanici {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "kullanici_id")
	private Long kullaniciId;

	@Column(name = "tip", nullable = false)
	private String tip;

	@Column(name = "kullanici_adi", nullable = false)
	private String kullaniciAdi;

	@Column(name = "ad", nullable = false)
	private String ad;

	@Column(name = "email", nullable = false)
	private String email;

	@Column(name = "durum", nullable = false)
	private Durum durum;

	@Column(name = "kayit_tarihi", nullable = false)
	private LocalDate kayitTarihi;

	@Column(name = "sifre_hash", nullable = false)
	private String sifreHash;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "iletisim_id")
	private Iletisim iletisim;

	public Kullanici() {
		this.durum = Durum.ONAY_BEKLIYOR;
		this.kayitTarihi = LocalDate.now();
		this.sifreHash = "...";
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Kullanici)) {
			return false;
		}

		Kullanici other = (Kullanici) obj;
		return Objects.equals(this.getEmail(), other.getEmail());
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(this.getEmail());
	}

	public String toString() {
		StringBuilder sb = new StringBuilder("Kullanıcı[");
		sb.append("kullaniciId=").append(getKullaniciId()).append(",");
		sb.append("Tip=").append(getTip()).append(",");
		sb.append("Kullanıcı Adı=").append(getKullaniciAdi()).append(",");
		sb.append("Email=").append(getEmail()).append(",");
		sb.append("Durum=").append(getDurum().getAciklama()).append(",");
		sb.append("Kayıt Tarihi=").append(getKayitTarihi()).append(",");
		sb.append("İletişim=").append(getIletisim()).append(",");
		sb.append("]");
		return sb.toString();
	}

	public enum Durum {
		ONAY_BEKLIYOR("Onay Bekliyor"),AKTIF("Aktif"),PASIF("Pasif"),SILINDI("Silindi");

		private String aciklama;

		private Durum(String aciklama) {
			this.aciklama = aciklama;
		}

		public String getAciklama() {
			return aciklama;
		}
	}

		/* getters-setters */
	public Long getKullaniciId() {
		return kullaniciId;
	}

	public void setKullaniciId(Long kullaniciId) {
		this.kullaniciId = kullaniciId;
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public String getKullaniciAdi() {
		return kullaniciAdi;
	}

	public void setKullaniciAdi(String kullaniciAdi) {
		this.kullaniciAdi = kullaniciAdi;
	}

	public String getAd() {
		return ad;
	}

	public void setAd(String ad) {
		this.ad = ad;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Durum getDurum() {
		return durum;
	}

	public void setDurum(Durum durum) {
		this.durum = durum;
	}

	public LocalDate getKayitTarihi() {
		return kayitTarihi;
	}

	public void setKayitTarihi(LocalDate kayitTarihi) {
		this.kayitTarihi = kayitTarihi;
	}

	public String getSifreHash() {
		return sifreHash;
	}

	public void setSifreHash(String sifreHash) {
		this.sifreHash = sifreHash;
	}

	public Iletisim getIletisim() {
		return iletisim;
	}

	public void setIletisim(Iletisim iletisim) {
		this.iletisim = iletisim;
	}
}
