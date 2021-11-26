package ayu.edu.tr.iskolik.common.domain.mapper;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;

public class DateMapper {

	public LocalDate asLocalDate(Date date) {

		return LocalDate.ofInstant(date.toInstant(), ZoneId.systemDefault());
	}

	public Date asDate(LocalDate date) {
		return Date.valueOf(date);
	}
}