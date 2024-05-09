package vigo.com.viewgorithm.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import vigo.com.viewgorithm.user.domain.Authority;
import vigo.com.viewgorithm.user.domain.Member;
import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberRequestDto {

  private String userId;
  private String password;
  private String name;
  private Date birth;
  private String sex;
  private String email;

  public Member toMember(PasswordEncoder passwordEncoder) {
    return Member.builder()
            .userId(userId)
            .password(passwordEncoder.encode(password))
            .authority(Authority.ROLE_USER)
            .build();
  }

  public Member toAdmin(PasswordEncoder passwordEncoder) {
    return Member.builder()
            .userId(userId)
            .password(passwordEncoder.encode(password))
            .authority(Authority.ROLE_ADMIN)
            .build();
  }

  public UsernamePasswordAuthenticationToken toAuthentication() {
    return new UsernamePasswordAuthenticationToken(userId, password);
  }
}