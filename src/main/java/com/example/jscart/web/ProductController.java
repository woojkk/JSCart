package com.example.jscart.web;

import com.example.jscart.domain.product.dto.ProductDto;
import com.example.jscart.domain.product.service.ProductService;
import com.example.jscart.web.dto.CreateProduct;
import com.example.jscart.web.dto.ProductInfo;
import com.example.jscart.web.dto.UpdateProduct;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequiredArgsConstructor
public class ProductController {

  private final ProductService productService;

  @PostMapping("/products")
  public CreateProduct.Response createProduct(@Valid @RequestBody CreateProduct.Request request) {
    return CreateProduct.Response.from(productService.createProduct(
        request.getName(), request.getPrice(), request.getImage()));
  }

  @GetMapping("products/{id}")
  public ProductInfo readProduct(@PathVariable Long id) {
    return ProductInfo.from(productService.getProduct(id));
  }

  @GetMapping("/products")
  public List<ProductInfo> readProducts() {
    return productService.getAllProduct()
        .stream().map(ProductInfo::from)
        .collect(Collectors.toList());
  }

  @PostMapping("/products/{id}")
  public UpdateProduct.Response updateProduct(@PathVariable Long id,
      @Valid @RequestBody UpdateProduct.Request request) {
    return UpdateProduct.Response.from(
        productService.updateProduct(id, ProductDto.builder()
            .name(request.getName())
            .price(request.getPrice())
            .image(request.getImage())
            .build()
        ));
  }

  @PostMapping("/products/delete/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteProduct(@PathVariable Long id) {
    productService.deleteProduct(id);
  }

  @GetMapping("/admin")
  public ModelAndView showAdmin() {
    return new ModelAndView(
        "admin", "products", readProducts());
  }

  @GetMapping("/")
  public ModelAndView showIndex() {
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.addObject("products", readProducts());
    modelAndView.setViewName("index");

    return modelAndView;
  }
}
