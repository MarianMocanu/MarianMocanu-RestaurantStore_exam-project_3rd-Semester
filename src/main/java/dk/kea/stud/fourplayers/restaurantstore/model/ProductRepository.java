package dk.kea.stud.fourplayers.restaurantstore.model;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {
  List<Product> findAll();

  @Query("SELECT product FROM Product product WHERE product.category.id =:id")
  @Transactional(readOnly = true)
  Collection<Product> findProductsByCategoryId(@Param("id") Integer id);
}
