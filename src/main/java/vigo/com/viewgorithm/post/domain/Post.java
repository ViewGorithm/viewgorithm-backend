package vigo.com.viewgorithm.post.domain;

import jakarta.persistence.*;
import lombok.*;
import vigo.com.viewgorithm.user.join.domain.User;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long post_pk;

    @Column(columnDefinition = "VARCHAR(100)")
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime created_at;

    @PrePersist
    protected void onCreate() {
        created_at = LocalDateTime.now(); // created_at을 현재시간으로 설정
    }

    @ManyToOne // 게시글 다 : 유저 1
    @JoinColumn(name = "user_pk", referencedColumnName = "user_pk")
    private User user;

}
