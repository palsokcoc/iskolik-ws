package ayu.edu.tr.iskolik.profil.domain.model.mapper;

import ayu.edu.tr.iskolik.profil.domain.model.dto.ProfilDTO;
import ayu.edu.tr.iskolik.profil.domain.model.dto.SinavDTO;
import ayu.edu.tr.iskolik.profil.domain.model.entity.Profil;
import ayu.edu.tr.iskolik.profil.domain.model.entity.Sinav;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SinavDTOMapper {

	SinavDTO toSinavDTO(Sinav sinav);
	Sinav toSinav(SinavDTO sinavDTO);
	List<SinavDTO> toSinavDTOList(List<Sinav> sinavList);

	@Mapping(target = "sinavlar", ignore = true)
	ProfilDTO toProfilDTO(Profil profil);
}

