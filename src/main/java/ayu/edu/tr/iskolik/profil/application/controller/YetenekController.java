package ayu.edu.tr.iskolik.profil.application.controller;

import ayu.edu.tr.iskolik.common.application.controller.BaseController;
import ayu.edu.tr.iskolik.common.application.model.request.validation.PutValidation;
import ayu.edu.tr.iskolik.common.domain.repository.filter.Filters;
import ayu.edu.tr.iskolik.common.model.response.GenericServerResponse;
import ayu.edu.tr.iskolik.profil.application.model.mapper.YetenekRequestMapper;
import ayu.edu.tr.iskolik.profil.application.model.request.YetenekRequest;
import ayu.edu.tr.iskolik.profil.domain.model.dto.YetenekDTO;
import ayu.edu.tr.iskolik.profil.domain.service.YetenekService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/kullanici/{kullaniciId}/profil/yetenek")
public class YetenekController extends BaseController {

	private final YetenekService yetenekService;
	private final YetenekRequestMapper yetenekRequestMapper;

	public YetenekController(YetenekService yetenekService, YetenekRequestMapper yetenekRequestMapper) {
		this.yetenekService = yetenekService;
		this.yetenekRequestMapper = yetenekRequestMapper;
	}

	@GetMapping(value = "/")
	public ResponseEntity<GenericServerResponse> getAllYetenekByKullaniciId(@PathVariable Long kullaniciId, @ModelAttribute() Filters filters) {
		List<YetenekDTO> yetenekDTOList = yetenekService.findAllByKullaniciId(kullaniciId, filters);
		return createResponseForSuccess(HttpStatus.OK, yetenekDTOList);
	}

	@GetMapping(value = "/{yetenekId}")
	public ResponseEntity<GenericServerResponse> getYetenekById(@PathVariable Long kullaniciId, @PathVariable Long yetenekId) {
		YetenekDTO yetenekDTO = yetenekService.findYetenekByProfilAndYetenekId(kullaniciId, yetenekId);
		return createResponseForSuccess(HttpStatus.OK, yetenekDTO);
	}

	@PutMapping(value = "/{yetenekId}")
	public ResponseEntity<GenericServerResponse> updateYetenek(@PathVariable Long kullaniciId, @PathVariable Long yetenekId, @Validated(PutValidation.class) @RequestBody YetenekRequest yetenekRequest) {
		YetenekDTO requestYetenekDTO = yetenekRequestMapper.toYetenekDTO(yetenekRequest);
		YetenekDTO responseYetenekDTO = yetenekService.updateYetenek(kullaniciId,yetenekId, requestYetenekDTO);
		return createResponseForSuccess(HttpStatus.CREATED, responseYetenekDTO, responseYetenekDTO.getProfil().getKullaniciId() + " nolu yetenek başarıyla güncellendi");
	}

	@DeleteMapping(value = "/{yetenekId}")
	public ResponseEntity<GenericServerResponse> deleteYetenek(@PathVariable Long kullaniciId, @PathVariable Long yetenekId) {
		YetenekDTO responseYetenekDTO = yetenekService.deleteYetenek(kullaniciId, yetenekId);
		return createResponseForSuccess(HttpStatus.CREATED, responseYetenekDTO, responseYetenekDTO.getProfil().getKullaniciId() + " nolu yetenek başarıyla silindi");
	}
}