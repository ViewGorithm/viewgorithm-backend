package vigo.com.viewgorithm.comment.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vigo.com.viewgorithm.comment.dto.CommentDto;
import vigo.com.viewgorithm.comment.service.CommentProvider;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

    private final CommentProvider commentProvider;

    // 게시글의 댓글 조회
    @GetMapping("/{id}")
    public List<CommentDto> get(@PathVariable Long id) {
        List<CommentDto> commentDtoList = commentProvider.getCommentListByPostPk(id);
        return ResponseEntity.ok(commentDtoList).getBody();  // 200 OK와 함께 리스트 반환
    }





    


}
