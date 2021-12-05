package ayu.edu.tr.iskolik.ilan.domain.model.mapper;

import ayu.edu.tr.iskolik.ilan.domain.model.dto.IlanDTO;
import ayu.edu.tr.iskolik.ilan.domain.model.entity.Ilan;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IlanDTOMapper {

	IlanDTO toIlanDTO(Ilan ilan);
	Ilan toIlan(IlanDTO ilanDTO);
	List<IlanDTO> toIlanDTOList(List<Ilan> ilanList);
}

