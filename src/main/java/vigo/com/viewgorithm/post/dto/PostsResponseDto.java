package vigo.com.viewgorithm.post.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class PostsResponseDto {
    private Long id;
    private String title;
    private LocalDateTime createdAt;
}
