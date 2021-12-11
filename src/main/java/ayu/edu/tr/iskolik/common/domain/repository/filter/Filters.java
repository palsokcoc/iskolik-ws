package ayu.edu.tr.iskolik.common.domain.repository.filter;

import java.util.ArrayList;
import java.util.List;

/**
 * Filtreleme ve sıralama kriterlerinin listesini içerir.
 */
public class Filters {
	private List<Filter> filters;

	public Filters() {
		this.filters = new ArrayList<>();
	}

	public Filter findFilterByFieldName(String fieldName) {
		for (Filter filter : filters) {
			if (filter.getField().equals(fieldName)) {
				return filter;
			}
		}
		return null;
	}

	public void addFilter(Filter filter) {
		this.filters.add(filter);
	}

	public List<Filter> getFilters() {
		return filters;
	}

	public void setFilters(List<Filter> filters) {
		this.filters = filters;
	}
}