package com.oio.compositionservice.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oio.compositionservice.client.MemberServiceClient;
import com.oio.compositionservice.dto.member.*;
import com.oio.compositionservice.module.Decoder;
import lombok.RequiredArgsConstructor;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Member;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/oio")
public class LoginController {

    private final ObjectMapper objectMapper;
    private final MemberServiceClient memberServiceClient;

    private final Decoder decoder;

    @PostMapping("/signup")
    public ResponseEntity<String> signUp( MemberRequestDto memberRequestDto,
                                         MultipartFile file,
                                          HttpServletRequest request) {
        File temp = new File("C:/oioback",file.getOriginalFilename());

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

    @PostMapping("/login")
    public Map<String,String> login(@RequestBody LoginDto loginDto, HttpServletResponse response){
        Map<String,String> responseMap = new HashMap<>();
        Token result= memberServiceClient.login(loginDto);
        if(result.getAccessToken().equals("fail") && result.getRefreshToken().equals("fail")){
            responseMap.put("result","fail");
            return responseMap;
        }

        if(result.getAccessToken().equals("withdrawal")){
            responseMap.put("result","withdrawal");
            return responseMap;
        }

        response.addHeader("accessToken", result.getAccessToken());
        response.addHeader("refreshToken", result.getRefreshToken());
        response.addHeader("Access-Control-Expose-Headers", "accessToken");
        responseMap.put("result","success");
        return responseMap;
    }

    @PostMapping("/logout")
    public String login(){
        return "success";
    }

}
