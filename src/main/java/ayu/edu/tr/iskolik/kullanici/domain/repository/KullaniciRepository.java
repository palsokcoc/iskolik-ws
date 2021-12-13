package ayu.edu.tr.iskolik.kullanici.domain.repository;

import ayu.edu.tr.iskolik.kullanici.domain.model.dto.BireyselKullaniciDTO;
import ayu.edu.tr.iskolik.kullanici.domain.model.dto.ElemanAramaSonucuDTO;
import ayu.edu.tr.iskolik.kullanici.domain.model.entity.BireyselKullanici;
import ayu.edu.tr.iskolik.kullanici.domain.model.entity.Kullanici;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface KullaniciRepository extends JpaRepository<Kullanici, Long>, JpaSpecificationExecutor<Kullanici> {

	@Query("select new ayu.edu.tr.iskolik.kullanici.domain.model.dto.ElemanAramaSonucuDTO(bk.kullaniciId,bk.ad,bk.soyad,bk.dogumTarihi, bk.cinsiyet) from BireyselKullanici bk")
	List<ElemanAramaSonucuDTO> findBireyselKullaniciOzet(Specification specification, Pageable pageable);

//	public List<BireyselKullaniciDTO> elemanAra(String filter) {
//		DetachedCriteria dc = DetachedCriteria.forClass(BireyselKullanici.class);
//		dc.createAlias("profil", "profil");
//		dc.createAlias("profil.sertifikalar", "sertifikalar");
//
//		return null;
//	}

	@Query("select distinct bk " +
			"from  BireyselKullanici bk inner join Profil profil " +
			"on bk.profil = profil " +
			"left outer join Sertifika sertifika " +
			"on profil = sertifika.profil " +
			"left outer join Sinav sinav " +
			"on profil = sinav.profil " +
			"left outer join Yetenek yetenek " +
			"on profil = yetenek.profil " +
			"left outer join Kategori kategori " +
			"on yetenek.kategori = kategori " +
			"where (" +
			":filter = '' " +
			"or profil.meslek like %:filter% " +
			"or profil.unvan like %:filter% " +
			"or profil.ozgecmis like %:filter% " +
			"or sertifika.sertifikaAdi like %:filter% " +
			"or sinav.sinavAdi like %:filter% " +
			"or kategori.ad like %:filter% " +
			") " +
			"order by bk.ad, bk.soyad")
	List<BireyselKullanici> elemanAra(@Param("filter") String filter, Pageable pageable);
}
