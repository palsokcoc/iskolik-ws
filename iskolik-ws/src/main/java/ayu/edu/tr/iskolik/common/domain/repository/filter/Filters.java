package ayu.edu.tr.iskolik.common.domain.repository.filter;

import java.util.List;

/**
 * Filtreleme ve sıralama kriterlerinin listesini içerir.
 */
public class Filters {
	private List<Filter> filters;

	public Filter findFilterByFieldName(String fieldName) {
		for (Filter filter : filters) {
			if (filter.getField().equals(fieldName)) {
				return filter;
			}
		}
		return null;
	}

	public List<Filter> getFilters() {
		return filters;
	}

	public void setFilters(List<Filter> filters) {
		this.filters = filters;
	}
}