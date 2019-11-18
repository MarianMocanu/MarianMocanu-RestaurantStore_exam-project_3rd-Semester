package dk.kea.stud.fourplayers.restaurantstore.controllers;

import dk.kea.stud.fourplayers.restaurantstore.model.Category;
import dk.kea.stud.fourplayers.restaurantstore.model.CategoryRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CategoryController {
    private final CategoryRepository categoriesRepo;

    private final String ADD_OR_UPDATE_CATEGORY = "categories/addOrUpdateCategory";

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoriesRepo = categoryRepository;
    }

    @GetMapping("/listCategories")
    @ResponseBody
    public List<Category> listCategories() {
        return categoriesRepo.findAll();
    }

    @GetMapping("viewCategory/{categoryId}")
    @ResponseBody
    public Category viewCategory(@PathVariable("categoryId") int categoryId) {
        return categoriesRepo.findById(categoryId).get();
    }

    @GetMapping("/newCategory")
    public String addCategory(Model model) {
        model.addAttribute("category", new Category());
        return ADD_OR_UPDATE_CATEGORY;
    }

    @GetMapping("/editCategory/{categoryId}")
    public String updateCategory(Model model, @PathVariable("categoryId") int categoryId) {
        model.addAttribute(categoriesRepo.findById(categoryId).get());
        return ADD_OR_UPDATE_CATEGORY;
    }

    @PostMapping("/saveCategory")
    public String saveCategory(@ModelAttribute Category category, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("category", category);
            return ADD_OR_UPDATE_CATEGORY;
        } else {
            categoriesRepo.save(category);
            return "redirect:/listCategories";
        }
    }

    @PostMapping("/deleteCategory")
    public String deleteCategory(@RequestParam("categoryId") String categoryId) {
        Category category = categoriesRepo.findById(Integer.parseInt(categoryId)).get();
        categoriesRepo.delete(category);
        return "/listCategories";
    }

}
