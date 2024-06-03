package vigo.com.viewgorithm.post.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vigo.com.viewgorithm.post.domain.Post;
import vigo.com.viewgorithm.post.dto.PostDto;
import vigo.com.viewgorithm.post.dto.PostUploadDto;
import vigo.com.viewgorithm.post.error.PostContentMissingException;
import vigo.com.viewgorithm.post.error.PostNotFoundException;
import vigo.com.viewgorithm.post.repository.PostRepository;
import vigo.com.viewgorithm.member.domain.Member;
import vigo.com.viewgorithm.member.repository.MemberRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostProvider {

    private final PostRepository postRepository; // UserRepository 주입
    private final MemberRepository memberRepository;// UserRepository 주입

    // 전체 게시글 조회
    public List<PostDto> getPostList() {
        List<Post> posts = postRepository.findAll();  // 데이터베이스에서 모든 Post를 가져옵니다.
        List<PostDto> postDtoList = new ArrayList<>();

        for (Post post : posts) {
            PostDto postDto = PostDto.builder()
                    .postPk(post.getId())
                    .title(post.getTitle())
                    .createdAt(post.getCreated_at())
                    .build();
            postDtoList.add(postDto);  // PostDto를 리스트에 추가
        }
        return postDtoList;
    }

    // 게시글 작성
    public void writePost(PostUploadDto postDto) {
        Member member = memberRepository.findById((long) postDto.getMemberPk())
                .orElse(null);

        if (member == null) {
            throw new IllegalArgumentException("유저를 찾을 수 없습니다.");
        }

        Post post = Post.builder()
                .member(member) // 유저 엔티티 설정
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



    @Transactional
    public void updatePost(PostUploadDto postUploadDto) {
        Long postId = postUploadDto.getPostPk();

        Optional<Post> postEntity = postRepository.findById(postId);
        // PostEntity id를 통해 가져오기

        // 없을시 예외처리
        if (!postEntity.isPresent()) {
            throw new PostNotFoundException("게시글을 찾을 수 없습니다.");
        }

        // postEntity
        Post post = postEntity.get();

        // title or content가 null 그리고 공백을 제거한 값이 없을시 커스텀 예외 발생
        if ((postUploadDto.getTitle() == null || Objects.equals(postUploadDto.getTitle().trim(), "")) &&
                (postUploadDto.getContent() == null || Objects.equals(postUploadDto.getContent().trim(), ""))) {
            throw new PostContentMissingException("제목 또는 내용을 입력해야 합니다.");
        }
        //내용이 null이 아니고 공백이 아닐시 내용 설정
        if (postUploadDto.getContent() != null && !Objects.equals(postUploadDto.getContent().trim(), "")) {
            post.setContent(postUploadDto.getContent());
        }
        // 제목의 경우
        if (postUploadDto.getTitle() != null && !Objects.equals(postUploadDto.getTitle().trim(), "")) {
            post.setTitle(postUploadDto.getTitle());
        }
        postRepository.save(post);
    }
}

