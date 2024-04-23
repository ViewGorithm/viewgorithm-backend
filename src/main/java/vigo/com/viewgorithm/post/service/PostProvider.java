package vigo.com.viewgorithm.post.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vigo.com.viewgorithm.post.domain.Post;
import vigo.com.viewgorithm.post.dto.PostDto;
import vigo.com.viewgorithm.post.repository.PostRepository;
import vigo.com.viewgorithm.user.join.domain.User;
import vigo.com.viewgorithm.user.join.domain.repository.UserRepository; // 예시로 사용한 UserRepository 클래스
@Service
@RequiredArgsConstructor
public class PostProvider {

    private final PostRepository postRepository; // UserRepository 주입
    private final UserRepository userRepository; // UserRepository 주입

    // 게시글 작성 
    public void writePost(PostDto postDto) {
        User user = userRepository.findById((long) postDto.getUserPk())
                .orElse(null);

        if (user == null) {
            throw new IllegalArgumentException("유저를 찾을 수 없습니다.");
        }

        Post post = Post.builder()
                .user(user) // 유저 엔티티 설정
                .title(postDto.getTitle())
                .content(postDto.getContent())
                .created_at(postDto.getCreatedAt())
                .build();
        postRepository.save(post);

    }
    // 게시글 삭제
    public boolean deletePost(Long id) {
        if (postRepository.existsById(id)) { // 해당 아이디를 가진 게시글 존재시
            postRepository.deleteById(id); // 삭제
            return true; // 삭제 여부 반환
        } else {
            return false; // 해당 ID 게시글을 찾지 못함
        }
    }
}
