package com.example.jscart.cart.product.domain.entity;

import com.example.jscart.cart.product.domain.vo.ImagePath;
import com.example.jscart.cart.product.domain.vo.Price;
import com.example.jscart.cart.product.domain.vo.ProductId;
import com.example.jscart.cart.product.domain.vo.ProductName;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class Product {
  private ProductId id;
  private ProductName name;
  private ImagePath image;
  private Price price;
  private LocalDateTime createdAt;

  public Long getId() {
    return id.getId();
  }

  public void setId(Long id) {
    this.id = new ProductId(id);
  }

  public String getName() {
    return name.getName();
  }

  public String getImage() {
    return image.getPath();
  }

  public int getPrice() {
    return price.getPrice();
  }
}
