package dk.kea.stud.fourplayers.restaurantstore.product;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRepository extends CrudRepository<Price, Integer> {
}
