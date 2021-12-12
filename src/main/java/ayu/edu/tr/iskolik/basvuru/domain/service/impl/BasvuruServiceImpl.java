package ayu.edu.tr.iskolik.basvuru.domain.service.impl;

import ayu.edu.tr.iskolik.basvuru.domain.model.dto.BasvuruDTO;
import ayu.edu.tr.iskolik.basvuru.domain.model.entity.Basvuru;
import ayu.edu.tr.iskolik.basvuru.domain.model.mapper.BasvuruDTOMapper;
import ayu.edu.tr.iskolik.basvuru.domain.repository.BasvuruRepository;
import ayu.edu.tr.iskolik.basvuru.domain.service.BasvuruService;
import ayu.edu.tr.iskolik.common.domain.exception.ErrorCode;
import ayu.edu.tr.iskolik.common.domain.exception.IskolikOrtakException;
import ayu.edu.tr.iskolik.common.domain.repository.BaseSpecification;
import ayu.edu.tr.iskolik.common.domain.repository.filter.Filter;
import ayu.edu.tr.iskolik.common.domain.repository.filter.Filters;
import ayu.edu.tr.iskolik.basvuru.domain.model.entity.Basvuru.Durum;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class BasvuruServiceImpl implements BasvuruService {

	private final BasvuruRepository basvuruRepository;
	private final BasvuruDTOMapper basvuruDTOMapper;

	public BasvuruServiceImpl(BasvuruRepository basvuruRepository, BasvuruDTOMapper basvuruDTOMapper) {
		this.basvuruRepository = basvuruRepository;
		this.basvuruDTOMapper = basvuruDTOMapper;
	}

	@Override
	public List<BasvuruDTO> findAllByIlanId(Long ilanId, Filters filters) {
		filters.addFilter(new Filter("ilanId=" + ilanId));
		Specification specification = new BaseSpecification(filters);
		return basvuruDTOMapper.toBasvuruDTOList(basvuruRepository.findAll(specification));
	}

	@Override
	public List<BasvuruDTO> findAllByKullaniciId(Long kullaniciId, Filters filters) {
		filters.addFilter(new Filter("kullaniciId=" + kullaniciId));
		Specification specification = new BaseSpecification(filters);
		return basvuruDTOMapper.toBasvuruDTOList(basvuruRepository.findAll(specification));
	}

	@Override
	public BasvuruDTO findBasvuruById(Long id) {
		Optional<Basvuru> basvuru = basvuruRepository.findById(id);

		BasvuruDTO BasvuruDTO;
		if (basvuru.isPresent()) {
			BasvuruDTO = basvuruDTOMapper.toBasvuruDTO(basvuru.orElse(null));
		} else {
			throw new IskolikOrtakException(ErrorCode.VALIDATION_BUSINESS_RESOURCE_NOT_FOUND, String.valueOf(id));
		}

		return BasvuruDTO;
	}

	@Override
	public List<BasvuruDTO> findAll(Specification specification, Pageable pageable) {
		return basvuruDTOMapper.toBasvuruDTOList(basvuruRepository.findAll(specification, pageable).toList());
	}

	@Override
	public BasvuruDTO saveBasvuru(BasvuruDTO basvuruDTO) {
		if (basvuruDTO.getBasvuruId() != null) {
			throw new IskolikOrtakException(ErrorCode.VALIDATION_BUSINESS_FIELD_NOT_NULL, "id");
		}

		Basvuru basvuru = basvuruRepository.findByKullaniciIdAndIlanId(basvuruDTO.getKullaniciId(), basvuruDTO.getIlanId());
		if(basvuru != null) {
			throw new IskolikOrtakException(ErrorCode.VALIDATION_BUSINESS_RESOURCE_ALREADY_EXISTS, "Başvuru");
		}

		basvuru = basvuruDTOMapper.toBasvuru(basvuruDTO);

		Basvuru savedBasvuru = basvuruRepository.save(basvuru);
		return basvuruDTOMapper.toBasvuruDTO(savedBasvuru);
	}

	@Override
	public BasvuruDTO updateBasvuru(Long id, BasvuruDTO basvuruDTO) {
		if (id == null) {
			throw new IskolikOrtakException(ErrorCode.VALIDATION_BUSINESS_RESOURCE_NOT_FOUND, String.valueOf(id));
		}
		Basvuru basvuru = basvuruRepository.findById(id).orElseThrow(() -> new IskolikOrtakException(ErrorCode.VALIDATION_BUSINESS_RESOURCE_NOT_FOUND, String.valueOf(id)));

		final Durum durum = basvuruDTO.getDurum();
		if(basvuru.getDurum() == durum) {
			throw new IskolikOrtakException(ErrorCode.CUSTOM_ERROR, id + " nolu başvuru zaten " + durum.getAciklama() + " durumundadır");
		}

		if(durum == Durum.IPTAL) {
			basvuruDTO.setIptalTarihi(LocalDate.now());
		}

		basvuruDTO.setBasvuruId(id);
		basvuru = basvuruDTOMapper.toBasvuru(basvuruDTO);

		basvuruRepository.save(basvuru);

		return basvuruDTO;
	}

	@Override
	public BasvuruDTO deleteBasvuruById(Long id) {
		Optional<Basvuru> basvuru = basvuruRepository.findById(id);
		if (basvuru.isEmpty()) {
			throw new IskolikOrtakException(ErrorCode.VALIDATION_BUSINESS_RESOURCE_NOT_FOUND, String.valueOf(id));
		}

		BasvuruDTO BasvuruDTO = basvuruDTOMapper.toBasvuruDTO(basvuru.get());
		basvuruRepository.deleteById(id);
		return BasvuruDTO;
	}

	@Override
	public BasvuruDTO patchBasvuruById(Long id, Map<String, String> values) {
		Basvuru basvuru = basvuruRepository.findById(id).orElseThrow(() -> new IskolikOrtakException(ErrorCode.VALIDATION_BUSINESS_RESOURCE_NOT_FOUND, String.valueOf(id)));

		final String durumValue = values.get("durum");
		if(durumValue!= null) {
			Durum durum;
			try {
				durum = Durum.valueOf(durumValue);
			} catch (IllegalArgumentException e) {
				throw new IskolikOrtakException(ErrorCode.VALIDATION_BUSINESS_PARAMETER_INVALID, e, "durum", durumValue);
			}
			if(basvuru.getDurum() == durum) {
				throw new IskolikOrtakException(ErrorCode.CUSTOM_ERROR, id + " nolu başvuru zaten " + durum.getAciklama() + " durumundadır");
			}

			if(durum == Durum.IPTAL) {
				basvuru.setIptalTarihi(LocalDate.now());
			}

			basvuru.setDurum(durum);
		}

		BasvuruDTO basvuruDTO = basvuruDTOMapper.toBasvuruDTO(basvuru);
		return basvuruDTO;
	}
}
