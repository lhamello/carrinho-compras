package br.com.geofusion.cart.domain.model.price;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import br.com.geofusion.cart.domain.shared.DomainException;
import br.com.geofusion.cart.domain.shared.ValidationError;

@Component
public class PriceFactory {

	private PriceRepository repository;

	public PriceFactory(final PriceRepository repository) {
		this.repository = repository;
	}

	public Price createPrice(final Long productCode, final BigDecimal value, final LocalDateTime activationDate) {
		final Price price = new Price(productCode, value, activationDate);
		this.validateFields(price);
		this.validateActivationDate(price);
		return price;
	}

	public void validateFields(final Price price) {
		final Optional<List<ValidationError>> possibleErrors = price.validate();
		
		if (possibleErrors.isPresent()) {
			throw new DomainException(possibleErrors.get());
		}
	}
	
	private void validateActivationDate(final Price price) {
		if (!repository.findEqualOrAfter(price.getActivationDate()).isEmpty()) {
			throw new DomainException(ValidationError.create("Data de ativação inválida."));
		}
	}
}
