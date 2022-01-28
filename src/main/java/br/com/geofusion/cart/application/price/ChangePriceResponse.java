package br.com.geofusion.cart.application.price;

import br.com.geofusion.cart.application.CommandResponse;

public class ChangePriceResponse implements CommandResponse {

	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
