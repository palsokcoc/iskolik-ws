package ayu.edu.tr.iskolik.profil.domain.model.mapper;

import ayu.edu.tr.iskolik.profil.domain.model.dto.SinavDTO;
import ayu.edu.tr.iskolik.profil.domain.model.entity.Sinav;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SinavDTOMapper {

	SinavDTO toSinavDTO(Sinav sinav);
	Sinav toSinav(SinavDTO sinavDTO);
	List<SinavDTO> toSinavDTOList(List<Sinav> sinavList);
}

