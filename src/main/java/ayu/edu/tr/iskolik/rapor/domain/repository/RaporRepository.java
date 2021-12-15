package ayu.edu.tr.iskolik.rapor.domain.repository;

import ayu.edu.tr.iskolik.rapor.domain.model.dto.EnCokArananOzelliklerRaporuDTO;
import ayu.edu.tr.iskolik.rapor.domain.model.dto.EnCokBasvuruYapilanIlanlarDTO;
import ayu.edu.tr.iskolik.rapor.domain.model.entity.Rapor;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RaporRepository extends JpaRepository<Rapor, Long> {

	@Query("select new ayu.edu.tr.iskolik.rapor.domain.model.dto.EnCokArananOzelliklerRaporuDTO (" +
			"kategori.ad, count(*) as adet) " +
			"from Ilan ilan " +
			"inner join Kategori kategori " +
			"on kategori MEMBER OF ilan.kategoriler " +
			"where ilan.yayinTarihi between :ilkTarih and :sonTarih " +
			"group by kategori.ad " +
			"order by count(*) desc"
	)
	List<EnCokArananOzelliklerRaporuDTO> findEnCokArananOzellikler(@Param("ilkTarih") LocalDate ilkTarih, @Param("sonTarih") LocalDate sonTarih, Pageable pageable);

	@Query("select new ayu.edu.tr.iskolik.rapor.domain.model.dto.EnCokBasvuruYapilanIlanlarDTO (" +
			"ilan.ilanId, kullanici.ad, ilan.unvan, ilan.yer, ilan.durum, ilan.yayinTarihi, count(*) as adet) " +
			"from  Basvuru basvuru " +
			"inner join Ilan ilan " +
			"on basvuru.ilan = ilan " +
			"inner join Kullanici kullanici " +
			"on ilan.kullanici = kullanici " +
			"where basvuru.basvuruTarihi between :ilkTarih and :sonTarih " +
			"group by ilan.ilanId, kullanici.ad, ilan.unvan, ilan.yer, ilan.yayinTarihi, ilan.durum " +
			"order by count(*) desc"
	)
	List<EnCokBasvuruYapilanIlanlarDTO> findEnCokBasvuruYapilanIlanlar(@Param("ilkTarih") LocalDate ilkTarih, @Param("sonTarih") LocalDate sonTarih, Pageable pageable);
}
