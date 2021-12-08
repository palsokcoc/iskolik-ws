package ayu.edu.tr.iskolik.ilan.domain.service.impl;

import ayu.edu.tr.iskolik.common.domain.exception.ErrorCode;
import ayu.edu.tr.iskolik.common.domain.exception.IskolikOrtakException;
import ayu.edu.tr.iskolik.ilan.domain.model.dto.IlanDTO;
import ayu.edu.tr.iskolik.ilan.domain.model.entity.Ilan;
import ayu.edu.tr.iskolik.ilan.domain.model.entity.Ilan.Durum;
import ayu.edu.tr.iskolik.ilan.domain.model.mapper.IlanDTOMapper;
import ayu.edu.tr.iskolik.ilan.domain.repository.IlanRepository;
import ayu.edu.tr.iskolik.ilan.domain.service.IlanService;
import ayu.edu.tr.iskolik.kullanici.domain.model.dto.KullaniciDTO;
import ayu.edu.tr.iskolik.kullanici.domain.model.entity.KurumsalKullanici;
import ayu.edu.tr.iskolik.kullanici.domain.model.mapper.KullaniciDTOMapper;
import ayu.edu.tr.iskolik.kullanici.domain.service.KullaniciService;
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

		if(ilanDTO.getYayinTarihi() != null && ilanDTO.getSonBasvuruTarihi() != null && ilanDTO.getSonBasvuruTarihi().isBefore(ilanDTO.getYayinTarihi())) {
			throw new IskolikOrtakException(ErrorCode.CUSTOM_ERROR, "Son başvuru tarihi yayın tarihinden önce olamaz");
		}

		if(ilanDTO.getMinMaas() > ilanDTO.getMaxMaas()) {
			throw new IskolikOrtakException(ErrorCode.CUSTOM_ERROR, "Maaş aralığı küçükten büyüğe olmalıdır");
		}

		ilan.setGirisTarihi(LocalDate.now());
		Ilan savedIlan = ilanRepository.save(ilan);
		return ilanDTOMapper.toIlanDTO(savedIlan);
	}

	@Override
	public IlanDTO updateIlan(Long id, IlanDTO ilanDTO) {
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

		if(ilanDTO.getYayinTarihi() != null && ilanDTO.getSonBasvuruTarihi() != null && ilanDTO.getSonBasvuruTarihi().isBefore(ilanDTO.getYayinTarihi())) {
			throw new IskolikOrtakException(ErrorCode.CUSTOM_ERROR, "Son başvuru tarihi yayın tarihinden önce olamaz");
		}

		if(ilanDTO.getMinMaas() > ilanDTO.getMaxMaas()) {
			throw new IskolikOrtakException(ErrorCode.CUSTOM_ERROR, "Maaş aralığı küçükten büyüğe olmalıdır");
		}

		ilan = ilanRepository.save(ilan);

		return ilanDTOMapper.toIlanDTO(ilan);
	}

	@Override
	public IlanDTO deleteIlanById(Long id) {
		Optional<Ilan> ilan = ilanRepository.findById(id);
		if (ilan.isEmpty()) {
			throw new IskolikOrtakException(ErrorCode.VALIDATION_BUSINESS_RESOURCE_NOT_FOUND, String.valueOf(id));
		}

		IlanDTO ilanDTO = ilanDTOMapper.toIlanDTO(ilan.get());
		ilanRepository.deleteById(id);
		return ilanDTO;
	}

	@Override
	public IlanDTO patchIlanById(Long id, Map<String, String> values) {
		Ilan ilan = ilanRepository.findById(id).orElseThrow(() -> new IskolikOrtakException(ErrorCode.VALIDATION_BUSINESS_RESOURCE_NOT_FOUND, String.valueOf(id)));

		final String durumValue = values.get("durum");
			if(durumValue!= null) {
				Durum durum;
				try {
					durum = Durum.valueOf(durumValue);
				} catch (IllegalArgumentException e) {
					throw new IskolikOrtakException(ErrorCode.VALIDATION_BUSINESS_PARAMETER_INVALID, e, "durum", durumValue);
				}
				if(ilan.getDurum() == durum) {
					throw new IskolikOrtakException(ErrorCode.CUSTOM_ERROR, id + " nolu ilan zaten " + durum.getAciklama() + " durumundadır");
				}

				if(durum == Durum.IPTAL) {
					ilan.setIptalTarihi(LocalDate.now());
				}

				ilan.setDurum(durum);
			}

		IlanDTO ilanDTO = ilanDTOMapper.toIlanDTO(ilan);
		return ilanDTO;
	}
}
