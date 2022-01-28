package br.com.geofusion.cart.application.shoppingcart;

import br.com.geofusion.cart.application.CommandResponse;

public class NewShoppingCartResponse implements CommandResponse {

	private Long cartId;
	private String clientId;

	public Long getCartId() {
		return cartId;
	}

	public void setCartId(Long cartId) {
		this.cartId = cartId;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
}
