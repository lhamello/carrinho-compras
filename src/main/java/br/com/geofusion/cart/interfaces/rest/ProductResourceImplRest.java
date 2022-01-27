package br.com.geofusion.cart.interfaces.rest;

import java.net.URI;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.geofusion.cart.application.CommandHandler;
import br.com.geofusion.cart.application.product.NewProductCommand;
import br.com.geofusion.cart.application.product.NewProductRequest;
import br.com.geofusion.cart.application.product.NewProductResponse;
import br.com.geofusion.cart.config.exception.ErrorRestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Products")
public class ProductResourceImplRest {

	private final CommandHandler handler;
	private final ModelMapper mapper;

	public ProductResourceImplRest(final CommandHandler handler, final ModelMapper mapper) {
		this.handler = handler;
		this.mapper = mapper;
	}

	@PostMapping(value = "/api/products")
	@Operation(summary = "New Product", description = "Create new product", responses = {
			@ApiResponse(responseCode = "201", description = "Created", content = @Content(mediaType = "Application/JSON")),
			@ApiResponse(responseCode = "400", description = "Bad Request",
					content = @Content(mediaType = "Application/JSON", schema = @Schema(implementation =  ErrorRestDTO.class))),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", 
				content = @Content(mediaType = "Application/JSON", schema = @Schema(implementation =  ErrorRestDTO.class)))
	})
	public ResponseEntity<Void> create(final @Valid @RequestBody ProductDTO productDTO) {
		final NewProductRequest request = mapper.map(productDTO, NewProductRequest.class);
		final NewProductResponse response = handler.handle(NewProductCommand.class, request);
		return ResponseEntity.created(URI.create(String.format("/api/products/%s", response.getCode()))).build();
	}
}
