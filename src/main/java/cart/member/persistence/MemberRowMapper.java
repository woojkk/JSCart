package cart.member.persistence;

import cart.member.domain.entity.Member;
import cart.member.domain.vo.MemberId;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class MemberRowMapper implements RowMapper<Member> {

  @Override
  public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
    return Member.builder()
        .id(new MemberId(rs.getLong("id")))
        .email(rs.getString("email"))
        .password(rs.getString("password"))
        .build();
  }
}
