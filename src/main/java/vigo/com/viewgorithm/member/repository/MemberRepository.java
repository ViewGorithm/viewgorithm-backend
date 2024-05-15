package vigo.com.viewgorithm.member.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import vigo.com.viewgorithm.member.domain.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long> {
  Optional<Member> findByUserId(String userId);
  boolean existsByUserId(String userId);
}
