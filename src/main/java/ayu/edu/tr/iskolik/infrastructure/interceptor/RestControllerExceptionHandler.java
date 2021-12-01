package ayu.edu.tr.iskolik.infrastructure.interceptor;

import ayu.edu.tr.iskolik.common.domain.exception.ErrorCode;
import ayu.edu.tr.iskolik.common.domain.exception.IskolikOrtakException;
import ayu.edu.tr.iskolik.common.model.response.GenericServerResponse;
import ayu.edu.tr.iskolik.common.model.response.Message;
import ayu.edu.tr.iskolik.common.model.response.Message.Level;
import ayu.edu.tr.iskolik.common.model.response.ValidationError;
import ayu.edu.tr.iskolik.infrastructure.localization.MessageLocalizationService;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class RestControllerExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(RestControllerExceptionHandler.class);

	/**
	 * BindException: Controller metotlarında @ModelAttribute annotation'ı kullanılan veya hiç annotation kullanılmayan model parametreleri gelen request'e göre
	 * validate
	 * edilirken hata alınırsa bu hata fırlatılır.
	 * <p>
	 * MethodArgumentNotValidException: Controller metotlarında @RequestBody annotation'ı kullanılan model parametreleri gelen request body'sine göre validate
	 * edilirken hata alınırsa bu hata fırlatılır.
	 */
	@ExceptionHandler({BindException.class, MethodArgumentNotValidException.class})
	public ResponseEntity<GenericServerResponse> handleBindErrors(BindException e) {
		GenericServerResponse response = new GenericServerResponse();
		List<ValidationError> validationErrors = new ArrayList<>();
		for (FieldError fieldError : e.getFieldErrors()) {
			String message = MessageLocalizationService.getLocaleMessage(fieldError.getDefaultMessage(), fieldError.getField());
			validationErrors.add(new ValidationError(fieldError.getField(), message));
		}
		response.setErrors(validationErrors);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}

	/**
	 * Controller'da tanımlanmış methodun argumanlarının hatalı olduğu durumlarda bu hata atılır.
	 * Örnek: "/parabirimi1/{id}" olarak tanımlanmış bir URI için "/parabirimi1/s" gibi bir istek gelmesi durumunda
	 * controller metoduna parametre olarak tanımlanmış olan "@PathVariable id" argumanı için bu hata atılır.
	 */
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<GenericServerResponse> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
		// Hata PathVariable veya RequestParam'dan kaynaklanıyor olabilir. Duruma göre farklı mesaj verebilmek için hata alan argumanın tipine bakıyoruz
		IskolikOrtakException iskolikOrtakException;
		String value = (e.getValue() != null) ? e.getValue().toString() : "";
		String requiredType = (e.getRequiredType() != null) ? e.getRequiredType().getSimpleName() : "";
		if (e.getParameter().getParameterAnnotation(PathVariable.class) != null) {
			iskolikOrtakException = new IskolikOrtakException(ErrorCode.VALIDATION_REQUEST_PATH_VARIABLE_TYPE_MISMATCH, e, e.getName(), value, requiredType);
		} else {
			iskolikOrtakException = new IskolikOrtakException(ErrorCode.VALIDATION_REQUEST_ARGUMENT_TYPE_MISMATCH, e, e.getName(), value, requiredType);
		}

		return handleIskolikOrtakException(iskolikOrtakException, HttpStatus.BAD_REQUEST);
	}

	/**
	 * URI doğru ama HTTP method desteklenmiyorsa bu hata atılır.
	 * Örnek: "/parabirimi" adresi için sadece GetMapping tanımlı olmasına rağmen POST isteği gelmesi
	 */
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<GenericServerResponse> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
		String[] supportedMethods = (e.getSupportedMethods() != null) ? e.getSupportedMethods() : new String[0];
		IskolikOrtakException iskolikOrtakException = new IskolikOrtakException(ErrorCode.VALIDATION_REQUEST_HTTP_METHOD_NOT_SUPPORTED, e, e.getMethod(),
				String.join(",", supportedMethods));
		return handleIskolikOrtakException(iskolikOrtakException, HttpStatus.METHOD_NOT_ALLOWED);
	}

	/**
	 * Herhangi bir sebepten dolayı gelen istekteki değerler ile json deserialize yapılamıyorsa bu hata atılıyor.
	 * Örnek: JsonTypeInfo için belirtilen alanın hiç olmaması veya hatalı olması
	 * Örnek: Verilen tarihin formatının uygun olmaması veya geçersiz bir tarih olması
	 */
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<GenericServerResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
		IskolikOrtakException iskolikOrtakException = new IskolikOrtakException(ErrorCode.VALIDATION_REQUEST_HTTP_MESSAGE_NOT_READABLE, e,
				e.getMostSpecificCause().getMessage());
		return handleIskolikOrtakException(iskolikOrtakException, HttpStatus.BAD_REQUEST);
	}

	/**
	 * Veri güncelleme işlemlerinde veri bütünlüğünü bozacak bir durum oluşuyorsa bu hata atılıyor.
	 */
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<GenericServerResponse> handleDataIntegrityViolationException(DataIntegrityViolationException e) {
		IskolikOrtakException iskolikOrtakException = new IskolikOrtakException(ErrorCode.VALIDATION_DATA_INTEGRITY_VIOLATION, e,
				e.getMostSpecificCause().getMessage());
		return handleIskolikOrtakException(iskolikOrtakException, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(IskolikOrtakException.class)
	public ResponseEntity<GenericServerResponse> handleIskolikOrtakException(IskolikOrtakException e) {
		HttpStatus httpStatus;
		switch (e.getErrorCode()) {
			case VALIDATION_BUSINESS_RESOURCE_NOT_FOUND:
				httpStatus = HttpStatus.NOT_FOUND;
				break;
			case VALIDATION_BUSINESS_RESOURCE_ALREADY_EXISTS:
				httpStatus = HttpStatus.CONFLICT;
				break;
			default:
				httpStatus = HttpStatus.BAD_REQUEST;
		}
		return handleIskolikOrtakException(e, httpStatus);
	}

	@ExceptionHandler({Exception.class})
	public ResponseEntity<GenericServerResponse> handleException(Exception e, HttpServletRequest request) {
		logger.error(request.getRequestURI().toString(), "", e);
		IskolikOrtakException iskolikOrtakException = new IskolikOrtakException(ErrorCode.SYSTEM_ERROR, e.getMessage());
		return handleIskolikOrtakException(iskolikOrtakException, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private ResponseEntity<GenericServerResponse> handleIskolikOrtakException(IskolikOrtakException e, HttpStatus httpStatus) {
		try {
			String message = MessageLocalizationService.getLocaleMessage(e.getErrorCode().getMessage(), (Object[]) e.getMessageArguments());
			GenericServerResponse response = getResponseForError(String.valueOf(e.getErrorCode().getId()), message);

			String timestampedMessage = response.getTimestamp().format(DateTimeFormatter.ISO_DATE_TIME) + " --> " + message;
			logger.error("İşkolik", timestampedMessage, e);

			return ResponseEntity.status(httpStatus).body(response);
		} catch (Exception exception) {
			GenericServerResponse genericRestResponse = new GenericServerResponse();
			genericRestResponse.setSuccess(false);
			logger.error("RestControllerExceptionHandler/createErrorResponse", "HATA ALINDI", exception);
			String errorMessage = MessageLocalizationService.getLocaleMessage(ErrorCode.SYSTEM_ERROR.getMessage(), e.getMessage());
			Message message = new Message("-", errorMessage, Level.ERROR);
			genericRestResponse.addMessage(message);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(genericRestResponse);
		}
	}

	private GenericServerResponse getResponseForError(String errorCode, String errorMessage) {
		GenericServerResponse genericRestResponse = new GenericServerResponse();
		genericRestResponse.setSuccess(false);
		Message message = new Message(errorCode, errorMessage, Level.ERROR);
		genericRestResponse.addMessage(message);
		return genericRestResponse;
	}
}


