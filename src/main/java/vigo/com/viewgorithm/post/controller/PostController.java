package vigo.com.viewgorithm.post.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vigo.com.viewgorithm.post.dto.PostDto;
import vigo.com.viewgorithm.post.service.PostProvider;
import vigo.com.viewgorithm.user.dto.UserDto;

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


}
