package ayu.edu.tr.iskolik.kullanici.domain.repository;

import ayu.edu.tr.iskolik.kullanici.domain.model.dto.BireyselKullaniciDTO;
import ayu.edu.tr.iskolik.kullanici.domain.model.dto.ElemanAramaSonucuDTO;
import ayu.edu.tr.iskolik.kullanici.domain.model.entity.Kullanici;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface KullaniciRepository extends JpaRepository<Kullanici, Long>, JpaSpecificationExecutor<Kullanici> {

	@Query("select new ayu.edu.tr.iskolik.kullanici.domain.model.dto.ElemanAramaSonucuDTO(bk.kullaniciId,bk.ad,bk.soyad,bk.dogumTarihi, bk.cinsiyet) from BireyselKullanici bk")
	List<ElemanAramaSonucuDTO> findBireyselKullaniciOzet(Specification specification, Pageable pageable);
}
