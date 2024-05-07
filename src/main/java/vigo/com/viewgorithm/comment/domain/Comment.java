package vigo.com.viewgorithm.comment.domain;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Index;
import vigo.com.viewgorithm.post.domain.Post;
import vigo.com.viewgorithm.user.join.domain.User;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    // 댓글 : 게시글 = Many : One
    @ManyToOne
    @JoinColumn(name = "post_pk")
    private Post post;


    @ManyToOne  // 댓글 : 유저 = Many :One = 댓글 여러개는 유저가 쓸수 있다.
    @JoinColumn(name = "user_pk")  //  외래키이고 User 엔티티의 user_pk Join
    private User user;  // User 엔티티 참조


    @Column(columnDefinition = "VARCHAR(100)")
    private String content;


    @Column(name = "created_at")
    private LocalDateTime created_at;

    @PrePersist
    protected void onCreate() {
        created_at = LocalDateTime.now(); // created_at을 현재시간으로 설정
    }

}
