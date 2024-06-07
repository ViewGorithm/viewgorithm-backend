package vigo.com.viewgorithm.post.dto;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

// 게시글 업로드 Dto
@Data
@Builder
public class PostUploadDto {
    private Long postPk;
    private String content;
    private String title;
    private LocalDateTime createdAt;
}
