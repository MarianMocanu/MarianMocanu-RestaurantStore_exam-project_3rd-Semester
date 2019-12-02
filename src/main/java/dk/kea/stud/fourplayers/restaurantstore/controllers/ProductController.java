package dk.kea.stud.fourplayers.restaurantstore.controllers;

import dk.kea.stud.fourplayers.restaurantstore.product.*;
import dk.kea.stud.fourplayers.restaurantstore.order.Basket;
import dk.kea.stud.fourplayers.restaurantstore.order.OrderItemRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@Controller
@SessionAttributes("basket")
public class ProductController {
  private final CategoryRepository categories;
  private final ProductRepository products;
  private final PriceRepository prices;
  private final ProductImageRepository images;
  private final String ADD_OR_UPDATE_PRODUCT = "products/addOrUpdateProduct";
  private final OrderItemRepository orderItems;

  public ProductController(CategoryRepository categoryRepo, ProductRepository productRepo,
                           PriceRepository priceRepo, ProductImageRepository imageRepo, OrderItemRepository orderItems) {
    this.categories = categoryRepo;
    this.products = productRepo;
    this.prices = priceRepo;
    this.images = imageRepo;
    this.orderItems = orderItems;
  }

  @ModelAttribute("allCategories")
  public List<Category> populateCategories() {
    return categories.findAll();
  }

  @ModelAttribute("basket")
  public Basket setUpBasket() {
    return new Basket();
  }

  @InitBinder("productForm")
  public void initProductFormBinder(WebDataBinder dataBinder) {
    dataBinder.setValidator(new ProductFormValidator());
  }

  @GetMapping(value = {"/shop", "/"})
  public String viewProducts(Model model,
                             @RequestParam(name = "cat", required = false) Integer categoryId,
                             @RequestParam(value = "search", required = false) String input) {
    if (input != null) {
      Collection<Product> productsCollection = new HashSet<>();
      //The \\W+ will match all non-alphabetic characters occurring one or more times
      String[] words = input.split("\\W+");
      for (String word : words) {
        productsCollection.addAll(products.findProductBySearchInput(word));
      }
      if (productsCollection.isEmpty()) {
        model.addAttribute("noProducts", "none");
      } else {
        model.addAttribute("products", productsCollection);
      }
    } else {
      if (categoryId == null) {
        model.addAttribute("products", products.findAll());
      } else {
        model.addAttribute("products", products.findProductsByCategoryId(categoryId));
      }
    }
    return "products/viewProducts";
  }

  @GetMapping("/admin/product/add")
  public String addProduct(Model model) {
    ProductForm formData = new ProductForm();
    formData.setProduct(new Product());
    Price newPrice = new Price();
    newPrice.setQuantity(1);
    formData.setNewPrice(newPrice);
    formData.setNewImage(new ProductImage());
    model.addAttribute("formData", formData);

    return ADD_OR_UPDATE_PRODUCT;
  }

  @PostMapping("/admin/product/add")
  public String saveNewProduct(@Valid ProductForm formData, BindingResult result, Model model) {
    if (result.hasErrors()) {
      model.addAttribute("formData", formData);

      return ADD_OR_UPDATE_PRODUCT;
    } else {
      if (formData.getNewPrice().getQuantity() > 0) {
        formData.getProduct().addPrice(formData.getNewPrice());
      }
      if (formData.getNewImage().getUrl() != null && !formData.getNewImage().getUrl().equals("")) {
        formData.getProduct().addImage(formData.getNewImage());
      }
      Product newProduct = products.save(formData.getProduct());
      return "redirect:/admin/product/edit/" + newProduct.getId();
    }
  }

  @GetMapping("/admin/product/edit/{productId}")
  public String editProduct(@PathVariable("productId") int productId, Model model) {
    ProductForm formData = new ProductForm();
    Optional<Product> product = products.findById(productId);
    if (!product.isPresent()) {
      return "redirect:/shop";
    } else {
      formData.setProduct(product.get());
      formData.setNewPrice(new Price());
      formData.setNewImage(new ProductImage());
      model.addAttribute("formData", formData);

      return ADD_OR_UPDATE_PRODUCT;
    }
  }

  @PostMapping("/admin/product/edit/{productId}")
  public String saveEditedProduct(@PathVariable("productId") int productId, @Valid  ProductForm formData,
                                  BindingResult result, Model model) {
    if (result.hasErrors()) {
      formData.getProduct().setId(productId);
      model.addAttribute("formData", formData);

      return ADD_OR_UPDATE_PRODUCT;
    } else {
      if (formData.getNewPrice().getQuantity() > 0 && formData.getNewPrice().getPrice() > 0) {
        formData.getProduct().addPrice(formData.getNewPrice());
      }
      if (formData.getNewImage().getUrl() != null && !formData.getNewImage().getUrl().equals("")) {
        formData.getProduct().addImage(formData.getNewImage());
      }
      formData.getProduct().setId(productId);
      products.save(formData.getProduct());

      return "redirect:/admin/product/edit/{productId}";
    }
  }

  @GetMapping("/admin/product/delete/{product_id}")
  public String deleteProduct(@PathVariable(name = "product_id", required = true) int id) {
    products.deleteById(id);
    return "redirect:/";
  }

  @GetMapping("/admin/product/delete/{productId}/price/{priceId}")
  public String deletePrice(@PathVariable("priceId") int priceId,
                            @PathVariable("productId") int productId) {
    prices.deleteById(priceId);
    return "redirect:/admin/product/edit/" + productId;
  }

  @GetMapping("/admin/product/delete/{productId}/image/{imageId}")
  public String deleteImage(@PathVariable("productId") int productId,
                            @PathVariable("imageId") int imageId) {
    images.deleteById(imageId);
    return "redirect:/admin/product/edit/" + productId;
  }

  @GetMapping("/product/{id}")
  public String productDetails(@PathVariable(name = "id") int id, Model model){
    Optional<Product> product = products.findById(id);
    model.addAttribute("product", product.get());
    return "products/productDetail";
  }

  @GetMapping("/admin/statistics")
  public String statistics(){
    return "statistics/statisticsList";
  }

}
