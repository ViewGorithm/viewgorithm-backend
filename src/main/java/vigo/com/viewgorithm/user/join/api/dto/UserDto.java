package vigo.com.viewgorithm.user.join.api.dto;


import lombok.Builder;
import lombok.Data;
import vigo.com.viewgorithm.user.join.domain.Role;

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

    // Role
    private Role role;

}
