package br.com.geofusion.cart.domain.model.shoppingcart;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

/**
 * Classe responsável pela criação e recuperação dos carrinhos de compras.
 */
@Component
public class ShoppingCartFactory {

	private ShoppingCartRepository repository;
	
	public ShoppingCartFactory(final ShoppingCartRepository repository) {
		this.repository = repository;
	}
	
    /**
     * Cria e retorna um novo carrinho de compras para o cliente passado como parâmetro.
     *
     * Caso já exista um carrinho de compras para o cliente passado como parâmetro, este carrinho deverá ser retornado.
     *
     * @param clientId
     * @return ShoppingCart
     */
    public ShoppingCart create(final String clientId) {
    	final Optional<ShoppingCart> existentCart = repository.findByClientId(clientId);
    	
    	if (existentCart.isPresent()) {
    		return existentCart.get();
    	}
    	
    	return new ShoppingCart(clientId);
    }

    /**
     * Retorna o valor do ticket médio no momento da chamada ao método.
     * O valor do ticket médio é a soma do valor total de todos os carrinhos de compra dividido
     * pela quantidade de carrinhos de compra.
     * O valor retornado deverá ser arredondado com duas casas decimais, seguindo a regra:
     * 0-4 deve ser arredondado para baixo e 5-9 deve ser arredondado para cima.
     *
     * @return BigDecimal
     */
    public BigDecimal getAverageTicketAmount() {
    	final List<ShoppingCart> shoppingCarts = repository.findAll();
    	
    	final BigDecimal sum = shoppingCarts.stream()
    		.map(ShoppingCart::getAmount)
    		.reduce(BigDecimal.ZERO, BigDecimal::add);
    	
    	if (shoppingCarts.isEmpty()) {
    		return BigDecimal.ZERO;
    	}
    	
    	return  sum.divide(BigDecimal.valueOf(shoppingCarts.size()), 2, RoundingMode.HALF_DOWN);
    }
    
    /**
     * Invalida um carrinho de compras quando o cliente faz um checkout ou sua sessão expirar.
     * Deve ser efetuada a remoção do carrinho do cliente passado como parâmetro da listagem de carrinhos de compras.
     *
     * @param clientId
     * @return Retorna um boolean, tendo o valor true caso o cliente passado como parämetro tenha um carrinho de compras e
     * e false caso o cliente não possua um carrinho.
     */
    public boolean invalidate(final String clientId) {
    	final Optional<ShoppingCart> existentCart = repository.findByClientId(clientId);
    	
    	if (existentCart.isPresent()) {
    		repository.delete(existentCart.get());
    		return true;
    	}
    	
        return false;
    }
}
