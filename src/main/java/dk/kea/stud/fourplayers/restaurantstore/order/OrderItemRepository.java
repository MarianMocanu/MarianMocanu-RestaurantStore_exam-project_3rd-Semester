package dk.kea.stud.fourplayers.restaurantstore.order;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface OrderItemRepository extends CrudRepository<OrderItem, Integer> {
    @Query("SELECT orderitem.productName, SUM(quantity) AS quantity FROM OrderItem orderitem GROUP BY product_id ORDER BY quantity DESC")
    @Transactional(readOnly = true)
    Collection<List<String>> findBestSellingProducts();

    @Query("SELECT orderitem.categoryName, SUM(quantity) AS quantity FROM OrderItem orderitem GROUP BY category_id ORDER BY quantity DESC")
    @Transactional(readOnly = true)
    Collection<List<String>> findBestSellingCategories();



}
