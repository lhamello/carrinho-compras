package br.com.geofusion.cart.application.product;

import br.com.geofusion.cart.application.CommandRequest;

public class NewProductRequest implements CommandRequest {

	private Long code;
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
