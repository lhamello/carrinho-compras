package br.com.geofusion.cart.domain.model.shoppingcart;


import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.geofusion.cart.domain.model.product.Product;

/**
 * Classe que representa o carrinho de compras de um cliente.
 */
@Entity
@Table(name = "shopping_cart")
public class ShoppingCart {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OneToMany(cascade = CascadeType.ALL)
    private List<Item> items;
	private BigDecimal amount;
	private String clientId;

	private ShoppingCart() {
		// utilizado pelo hibernate
	}
	
    ShoppingCart(final String clientId) {
    	this();
    	this.items = new LinkedList<>();
    	this.amount = BigDecimal.ZERO;
    	this.clientId = clientId;
    }
    
    /**
     * Permite a adição de um novo item no carrinho de compras.
     *
     * Caso o item já exista no carrinho para este mesmo produto, as seguintes regras deverão ser seguidas:
     * - A quantidade do item deverá ser a soma da quantidade atual com a quantidade passada como parâmetro.
     * - Se o valor unitário informado for diferente do valor unitário atual do item, o novo valor unitário do item deverá ser
     * o passado como parâmetro.
     *
     * Devem ser lançadas subclasses de RuntimeException caso não seja possível adicionar o item ao carrinho de compras.
     *
     * @param product
     * @param unitPrice
     * @param quantity
     */
    public void addItem(final Product product, final BigDecimal unitPrice, final int quantity) {
    	final Item item = new Item(product, unitPrice, quantity);
    	
    	if (items.contains(item)) {
    		final int index = items.indexOf(item);
    		final Item existent = items.get(index);
    		existent.update(unitPrice, quantity);
    	} else {
    		items.add(item);
    		amount = amount.add(item.getAmount());
    	}
    }

    /**
     * Permite a remoção do item que representa este produto do carrinho de compras.
     *
     * @param product
     * @return Retorna um boolean, tendo o valor true caso o produto exista no carrinho de compras e false
     * caso o produto não exista no carrinho.
     */
    public boolean removeItem(final Product product) {
    	return items.removeIf(i -> i.getProduct().equals(product));
    }

    /**
     * Permite a remoção do item de acordo com a posição.
     * Essa posição deve ser determinada pela ordem de inclusão do produto na
     * coleção, em que zero representa o primeiro item.
     *
     * @param itemIndex
     * @return Retorna um boolean, tendo o valor true caso o produto exista no carrinho de compras e false
     * caso o produto não exista no carrinho.
     */
    public boolean removeItem(final int itemIndex) {
        return Optional.ofNullable(items.remove(itemIndex))
        		.isPresent();
    }

    /**
     * Retorna o valor total do carrinho de compras, que deve ser a soma dos valores totais
     * de todos os itens que compõem o carrinho.
     *
     * @return BigDecimal
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * Retorna a lista de itens do carrinho de compras.
     *
     * @return items
     */
    public Collection<Item> getItems() {
        return Collections.unmodifiableCollection(items);
    }
    
    public String getClientId() {
		return clientId;
	}
    
    public Long getId() {
		return id;
	}
}