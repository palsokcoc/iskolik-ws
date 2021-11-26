package ayu.edu.tr.iskolik.common.model.response;

/**
 * Kurum içi standartları uymak için platform.web2 paketinde tanımlanmış
 * olan tr.gov.tcmb.web.model.Message modelinden kopyalandı.
 * - errorCode alanı eklendi
 */
public class Message {
	private final String errorCode;
	private String message;
	private Level level;

	public Message(String message, Level level) {
		this.errorCode = null;
		this.message = message;
		this.level = level;
	}

	public Message(String errorCode, String message, Level level) {
		this.errorCode = errorCode;
		this.message = message;
		this.level = level;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Level getLevel() {
		return this.level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public enum Level {
		INFO, WARN, ERROR
	}
}