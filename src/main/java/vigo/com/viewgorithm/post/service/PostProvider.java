package vigo.com.viewgorithm.post.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vigo.com.viewgorithm.post.domain.Post;
import vigo.com.viewgorithm.post.dto.PostDto;
import vigo.com.viewgorithm.post.repository.PostRepository;
import vigo.com.viewgorithm.user.join.domain.User;
import vigo.com.viewgorithm.user.join.domain.repository.UserRepository; // 예시로 사용한 UserRepository 클래스

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostProvider {

    private final PostRepository postRepository; // UserRepository 주입
    private final UserRepository userRepository; // UserRepository 주입





    // 전체 게시글 조회
    public List<PostDto> getPostList() {
        List<Post> posts = postRepository.findAll();  // 데이터베이스에서 모든 Post를 가져옵니다.
        List<PostDto> postDtoList = new ArrayList<>();

        for (Post post : posts) {
            PostDto postDto = PostDto.builder()
                    .post_pk(post.getPost_pk())
                    .userPk(post.getUser().getUser_pk())
                    .title(post.getTitle())
                    .content(post.getContent())
                    .createdAt(post.getCreated_at())
                    .build();
            postDtoList.add(postDto);  // PostDto를 리스트에 추가
        }
        return postDtoList;
    }

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



    // 게시글 수정
    @Transactional
    public boolean updatePost(PostDto postDto){
        Long postId = postDto.getPost_pk();

        Optional<Post> postEntity = postRepository.findById(postId); // 엔티티 가져오기


        if(postEntity.isPresent()){ // 존재하는지 확인
            Post post = postEntity.get();

            if (postDto.getContent() != null){ // postDto
                post.setContent(postDto.getContent());
            }
            if(postDto.getTitle() != null){
                post.setTitle(postDto.getTitle());
            }

            if(Objects.equals(postDto.getTitle(), "") && Objects.equals(post.getContent(), "")){
                return false;  // Title의 값과
            }


            postRepository.save(post);
            return true; // 수정 성공
        }
        else{
            return false; // 수정 실패
        }
    }
}
