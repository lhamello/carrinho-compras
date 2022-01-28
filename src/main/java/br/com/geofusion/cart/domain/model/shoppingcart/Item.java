package br.com.geofusion.cart.domain.model.shoppingcart;

import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.geofusion.cart.domain.model.product.Product;

/**
 * Classe que representa um item no carrinho de compras.
 */
@Entity
@Table(name = "item_shopping_cart")
public class Item {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	private Product product;
	private BigDecimal unitPrice;
	private Integer quantity;
	private BigDecimal amount;
	
    /**
     * Construtor da classe Item.
     *
     * @param product
     * @param unitPrice
     * @param quantity
     */
    public Item(Product product, BigDecimal unitPrice, int quantity) {
    	this.product = product;
    	this.unitPrice = unitPrice;
    	this.quantity = quantity;
    	this.amount = this.calculateAmout();
    }

    public void update(final BigDecimal unitPrice, final int quantity) {
    	this.unitPrice = unitPrice;
    	this.quantity += quantity;
    	this.amount = this.calculateAmout();
    }
    
    private BigDecimal calculateAmout() {
    	return unitPrice.multiply(BigDecimal.valueOf(quantity));
    }
    
    /**
     * Retorna o produto.
     *
     * @return Produto
     */
    public Product getProduct() {
        return product;
    }

    /**
     * Retorna o valor unitário do item.
     *
     * @return BigDecimal
     */
    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    /**
     * Retorna a quantidade dos item.
     *
     * @return int
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Retorna o valor total do item.
     *
     * @return BigDecimal
     */
    public BigDecimal getAmount() {
        return amount;
    }

	@Override
	public int hashCode() {
		return Objects.hash(product);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		return Objects.equals(product, other.product);
	}
}

