package ayu.edu.tr.iskolik.profil.domain.model.mapper;

import ayu.edu.tr.iskolik.profil.domain.model.dto.SertifikaDTO;
import ayu.edu.tr.iskolik.profil.domain.model.entity.Sertifika;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SertifikaDTOMapper {

	SertifikaDTO toSertifikaDTO(Sertifika sertifika);
	Sertifika toSertifika(SertifikaDTO sertifikaDTO);
	List<SertifikaDTO> toSertifikaDTOList(List<Sertifika> sertifikaList);
}

