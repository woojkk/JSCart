package com.example.jscart.persistence;

import com.example.jscart.domain.product.entity.Product;
import com.example.jscart.domain.product.vo.ImagePath;
import com.example.jscart.domain.product.vo.Price;
import com.example.jscart.domain.product.vo.ProductId;
import com.example.jscart.domain.product.vo.ProductName;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class ProductRowMapper implements RowMapper<Product> {

  @Override
  public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
    return Product.builder()
        .id(new ProductId(rs.getLong("id")))
        .name(new ProductName(rs.getString("name")))
        .price(new Price(rs.getInt("price")))
        .image(new ImagePath(rs.getString("image")))
        .createdAt(rs.getTimestamp("created_at").toLocalDateTime())
        .build();
  }
}
