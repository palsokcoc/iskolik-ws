package ayu.edu.tr.iskolik.rapor.application.controller;

import ayu.edu.tr.iskolik.common.application.controller.BaseController;
import ayu.edu.tr.iskolik.common.application.jasper.IskolikJasperReportsManager;
import ayu.edu.tr.iskolik.common.application.jasper.IskolikReport;
import ayu.edu.tr.iskolik.common.application.model.request.validation.PostValidation;
import ayu.edu.tr.iskolik.common.application.model.request.validation.PutValidation;
import ayu.edu.tr.iskolik.common.domain.repository.BaseSpecification;
import ayu.edu.tr.iskolik.common.domain.repository.filter.Filters;
import ayu.edu.tr.iskolik.common.model.response.GenericServerResponse;
import ayu.edu.tr.iskolik.ilan.application.model.mapper.IlanRequestMapper;
import ayu.edu.tr.iskolik.ilan.application.model.request.IlanRequest;
import ayu.edu.tr.iskolik.ilan.domain.model.dto.IlanDTO;
import ayu.edu.tr.iskolik.ilan.domain.model.entity.Ilan.Durum;
import ayu.edu.tr.iskolik.ilan.domain.service.IlanService;
import ayu.edu.tr.iskolik.infrastructure.configuration.IskolikConfigutarion;
import ayu.edu.tr.iskolik.rapor.domain.model.dto.EnCokArananOzelliklerRaporuDTO;
import ayu.edu.tr.iskolik.rapor.domain.model.dto.EnCokBasvuruYapilanIlanlarDTO;
import ayu.edu.tr.iskolik.rapor.domain.service.RaporService;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/rapor")
public class RaporController extends BaseController {

	private final RaporService raporService;

	public RaporController(RaporService raporService) {
		this.raporService = raporService;
	}

	@GetMapping(value = "/en-cok-aranan-ozellikler")
	public ResponseEntity<GenericServerResponse> findEnCokArananOzellikler(@RequestParam(required = false) @DateTimeFormat(iso = ISO.DATE) LocalDate ilkTarih, @RequestParam(required = false) @DateTimeFormat(iso = ISO.DATE) LocalDate sonTarih, Pageable pageable) {
		final List<EnCokArananOzelliklerRaporuDTO> enCokArananOzellikler = raporService.findEnCokArananOzellikler(ilkTarih, sonTarih, pageable);
		System.out.println("size:" + enCokArananOzellikler.size());
		return createResponseForSuccess(HttpStatus.OK, enCokArananOzellikler);
	}

	@GetMapping(value = "/en-cok-aranan-ozellikler-pdf", produces = {"application/pdf"})
	public void findEnCokArananOzelliklerPDF(@RequestParam(required = false) @DateTimeFormat(iso = ISO.DATE) LocalDate ilkTarih, @RequestParam(required = false) @DateTimeFormat(iso = ISO.DATE) LocalDate sonTarih, Pageable pageable, HttpServletResponse response) {
		final IskolikReport report = raporService.findEnCokArananOzelliklerRaporu(ilkTarih, sonTarih, pageable);
		try {
			IskolikJasperReportsManager.displayReport(response, report);
//			IskolikJasperReportsManager.getReport(report);
//			File file = new File("C:\\Users\\aykut\\Documents\\jasper\\Report.pdf");
//			IskolikJasperReportsManager.saveReportOnServer(report,file);
//			System.out.println(report.getFilePath());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JRException e) {
			e.printStackTrace();
		}
	}

	@GetMapping(value = "/en-cok-basvuru-yapilan-ilanlar")
	public ResponseEntity<GenericServerResponse> findEnCokBasvuruYapilanIlanlar(@RequestParam(required = false) @DateTimeFormat(iso = ISO.DATE) LocalDate ilkTarih, @RequestParam(required = false) @DateTimeFormat(iso = ISO.DATE) LocalDate sonTarih, Pageable pageable) {
		System.out.println("ilk tarih: " + ilkTarih);
		System.out.println("son tarih: " + sonTarih);
		final List<EnCokBasvuruYapilanIlanlarDTO> enCokBasvuruYapilanIlanlarDTO = raporService.findEnCokBasvuruYapilanIlanlar(ilkTarih, sonTarih, pageable);
		return createResponseForSuccess(HttpStatus.OK, enCokBasvuruYapilanIlanlarDTO);
	}

	@GetMapping(value = "/en-cok-basvuru-yapilan-ilanlar-pdf", produces = {"application/pdf"})
	public void findEnCokBasvuruYapilanIlanlarPDF(@RequestParam(required = false) @DateTimeFormat(iso = ISO.DATE) LocalDate ilkTarih, @RequestParam(required = false) @DateTimeFormat(iso = ISO.DATE) LocalDate sonTarih, Pageable pageable, HttpServletResponse response) {
		final IskolikReport report = raporService.findEnCokBasvuruYapilanIlanlarRaporu(ilkTarih, sonTarih, pageable);
		try {
			IskolikJasperReportsManager.displayReport(response, report);
//			IskolikJasperReportsManager.getReport(report);
//			File file = new File("C:\\Users\\aykut\\Documents\\jasper\\Report.pdf");
//			IskolikJasperReportsManager.saveReportOnServer(report,file);
//			System.out.println(report.getFilePath());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JRException e) {
			e.printStackTrace();
		}
	}
}
