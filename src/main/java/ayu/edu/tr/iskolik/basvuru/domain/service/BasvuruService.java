package ayu.edu.tr.iskolik.basvuru.domain.service;

import ayu.edu.tr.iskolik.basvuru.domain.model.dto.BasvuruDTO;
import ayu.edu.tr.iskolik.common.domain.repository.filter.Filters;
import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface BasvuruService {

	List<BasvuruDTO> findAllByIlanId(Long ilanId, Filters filters, Pageable pageable);
	List<BasvuruDTO> findAllByKullaniciId(Long kullaniciId, Filters filters, Pageable pageable);

	BasvuruDTO findBasvuruById(Long id);

	List<BasvuruDTO> findAll(Specification specification, Pageable pageable);

	BasvuruDTO saveBasvuru(BasvuruDTO basvuruDTO);

	BasvuruDTO updateBasvuru(Long id, BasvuruDTO basvuruDTO);

	BasvuruDTO deleteBasvuruById(Long id);

	BasvuruDTO patchBasvuruById(Long id, Map<String, String> values);
}