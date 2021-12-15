package ayu.edu.tr.iskolik.rapor.domain.model.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Rapor {

	@Id
	private Long id;

	/* getters-setters */
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
