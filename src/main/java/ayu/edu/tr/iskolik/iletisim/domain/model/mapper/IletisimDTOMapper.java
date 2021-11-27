package ayu.edu.tr.iskolik.iletisim.domain.model.mapper;

import ayu.edu.tr.iskolik.iletisim.domain.model.dto.IletisimDTO;
import ayu.edu.tr.iskolik.iletisim.domain.model.entity.Iletisim;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IletisimDTOMapper {

	IletisimDTO toIletisimDTO(Iletisim iletisim);
	Iletisim toIletisim(IletisimDTO iletisimDTO);
	List<IletisimDTO> toIletisimDTOList(List<Iletisim> iletisimList);
}

