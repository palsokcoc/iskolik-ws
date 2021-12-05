package ayu.edu.tr.iskolik.ilan.domain.service.impl;

import ayu.edu.tr.iskolik.common.domain.exception.ErrorCode;
import ayu.edu.tr.iskolik.common.domain.exception.IskolikOrtakException;
import ayu.edu.tr.iskolik.ilan.domain.model.dto.IlanDTO;
import ayu.edu.tr.iskolik.ilan.domain.model.entity.Ilan;
import ayu.edu.tr.iskolik.ilan.domain.model.mapper.IlanDTOMapper;
import ayu.edu.tr.iskolik.ilan.domain.repository.IlanRepository;
import ayu.edu.tr.iskolik.ilan.domain.service.IlanService;
import ayu.edu.tr.iskolik.kategori.domain.model.dto.KategoriDTO;
import ayu.edu.tr.iskolik.kullanici.domain.model.dto.KullaniciDTO;
import ayu.edu.tr.iskolik.kullanici.domain.model.entity.KurumsalKullanici;
import ayu.edu.tr.iskolik.kullanici.domain.model.mapper.KullaniciDTOMapper;
import ayu.edu.tr.iskolik.kullanici.domain.service.KullaniciService;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class IlanServiceImpl implements IlanService {

	private final IlanRepository ilanRepository;
	private final IlanDTOMapper ilanDTOMapper;
	private final KullaniciService kullaniciService;
	private final KullaniciDTOMapper kullaniciDTOMapper;

	public IlanServiceImpl(IlanRepository ilanRepository, IlanDTOMapper ilanDTOMapper, KullaniciService kullaniciService, KullaniciDTOMapper kullaniciDTOMapper) {
		this.ilanRepository = ilanRepository;
		this.ilanDTOMapper = ilanDTOMapper;
		this.kullaniciService = kullaniciService;
		this.kullaniciDTOMapper = kullaniciDTOMapper;
	}

	@Override
	public IlanDTO findIlanById(Long id) {
		Optional<Ilan> ilan = ilanRepository.findById(id);

		IlanDTO IlanDTO;
		if (ilan.isPresent()) {
			IlanDTO = ilanDTOMapper.toIlanDTO(ilan.orElse(null));
		} else {
			throw new IskolikOrtakException(ErrorCode.VALIDATION_BUSINESS_RESOURCE_NOT_FOUND, String.valueOf(id));
		}

		return IlanDTO;
	}

	@Override
	public List<IlanDTO> findAll(Specification specification, Pageable pageable) {
		return ilanDTOMapper.toIlanDTOList(ilanRepository.findAll(specification, pageable).toList());
	}

	@Override
	public IlanDTO saveIlan(IlanDTO ilanDTO) {
		if (ilanDTO.getIlanId() != null) {
			throw new IskolikOrtakException(ErrorCode.VALIDATION_BUSINESS_FIELD_NOT_NULL, "id");
		}


		Ilan ilan = ilanDTOMapper.toIlan(ilanDTO);

		if(ilanDTO.getKullanici() != null && ilanDTO.getKullanici().getKullaniciId() != null) {
			KullaniciDTO kullaniciDTO = kullaniciService.findKullaniciById(ilanDTO.getKullanici().getKullaniciId());
			if (kullaniciDTO!=null) {
				ilan.setKullanici((KurumsalKullanici) kullaniciDTOMapper.toKullanici(kullaniciDTO));
			}
		}

		ilan.setGirisTarihi(LocalDate.now());
		Ilan savedIlan = ilanRepository.save(ilan);
		return ilanDTOMapper.toIlanDTO(savedIlan);
	}

	@Override
	public IlanDTO updateIlan(Long id, IlanDTO ilanDTO) {
		if (ilanDTO.getIlanId() == null) {
			throw new IskolikOrtakException(ErrorCode.VALIDATION_BUSINESS_FIELD_NULL, "id");
		}

		if (ilanRepository.findById(id).isEmpty()) {
			throw new IskolikOrtakException(ErrorCode.VALIDATION_BUSINESS_RESOURCE_NOT_FOUND, String.valueOf(id));
		}

		ilanDTO.setIlanId(id);
		Ilan ilan = ilanDTOMapper.toIlan(ilanDTO);

		if(ilanDTO.getKullanici() != null && ilanDTO.getKullanici().getKullaniciId() != null) {
			KullaniciDTO kullaniciDTO = kullaniciService.findKullaniciById(ilanDTO.getKullanici().getKullaniciId());
			if (kullaniciDTO!=null) {
				ilan.setKullanici((KurumsalKullanici) kullaniciDTOMapper.toKullanici(kullaniciDTO));
			}
		}

		ilan = ilanRepository.save(ilan);

		return ilanDTOMapper.toIlanDTO(ilan);
	}

	@Override
	public IlanDTO deleteIlanById(Long id) {
		Optional<Ilan> Ilan = ilanRepository.findById(id);
		if (Ilan.isEmpty()) {
			throw new IskolikOrtakException(ErrorCode.VALIDATION_BUSINESS_RESOURCE_NOT_FOUND, String.valueOf(id));
		}

		IlanDTO IlanDTO = ilanDTOMapper.toIlanDTO(Ilan.get());
		ilanRepository.deleteById(id);
		return IlanDTO;
	}
}
