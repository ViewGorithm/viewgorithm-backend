package vigo.com.viewgorithm.post.dto;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

// 게시글 업로드 Dto
@Data
@Builder
public class PostUploadDto {
    private String content;
    private String title;
    private Date createdAt;
}
