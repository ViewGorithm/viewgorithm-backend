package vigo.com.viewgorithm.comment.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
public class CommentDto {

    private Long commentPk;
    private Long postPk;
    private Long userPk;
    private String content;
    private LocalDateTime createdAt;
}
