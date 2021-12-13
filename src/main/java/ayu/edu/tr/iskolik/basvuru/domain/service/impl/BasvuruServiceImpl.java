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
import ayu.edu.tr.iskolik.ilan.domain.model.entity.Ilan;
import ayu.edu.tr.iskolik.ilan.domain.model.mapper.IlanDTOMapper;
import ayu.edu.tr.iskolik.ilan.domain.repository.IlanRepository;
import ayu.edu.tr.iskolik.kullanici.domain.model.entity.Kullanici;
import ayu.edu.tr.iskolik.kullanici.domain.model.mapper.KullaniciDTOMapper;
import ayu.edu.tr.iskolik.kullanici.domain.repository.KullaniciRepository;
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

	private KullaniciRepository kullaniciRepository;
	private KullaniciDTOMapper kullaniciDTOMapper;

	private IlanRepository ilanRepository;
	private IlanDTOMapper ilanDTOMapper;

	public BasvuruServiceImpl(BasvuruRepository basvuruRepository, BasvuruDTOMapper basvuruDTOMapper, KullaniciRepository kullaniciRepository, KullaniciDTOMapper kullaniciDTOMapper, IlanRepository ilanRepository, IlanDTOMapper ilanDTOMapper) {
		this.basvuruRepository = basvuruRepository;
		this.basvuruDTOMapper = basvuruDTOMapper;
		this.kullaniciRepository = kullaniciRepository;
		this.kullaniciDTOMapper = kullaniciDTOMapper;
		this.ilanRepository = ilanRepository;
		this.ilanDTOMapper = ilanDTOMapper;
	}

	@Override
	public List<BasvuruDTO> findAllByIlanId(Long ilanId, Filters filters, Pageable pageable) {
		filters.addFilter(new Filter("ilanId=" + ilanId));
		Specification specification = new BaseSpecification(filters);
		return basvuruDTOMapper.toBasvuruDTOList(basvuruRepository.findAll(specification, pageable).toList());
	}

	@Override
	public List<BasvuruDTO> findAllByKullaniciId(Long kullaniciId, Filters filters, Pageable pageable) {
		filters.addFilter(new Filter("kullanici.kullaniciId=" + kullaniciId));
		Specification specification = new BaseSpecification(filters);
		return basvuruDTOMapper.toBasvuruDTOList(basvuruRepository.findAll(specification, pageable).toList());
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

		Kullanici kullanici = kullaniciRepository.findById(basvuruDTO.getKullanici().getKullaniciId()).orElseThrow(() -> new IskolikOrtakException(ErrorCode.VALIDATION_BUSINESS_RESOURCE_NOT_FOUND, String.valueOf(basvuruDTO.getKullanici().getKullaniciId())));
		Ilan ilan = ilanRepository.findById(basvuruDTO.getIlan().getIlanId()).orElseThrow(() -> new IskolikOrtakException(ErrorCode.VALIDATION_BUSINESS_RESOURCE_NOT_FOUND, String.valueOf(basvuruDTO.getIlan().getIlanId())));
		Basvuru basvuru = basvuruRepository.findByKullaniciAndIlan(kullanici, ilan);
		if(basvuru != null) {
			throw new IskolikOrtakException(ErrorCode.VALIDATION_BUSINESS_RESOURCE_ALREADY_EXISTS, "Başvuru");
		}

		basvuru = basvuruDTOMapper.toBasvuru(basvuruDTO);
		basvuru.setBasvuruTarihi(LocalDate.now());
		basvuru.setDurum(Durum.AKTIF);

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
