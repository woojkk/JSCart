package cart.cart.domain.service;

import cart.cart.domain.dto.CartDto;
import cart.cart.domain.entity.Cart;
import cart.cart.domain.repository.CartRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CartService {
  private final CartRepository cartRepository;

  public List<CartDto> getCartsByMemberId(Long memberId) {
    return cartRepository.findByMemberId(memberId).stream()
        .map(CartDto::from)
        .collect(Collectors.toList());
  }

  @Transactional
  public Long addCart(Long memberId, Long productId) {
    Optional<Cart> optionalCart = cartRepository.findByProductIdAndMemberId(memberId, productId);

    if (optionalCart.isEmpty()) {
      return cartRepository.insert(Cart.makeNewCart(memberId, productId));
    }

    Cart cart = optionalCart.get();
    cart.increaseCount(1);
    cartRepository.update(cart);

    return cart.getId();
  }

  @Transactional
  public void deleteCart(Long cartId) {
    Cart cart = cartRepository.findById(cartId)
        .orElseThrow(() -> new IllegalArgumentException("cartId에 해당하는 정보가 없습니다."));
    cartRepository.delete(cart);
  }
}
