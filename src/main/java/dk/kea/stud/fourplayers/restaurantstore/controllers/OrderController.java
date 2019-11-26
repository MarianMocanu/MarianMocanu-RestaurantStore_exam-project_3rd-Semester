package dk.kea.stud.fourplayers.restaurantstore.controllers;

import dk.kea.stud.fourplayers.restaurantstore.order.Order;
import dk.kea.stud.fourplayers.restaurantstore.order.OrderRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class OrderController {
  private final OrderRepository orders;

  private final String ORDERS = "order/orders";

  public OrderController(OrderRepository orders) {
    this.orders = orders;
  }

  @GetMapping("admin/order/viewAll")
  public String viewAllOrders(Model model) {
    model.addAttribute("orders", orders.findAllByOrderByOrderTimestampDesc());

    return ORDERS;
  }

  
}
