package ayu.edu.tr.iskolik.iletisim.domain.model.entity;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(schema = "iskolik", name = "iletisim")
public class Iletisim {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "iletisim_id")
	private Long iletisimId;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "adres_id")
	private Adres adres;

	@Column(name = "cep_telefonu", nullable = false, length = 10)
	private String cepTelefonu;

	@Column(name = "sabit_telefon", nullable = false, length = 10)
	private String sabitTelefon;

	public String toString() {
		StringBuilder sb = new StringBuilder("İletişim[");
		sb.append("Adres=").append(getAdres()).append(",");
		sb.append("Cep Telefonu=").append(getCepTelefonu()).append(",");
		sb.append("Sabit Telefon=").append(getSabitTelefon()).append(",");
		sb.append("]");
		return sb.toString();
	}

	/* getters-setters */
	public Long getIletisimId() {
		return iletisimId;
	}

	public void setIletisimId(Long iletisimId) {
		this.iletisimId = iletisimId;
	}

	public Adres getAdres() {
		return adres;
	}

	public void setAdres(Adres adres) {
		this.adres = adres;
	}

	public String getCepTelefonu() {
		return cepTelefonu;
	}

	public void setCepTelefonu(String cepTelefonu) {
		this.cepTelefonu = cepTelefonu;
	}

	public String getSabitTelefon() {
		return sabitTelefon;
	}

	public void setSabitTelefon(String sabitTelefon) {
		this.sabitTelefon = sabitTelefon;
	}
}
