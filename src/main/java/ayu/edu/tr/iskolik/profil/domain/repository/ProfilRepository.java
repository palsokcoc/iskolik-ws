package ayu.edu.tr.iskolik.profil.domain.repository;

import ayu.edu.tr.iskolik.profil.domain.model.entity.Profil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProfilRepository extends JpaRepository<Profil, Long>, JpaSpecificationExecutor<Profil> {

}
