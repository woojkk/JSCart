package cart.cart.web;

import cart.auth.AuthPrincipal;
import cart.cart.domain.service.CartService;
import cart.cart.web.dto.CreateAuthRequest;
import cart.cart.web.dto.CreateCart;
import cart.cart.web.dto.ReadMemberCarts;
import cart.cart.web.dto.ReadMemberCarts.Response;
import cart.global.config.argumentResolver.AuthenticationPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequiredArgsConstructor
public class CartController {

  private final CartService cartService;

  @GetMapping("/cart")
  public ModelAndView showCart() {
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName("cart");

    return modelAndView;
  }

  @GetMapping("/carts")
  public Response readMemberCarts(@RequestParam Long memberId) {
    return ReadMemberCarts.Response.of(
        memberId, cartService.getCartsByMemberId(memberId));
  }

  @GetMapping(value = "/carts/auth")
  public ReadMemberCarts.Response readMemberCartsInAuth(
      @AuthenticationPrincipal AuthPrincipal authPrincipal) {
    return readMemberCarts(authPrincipal.getMemberId());
  }

  @PostMapping("/carts")
  public CreateCart.Response createCart(@RequestBody CreateCart.Request request) {
    return CreateCart.Response.of(
        cartService.addCart(request.getMemberId(), request.getProductId()));
  }

  @PostMapping("/carts/auth")
  public CreateCart.Response createCartInAuth(@RequestBody CreateAuthRequest request,
      @AuthenticationPrincipal AuthPrincipal authPrincipal) {
    return CreateCart.Response.of(
        cartService.addCart(authPrincipal.getMemberId(), request.getProductId()));
  }

  @DeleteMapping("/carts/{cartId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteCart(@PathVariable Long cartId) {
    cartService.deleteCart(cartId);
  }
}
