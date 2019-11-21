package dk.kea.stud.fourplayers.restaurantstore.order;

import dk.kea.stud.fourplayers.restaurantstore.model.Product;
import dk.kea.stud.fourplayers.restaurantstore.model.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

      theBasket.addProduct(product.get(), quantity);
    }
    return "redirect:/shop";
  }

  @GetMapping("/basket")
  @ResponseBody
  public Map<Product, Integer> seeBasket(@SessionAttribute("basket") Basket basket) {
    return basket.getProductsInBasket();
  }
}
