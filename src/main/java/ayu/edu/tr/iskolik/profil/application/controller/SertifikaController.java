package ayu.edu.tr.iskolik.profil.application.controller;

import ayu.edu.tr.iskolik.common.application.controller.BaseController;
import ayu.edu.tr.iskolik.common.application.model.request.validation.PutValidation;
import ayu.edu.tr.iskolik.common.model.response.GenericServerResponse;
import ayu.edu.tr.iskolik.profil.application.model.mapper.SertifikaRequestMapper;
import ayu.edu.tr.iskolik.profil.application.model.request.SertifikaRequest;
import ayu.edu.tr.iskolik.profil.domain.model.dto.SertifikaDTO;
import ayu.edu.tr.iskolik.profil.domain.service.SertifikaService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/kullanici/{kullaniciId}/profil/sertifika")
public class SertifikaController extends BaseController {

	private final SertifikaService sertifikaService;
	private final SertifikaRequestMapper sertifikaRequestMapper;

	public SertifikaController(SertifikaService sertifikaService, SertifikaRequestMapper sertifikaRequestMapper) {
		this.sertifikaService = sertifikaService;
		this.sertifikaRequestMapper = sertifikaRequestMapper;
	}

	@GetMapping(value = "/")
	public ResponseEntity<GenericServerResponse> getAllSertifikaByKullaniciId(@PathVariable Long kullaniciId) {
		List<SertifikaDTO> sertifikaDTOList = sertifikaService.findAllByKullaniciId(kullaniciId);
		return createResponseForSuccess(HttpStatus.OK, sertifikaDTOList);
	}

	@GetMapping(value = "/{sertifikaId}")
	public ResponseEntity<GenericServerResponse> getSertifikaById(@PathVariable Long kullaniciId, @PathVariable Long sertifikaId) {
		SertifikaDTO sertifikaDTO = sertifikaService.findSertifikaByProfilAndSertifikaId(kullaniciId, sertifikaId);
		return createResponseForSuccess(HttpStatus.OK, sertifikaDTO);
	}

	@PutMapping(value = "/{sertifikaId}")
	public ResponseEntity<GenericServerResponse> updateSertifika(@PathVariable Long kullaniciId, @PathVariable Long sertifikaId, @Validated(PutValidation.class) @RequestBody SertifikaRequest sertifikaRequest) {
		SertifikaDTO requestSertifikaDTO = sertifikaRequestMapper.toSertifikaDTO(sertifikaRequest);
		SertifikaDTO responseSertifikaDTO = sertifikaService.updateSertifika(kullaniciId,sertifikaId, requestSertifikaDTO);
		return createResponseForSuccess(HttpStatus.CREATED, responseSertifikaDTO, responseSertifikaDTO.getProfil().getKullaniciId() + " nolu sertifika başarıyla güncellendi");
	}

	@DeleteMapping(value = "/{sertifikaId}")
	public ResponseEntity<GenericServerResponse> deleteSertifika(@PathVariable Long kullaniciId, @PathVariable Long sertifikaId) {
		SertifikaDTO responseSertifikaDTO = sertifikaService.deleteSertifika(kullaniciId,sertifikaId);
		return createResponseForSuccess(HttpStatus.CREATED, responseSertifikaDTO, responseSertifikaDTO.getProfil().getKullaniciId() + " nolu sertifika başarıyla silindi");
	}

}