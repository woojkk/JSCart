package com.example.jscart.cart.product.domain.repository;

import com.example.jscart.cart.product.domain.entity.Product;
import java.util.List;

public interface ProductRepository {
  Product insert(Product product);
  Product findById(Long id);
  List<Product> findAll();
  Product updateProduct(Long id, Product product);
  void delete(Long id);
}
