package vigo.com.viewgorithm.post.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

// 게시글 상세내용 Dto
@Data
@Builder
public class PostDetailDto {

    private Long postPk;
    private String title;
    private LocalDateTime createdAt;
    private String content;

}
