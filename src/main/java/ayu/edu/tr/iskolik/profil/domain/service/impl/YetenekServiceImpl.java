package ayu.edu.tr.iskolik.profil.domain.service.impl;

import ayu.edu.tr.iskolik.common.domain.exception.ErrorCode;
import ayu.edu.tr.iskolik.common.domain.exception.IskolikOrtakException;
import ayu.edu.tr.iskolik.common.domain.repository.BaseSpecification;
import ayu.edu.tr.iskolik.common.domain.repository.filter.Filter;
import ayu.edu.tr.iskolik.common.domain.repository.filter.Filters;
import ayu.edu.tr.iskolik.kategori.domain.model.entity.Kategori;
import ayu.edu.tr.iskolik.kategori.domain.model.mapper.KategoriDTOMapper;
import ayu.edu.tr.iskolik.kategori.domain.repository.KategoriRepository;
import ayu.edu.tr.iskolik.kullanici.domain.model.entity.BireyselKullanici;
import ayu.edu.tr.iskolik.kullanici.domain.model.entity.Kullanici;
import ayu.edu.tr.iskolik.kullanici.domain.model.mapper.KullaniciDTOMapper;
import ayu.edu.tr.iskolik.kullanici.domain.repository.KullaniciRepository;
import ayu.edu.tr.iskolik.profil.domain.model.dto.SertifikaDTO;
import ayu.edu.tr.iskolik.profil.domain.model.dto.YetenekDTO;
import ayu.edu.tr.iskolik.profil.domain.model.entity.Sertifika;
import ayu.edu.tr.iskolik.profil.domain.model.entity.Yetenek;
import ayu.edu.tr.iskolik.profil.domain.model.mapper.YetenekDTOMapper;
import ayu.edu.tr.iskolik.profil.domain.repository.YetenekRepository;
import ayu.edu.tr.iskolik.profil.domain.service.YetenekService;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class YetenekServiceImpl implements YetenekService {

	private final YetenekRepository yetenekRepository;
	private final YetenekDTOMapper yetenekDTOMapper;

	private final KullaniciRepository kullaniciRepository;
	private final KullaniciDTOMapper kullaniciDTOMapper;

	private final KategoriRepository kategoriRepository;
	private final KategoriDTOMapper kategoriDTOMapper;

	public YetenekServiceImpl(YetenekRepository yetenekRepository, YetenekDTOMapper yetenekDTOMapper, KullaniciRepository kullaniciRepository, KullaniciDTOMapper kullaniciDTOMapper, KategoriRepository kategoriRepository, KategoriDTOMapper kategoriDTOMapper) {
		this.yetenekRepository = yetenekRepository;
		this.yetenekDTOMapper = yetenekDTOMapper;
		this.kullaniciRepository = kullaniciRepository;
		this.kullaniciDTOMapper = kullaniciDTOMapper;
		this.kategoriRepository = kategoriRepository;
		this.kategoriDTOMapper = kategoriDTOMapper;
	}

	@Override
	public List<YetenekDTO> findAllByKullaniciId(Long kullaniciId) {
		Filters filters = new Filters();
		filters.addFilter(new Filter("profil.kullaniciId=" + kullaniciId));
		BaseSpecification<Yetenek> specification = new BaseSpecification<>(filters);
		return yetenekDTOMapper.toYetenekDTOList(yetenekRepository.findAll(specification));
	}

	@Override
	public YetenekDTO findYetenekByProfilAndYetenekId(Long kullaniciId, Long yetenekId) {
		Kullanici kullanici = kullaniciRepository.findById(kullaniciId).orElseThrow(() -> new IskolikOrtakException(ErrorCode.VALIDATION_BUSINESS_RESOURCE_NOT_FOUND, String.valueOf(kullaniciId)));
		try {
			BireyselKullanici bireyselKullanici = (BireyselKullanici) kullanici;
			Yetenek yetenek = yetenekRepository.findYetenekByProfilAndYetenekId(bireyselKullanici.getProfil(), yetenekId);
			if(yetenek == null) {
				throw new IskolikOrtakException(ErrorCode.VALIDATION_BUSINESS_RESOURCE_NOT_FOUND,String.valueOf(yetenekId));
			}
			return yetenekDTOMapper.toYetenekDTO(yetenek);
		} catch (Exception e) {
			throw new IskolikOrtakException(ErrorCode.VALIDATION_BUSINESS_RESOURCE_NOT_FOUND,String.valueOf(yetenekId));
		}
	}

	@Override
	public YetenekDTO updateYetenek(Long kullaniciId, Long yetenekId, YetenekDTO yetenekDTO) {
		Kullanici kullanici = kullaniciRepository.findById(kullaniciId).orElseThrow(() -> new IskolikOrtakException(ErrorCode.VALIDATION_BUSINESS_RESOURCE_NOT_FOUND, String.valueOf(kullaniciId)));
		try {
			BireyselKullanici bireyselKullanici = (BireyselKullanici) kullanici;
			Yetenek yetenek = yetenekRepository.findYetenekByProfilAndYetenekId(bireyselKullanici.getProfil(), yetenekId);
			if(yetenek == null) {
				throw new IskolikOrtakException(ErrorCode.VALIDATION_BUSINESS_RESOURCE_NOT_FOUND,String.valueOf(yetenekId));
			}

			if(yetenek.getKategori().getKategoriId() != yetenekDTO.getKategori().getKategoriId()) {
				Kategori kategori = kategoriRepository.findById(yetenekDTO.getKategori().getKategoriId())
						.orElseThrow( () -> new IskolikOrtakException(ErrorCode.VALIDATION_BUSINESS_RESOURCE_NOT_FOUND, String.valueOf(yetenekDTO.getKategori().getKategoriId())));
				yetenek.setKategori(kategori);
			}

			yetenek.setAciklama(yetenekDTO.getAciklama());
			yetenekRepository.save(yetenek);

			return yetenekDTOMapper.toYetenekDTO(yetenek);
		} catch (Exception e) {
			throw new IskolikOrtakException(ErrorCode.VALIDATION_BUSINESS_RESOURCE_NOT_FOUND,String.valueOf(yetenekId));
		}
	}

	@Override
	public YetenekDTO deleteYetenek(Long kullaniciId, Long yetenekId) {
		Kullanici kullanici = kullaniciRepository.findById(kullaniciId).orElseThrow(() -> new IskolikOrtakException(ErrorCode.VALIDATION_BUSINESS_RESOURCE_NOT_FOUND, String.valueOf(kullaniciId)));
		try {
			BireyselKullanici bireyselKullanici = (BireyselKullanici) kullanici;
			Yetenek yetenek = yetenekRepository.findYetenekByProfilAndYetenekId(bireyselKullanici.getProfil(), yetenekId);
			if(yetenek == null) {
				throw new IskolikOrtakException(ErrorCode.VALIDATION_BUSINESS_RESOURCE_NOT_FOUND,String.valueOf(yetenekId));
			}

			yetenekRepository.delete(yetenek);

			return yetenekDTOMapper.toYetenekDTO(yetenek);
		} catch (Exception e) {
			throw new IskolikOrtakException(ErrorCode.VALIDATION_BUSINESS_RESOURCE_NOT_FOUND,String.valueOf(yetenekId));
		}
	}
}
