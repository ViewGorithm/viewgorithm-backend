package vigo.com.viewgorithm.member.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
@Entity
@Getter
@NoArgsConstructor
public class Member {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String userId;

  private String name;

  @Column(name = "created_at")
  @Temporal(TemporalType.TIMESTAMP)
  private Date createdAt;

  private String email;

  private String password;

  private String sex;
  //enum type을 엔티티 속성으로 쓰기 위한 어노테이션이다. 뒤에 (EnumType.String)은
  //enum의 이름을 db에 저장한다는 의미다. 참고로 (EnumType.ORDINAL)은 enum의 순서를
  //db에 저장한다. 전자의 경우, role_user와 role_admin이 db에 저장된다고 보면 된다.
  @Enumerated(EnumType.STRING)
  private Authority authority;

  @PrePersist
  protected void onCreate() {
    createdAt = new Date(); // 현재 시간 설정
  }

  @Builder
  public Member(String userId, String password, String name, String sex, String email,Authority authority) {
    this.userId = userId;
    this.password = password;
    this.authority = authority;
    this.email = email;
    this.name = name;
    this.sex = sex;
  }
}
