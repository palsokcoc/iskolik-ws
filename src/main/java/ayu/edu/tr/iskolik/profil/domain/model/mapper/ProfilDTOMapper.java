package ayu.edu.tr.iskolik.profil.domain.model.mapper;

import ayu.edu.tr.iskolik.profil.domain.model.dto.ProfilDTO;
import ayu.edu.tr.iskolik.profil.domain.model.entity.Profil;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProfilDTOMapper {

	ProfilDTO toProfilDTO(Profil profil);
	Profil toProfil(ProfilDTO profilDTO);
	List<ProfilDTO> toProfilDTOList(List<Profil> profilList);
}

