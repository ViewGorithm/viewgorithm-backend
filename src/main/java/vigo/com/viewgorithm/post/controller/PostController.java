package vigo.com.viewgorithm.post.controller;


import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vigo.com.viewgorithm.post.dto.PostDto;
import vigo.com.viewgorithm.post.service.PostProvider;
import vigo.com.viewgorithm.user.auth.service.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user/post")
public class PostController {
    private final UserService userProvider;
    private final PostProvider postProvider;


    // 게시글 전체 조회
    @GetMapping()
    public List<PostDto> get() {

        List<PostDto> postDtoList = postProvider.getPostList();

        return ResponseEntity.ok(postDtoList).getBody();  // 200 OK와 함께 리스트 반환
    }


    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody PostDto postDto) {

        postProvider.writePost(postDto); // 게시글 등록 기능 수행

        return ResponseEntity.ok().body("게시글 등록 완료.");
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
    public ResponseEntity<String> update(@RequestBody PostDto postDto, @PathVariable Long userId) {
        boolean isUpdated = postProvider.updatePost(postDto);
        // 그니까 컨트롤러에서 해당 유저가 있는지 없는지 알기 위해서는 userService

        boolean isUser = userProvider.findByUserId(userId);

        if (!isUser) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("해당하는 유저를 찾을 수 없습니다.");
        }

        if (isUpdated) {
            return ResponseEntity.ok("게시글 수정 완료.");
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("게시글을 찾을 수 없습니다.");
        }
    }

    }