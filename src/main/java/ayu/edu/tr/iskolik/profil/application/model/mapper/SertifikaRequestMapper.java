package ayu.edu.tr.iskolik.profil.application.model.mapper;

import ayu.edu.tr.iskolik.profil.application.model.request.ProfilRequest;
import ayu.edu.tr.iskolik.profil.application.model.request.SertifikaRequest;
import ayu.edu.tr.iskolik.profil.domain.model.dto.ProfilDTO;
import ayu.edu.tr.iskolik.profil.domain.model.dto.SertifikaDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SertifikaRequestMapper {

	SertifikaDTO toSertifikaDTO(SertifikaRequest sertifikaRequest);
}
