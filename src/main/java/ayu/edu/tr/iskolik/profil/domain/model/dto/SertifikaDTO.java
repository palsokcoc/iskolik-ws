package ayu.edu.tr.iskolik.profil.domain.model.dto;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

public class SertifikaDTO {

	private Long sertifikaId;
	private ProfilDTO profil;
	private String sertifikaAdi;

	/* getters-setters */
	public Long getSertifikaId() {
		return sertifikaId;
	}

	public void setSertifikaId(Long sertifikaId) {
		this.sertifikaId = sertifikaId;
	}

	public ProfilDTO getProfil() {
		return profil;
	}

	public void setProfil(ProfilDTO profil) {
		this.profil = profil;
	}

	public String getSertifikaAdi() {
		return sertifikaAdi;
	}

	public void setSertifikaAdi(String sertifikaAdi) {
		this.sertifikaAdi = sertifikaAdi;
	}
}
