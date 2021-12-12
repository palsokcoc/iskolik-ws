package ayu.edu.tr.iskolik.profil.domain.service;

import ayu.edu.tr.iskolik.common.domain.repository.filter.Filters;
import ayu.edu.tr.iskolik.profil.domain.model.dto.SertifikaDTO;
import java.util.List;

public interface SertifikaService {

	List<SertifikaDTO> findAllByKullaniciId(Long kullaniciId, Filters filters);
	SertifikaDTO findSertifikaByProfilAndSertifikaId(Long kullaniciId, Long sertifikaId);
	SertifikaDTO updateSertifika(Long kullaniciId, Long sertifikaId, SertifikaDTO sertifikaDTO);
	SertifikaDTO deleteSertifika(Long kullaniciId, Long sertifikaId);
}
