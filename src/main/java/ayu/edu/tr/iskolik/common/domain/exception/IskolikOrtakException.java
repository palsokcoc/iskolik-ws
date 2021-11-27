package ayu.edu.tr.iskolik.common.domain.exception;

import ayu.edu.tr.iskolik.infrastructure.localization.MessageLocalizationService;

public class IskolikOrtakException extends RuntimeException {

	private final ErrorCode errorCode;
	private final String[] messageArguments;

	public IskolikOrtakException(ErrorCode errorCode, String... messageArguments) {
		super();
		this.errorCode = errorCode;
		this.messageArguments = messageArguments;
	}

	public IskolikOrtakException(ErrorCode errorCode, Throwable cause, String... messageArguments) {
		super(cause);
		this.errorCode = errorCode;
		this.messageArguments = messageArguments;
	}

	@Override
	public String getMessage() {
		return this.getLocalizedMessage();
	}

	@Override
	public String getLocalizedMessage() {
		return MessageLocalizationService.getLocaleMessage(this.errorCode.getMessage(), this.getMessageArguments());
	}

	public ErrorCode getErrorCode() {
		return errorCode;
	}

	public String[] getMessageArguments() {
		return messageArguments;
	}
}
