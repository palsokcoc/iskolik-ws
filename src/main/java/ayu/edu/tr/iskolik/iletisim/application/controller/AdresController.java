package ayu.edu.tr.iskolik.iletisim.application.controller;

import ayu.edu.tr.iskolik.common.application.controller.BaseController;
import ayu.edu.tr.iskolik.common.application.model.request.validation.PostValidation;
import ayu.edu.tr.iskolik.common.domain.repository.BaseSpecification;
import ayu.edu.tr.iskolik.common.domain.repository.filter.Filters;
import ayu.edu.tr.iskolik.common.model.response.GenericServerResponse;
import ayu.edu.tr.iskolik.iletisim.application.model.mapper.AdresRequestMapper;
import ayu.edu.tr.iskolik.iletisim.application.model.request.AdresRequest;
import ayu.edu.tr.iskolik.iletisim.domain.model.dto.AdresDTO;
import ayu.edu.tr.iskolik.iletisim.domain.service.AdresService;
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
@RequestMapping(value = "/adres")
public class AdresController extends BaseController {

	private final AdresService adresService;
	private final AdresRequestMapper adresRequestMapper;

	public AdresController(AdresService adresService, AdresRequestMapper adresRequestMapper) {
		this.adresService = adresService;
		this.adresRequestMapper = adresRequestMapper;
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<GenericServerResponse> getAdresById(@PathVariable Long id) {
		AdresDTO adresDTO = adresService.findAdresById(id);
		return createResponseForSuccess(HttpStatus.OK, adresDTO);
	}

	@GetMapping(value = "")
	public ResponseEntity<GenericServerResponse> getAdresList(@ModelAttribute() Filters filters, @PageableDefault(size = Integer.MAX_VALUE) Pageable pageable) {
		BaseSpecification specification = new BaseSpecification(filters);
		List<AdresDTO> musteriDTOList = adresService.findAll(specification,pageable);
		return createResponseForSuccess(HttpStatus.OK, musteriDTOList);
	}

	@PostMapping(value = "")
	public ResponseEntity<GenericServerResponse> saveAdres(@Validated(PostValidation.class) @RequestBody AdresRequest adresRequest) {
		AdresDTO requestAdresDTO = adresRequestMapper.toAdresDTO(adresRequest);
		AdresDTO responseAdresDTO = adresService.saveAdres(requestAdresDTO);
		return createResponseForSuccess(HttpStatus.CREATED, responseAdresDTO, "Adres başarıyla kaydedildi");
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<GenericServerResponse> updateAdres(@PathVariable Long id, @Validated(PostValidation.class) @RequestBody AdresRequest adresRequest) {
		AdresDTO requestAdresDTO = adresRequestMapper.toAdresDTO(adresRequest);
		AdresDTO responseAdresDTO = adresService.updateAdres(id, requestAdresDTO);
		return createResponseForSuccess(HttpStatus.CREATED, responseAdresDTO, "Adres başarıyla güncellendi");
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<GenericServerResponse> deleteAdres(@PathVariable Long id) {
		AdresDTO adresDTO = adresService.deleteAdresById(id);
		return createResponseForSuccess(HttpStatus.OK, adresDTO, "Adres başarıyla silindi");
	}
}
