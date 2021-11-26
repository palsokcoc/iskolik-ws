package ayu.edu.tr.iskolik.iletisim.application.model.mapper;

import ayu.edu.tr.iskolik.iletisim.application.model.request.AdresRequest;
import ayu.edu.tr.iskolik.iletisim.domain.model.dto.AdresDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-11-26T01:46:31+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.10 (AdoptOpenJDK)"
)
@Component
public class AdresRequestMapperImpl implements AdresRequestMapper {

    @Override
    public AdresDTO toAdresDTO(AdresRequest adresRequest) {
        if ( adresRequest == null ) {
            return null;
        }

        AdresDTO adresDTO = new AdresDTO();

        adresDTO.setAdresId( adresRequest.getAdresId() );
        adresDTO.setSehir( adresRequest.getSehir() );
        adresDTO.setIlce( adresRequest.getIlce() );
        adresDTO.setMahalle( adresRequest.getMahalle() );
        adresDTO.setCadde( adresRequest.getCadde() );
        if ( adresRequest.getApartmanNo() != null ) {
            adresDTO.setApartmanNo( adresRequest.getApartmanNo() );
        }
        if ( adresRequest.getDaireNo() != null ) {
            adresDTO.setDaireNo( adresRequest.getDaireNo() );
        }

        return adresDTO;
    }
}
