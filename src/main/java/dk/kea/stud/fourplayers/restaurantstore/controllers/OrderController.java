package dk.kea.stud.fourplayers.restaurantstore.controllers;

import dk.kea.stud.fourplayers.restaurantstore.order.*;
import dk.kea.stud.fourplayers.restaurantstore.product.Product;
import dk.kea.stud.fourplayers.restaurantstore.product.ProductRepository;
import dk.kea.stud.fourplayers.restaurantstore.security.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@SessionAttributes("basket")
public class OrderController {
  private final ProductRepository products;
  private final RoleRepository roleRepository;
  private final UserService users;
  private final OrderRepository orders;
  private final EmailController email;

  private final String ORDERS = "order/orders";
  private final String ORDER_DETAILS = "order/orderDetails";

  public OrderController(ProductRepository products, UserService users, OrderRepository orders,
                         RoleRepository roleRepository, EmailController email) {
    this.email = email;
    this.products = products;
    this.users = users;
    this.orders = orders;
    this.roleRepository = roleRepository;
  }

  @InitBinder("order")
  public void initProductFormBinder(WebDataBinder dataBinder) {
    dataBinder.setValidator(new OrderValidator());
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
  public String processOrder(@ModelAttribute @Valid Order order, BindingResult result,
                             @ModelAttribute Basket basket, SessionStatus session, Model model) {
    if (result.hasErrors()) {
      model.addAttribute("order", order);
      return "order/checkout";
    }
    Order finalOrder = processOrderFromBasket(basket);
    finalOrder.setRecipientName(order.getRecipientName());
    finalOrder.setPhoneNo(order.getPhoneNo());
    finalOrder.setCompanyName(order.getCompanyName());
    finalOrder.setCVR(order.getCVR());
    finalOrder.setDeliveryAddress(order.getDeliveryAddress());
    finalOrder.setZipCode(order.getZipCode());
    finalOrder.setDeliveryTimestamp(order.getDeliveryTimestamp());
    finalOrder.setOrderTimestamp(LocalDateTime.now());
    orders.save(finalOrder);
    session.setComplete();

    Role adminRole = roleRepository.findByRole("ADMIN");
    List<User> adminUsers = users.findUsersByRole(adminRole);
    for (User admin : adminUsers) {
      email.sendNewOrderMail(admin.getEmail(), finalOrder);
    }
    return "redirect:/";
  }

  private Order processOrderFromBasket(Basket basket) {
    User currentUser = users.findUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
    List<OrderItem> orderedProducts = new ArrayList<>();
    double total = 0;
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
      order.setCompanyName(details.getCompanyName());
      order.setCVR(details.getCvr());
      order.setDeliveryAddress(details.getAddress());
      order.setPhoneNo(details.getMobilePhone());
      order.setZipCode(details.getZipCode());
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
                                  @RequestParam(value = "discountAmount", required = false) Integer discount,
                                  @RequestParam(value = "discountType", required = false) int discountType) {
    Order order = orders.findById(orderId).get();
    order.setStatus(status);
    order.setProcessedTimestamp(LocalDateTime.now());
    if (discount != null) {
      if (discount > 0) {
        if (discountType == 0) {
          order.setDiscount(discount);
          order.setTotal(order.getTotal() - discount);
        }
        if (discountType == 1) {
          double processedDiscount = order.getTotal() * discount / 100.0;
          order.setDiscount(processedDiscount);
          order.setTotal(order.getTotal() - processedDiscount);
        }
      }
    }

    orders.save(order);

    email.sendOrderProcessedMail(order);

    if (status == Order.Status.ACCEPTED) {
      email.sendInvoiceMail(order);
    }

    return "redirect:/admin/order/viewAll";
  }

}
