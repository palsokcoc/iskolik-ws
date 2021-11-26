package ayu.edu.tr.iskolik.common.domain.exception;

public enum ErrorCode {

	SYSTEM_ERROR(10000, "system.error"),

	VALIDATION_REQUEST_ARGUMENT_NOT_VALID(11100, "validation.request.argument.not.valid"),
	VALIDATION_REQUEST_ARGUMENT_TYPE_MISMATCH(11101, "validation.request.argument.type.mismatch"),
	VALIDATION_REQUEST_FIELD_NULL(11200, "validation.request.field.null"),
	VALIDATION_REQUEST_FILTER_FORMAT_NOT_VALID(11300, "validation.request.filter.format.not.valid"),
	VALIDATION_REQUEST_FILTER_FIELD_NOT_VALID(11301, "validation.request.filter.field.not.valid"),
	VALIDATION_REQUEST_FILTER_OPERATION_NOT_VALID(11302, "validation.request.filter.operation.not.valid"),
	VALIDATION_REQUEST_FILTER_VALUE_NOT_VALID(11303, "validation.request.filter.value.not.valid"),
	VALIDATION_REQUEST_FILTER_VALUE_TYPE_NOT_VALID(11304, "validation.request.filter.valueType.not.valid"),
	VALIDATION_REQUEST_HTTP_METHOD_NOT_SUPPORTED(11400, "validation.request.http.method.not.supported"),
	VALIDATION_REQUEST_HTTP_MESSAGE_NOT_READABLE(11410, "validation.request.http.message.not.readable"),
	VALIDATION_REQUEST_PATH_VARIABLE_TYPE_MISMATCH(11500, "validation.request.path.variable.type.mismatch"),


	VALIDATION_BUSINESS_ARGUMENT_NOT_VALID(12100, "validation.business.argument.not.valid"),
	VALIDATION_BUSINESS_FIELD_NOT_NULL(12200, "validation.business.field.not.null"),
	VALIDATION_BUSINESS_FIELD_NULL(12201, "validation.business.field.null"),
	VALIDATION_BUSINESS_PARAMETER_INVALID(12300, "validation.business.parameter.invalid"),
	VALIDATION_BUSINESS_RESOURCE_ALREADY_EXISTS(12400, "validation.business.resource.already.exists"),
	VALIDATION_BUSINESS_RESOURCE_NOT_FOUND(12401, "validation.business.resource.not.found"),

	VALIDATION_DATA_INTEGRITY_VIOLATION(13000, "validation.data.integrity.violation");

	private final int id;
	private final String message;

	ErrorCode(int id, String message) {
		this.id = id;
		this.message = message;
	}

	public int getId() {
		return id;
	}

	public String getMessage() {
		return message;
	}
}
