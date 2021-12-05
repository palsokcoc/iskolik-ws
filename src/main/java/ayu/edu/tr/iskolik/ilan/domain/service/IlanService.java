package ayu.edu.tr.iskolik.ilan.domain.service;

import ayu.edu.tr.iskolik.ilan.domain.model.dto.IlanDTO;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface IlanService {

	IlanDTO findIlanById(Long id);

	List<IlanDTO> findAll(Specification specification, Pageable pageable);

	IlanDTO saveIlan(IlanDTO ilanDTO);
	IlanDTO updateIlan(Long id, IlanDTO ilanDTO);
	IlanDTO deleteIlanById(Long id);
}
