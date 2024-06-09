package vigo.com.viewgorithm.post.dto;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
// 게시글 전체 조회 Dto
public class PostDto {

    private Long id;
    private String title;
    private Date createdAt;

}
