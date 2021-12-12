package ayu.edu.tr.iskolik.kullanici.domain.converters;

import ayu.edu.tr.iskolik.basvuru.domain.model.entity.Basvuru.Durum;
import java.util.Arrays;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
class BasvuruDurumConverter implements AttributeConverter<Durum, String> {

	@Override
	public String convertToDatabaseColumn(Durum durum) {
		if (durum==null) {
			return null;
		}

		return durum.getAciklama();
	}

	@Override
	public Durum convertToEntityAttribute(String aciklama) {
		if (aciklama==null) {
			return null;
		}

		return Arrays.stream(Durum.values()).filter(durum -> durum.getAciklama().equals(aciklama)).findFirst().orElseThrow(IllegalArgumentException::new);
	}
}
