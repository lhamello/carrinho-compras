package br.com.geofusion.cart.application.shoppingcart;

import br.com.geofusion.cart.application.CommandRequest;

public class NewShoppingCartRequest implements CommandRequest {

	private Long productCode;
	private Integer quantity;
	private String clientId;

	public Long getProductCode() {
		return productCode;
	}

	public void setProductCode(Long productCode) {
		this.productCode = productCode;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
}
