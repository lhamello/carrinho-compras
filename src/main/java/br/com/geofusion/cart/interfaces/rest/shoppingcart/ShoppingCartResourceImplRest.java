package br.com.geofusion.cart.interfaces.rest.shoppingcart;

import java.net.URI;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.geofusion.cart.application.CommandHandler;
import br.com.geofusion.cart.application.shoppingcart.NewShoppingCartCommand;
import br.com.geofusion.cart.application.shoppingcart.NewShoppingCartRequest;
import br.com.geofusion.cart.application.shoppingcart.NewShoppingCartResponse;
import br.com.geofusion.cart.interfaces.rest.shoppingcart.dto.CreateCartDTO;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Shopping Carts")
public class ShoppingCartResourceImplRest implements ShoppingCartResource {

	private final CommandHandler handler;
	private final ModelMapper mapper;

	public ShoppingCartResourceImplRest(final CommandHandler handler, final ModelMapper mapper) {
		this.handler = handler;
		this.mapper = mapper;
	}
	
	@Override
	@PostMapping(value = "/api/shopping-carts")
	public ResponseEntity<Void> create(final @Valid CreateCartDTO createCartDTO) {
		final NewShoppingCartRequest request = mapper.map(createCartDTO, NewShoppingCartRequest.class);
		final NewShoppingCartResponse response = handler.handle(NewShoppingCartCommand.class, request);
		
		return ResponseEntity.created(URI
				.create(String.format("/api/shopping-carts/%s", response.getCartId())))
			.build();
	}
}
