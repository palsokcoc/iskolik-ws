package ayu.edu.tr.iskolik.basvuru.domain.model.mapper;

import ayu.edu.tr.iskolik.basvuru.domain.model.dto.BasvuruDTO;
import ayu.edu.tr.iskolik.basvuru.domain.model.entity.Basvuru;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BasvuruDTOMapper {

	BasvuruDTO toBasvuruDTO(Basvuru basvuru);
	Basvuru toBasvuru(BasvuruDTO basvuruDTO);
	List<BasvuruDTO> toBasvuruDTOList(List<Basvuru> basvuruList);
}

