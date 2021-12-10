package ayu.edu.tr.iskolik.profil.domain.model.entity;


import ayu.edu.tr.iskolik.kategori.domain.model.entity.Kategori;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(schema = "iskolik", name = "yetenek")
public class Yetenek {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "yetenek_id")
	private Long yetenekId;

	@ManyToOne()
	@JoinColumn(name = "profil_id")
	private Profil profil;

	@OneToOne()
	@JoinColumn(name = "kategori_id", nullable = false)
	private Kategori kategori;

	@Column(name = "aciklama", nullable = false)
	private String aciklama;

	public String toString() {
		StringBuilder sb = new StringBuilder("Yetenek[");
		sb.append("yetenekId=").append(getYetenekId()).append(",");
		sb.append("profil=").append(getProfil()).append(",");
		sb.append("kategori=").append(getKategori()).append(",");
		sb.append("aciklama=").append(getAciklama()).append(",");
		sb.append("]");
		return sb.toString();
	}

	/* getters-setters */
	public Long getYetenekId() {
		return yetenekId;
	}

	public void setYetenekId(Long yetenekId) {
		this.yetenekId = yetenekId;
	}

	public Profil getProfil() {
		return profil;
	}

	public Kategori getKategori() {
		return kategori;
	}

	public void setKategori(Kategori kategori) {
		this.kategori = kategori;
	}

	public String getAciklama() {
		return aciklama;
	}

	public void setAciklama(String aciklama) {
		this.aciklama = aciklama;
	}
}
