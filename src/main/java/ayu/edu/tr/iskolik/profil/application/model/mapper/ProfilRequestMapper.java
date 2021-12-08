package ayu.edu.tr.iskolik.profil.application.model.mapper;

import ayu.edu.tr.iskolik.profil.application.model.request.ProfilRequest;
import ayu.edu.tr.iskolik.profil.domain.model.dto.ProfilDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProfilRequestMapper {

	ProfilDTO toProfilDTO(ProfilRequest profilRequest);
}
