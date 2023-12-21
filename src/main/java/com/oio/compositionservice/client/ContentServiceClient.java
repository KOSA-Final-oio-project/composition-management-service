package com.oio.compositionservice.client;

import com.oio.compositionservice.dto.content.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.Path;
import java.util.List;
import java.util.Map;

@FeignClient(name = "content-service")
public interface ContentServiceClient {
    @PostMapping("/post/register/{nickname}")
    Map<String,Long> create(@PathVariable String nickname, @SpringQueryMap PostDto postDto);

    @GetMapping("/posts/{category}")
    PageResponseDto getPosts(@PathVariable String category, @SpringQueryMap PageRequestDto pageRequestDto);

    @GetMapping("posts/member/{nickname}")
    ResponseEntity<List<PostDto>> getPostsByNickname(@PathVariable String nickname);

    @GetMapping("/post/{pno}/{nickname}")
    ResponseEntity<Map<String,Object>> getPost(@PathVariable Long pno,@PathVariable String nickname);

    @PutMapping("/post/{pno}")
    ResponseEntity<ResponsePostModify> modify(@PathVariable Long pno, @SpringQueryMap RequestPostModify requestPostModify);

    @DeleteMapping("/post/{pno}")
    Map<String, Long> remove(@PathVariable Long pno,@SpringQueryMap RequestPostRemove requestPostRemove);

    @PostMapping("/reply/register/{nickName}")
    ResponseEntity<Map<String, String>> registerReply(@PathVariable String nickName, @ModelAttribute ReplyDto replyDto);

    @PutMapping("/reply/{rno}")
    ResponseEntity<ResponseReplyModify> modifyReply(@PathVariable Long rno,@ModelAttribute RequestReplyModify requestReplyModify);

    @DeleteMapping("/reply/{rno}/{pno}")
    ResponseEntity<Map<String, String>> removeReply(@PathVariable("pno") Long pno, @PathVariable("rno") Long rno);

    @GetMapping("/replies/{pno}/{nickname}")
    ResponseEntity<Map<String, Object>> getReplies(@PathVariable("pno") Long pno, @PathVariable("nickname") String nickname);
}
