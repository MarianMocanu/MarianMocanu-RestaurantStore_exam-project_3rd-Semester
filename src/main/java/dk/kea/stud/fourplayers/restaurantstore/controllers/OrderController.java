package dk.kea.stud.fourplayers.restaurantstore.controllers;

import dk.kea.stud.fourplayers.restaurantstore.order.OrderRepository;
import org.springframework.stereotype.Controller;

@Controller
public class OrderController {
  private final OrderRepository orders;

  public OrderController(OrderRepository orders) {
    this.orders = orders;
  }
}
