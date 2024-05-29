package vigo.com.viewgorithm.post.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vigo.com.viewgorithm.post.dto.PostDto;
import vigo.com.viewgorithm.post.service.PostContentMissingException;
import vigo.com.viewgorithm.post.service.PostNotFoundException;
import vigo.com.viewgorithm.post.service.PostProvider;
import vigo.com.viewgorithm.member.service.MemberService;

import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user/post")
public class PostController {
    private final MemberService memberService;
    private final PostProvider postProvider;


    // 게시글 전체 조회
    @GetMapping()

    public List<PostDto> get() {
        try {
            List<PostDto> postDtoList = postProvider.getPostList();

            // postDtoList가 null이면 예외를 던집니다.
            if(postDtoList == null){
                throw new NullPointerException();
            }
            return ResponseEntity.ok(postDtoList).getBody();  // 200 OK와 함께 리스트 반환

        } catch(NullPointerException e){
            throw new RuntimeException("Failed to fetch post list", e);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody PostDto postDto) {
        try {
            // 게시글 유효성 검사
            if (postDto == null || postDto.getTitle() == null || postDto.getContent() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("게시글 제목 또는 내용이 누락되었습니다.");
            }
            // 게시글 등록 기능 수행
            postProvider.writePost(postDto);

            return ResponseEntity.ok().body("게시글 등록 완료.");
        } catch (Exception e) {
            // 예외가 발생한 경우
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("게시글 등록 중 오류가 발생했습니다.");
        }
    }

    // 게시글 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) { // 게시글 pk 받음
        boolean isDeleted = postProvider.deletePost(id); // 게시글 삭제 후 boolean 타입 리턴

        if (isDeleted) {
            return ResponseEntity.ok("게시글 삭제 완료.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("게시글을 찾을 수 없습니다.");
        }
    }

    // 게시글 수정
    @PostMapping("/{userId}")
    public ResponseEntity<String> update(@RequestBody PostDto postDto, @PathVariable Long MemberPk) {
        boolean isUser = memberService.findByMemberId(MemberPk);

        if (!isUser) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("해당하는 유저를 찾을 수 없습니다.");
        }
        else try{
            postProvider.updatePost(postDto);
            return ResponseEntity.ok("게시글 수정 완료");
        } catch (PostContentMissingException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (PostNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        }
    }

