package ayu.edu.tr.iskolik.profil.domain.service.impl;

import ayu.edu.tr.iskolik.common.domain.exception.ErrorCode;
import ayu.edu.tr.iskolik.common.domain.exception.IskolikOrtakException;
import ayu.edu.tr.iskolik.common.domain.repository.BaseSpecification;
import ayu.edu.tr.iskolik.common.domain.repository.filter.Filter;
import ayu.edu.tr.iskolik.common.domain.repository.filter.Filters;
import ayu.edu.tr.iskolik.kullanici.domain.model.entity.BireyselKullanici;
import ayu.edu.tr.iskolik.kullanici.domain.model.entity.Kullanici;
import ayu.edu.tr.iskolik.kullanici.domain.model.mapper.KullaniciDTOMapper;
import ayu.edu.tr.iskolik.kullanici.domain.repository.KullaniciRepository;
import ayu.edu.tr.iskolik.profil.domain.model.dto.SertifikaDTO;
import ayu.edu.tr.iskolik.profil.domain.model.entity.Sertifika;
import ayu.edu.tr.iskolik.profil.domain.model.mapper.SertifikaDTOMapper;
import ayu.edu.tr.iskolik.profil.domain.repository.SertifikaRepository;
import ayu.edu.tr.iskolik.profil.domain.service.SertifikaService;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class SertifikaServiceImpl implements SertifikaService {

	private final SertifikaRepository sertifikaRepository;
	private final SertifikaDTOMapper sertifikaDTOMapper;

	private final KullaniciRepository kullaniciRepository;
	private final KullaniciDTOMapper kullaniciDTOMapper;

	public SertifikaServiceImpl(SertifikaRepository sertifikaRepository, SertifikaDTOMapper sertifikaDTOMapper, KullaniciRepository kullaniciRepository, KullaniciDTOMapper kullaniciDTOMapper) {
		this.sertifikaRepository = sertifikaRepository;
		this.sertifikaDTOMapper = sertifikaDTOMapper;
		this.kullaniciRepository = kullaniciRepository;
		this.kullaniciDTOMapper = kullaniciDTOMapper;
	}

	@Override
	public List<SertifikaDTO> findAllByKullaniciId(Long kullaniciId) {
		Filters filters = new Filters();
		filters.addFilter(new Filter("profil.kullaniciId=" + kullaniciId));
		BaseSpecification<Sertifika> specification = new BaseSpecification<>(filters);
		return sertifikaDTOMapper.toSertifikaDTOList(sertifikaRepository.findAll(specification));
	}

	@Override
	public SertifikaDTO findSertifikaByProfilAndSertifikaId(Long kullaniciId, Long sertifikaId) {
		Kullanici kullanici = kullaniciRepository.findById(kullaniciId).orElseThrow(() -> new IskolikOrtakException(ErrorCode.VALIDATION_BUSINESS_RESOURCE_NOT_FOUND, String.valueOf(kullaniciId)));
		try {
			BireyselKullanici bireyselKullanici = (BireyselKullanici) kullanici;
			Sertifika sertifika = sertifikaRepository.findSertifikaByProfilAndSertifikaId(bireyselKullanici.getProfil(), sertifikaId);
			if(sertifika == null) {
				throw new IskolikOrtakException(ErrorCode.VALIDATION_BUSINESS_RESOURCE_NOT_FOUND,String.valueOf(sertifikaId));
			}
			return sertifikaDTOMapper.toSertifikaDTO(sertifika);
		} catch (Exception e) {
			throw new IskolikOrtakException(ErrorCode.VALIDATION_BUSINESS_RESOURCE_NOT_FOUND,String.valueOf(sertifikaId));
		}
	}

	@Override
	public SertifikaDTO updateSertifika(Long kullaniciId, Long sertifikaId, SertifikaDTO sertifikaDTO) {
		Kullanici kullanici = kullaniciRepository.findById(kullaniciId).orElseThrow(() -> new IskolikOrtakException(ErrorCode.VALIDATION_BUSINESS_RESOURCE_NOT_FOUND, String.valueOf(kullaniciId)));
		try {
			BireyselKullanici bireyselKullanici = (BireyselKullanici) kullanici;
			Sertifika sertifika = sertifikaRepository.findSertifikaByProfilAndSertifikaId(bireyselKullanici.getProfil(), sertifikaId);
			if(sertifika == null) {
				throw new IskolikOrtakException(ErrorCode.VALIDATION_BUSINESS_RESOURCE_NOT_FOUND,String.valueOf(sertifikaId));
			}

			sertifika.setSertifikaAdi(sertifikaDTO.getSertifikaAdi());
			sertifikaRepository.save(sertifika);

			return sertifikaDTOMapper.toSertifikaDTO(sertifika);
		} catch (Exception e) {
			throw new IskolikOrtakException(ErrorCode.VALIDATION_BUSINESS_RESOURCE_NOT_FOUND,String.valueOf(sertifikaId));
		}
	}

	@Override
	public SertifikaDTO deleteSertifika(Long kullaniciId, Long sertifikaId) {
		Kullanici kullanici = kullaniciRepository.findById(kullaniciId).orElseThrow(() -> new IskolikOrtakException(ErrorCode.VALIDATION_BUSINESS_RESOURCE_NOT_FOUND, String.valueOf(kullaniciId)));
		try {
			BireyselKullanici bireyselKullanici = (BireyselKullanici) kullanici;
			Sertifika sertifika = sertifikaRepository.findSertifikaByProfilAndSertifikaId(bireyselKullanici.getProfil(), sertifikaId);
			if(sertifika == null) {
				throw new IskolikOrtakException(ErrorCode.VALIDATION_BUSINESS_RESOURCE_NOT_FOUND,String.valueOf(sertifikaId));
			}

			sertifikaRepository.delete(sertifika);
			return sertifikaDTOMapper.toSertifikaDTO(sertifika);
		} catch (Exception e) {
			throw new IskolikOrtakException(ErrorCode.VALIDATION_BUSINESS_RESOURCE_NOT_FOUND,String.valueOf(sertifikaId));
		}
	}
}
