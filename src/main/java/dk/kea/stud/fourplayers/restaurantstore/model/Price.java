package dk.kea.stud.fourplayers.restaurantstore.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "prices")
public class Price extends BaseEntity {
  @Column(name = "quantity")
  private int quantity;

  public Price() {
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }
}
