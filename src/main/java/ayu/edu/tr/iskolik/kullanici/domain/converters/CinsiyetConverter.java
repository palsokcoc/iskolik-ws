package ayu.edu.tr.iskolik.kullanici.domain.converters;

import ayu.edu.tr.iskolik.kullanici.domain.model.entity.BireyselKullanici.Cinsiyet;
import java.util.Arrays;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
class CinsiyetConverter implements AttributeConverter<Cinsiyet,String> {

	@Override
	public String convertToDatabaseColumn(Cinsiyet cinsiyet) {
		if(cinsiyet == null) {
			return null;
		};
		return cinsiyet.getKisaltma();
	}

	@Override
	public Cinsiyet convertToEntityAttribute(String kisaltma) {
		if(kisaltma == null) {
			return null;
		}

		return Arrays.stream(Cinsiyet.values()).filter(cinsiyet -> cinsiyet.getKisaltma().equals(kisaltma)).findFirst().orElseThrow(IllegalArgumentException::new);
	}
}

