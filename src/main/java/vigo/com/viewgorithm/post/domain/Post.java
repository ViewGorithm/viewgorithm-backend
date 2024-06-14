package vigo.com.viewgorithm.post.domain;
import jakarta.persistence.*;
import lombok.*;
import vigo.com.viewgorithm.member.domain.Member;
import java.time.LocalDateTime;
import java.util.Date;
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "VARCHAR(100)")
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created_at;

    @ManyToOne // 게시글 다 : 유저 1
    @JoinColumn(name = "member_id", referencedColumnName = "id")
    private Member member;

}
