package br.com.geofusion.cart.domain.model.product;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import br.com.geofusion.cart.domain.shared.DomainException;
import br.com.geofusion.cart.domain.shared.ValidationError;

@Component
public class ProductFactory {

	private ProductRepository repository;

	public ProductFactory(final ProductRepository repository) {
		this.repository = repository;
	}

	public Product createProduct(final Long code, final String description) {
		final Product product = new Product(code, description);
		this.validateFields(product);
		this.validateExistsCode(product);
		return product;
	}
	
	private void validateExistsCode(final Product product) {
		if (repository.existsByCode(product.getCode())) {
			throw new DomainException(ValidationError.create("Código já adicionado!"));
		}
	}

	public void validateFields(final Product product) {
		final Optional<List<ValidationError>> possibleErrors = product.validate();
		
		if (possibleErrors.isPresent()) {
			throw new DomainException(possibleErrors.get());
		}
	}
}
