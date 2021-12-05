package ayu.edu.tr.iskolik.kategori.domain.service.impl;

import ayu.edu.tr.iskolik.common.domain.exception.ErrorCode;
import ayu.edu.tr.iskolik.common.domain.exception.IskolikOrtakException;
import ayu.edu.tr.iskolik.kategori.domain.model.dto.KategoriDTO;
import ayu.edu.tr.iskolik.kategori.domain.model.entity.Kategori;
import ayu.edu.tr.iskolik.kategori.domain.model.mapper.KategoriDTOMapper;
import ayu.edu.tr.iskolik.kategori.domain.repository.KategoriRepository;
import ayu.edu.tr.iskolik.kategori.domain.service.KategoriService;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class KategoriServiceImpl implements KategoriService {

	private final KategoriRepository kategoriRepository;
	private final KategoriDTOMapper kategoriDTOMapper;

	public KategoriServiceImpl(KategoriRepository kategoriRepository, KategoriDTOMapper kategoriDTOMapper) {
		this.kategoriRepository = kategoriRepository;
		this.kategoriDTOMapper = kategoriDTOMapper;
	}

	@Override
	public KategoriDTO findKategoriById(Long id) {
		Optional<Kategori> Kategori = kategoriRepository.findById(id);

		KategoriDTO KategoriDTO;
		if (Kategori.isPresent()) {
			KategoriDTO = kategoriDTOMapper.toKategoriDTO(Kategori.orElse(null));
		} else {
			throw new IskolikOrtakException(ErrorCode.VALIDATION_BUSINESS_RESOURCE_NOT_FOUND, String.valueOf(id));
		}

		return KategoriDTO;
	}

	@Override
	public List<KategoriDTO> findAll(Specification specification, Pageable pageable) {
		return kategoriDTOMapper.toKategoriDTOList(kategoriRepository.findAll(specification, pageable).toList());
	}

	@Override
	public KategoriDTO saveKategori(KategoriDTO kategoriDTO) {
		if (kategoriDTO.getKategoriId() != null) {
			throw new IskolikOrtakException(ErrorCode.VALIDATION_BUSINESS_FIELD_NOT_NULL, "id");
		}

		Kategori kategori = kategoriDTOMapper.toKategori(kategoriDTO);

		if(kategori.getAtaKategori() != null && kategori.getAtaKategori().getKategoriId() != null && kategori.getAtaKategori().getKategoriId() != 0) {
			KategoriDTO ataKategoriDTO = this.findKategoriById(kategori.getAtaKategori().getKategoriId());
			if (kategoriDTO.getKategoriId() == ataKategoriDTO.getKategoriId()) {
				throw new IskolikOrtakException(ErrorCode.SYSTEM_ERROR, "...");
			}

			if(cyclicDependencyVarMi(kategoriDTO, ataKategoriDTO)) {
				throw new IskolikOrtakException(ErrorCode.CUSTOM_ERROR, "Kategoriler arasında sonsuz döngü oluşamaz");
			}

			if (ataKategoriDTO!=null) {
				kategori.setAtaKategori(kategoriDTOMapper.toKategori(ataKategoriDTO));
			}
		} else {
			kategori.setAtaKategori(null);
		}
		ayu.edu.tr.iskolik.kategori.domain.model.entity.Kategori savedKategori = kategoriRepository.save(kategori);
		return kategoriDTOMapper.toKategoriDTO(savedKategori);
	}

	@Override
	public KategoriDTO updateKategori(Long id, KategoriDTO kategoriDTO) {
		if (kategoriDTO.getKategoriId() == null) {
			throw new IskolikOrtakException(ErrorCode.VALIDATION_BUSINESS_FIELD_NULL, "id");
		}

		if (kategoriRepository.findById(id).isEmpty()) {
			throw new IskolikOrtakException(ErrorCode.VALIDATION_BUSINESS_RESOURCE_NOT_FOUND, String.valueOf(id));
		}

		kategoriDTO.setKategoriId(id);
		Kategori kategori = kategoriDTOMapper.toKategori(kategoriDTO);

		if(kategori.getAtaKategori() != null && kategori.getAtaKategori().getKategoriId() != null && kategori.getAtaKategori().getKategoriId() != 0) {
			KategoriDTO ataKategoriDTO = this.findKategoriById(kategori.getAtaKategori().getKategoriId());
			if (kategoriDTO.getKategoriId() == ataKategoriDTO.getKategoriId()) {
				throw new IskolikOrtakException(ErrorCode.SYSTEM_ERROR, "...");
			}

			if(cyclicDependencyVarMi(kategoriDTO, ataKategoriDTO)) {
				throw new IskolikOrtakException(ErrorCode.CUSTOM_ERROR, "Kategoriler arasında sonsuz döngü oluşamaz");
			}

			if (ataKategoriDTO != null) {
				kategori.setAtaKategori(kategoriDTOMapper.toKategori(ataKategoriDTO));
			}
		} else {
			kategori.setAtaKategori(null);
		}

		kategoriRepository.save(kategori);

		return kategoriDTO;
	}

	@Override
	public KategoriDTO deleteKategoriById(Long id) {
		Optional<Kategori> Kategori = kategoriRepository.findById(id);
		if (Kategori.isEmpty()) {
			throw new IskolikOrtakException(ErrorCode.VALIDATION_BUSINESS_RESOURCE_NOT_FOUND, String.valueOf(id));
		}

		KategoriDTO KategoriDTO = kategoriDTOMapper.toKategoriDTO(Kategori.get());
		kategoriRepository.deleteById(id);
		return KategoriDTO;
	}

	private boolean cyclicDependencyVarMi(KategoriDTO kategoriDTO, KategoriDTO ataKategoriDTO) {
		if(kategoriDTO.getKategoriId() == ataKategoriDTO.getKategoriId()) {
			return true;
		} else {
			while (ataKategoriDTO.getAtaKategori() != null) {
				ataKategoriDTO = ataKategoriDTO.getAtaKategori();
				if(kategoriDTO.getKategoriId() == ataKategoriDTO.getKategoriId()) {
					return true;
				}
			}
		}

		return false;
	}
}
