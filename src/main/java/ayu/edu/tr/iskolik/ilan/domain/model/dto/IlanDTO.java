package ayu.edu.tr.iskolik.ilan.domain.model.dto;

import ayu.edu.tr.iskolik.ilan.domain.model.entity.Ilan.Durum;
import ayu.edu.tr.iskolik.kullanici.domain.model.dto.KurumsalKullaniciDTO;
import java.time.LocalDate;

public class IlanDTO {

	private Long ilanId;
	private KurumsalKullaniciDTO kullanici;
	private String unvan;
	private Boolean isPartTime;
	private String yer;
	private LocalDate girisTarihi;
	private LocalDate yayinTarihi;
	private LocalDate iptalTarihi;
	private LocalDate sonBasvuruTarihi;
	private Integer basvuruLimiti;
	private String aciklama;
	private String zorunluOzellikler;
	private String tercihenOzellikler;
	private int minMaas;
	private int maxMaas;
	private Durum durum;

	/* getters-setters */
	public Long getIlanId() {
		return ilanId;
	}

	public void setIlanId(Long ilanId) {
		this.ilanId = ilanId;
	}

	public KurumsalKullaniciDTO getKullanici() {
		return kullanici;
	}

	public void setKullanici(KurumsalKullaniciDTO kullanici) {
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

	public int getMinMaas() {
		return minMaas;
	}

	public void setMinMaas(int minMaas) {
		this.minMaas = minMaas;
	}

	public int getMaxMaas() {
		return maxMaas;
	}

	public void setMaxMaas(int maxMaas) {
		this.maxMaas = maxMaas;
	}

	public Durum getDurum() {
		return durum;
	}

	public void setDurum(Durum durum) {
		this.durum = durum;
	}
}
