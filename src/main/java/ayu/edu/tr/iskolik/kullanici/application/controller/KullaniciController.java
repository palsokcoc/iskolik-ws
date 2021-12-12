package ayu.edu.tr.iskolik.kullanici.application.controller;

import ayu.edu.tr.iskolik.common.application.controller.BaseController;
import ayu.edu.tr.iskolik.common.application.model.request.validation.PostValidation;
import ayu.edu.tr.iskolik.common.domain.repository.BaseSpecification;
import ayu.edu.tr.iskolik.common.domain.repository.filter.Filters;
import ayu.edu.tr.iskolik.common.model.response.GenericServerResponse;
import ayu.edu.tr.iskolik.kullanici.application.model.mapper.KullaniciRequestMapper;
import ayu.edu.tr.iskolik.kullanici.application.model.request.KullaniciRequest;
import ayu.edu.tr.iskolik.kullanici.domain.model.dto.BireyselKullaniciDTO;
import ayu.edu.tr.iskolik.kullanici.domain.model.dto.ElemanAramaSonucuDTO;
import ayu.edu.tr.iskolik.kullanici.domain.model.dto.KullaniciDTO;
import ayu.edu.tr.iskolik.kullanici.domain.repository.KullaniciSpecification;
import ayu.edu.tr.iskolik.kullanici.domain.service.KullaniciService;
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
@RequestMapping(value = "/kullanici")
public class KullaniciController extends BaseController {

	private final KullaniciService kullaniciService;
	private final KullaniciRequestMapper kullaniciRequestMapper;

	public KullaniciController(KullaniciService kullaniciService, KullaniciRequestMapper kullaniciRequestMapper) {
		this.kullaniciService = kullaniciService;
		this.kullaniciRequestMapper = kullaniciRequestMapper;
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<GenericServerResponse> getKullaniciById(@PathVariable Long id) {
		KullaniciDTO kullaniciDTO = kullaniciService.findKullaniciById(id);
		return createResponseForSuccess(HttpStatus.OK, kullaniciDTO);
	}

	@GetMapping(value = "")
	public ResponseEntity<GenericServerResponse> getKullaniciList(@ModelAttribute() Filters filters, @PageableDefault(size = Integer.MAX_VALUE) Pageable pageable) {
		BaseSpecification specification = new KullaniciSpecification(filters);
		List<KullaniciDTO> kullaniciDTOList = kullaniciService.findAll(specification, pageable);
		return createResponseForSuccess(HttpStatus.OK, kullaniciDTOList);
	}

	@GetMapping(value = "/aramaSonucu")
	public ResponseEntity<GenericServerResponse> getKullaniciListForElemanArama(@ModelAttribute() Filters filters, @PageableDefault(size = Integer.MAX_VALUE) Pageable pageable) {
		BaseSpecification specification = new KullaniciSpecification(filters);
//		List<ElemanAramaSonucuDTO> aramaSonucuDTOList = kullaniciService.findBireyselKullaniciOzet(specification, pageable);
		List<BireyselKullaniciDTO> aramaSonucuDTOList = kullaniciService.findBireyselKullanici(specification, pageable);
		return createResponseForSuccess(HttpStatus.OK, aramaSonucuDTOList);
	}

	@PostMapping(value = "")
	public ResponseEntity<GenericServerResponse> saveKullanici(@Validated(PostValidation.class) @RequestBody KullaniciRequest kullaniciRequest) {
		KullaniciDTO requestKullaniciDTO = kullaniciRequestMapper.toKullaniciDTO(kullaniciRequest);
		KullaniciDTO responseKullaniciDTO = kullaniciService.saveKullanici(requestKullaniciDTO);
		return createResponseForSuccess(HttpStatus.CREATED, responseKullaniciDTO, "Kullanıcı başarıyla kaydedildi");
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<GenericServerResponse> updateKullanici(@PathVariable Long id, @Validated(PostValidation.class) @RequestBody KullaniciRequest kullaniciRequest) {
		KullaniciDTO requestKullaniciDTO = kullaniciRequestMapper.toKullaniciDTO(kullaniciRequest);
		KullaniciDTO responseKullaniciDTO = kullaniciService.updateKullanici(id, requestKullaniciDTO);
		return createResponseForSuccess(HttpStatus.CREATED, responseKullaniciDTO, "Kullanıcı başarıyla güncellendi");
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<GenericServerResponse> deleteKullanici(@PathVariable Long id) {
		KullaniciDTO kullaniciDTO = kullaniciService.deleteKullaniciById(id);
		return createResponseForSuccess(HttpStatus.OK, kullaniciDTO, "Kullanıcı başarıyla silindi");
	}
}
