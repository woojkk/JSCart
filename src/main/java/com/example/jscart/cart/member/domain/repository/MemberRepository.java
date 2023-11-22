package com.example.jscart.cart.member.domain.repository;

import com.example.jscart.cart.member.domain.entity.Member;
import java.util.List;

public interface MemberRepository {
  List<Member> findAll();
  Member findById(Long id);
  Member findByEmailAndPassword(String email, String password);
}
