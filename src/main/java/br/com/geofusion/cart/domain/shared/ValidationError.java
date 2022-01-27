package br.com.geofusion.cart.domain.shared;

public class ValidationError {

	private String message;

	private ValidationError(final String message) {
		this.message = message;
	}
	
	public static ValidationError create(final String message) {
		return new ValidationError(message);
	}

	public String getMessage() {
		return message;
	}
}
