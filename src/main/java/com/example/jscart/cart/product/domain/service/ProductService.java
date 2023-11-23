package com.example.jscart.cart.product.domain.service;

import com.example.jscart.cart.product.domain.dto.ProductDto;
import com.example.jscart.cart.product.domain.entity.Product;
import com.example.jscart.cart.product.domain.repository.ProductRepository;
import com.example.jscart.cart.product.domain.vo.ImagePath;
import com.example.jscart.cart.product.domain.vo.Price;
import com.example.jscart.cart.product.domain.vo.ProductName;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductService {

  private final ProductRepository productRepository;

  @Transactional
  public ProductDto createProduct(String productName, int price, String imagePath) {
    return ProductDto.from(productRepository.insert(Product.builder()
        .name(new ProductName(productName))
        .price(new Price(price))
        .image(new ImagePath(imagePath))
        .build()));
  }

  public ProductDto getProduct(Long id) {
    return ProductDto.from(productRepository.findById(id));
  }

  public List<ProductDto> getAllProduct() {
    return productRepository.findAll().stream()
        .map(ProductDto::from)
        .collect(Collectors.toList());
  }

  @Transactional
  public ProductDto updateProduct(Long id, ProductDto productDto) {
    return ProductDto.from(
        productRepository.updateProduct(id,
            Product.builder()
                .name(new ProductName(productDto.getName()))
                .price(new Price(productDto.getPrice()))
                .image(new ImagePath(productDto.getImage()))
                .build()));
  }

  public void deleteProduct(Long id) {
    productRepository.delete(id);
  }
}
