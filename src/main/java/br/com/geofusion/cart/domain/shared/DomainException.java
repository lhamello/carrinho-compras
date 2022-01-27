package br.com.geofusion.cart.domain.shared;

import java.util.Collections;
import java.util.List;

public class DomainException extends RuntimeException {

	private static final long serialVersionUID = 1403749611650167864L;
	private List<ValidationError> validationErrors;

	public DomainException(final List<ValidationError> validationErrors) {
		super("Erro ao validar domínio.");
		this.validationErrors = validationErrors;
	}
	
	public DomainException(final ValidationError validationError) {
		super("Erro ao validar domínio.");
		this.validationErrors = Collections.singletonList(validationError);
	}

	public List<ValidationError> getValidationErrors() {
		return Collections.unmodifiableList(validationErrors);
	}
}
