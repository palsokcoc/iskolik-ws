package ayu.edu.tr.iskolik.common.domain.repository.filter;

import ayu.edu.tr.iskolik.common.domain.exception.ErrorCode;
import ayu.edu.tr.iskolik.common.domain.exception.IskolikOrtakException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

/**
 * Filtreleme için kullanılacak kriterlerden her birisini temsil etmek için kullanılır.
 * "field operation value" şeklinde tek bir string kullanılarak oluşturulur.
 * Örnek: "subeKodu = 1"
 * Örnek: "adet > 1000"
 * Örnek: "vezneNo in (400, 401, 403)"
 */
public class Filter {

	private final static String REGEX = StringUtils.join(Operation.map.keySet(),"|");
	private final static String WITH_DELIMITER = "((?<=%1$s)|(?=%1$s))";

	private String field;
	private Operation operation;
	private String value;

	/**
	 *
	 * @param filter Filtreleme için kullanılacak kriterin tek satırlık ifadesini belirtir
	 *  Örnek: "subeKodu = 1"
	 *  Örnek: "adet > 1000"
	 *  Örnek: "vezneNo in (400, 401, 403)"
	 *  @throws IskolikOrtakException Verilen filter ifadesi "field operation value" formatına uymuyorsa veya "operation" için verilen değer geçerli
	 *  bir işleme karşılık gelmiyorsa bu hata fırlatılır.
	 */
	@JsonCreator
	public Filter(@JsonProperty("filter") String filter) {
		if(filter == null || filter.equals("")) {
			throw new IskolikOrtakException(ErrorCode.VALIDATION_REQUEST_FIELD_NULL, "filter");
		}

		String[] split = filter.split(String.format(WITH_DELIMITER, REGEX));

		if(split.length != 3) {
			throw new IskolikOrtakException(ErrorCode.VALIDATION_REQUEST_FILTER_FORMAT_NOT_VALID, filter);
		}

		this.field = split[0];
		this.operation = Operation.findBySymbol(split[1]);
		if (operation == null) {
			throw new IskolikOrtakException(ErrorCode.VALIDATION_REQUEST_FILTER_OPERATION_NOT_VALID, split[1]);
		}
		this.value = split[2];
	}

	/* getters-setters */
	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public Operation getOperation() {
		return operation;
	}

	public void setOperation(Operation operation) {
		this.operation = operation;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public enum Operation {
		@JsonProperty("=")
		EQUALS("="),

		@JsonProperty("!=")
		NOT_EQUALS("!="),

		@JsonProperty(">")
		GT(">"),

		@JsonProperty(">=")
		GE(">="),

		@JsonProperty("<")
		LT("<"),

		@JsonProperty("<=")
		LE("<="),

		@JsonProperty("~")
		LIKE("~"),

		@JsonProperty("!~")
		NOTLIKE("!~"),

		@JsonProperty("_in_")
		IN("_in_"),

		@JsonProperty("_nin_")
		NOTIN("_nin_");

		private final static Map<String, Operation> map;
		static {
			map = new HashMap<>();
			for (Operation operation: Operation.values()) {
				map.put(operation.getSymbol(), operation);
			}
		}

		private final String symbol;

		Operation(String symbol) {
			this.symbol = symbol;
		}

		public static Operation findBySymbol(String symbol) {
			return map.get(symbol);
		}

		public String getSymbol() {
			return symbol;
		}
	}
}
