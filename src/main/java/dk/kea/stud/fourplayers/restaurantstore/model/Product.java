package dk.kea.stud.fourplayers.restaurantstore.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "products")
public class Product extends BaseEntity {
  @Column(name = "name")
  private String name;

  @Column(name = "description")
  private String description;

  @OneToMany(targetEntity = dk.kea.stud.fourplayers.restaurantstore.model.Price.class,
      cascade = CascadeType.ALL)
  private List<Price> prices;

  @OneToOne(targetEntity = dk.kea.stud.fourplayers.restaurantstore.model.Category.class)
  @JoinColumn(name = "category_id")
  private Category category;

  @OneToMany(targetEntity = dk.kea.stud.fourplayers.restaurantstore.model.ProductImage.class,
      cascade = CascadeType.ALL)
  private List<ProductImage> images;

  public Product() {
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public List<Price> getPrices() {
    if (prices == null) {
      prices = new ArrayList<>();
    }
    return prices;
  }

  public void setPrices(List<Price> prices) {
    this.prices = prices;
  }

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

  public List<ProductImage> getImages() {
    if (images == null) {
      images = new ArrayList<>();
    }
    return images;
  }

  public void setImages(List<ProductImage> images) {
    this.images = images;
  }

  public void addPrice(Price price) {
    if (this.prices == null) {
      this.prices = new ArrayList<>();
    }
    this.prices.add(price);
  }

  public void addImage(ProductImage image) {
    if (this.images == null) {
      this.images = new ArrayList<>();
    }
    this.images.add(image);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Product)) return false;
    Product product = (Product) o;
    return this.getId().intValue() == product.getId().intValue();
  }

  @Override
  public int hashCode() {
    return Objects.hash(name);
  }

  @Override
  public String toString() {
    return "Product ID: " + this.getId() + ", name: " + this.name;
  }
}
