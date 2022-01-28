package br.com.geofusion.cart.interfaces.rest.shoppingcart;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.geofusion.cart.interfaces.rest.shoppingcart.dto.CreateCartDTO;

public interface ShoppingCartResource {

	public ResponseEntity<Void> create(final @Valid @RequestBody CreateCartDTO createCartDTO);
}
