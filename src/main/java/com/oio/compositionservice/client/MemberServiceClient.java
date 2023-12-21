package com.oio.compositionservice.client;

import com.oio.compositionservice.dto.PhoneDto;
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

//    @GetMapping("/member/member")
//    String getMember();

    @PostMapping("member/login")
    Token login(@RequestBody LoginDto dto);

    @PostMapping(value = "member/signup",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<String> signUp(@RequestPart MultipartFile file,
                                  @RequestParam Map<String, Object> map);

    @GetMapping("/member/{memberNickname}")
    Map<String,Object> showMember(@PathVariable String memberNickname);

    @PutMapping(value = "/member/{memberNickname}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    Map updateMember(@PathVariable String memberNickname,@RequestPart("file") MultipartFile file,
                        @RequestParam Map<String, Object> map);

    @DeleteMapping("/member/{memberNickname}")
    String deleteMemberByNickname(@PathVariable String memberNickname);

    @PostMapping("/member/email-chk")
    String idDupChk(EmailChkDto emailChkDto);

    @PostMapping("/member/nickname-chk")
    String emailDupChk(nicknameDto nicknameDto);

    @PostMapping("/member/send-email")
    Map<String,String> sendEmail(EmailChkDto emailRequest);

    @PostMapping("/member/send-sms")
    Map<String, String> sendPhone(PhoneDto phoneDto);

    @PostMapping("/member/{memberEmail}")
    Map<String,String> findPassword(@PathVariable String memberEmail, EmailChkDto dto);

    @PostMapping(value = "/member/report",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<String> report(@RequestPart List<MultipartFile> photos, @RequestParam Map<String, Object> dto);

    @PostMapping("/member/refresh")
    Map<String, Object> refresh(@SpringQueryMap LoginDto dto);

    @PostMapping("/member/phone-chk")
    String phoneDupChk(PhoneDto phoneNumber);

    @PostMapping("/member/find-email")
    Map<String, String> findId(PhoneDto phoneDto);
}
