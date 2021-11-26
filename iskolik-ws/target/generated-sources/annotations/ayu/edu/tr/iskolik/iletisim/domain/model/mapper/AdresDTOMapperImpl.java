package ayu.edu.tr.iskolik.iletisim.domain.model.mapper;

import ayu.edu.tr.iskolik.iletisim.domain.model.dto.AdresDTO;
import ayu.edu.tr.iskolik.iletisim.domain.model.entity.Adres;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-11-26T02:15:26+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.10 (AdoptOpenJDK)"
)
@Component
public class AdresDTOMapperImpl implements AdresDTOMapper {

    @Override
    public AdresDTO toAdresDTO(Adres adres) {
        if ( adres == null ) {
            return null;
        }

        AdresDTO adresDTO = new AdresDTO();

        adresDTO.setAdresId( adres.getAdresId() );
        adresDTO.setSehir( adres.getSehir() );
        adresDTO.setIlce( adres.getIlce() );
        adresDTO.setMahalle( adres.getMahalle() );
        adresDTO.setCadde( adres.getCadde() );
        adresDTO.setApartmanNo( adres.getApartmanNo() );
        adresDTO.setDaireNo( adres.getDaireNo() );

        return adresDTO;
    }

    @Override
    public Adres toAdres(AdresDTO adresDTO) {
        if ( adresDTO == null ) {
            return null;
        }

        Adres adres = new Adres();

        adres.setAdresId( adresDTO.getAdresId() );
        adres.setSehir( adresDTO.getSehir() );
        adres.setIlce( adresDTO.getIlce() );
        adres.setMahalle( adresDTO.getMahalle() );
        adres.setCadde( adresDTO.getCadde() );
        adres.setApartmanNo( adresDTO.getApartmanNo() );
        adres.setDaireNo( adresDTO.getDaireNo() );

        return adres;
    }

    @Override
    public List<AdresDTO> toAdresDTOList(List<Adres> adresList) {
        if ( adresList == null ) {
            return null;
        }

        List<AdresDTO> list = new ArrayList<AdresDTO>( adresList.size() );
        for ( Adres adres : adresList ) {
            list.add( toAdresDTO( adres ) );
        }

        return list;
    }
}
