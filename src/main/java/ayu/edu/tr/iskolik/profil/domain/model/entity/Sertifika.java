package ayu.edu.tr.iskolik.profil.domain.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(schema = "iskolik", name = "sertifika")
public class Sertifika {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sertifika_id")
	private Long sertifikaId;

	@ManyToOne()
	@JoinColumn(name = "profil_id")
	private Profil profil;

	@Column(name = "sertifika_adi", nullable = false)
	private String sertifikaAdi;

	public String toString() {
		StringBuilder sb = new StringBuilder("Sertifika[");
		sb.append("sertifika_id=").append(getSertifikaId()).append(",");
		sb.append("profil=").append(getProfil()).append(",");
		sb.append("sertifikaAdi=").append(getSertifikaAdi()).append(",");
		sb.append("]");
		return sb.toString();
	}

	/* getters-setters */
	public Long getSertifikaId() {
		return sertifikaId;
	}

	public void setSertifikaId(Long sertifikaId) {
		this.sertifikaId = sertifikaId;
	}

	public Profil getProfil() {
		return profil;
	}

	public String getSertifikaAdi() {
		return sertifikaAdi;
	}

	public void setSertifikaAdi(String sertifikaAdi) {
		this.sertifikaAdi = sertifikaAdi;
	}
}
