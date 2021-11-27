package ayu.edu.tr.iskolik.iletisim.application.model.mapper;


import ayu.edu.tr.iskolik.iletisim.application.model.request.IletisimRequest;
import ayu.edu.tr.iskolik.iletisim.domain.model.dto.IletisimDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IletisimRequestMapper {

	IletisimDTO toIletisimDTO(IletisimRequest iletisimRequest);
}
