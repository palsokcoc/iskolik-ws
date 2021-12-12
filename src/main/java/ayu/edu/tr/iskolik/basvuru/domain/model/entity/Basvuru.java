package ayu.edu.tr.iskolik.basvuru.domain.model.entity;


import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "iskolik", name = "basvuru")
public class Basvuru {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "basvuru_id")
	private Long basvuruId;

	@Column(name = "kullanici_id")
	private Long kullaniciId;

//	@ManyToOne
//	@JoinColumn(name = "ilan_id")
	@Column(name = "ilan_id")
	private Long ilanId;

	@Column(name = "basvuru_tarihi", nullable = false)
	private LocalDate basvuruTarihi;

	@Column(name = "iptal_tarihi", nullable = false)
	private LocalDate iptalTarihi;

	@Column(name = "durum", nullable = false)
	private Durum durum;

	public String toString() {
		StringBuilder sb = new StringBuilder("Basvuru[");
		sb.append("basvuruId=").append(getBasvuruId()).append(",");
		sb.append("kullaniciId=").append(getKullaniciId()).append(",");
		sb.append("ilanId=").append(getIlanId()).append(",");
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

	public Long getKullaniciId() {
		return kullaniciId;
	}

	public void setKullaniciId(Long kullaniciId) {
		this.kullaniciId = kullaniciId;
	}

	public Long getIlanId() {
		return ilanId;
	}

	public void setIlanId(Long ilanId) {
		this.ilanId = ilanId;
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
