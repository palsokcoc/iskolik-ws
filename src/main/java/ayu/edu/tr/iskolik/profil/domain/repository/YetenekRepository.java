package ayu.edu.tr.iskolik.profil.domain.repository;

import ayu.edu.tr.iskolik.profil.domain.model.entity.Profil;
import ayu.edu.tr.iskolik.profil.domain.model.entity.Yetenek;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface YetenekRepository extends JpaRepository<Yetenek, Long>, JpaSpecificationExecutor<Yetenek> {

	Yetenek findYetenekByProfilAndYetenekId(Profil profil, Long yetenekId);
}
