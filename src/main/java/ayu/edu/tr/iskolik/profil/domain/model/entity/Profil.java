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

	@Column(name = "meslek", nullable = false)
	private String meslek;

	@Column(name = "unvan", nullable = false)
	private String unvan;

	@Column(name = "ozgecmis", nullable = false)
	private String ozgecmis;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "profil")
	private List<Sertifika> sertifikalar;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "profil")
	private List<Sinav> sinavlar;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "profil")
	private List<Yetenek> yetenekler;

	public String toString() {
		StringBuilder sb = new StringBuilder("Profil[");
		sb.append("kullaniciId=").append(getKullaniciId()).append(",");
		sb.append("meslek=").append(getMeslek()).append(",");
		sb.append("unvan=").append(getUnvan()).append(",");
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

	public List<Sertifika> getSertifikalar() {
		return sertifikalar;
	}

	public void setSertifikalar(List<Sertifika> sertifikalar) {
		this.sertifikalar = sertifikalar;
	}

	public List<Sinav> getSinavlar() {
		return sinavlar;
	}

	public void setSinavlar(List<Sinav> sinavlar) {
		this.sinavlar = sinavlar;
	}

	public List<Yetenek> getYetenekler() {
		return yetenekler;
	}

	public void setYetenekler(List<Yetenek> yetenekler) {
		this.yetenekler = yetenekler;
	}
}
