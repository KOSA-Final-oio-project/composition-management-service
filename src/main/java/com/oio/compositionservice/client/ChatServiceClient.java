package com.oio.compositionservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "chat-service")
public interface ChatServiceClient {

    @GetMapping("/member-service/member")
    String getMember();
}
