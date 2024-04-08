package vigo.com.viewgorithm.user.repository;

import lombok.Builder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vigo.com.viewgorithm.user.domain.User;
import vigo.com.viewgorithm.user.dto.UserDto;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByName(String userName);

    // userId를 받아서 DB 테이블에서 회원을 조회하는 메소드 작성
    Optional<User> findByUserId(String userId);

}
