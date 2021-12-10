package ayu.edu.tr.iskolik.profil.domain.service;

import ayu.edu.tr.iskolik.profil.domain.model.dto.SinavDTO;

public interface SinavService {

	SinavDTO findSinavByProfilAndSinavId(Long kullaniciId, Long sinavId);
	SinavDTO updateSinav(Long kullaniciId, Long sinavId, SinavDTO sinavDTO);
}
