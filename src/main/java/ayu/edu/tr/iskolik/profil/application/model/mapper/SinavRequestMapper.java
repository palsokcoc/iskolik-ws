package ayu.edu.tr.iskolik.profil.application.model.mapper;

import ayu.edu.tr.iskolik.profil.application.model.request.SinavRequest;
import ayu.edu.tr.iskolik.profil.domain.model.dto.SinavDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SinavRequestMapper {

	SinavDTO toSinavDTO(SinavRequest sinavRequest);
}
