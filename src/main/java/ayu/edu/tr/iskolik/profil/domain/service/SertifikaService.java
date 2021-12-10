package ayu.edu.tr.iskolik.profil.domain.service;

import ayu.edu.tr.iskolik.profil.domain.model.dto.SertifikaDTO;

public interface SertifikaService {

	SertifikaDTO findSertifikaByProfilAndSertifikaId(Long kullaniciId, Long sertifikaId);
	SertifikaDTO updateSertifika(Long kullaniciId, Long sertifikaId, SertifikaDTO sertifikaDTO);
}
