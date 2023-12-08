package com.oio.compositionservice.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.oio.compositionservice.client.MemberServiceClient;
import com.oio.compositionservice.dto.member.*;
import com.oio.compositionservice.module.Decoder;
import lombok.RequiredArgsConstructor;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.io.IOUtils;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/oio")
@JsonSerialize
public class MemberController {

    private final ObjectMapper objectMapper;
    private final MemberServiceClient memberServiceClient;

    private final Decoder decoder;

    //회원가입 test
    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestPart("memberRequestDto") MemberRequestDto memberRequestDto,
                                         MultipartFile file) {

        File temp = new File("/Users/hongsikcho/oio",file.getOriginalFilename());

        try {
            file.transferTo(temp);//임시파일 생성
            FileItem fileItem =
                    new org.apache.commons.fileupload.disk.DiskFileItem(
                            "file",
                            Files.probeContentType(temp.toPath()),
                            false,
                            //temp.getName(),
                            file.getOriginalFilename(),
                            (int) temp.length(),
                            temp.getParentFile()
                    );


            InputStream input =  new FileInputStream(temp);
            OutputStream output = fileItem.getOutputStream();
            IOUtils.copy(input, output);
            MultipartFile mFile = new CommonsMultipartFile(fileItem);
            /*-------------------*/
            input.close();
            output.close();

            Map<String, Object> map = objectMapper.convertValue(memberRequestDto, new TypeReference<Map<String, Object>>() {});
            ResponseEntity<String> result = memberServiceClient.signUp(mFile, map);

            return result;
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    //회원 정보 조회 test
    @GetMapping("/member/{memberNickname}")
    public String showMember(@PathVariable String memberNickname){
        String result = memberServiceClient.showMember(memberNickname);

        return result;
    }

    //로그인 test
    @PostMapping("/login")
    public String login(@RequestBody LoginDto loginDto, HttpServletResponse response){
        String result = memberServiceClient.login(loginDto);
        response.addHeader("token",result);
        return "success";
    }

    //회원 수정 test
    @PutMapping("/member/{memberNickname}")
    public String updateMember(@PathVariable String memberNickname, @RequestBody memberUpdateDto dto){
        String result = memberServiceClient.updateMember(memberNickname,dto);
        return result;
    }

    //회원 삭제 test
    @DeleteMapping("member/{memberNickname}")
    public String deleteMember(@PathVariable String memberNickname){
        String result = memberServiceClient.deleteMemberByNickname(memberNickname);
        return result;
    }

    //이메일 중복체크 test
    @PostMapping("/email-chk")
    public String idDupChk(@RequestBody EmailChkDto emailChkDto){
        String result = memberServiceClient.idDupChk(emailChkDto);
        return result;
    }

    //닉네임 중복체크 test
    @PostMapping("/nickname-chk")
    public String nicknameDupChk(@RequestBody nicknameDto nicknameDto){
        String result = memberServiceClient.emailDupChk(nicknameDto);

        return result;
    }

    //이메일 전송 test
    @PostMapping("/send-email")
    public Map<String,String> sendEmail(@RequestBody EmailChkDto emailRequest) {
        Map<String, String> result = memberServiceClient.sendEmail(emailRequest);
        return result;
    }

    //비밀번호 찾기 test
    @PostMapping("/member/{memberEmail}")
    public Map<String,String> findPassword(@PathVariable String memberEmail ,@RequestBody EmailChkDto dto){
        Map<String,String> result = memberServiceClient.findPassword(memberEmail,dto);
        return result;
    }

    @GetMapping("member/{memberNickname}/report")
    public ResponseEntity<String> reportMember(@PathVariable String memberNickname, @RequestPart("reportDto") ReportDto dto,
                                               @RequestPart("photos") List<MultipartFile> photos, HttpServletRequest request){

        String nickname = decoder.decode(request);
        dto.setReportedNickname(memberNickname);
        dto.setReporterNickname(nickname);
        System.out.println(nickname);
        CommonsMultipartFile mFile = null;
        List<CommonsMultipartFile> list = new ArrayList();
        for(MultipartFile file : photos) {
            File temp = new File("/Users/hongsikcho/oio", file.getOriginalFilename());

            try {
                file.transferTo(temp);//임시파일 생성
                FileItem fileItem =
                        new org.apache.commons.fileupload.disk.DiskFileItem(
                                "file",
                                Files.probeContentType(temp.toPath()),
                                false,
                                //temp.getName(),
                                file.getOriginalFilename(),
                                (int) temp.length(),
                                temp.getParentFile()
                        );


                InputStream input = new FileInputStream(temp);
                OutputStream output = fileItem.getOutputStream();
                IOUtils.copy(input, output);
                mFile = new CommonsMultipartFile(fileItem);
                /*-------------------*/
                input.close();
                output.close();

                list.add(mFile);
            } catch (IllegalStateException | IOException e) {
                e.printStackTrace();
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }

        }
        Map<String, Object> map = objectMapper.convertValue(dto, new TypeReference<Map<String, Object>>() {
        });
        ResponseEntity<String> result = memberServiceClient.report(list, map);


        return result;
    }


}
