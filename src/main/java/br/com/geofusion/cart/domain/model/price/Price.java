package br.com.geofusion.cart.domain.model.price;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.geofusion.cart.domain.shared.ValidationError;

@Entity
@Table(name = "price")
public class Price {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long productCode;
	private BigDecimal value;
	private LocalDateTime activationDate;
	private Boolean active;
	
	private Price() {
		// utilizado pelo hibernate
	}
	
	Price(final Long productCode, final BigDecimal value, final LocalDateTime activationDate) {
		this();
		this.productCode = productCode;
		this.value = value;
		this.activationDate = activationDate;
		this.active = true;
	}
	
	Optional<List<ValidationError>> validate() {
		final List<ValidationError> validationErrors = new LinkedList<>();
		
		if (Objects.isNull(productCode) || Long.valueOf("0").compareTo(productCode) >= 0) {
			validationErrors.add(ValidationError.create("Informe o código do produto."));
		}
		
		if (Objects.isNull(value) || BigDecimal.valueOf(0).compareTo(value) >= 0) {
			validationErrors.add(ValidationError.create("Informe o preço."));
		}
		
		if (Objects.isNull(activationDate)) {
			validationErrors.add(ValidationError.create("Informe a data de ativação do preço."));
		}
		
		if (LocalDateTime.now().isAfter(activationDate)) {
			validationErrors.add(ValidationError.create("Data de ativação deve ser maior ou igual data atual."));
		}
		
		if (!validationErrors.isEmpty()) {
			return Optional.of(validationErrors);
		}
		
		return Optional.empty();
	}

	public Long getId() {
		return id;
	}

	public Long getProductCode() {
		return productCode;
	}
	
	public BigDecimal getValue() {
		return value;
	}

	public LocalDateTime getActivationDate() {
		return activationDate;
	}

	public Boolean getActive() {
		return active;
	}

	public void inativate() {
		this.active = false;
	}
}
