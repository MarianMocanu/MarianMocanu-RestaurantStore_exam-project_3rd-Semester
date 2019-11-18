package dk.kea.stud.fourplayers.restaurantstore.controllers;

import dk.kea.stud.fourplayers.restaurantstore.model.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProductController {
  private final CategoryRepository categories;
  private final ProductRepository products;
  private final String ADD_OR_UPDATE_PRODUCT = "products/addOrUpdateProduct";

  public ProductController(CategoryRepository categoryRepo, ProductRepository productRepo) {
    this.categories = categoryRepo;
    this.products = productRepo;
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
  public String viewProducts(Model model, @RequestParam(name = "cat", required = false) Integer categoryId,
                             @RequestParam(value = "input", required = false) String input) {
    if(input!=null){
      model.addAttribute("products", products.findProductBySearchInput(input));
    }
    else {
      if (categoryId == null) {
        model.addAttribute("products", products.findAll());
      } else {
        model.addAttribute("products", products.findProductsByCategoryId(categoryId));
      }
    }
    return "products/viewProducts";
  }

  @GetMapping("/search")
  public String search(@RequestParam(name = "search") String search){
    return "redirect:/list?input=" + search;
  }
  @GetMapping("/add")
  public String addProduct(Model model) {
    model.addAttribute("unitPrice", new Price());
    model.addAttribute("product", new Product());

    return ADD_OR_UPDATE_PRODUCT;
  }

  @PostMapping("/add")
  public String saveProduct(Product product, BindingResult result, Model model) {
    if (result.hasErrors()) {
      model.addAttribute("product", product);
      return ADD_OR_UPDATE_PRODUCT;
    } else {
      products.save(product);
      return "redirect:/list";
    }
  }

  @GetMapping("/delete/{product_id}")
  public String deleteProduct(@PathVariable(name = "product_id", required = true) int id) {
    products.deleteById(id);
    return "redirect:/list";
  }
}
