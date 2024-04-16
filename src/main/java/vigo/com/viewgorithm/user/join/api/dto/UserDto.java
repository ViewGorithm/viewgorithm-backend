package vigo.com.viewgorithm.user.join.api.dto;


import lombok.Builder;
import lombok.Data;
import java.util.Date;

@Data
@Builder
public class UserDto {
    private String userId;
    private String password;
    private String name;
    private Date birth;
    private String sex;
    private String email;
    private Date createdAt;

    // role 을 위한 userType
    private String userType;

}
