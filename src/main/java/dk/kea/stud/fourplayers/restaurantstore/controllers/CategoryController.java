package dk.kea.stud.fourplayers.restaurantstore.controllers;

import dk.kea.stud.fourplayers.restaurantstore.product.Category;
import dk.kea.stud.fourplayers.restaurantstore.product.CategoryForm;
import dk.kea.stud.fourplayers.restaurantstore.product.CategoryRepository;
import dk.kea.stud.fourplayers.restaurantstore.order.OrderItemRepository;
import dk.kea.stud.fourplayers.restaurantstore.product.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CategoryController {
  private final CategoryRepository categoryRepo;
  private final ProductRepository productRepo;

  private final String CATEGORY = "categories/category";

  public CategoryController(CategoryRepository categoryRepository, ProductRepository productRepo) {
    this.categoryRepo = categoryRepository;
    this.productRepo = productRepo;
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
      categoryRepo.saveAll(form.getCategories());
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
    }

    return "redirect:/admin/category/view";
  }


  @GetMapping("/admin/category/delete/{categoryId}")
  public String deleteCategory(@PathVariable("categoryId") int categoryId, RedirectAttributes redir) {
    if (productRepo.findProductsByCategoryId(categoryId).size() == 0) {
      categoryRepo.deleteById(categoryId);
    } else {
      redir.addFlashAttribute("error", "Cannot delete a category that has associated products.");
    }

    return "redirect:/admin/category/view";
  }

}
