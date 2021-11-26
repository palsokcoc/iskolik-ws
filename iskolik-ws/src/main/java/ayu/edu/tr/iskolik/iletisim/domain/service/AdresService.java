package ayu.edu.tr.iskolik.iletisim.domain.service;

import ayu.edu.tr.iskolik.iletisim.domain.model.dto.AdresDTO;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface AdresService {

	AdresDTO findAdresById(Long id);

	List<AdresDTO> findAll(Specification specification, Pageable pageable);

	AdresDTO saveAdres(AdresDTO AdresDTO);
	AdresDTO updateAdres(Long id, AdresDTO AdresDTO);
	AdresDTO deleteAdresById(Long id);
}
