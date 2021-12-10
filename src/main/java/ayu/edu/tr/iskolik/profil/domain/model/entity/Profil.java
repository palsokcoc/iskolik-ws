package ayu.edu.tr.iskolik.profil.domain.model.entity;


import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(schema = "iskolik", name = "profil")
public class Profil {

	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "kullanici_id")
	private Long kullaniciId;

	@Column(name = "ozgecmis", nullable = false)
	private String ozgecmis;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "profil")
	private List<Sertifika> sertifikalar;

	public String toString() {
		StringBuilder sb = new StringBuilder("Profil[");
		sb.append("kullaniciId=").append(getKullaniciId()).append(",");
		sb.append("ozgecmis=").append(getOzgecmis()).append(",");
		sb.append("]");
		return sb.toString();
	}

	/* getters-setters */
	public Long getKullaniciId() {
		return kullaniciId;
	}

	public void setKullaniciId(Long kullaniciId) {
		this.kullaniciId = kullaniciId;
	}

	public String getOzgecmis() {
		return ozgecmis;
	}

	public void setOzgecmis(String ozgecmis) {
		this.ozgecmis = ozgecmis;
	}
}
