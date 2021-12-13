package ayu.edu.tr.iskolik.profil.domain.model.mapper;

import ayu.edu.tr.iskolik.profil.domain.model.dto.ProfilDTO;
import ayu.edu.tr.iskolik.profil.domain.model.dto.SertifikaDTO;
import ayu.edu.tr.iskolik.profil.domain.model.entity.Profil;
import ayu.edu.tr.iskolik.profil.domain.model.entity.Sertifika;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SertifikaDTOMapper {

	SertifikaDTO toSertifikaDTO(Sertifika sertifika);
	Sertifika toSertifika(SertifikaDTO sertifikaDTO);
	List<SertifikaDTO> toSertifikaDTOList(List<Sertifika> sertifikaList);

	@Mapping(target = "sertifikalar", ignore = true)
	ProfilDTO toProfilDTO(Profil profil);
}

