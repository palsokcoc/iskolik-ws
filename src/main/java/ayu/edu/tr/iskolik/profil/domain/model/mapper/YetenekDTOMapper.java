package ayu.edu.tr.iskolik.profil.domain.model.mapper;

import ayu.edu.tr.iskolik.profil.domain.model.dto.YetenekDTO;
import ayu.edu.tr.iskolik.profil.domain.model.entity.Yetenek;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface YetenekDTOMapper {

	YetenekDTO toYetenekDTO(Yetenek yetenek);
	Yetenek toYetenek(YetenekDTO yetenekDTO);
	List<YetenekDTO> toYetenekDTOList(List<Yetenek> yetenekList);
}

