package cart.product;


import static org.junit.jupiter.api.Assertions.assertAll;

import cart.product.domain.entity.Product;
import cart.product.domain.repository.ProductRepository;
import cart.product.web.dto.CreateProduct.Request;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.jdbc.SqlConfig.TransactionMode;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Sql(value = {"/truncate.sql", "/data.sql"},
config = @SqlConfig(transactionMode = TransactionMode.ISOLATED))
class IntegrationTest {

  @LocalServerPort
  int port;

  @Autowired
  ProductRepository productRepository;

  @BeforeEach
  void setUp() {
    RestAssured.port = port;
  }

  @Test
  void 상품추가성공() {
    Request param = Request.builder()
        .name("제로콜라")
        .price(2000)
        .image("/test")
        .build();

    ExtractableResponse<Response> createProduct = RestAssured
        .given().log().all()
        .contentType(ContentType.JSON)
        .body(param)
        .when()
        .post("/products")
        .then()
        .statusCode(200)
        .extract();

    Product product = productRepository.findById(createProduct.jsonPath()
        .getLong("id"));

    assertAll(
        () -> Assertions.assertThat(product.getName())
            .isEqualTo(createProduct.jsonPath().get("name")),
        () -> Assertions.assertThat(product.getPrice())
            .isEqualTo(createProduct.jsonPath().get("price")),
        () -> Assertions.assertThat(product.getImage())
            .isEqualTo(createProduct.jsonPath().get("image"))
    );
  }
}