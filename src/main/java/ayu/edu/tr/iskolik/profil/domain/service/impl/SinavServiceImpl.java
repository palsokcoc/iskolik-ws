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
import ayu.edu.tr.iskolik.profil.domain.model.dto.SinavDTO;
import ayu.edu.tr.iskolik.profil.domain.model.entity.Sinav;
import ayu.edu.tr.iskolik.profil.domain.model.mapper.SinavDTOMapper;
import ayu.edu.tr.iskolik.profil.domain.repository.SinavRepository;
import ayu.edu.tr.iskolik.profil.domain.service.SinavService;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class SinavServiceImpl implements SinavService {

	private final SinavRepository sinavRepository;
	private final SinavDTOMapper sinavDTOMapper;

	private final KullaniciRepository kullaniciRepository;
	private final KullaniciDTOMapper kullaniciDTOMapper;

	public SinavServiceImpl(SinavRepository sinavRepository, SinavDTOMapper sinavDTOMapper, KullaniciRepository kullaniciRepository, KullaniciDTOMapper kullaniciDTOMapper) {
		this.sinavRepository = sinavRepository;
		this.sinavDTOMapper = sinavDTOMapper;
		this.kullaniciRepository = kullaniciRepository;
		this.kullaniciDTOMapper = kullaniciDTOMapper;
	}

	@Override
	public List<SinavDTO> findAllByKullaniciId(Long kullaniciId, Filters filters) {
		filters.addFilter(new Filter("profil.kullaniciId=" + kullaniciId));
		BaseSpecification<Sinav> specification = new BaseSpecification<>(filters);
		return sinavDTOMapper.toSinavDTOList(sinavRepository.findAll(specification));
	}

	@Override
	public SinavDTO findSinavByProfilAndSinavId(Long kullaniciId, Long sinavId) {
		Kullanici kullanici = kullaniciRepository.findById(kullaniciId).orElseThrow(() -> new IskolikOrtakException(ErrorCode.VALIDATION_BUSINESS_RESOURCE_NOT_FOUND, String.valueOf(kullaniciId)));
		try {
			BireyselKullanici bireyselKullanici = (BireyselKullanici) kullanici;
			Sinav sinav = sinavRepository.findSinavByProfilAndSinavId(bireyselKullanici.getProfil(), sinavId);
			if(sinav == null) {
				throw new IskolikOrtakException(ErrorCode.VALIDATION_BUSINESS_RESOURCE_NOT_FOUND,String.valueOf(sinavId));
			}
			return sinavDTOMapper.toSinavDTO(sinav);
		} catch (Exception e) {
			throw new IskolikOrtakException(ErrorCode.VALIDATION_BUSINESS_RESOURCE_NOT_FOUND,String.valueOf(sinavId));
		}
	}

	@Override
	public SinavDTO updateSinav(Long kullaniciId, Long sinavId, SinavDTO sinavDTO) {
		Kullanici kullanici = kullaniciRepository.findById(kullaniciId).orElseThrow(() -> new IskolikOrtakException(ErrorCode.VALIDATION_BUSINESS_RESOURCE_NOT_FOUND, String.valueOf(kullaniciId)));
		try {
			BireyselKullanici bireyselKullanici = (BireyselKullanici) kullanici;
			Sinav sinav = sinavRepository.findSinavByProfilAndSinavId(bireyselKullanici.getProfil(), sinavId);
			if(sinav == null) {
				throw new IskolikOrtakException(ErrorCode.VALIDATION_BUSINESS_RESOURCE_NOT_FOUND,String.valueOf(sinavId));
			}

			sinav.setSinavAdi(sinavDTO.getSinavAdi());
			sinav.setSinavSonucu(sinavDTO.getSinavSonucu());
			sinavRepository.save(sinav);

			return sinavDTOMapper.toSinavDTO(sinav);
		} catch (Exception e) {
			throw new IskolikOrtakException(ErrorCode.VALIDATION_BUSINESS_RESOURCE_NOT_FOUND,String.valueOf(sinavId));
		}
	}

	@Override
	public SinavDTO deleteSinav(Long kullaniciId, Long sinavId) {
		Kullanici kullanici = kullaniciRepository.findById(kullaniciId).orElseThrow(() -> new IskolikOrtakException(ErrorCode.VALIDATION_BUSINESS_RESOURCE_NOT_FOUND, String.valueOf(kullaniciId)));
		try {
			BireyselKullanici bireyselKullanici = (BireyselKullanici) kullanici;
			Sinav sinav = sinavRepository.findSinavByProfilAndSinavId(bireyselKullanici.getProfil(), sinavId);
			if(sinav == null) {
				throw new IskolikOrtakException(ErrorCode.VALIDATION_BUSINESS_RESOURCE_NOT_FOUND,String.valueOf(sinavId));
			}

			sinavRepository.delete(sinav);

			return sinavDTOMapper.toSinavDTO(sinav);
		} catch (Exception e) {
			throw new IskolikOrtakException(ErrorCode.VALIDATION_BUSINESS_RESOURCE_NOT_FOUND,String.valueOf(sinavId));
		}
	}
}
