package ayu.edu.tr.iskolik.infrastructure.utils;

public class DB2390Dialect extends org.hibernate.dialect.DB2390Dialect {

	@Override
	public String getForUpdateString() {
		return " for update with rs use and keep update locks";
	}
}
