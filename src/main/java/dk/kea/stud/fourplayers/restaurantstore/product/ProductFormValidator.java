package dk.kea.stud.fourplayers.restaurantstore.product;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class ProductFormValidator implements Validator {
  @Override
  public boolean supports(Class<?> aClass) {
    return ProductForm.class.isAssignableFrom(aClass);
  }

  @Override
  public void validate(Object o, Errors errors) {
    ProductForm formData = (ProductForm) o;

    if (formData.getNewPrice().getQuantity() < 0 || formData.getNewPrice().getPrice() < 0) {
      errors.rejectValue("newPrice", "invalidValue", "Price and quantity must be greater than 0");
    }

    for (Price price : formData.getProduct().getPrices()) {
      if (formData.getNewPrice().getQuantity() == price.getQuantity()) {
        errors.rejectValue("newPrice", "notUnique", "Price already exists for quantity");
      }
    }
  }
}
