package br.com.geofusion.cart.interfaces.rest.product;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.geofusion.cart.config.exception.ErrorRestDTO;
import br.com.geofusion.cart.interfaces.rest.product.dto.request.PriceDTO;
import br.com.geofusion.cart.interfaces.rest.product.dto.request.ProductDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

public interface ProductResource {

	@Operation(summary = "New Product", description = "Create new product", responses = {
			@ApiResponse(responseCode = "201", description = "Created", content = @Content(mediaType = "Application/JSON")),
			@ApiResponse(responseCode = "400", description = "Bad Request",
					content = @Content(mediaType = "Application/JSON", schema = @Schema(implementation =  ErrorRestDTO.class))),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", 
				content = @Content(mediaType = "Application/JSON", schema = @Schema(implementation =  ErrorRestDTO.class)))
	})
	public ResponseEntity<Void> create(final @Valid @RequestBody ProductDTO productDTO);

	@Operation(summary = "Change Price", description = "Change the price of a product", responses = {
			@ApiResponse(responseCode = "201", description = "Created new price", content = @Content(mediaType = "Application/JSON")),
			@ApiResponse(responseCode = "400", description = "Bad Request",
				content = @Content(mediaType = "Application/JSON", schema = @Schema(implementation =  ErrorRestDTO.class))),
			@ApiResponse(responseCode = "404", description = "Not Found",
				content = @Content(mediaType = "Application/JSON", schema = @Schema(implementation =  ErrorRestDTO.class))),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", 
				content = @Content(mediaType = "Application/JSON", schema = @Schema(implementation =  ErrorRestDTO.class)))
	})
	public ResponseEntity<Void> changePrice(final @PathVariable(name = "code") Long code, final @Valid @RequestBody PriceDTO priceDTO);
}
