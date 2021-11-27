package ayu.edu.tr.iskolik.iletisim.domain.service;

import ayu.edu.tr.iskolik.iletisim.domain.model.dto.IletisimDTO;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface IletisimService {

	IletisimDTO findIletisimById(Long id);

	List<IletisimDTO> findAll(Specification specification, Pageable pageable);

	IletisimDTO saveIletisim(IletisimDTO iletisimDTO);
	IletisimDTO updateIletisim(Long id, IletisimDTO iletisimDTO);
	IletisimDTO deleteIletisimById(Long id);
}
