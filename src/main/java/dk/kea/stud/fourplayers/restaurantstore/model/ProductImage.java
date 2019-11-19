package dk.kea.stud.fourplayers.restaurantstore.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "product_images")
@Embeddable
public class ProductImage extends BaseEntity {
  @Column(name = "url")
  private String url;

  public ProductImage() {
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }
}
