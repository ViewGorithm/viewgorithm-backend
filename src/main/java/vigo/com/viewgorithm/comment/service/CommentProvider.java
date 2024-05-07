package vigo.com.viewgorithm.comment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vigo.com.viewgorithm.comment.domain.Comment;
import vigo.com.viewgorithm.comment.dto.CommentDto;
import vigo.com.viewgorithm.comment.repository.CommentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentProvider {

    private final CommentRepository commentRepository;

    public List<CommentDto> getCommentListByPostPk(Long id) {
        List<Comment> commentList = commentRepository.findByPost_Id(id);

        List<CommentDto> commentDtoList = new ArrayList<>();

        for (Comment comment : commentList){
            CommentDto commentDto = CommentDto.builder()
                    .commentPk(comment.getId())
                    .postPk(comment.getPost().getId())
                    .userPk(comment.getUser().getUser_pk())
                    .content(comment.getContent())
                    .createdAt(comment.getCreated_at())
                    .build();

            commentDtoList.add(commentDto);
        }

        return commentDtoList;
    }
}
