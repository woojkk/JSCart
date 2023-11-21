package com.example.jscart.domain.product.entity;

import com.example.jscart.domain.product.vo.ImagePath;
import com.example.jscart.domain.product.vo.Price;
import com.example.jscart.domain.product.vo.ProductId;
import com.example.jscart.domain.product.vo.ProductName;
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
