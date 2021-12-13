package ayu.edu.tr.iskolik.basvuru.application.model.mapper;

import ayu.edu.tr.iskolik.basvuru.application.model.request.BasvuruRequest;
import ayu.edu.tr.iskolik.basvuru.domain.model.dto.BasvuruDTO;
import ayu.edu.tr.iskolik.profil.application.model.mapper.ProfilRequestMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BasvuruRequestMapper {

	BasvuruDTO toBasvuruDTO(BasvuruRequest basvuruRequest);
}
