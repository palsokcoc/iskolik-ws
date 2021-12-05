package ayu.edu.tr.iskolik.kategori.application.controller;

import ayu.edu.tr.iskolik.common.application.controller.BaseController;
import ayu.edu.tr.iskolik.common.application.model.request.validation.PostValidation;
import ayu.edu.tr.iskolik.common.application.model.request.validation.PutValidation;
import ayu.edu.tr.iskolik.common.domain.repository.BaseSpecification;
import ayu.edu.tr.iskolik.common.domain.repository.filter.Filters;
import ayu.edu.tr.iskolik.common.model.response.GenericServerResponse;
import ayu.edu.tr.iskolik.kategori.domain.model.dto.KategoriDTO;
import ayu.edu.tr.iskolik.kategori.domain.service.KategoriService;
import ayu.edu.tr.iskolik.kategori.application.model.mapper.KategoriRequestMapper;
import ayu.edu.tr.iskolik.kategori.application.model.request.KategoriRequest;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@RequestMapping(value = "/kategori")
public class KategoriController extends BaseController {

	private final KategoriService kategoriService;
	private final KategoriRequestMapper kategoriRequestMapper;

	public KategoriController(KategoriService kategoriService, KategoriRequestMapper kategoriRequestMapper) {
		this.kategoriService = kategoriService;
		this.kategoriRequestMapper = kategoriRequestMapper;
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<GenericServerResponse> getKategoriById(@PathVariable Long id) {
		KategoriDTO kategoriDTO = kategoriService.findKategoriById(id);
		return createResponseForSuccess(HttpStatus.OK, kategoriDTO);
	}

	@GetMapping(value = "")
	public ResponseEntity<GenericServerResponse> getKategoriList(@ModelAttribute() Filters filters, @PageableDefault(size = Integer.MAX_VALUE) Pageable pageable) {
		BaseSpecification specification = new BaseSpecification(filters);
		List<KategoriDTO> kategoriDTOList = kategoriService.findAll(specification,pageable);
		return createResponseForSuccess(HttpStatus.OK, kategoriDTOList);
	}

	@PostMapping(value = "/")
	public ResponseEntity<GenericServerResponse> saveKategori(@Validated(PostValidation.class) @RequestBody KategoriRequest kategoriRequest) {
		KategoriDTO requestKategoriDTO = kategoriRequestMapper.toKategoriDTO(kategoriRequest);
		KategoriDTO responseKategoriDTO = kategoriService.saveKategori(requestKategoriDTO);
		return createResponseForSuccess(HttpStatus.CREATED, responseKategoriDTO, responseKategoriDTO.getAd() + " adlı kategori başarıyla kaydedildi");
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<GenericServerResponse> updateKategori(@PathVariable Long id, @Validated(PutValidation.class) @RequestBody KategoriRequest kategoriRequest) {
		KategoriDTO requestKategoriDTO = kategoriRequestMapper.toKategoriDTO(kategoriRequest);
		KategoriDTO responseKategoriDTO = kategoriService.updateKategori(id, requestKategoriDTO);
		return createResponseForSuccess(HttpStatus.CREATED, responseKategoriDTO, responseKategoriDTO.getAd() + " adlı kategori başarıyla güncellendi");
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<GenericServerResponse> deleteKategori(@PathVariable Long id) {
		KategoriDTO kategoriDTO = kategoriService.deleteKategoriById(id);
		return createResponseForSuccess(HttpStatus.OK, kategoriDTO, kategoriDTO.getAd() + " adlı kategori başarıyla silindi");
	}
}
