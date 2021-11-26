package ayu.edu.tr.iskolik.iletisim.domain.service.impl;

import ayu.edu.tr.iskolik.common.domain.exception.ErrorCode;
import ayu.edu.tr.iskolik.common.domain.exception.IskolikOrtakException;
import ayu.edu.tr.iskolik.iletisim.domain.model.dto.AdresDTO;
import ayu.edu.tr.iskolik.iletisim.domain.model.entity.Adres;
import ayu.edu.tr.iskolik.iletisim.domain.model.mapper.AdresDTOMapper;
import ayu.edu.tr.iskolik.iletisim.domain.repository.AdresRepository;
import ayu.edu.tr.iskolik.iletisim.domain.service.AdresService;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class AdresServiceImpl implements AdresService {

	private final AdresRepository adresRepository;
	private final AdresDTOMapper adresDTOMapper;

	public AdresServiceImpl(AdresRepository adresRepository, AdresDTOMapper adresDTOMapper) {
		this.adresRepository = adresRepository;
		this.adresDTOMapper = adresDTOMapper;
	}

	@Override
	public AdresDTO findAdresById(Long id) {
		Optional<Adres> Adres = adresRepository.findById(id);

		AdresDTO AdresDTO;
		if (Adres.isPresent()) {
			AdresDTO = adresDTOMapper.toAdresDTO(Adres.orElse(null));
		} else {
			throw new IskolikOrtakException(ErrorCode.VALIDATION_BUSINESS_RESOURCE_NOT_FOUND, String.valueOf(id));
		}

		return AdresDTO;
	}

	@Override
	public List<AdresDTO> findAll(Specification specification, Pageable pageable) {
		return adresDTOMapper.toAdresDTOList(adresRepository.findAll(specification, pageable).toList());
	}

	@Override
	public AdresDTO saveAdres(AdresDTO AdresDTO) {
		if (AdresDTO.getAdresId() != null) {
			throw new IskolikOrtakException(ErrorCode.VALIDATION_BUSINESS_FIELD_NOT_NULL, "id");
		}

		Adres Adres = adresDTOMapper.toAdres(AdresDTO);
		Adres savedAdres = adresRepository.save(Adres);
		return adresDTOMapper.toAdresDTO(savedAdres);
	}

	@Override
	public AdresDTO updateAdres(Long id, AdresDTO AdresDTO) {
		if (AdresDTO.getAdresId() != null) {
			throw new IskolikOrtakException(ErrorCode.VALIDATION_BUSINESS_FIELD_NOT_NULL, "id");
		}

		if (adresRepository.findById(id).isEmpty()) {
			throw new IskolikOrtakException(ErrorCode.VALIDATION_BUSINESS_RESOURCE_NOT_FOUND, String.valueOf(id));
		}

		AdresDTO.setAdresId(id);
		Adres Adres = adresDTOMapper.toAdres(AdresDTO);
		adresRepository.save(Adres);

		return AdresDTO;
	}

	@Override
	public AdresDTO deleteAdresById(Long id) {
		Optional<Adres> Adres = adresRepository.findById(id);
		if (Adres.isEmpty()) {
			throw new IskolikOrtakException(ErrorCode.VALIDATION_BUSINESS_RESOURCE_NOT_FOUND, String.valueOf(id));
		}

		AdresDTO AdresDTO = adresDTOMapper.toAdresDTO(Adres.get());
		adresRepository.deleteById(id);
		return AdresDTO;
	}
}
