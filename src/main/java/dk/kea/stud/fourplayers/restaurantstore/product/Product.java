package dk.kea.stud.fourplayers.restaurantstore.product;

import dk.kea.stud.fourplayers.restaurantstore.BaseEntity;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "products")
public class Product extends BaseEntity {
  @Column(name = "name")
  private String name;

  @Column(name = "description")
  private String description;

  @OneToMany(targetEntity = dk.kea.stud.fourplayers.restaurantstore.product.Price.class,
      cascade = CascadeType.ALL)
  private List<Price> prices;

  @OneToOne(targetEntity = dk.kea.stud.fourplayers.restaurantstore.product.Category.class)
  @JoinColumn(name = "category_id")
  private Category category;

  @OneToMany(targetEntity = dk.kea.stud.fourplayers.restaurantstore.product.ProductImage.class,
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
    prices.sort(Price::compareTo);
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

  public Double getBestPriceForQuantity(int quantity) {
    Double result = 0.0;
    if (quantity < 1) {
      return result;
    }

    Map<Integer, Double> sortedPrices = new TreeMap<>();
    for (Price price : this.prices) {
      sortedPrices.put(price.getQuantity(), price.getPrice());
    }

    for (Map.Entry<Integer, Double> entry : sortedPrices.entrySet()) {
      if (entry.getKey() > quantity) {
        break;
      }
      result = entry.getValue();
    }

    return result;
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
