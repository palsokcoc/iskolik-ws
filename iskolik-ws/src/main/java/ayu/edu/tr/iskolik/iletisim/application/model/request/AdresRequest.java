package ayu.edu.tr.iskolik.iletisim.application.model.request;

import ayu.edu.tr.iskolik.common.application.model.request.validation.PostValidation;
import ayu.edu.tr.iskolik.common.application.model.request.validation.PutValidation;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;

public class AdresRequest {

	@NotNull(message = "validation.request.field.null", groups = {PutValidation.class, Default.class})
	private Long adresId;

	@NotEmpty(message = "validation.request.field.null", groups = {PostValidation.class, PutValidation.class, Default.class})
	private String sehir;

	@NotEmpty(message = "validation.request.field.null", groups = {PostValidation.class, PutValidation.class, Default.class})
	private String ilce;

	@NotEmpty(message = "validation.request.field.null", groups = {PostValidation.class, PutValidation.class, Default.class})
	private String mahalle;

	@NotEmpty(message = "validation.request.field.null", groups = {PostValidation.class, PutValidation.class, Default.class})
	private String cadde;

	@NotNull(message = "validation.request.field.null", groups = {PostValidation.class, PutValidation.class, Default.class})
	private Integer apartmanNo;

	@NotNull(message = "validation.request.field.null", groups = {PostValidation.class, PutValidation.class, Default.class})
	private Integer daireNo;

	/* getters-setters */
	public Long getAdresId() {
		return adresId;
	}

	public void setAdresId(Long adresId) {
		this.adresId = adresId;
	}

	public String getSehir() {
		return sehir;
	}

	public void setSehir(String sehir) {
		this.sehir = sehir;
	}

	public String getIlce() {
		return ilce;
	}

	public void setIlce(String ilce) {
		this.ilce = ilce;
	}

	public String getMahalle() {
		return mahalle;
	}

	public void setMahalle(String mahalle) {
		this.mahalle = mahalle;
	}

	public String getCadde() {
		return cadde;
	}

	public void setCadde(String cadde) {
		this.cadde = cadde;
	}

	public Integer getApartmanNo() {
		return apartmanNo;
	}

	public void setApartmanNo(Integer apartmanNo) {
		this.apartmanNo = apartmanNo;
	}

	public Integer getDaireNo() {
		return daireNo;
	}

	public void setDaireNo(Integer daireNo) {
		this.daireNo = daireNo;
	}
}
