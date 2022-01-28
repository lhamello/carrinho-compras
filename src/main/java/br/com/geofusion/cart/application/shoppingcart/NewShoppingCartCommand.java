package br.com.geofusion.cart.application.shoppingcart;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import br.com.geofusion.cart.application.Command;
import br.com.geofusion.cart.domain.model.price.Price;
import br.com.geofusion.cart.domain.model.price.PriceRepository;
import br.com.geofusion.cart.domain.model.product.Product;
import br.com.geofusion.cart.domain.model.product.ProductRepository;
import br.com.geofusion.cart.domain.model.shoppingcart.ShoppingCart;
import br.com.geofusion.cart.domain.model.shoppingcart.ShoppingCartFactory;
import br.com.geofusion.cart.domain.model.shoppingcart.ShoppingCartRepository;

@Service
public class NewShoppingCartCommand implements Command<NewShoppingCartResponse, NewShoppingCartRequest> {

	private final ShoppingCartFactory factory;
	private final ShoppingCartRepository repository;
	private final ProductRepository productRepository;
	private final PriceRepository priceRepository;
	
	public NewShoppingCartCommand(final ShoppingCartFactory factory, final ShoppingCartRepository repository, final ProductRepository productRepository, final PriceRepository priceRepository) {
		this.factory = factory;
		this.repository = repository;
		this.productRepository = productRepository;
		this.priceRepository = priceRepository;
	}
	
	@Override
	public NewShoppingCartResponse execute(final NewShoppingCartRequest request) {
		final Product product = productRepository.findByCode(request.getProductCode()).orElseThrow(() -> new EntityNotFoundException("Produto não encontrado."));
		final Price price = priceRepository.findActive(request.getProductCode()).orElseThrow(() -> new EntityNotFoundException("Preço não encontrado."));
		
		final ShoppingCart shoppingCart = factory.create(request.getClientId());
		shoppingCart.addItem(product, price.getValue(), request.getQuantity());
		
		final ShoppingCart persisted = repository.save(shoppingCart);
		
		final NewShoppingCartResponse response = new NewShoppingCartResponse();
		response.setCartId(persisted.getId());
		response.setClientId(persisted.getClientId());
		
		return response;
	}
}
