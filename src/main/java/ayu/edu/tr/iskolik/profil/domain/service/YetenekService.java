package ayu.edu.tr.iskolik.profil.domain.service;

import ayu.edu.tr.iskolik.common.domain.repository.filter.Filters;
import ayu.edu.tr.iskolik.profil.domain.model.dto.YetenekDTO;
import java.util.List;

public interface YetenekService {

	List<YetenekDTO> findAllByKullaniciId(Long kullaniciId, Filters filters);
	YetenekDTO findYetenekByProfilAndYetenekId(Long kullaniciId, Long yetenekId);
	YetenekDTO updateYetenek(Long kullaniciId, Long yetenekId, YetenekDTO yetenekDTO);
	YetenekDTO deleteYetenek(Long kullaniciId, Long yetenekId);
}
