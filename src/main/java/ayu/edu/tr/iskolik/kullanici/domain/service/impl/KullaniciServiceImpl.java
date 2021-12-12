package ayu.edu.tr.iskolik.kullanici.domain.service.impl;

import ayu.edu.tr.iskolik.common.domain.exception.ErrorCode;
import ayu.edu.tr.iskolik.common.domain.exception.IskolikOrtakException;
import ayu.edu.tr.iskolik.common.domain.repository.BaseSpecification;
import ayu.edu.tr.iskolik.common.domain.repository.filter.Filter;
import ayu.edu.tr.iskolik.common.domain.repository.filter.Filters;
import ayu.edu.tr.iskolik.kullanici.domain.model.dto.BireyselKullaniciDTO;
import ayu.edu.tr.iskolik.kullanici.domain.model.dto.ElemanAramaSonucuDTO;
import ayu.edu.tr.iskolik.kullanici.domain.model.dto.KullaniciDTO;
import ayu.edu.tr.iskolik.kullanici.domain.model.entity.Kullanici;
import ayu.edu.tr.iskolik.kullanici.domain.model.mapper.KullaniciDTOMapper;
import ayu.edu.tr.iskolik.kullanici.domain.repository.KullaniciRepository;
import ayu.edu.tr.iskolik.kullanici.domain.service.KullaniciService;
import com.fasterxml.jackson.databind.ser.Serializers.Base;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class KullaniciServiceImpl implements KullaniciService {

	private final KullaniciRepository kullaniciRepository;
	private final KullaniciDTOMapper kullaniciDTOMapper;

	public KullaniciServiceImpl(KullaniciRepository kullaniciRepository, KullaniciDTOMapper kullaniciDTOMapper) {
		this.kullaniciRepository = kullaniciRepository;
		this.kullaniciDTOMapper = kullaniciDTOMapper;
	}

	@Override
	public KullaniciDTO findKullaniciById(Long id) {
		Optional<Kullanici> kullanici = kullaniciRepository.findById(id);

		KullaniciDTO kullaniciDTO;
		if (kullanici.isPresent()) {
			kullaniciDTO = kullaniciDTOMapper.toKullaniciDTO(kullanici.orElse(null));
		} else {
			throw new IskolikOrtakException(ErrorCode.VALIDATION_BUSINESS_RESOURCE_NOT_FOUND, String.valueOf(id));
		}

		return kullaniciDTO;
	}

	@Override
	public List<KullaniciDTO> findAll(Specification specification, Pageable pageable) {
		return kullaniciDTOMapper.toKullaniciDTOList(kullaniciRepository.findAll(specification, pageable).toList());
	}

	@Override
	public List<ElemanAramaSonucuDTO> findBireyselKullaniciOzet(Specification specification, Pageable pageable) {
		return kullaniciRepository.findBireyselKullaniciOzet(specification, pageable);
	}

	@Override
	public List<BireyselKullaniciDTO> findBireyselKullanici(BaseSpecification specification, Pageable pageable) {
		specification.addFilter(new Filter("tip=Bireysel"));
		return kullaniciDTOMapper.toKullaniciDTOList(kullaniciRepository.findAll(specification, pageable).toList());
	}


	@Override
	public KullaniciDTO saveKullanici(KullaniciDTO kullaniciDTO) {
		if (kullaniciDTO.getKullaniciId() != null) {
			throw new IskolikOrtakException(ErrorCode.VALIDATION_BUSINESS_FIELD_NOT_NULL, "id");
		}

		Kullanici Kullanici = kullaniciDTOMapper.toKullanici(kullaniciDTO);
		Kullanici savedKullanici = kullaniciRepository.save(Kullanici);
		return kullaniciDTOMapper.toKullaniciDTO(savedKullanici);
	}

	@Override
	public KullaniciDTO updateKullanici(Long id, KullaniciDTO KullaniciDTO) {
		if (KullaniciDTO.getKullaniciId() != null) {
			throw new IskolikOrtakException(ErrorCode.VALIDATION_BUSINESS_FIELD_NOT_NULL, "id");
		}

		if (kullaniciRepository.findById(id).isEmpty()) {
			throw new IskolikOrtakException(ErrorCode.VALIDATION_BUSINESS_RESOURCE_NOT_FOUND, String.valueOf(id));
		}

		KullaniciDTO.setKullaniciId(id);
		Kullanici Kullanici = kullaniciDTOMapper.toKullanici(KullaniciDTO);
		kullaniciRepository.save(Kullanici);

		return KullaniciDTO;
	}

	@Override
	public KullaniciDTO deleteKullaniciById(Long id) {
		Optional<Kullanici> Kullanici = kullaniciRepository.findById(id);
		if (Kullanici.isEmpty()) {
			throw new IskolikOrtakException(ErrorCode.VALIDATION_BUSINESS_RESOURCE_NOT_FOUND, String.valueOf(id));
		}

		KullaniciDTO KullaniciDTO = kullaniciDTOMapper.toKullaniciDTO(Kullanici.get());
		kullaniciRepository.deleteById(id);
		return KullaniciDTO;
	}
}
