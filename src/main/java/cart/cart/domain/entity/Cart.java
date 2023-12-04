package cart.cart.domain.entity;

import cart.cart.domain.vo.CartId;
import cart.member.domain.entity.Member;
import cart.cart.domain.vo.Count;
import cart.product.domain.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class Cart {

  private final CartId id;
  private final Member member;
  private final Product product;
  private final Count count;

  public static Cart makeNewCart(Long memberId, Long productId) {
    return Cart.builder()
        .member(new Member(memberId))
        .product(new Product(productId))
        .count(new Count(1))
        .build();
  }

  public void increaseCount(int count) {
    this.count.increase(count);
  }

  public Long getId() {
    return id.getId();
  }

  public Long getMemberId() {
    return member.getId();
  }

  public Long getProductId() {
    return product.getId();
  }

  public String getProductName() {
    return product.getName();
  }

  public Integer getProductPrice() {
    return product.getPrice();
  }

  public String getProductImage() {
    return product.getImage();
  }

  public int getCount() {
    return count.getCount();
  }
}
