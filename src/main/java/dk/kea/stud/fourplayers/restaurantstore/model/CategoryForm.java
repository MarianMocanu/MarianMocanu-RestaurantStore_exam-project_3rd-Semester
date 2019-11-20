package dk.kea.stud.fourplayers.restaurantstore.model;


import java.util.List;

public class CategoryForm {
  private List<Category> categories;

  public CategoryForm(List<Category> categories) {
    this.categories = categories;
  }

  public List<Category> getCategories() {
    return categories;
  }

  public void setCategories(List<Category> categories) {
    this.categories = categories;
  }
}
