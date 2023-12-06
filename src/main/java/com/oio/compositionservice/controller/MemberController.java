package com.oio.compositionservice.controller;

import com.oio.compositionservice.client.MemberServiceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/oio")
public class MemberController {

    private final MemberServiceClient memberServiceClient;

}
