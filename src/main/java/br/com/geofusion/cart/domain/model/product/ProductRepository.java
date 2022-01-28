package br.com.geofusion.cart.domain.model.product;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	boolean existsByCode(final Long code);
	
	@Query("select p from Product p where p.code = :code")
	Optional<Product> findByCode(final Long code);
}
