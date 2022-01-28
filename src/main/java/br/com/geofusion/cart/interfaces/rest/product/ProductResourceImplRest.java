package br.com.geofusion.cart.interfaces.rest.product;

import java.net.URI;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.geofusion.cart.application.CommandHandler;
import br.com.geofusion.cart.application.price.ChangePriceCommand;
import br.com.geofusion.cart.application.price.ChangePriceRequest;
import br.com.geofusion.cart.application.price.ChangePriceResponse;
import br.com.geofusion.cart.application.product.NewProductCommand;
import br.com.geofusion.cart.application.product.NewProductRequest;
import br.com.geofusion.cart.application.product.NewProductResponse;
import br.com.geofusion.cart.interfaces.rest.product.dto.request.PriceDTO;
import br.com.geofusion.cart.interfaces.rest.product.dto.request.ProductDTO;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Products")
public class ProductResourceImplRest implements ProductResource {

	private final CommandHandler handler;
	private final ModelMapper mapper;

	public ProductResourceImplRest(final CommandHandler handler, final ModelMapper mapper) {
		this.handler = handler;
		this.mapper = mapper;
	}

	@Override
	@PostMapping(value = "/api/products")
	public ResponseEntity<Void> create(final @Valid @RequestBody ProductDTO productDTO) {
		final NewProductRequest request = mapper.map(productDTO, NewProductRequest.class);
		final NewProductResponse response = handler.handle(NewProductCommand.class, request);
		
		return ResponseEntity.created(URI
					.create(String.format("/api/products/%s", response.getCode())))
				.build();
	}

	@Override
	@PostMapping(value = "/api/products/{code}/prices")
	public ResponseEntity<Void> changePrice(final @PathVariable(name = "code") Long code, final @Valid @RequestBody PriceDTO priceDTO) {
		final ChangePriceRequest request = mapper.map(priceDTO, ChangePriceRequest.class);
		request.setProductCode(code);
		
		final ChangePriceResponse response = handler.handle(ChangePriceCommand.class, request);
		
		return ResponseEntity.created(URI
					.create(String.format("/api/products/%s/prices/%s", code, response.getId())))
				.build();
	}
}
