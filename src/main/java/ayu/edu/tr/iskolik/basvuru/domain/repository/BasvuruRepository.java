package ayu.edu.tr.iskolik.basvuru.domain.repository;

import ayu.edu.tr.iskolik.basvuru.domain.model.entity.Basvuru;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BasvuruRepository extends JpaRepository<Basvuru, Long>, JpaSpecificationExecutor<Basvuru> {

	Basvuru findByKullaniciIdAndIlanId(long kullaniciId, long ilanId);
}
