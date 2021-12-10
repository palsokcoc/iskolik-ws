package ayu.edu.tr.iskolik.profil.domain.repository;

import ayu.edu.tr.iskolik.profil.domain.model.entity.Profil;
import ayu.edu.tr.iskolik.profil.domain.model.entity.Sertifika;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SertifikaRepository extends JpaRepository<Sertifika, Long>, JpaSpecificationExecutor<Sertifika> {

	Sertifika findSertifikaByProfilAndSertifikaId(Profil profil, Long sertifikaId);
}
