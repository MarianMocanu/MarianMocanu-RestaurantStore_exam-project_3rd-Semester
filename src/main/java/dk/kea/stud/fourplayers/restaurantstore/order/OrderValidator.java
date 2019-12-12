package dk.kea.stud.fourplayers.restaurantstore.order;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class OrderValidator implements Validator {
  @Override
  public boolean supports(Class<?> aClass) {
    return Order.class.isAssignableFrom(aClass);
  }

  @Override
  public void validate(Object o, Errors errors) {
    Order order = (Order) o;

    if (!StringUtils.hasLength(order.getCompanyName())) {
      errors.rejectValue("companyName", "req", "Company name is required");
    }

    if (!StringUtils.hasLength(order.getRecipientName())) {
      errors.rejectValue("recipientName", "req", "Recipient name is required");
    }

    if (!StringUtils.hasLength(order.getCVR())) {
      errors.rejectValue("CVR", "req", "CVR number is required");
    }

    if (!StringUtils.hasLength(order.getDeliveryAddress())) {
      errors.rejectValue("deliveryAddress", "req", "Delivery address is required");
    }

    if (!StringUtils.hasLength(order.getZipCode())) {
      errors.rejectValue("zipCode", "req", "Zip code is required");
    }

    if (!StringUtils.hasLength(order.getPhoneNo())) {
      errors.rejectValue("phoneNo", "req", "Phone number is required");
    }
  }
}
