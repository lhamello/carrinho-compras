package br.com.geofusion.cart.config.exception;

import java.time.LocalDateTime;

public class ErrorRestDTO {

	private LocalDateTime dateTime;
	private Integer code;
	private String description;
	private String message;

	private ErrorRestDTO(final LocalDateTime dateTime, final Integer code, final String description, final String message) {
		this.dateTime = dateTime;
		this.code = code;
		this.description = description;
		this.message = message;
	}

	public static ErrorRestDTO create(final LocalDateTime dateTime, final Integer code, final String description, final String message) {
		return new ErrorRestDTO(dateTime, code, description, message);
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public Integer getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

	public String getMessage() {
		return message;
	}
}
