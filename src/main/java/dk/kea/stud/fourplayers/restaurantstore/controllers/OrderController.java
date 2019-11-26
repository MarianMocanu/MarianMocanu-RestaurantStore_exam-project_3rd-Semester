package dk.kea.stud.fourplayers.restaurantstore.controllers;

import dk.kea.stud.fourplayers.restaurantstore.order.Order;
import dk.kea.stud.fourplayers.restaurantstore.order.OrderRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class OrderController {
  private final OrderRepository orders;

  private final String ORDERS = "order/orders";
  private final String ORDER_DETAILS = "order/orderDetails";

  public OrderController(OrderRepository orders) {
    this.orders = orders;
  }

  @GetMapping("/admin/order/viewAll")
  public String viewAllOrders(Model model) {
    model.addAttribute("orders", orders.findAllByOrderByOrderTimestampDesc());

    return ORDERS;
  }

  @GetMapping("/admin/order/view/{orderId}")
  public String viewOrderDetails(@PathVariable("orderId") int orderId, Model model) {
    model.addAttribute("order", orders.findById(orderId).get());

    return ORDER_DETAILS;
  }

  @PostMapping("/admin/order/view/{orderId}")
  public String updateOrderStatus(@PathVariable("orderId") int orderId,
                                        @RequestParam("status") Order.Status status) {
    Order order = orders.findById(orderId).get();
    order.setStatus(status);
    orders.save(order);
    
    return "redirect:/admin/order/viewAll";
  }
}
