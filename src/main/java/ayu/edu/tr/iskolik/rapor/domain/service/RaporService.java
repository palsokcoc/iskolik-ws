package ayu.edu.tr.iskolik.rapor.domain.service;

import ayu.edu.tr.iskolik.common.application.jasper.IskolikReport;
import ayu.edu.tr.iskolik.rapor.domain.model.dto.EnCokArananOzelliklerRaporuDTO;
import ayu.edu.tr.iskolik.rapor.domain.model.dto.EnCokBasvuruYapilanIlanlarDTO;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface RaporService {

	List<EnCokArananOzelliklerRaporuDTO> findEnCokArananOzellikler(LocalDate ilkTarih, LocalDate sonTarih, Pageable pageable);
	IskolikReport findEnCokArananOzelliklerRaporu(LocalDate ilkTarih, LocalDate sonTarih, Pageable pageable);

	List<EnCokBasvuruYapilanIlanlarDTO> findEnCokBasvuruYapilanIlanlar(LocalDate ilkTarih, LocalDate sonTarih, Pageable pageable);
	IskolikReport findEnCokBasvuruYapilanIlanlarRaporu(LocalDate ilkTarih, LocalDate sonTarih, Pageable pageable);
}
