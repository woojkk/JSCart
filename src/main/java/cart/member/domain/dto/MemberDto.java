package cart.member.domain.dto;

import cart.member.domain.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class MemberDto {
  private final Long id;
  private final String email;
  private final String password;

  public static MemberDto from(Member member) {
    return MemberDto.builder()
        .id(member.getId())
        .email(member.getEmail())
        .password(member.getPassword())
        .build();
  }
}
