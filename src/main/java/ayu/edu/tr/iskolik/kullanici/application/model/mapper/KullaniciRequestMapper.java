package ayu.edu.tr.iskolik.kullanici.application.model.mapper;

import ayu.edu.tr.iskolik.common.domain.exception.ErrorCode;
import ayu.edu.tr.iskolik.common.domain.exception.IskolikOrtakException;
import ayu.edu.tr.iskolik.kullanici.application.model.request.BireyselKullaniciRequest;
import ayu.edu.tr.iskolik.kullanici.application.model.request.KullaniciRequest;
import ayu.edu.tr.iskolik.kullanici.application.model.request.KurumsalKullaniciRequest;
import ayu.edu.tr.iskolik.kullanici.domain.model.dto.BireyselKullaniciDTO;
import ayu.edu.tr.iskolik.kullanici.domain.model.dto.KullaniciDTO;
import ayu.edu.tr.iskolik.kullanici.domain.model.dto.KurumsalKullaniciDTO;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface KullaniciRequestMapper {

	// Request --> DTO
	default KullaniciDTO toKullaniciDTO(KullaniciRequest kullaniciRequest) {
		if(kullaniciRequest == null) {
			return null;
		}

		if(kullaniciRequest.getTip().equals("Bireysel")) {
			return toBireyselKullaniciDTO((BireyselKullaniciRequest) kullaniciRequest);
		} else if(kullaniciRequest.getTip().equals("Kurumsal")) {
			return toKurumsalKullaniciDTO((KurumsalKullaniciRequest) kullaniciRequest);
		} else {
			throw new IskolikOrtakException(ErrorCode.VALIDATION_BUSINESS_PARAMETER_INVALID, kullaniciRequest.getTip());
		}
	}

	BireyselKullaniciDTO toBireyselKullaniciDTO(BireyselKullaniciRequest bireyselKullaniciRequest);
	KurumsalKullaniciDTO toKurumsalKullaniciDTO(KurumsalKullaniciRequest kurumsalKullaniciRequest);
	List<KullaniciDTO> toKullaniciDTOList(List<KullaniciRequest> kullaniciRequestList);

}
