package dk.kea.stud.fourplayers.restaurantstore.order;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Order, Integer> {
    List<Order> findAllByStatusOrderByOrderTimestampDesc(Order.Status status);

    List<Order> findAllByStatusNotLikeOrderByOrderTimestampDesc(Order.Status status);

}
