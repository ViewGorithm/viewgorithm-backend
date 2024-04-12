package vigo.com.viewgorithm.user.auth.api.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class SiginInDto {
    private String userId;
    private String password;
}
