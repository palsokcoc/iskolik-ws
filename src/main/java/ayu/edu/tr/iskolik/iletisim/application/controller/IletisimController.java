package ayu.edu.tr.iskolik.iletisim.application.controller;

import ayu.edu.tr.iskolik.common.application.controller.BaseController;
import ayu.edu.tr.iskolik.common.application.model.request.validation.PostValidation;
import ayu.edu.tr.iskolik.common.domain.repository.BaseSpecification;
import ayu.edu.tr.iskolik.common.domain.repository.filter.Filters;
import ayu.edu.tr.iskolik.common.model.response.GenericServerResponse;
import ayu.edu.tr.iskolik.iletisim.application.model.mapper.IletisimRequestMapper;
import ayu.edu.tr.iskolik.iletisim.application.model.request.IletisimRequest;
import ayu.edu.tr.iskolik.iletisim.domain.model.dto.IletisimDTO;
import ayu.edu.tr.iskolik.iletisim.domain.service.IletisimService;
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
@RequestMapping(value = "/iletisim")
public class IletisimController extends BaseController {

	private final IletisimService iletisimService;
	private final IletisimRequestMapper iletisimRequestMapper;

	public IletisimController(IletisimService iletisimService, IletisimRequestMapper iletisimRequestMapper) {
		this.iletisimService = iletisimService;
		this.iletisimRequestMapper = iletisimRequestMapper;
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<GenericServerResponse> getIletisimById(@PathVariable Long id) {
		IletisimDTO iletisimDTO = iletisimService.findIletisimById(id);
		return createResponseForSuccess(HttpStatus.OK, iletisimDTO);
	}

	@GetMapping(value = "")
	public ResponseEntity<GenericServerResponse> getIletisimList(@ModelAttribute() Filters filters, @PageableDefault(size = Integer.MAX_VALUE) Pageable pageable) {
		BaseSpecification specification = new BaseSpecification(filters);
		List<IletisimDTO> musteriDTOList = iletisimService.findAll(specification,pageable);
		return createResponseForSuccess(HttpStatus.OK, musteriDTOList);
	}

	@PostMapping(value = "")
	public ResponseEntity<GenericServerResponse> saveIletisim(@Validated(PostValidation.class) @RequestBody IletisimRequest iletisimRequest) {
		IletisimDTO requestIletisimDTO = iletisimRequestMapper.toIletisimDTO(iletisimRequest);
		IletisimDTO responseIletisimDTO = iletisimService.saveIletisim(requestIletisimDTO);
		return createResponseForSuccess(HttpStatus.CREATED, responseIletisimDTO, "İletişim başarıyla kaydedildi");
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<GenericServerResponse> updateIletisim(@PathVariable Long id, @Validated(PostValidation.class) @RequestBody IletisimRequest iletisimRequest) {
		IletisimDTO requestIletisimDTO = iletisimRequestMapper.toIletisimDTO(iletisimRequest);
		IletisimDTO responseIletisimDTO = iletisimService.updateIletisim(id, requestIletisimDTO);
		return createResponseForSuccess(HttpStatus.CREATED, responseIletisimDTO, "İletişim başarıyla güncellendi");
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<GenericServerResponse> deleteIletisim(@PathVariable Long id) {
		IletisimDTO iletisimDTO = iletisimService.deleteIletisimById(id);
		return createResponseForSuccess(HttpStatus.OK, iletisimDTO, "İletişim başarıyla silindi");
	}
}
