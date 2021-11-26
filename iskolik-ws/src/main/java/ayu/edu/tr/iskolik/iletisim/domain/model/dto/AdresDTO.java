package ayu.edu.tr.iskolik.iletisim.domain.model.dto;

import ayu.edu.tr.iskolik.iletisim.domain.model.entity.Adres;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class AdresDTO {

	private Long adresId;
	private String sehir;
	private String ilce;
	private String mahalle;
	private String cadde;
	private int apartmanNo;
	private int daireNo;

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

	public int getApartmanNo() {
		return apartmanNo;
	}

	public void setApartmanNo(int apartmanNo) {
		this.apartmanNo = apartmanNo;
	}

	public int getDaireNo() {
		return daireNo;
	}

	public void setDaireNo(int daireNo) {
		this.daireNo = daireNo;
	}
}
