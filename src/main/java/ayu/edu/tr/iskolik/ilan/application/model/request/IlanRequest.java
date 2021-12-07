package ayu.edu.tr.iskolik.ilan.application.model.request;

import ayu.edu.tr.iskolik.common.application.model.request.validation.PostValidation;
import ayu.edu.tr.iskolik.common.application.model.request.validation.PutValidation;
import ayu.edu.tr.iskolik.ilan.domain.model.entity.Ilan.Durum;
import ayu.edu.tr.iskolik.kullanici.application.model.request.KurumsalKullaniciRequest;
import java.time.LocalDate;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.groups.Default;

public class IlanRequest {

	@NotNull(message = "validation.request.field.null", groups = {PutValidation.class, Default.class})
	private Long ilanId;

	private KurumsalKullaniciRequest kullanici;

	@NotEmpty(message = "validation.request.field.null", groups = {PostValidation.class, PutValidation.class, Default.class})
	private String unvan;

	@NotNull(message = "validation.request.field.null", groups = {PostValidation.class, PutValidation.class, Default.class})
	private Boolean isPartTime;

	private String yer;

	private LocalDate girisTarihi;

	@NotNull(message = "validation.request.field.null", groups = {PostValidation.class, PutValidation.class, Default.class})
	private LocalDate yayinTarihi;

	private LocalDate iptalTarihi;

	@NotNull(message = "validation.request.field.null", groups = {PostValidation.class, PutValidation.class, Default.class})
	private LocalDate sonBasvuruTarihi;

	private Integer basvuruLimiti;

	private String aciklama;

	private String zorunluOzellikler;

	private String tercihenOzellikler;

	@PositiveOrZero(message = "validation.request.field.negative", groups = {PostValidation.class, PutValidation.class, Default.class})
	private int minMaas;

	@PositiveOrZero(message = "validation.request.field.negative", groups = {PostValidation.class, PutValidation.class, Default.class})
	private int maxMaas;

	private Durum durum;

	/* getters-setters */
	public Long getIlanId() {
		return ilanId;
	}

	public void setIlanId(Long ilanId) {
		this.ilanId = ilanId;
	}

	public KurumsalKullaniciRequest getKullanici() {
		return kullanici;
	}

	public void setKullanici(KurumsalKullaniciRequest kullanici) {
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
