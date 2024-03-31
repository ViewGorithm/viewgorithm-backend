package vigo.com.viewgorithm.user.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;



@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="user_id")
    private String userId;

    @Column (name ="password")
    private String passWord;

    @Column(name="name")
    private String name;

    @Column(name="birth")
    private Date birth;

    @Column(name="sex")
    private String sex;

    @Column(name="email")
    private String email;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = new Date(); // 현재 시간 설정
    }

}
