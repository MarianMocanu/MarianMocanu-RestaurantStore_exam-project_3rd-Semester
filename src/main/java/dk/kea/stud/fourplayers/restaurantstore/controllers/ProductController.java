package dk.kea.stud.fourplayers.restaurantstore.controllers;

import dk.kea.stud.fourplayers.restaurantstore.model.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Controller
public class ProductController {
  private final CategoryRepository categories;
  private final ProductRepository products;
  private final String ADD_OR_UPDATE_PRODUCT = "products/addOrUpdateProduct";
  private final PriceRepository prices;

  public ProductController(CategoryRepository categoryRepo, ProductRepository productRepo,
                           PriceRepository priceRepo) {
    this.categories = categoryRepo;
    this.products = productRepo;
    this.prices = priceRepo;
  }

  @ModelAttribute("allCategories")
  public List<Category> populateCategories() {
    return categories.findAll();
  }

  @GetMapping("/list_resp")
  @ResponseBody
  public List<Product> testProducts() {
    return products.findAll();
  }

  @GetMapping("/list")
  public String viewProducts(Model model, @RequestParam(name = "cat", required = false) Integer categoryId) {
    if (categoryId == null) {
      model.addAttribute("products", products.findAll());
    } else {
      model.addAttribute("products", products.findProductsByCategoryId(categoryId));
    }
    return "products/viewProducts";
  }

  @GetMapping("/addProduct")
  public String addProduct(Model model) {
    model.addAttribute("product", new Product());
    model.addAttribute("newPrice", new Price());

    return ADD_OR_UPDATE_PRODUCT;
  }

  @PostMapping("/addProduct")
  public String saveNewProduct(@ModelAttribute Product product, @ModelAttribute Price newPrice,
                               BindingResult result, Model model) {
    if (result.hasErrors()) {
      model.addAttribute("product", product);
      model.addAttribute("price", newPrice);
      return ADD_OR_UPDATE_PRODUCT;
    } else {
      if (newPrice.getQuantity() > 0 && newPrice.getPrice() > 0) {
        product.addPrice(newPrice);
      }
      products.save(product);
      return "redirect:/list";
    }
  }

  @GetMapping("/editProduct/{productId}")
  public String editProduct(@PathVariable("productId") int productId, Model model) {
    Product product = products.findById(productId).get();
    product.getPrices().sort(Price::compareTo);
    model.addAttribute("product", product);
    model.addAttribute("newPrice", new Price());

    return ADD_OR_UPDATE_PRODUCT;
  }

  @PostMapping("/editProduct/{productId}")
  public String saveEditedProduct(@PathVariable("productId") int productId,
                                   Product product, Price newPrice, Model model,
                                  BindingResult result) {
      if (result.hasErrors()) {
          model.addAttribute("product", product);
          model.addAttribute("price", newPrice);

          return ADD_OR_UPDATE_PRODUCT;
      } else {
          if (newPrice.getQuantity() > 0 && newPrice.getPrice() > 0) {
              product.addPrice(newPrice);
          }
          product.setId(productId);
          products.save(product);

          return "redirect:/list";
      }
  }

  @GetMapping("/delete/{product_id}")
  public String deleteProduct(@PathVariable(name = "product_id", required = true) int id) {
    products.deleteById(id);
    return "redirect:/list";
  }

  @GetMapping("/deletePrice/{productId}/{priceId}")
  public String deletePrice(@PathVariable("priceId") int priceId,
                            @PathVariable("productId") int productId) {
    prices.deleteById(priceId);
    return "redirect:/editProduct/" + productId;
  }
}
