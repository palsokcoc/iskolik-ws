package ayu.edu.tr.iskolik.kullanici.application.model.request;

import ayu.edu.tr.iskolik.common.application.model.request.validation.PostValidation;
import ayu.edu.tr.iskolik.common.application.model.request.validation.PutValidation;
import ayu.edu.tr.iskolik.iletisim.domain.model.dto.IletisimDTO;
import ayu.edu.tr.iskolik.kullanici.domain.model.dto.BireyselKullaniciDTO;
import ayu.edu.tr.iskolik.kullanici.domain.model.dto.KurumsalKullaniciDTO;
import ayu.edu.tr.iskolik.kullanici.domain.model.entity.Kullanici;
import ayu.edu.tr.iskolik.kullanici.domain.model.entity.Kullanici.Durum;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.time.LocalDate;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;

@JsonTypeInfo(
		use = JsonTypeInfo.Id.NAME,
		include = JsonTypeInfo.As.PROPERTY,
		property = "type")
@JsonSubTypes({
		@Type(value = BireyselKullaniciRequest.class, name = "Bireysel"),
		@Type(value = KurumsalKullaniciRequest.class, name = "Kurumsal")
})
public class KullaniciRequest {

	@NotNull(message = "validation.request.field.null", groups = {PostValidation.class, PutValidation.class, Default.class})
	private String type;

	@NotNull(message = "validation.request.field.null", groups = {PutValidation.class, Default.class})
	private Long kullaniciId;

	@NotNull(message = "validation.request.field.null", groups = {PostValidation.class, PutValidation.class, Default.class})
	private String kullaniciAdi;

	@NotNull(message = "validation.request.field.null", groups = {PostValidation.class, PutValidation.class, Default.class})
	private String email;

	@NotNull(message = "validation.request.field.null", groups = {Default.class})
	private Kullanici.Durum durum;

	@NotNull(message = "validation.request.field.null", groups = {Default.class})
	private LocalDate kayitTarihi;

	@NotNull(message = "validation.request.field.null", groups = {PostValidation.class, PutValidation.class, Default.class})
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
