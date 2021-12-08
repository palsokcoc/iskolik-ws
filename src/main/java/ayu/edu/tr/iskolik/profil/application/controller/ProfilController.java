package ayu.edu.tr.iskolik.profil.application.controller;

import ayu.edu.tr.iskolik.common.application.controller.BaseController;
import ayu.edu.tr.iskolik.common.application.model.request.validation.PostValidation;
import ayu.edu.tr.iskolik.common.application.model.request.validation.PutValidation;
import ayu.edu.tr.iskolik.common.domain.repository.BaseSpecification;
import ayu.edu.tr.iskolik.common.domain.repository.filter.Filters;
import ayu.edu.tr.iskolik.common.model.response.GenericServerResponse;
import ayu.edu.tr.iskolik.infrastructure.configuration.IskolikConfigutarion;
import ayu.edu.tr.iskolik.profil.application.model.mapper.ProfilRequestMapper;
import ayu.edu.tr.iskolik.profil.application.model.request.ProfilRequest;
import ayu.edu.tr.iskolik.profil.domain.model.dto.ProfilDTO;
import ayu.edu.tr.iskolik.profil.domain.service.ProfilService;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/kullanici/{kullaniciId}/profil")
public class ProfilController extends BaseController {

	private final ProfilService profilService;
	private final ProfilRequestMapper profilRequestMapper;

	public ProfilController(ProfilService profilService, ProfilRequestMapper profilRequestMapper) {
		this.profilService = profilService;
		this.profilRequestMapper = profilRequestMapper;
	}

	@GetMapping(value = "")
	public ResponseEntity<GenericServerResponse> getProfilById(@PathVariable Long kullaniciId) {
		ProfilDTO profilDTO = profilService.findProfilById(kullaniciId);
		return createResponseForSuccess(HttpStatus.OK, profilDTO);
	}

	@PostMapping(value = "")
	public ResponseEntity<GenericServerResponse> saveProfil(@Validated(PostValidation.class) @RequestBody ProfilRequest profilRequest) {
		ProfilDTO requestProfilDTO = profilRequestMapper.toProfilDTO(profilRequest);
		ProfilDTO responseProfilDTO = profilService.saveProfil(requestProfilDTO);
		return createResponseForSuccess(HttpStatus.CREATED, responseProfilDTO, responseProfilDTO.getKullaniciId() + " nolu profil başarıyla kaydedildi");
	}

	@PutMapping(value = "")
	public ResponseEntity<GenericServerResponse> updateProfil(@PathVariable Long kullaniciId, @Validated(PutValidation.class) @RequestBody ProfilRequest profilRequest) {
		ProfilDTO requestProfilDTO = profilRequestMapper.toProfilDTO(profilRequest);
		ProfilDTO responseProfilDTO = profilService.updateProfil(kullaniciId, requestProfilDTO);
		return createResponseForSuccess(HttpStatus.CREATED, responseProfilDTO, responseProfilDTO.getKullaniciId() + " nolu profil başarıyla güncellendi");
	}

	@DeleteMapping(value = "")
	public ResponseEntity<GenericServerResponse> deleteProfil(@PathVariable Long kullaniciId) {
		ProfilDTO profilDTO = profilService.deleteProfilById(kullaniciId);
		return createResponseForSuccess(HttpStatus.OK, profilDTO, profilDTO.getKullaniciId() + " nolu profil başarıyla silindi");
	}
}
