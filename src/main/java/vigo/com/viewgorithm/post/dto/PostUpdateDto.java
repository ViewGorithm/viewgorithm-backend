package vigo.com.viewgorithm.post.dto;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

// 게시글 수정 Dto
@Data
@Builder
public class PostUpdateDto {
    private Long postPk;
    private String content;
    private String title;
    private Date createdAt;
}
