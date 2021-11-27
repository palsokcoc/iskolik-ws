package ayu.edu.tr.iskolik.common.domain.repository;

import ayu.edu.tr.iskolik.common.domain.exception.ErrorCode;
import ayu.edu.tr.iskolik.common.domain.exception.IskolikOrtakException;
import ayu.edu.tr.iskolik.common.domain.repository.filter.Filter;
import ayu.edu.tr.iskolik.common.domain.repository.filter.Filter.Operation;
import ayu.edu.tr.iskolik.common.domain.repository.filter.Filters;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

/**
 * JpaSpecificationExecutor interface'ini extend eden Repository'lerde sorgulama yaparken Specification sınıfı kullanılabiliyor.
 * Bu sınıfı özelleştirerek {@link Filter.Operation} sınıfında tanımlanmış olan işlemleri kullanarak "subeKodu=2, vezneNo>500" gibi
 * ifadelere karşılık gelen Specification nesnelerinin otomatik olarak oluşturabiliyoruz. Böylece istemciler kendi query'leri yazabiliyorlar.
 *
 * Bu sınıf herhangi bir sınıf hiyerarşisine dahil olmayan entity'ler için başka bir şey eklemeye gerek olmadan doğru bir şekilde çalışır.
 *
 * Eğer sorgulama yapılacak entity için bir sınıf hiyerarşisi bulunuyorsa sorgulamada base entity yerine subentity'lerden
 * birisinin kullanılması gerekebilir. Bu durum sorgulamada kullanılacak filtrelerden bir tanesinin discriminator alanı üzerinde
 * olduğunda yaşanır. Mesela ParaBirimi hiyerarşisinin tamamı yerine sadece EfektifParaBirimi entity'leri arasında arama yapmak istenebilir.
 * Bu gibi durumlarda bu sınıf extend edilip getRootEntityClass() metodu override edilerek gelen filtrelere göre tam olarak hangi entity
 * üzerinde sorgulama yapılacağı belirlenir. Örnek olarak {@link tr.gov.tcmb.subvezn.parabirimi.domain.repository.ParaBirimiSpecification}
 * sınıfı incelenebilir.
 *
 */
public class BaseSpecification<T> implements Specification<T> {

	private final Filters filters;

	public BaseSpecification(Filters filters) {
		this.filters = filters;
	}

	/**
	 * Bu metot JpaSpecificationExecutor'ı extend eden Repository sınıflarında parametre olarak Specification alan metotlar çağrıldığında
	 * Hibernate tarafından otomatik olarak çağrılır ve sql sorgularının bu metot tarafından dönülen predicate'lara göre üretilmesi sağlanır.
	 *
	 */
	@Override
	public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
		if(filters == null || filters.getFilters() == null || filters.getFilters().isEmpty()) {
			return null;
		}

		Root<? extends T> queryRoot;
		Class<? extends T> rootEntityClass = getRootEntityClass(root);
		if(rootEntityClass == null) {
			/*
			 * Musteri gibi type hiyerarşisi olmayan entity'ler için burası çalışır.
			 * Çünkü bu gibi durumlarda bu sınıf extend edilmez ve getRootEntityClass()
			 * metodu null döner.
			 */
			queryRoot = root;
		} else {
			/*
			 * ParaBirimi gibi type hiyerarşisi olan entity'ler için burası çalışır.
			 * Çünkü bu gibi durumlarda bu sınıf extend edilir ve getRootEntityClass()
			 * metodu null olmayan bir değer döner.
			 *
			 * Bu satırın amacı sorgulamanın gerçekte hangi entity üzerinde yapılacağını belirlemektir.
			 * Örneğin ParaBirimi tablosu sorgulanacaksa ParaBirimiRepository sınıfında
			 * generic entity olarak ParaBirimi verildiği için root entity otomatik olarak
			 * ParaBirimi sınıfı olur. Bu durumda da sorgulama sonucu olarak Parabirimi
			 * altındaki bütün alt sınıflar gelir. Ama mesela sadece efektif parabirimleri
			 * arasında sorgulama yapılmak istenirse filtrelerden birisi "type=E" olarak
			 * verilir. Bu durumda ParaBirimiSpecification sınıfındaki getRootEntityClass()
			 * metodu EfektifParaBirimi sınıfını dönecektir ve aşağıdaki satır sayesinde
			 * root olarak ParaBirimi yerine EfektifParaBirimi'nin kullanılması sağlanacaktır.
			 *
			 * builder.treat() metodunun aldığı iki parametre aynı type ise ve bu type'ın subtype'ı
			 * yoksa Hibernate ürettiği sql sorgusuna "null=null" ifadesini ekliyor ve hataya sebep oluyor.
			 * Bu yüzden bu satırı sadece type hiyerarşisi olan entity'ler için çalıştırıyoruz.
			 *
			 * Örnek: builder.treat(ParaBirimi.class, ParaBirimi.class): sorun yok çünkü ParaBirimi entity'si
			 * EfektifParaBirimi ve TcParaBirimi gibi subtype'lara sahip.
			 *
			 * Örnek: builder.treat(Musteri.class, Musteri.class): sql'e "null=null" ifadesi ekleniyor çünkü
			 * Musteri'nin subtype'ı yok.
			 */
			queryRoot = builder.treat(root, rootEntityClass);
		}

		// Aşağıdaki hatayı almamak için yeni bir final variable tanımlıyoruz
		// "local variables referenced from a lambda expression must be final or effectively final"
		final Root<? extends T> finalRoot = queryRoot;
		final Predicate[] predicates = filters.getFilters().stream()
			.map(filter -> toPredicate(filter, finalRoot, builder))
			.toArray(Predicate[]::new);

		return builder.and(predicates);
	}

	/**
	 * Eğer sorgulama yapılacak entity için bir sınıf hiyerarşisi bulunuyorsa sorgulamanın base entity yerine subentity'lerden birisinin kullanılması
	 * gerekebilir. Bu durum sorgulamada kullanılacak filtrelerden bir tanesinin discriminator alanı üzerinde olduğunda yaşanır.
	 * Bu gibi durumlarda {@link BaseSpecification} sınıfı extend edilir ve bu metot override edilerek eğer discriminator alanı üzerinde filtre varsa
	 * sorgulama için hangi subentity'nin kullanılacağına karar verilir.
	 */
	protected Class<? extends T> getRootEntityClass(Root<T> root) {
		return null;
	}

	protected Filter getFilterByField(String field) {
		return filters.findFilterByFieldName(field);
	}

	private Predicate toPredicate(Filter filter, Root<? extends T> root, CriteriaBuilder builder) {
		String field = filter.getField();
		Operation operation = filter.getOperation();
		String value = filter.getValue();

		Expression fieldExpression;
		try {
			/*
			 *	"kupur.emisyon.id = -1" gibi iç içe nesneler bulunan filtre varsa
			 *	path = path.get("kupur").get("emisyon").get("id)) elde etmek için
			 *	gelen ifadeyi "." karakterine göre ayırıp döngü içine sokuyoruz.
			 */
			String[] fields = field.split("\\.");
			Path<Comparable> path = root.get(fields[0]);
			for(int i = 1; i < fields.length; i++) {
				path = path.get(fields[i]);
			}
			fieldExpression = path;
		} catch (IllegalArgumentException e) {
			throw new IskolikOrtakException(ErrorCode.VALIDATION_REQUEST_FILTER_FIELD_NOT_VALID, field);
		}

		Comparable valueAsJavaType = null;
		List<Comparable> valuesAsJavaType = null;
		if(value.indexOf('(') == -1) {
			// Kriter olarak tek bir değer verilmiş
			valueAsJavaType = stringToJavaType(value, fieldExpression.getJavaType());
		} else {
			// Kriter olarak liste verilmiş. Verilen string'in başı ve sonundaki parantezleri kaldırıp virgüle göre ayırarak liste elde ediyoruz
			List<String> values = Arrays.asList(value.substring(1, value.length() - 1).split(";", -1));
			// Elde edilen string listesini uygun tipteki object listesine çeviriyoruz
			valuesAsJavaType = values.stream().map(val -> stringToJavaType(val, fieldExpression.getJavaType())).collect(Collectors.toList());
		}

		switch (operation) {
			case EQUALS:
				return builder.equal(fieldExpression, valueAsJavaType);
			case NOT_EQUALS:
				return builder.notEqual(fieldExpression, valueAsJavaType);
			case GT:
				return builder.greaterThan(fieldExpression, valueAsJavaType);
			case GE:
				return builder.greaterThanOrEqualTo(fieldExpression, valueAsJavaType);
			case LT:
				return builder.lessThan(fieldExpression, valueAsJavaType);
			case LE:
				return builder.lessThanOrEqualTo(fieldExpression, valueAsJavaType);
			case LIKE:
				return builder.like(fieldExpression, value);
			case NOTLIKE:
				return builder.notLike(fieldExpression, value);
			case IN:
				return fieldExpression.in(valuesAsJavaType);
			case NOTIN:
				return builder.not(fieldExpression.in(valuesAsJavaType));
			default:
				throw new IskolikOrtakException(ErrorCode.VALIDATION_REQUEST_FILTER_OPERATION_NOT_VALID, operation.getSymbol());
		}
	}

	@SuppressWarnings("rawtypes")
	private Comparable stringToJavaType(String value, Class<?> javaType) {
		try {
			if (javaType == Boolean.class) {
				return Boolean.valueOf(value);
			} else if (javaType == Integer.class) {
				return Integer.valueOf(value);
			} else if (javaType == Long.class) {
				return Long.valueOf(value);
			} else if (javaType == Double.class) {
				return Double.valueOf(value);
			}
			else if (javaType == String.class) {
				return value.replaceAll("'","");
			}
			else {
				return value;
			}
		} catch (IllegalArgumentException e) {
			throw new IskolikOrtakException(ErrorCode.VALIDATION_REQUEST_FILTER_VALUE_TYPE_NOT_VALID, value, javaType.getSimpleName());
		}
	}
}
