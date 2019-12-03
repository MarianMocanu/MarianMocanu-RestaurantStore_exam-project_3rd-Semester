package dk.kea.stud.fourplayers.restaurantstore.product;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Integer> {
  public List<Category> findAll();

}
