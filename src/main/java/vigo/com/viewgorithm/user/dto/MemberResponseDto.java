package vigo.com.viewgorithm.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import vigo.com.viewgorithm.user.domain.Member;


@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberResponseDto {

    private String userId;

    public static MemberResponseDto of(Member member) {
        return new MemberResponseDto(member.getUserId());
    }
}