package ayu.edu.tr.iskolik.iletisim.domain.service.impl;

import ayu.edu.tr.iskolik.common.domain.exception.ErrorCode;
import ayu.edu.tr.iskolik.common.domain.exception.IskolikOrtakException;
import ayu.edu.tr.iskolik.iletisim.domain.model.dto.IletisimDTO;
import ayu.edu.tr.iskolik.iletisim.domain.model.entity.Iletisim;
import ayu.edu.tr.iskolik.iletisim.domain.model.mapper.IletisimDTOMapper;
import ayu.edu.tr.iskolik.iletisim.domain.repository.IletisimRepository;
import ayu.edu.tr.iskolik.iletisim.domain.service.IletisimService;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class IletisimServiceImpl implements IletisimService {

	private final IletisimRepository iletisimRepository;
	private final IletisimDTOMapper iletisimDTOMapper;

	public IletisimServiceImpl(IletisimRepository iletisimRepository, IletisimDTOMapper iletisimDTOMapper) {
		this.iletisimRepository = iletisimRepository;
		this.iletisimDTOMapper = iletisimDTOMapper;
	}

	@Override
	public IletisimDTO findIletisimById(Long id) {
		Optional<Iletisim> Iletisim = iletisimRepository.findById(id);

		IletisimDTO iletisimDTO;
		if (Iletisim.isPresent()) {
			iletisimDTO = iletisimDTOMapper.toIletisimDTO(Iletisim.orElse(null));
		} else {
			throw new IskolikOrtakException(ErrorCode.VALIDATION_BUSINESS_RESOURCE_NOT_FOUND, String.valueOf(id));
		}

		return iletisimDTO;
	}

	@Override
	public List<IletisimDTO> findAll(Specification specification, Pageable pageable) {
		return iletisimDTOMapper.toIletisimDTOList(iletisimRepository.findAll(specification, pageable).toList());
	}

	@Override
	public IletisimDTO saveIletisim(IletisimDTO iletisimDTO) {
		if (iletisimDTO.getIletisimId() != null) {
			throw new IskolikOrtakException(ErrorCode.VALIDATION_BUSINESS_FIELD_NOT_NULL, "id");
		}

		Iletisim iletisim = iletisimDTOMapper.toIletisim(iletisimDTO);
		Iletisim savedIletisim = iletisimRepository.save(iletisim);
		return iletisimDTOMapper.toIletisimDTO(savedIletisim);
	}

	@Override
	public IletisimDTO updateIletisim(Long id, IletisimDTO iletisimDTO) {
		if (iletisimDTO.getIletisimId() != null) {
			throw new IskolikOrtakException(ErrorCode.VALIDATION_BUSINESS_FIELD_NOT_NULL, "id");
		}

		if (iletisimRepository.findById(id).isEmpty()) {
			throw new IskolikOrtakException(ErrorCode.VALIDATION_BUSINESS_RESOURCE_NOT_FOUND, String.valueOf(id));
		}

		iletisimDTO.setIletisimId(id);
		Iletisim iletisim = iletisimDTOMapper.toIletisim(iletisimDTO);
		iletisimRepository.save(iletisim);

		return iletisimDTO;
	}

	@Override
	public IletisimDTO deleteIletisimById(Long id) {
		Optional<Iletisim> Iletisim = iletisimRepository.findById(id);
		if (Iletisim.isEmpty()) {
			throw new IskolikOrtakException(ErrorCode.VALIDATION_BUSINESS_RESOURCE_NOT_FOUND, String.valueOf(id));
		}

		IletisimDTO iletisimDTO = iletisimDTOMapper.toIletisimDTO(Iletisim.get());
		iletisimRepository.deleteById(id);
		return iletisimDTO;
	}
}
