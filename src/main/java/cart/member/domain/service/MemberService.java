package cart.member.domain.service;

import cart.member.domain.dto.MemberDto;
import cart.member.domain.repository.MemberRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
  private final MemberRepository memberRepository;

  public List<MemberDto> getAllMember() {
    return memberRepository.findAll().stream()
        .map(MemberDto::from)
        .collect(Collectors.toList());
  }

  public MemberDto getMember(String email, String password) {
    return MemberDto.from(
        memberRepository.findByEmailAndPassword(email, password));
  }
}
