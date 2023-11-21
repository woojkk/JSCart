package com.example.jscart.web.dto;

import com.example.jscart.domain.product.dto.ProductDto;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class UpdateProduct {

  @Getter
  @AllArgsConstructor
  @Builder
  public static class Request {
    @NotEmpty
    private String name;
    @NotNull
    @Min(0)
    private int price;
    @NotEmpty
    private String image;
  }

  @Getter
  @AllArgsConstructor
  @Builder
  public static class Response {
    private Long id;
    private String name;
    private int price;
    private String image;

    public static Response from(ProductDto productDto) {
      return Response.builder()
          .id(productDto.getId())
          .name(productDto.getName())
          .price(productDto.getPrice())
          .image(productDto.getImage())
          .build();
    }
  }
}
