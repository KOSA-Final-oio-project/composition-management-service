package com.oio.compositionservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "admin-service")
public interface AdminServiceClient {

    @GetMapping("/member-service/member")
    String getMember();
}
