package ayu.edu.tr.iskolik.profil.domain.model.mapper;

import ayu.edu.tr.iskolik.profil.domain.model.dto.ProfilDTO;
import ayu.edu.tr.iskolik.profil.domain.model.dto.YetenekDTO;
import ayu.edu.tr.iskolik.profil.domain.model.entity.Profil;
import ayu.edu.tr.iskolik.profil.domain.model.entity.Yetenek;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface YetenekDTOMapper {

	YetenekDTO toYetenekDTO(Yetenek yetenek);
	Yetenek toYetenek(YetenekDTO yetenekDTO);
	List<YetenekDTO> toYetenekDTOList(List<Yetenek> yetenekList);

	@Mapping(target = "yetenekler", ignore = true)
	ProfilDTO toProfilDTO(Profil profil);
}

