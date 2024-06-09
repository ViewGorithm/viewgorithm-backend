package vigo.com.viewgorithm.post.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

// 게시글 상세내용 Dto
@Data
@Builder
public class PostDetailDto {

    private Long id;
    private String title;
    private Date createdAt;
    private String content;

}
