package cart.product.web.dto;

import cart.product.domain.dto.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class ProductInfo {
  private Long id;
  private String name;
  private String image;
  private int price;

  public static ProductInfo from(ProductDto productDto) {
    return ProductInfo.builder()
        .id(productDto.getId())
        .name(productDto.getName())
        .image(productDto.getImage())
        .price(productDto.getPrice())
        .build();
  }
}
