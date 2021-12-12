package ayu.edu.tr.iskolik.kullanici.domain.model.dto;

import ayu.edu.tr.iskolik.iletisim.domain.model.dto.IletisimDTO;
import ayu.edu.tr.iskolik.kullanici.domain.model.entity.Kullanici.Durum;
import java.time.LocalDate;

public class KullaniciDTO {

	private String tip;
	private Long kullaniciId;
	private String kullaniciAdi;
	private String email;
	private Durum durum;
	private LocalDate kayitTarihi;
	private IletisimDTO iletisim;

	/* getters-setters */
	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public Long getKullaniciId() {
		return kullaniciId;
	}

	public void setKullaniciId(Long kullaniciId) {
		this.kullaniciId = kullaniciId;
	}

	public String getKullaniciAdi() {
		return kullaniciAdi;
	}

	public void setKullaniciAdi(String kullaniciAdi) {
		this.kullaniciAdi = kullaniciAdi;
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

	public IletisimDTO getIletisim() {
		return iletisim;
	}

	public void setIletisim(IletisimDTO iletisim) {
		this.iletisim = iletisim;
	}
}
