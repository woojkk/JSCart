package com.example.jscart.domain.product.repository;

import com.example.jscart.domain.product.entity.Product;
import java.util.List;

public interface ProductRepository {
  Product insert(Product product);
  Product findById(Long id);
  List<Product> findAll();
  Product updateProduct(Long id, Product product);
  void delete(Long id);
}
