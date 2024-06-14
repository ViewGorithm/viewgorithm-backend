package vigo.com.viewgorithm.post.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vigo.com.viewgorithm.post.domain.Post;
import vigo.com.viewgorithm.post.dto.PostDetailDto;
import vigo.com.viewgorithm.post.dto.PostDto;
import vigo.com.viewgorithm.post.dto.PostUpdateDto;
import vigo.com.viewgorithm.post.dto.PostUploadDto;
import vigo.com.viewgorithm.post.error.PostContentMissingException;
import vigo.com.viewgorithm.post.error.PostNotFoundException;
import vigo.com.viewgorithm.post.repository.PostRepository;
import vigo.com.viewgorithm.member.repository.MemberRepository;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@Service
@RequiredArgsConstructor
public class PostProvider {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    // 전체 게시글 조회
    public List<PostDto> getPostList() {
        List<Post> posts = postRepository.findAll();
        List<PostDto> postDtoList = new ArrayList<>();

        for (Post post : posts) {
            PostDto postDto = PostDto.builder()
                    .id(post.getId())
                    .title(post.getTitle())
                    .createdAt(post.getCreated_at()) // Date 타입 그대로 사용
                    .build();
            postDtoList.add(postDto);
        }
        return postDtoList;
    }

    // 개별 게시글 조회
    public PostDetailDto getPostDetail(Long id) {
        Optional<Post> postEntity = postRepository.findById(id);

        if (!postEntity.isPresent()) {
            throw new PostNotFoundException("게시글을 찾을 수 없습니다.");
        }

        Post post = postEntity.get();

        return PostDetailDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .createdAt(post.getCreated_at()) // Date 타입 그대로 사용
                .content(post.getContent())
                .build();
    }

    // 게시글 작성
    public void writePost(PostUploadDto postDto) {
        Post post = Post.builder()
                .title(postDto.getTitle())
                .content(postDto.getContent())
                .created_at(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()))  // 현재 시간 설정
                .build();
        postRepository.save(post);
    }

    // 게시글 삭제
    public boolean deletePost(Long id) {
        if (postRepository.existsById(id)) {
            postRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    // 게시글 수정
    @Transactional
    public void updatePost(PostUpdateDto postUpdateDto) {
        Long postId = postUpdateDto.getPostPk();

        Optional<Post> postEntity = postRepository.findById(postId);

        if (!postEntity.isPresent()) {
            throw new PostNotFoundException("게시글을 찾을 수 없습니다.");
        }

        Post post = postEntity.get();

        if ((postUpdateDto.getTitle() == null || Objects.equals(postUpdateDto.getTitle().trim(), "")) &&
                (postUpdateDto.getContent() == null || Objects.equals(postUpdateDto.getContent().trim(), ""))) {
            throw new PostContentMissingException("제목 또는 내용을 입력해야 합니다.");
        }

        if (postUpdateDto.getContent() != null && !Objects.equals(postUpdateDto.getContent().trim(), "")) {
            post.setContent(postUpdateDto.getContent());
        }

        if (postUpdateDto.getTitle() != null && !Objects.equals(postUpdateDto.getTitle().trim(), "")) {
            post.setTitle(postUpdateDto.getTitle());
        }
        postRepository.save(post);
    }
}
