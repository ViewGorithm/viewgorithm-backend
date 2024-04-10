package vigo.com.viewgorithm.post.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import vigo.com.viewgorithm.algorithm.domain.AlgorithmCategory;
import vigo.com.viewgorithm.user.domain.User;

import java.util.Date;
import java.util.List;

@Getter
@Entity
@Setter
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


    @OneToMany
    @JoinColumn(name = "user_pk", referencedColumnName = "회원 고유번호", columnDefinition = "int")
    private List<User> user;

}
