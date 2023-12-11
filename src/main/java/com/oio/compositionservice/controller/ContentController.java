package com.oio.compositionservice.controller;


import com.oio.compositionservice.client.ContentServiceClient;
import com.oio.compositionservice.dto.content.*;
import com.oio.compositionservice.module.Decoder;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/oio")
public class ContentController {

    private final ContentServiceClient contentServiceClient;
    private final Decoder decoder;
    
    @PostMapping(value = "/post/register")
    public Map<String, Long> register(HttpServletRequest request,@RequestBody PostDto postDto) {
        String nickname = decoder.decode(request);
        Map result = contentServiceClient.create(nickname,postDto);
        return result;
    }

    @GetMapping("/posts/{category}")
    public ResponseEntity<PageResponseDto> getPosts(@PathVariable("category") String category, @SpringQueryMap PageRequestDto pageRequestDto) {
        PageResponseDto result = contentServiceClient.getPosts(category,pageRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/posts/member")
    public ResponseEntity<List<PostDto>> getPostsByNickname(HttpServletRequest request) {
        String nickname = decoder.decode(request);
        ResponseEntity<List<PostDto>> result = contentServiceClient.getPostsByNickname(nickname);
        return result;
    }

    @GetMapping("/post/{pno}")
    public ResponseEntity<Map<String, Object>> getPost(HttpServletRequest request,@PathVariable("pno") Long pno) {
        String nickname = decoder.decode(request);
        ResponseEntity<Map<String,Object>> result = contentServiceClient.getPost(pno,nickname);
        return result;
    }

    @PutMapping(value = "/post/{pno}")
    public ResponseEntity<ResponsePostModify> modify(@PathVariable("pno") Long pno,
                                                     @RequestBody RequestPostModify requestPostModify){
        ResponseEntity<ResponsePostModify> result = contentServiceClient.modify(pno,requestPostModify);
        return result;
    }

    @DeleteMapping ("/post/{pno}")
    public Map<String, Long> remove(@PathVariable("pno") Long pno,@RequestBody RequestPostRemove requestPostRemove) {
        Map<String,Long> result = contentServiceClient.remove(pno,requestPostRemove);
        return result;
    }


    //답글
    @PostMapping(value = "/reply/register")
    public ResponseEntity<Map<String, String>> register(HttpServletRequest request, @RequestBody ReplyDto replyDto){
        String nickname = decoder.decode(request);
        ResponseEntity<Map<String,String>> result = contentServiceClient.registerReply(nickname,replyDto);
        return result;
    }

    @PutMapping(value = "/reply/{rno}")
    public ResponseEntity<ResponseReplyModify> modify(@PathVariable("rno") Long rno, @RequestBody RequestReplyModify RequestReplyModify){

        ResponseEntity<ResponseReplyModify> result = contentServiceClient.modifyReply(rno,RequestReplyModify);
        return result;
    }

    @DeleteMapping("/reply/{rno}/{pno}")
    public ResponseEntity<Map<String, String>> remove(@PathVariable("rno") Long rno, @PathVariable("pno") Long pno) {

        ResponseEntity<Map<String,String>> result = contentServiceClient.removeReply(rno,pno);
        return result;
        }

    @GetMapping("/replies/{pno}")
    public ResponseEntity<Map<String, Object>> getReplies(HttpServletRequest request,@PathVariable("pno") Long pno) {
        String nickname = decoder.decode(request);
        ResponseEntity<Map<String,Object>> result = contentServiceClient.getReplies(pno,nickname);
        return result;
    }
}
