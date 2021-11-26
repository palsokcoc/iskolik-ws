package ayu.edu.tr.iskolik.iletisim.application.model.mapper;


import ayu.edu.tr.iskolik.iletisim.application.model.request.AdresRequest;
import ayu.edu.tr.iskolik.iletisim.domain.model.dto.AdresDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AdresRequestMapper {

	AdresDTO toAdresDTO(AdresRequest adresRequest);
}
