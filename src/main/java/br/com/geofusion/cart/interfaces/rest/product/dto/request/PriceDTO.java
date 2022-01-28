package br.com.geofusion.cart.interfaces.rest.product.dto.request;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Positive;

public class PriceDTO {

	@Positive(message = "Informe um valor maior que 0.")
	private BigDecimal price;
	@FutureOrPresent(message = "Informa uma data maior igual à data atual.")
	private LocalDateTime activationDate;

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public LocalDateTime getActivationDate() {
		return activationDate;
	}

	public void setActivationDate(LocalDateTime activationDate) {
		this.activationDate = activationDate;
	}
}
