package cart.cart.web.dto;

import cart.cart.domain.dto.CartDto;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class ReadMemberCarts {

  @Getter
  @AllArgsConstructor
  @Builder
  public static class Response {
    private Long memberId;
    private List<CartDto> carts;

    public static Response of(Long memberId, List<CartDto> carts) {
      return Response.builder()
          .memberId(memberId)
          .carts(carts)
          .build();
    }
  }
}
