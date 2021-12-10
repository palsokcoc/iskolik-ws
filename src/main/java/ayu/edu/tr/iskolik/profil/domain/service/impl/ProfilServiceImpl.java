package ayu.edu.tr.iskolik.profil.domain.service.impl;

import ayu.edu.tr.iskolik.common.domain.exception.ErrorCode;
import ayu.edu.tr.iskolik.common.domain.exception.IskolikOrtakException;
import ayu.edu.tr.iskolik.profil.domain.model.dto.ProfilDTO;
import ayu.edu.tr.iskolik.profil.domain.model.entity.Profil;
import ayu.edu.tr.iskolik.profil.domain.model.mapper.ProfilDTOMapper;
import ayu.edu.tr.iskolik.profil.domain.model.mapper.SertifikaDTOMapper;
import ayu.edu.tr.iskolik.profil.domain.model.mapper.SinavDTOMapper;
import ayu.edu.tr.iskolik.profil.domain.model.mapper.YetenekDTOMapper;
import ayu.edu.tr.iskolik.profil.domain.repository.ProfilRepository;
import ayu.edu.tr.iskolik.profil.domain.repository.SertifikaRepository;
import ayu.edu.tr.iskolik.profil.domain.repository.SinavRepository;
import ayu.edu.tr.iskolik.profil.domain.repository.YetenekRepository;
import ayu.edu.tr.iskolik.profil.domain.service.ProfilService;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ProfilServiceImpl implements ProfilService {

	private final ProfilRepository profilRepository;
	private final ProfilDTOMapper profilDTOMapper;

	private final SertifikaRepository sertifikaRepository;
	private final SertifikaDTOMapper sertifikaDTOMapper;

	private final SinavRepository sinavRepository;
	private final SinavDTOMapper sinavDTOMapper;

	private final YetenekRepository yetenekRepository;
	private final YetenekDTOMapper yetenekDTOMapper;

	public ProfilServiceImpl(ProfilRepository profilRepository, ProfilDTOMapper profilDTOMapper, SertifikaRepository sertifikaRepository, SertifikaDTOMapper sertifikaDTOMapper, SinavRepository sinavRepository, SinavDTOMapper sinavDTOMapper, YetenekRepository yetenekRepository, YetenekDTOMapper yetenekDTOMapper) {
		this.profilRepository = profilRepository;
		this.profilDTOMapper = profilDTOMapper;
		this.sertifikaRepository = sertifikaRepository;
		this.sertifikaDTOMapper = sertifikaDTOMapper;
		this.sinavRepository = sinavRepository;
		this.sinavDTOMapper = sinavDTOMapper;
		this.yetenekRepository = yetenekRepository;
		this.yetenekDTOMapper = yetenekDTOMapper;
	}

	@Override
	public ProfilDTO findProfilById(Long id) {
		Optional<Profil> profil = profilRepository.findById(id);

		ProfilDTO ProfilDTO;
		if (profil.isPresent()) {
			ProfilDTO = profilDTOMapper.toProfilDTO(profil.orElse(null));
		} else {
			throw new IskolikOrtakException(ErrorCode.VALIDATION_BUSINESS_RESOURCE_NOT_FOUND, String.valueOf(id));
		}

		return ProfilDTO;
	}

	@Override
	public List<ProfilDTO> findAll(Specification specification, Pageable pageable) {
		return profilDTOMapper.toProfilDTOList(profilRepository.findAll(specification, pageable).toList());
	}

	@Override
	public ProfilDTO saveProfil(ProfilDTO profilDTO) {
		if (profilDTO.getKullaniciId() != null) {
			throw new IskolikOrtakException(ErrorCode.VALIDATION_BUSINESS_FIELD_NOT_NULL, "id");
		}

		Profil profil = profilDTOMapper.toProfil(profilDTO);

		Profil savedProfil = profilRepository.save(profil);
		return profilDTOMapper.toProfilDTO(savedProfil);
	}

	@Override
	public ProfilDTO updateProfil(Long id, ProfilDTO profilDTO) {
		if (profilRepository.findById(id).isEmpty()) {
			throw new IskolikOrtakException(ErrorCode.VALIDATION_BUSINESS_RESOURCE_NOT_FOUND, String.valueOf(id));
		}

		profilDTO.setKullaniciId(id);
		Profil profil = profilDTOMapper.toProfil(profilDTO);

		profilRepository.save(profil);

		return profilDTO;
	}

	@Override
	public ProfilDTO deleteProfilById(Long id) {
		Optional<Profil> profil = profilRepository.findById(id);
		if (profil.isEmpty()) {
			throw new IskolikOrtakException(ErrorCode.VALIDATION_BUSINESS_RESOURCE_NOT_FOUND, String.valueOf(id));
		}

		ProfilDTO ProfilDTO = profilDTOMapper.toProfilDTO(profil.get());
		profilRepository.deleteById(id);
		return ProfilDTO;
	}
}
