package vigo.com.viewgorithm.user.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class SiginInDto {
    private String userid;
    private String password;
}
