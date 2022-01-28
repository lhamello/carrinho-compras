package br.com.geofusion.cart.domain.model.price;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {

	@Query("select p from Price p where p.productCode = :productCode and p.active = true")
	Optional<Price> findActive(final Long productCode);
	
	@Query("Select p FROM Price p where p.activationDate >= :activationDate")
	List<Price> findEqualOrAfter(final LocalDateTime activationDate);
}
