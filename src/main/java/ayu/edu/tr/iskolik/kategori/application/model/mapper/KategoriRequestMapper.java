package ayu.edu.tr.iskolik.kategori.application.model.mapper;


import ayu.edu.tr.iskolik.kategori.application.model.request.KategoriRequest;
import ayu.edu.tr.iskolik.kategori.domain.model.dto.KategoriDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface KategoriRequestMapper {

	KategoriDTO toKategoriDTO(KategoriRequest kategoriRequest);
}
