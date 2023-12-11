package com.oio.compositionservice.controller;

import com.oio.compositionservice.client.MemberServiceClient;
import com.oio.compositionservice.dto.member.LoginDto;
import com.oio.compositionservice.dto.member.Token;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/oio")
@RestController
@RequiredArgsConstructor
public class LoginController {
    private final MemberServiceClient memberServiceClient;

//    @PostMapping("/member-service/login")
//    public ResponseEntity<String> login(@RequestBody LoginDto dto){
//        Token token = memberServiceClient.login(dto);
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("accessToken", token.getAccessToken());
//        headers.set("refreshToken", token.getRefreshToken());
//        return ResponseEntity.ok();
//    }
}
