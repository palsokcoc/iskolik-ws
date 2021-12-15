package ayu.edu.tr.iskolik.common.application.jasper;

import java.util.Iterator;
import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 * hibernate.org --> Using JasperReports with Hibernate
 * http://www.hibernate.org/79.html?cmd=comphist&histnode=51
 * 
 * Herhangi bir java bean'a map edilmeyen hibernate queryler, query tek alan
 * dönüyorsa bir List, birden çok alan dönüyorsa bir object array listesi döner.
 * hibernate.org'tan indirdiğimiz bu sınıfta tek alan dönülen durum
 * düşünülmemiş. Her şartta object array dönüldüğü varsayılmış. Bu sorunu
 * {@link #getFieldValue(JRField)} metodunda düzeltmeler yaparak düzelttik.
 * 
 * @version 1.1
 */
public class HibernateQueryResultDataSource implements JRDataSource {

	private String[] fields;
	private Iterator<?> iterator;
	private Object currentValue;

	public HibernateQueryResultDataSource(List<?> list, String[] fields) {
		this.fields = fields;
		this.iterator = list.iterator();
	}

	public Object getFieldValue(JRField field) throws JRException {
		Object value = null;
		int index = getFieldIndex(field.getName());
		if (index > -1) {
			Object[] values = null;
			if (currentValue instanceof Object[]) {
				values = (Object[]) currentValue;
				value = values[index];
			} else {
				value = currentValue;
			}
		}
		return value;
	}

	public boolean next() throws JRException {
		currentValue = iterator.hasNext() ? iterator.next() : null;
		return (currentValue != null);
	}

	private int getFieldIndex(String field) {
		int index = -1;
		for (int i = 0; i < fields.length; i++) {
			if (fields[i].equals(field)) {
				index = i;
				break;
			}
		}
		return index;
	}
}
