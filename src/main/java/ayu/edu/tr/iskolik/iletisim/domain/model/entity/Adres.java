package ayu.edu.tr.iskolik.iletisim.domain.model.entity;


import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "ISKOLIK", name = "adres")
public class Adres {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "adres_id")
	private Long adresId;

	@Column(name = "sehir", nullable = false)
	private String sehir;

	@Column(name = "ilce", nullable = false)
	private String ilce;

	@Column(name = "mahalle", nullable = false)
	private String mahalle;

	@Column(name = "cadde", nullable = false)
	private String cadde;

	@Column(name = "apartman_no", nullable = false)
	private int apartmanNo;

	@Column(name = "daire_no", nullable = false)
	private int daireNo;

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Adres)) {
			return false;
		}

		Adres other = (Adres) obj;
		return Objects.equals(this.getSehir(), other.getSehir()) && Objects.equals(this.getIlce(), other.getIlce()) && Objects.equals(this.getMahalle(),
			other.getMahalle()) && Objects.equals(this.getCadde(), other.getCadde()) && Objects.equals(this.getApartmanNo(), other.getApartmanNo())
			&& Objects.equals(this.getDaireNo(), other.getDaireNo());
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.getSehir(), this.getIlce(), this.getMahalle(), this.getCadde(), this.getApartmanNo(), this.getDaireNo());
	}

	public String toString() {
		StringBuilder sb = new StringBuilder("Adres[");
		sb.append("Şehir=").append(getSehir()).append(",");
		sb.append("İlçe=").append(getIlce()).append(",");
		sb.append("Mahalle=").append(getMahalle()).append(",");
		sb.append("Cadde=").append(getCadde()).append(",");
		sb.append("Apartman No=").append(getApartmanNo()).append(",");
		sb.append("Daire No=").append(getDaireNo()).append(",");
		sb.append("]");
		return sb.toString();
	}

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
