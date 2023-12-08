package com.oio.compositionservice.dto.member;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberRequestDto {

    private String email;
    private String name;
    private String password;
    private String profile;
    private String phone;
    private String nickname;
    private String phoneNumber;

}
