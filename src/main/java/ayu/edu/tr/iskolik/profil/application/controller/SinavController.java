package ayu.edu.tr.iskolik.profil.application.controller;

import ayu.edu.tr.iskolik.common.application.controller.BaseController;
import ayu.edu.tr.iskolik.common.application.model.request.validation.PutValidation;
import ayu.edu.tr.iskolik.common.model.response.GenericServerResponse;
import ayu.edu.tr.iskolik.profil.application.model.mapper.SinavRequestMapper;
import ayu.edu.tr.iskolik.profil.application.model.request.SinavRequest;
import ayu.edu.tr.iskolik.profil.domain.model.dto.SinavDTO;
import ayu.edu.tr.iskolik.profil.domain.service.SinavService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/kullanici/{kullaniciId}/profil/sinav")
public class SinavController extends BaseController {

	private final SinavService sinavService;
	private final SinavRequestMapper sinavRequestMapper;

	public SinavController(SinavService sinavService, SinavRequestMapper sinavRequestMapper) {
		this.sinavService = sinavService;
		this.sinavRequestMapper = sinavRequestMapper;
	}

	@GetMapping(value = "/{sinavId}")
	public ResponseEntity<GenericServerResponse> getSinavById(@PathVariable Long kullaniciId, @PathVariable Long sinavId) {
		SinavDTO sinavDTO = sinavService.findSinavByProfilAndSinavId(kullaniciId, sinavId);
		return createResponseForSuccess(HttpStatus.OK, sinavDTO);
	}

	@PutMapping(value = "/{sinavId}")
	public ResponseEntity<GenericServerResponse> updateSinav(@PathVariable Long kullaniciId, @PathVariable Long sinavId, @Validated(PutValidation.class) @RequestBody SinavRequest sinavRequest) {
		SinavDTO requestSinavDTO = sinavRequestMapper.toSinavDTO(sinavRequest);
		SinavDTO responseSinavDTO = sinavService.updateSinav(kullaniciId,sinavId, requestSinavDTO);
		return createResponseForSuccess(HttpStatus.CREATED, responseSinavDTO, responseSinavDTO.getProfil().getKullaniciId() + " nolu sinav başarıyla güncellendi");
	}
}