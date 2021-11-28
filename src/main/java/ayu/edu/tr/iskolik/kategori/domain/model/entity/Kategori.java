package ayu.edu.tr.iskolik.kategori.domain.model.entity;


import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(schema = "iskolik", name = "kategori")
public class Kategori {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "kategori_id")
	private Long kategoriId;

	@Column(name = "ad", nullable = false)
	private String ad;

	@Column(name = "aciklama", nullable = false)
	private String aciklama;

	@ManyToOne(optional = true, cascade = CascadeType.MERGE)
	@JoinColumn(name = "ata_kategori_id")
	private Kategori ataKategori;

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Kategori)) {
			return false;
		}

		Kategori other = (Kategori) obj;
		return Objects.equals(this.getAtaKategori(), other.getAtaKategori()) && Objects.equals(this.getAd(), other.getAd());
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.getAtaKategori(), this.getAd());
	}

	public String toString() {
		StringBuilder sb = new StringBuilder("Kategori[");
		sb.append("Ad=").append(getAd()).append(",");
		sb.append("Açıklama=").append(getAciklama()).append(",");
		sb.append("Ata Kategori=").append(getAtaKategori()).append(",");
		sb.append("]");
		return sb.toString();
	}

	/* getters-setters */
	public Long getKategoriId() {
		return kategoriId;
	}

	public void setKategoriId(Long kategoriId) {
		this.kategoriId = kategoriId;
	}

	public String getAd() {
		return ad;
	}

	public void setAd(String ad) {
		this.ad = ad;
	}

	public String getAciklama() {
		return aciklama;
	}

	public void setAciklama(String aciklama) {
		this.aciklama = aciklama;
	}

	public Kategori getAtaKategori() {
		return ataKategori;
	}

	public void setAtaKategori(Kategori ataKategori) {
		this.ataKategori = ataKategori;
	}
}
