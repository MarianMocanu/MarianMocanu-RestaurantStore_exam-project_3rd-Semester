package dk.kea.stud.fourplayers.restaurantstore.controllers;

import dk.kea.stud.fourplayers.restaurantstore.model.Category;
import dk.kea.stud.fourplayers.restaurantstore.model.CategoryForm;
import dk.kea.stud.fourplayers.restaurantstore.model.CategoryRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class CategoryController {
  private final CategoryRepository categoryRepo;

  private final String CATEGORY = "categories/category";

  public CategoryController(CategoryRepository categoryRepository) {
    this.categoryRepo = categoryRepository;
  }

  @GetMapping("/admin/category/view")
  public String showCategoryView(Model model) {
    CategoryForm form = new CategoryForm(categoryRepo.findAll());
    model.addAttribute("form", form);
    model.addAttribute("newCategory", new Category());

    return CATEGORY;
  }

  @PostMapping("/admin/category/save")
  public String saveEditedCategories(@ModelAttribute CategoryForm form,
                                     Model model, BindingResult result) {
    if (result.hasErrors()) {
      model.addAttribute("categories", form);
      return CATEGORY;
    } else {
      for (Category category : form.getCategories()) {
        if (category.getName() == null || category.getName().equals("")) {
          categoryRepo.delete(category);
        } else {
          categoryRepo.save(category);
        }
      }
    }

    return "redirect:/admin/category/view";
  }

  @PostMapping("/admin/category/add")
  public String saveCategory(@ModelAttribute Category newCategory, BindingResult result, Model model) {
    if (result.hasErrors()) {
      model.addAttribute("category", newCategory);
      return CATEGORY;
    } else {
      categoryRepo.save(newCategory);

      return "redirect:/admin/category/view";
    }
  }

}
