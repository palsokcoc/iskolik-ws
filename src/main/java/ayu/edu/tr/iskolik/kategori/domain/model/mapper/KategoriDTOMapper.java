package ayu.edu.tr.iskolik.kategori.domain.model.mapper;

import ayu.edu.tr.iskolik.kategori.domain.model.dto.KategoriDTO;
import ayu.edu.tr.iskolik.kategori.domain.model.entity.Kategori;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface KategoriDTOMapper {

	KategoriDTO toKategoriDTO(Kategori kategori);
	Kategori toKategori(KategoriDTO kategoriDTO);
	List<KategoriDTO> toKategoriDTOList(List<Kategori> kategoriList);
}

