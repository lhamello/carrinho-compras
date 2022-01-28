package br.com.geofusion.cart.interfaces.rest.product.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class ProductDTO {

	@Positive(message = "Informe um código maior que 0.")
	private Long code;
	@NotBlank(message = "Informe a descrição.")
	private String description;

	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
