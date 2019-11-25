package dk.kea.stud.fourplayers.restaurantstore.model;

public class ProductForm {
  private Product product;
  private Price newPrice;
  private ProductImage newImage;

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  public Price getNewPrice() {
    return newPrice;
  }

  public void setNewPrice(Price newPrice) {
    this.newPrice = newPrice;
  }

  public ProductImage getNewImage() {
    return newImage;
  }

  public void setNewImage(ProductImage newImage) {
    this.newImage = newImage;
  }
}
