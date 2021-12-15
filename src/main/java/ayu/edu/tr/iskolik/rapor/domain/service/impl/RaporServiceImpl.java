package ayu.edu.tr.iskolik.rapor.domain.service.impl;

import ayu.edu.tr.iskolik.common.domain.exception.ErrorCode;
import ayu.edu.tr.iskolik.common.domain.exception.IskolikOrtakException;
import ayu.edu.tr.iskolik.ilan.domain.model.dto.IlanDTO;
import ayu.edu.tr.iskolik.ilan.domain.model.entity.Ilan;
import ayu.edu.tr.iskolik.ilan.domain.model.entity.Ilan.Durum;
import ayu.edu.tr.iskolik.ilan.domain.model.mapper.IlanDTOMapper;
import ayu.edu.tr.iskolik.ilan.domain.repository.IlanRepository;
import ayu.edu.tr.iskolik.kullanici.domain.model.dto.KullaniciDTO;
import ayu.edu.tr.iskolik.kullanici.domain.model.entity.KurumsalKullanici;
import ayu.edu.tr.iskolik.kullanici.domain.model.mapper.KullaniciDTOMapper;
import ayu.edu.tr.iskolik.kullanici.domain.service.KullaniciService;
import ayu.edu.tr.iskolik.rapor.domain.model.dto.EnCokArananOzelliklerRaporuDTO;
import ayu.edu.tr.iskolik.rapor.domain.model.dto.EnCokBasvuruYapilanIlanlarDTO;
import ayu.edu.tr.iskolik.rapor.domain.repository.RaporRepository;
import ayu.edu.tr.iskolik.rapor.domain.service.RaporService;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class RaporServiceImpl implements RaporService {

	private final RaporRepository raporRepository;

	public RaporServiceImpl(RaporRepository raporRepository) {
		this.raporRepository = raporRepository;
	}

	@Override
	public List<EnCokArananOzelliklerRaporuDTO> findEnCokArananOzellikler(LocalDate ilkTarih, LocalDate sonTarih, Pageable pageable) {
		ilkTarih = (ilkTarih != null ? ilkTarih : LocalDate.EPOCH);
		sonTarih = (sonTarih != null ? sonTarih : LocalDate.now());
		return raporRepository.findEnCokArananOzellikler(ilkTarih, sonTarih, pageable);
	}

	@Override
	public List<EnCokBasvuruYapilanIlanlarDTO> findEnCokBasvuruYapilanIlanlar(LocalDate ilkTarih, LocalDate sonTarih, Pageable pageable) {
		ilkTarih = (ilkTarih != null ? ilkTarih : LocalDate.EPOCH);
		sonTarih = (sonTarih != null ? sonTarih : LocalDate.now());
		return raporRepository.findEnCokBasvuruYapilanIlanlar(ilkTarih, sonTarih, pageable);
	}
}
