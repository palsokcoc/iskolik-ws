package ayu.edu.tr.iskolik.rapor.domain.service.impl;

import ayu.edu.tr.iskolik.common.application.jasper.IskolikReport;
import ayu.edu.tr.iskolik.rapor.domain.model.dto.EnCokArananOzelliklerRaporuDTO;
import ayu.edu.tr.iskolik.rapor.domain.model.dto.EnCokBasvuruYapilanIlanlarDTO;
import ayu.edu.tr.iskolik.rapor.domain.repository.RaporRepository;
import ayu.edu.tr.iskolik.rapor.domain.service.RaporService;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class RaporServiceImpl implements RaporService {

	private final RaporRepository raporRepository;

	public RaporServiceImpl(RaporRepository raporRepository) {
		this.raporRepository = raporRepository;
	}

	@Override
	public List<EnCokArananOzelliklerRaporuDTO> findEnCokArananOzellikler(LocalDate ilkTarih, LocalDate sonTarih, Pageable pageable) {
		ilkTarih = (ilkTarih != null ? ilkTarih : LocalDate.EPOCH);
		sonTarih = (sonTarih != null ? sonTarih : LocalDate.now());
		return raporRepository.findEnCokArananOzellikler(ilkTarih, sonTarih, pageable);
	}

	@Override
	public IskolikReport findEnCokArananOzelliklerRaporu(LocalDate ilkTarih, LocalDate sonTarih, Pageable pageable) {
		ilkTarih = (ilkTarih != null ? ilkTarih : LocalDate.EPOCH);
		sonTarih = (sonTarih != null ? sonTarih : LocalDate.now());
		List<EnCokArananOzelliklerRaporuDTO> dataList =  raporRepository.findEnCokArananOzellikler(ilkTarih, sonTarih, pageable);

		IskolikReport report = new IskolikReport();
		report.setFilePath("/reports/EnCokArananOzellikler.jrxml");
		report.setDataList(dataList);
		report.addReportParameter("kayitSayisi", dataList.size());
		report.addReportParameter("baslikTarihi", generateBaslikTarihi(ilkTarih, sonTarih));

		return report;
	}

	@Override
	public List<EnCokBasvuruYapilanIlanlarDTO> findEnCokBasvuruYapilanIlanlar(LocalDate ilkTarih, LocalDate sonTarih, Pageable pageable) {
		ilkTarih = (ilkTarih != null ? ilkTarih : LocalDate.EPOCH);
		sonTarih = (sonTarih != null ? sonTarih : LocalDate.now());
		return raporRepository.findEnCokBasvuruYapilanIlanlar(ilkTarih, sonTarih, pageable);
	}

	@Override
	public IskolikReport findEnCokBasvuruYapilanIlanlarRaporu(LocalDate ilkTarih, LocalDate sonTarih, Pageable pageable) {
		ilkTarih = (ilkTarih != null ? ilkTarih : LocalDate.EPOCH);
		sonTarih = (sonTarih != null ? sonTarih : LocalDate.now());
		List<EnCokBasvuruYapilanIlanlarDTO> dataList =  raporRepository.findEnCokBasvuruYapilanIlanlar(ilkTarih, sonTarih, pageable);

		IskolikReport report = new IskolikReport();
		report.setFilePath("/reports/EnCokBasvuruYapilanIlanlar.jrxml");
		report.setDataList(dataList);
		report.addReportParameter("kayitSayisi", dataList.size());
		report.addReportParameter("baslikTarihi", generateBaslikTarihi(ilkTarih, sonTarih));

		return report;
	}

	private String generateBaslikTarihi (LocalDate ilkTarih, LocalDate sonTarih) {
		String baslikTarihi;

		if(ilkTarih == null && sonTarih == null) {
			baslikTarihi = "Tüm Zamanlar";
		} else if (ilkTarih != null && sonTarih == null) {
			baslikTarihi = DateTimeFormatter.ofLocalizedDate(java.time.format.FormatStyle.LONG).format(ilkTarih) + " Tarihinden Sonrası";
		} else if (ilkTarih == null && sonTarih != null) {
			baslikTarihi = DateTimeFormatter.ofLocalizedDate(java.time.format.FormatStyle.LONG).format(ilkTarih) + " Tarihine Kadar";
		} else {
			baslikTarihi = DateTimeFormatter.ofLocalizedDate(java.time.format.FormatStyle.LONG).format(ilkTarih) + "  -  " + DateTimeFormatter.ofLocalizedDate(java.time.format.FormatStyle.LONG).format(sonTarih);
		}

		return baslikTarihi;
	}
}
