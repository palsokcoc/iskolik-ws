package ayu.edu.tr.iskolik.profil.application.model.request;

import ayu.edu.tr.iskolik.common.application.model.request.validation.PostValidation;
import ayu.edu.tr.iskolik.common.application.model.request.validation.PutValidation;
import ayu.edu.tr.iskolik.profil.domain.model.entity.Sertifika;
import ayu.edu.tr.iskolik.profil.domain.model.entity.Sinav;
import ayu.edu.tr.iskolik.profil.domain.model.entity.Yetenek;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;

public class ProfilRequest {

	@NotNull(message = "validation.request.field.null", groups = {PutValidation.class, Default.class})
	private Long kullaniciId;

	@NotEmpty(message = "validation.request.field.null", groups = {PostValidation.class, PutValidation.class, Default.class})
	private String meslek;

	@NotEmpty(message = "validation.request.field.null", groups = {PostValidation.class, PutValidation.class, Default.class})
	private String unvan;

	@NotEmpty(message = "validation.request.field.null", groups = {PostValidation.class, PutValidation.class, Default.class})
	private String ozgecmis;

	private List<SertifikaRequest> sertifikalar;

	private List<SinavRequest> sinavlar;

	private List<YetenekRequest> yetenekler;

	/* getters-setters */
	public Long getKullaniciId() {
		return kullaniciId;
	}

	public void setKullaniciId(Long kullaniciId) {
		this.kullaniciId = kullaniciId;
	}

	public String getMeslek() {
		return meslek;
	}

	public void setMeslek(String meslek) {
		this.meslek = meslek;
	}

	public String getUnvan() {
		return unvan;
	}

	public void setUnvan(String unvan) {
		this.unvan = unvan;
	}

	public String getOzgecmis() {
		return ozgecmis;
	}

	public void setOzgecmis(String ozgecmis) {
		this.ozgecmis = ozgecmis;
	}

	public List<SertifikaRequest> getSertifikalar() {
		return sertifikalar;
	}

	public void setSertifikalar(List<SertifikaRequest> sertifikalar) {
		this.sertifikalar = sertifikalar;
	}

	public List<SinavRequest> getSinavlar() {
		return sinavlar;
	}

	public void setSinavlar(List<SinavRequest> sinavlar) {
		this.sinavlar = sinavlar;
	}

	public List<YetenekRequest> getYetenekler() {
		return yetenekler;
	}

	public void setYetenekler(List<YetenekRequest> yetenekler) {
		this.yetenekler = yetenekler;
	}
}
