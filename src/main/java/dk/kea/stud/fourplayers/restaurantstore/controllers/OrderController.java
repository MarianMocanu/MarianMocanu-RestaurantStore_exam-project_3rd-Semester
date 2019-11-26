package dk.kea.stud.fourplayers.restaurantstore.controllers;

import dk.kea.stud.fourplayers.restaurantstore.model.BusinessDetails;
import dk.kea.stud.fourplayers.restaurantstore.model.Product;
import dk.kea.stud.fourplayers.restaurantstore.model.ProductRepository;
import dk.kea.stud.fourplayers.restaurantstore.order.Basket;
import dk.kea.stud.fourplayers.restaurantstore.order.Order;
import dk.kea.stud.fourplayers.restaurantstore.order.OrderItem;
import dk.kea.stud.fourplayers.restaurantstore.order.OrderRepository;
import dk.kea.stud.fourplayers.restaurantstore.security.User;
import dk.kea.stud.fourplayers.restaurantstore.security.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionAttributeStore;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@SessionAttributes("basket")
public class OrderController {
  private final ProductRepository products;
  private final UserService users;
  private final OrderRepository orders;

  public OrderController(ProductRepository products, UserService users, OrderRepository orders) {
    this.products = products;
    this.users = users;
    this.orders = orders;
  }

  @GetMapping("/checkout")
  public String displayCheckout(Model model, @ModelAttribute Basket basket) {
    if (SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
      if (basket == null) {
        return "redirect:/shop";
      }

      Order order = processOrderFromBasket(basket);

      model.addAttribute("order", order);
      return "order/checkout";
    } else {
      return "redirect:/login";
    }
  }

  @PostMapping("/checkout")
  public String processOrder(@ModelAttribute Order order, @ModelAttribute Basket basket, SessionStatus session) {
    Order finalOrder = processOrderFromBasket(basket);
    finalOrder.setRecipientName(order.getRecipientName());
    finalOrder.setDeliveryAddress(order.getDeliveryAddress());
    finalOrder.setDeliveryTimestamp(order.getDeliveryTimestamp());
    finalOrder.setOrderTimestamp(LocalDateTime.now());
    orders.save(finalOrder);
    session.setComplete();
    return "redirect:/";
  }

  private Order processOrderFromBasket(Basket basket) {
    User currentUser = users.findUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
    List<OrderItem> orderedProducts = new ArrayList<>();
    int total = 0;
    for (Map.Entry<Integer, Integer> basketEntry : basket.getProductsInBasket().entrySet()) {
      Product product = products.findById(basketEntry.getKey()).get();
      OrderItem item = new OrderItem(product.getId(), product.getName(),
          product.getCategory().getId(), product.getCategory().getName(),
          product.getBestPriceForQuantity(basketEntry.getValue()), basketEntry.getValue());
      orderedProducts.add(item);
      total += item.getPrice() * item.getQuantity();
    }
    Order order = new Order();
    order.setTotal(total);
    order.setItemList(orderedProducts);
    order.setUser(currentUser);
    order.setStatus(Order.Status.PENDING);
    if (currentUser.getBusinessDetails() != null) {
      BusinessDetails details = currentUser.getBusinessDetails();
      order.setRecipientName(details.getFirstName() + " " + details.getLastName());
      order.setDeliveryAddress(details.getAddress());
    }
    return order;
  }
}
