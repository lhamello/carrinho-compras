package br.com.geofusion.cart.domain.model.product;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.geofusion.cart.domain.shared.ValidationError;


/**
 * Classe que representa um produto que pode ser adicionado como item ao
 * carrinho de compras.
 *
 * Importante: Dois produtos são considerados iguais quando ambos possuem o
 * mesmo código.
 */
@Entity
@Table(name = "product")
public class Product {

	@Id
	private Long code;
	private String description;

	private Product() {
		super();
		// necessário para hibernate
	}
	
	/**
	 * Construtor da classe Produto.
	 *
	 * @param code
	 * @param description
	 */
	public Product(Long code, String description) {
		this();
		this.code = code;
		this.description = description;
	}
	
	Optional<List<ValidationError>> validate() {
		final List<ValidationError> validationErrors = new LinkedList<>();
		
		if (Objects.isNull(code) || Long.valueOf("0").compareTo(code) >= 0) {
			validationErrors.add(ValidationError.create("Informe o código do produto."));
		}
		
		if (Objects.isNull(description) || description.isBlank()) {
			validationErrors.add(ValidationError.create("Informe a descrição do produto."));
		}
		
		if (!validationErrors.isEmpty()) {
			return Optional.of(validationErrors);
		}
		
		return Optional.empty();
	}

	/**
	 * Retorna o código da produto.
	 *
	 * @return Long
	 */
	public Long getCode() {
		return this.code;
	}

	/**
	 * Retorna a descrição do produto.
	 *
	 * @return String
	 */
	public String getDescription() {
		return this.description;
	}

	@Override
	public int hashCode() {
		return Objects.hash(code);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return Objects.equals(code, other.code);
	}
}