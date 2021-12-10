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
@Table(schema = "iskolik", name = "sinav")
public class Sinav {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sinav_id")
	private Long sinavId;

	@ManyToOne()
	@JoinColumn(name = "profil_id")
	private Profil profil;

	@Column(name = "sinav_adi", nullable = false)
	private String sinavAdi;

	@Column(name = "sinav_sonucu", nullable = false)
	private String sinavSonucu;

	public String toString() {
		StringBuilder sb = new StringBuilder("Sertifika[");
		sb.append("sinavId=").append(getSinavId()).append(",");
		sb.append("profil=").append(getProfil()).append(",");
		sb.append("sinavAdi=").append(getSinavAdi()).append(",");
		sb.append("sinavSonucu=").append(getSinavSonucu()).append(",");
		sb.append("]");
		return sb.toString();
	}

	/* getters-setters */
	public Long getSinavId() {
		return sinavId;
	}

	public void setSinavId(Long sinavId) {
		this.sinavId = sinavId;
	}

	public Profil getProfil() {
		return profil;
	}

	public String getSinavAdi() {
		return sinavAdi;
	}

	public void setSinavAdi(String sinavAdi) {
		this.sinavAdi = sinavAdi;
	}

	public String getSinavSonucu() {
		return sinavSonucu;
	}

	public void setSinavSonucu(String sinavSonucu) {
		this.sinavSonucu = sinavSonucu;
	}
}
