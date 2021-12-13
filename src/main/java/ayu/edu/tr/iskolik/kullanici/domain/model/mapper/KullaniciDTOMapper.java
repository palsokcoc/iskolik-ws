package ayu.edu.tr.iskolik.kullanici.domain.model.mapper;

import ayu.edu.tr.iskolik.common.domain.exception.ErrorCode;
import ayu.edu.tr.iskolik.common.domain.exception.IskolikOrtakException;
import ayu.edu.tr.iskolik.kullanici.domain.model.dto.BireyselKullaniciDTO;
import ayu.edu.tr.iskolik.kullanici.domain.model.dto.KullaniciDTO;
import ayu.edu.tr.iskolik.kullanici.domain.model.dto.KurumsalKullaniciDTO;
import ayu.edu.tr.iskolik.kullanici.domain.model.entity.BireyselKullanici;
import ayu.edu.tr.iskolik.kullanici.domain.model.entity.Kullanici;
import ayu.edu.tr.iskolik.kullanici.domain.model.entity.KurumsalKullanici;
import ayu.edu.tr.iskolik.profil.domain.model.dto.SertifikaDTO;
import ayu.edu.tr.iskolik.profil.domain.model.dto.SinavDTO;
import ayu.edu.tr.iskolik.profil.domain.model.dto.YetenekDTO;
import ayu.edu.tr.iskolik.profil.domain.model.entity.Sertifika;
import ayu.edu.tr.iskolik.profil.domain.model.entity.Sinav;
import ayu.edu.tr.iskolik.profil.domain.model.entity.Yetenek;
import ayu.edu.tr.iskolik.profil.domain.model.mapper.ProfilDTOMapper;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", uses = {ProfilDTOMapper.class})
public interface KullaniciDTOMapper {

	// Entity --> DTO
	default KullaniciDTO toKullaniciDTO(Kullanici kullanici) {
		if(kullanici == null) {
			return null;
		}

		if(kullanici instanceof BireyselKullanici) {
			return toBireyselKullaniciDTO((BireyselKullanici) kullanici);
		} else if(kullanici instanceof KurumsalKullanici) {
			return toKurumsalKullaniciDTO((KurumsalKullanici) kullanici);
		} else {
			throw new IskolikOrtakException(ErrorCode.VALIDATION_BUSINESS_PARAMETER_INVALID, kullanici.getEmail());
		}
	}

	BireyselKullaniciDTO toBireyselKullaniciDTO(BireyselKullanici bireyselKullanici);
	KurumsalKullaniciDTO toKurumsalKullaniciDTO(KurumsalKullanici kurumsalKullanici);
	List<KullaniciDTO> toKullaniciDTOList(List<Kullanici> kullaniciList);
	List<BireyselKullaniciDTO> toBireyselKullaniciDTOList(List<BireyselKullanici> bireyselKullaniciList);

	// DTO --> Entity
	default Kullanici toKullanici(KullaniciDTO kullaniciDTO) {
		if(kullaniciDTO == null) {
			return null;
		}

		if(kullaniciDTO instanceof BireyselKullaniciDTO) {
			return toBireyselKullanici((BireyselKullaniciDTO) kullaniciDTO);
		} else if(kullaniciDTO instanceof KurumsalKullaniciDTO) {
			return toKurumsalKullanici((KurumsalKullaniciDTO) kullaniciDTO);
		} else {
			throw new IskolikOrtakException(ErrorCode.VALIDATION_BUSINESS_PARAMETER_INVALID, kullaniciDTO.getEmail());
		}
	}

	@Mapping(target = "durum", ignore = true)
	@Mapping(target = "kayitTarihi", ignore = true)
	@Mapping(target = "sifreHash", ignore = true)
	BireyselKullanici toBireyselKullanici(BireyselKullaniciDTO bireyselKullaniciDTO);

	@Mapping(target = "durum", ignore = true)
	@Mapping(target = "kayitTarihi", ignore = true)
	@Mapping(target = "sifreHash", ignore = true)
	KurumsalKullanici toKurumsalKullanici(KurumsalKullaniciDTO kurumsalKullaniciDTO);
}

