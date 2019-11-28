package dk.kea.stud.fourplayers.restaurantstore.order;

import net.bytebuddy.dynamic.scaffold.MethodGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Order, Integer> {
    List<Order> findAllByStatusOrderByOrderTimestampDesc(Order.Status status);

    List<Order> findAllByStatusNotLikeOrderByOrderTimestampDesc(Order.Status status);

    @Query("SELECT order.user.email, SUM(total) AS total FROM Order order GROUP BY user_id ORDER BY total DESC")
    @Transactional(readOnly = true)
    Collection<List<String>> findBestCustomers();

}
