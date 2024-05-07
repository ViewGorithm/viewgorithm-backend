package vigo.com.viewgorithm.comment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vigo.com.viewgorithm.comment.domain.Comment;
import vigo.com.viewgorithm.post.domain.Post;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {

    // 게시글 번호에 해당하는 댓글 리스트 얻기
    List<Comment> findByPost_Id(Long id);

}
