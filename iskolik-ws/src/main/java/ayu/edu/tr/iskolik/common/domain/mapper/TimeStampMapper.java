package ayu.edu.tr.iskolik.common.domain.mapper;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class TimeStampMapper {

	public LocalDateTime asLocalDateTime(Timestamp date) {

		return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
	}

	public Timestamp asLocalDateTime(LocalDateTime date) {
		return Timestamp.valueOf(date);
	}
}