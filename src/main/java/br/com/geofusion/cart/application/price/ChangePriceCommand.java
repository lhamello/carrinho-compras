package br.com.geofusion.cart.application.price;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import br.com.geofusion.cart.application.Command;
import br.com.geofusion.cart.domain.model.price.Price;
import br.com.geofusion.cart.domain.model.price.PriceFactory;
import br.com.geofusion.cart.domain.model.price.PriceRepository;
import br.com.geofusion.cart.domain.model.product.ProductRepository;

@Service
public class ChangePriceCommand implements Command<ChangePriceResponse, ChangePriceRequest> {

	private final PriceFactory factory;
	private final PriceRepository repository;
	private final ProductRepository productRepository;
	private final ModelMapper mapper;
	
	public ChangePriceCommand(final PriceFactory factory, final PriceRepository repository, final ProductRepository productRepository, final ModelMapper mapper) {
		this.factory = factory;
		this.repository = repository;
		this.productRepository = productRepository;
		this.mapper = mapper;
	}
	
	@Override
	public ChangePriceResponse execute(final ChangePriceRequest request) {
		if (!productRepository.existsByCode(request.getProductCode())) {
			throw new EntityNotFoundException("Produto não encontrado.");
		}
		
		final Price newPrice = factory.createPrice(request.getProductCode(), request.getPrice(), request.getActivationDate());
		final Optional<Price> optionalActualPrice = repository.findActive(request.getProductCode());
		
		if (optionalActualPrice.isPresent()) {
			final Price actualPrice = optionalActualPrice.get();
			actualPrice.inativate();
			repository.save(actualPrice);
		}
		
		final Price persisted = repository.save(newPrice);
		
		return mapper.map(persisted, ChangePriceResponse.class);
	}
}
