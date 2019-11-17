package dk.kea.stud.fourplayers.restaurantstore.controllers;


import dk.kea.stud.fourplayers.restaurantstore.model.Product;
import dk.kea.stud.fourplayers.restaurantstore.model.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@Controller
public class ProductDetailsController {
    private final ProductRepository productRepository;

    public ProductDetailsController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @ResponseBody
    @GetMapping("/product/{id}")
    public Optional<Product> productDetails(@PathVariable(name = "id") int id){
        return productRepository.findById(id);
    }
}
