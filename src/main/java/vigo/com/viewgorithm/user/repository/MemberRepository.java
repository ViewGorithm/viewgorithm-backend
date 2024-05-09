package vigo.com.viewgorithm.user.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import vigo.com.viewgorithm.user.domain.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long> {
  Optional<Member> findByUserId(String userId);
  boolean existsByUserId(String userId);
}
