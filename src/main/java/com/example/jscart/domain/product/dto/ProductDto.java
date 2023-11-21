package com.example.jscart.domain.product.dto;

import com.example.jscart.domain.product.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class ProductDto {
  private Long id;
  private String name;
  private String image;
  private int price;

  public static ProductDto from(Product product) {
    return ProductDto.builder()
        .id(product.getId())
        .name(product.getName())
        .image(product.getImage())
        .price(product.getPrice())
        .build();
  }
}
