package ayu.edu.tr.iskolik.common.application.controller;

import ayu.edu.tr.iskolik.common.model.response.GenericServerResponse;
import java.util.Collections;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "")
public abstract class BaseController<T> {

	protected ResponseEntity<GenericServerResponse> createResponseForSuccess(HttpStatus httpStatus, T data) {
		return createResponseForSuccess(httpStatus, data, null);
	}

	protected ResponseEntity<GenericServerResponse> createResponseForSuccess(HttpStatus httpStatus, String message) {
		return createResponseForSuccess(httpStatus, null, message);
	}

	protected ResponseEntity<GenericServerResponse> createResponseForSuccess(HttpStatus httpStatus, T data, String message) {
		GenericServerResponse genericRestResponse = new GenericServerResponse();
		if(data != null) {
			genericRestResponse.setData(data);
		} else {
			genericRestResponse.setData(Collections.emptyList());
		}
		genericRestResponse.setSuccess(true);
		genericRestResponse.setMessage(message);
		return ResponseEntity.status(httpStatus).body(genericRestResponse);
	}

	protected ResponseEntity<GenericServerResponse> createResponseForSuccess(HttpStatus httpStatus, List<T> data) {
		GenericServerResponse genericRestResponse = new GenericServerResponse();
		genericRestResponse.setData(data);
		genericRestResponse.setSuccess(true);
		return ResponseEntity.status(httpStatus).body(genericRestResponse);
	}
}
