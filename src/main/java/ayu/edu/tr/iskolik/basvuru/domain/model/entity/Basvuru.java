package ayu.edu.tr.iskolik.basvuru.domain.model.entity;


import ayu.edu.tr.iskolik.ilan.domain.model.entity.Ilan;
import ayu.edu.tr.iskolik.kullanici.domain.model.entity.BireyselKullanici;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(schema = "iskolik", name = "basvuru")
public class Basvuru {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "basvuru_id")
	private Long basvuruId;

	@ManyToOne
	@JoinColumn(name = "kullanici_id")
	private BireyselKullanici kullanici;

	@ManyToOne
	@JoinColumn(name = "ilan_id")
	private Ilan ilan;

	@Column(name = "basvuru_tarihi", nullable = false)
	private LocalDate basvuruTarihi;

	@Column(name = "iptal_tarihi", nullable = false)
	private LocalDate iptalTarihi;

	@Column(name = "durum", nullable = false)
	private Durum durum;

	public String toString() {
		StringBuilder sb = new StringBuilder("Basvuru[");
		sb.append("basvuruId=").append(getBasvuruId()).append(",");
		sb.append("kullanici=").append(getKullanici()).append(",");
		sb.append("ilan=").append(getIlan()).append(",");
		sb.append("basvuruTarihi=").append(getBasvuruTarihi()).append(",");
		sb.append("durum=").append(getDurum().getAciklama()).append(",");
		sb.append("]");
		return sb.toString();
	}

	public enum Durum {
		AKTIF("Aktif"), IPTAL("İptal"), TAMAMLANDI("Tamamlandı");

		private String aciklama;

		private Durum(String aciklama) {
			this.aciklama = aciklama;
		}

		public String getAciklama() {
			return aciklama;
		}

		@Override
		public String toString() {
			return aciklama;
		}
	}

	/* getters-setters */
	public Long getBasvuruId() {
		return basvuruId;
	}

	public void setBasvuruId(Long basvuruId) {
		this.basvuruId = basvuruId;
	}

	public BireyselKullanici getKullanici() {
		return kullanici;
	}

	public void setKullanici(BireyselKullanici kullanici) {
		this.kullanici = kullanici;
	}

	public Ilan getIlan() {
		return ilan;
	}

	public void setIlan(Ilan ilan) {
		this.ilan = ilan;
	}

	public LocalDate getBasvuruTarihi() {
		return basvuruTarihi;
	}

	public void setBasvuruTarihi(LocalDate basvuruTarihi) {
		this.basvuruTarihi = basvuruTarihi;
	}

	public LocalDate getIptalTarihi() {
		return iptalTarihi;
	}

	public void setIptalTarihi(LocalDate iptalTarihi) {
		this.iptalTarihi = iptalTarihi;
	}

	public Basvuru.Durum getDurum() {
		return durum;
	}

	public void setDurum(Basvuru.Durum durum) {
		this.durum = durum;
	}
}
