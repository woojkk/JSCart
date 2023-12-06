package cart.product;

import cart.product.domain.vo.Price;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class PriceTest {

  @Test
  void failPriceValueUnderZero() {
    int price = -1;

    Assertions.assertThatThrownBy(() -> new Price(price))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("가격은 0원 이하일 수 없습니다.");
  }

}
