package ayu.edu.tr.iskolik.profil.domain.model.mapper;

import ayu.edu.tr.iskolik.profil.domain.model.dto.ProfilDTO;
import ayu.edu.tr.iskolik.profil.domain.model.dto.SertifikaDTO;
import ayu.edu.tr.iskolik.profil.domain.model.dto.SinavDTO;
import ayu.edu.tr.iskolik.profil.domain.model.dto.YetenekDTO;
import ayu.edu.tr.iskolik.profil.domain.model.entity.Profil;
import ayu.edu.tr.iskolik.profil.domain.model.entity.Sertifika;
import ayu.edu.tr.iskolik.profil.domain.model.entity.Sinav;
import ayu.edu.tr.iskolik.profil.domain.model.entity.Yetenek;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProfilDTOMapper {

	ProfilDTO toProfilDTO(Profil profil);
	Profil toProfil(ProfilDTO profilDTO);
	List<ProfilDTO> toProfilDTOList(List<Profil> profilList);

	@Mapping(target = "profil", ignore = true)
	SertifikaDTO toSertifikaDTO(Sertifika sertifika);

	@Mapping(target = "profil", ignore = true)
	SinavDTO toSinavDTO(Sinav sinav);

	@Mapping(target = "profil", ignore = true)
	YetenekDTO toYetenekDTO(Yetenek yetenek);
}

