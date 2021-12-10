package ayu.edu.tr.iskolik.profil.domain.service;

import ayu.edu.tr.iskolik.profil.domain.model.dto.YetenekDTO;

public interface YetenekService {

	YetenekDTO findYetenekByProfilAndYetenekId(Long kullaniciId, Long yetenekId);
	YetenekDTO updateYetenek(Long kullaniciId, Long yetenekId, YetenekDTO yetenekDTO);
}
