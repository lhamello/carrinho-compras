package br.com.geofusion.cart.application.product;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import br.com.geofusion.cart.application.Command;
import br.com.geofusion.cart.domain.product.Product;
import br.com.geofusion.cart.domain.product.ProductFactory;
import br.com.geofusion.cart.domain.product.ProductRepository;

@Service
public class NewProductCommand implements Command<NewProductResponse, NewProductRequest> {

	private ProductFactory factory;
	private ProductRepository repository;
	private ModelMapper mapper;
	
	public NewProductCommand(final ProductFactory factory, final ProductRepository repository, final ModelMapper mapper) {
		this.factory = factory;
		this.repository = repository;
		this.mapper = mapper;
	}
	
	@Override
	public NewProductResponse execute(final NewProductRequest request) {
		final Product product = factory.createProduct(request.getCode(), request.getDescription());
		final Product newProduct = repository.save(product);
		return mapper.map(newProduct, NewProductResponse.class);
	}
}
