package ayu.edu.tr.iskolik.kullanici.domain.repository;

import ayu.edu.tr.iskolik.common.domain.exception.ErrorCode;
import ayu.edu.tr.iskolik.common.domain.exception.IskolikOrtakException;
import ayu.edu.tr.iskolik.common.domain.repository.BaseSpecification;
import ayu.edu.tr.iskolik.common.domain.repository.filter.Filter;
import ayu.edu.tr.iskolik.common.domain.repository.filter.Filter.Operation;
import ayu.edu.tr.iskolik.common.domain.repository.filter.Filters;
import ayu.edu.tr.iskolik.kullanici.domain.model.entity.BireyselKullanici;
import ayu.edu.tr.iskolik.kullanici.domain.model.entity.Kullanici;
import ayu.edu.tr.iskolik.kullanici.domain.model.entity.KurumsalKullanici;
import javax.persistence.criteria.Root;

public class KullaniciSpecification extends BaseSpecification<Kullanici> {

	public KullaniciSpecification(Filters filters) {
		super(filters);
	}

	@Override
	protected Class getRootEntityClass(Root<Kullanici> root) {
		Filter typeFilter = super.getFilterByField("tip");
		if(typeFilter == null || typeFilter.getOperation() != Operation.EQUALS) {
			return Kullanici.class;
		} else {
			String type = typeFilter.getValue();
			if(type.equals("Bireysel")) {
				return BireyselKullanici.class;
			} else if (type.equals("Kurumsal")) {
				return KurumsalKullanici.class;
			} else {
				throw new IskolikOrtakException(ErrorCode.VALIDATION_BUSINESS_ARGUMENT_NOT_VALID, "tip", type, "Bireysel,Kurumsal");
			}
		}
	}
}
