package dk.kea.stud.fourplayers.restaurantstore.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "product_prices")
public class Price extends BaseEntity {
  @Column(name = "quantity")
  private int quantity;
  @Column(name = "price")
  private int price;

  public Price() {
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }
}
