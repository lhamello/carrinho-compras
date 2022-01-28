package br.com.geofusion.cart.config.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

public class ErrorRestDTO {

	private LocalDateTime timestamp;
	private Integer status;
	private String error;
	private String message;

	private ErrorRestDTO(final HttpStatus httpStatus, final String message) {
		this.timestamp = LocalDateTime.now();
		this.status = httpStatus.value();
		this.error = httpStatus.getReasonPhrase();
		this.message = message;
	}

	public static ErrorRestDTO create(final HttpStatus httpStatus, final String message) {
		return new ErrorRestDTO(httpStatus, message);
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public Integer getStatus() {
		return status;
	}

	public String getError() {
		return error;
	}

	public String getMessage() {
		return message;
	}
}
