package ayu.edu.tr.iskolik.kullanici.domain.model.dto;

import ayu.edu.tr.iskolik.iletisim.domain.model.dto.IletisimDTO;
import ayu.edu.tr.iskolik.kullanici.application.model.request.BireyselKullaniciRequest;
import ayu.edu.tr.iskolik.kullanici.application.model.request.KurumsalKullaniciRequest;
import ayu.edu.tr.iskolik.kullanici.domain.model.entity.Kullanici;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.time.LocalDate;

@JsonTypeInfo(
		use = JsonTypeInfo.Id.NAME,
		include = JsonTypeInfo.As.PROPERTY,
		property = "type")
@JsonSubTypes({
		@Type(value = BireyselKullaniciDTO.class, name = "Bireysel"),
		@Type(value = KurumsalKullaniciDTO.class, name = "Kurumsal")
})
public class KullaniciDTO {

	private String type;
	private Long kullaniciId;
	private String kullaniciAdi;
	private String email;
	private Kullanici.Durum durum;
	private LocalDate kayitTarihi;
	private IletisimDTO iletisim;

	/* getters-setters */
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public ayu.edu.tr.iskolik.kullanici.domain.model.entity.Kullanici.Durum getDurum() {
		return durum;
	}

	public void setDurum(ayu.edu.tr.iskolik.kullanici.domain.model.entity.Kullanici.Durum durum) {
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
