package dk.kea.stud.fourplayers.restaurantstore.order;

import dk.kea.stud.fourplayers.restaurantstore.model.Product;
import dk.kea.stud.fourplayers.restaurantstore.model.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Controller
@SessionAttributes("basket")
public class BasketController {

  private final ProductRepository products;

  public BasketController(ProductRepository products) {
    this.products = products;
  }

  @PostMapping("/addToBasket")
  public String addToBasket(@SessionAttribute("basket") Basket theBasket,
                            @RequestParam(required = false, name = "quantity") Integer quantity,
                            @RequestParam(name = "productId") Integer productId) {
    Optional<Product> product = products.findById(productId);
    if (product.isPresent()) {
      if (quantity == null) {
        quantity = 1;
      }

      theBasket.addProduct(productId, quantity);
    }
    return "redirect:/shop";
  }

  @GetMapping("/basket")
  public String seeBasket(@SessionAttribute("basket") Basket basket, Model model) {
    Map<Integer, Product> productMap = new HashMap<>();
    for (Integer id : basket.getProductsInBasket().keySet()) {
      Optional<Product> product = products.findById(id);
      if (product.isPresent()) {
        productMap.put(id, product.get());
      }
    }
    int total = 0;
    for (Map.Entry<Integer, Integer> entry : basket.getProductsInBasket().entrySet()) {
      total += productMap.get(entry.getKey()).getBestPriceForQuantity(entry.getValue()) * entry.getValue();
    }
    model.addAttribute("productMap", productMap);
    model.addAttribute("basket", basket);
    model.addAttribute("total", total);
    return "order/viewAndUpdateBasket";
  }

  @PostMapping("/basket")
  public String updateBasket(@SessionAttribute("basket") Basket basket,
                             @ModelAttribute Basket submittedBasket) {
    basket.setProductsInBasket(submittedBasket.getProductsInBasket());
    return "redirect:/basket";
  }
}
