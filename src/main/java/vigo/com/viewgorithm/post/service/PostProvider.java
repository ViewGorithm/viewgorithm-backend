package vigo.com.viewgorithm.post.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vigo.com.viewgorithm.post.domain.Post;
import vigo.com.viewgorithm.post.dto.PostDto;
import vigo.com.viewgorithm.post.repository.PostRepository;
import vigo.com.viewgorithm.user.domain.User;
import vigo.com.viewgorithm.user.repository.UserRepository; // 예시로 사용한 UserRepository 클래스
@Service
@RequiredArgsConstructor
public class PostProvider {

    private final PostRepository postRepository; // UserRepository 주입
    private final UserRepository userRepository; // UserRepository 주입


    public String writePost(PostDto postDto) {
        User user = userRepository.findById(Long.valueOf(postDto.getUserPk()))
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

        return "게시글 등록 완료";
    }

}
