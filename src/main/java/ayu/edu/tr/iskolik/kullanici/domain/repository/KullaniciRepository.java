package ayu.edu.tr.iskolik.kullanici.domain.repository;

import ayu.edu.tr.iskolik.kullanici.domain.model.entity.Kullanici;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface KullaniciRepository extends JpaRepository<Kullanici, Long>, JpaSpecificationExecutor<Kullanici> {

}
