package cart.product.persistence;

import cart.product.domain.vo.ProductId;
import cart.product.domain.entity.Product;
import cart.product.domain.vo.ImagePath;
import cart.product.domain.vo.Price;
import cart.product.domain.vo.ProductName;
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
