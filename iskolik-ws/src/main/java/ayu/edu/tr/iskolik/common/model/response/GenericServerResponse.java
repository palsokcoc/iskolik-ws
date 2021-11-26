package ayu.edu.tr.iskolik.common.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


/**
 * Kurum içi standartları uymak için platform.web2 paketinde tanımlanmış
 * olan tr.gov.tcmb.web.model.Message modelinden kopyalanmıştır.
 * - pikurList alanı çıkarıldı
 * - timestamp alanı eklendi
 */
public class GenericServerResponse<T> {
	private Long total;
	private boolean success = true;
	private List<ValidationError> errors;
	private List<T> data;
	private String message;
	private List<Message> messages;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private final LocalDateTime timestamp;

	public GenericServerResponse() {
		timestamp = LocalDateTime.now();
	}

	public Long getTotal() {
		return this.total;
	}

	public GenericServerResponse setTotal(Long total) {
		this.total = total;
		return this;
	}

	public boolean isSuccess() {
		return this.success;
	}

	public GenericServerResponse setSuccess(boolean success) {
		this.success = success;
		return this;
	}

	public List<ValidationError> getErrors() {
		return this.errors;
	}

	public GenericServerResponse setErrors(List<ValidationError> errors) {
		this.errors = errors;
		this.success = false;
		return this;
	}

	public List<T> getData() {
		return this.data;
	}



	public void setData(List<T> data) {
		this.data = data;
		if (this.total == null) {
			this.total = this.data == null ? 0L : (long)this.data.size();
		}


	}
	public void setData(T data) {
		List<T> datas=new ArrayList<>(1);
		datas.add(data);
		this.data=datas;
		this.total=1L;


	}
	public String getMessage() {
		return this.message;
	}

	public GenericServerResponse setMessage(String message) {
		this.message = message;
		return this;
	}

	public List<Message> getMessages() {
		return this.messages;
	}

	public GenericServerResponse setMessages(List<Message> messages) {
		this.messages = messages;
		return this;
	}

	public GenericServerResponse addMessage(Message message) {
		if (this.messages == null) {
			this.messages = new ArrayList<>();
		}

		this.messages.add(message);
		return this;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}
}
