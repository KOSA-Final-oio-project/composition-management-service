package com.oio.compositionservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "content-service")
public interface ContentServiceClient {

    @GetMapping("/member-service/member")
    String getMember();
}
