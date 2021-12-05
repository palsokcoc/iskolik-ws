package ayu.edu.tr.iskolik.ilan.application.model.mapper;


import ayu.edu.tr.iskolik.ilan.application.model.request.IlanRequest;
import ayu.edu.tr.iskolik.ilan.domain.model.dto.IlanDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IlanRequestMapper {

	IlanDTO toIlanDTO(IlanRequest kategoriRequest);
}
