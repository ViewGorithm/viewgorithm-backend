package vigo.com.viewgorithm.post.dto;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
// 게시글 전체 조회 Dto
public class PostDto {

    private Long postPk;
    private String title;
    private LocalDateTime createdAt;

}
