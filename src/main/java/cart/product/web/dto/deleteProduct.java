package cart.product.web.dto;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class deleteProduct {

  @Getter
  @AllArgsConstructor
  @Builder
  public static class Request {
    @NotNull
    private Long id;
  }

}
