package br.com.geofusion.cart.interfaces.rest;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface ProductResource {

	@PostMapping(value = "/api/products")
	public ResponseEntity<ProductDTO> create(final @Valid @RequestBody ProductDTO productDTO);
}
