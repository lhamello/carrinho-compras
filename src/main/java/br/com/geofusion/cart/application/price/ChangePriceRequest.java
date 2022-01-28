package br.com.geofusion.cart.application.price;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import br.com.geofusion.cart.application.CommandRequest;

public class ChangePriceRequest implements CommandRequest {

	private Long productCode;
	private BigDecimal price;
	private LocalDateTime activationDate;

	public Long getProductCode() {
		return productCode;
	}

	public void setProductCode(Long productCode) {
		this.productCode = productCode;
	}

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
