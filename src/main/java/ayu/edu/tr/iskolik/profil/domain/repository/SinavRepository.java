package ayu.edu.tr.iskolik.profil.domain.repository;

import ayu.edu.tr.iskolik.profil.domain.model.entity.Profil;
import ayu.edu.tr.iskolik.profil.domain.model.entity.Sinav;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SinavRepository extends JpaRepository<Sinav, Long>, JpaSpecificationExecutor<Sinav> {

	Sinav findSinavByProfilAndSinavId(Profil profil, Long sinavId);
}
