package dk.kea.stud.fourplayers.restaurantstore.product;

import dk.kea.stud.fourplayers.restaurantstore.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "product_images")
@Embeddable
public class ProductImage extends BaseEntity implements Comparable {
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

  @Override
  public String toString() {
    return "ProductImage{" +
        "id=" + getId() + ", " +
        "url='" + url + '\'' +
        '}';
  }

  @Override
  public int compareTo(Object o) {
    ProductImage other = (ProductImage) o;

    return this.getId().compareTo(other.getId());
  }
}
