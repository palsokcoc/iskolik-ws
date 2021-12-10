package ayu.edu.tr.iskolik.profil.application.model.mapper;

import ayu.edu.tr.iskolik.profil.application.model.request.YetenekRequest;
import ayu.edu.tr.iskolik.profil.domain.model.dto.YetenekDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface YetenekRequestMapper {

	YetenekDTO toYetenekDTO(YetenekRequest yetenekRequest);
}
