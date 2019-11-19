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

  private final String CATEGORY = "categories/category";

  public CategoryController(CategoryRepository categoryRepository) {
    this.categoriesRepo = categoryRepository;
  }

  @GetMapping("/admin/category/view")
  public String showCategoryView(Model model) {
    model.addAttribute("categoriesList", categoriesRepo.findAll());

    return CATEGORY;
  }

  @PostMapping("/admin/categories/save")
  public String saveEditedCategories(@ModelAttribute("categories") List<Category> categories,
                                     Model model, BindingResult result) {
    if (result.hasErrors()) {
      model.addAttribute("categories", categories);
      return CATEGORY;
    } else {
      categoriesRepo.saveAll(categories);
    }

    return "redirect:/admin/category/view";
  }


  @GetMapping("/admin/category/add")
  public String addCategory(Model model) {
    model.addAttribute("category", new Category());
    return CATEGORY;
  }

  @GetMapping("/admin/category/edit/{categoryId}")
  public String updateCategory(Model model, @PathVariable("categoryId") int categoryId) {
    model.addAttribute(categoriesRepo.findById(categoryId).get());
    return CATEGORY;
  }

  @PostMapping("/admin/category/save")
  public String saveCategory(@ModelAttribute Category category, BindingResult result, Model model) {
    if (result.hasErrors()) {
      model.addAttribute("category", category);
      return CATEGORY;
    } else {
      categoriesRepo.save(category);
      return "redirect:/listCategories";
    }
  }

  @PostMapping("/deleteCategory")
  public String deleteCategory(@RequestParam("categoryId") String categoryId) {
    Category category = categoriesRepo.findById(Integer.parseInt(categoryId)).get();
    categoriesRepo.delete(category);
    return "redirect:/";
  }

}
