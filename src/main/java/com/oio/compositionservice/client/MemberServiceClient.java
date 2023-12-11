package com.oio.compositionservice.client;

import com.oio.compositionservice.dto.member.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.List;
import java.util.Map;

@FeignClient(name="member-service")
public interface MemberServiceClient {

    @GetMapping("/member-service/member")
    String getMember();

    @PostMapping("member-service/login")
    Token login(@RequestBody LoginDto dto);

    @PostMapping(value = "member-service/signup",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<String> signUp(@RequestPart MultipartFile file,
                                  @RequestParam Map<String, Object> map);

    @GetMapping("/member-service/member/{memberNickname}")
    String showMember(@PathVariable String memberNickname);

    @PutMapping("/member-service/{memberNickname}")
    String updateMember(@PathVariable String memberNickname, @RequestBody memberUpdateDto dto);

    @DeleteMapping("/member-service/member/{memberNickname}")
    String deleteMemberByNickname(@PathVariable String memberNickname);

    @PostMapping("/member-service/email-chk")
    String idDupChk(EmailChkDto emailChkDto);

    @PostMapping("/member-service/nickname-chk")
    String emailDupChk(nicknameDto nicknameDto);

    @PostMapping("/member-service/send-email")
    Map<String,String> sendEmail(EmailChkDto emailRequest);

    @PostMapping("/member-service/member/{memberEmail}")
    Map<String,String> findPassword(@PathVariable String memberEmail, EmailChkDto dto);

    @GetMapping("/member-service/report")
    ResponseEntity<String> report(@RequestPart List<CommonsMultipartFile> list, @RequestParam Map<String, Object> map);

    @PostMapping("/member-service/refresh")
    Map<String, Object> refresh(@SpringQueryMap LoginDto dto);
}
