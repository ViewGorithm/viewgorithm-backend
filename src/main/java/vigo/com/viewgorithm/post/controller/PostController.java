package vigo.com.viewgorithm.post.controller;


import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vigo.com.viewgorithm.post.dto.PostDto;
import vigo.com.viewgorithm.post.service.PostProvider;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user/post")
public class PostController {

    private final PostProvider postProvider;

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody PostDto postDto){

        postProvider.writePost(postDto); // 게시글 등록 기능 수행

        return ResponseEntity.ok().body("게시글 등록 완료.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) { // 게시글 pk 받음
        boolean isDeleted = postProvider.deletePost(id); // 게시글 삭제 후 boolean 타입 리턴

        if (isDeleted) {
            return ResponseEntity.ok("게시글 삭제 완료.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("게시글을 찾을 수 없습니다.");
        }
    }
}

