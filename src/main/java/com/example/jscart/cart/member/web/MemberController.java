package com.example.jscart.cart.member.web;

import com.example.jscart.cart.member.domain.dto.MemberDto;
import com.example.jscart.cart.member.domain.service.MemberService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class MemberController {
  private final MemberService memberService;

  @GetMapping("/members")
  @ResponseBody
  public List<MemberDto> getAllMembers() {
    return memberService.getAllMember();
  }

  @GetMapping("/settings")
  public String showSettings(Model model) {
    model.addAttribute("members", memberService.getAllMember());

    return "settings";
  }
}
