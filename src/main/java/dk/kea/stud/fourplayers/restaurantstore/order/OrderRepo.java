package dk.kea.stud.fourplayers.restaurantstore.order;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepo extends CrudRepository<Order, Integer> {
    List<Order> findAllOrderByStatusAndOrderTimestamp();
}
