package ayu.edu.tr.iskolik.ilan.domain.repository;

import ayu.edu.tr.iskolik.ilan.domain.model.entity.Ilan;
import ayu.edu.tr.iskolik.ilan.domain.model.entity.Ilan.Durum;
import ayu.edu.tr.iskolik.kullanici.domain.model.entity.BireyselKullanici;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IlanRepository extends JpaRepository<Ilan, Long>, JpaSpecificationExecutor<Ilan> {

	@Query("select distinct ilan " +
			"from  Ilan ilan " +
			"inner join Kullanici kullanici " +
			"on ilan.kullanici = kullanici " +
//			"left outer join Sertifika sertifika " +
//			"on profil = sertifika.profil " +
//			"left outer join Sinav sinav " +
//			"on profil = sinav.profil " +
//			"left outer join Yetenek yetenek " +
//			"on profil = yetenek.profil " +
//			"left outer join Kategori kategori " +
//			"on yetenek.kategori = kategori " +
			"where (" +
			":durum is null " +
			"or ilan.durum = :durum " +
			")" +
			"and (" +
			":filter = '' " +
			"or lower(ilan.unvan) like lower(concat('%',:filter,'%')) " +
			"or lower(ilan.yer) like lower(concat('%',:filter,'%')) " +
			"or lower(ilan.aciklama) like lower(concat('%',:filter,'%')) " +
			"or lower(ilan.zorunluOzellikler) like lower(concat('%',:filter,'%')) " +
			"or lower(ilan.tercihenOzellikler) like lower(concat('%',:filter,'%')) " +
			"or lower(kullanici.ad) like lower(concat('%',:filter,'%')) " +
//			"or lower(kategori.ad) like lower(concat('%',:filter,'%')) " +
			") "
//			"order by bk.ad, bk.soyad"
	)
	List<Ilan> ilanAra(@Param("filter") String filter, @Param("durum")Durum durum, Pageable pageable);
}
