package ayu.edu.tr.iskolik.kategori.domain.service;

import ayu.edu.tr.iskolik.kategori.domain.model.dto.KategoriDTO;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface KategoriService {

	KategoriDTO findKategoriById(Long id);

	List<KategoriDTO> findAll(Specification specification, Pageable pageable);

	KategoriDTO saveKategori(KategoriDTO kategoriDTO);
	KategoriDTO updateKategori(Long id, KategoriDTO kategoriDTO);
	KategoriDTO deleteKategoriById(Long id);
}
