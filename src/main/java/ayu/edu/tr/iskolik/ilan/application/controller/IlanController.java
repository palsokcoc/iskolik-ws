package ayu.edu.tr.iskolik.ilan.application.controller;

import ayu.edu.tr.iskolik.common.application.controller.BaseController;
import ayu.edu.tr.iskolik.common.application.model.request.validation.PostValidation;
import ayu.edu.tr.iskolik.common.application.model.request.validation.PutValidation;
import ayu.edu.tr.iskolik.common.domain.repository.BaseSpecification;
import ayu.edu.tr.iskolik.common.domain.repository.filter.Filters;
import ayu.edu.tr.iskolik.common.model.response.GenericServerResponse;
import ayu.edu.tr.iskolik.ilan.application.model.mapper.IlanRequestMapper;
import ayu.edu.tr.iskolik.ilan.application.model.request.IlanRequest;
import ayu.edu.tr.iskolik.ilan.domain.model.dto.IlanDTO;
import ayu.edu.tr.iskolik.ilan.domain.service.IlanService;
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
@RequestMapping(value = "/ilan")
public class IlanController extends BaseController {

	private final IlanService ilanService;
	private final IlanRequestMapper ilanRequestMapper;

	public IlanController(IlanService ilanService, IlanRequestMapper ilanRequestMapper) {
		this.ilanService = ilanService;
		this.ilanRequestMapper = ilanRequestMapper;
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<GenericServerResponse> getIlanById(@PathVariable Long id) {
		IlanDTO ilanDTO = ilanService.findIlanById(id);
		return createResponseForSuccess(HttpStatus.OK, ilanDTO);
	}

	@GetMapping(value = "")
	public ResponseEntity<GenericServerResponse> getIlanList(@ModelAttribute() Filters filters, @PageableDefault(size = Integer.MAX_VALUE) Pageable pageable) {
		BaseSpecification specification = new BaseSpecification(filters);
		List<IlanDTO> ilanDTOList = ilanService.findAll(specification,pageable);
		return createResponseForSuccess(HttpStatus.OK, ilanDTOList);
	}

	@PostMapping(value = "")
	public ResponseEntity<GenericServerResponse> saveIlan(@Validated(PostValidation.class) @RequestBody IlanRequest ilanRequest) {
		IlanDTO requestIlanDTO = ilanRequestMapper.toIlanDTO(ilanRequest);
		IlanDTO responseIlanDTO = ilanService.saveIlan(requestIlanDTO);
		return createResponseForSuccess(HttpStatus.CREATED, responseIlanDTO, responseIlanDTO.getIlanId() + " nolu ilan başarıyla kaydedildi");
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<GenericServerResponse> updateIlan(@PathVariable Long id, @Validated(PutValidation.class) @RequestBody IlanRequest ilanRequest) {
		IlanDTO requestIlanDTO = ilanRequestMapper.toIlanDTO(ilanRequest);
		IlanDTO responseIlanDTO = ilanService.updateIlan(id, requestIlanDTO);
		return createResponseForSuccess(HttpStatus.CREATED, responseIlanDTO, responseIlanDTO.getIlanId() + " nolu ilan başarıyla güncellendi");
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<GenericServerResponse> deleteIlan(@PathVariable Long id) {
		IlanDTO ilanDTO = ilanService.deleteIlanById(id);
		return createResponseForSuccess(HttpStatus.OK, ilanDTO, ilanDTO.getIlanId() + " nlan başarıyla silindi");
	}
}
