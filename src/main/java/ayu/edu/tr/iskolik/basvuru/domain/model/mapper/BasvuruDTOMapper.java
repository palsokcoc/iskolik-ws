package ayu.edu.tr.iskolik.basvuru.domain.model.mapper;

import ayu.edu.tr.iskolik.basvuru.domain.model.dto.BasvuruDTO;
import ayu.edu.tr.iskolik.basvuru.domain.model.entity.Basvuru;
import ayu.edu.tr.iskolik.profil.domain.model.mapper.ProfilDTOMapper;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {ProfilDTOMapper.class})
public interface BasvuruDTOMapper {

	BasvuruDTO toBasvuruDTO(Basvuru basvuru);
	Basvuru toBasvuru(BasvuruDTO basvuruDTO);
	List<BasvuruDTO> toBasvuruDTOList(List<Basvuru> basvuruList);
}

