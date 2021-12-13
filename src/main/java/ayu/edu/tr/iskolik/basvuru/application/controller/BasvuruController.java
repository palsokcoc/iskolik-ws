package ayu.edu.tr.iskolik.basvuru.application.controller;

import ayu.edu.tr.iskolik.basvuru.application.model.mapper.BasvuruRequestMapper;
import ayu.edu.tr.iskolik.basvuru.application.model.request.BasvuruRequest;
import ayu.edu.tr.iskolik.basvuru.domain.model.dto.BasvuruDTO;
import ayu.edu.tr.iskolik.basvuru.domain.service.BasvuruService;
import ayu.edu.tr.iskolik.common.application.controller.BaseController;
import ayu.edu.tr.iskolik.common.application.model.request.validation.PostValidation;
import ayu.edu.tr.iskolik.common.application.model.request.validation.PutValidation;
import ayu.edu.tr.iskolik.common.domain.repository.filter.Filters;
import ayu.edu.tr.iskolik.common.model.response.GenericServerResponse;
import java.util.List;
import java.util.Map;
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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "")
public class BasvuruController extends BaseController {

	private final BasvuruService basvuruService;
	private final BasvuruRequestMapper basvuruRequestMapper;

	public BasvuruController(BasvuruService basvuruService, BasvuruRequestMapper basvuruRequestMapper) {
		this.basvuruService = basvuruService;
		this.basvuruRequestMapper = basvuruRequestMapper;
	}

	@GetMapping(value = "/ilan/{ilanId}/basvuru/")
	public ResponseEntity<GenericServerResponse> getAllBasvuruByIlanId(@PathVariable Long ilanId, @ModelAttribute Filters filters) {
		List<BasvuruDTO> basvuruDTOList = basvuruService.findAllByIlanId(ilanId, filters);
		return createResponseForSuccess(HttpStatus.OK, basvuruDTOList);
	}

	@GetMapping(value = "/kullanici/{kullaniciId}/basvuru/")
	public ResponseEntity<GenericServerResponse> getAllBasvuruByKullaniciId(@PathVariable Long kullaniciId, @ModelAttribute Filters filters) {
		List<BasvuruDTO> basvuruDTOList = basvuruService.findAllByKullaniciId(kullaniciId, filters);
		return createResponseForSuccess(HttpStatus.OK, basvuruDTOList);
	}

	@PostMapping(value = "/basvuru")
	public ResponseEntity<GenericServerResponse> saveBasvuru(@Validated(PostValidation.class) @RequestBody BasvuruRequest basvuruRequest) {
		BasvuruDTO requestBasvuruDTO = basvuruRequestMapper.toBasvuruDTO(basvuruRequest);
		BasvuruDTO responseBasvuruDTO = basvuruService.saveBasvuru(requestBasvuruDTO);
		return createResponseForSuccess(HttpStatus.CREATED, responseBasvuruDTO, responseBasvuruDTO.getIlanId() + " nolu ilana başarıyla başvuru yapıldı");
	}

	@PutMapping(value = "/basvuru/{basvuruId}")
	public ResponseEntity<GenericServerResponse> updateBasvuru(@PathVariable Long basvuruId, @Validated(PutValidation.class) @RequestBody BasvuruRequest basvuruRequest) {
		BasvuruDTO requestBasvuruDTO = basvuruRequestMapper.toBasvuruDTO(basvuruRequest);
		BasvuruDTO responseBasvuruDTO = basvuruService.updateBasvuru(basvuruId, requestBasvuruDTO);
		return createResponseForSuccess(HttpStatus.CREATED, responseBasvuruDTO, responseBasvuruDTO.getBasvuruId() + " nolu basvuru başarıyla güncellendi");
	}

	@DeleteMapping(value = "/basvuru/{basvuruId}")
	public ResponseEntity<GenericServerResponse> deleteBasvuru(@PathVariable Long basvuruId) {
		BasvuruDTO basvuruDTO = basvuruService.deleteBasvuruById(basvuruId);
		return createResponseForSuccess(HttpStatus.OK, basvuruDTO, basvuruDTO.getBasvuruId() + " nolu basvuru başarıyla silindi");
	}

	@PatchMapping(value = "/basvuru/{basvuruId}")
	public ResponseEntity<GenericServerResponse> patchBasvuru(@PathVariable Long basvuruId, @RequestBody Map<String,String> values) {
		BasvuruDTO basvuruDTO = basvuruService.patchBasvuruById(basvuruId, values);
		return createResponseForSuccess(HttpStatus.OK, basvuruDTO, basvuruDTO.getBasvuruId() + " nolu basvuru başarıyla gücellendi");
	}
}
