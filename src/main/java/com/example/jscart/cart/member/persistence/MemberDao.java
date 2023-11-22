package com.example.jscart.cart.member.persistence;

import com.example.jscart.cart.member.domain.entity.Member;
import com.example.jscart.cart.member.domain.repository.MemberRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDao implements MemberRepository {

  NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  public MemberDao(DataSource dataSource) {
    namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
  }

  @Override
  public List<Member> findAll() {
    return namedParameterJdbcTemplate.query(
        "select * from member", new MemberRowMapper());
  }

  @Override
  public Member findById(Long id) {
    try {
      return namedParameterJdbcTemplate.queryForObject(
          "select * from member where id = :id",
          new MapSqlParameterSource("id", id),
          new MemberRowMapper());
    } catch (IncorrectResultSizeDataAccessException e) {
      throw new IllegalArgumentException("해당 id를 가진 회원이 존재하지 않습니다.");
    }
  }

  @Override
  public Member findByEmailAndPassword(String email, String password) {
    Map<String, Object> parameters = new HashMap<>();
    parameters.put("email",email);
    parameters.put("password", password);

    try {
      return namedParameterJdbcTemplate.queryForObject(
          "select * from member where email= :email and password = :password",
          parameters, new MemberRowMapper());
    } catch (IncorrectResultSizeDataAccessException e) {
      throw new IllegalArgumentException("email 또는 password를 확인해주세요");
    }
  }
}
