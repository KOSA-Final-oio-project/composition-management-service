package com.oio.compositionservice.client;

import com.oio.compositionservice.dto.LoginDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="member-service")
public interface MemberServiceClient {

    @GetMapping("/member-service/member")
    String getMember();

    @PostMapping("member-service/login")
    String login(@RequestBody LoginDto dto);
}
