package ayu.edu.tr.iskolik.ilan.domain.model.entity;


import ayu.edu.tr.iskolik.kullanici.domain.model.entity.KurumsalKullanici;
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
@Table(schema = "iskolik", name = "ilan")
public class Ilan {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ilan_id")
	private Long ilanId;

	@ManyToOne
	@JoinColumn(name = "kullanici_id")
	private KurumsalKullanici kullanici;

	@Column(name = "unvan", nullable = false)
	private String unvan;

	@Column(name = "is_part_time")
	private Boolean isPartTime;

	@Column(name = "yer", nullable = false)
	private String yer;

	@Column(name = "girisTarihi", nullable = false)
	private LocalDate girisTarihi;

	@Column(name = "yayinTarihi")
	private LocalDate yayinTarihi;

	@Column(name = "iptalTarihi")
	private LocalDate iptalTarihi;

	@Column(name = "sonBasvuruTarihi")
	private LocalDate sonBasvuruTarihi;

	@Column(name = "basvuruLimiti", nullable = false)
	private Integer basvuruLimiti;

	@Column(name = "aciklama", nullable = false)
	private String aciklama;

	@Column(name = "zorunlu_ozellikler", nullable = false)
	private String zorunluOzellikler;

	@Column(name = "tercihen_ozellikler", nullable = false)
	private String tercihenOzellikler;

	@Column(name = "min_maas")
	private Integer minMaas;

	@Column(name = "max_maas")
	private Integer maxMaas;

	@Column(name = "durum", nullable = false)
	private Durum durum;

	public String toString() {
		StringBuilder sb = new StringBuilder("İlan[");
		sb.append("ilan_id=").append(getIlanId()).append(",");
		sb.append("kullanıcı=").append(getKullanici()).append(",");
		sb.append("unvan=").append(getUnvan()).append(",");
		sb.append("is_part_time=").append(getIsPartTime()).append(",");
		sb.append("yer=").append(getYer()).append(",");
		sb.append("giris_tarihi=").append(getGirisTarihi()).append(",");
		sb.append("yayin_tarihi=").append(getYayinTarihi()).append(",");
		sb.append("iptal_tarihi=").append(getIptalTarihi()).append(",");
		sb.append("son_basvuru_tarihi=").append(getSonBasvuruTarihi()).append(",");
		sb.append("basvuru_limiti=").append(getBasvuruLimiti()).append(",");
		sb.append("aciklama=").append(getAciklama()).append(",");
		sb.append("zorunlu_ozellikler=").append(getZorunluOzellikler()).append(",");
		sb.append("tercihen_ozellikler=").append(getTercihenOzellikler()).append(",");
		sb.append("min_maas=").append(getMinMaas()).append(",");
		sb.append("max_maas=").append(getMaxMaas()).append(",");
		sb.append("durum=").append(getDurum()).append(",");
		sb.append("]");
		return sb.toString();
	}

	public enum Durum {
		BEKLEMEDE("Beklemede"), AKTIF("Aktif"), IPTAL("İptal"), TAMAMLANDI("Tamamlandı");

		private String aciklama;

		private Durum(String aciklama) {
			this.aciklama = aciklama;
		}

		public String getAciklama() {
			return aciklama;
		}
	}

	/* getters-setters */
	public Long getIlanId() {
		return ilanId;
	}

	public void setIlanId(Long ilanId) {
		this.ilanId = ilanId;
	}

	public KurumsalKullanici getKullanici() {
		return kullanici;
	}

	public void setKullanici(KurumsalKullanici kullanici) {
		this.kullanici = kullanici;
	}

	public String getUnvan() {
		return unvan;
	}

	public void setUnvan(String unvan) {
		this.unvan = unvan;
	}

	public Boolean getIsPartTime() {
		return isPartTime;
	}

	public void setIsPartTime(Boolean partTime) {
		isPartTime = partTime;
	}

	public String getYer() {
		return yer;
	}

	public void setYer(String yer) {
		this.yer = yer;
	}

	public LocalDate getGirisTarihi() {
		return girisTarihi;
	}

	public void setGirisTarihi(LocalDate girisTarihi) {
		this.girisTarihi = girisTarihi;
	}

	public LocalDate getYayinTarihi() {
		return yayinTarihi;
	}

	public void setYayinTarihi(LocalDate yayinTarihi) {
		this.yayinTarihi = yayinTarihi;
	}

	public LocalDate getIptalTarihi() {
		return iptalTarihi;
	}

	public void setIptalTarihi(LocalDate iptalTarihi) {
		this.iptalTarihi = iptalTarihi;
	}

	public LocalDate getSonBasvuruTarihi() {
		return sonBasvuruTarihi;
	}

	public void setSonBasvuruTarihi(LocalDate sonBasvuruTarihi) {
		this.sonBasvuruTarihi = sonBasvuruTarihi;
	}

	public Integer getBasvuruLimiti() {
		return basvuruLimiti;
	}

	public void setBasvuruLimiti(Integer basvuruLimiti) {
		this.basvuruLimiti = basvuruLimiti;
	}

	public String getAciklama() {
		return aciklama;
	}

	public void setAciklama(String aciklama) {
		this.aciklama = aciklama;
	}

	public String getZorunluOzellikler() {
		return zorunluOzellikler;
	}

	public void setZorunluOzellikler(String zorunluOzellikler) {
		this.zorunluOzellikler = zorunluOzellikler;
	}

	public String getTercihenOzellikler() {
		return tercihenOzellikler;
	}

	public void setTercihenOzellikler(String tercihenOzellikler) {
		this.tercihenOzellikler = tercihenOzellikler;
	}

	public Integer getMinMaas() {
		return minMaas;
	}

	public void setMinMaas(Integer minMaas) {
		this.minMaas = minMaas;
	}

	public Integer getMaxMaas() {
		return maxMaas;
	}

	public void setMaxMaas(Integer maxMaas) {
		this.maxMaas = maxMaas;
	}

	public Durum getDurum() {
		return durum;
	}

	public void setDurum(Durum durum) {
		this.durum = durum;
	}
}
