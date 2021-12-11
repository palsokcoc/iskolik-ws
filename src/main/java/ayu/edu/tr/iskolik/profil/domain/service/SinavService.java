package ayu.edu.tr.iskolik.profil.domain.service;

import ayu.edu.tr.iskolik.profil.domain.model.dto.SertifikaDTO;
import ayu.edu.tr.iskolik.profil.domain.model.dto.SinavDTO;
import java.util.List;

public interface SinavService {

	List<SinavDTO> findAllByKullaniciId(Long kullaniciId);
	SinavDTO findSinavByProfilAndSinavId(Long kullaniciId, Long sinavId);
	SinavDTO updateSinav(Long kullaniciId, Long sinavId, SinavDTO sinavDTO);
	SinavDTO deleteSinav(Long kullaniciId, Long sinavId);
}
