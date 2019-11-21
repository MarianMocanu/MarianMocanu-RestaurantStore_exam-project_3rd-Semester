package dk.kea.stud.fourplayers.restaurantstore.order;

import dk.kea.stud.fourplayers.restaurantstore.model.Product;

import java.util.LinkedHashMap;
import java.util.Map;

public class Basket {
  private Map<Product, Integer> productsInBasket;

  public Map<Product, Integer> getProductsInBasket() {
    if (productsInBasket == null) {
      productsInBasket = new LinkedHashMap<>();
    }
    return productsInBasket;
  }

  public void setProductsInBasket(Map<Product, Integer> productsInBasket) {
    this.productsInBasket = productsInBasket;
  }

  public void addProduct(Product product, Integer quantity) {
    if (getProductsInBasket().containsKey(product)) {
      productsInBasket.replace(product, productsInBasket.get(product) + quantity);
    } else {
      productsInBasket.put(product, quantity);
    }
  }

  public void setQuantityForProduct(Product product, Integer quantity) {
    if (getProductsInBasket().containsKey(product)) {
      productsInBasket.replace(product, productsInBasket.get(product) + quantity);
    }
  }

  public void removeProduct(Product product) {
    getProductsInBasket().remove(product);
  }

  public boolean isEmpty() {
    return getProductsInBasket().isEmpty();
  }

  public int getNumProducts() {
    return getProductsInBasket().size();
  }
}
