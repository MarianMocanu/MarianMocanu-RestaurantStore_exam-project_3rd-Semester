package dk.kea.stud.fourplayers.restaurantstore.product;

import dk.kea.stud.fourplayers.restaurantstore.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "product_prices")
@Embeddable
public class Price extends BaseEntity implements Comparable {
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

  @Override
  public int compareTo(Object o) {
    Price other = (Price) o;
    return this.quantity - other.getQuantity();
  }

  @Override
  public String toString() {
    return "Price{ id=" + super.getId() +
        "quantity=" + quantity +
        ", price=" + price +
        '}';
  }
}
