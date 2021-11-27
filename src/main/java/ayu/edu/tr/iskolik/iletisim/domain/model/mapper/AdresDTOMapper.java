package ayu.edu.tr.iskolik.iletisim.domain.model.mapper;

import ayu.edu.tr.iskolik.iletisim.domain.model.dto.AdresDTO;
import ayu.edu.tr.iskolik.iletisim.domain.model.entity.Adres;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AdresDTOMapper {

	AdresDTO toAdresDTO(Adres adres);
	Adres toAdres(AdresDTO adresDTO);
	List<AdresDTO> toAdresDTOList(List<Adres> adresList);
}

