package ayu.edu.tr.iskolik.rapor.domain.model.dto;

public class EnCokArananOzelliklerRaporuDTO {

	private String ozellik;
	private long adet;

	public EnCokArananOzelliklerRaporuDTO(String ad, long adet) {
		this.ozellik = ad;
		this.adet = adet;
	}

	/* getters-setters */
	public String getOzellik() {
		return ozellik;
	}

	public void setOzellik(String ozellik) {
		this.ozellik = ozellik;
	}

	public long getAdet() {
		return adet;
	}

	public void setAdet(long adet) {
		this.adet = adet;
	}
}
