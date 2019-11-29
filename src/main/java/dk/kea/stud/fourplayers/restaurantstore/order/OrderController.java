package dk.kea.stud.fourplayers.restaurantstore.order;

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
import java.util.*;

@Controller
@SessionAttributes("basket")
public class OrderController {
  private final ProductRepository products;
  private final UserService users;
  private final OrderRepository orders;
  private final OrderItemRepository orderItems;

  private final String ORDERS = "order/orders";
  private final String ORDER_DETAILS = "order/orderDetails";

  public OrderController(ProductRepository products, UserService users, OrderRepository orders, OrderItemRepository orderItems) {
    this.products = products;
    this.users = users;
    this.orders = orders;
    this.orderItems = orderItems;
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

  @GetMapping("/admin/order/viewAll")
  public String viewAllOrders(Model model) {
    model.addAttribute("pendingOrders",
        orders.findAllByStatusOrderByOrderTimestampDesc(Order.Status.PENDING));
    model.addAttribute("notPendingOrders",
        orders.findAllByStatusNotLikeOrderByOrderTimestampDesc(Order.Status.PENDING));

    return ORDERS;
  }

  @GetMapping("/admin/order/view/{orderId}")
  public String viewOrderDetails(@PathVariable("orderId") int orderId, Model model) {
    model.addAttribute("order", orders.findById(orderId).get());
    Integer discount = 0;
    model.addAttribute("discountAmount", discount);

    return ORDER_DETAILS;
  }

  @PostMapping("/admin/order/view/{orderId}")
  public String updateOrderStatus(@PathVariable("orderId") int orderId,
                                  @RequestParam("status") Order.Status status,
                                  @RequestParam(value = "discountAmount", required = false) Integer discount) {
    Order order = orders.findById(orderId).get();
    order.setStatus(status);
    order.setProcessedTimestamp(LocalDateTime.now());
    if (discount != null) {
      if (discount > 0) {
        order.setTotal(order.getTotal() - discount);
      }
    }

    orders.save(order);
    //TODO if status == ACCEPTED ? send accepted type of mail + invoice
    //TODO if status == DECLINED ? send declined type of mail

    return "redirect:/admin/order/viewAll";
  }

}
