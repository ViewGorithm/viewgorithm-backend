package vigo.com.viewgorithm.post.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class PostDto {
    private Long postPk;
    private Long memberPk;
    private String title;
    private String content;
    private LocalDateTime createdAt;
}
