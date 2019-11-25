package dk.kea.stud.fourplayers.restaurantstore.order;

import java.util.LinkedHashMap;
import java.util.Map;

public class Basket {
  private Map<Integer, Integer> productsInBasket;

  public Map<Integer, Integer> getProductsInBasket() {
    if (productsInBasket == null) {
      productsInBasket = new LinkedHashMap<>();
    }
    return productsInBasket;
  }

  public void setProductsInBasket(Map<Integer, Integer> productsInBasket) {
    this.productsInBasket = productsInBasket;
  }

  public void addProduct(Integer productId, Integer quantity) {
    if (getProductsInBasket().containsKey(productId)) {
      productsInBasket.replace(productId, productsInBasket.get(productId) + quantity);
    } else {
      productsInBasket.put(productId, quantity);
    }
  }

  public void setQuantityForProduct(Integer productId, Integer quantity) {
    if (getProductsInBasket().containsKey(productId)) {
      productsInBasket.replace(productId, productsInBasket.get(productId) + quantity);
    }
  }

  public void removeProduct(Integer productId) {
    getProductsInBasket().remove(productId);
  }

  public boolean isEmpty() {
    return getProductsInBasket().isEmpty();
  }

  public int getNumProducts() {
    return getProductsInBasket().size();
  }
}
