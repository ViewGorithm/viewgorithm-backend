package vigo.com.viewgorithm.post.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class PostDto {
    private Long post_pk;
    private int userPk;
    private String title;
    private String content;
    private LocalDateTime createdAt;
}
